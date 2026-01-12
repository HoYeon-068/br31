package mvc.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import mvc.domain.user.UserDTO;
import mvc.persistence.user.UserDAO;
import mvc.persistence.user.UserDAOImpl;

public class UserService {

    // 로그인 업무 메서드
    public UserDTO login(String userId, String password) throws Exception {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();
            UserDAO dao = new UserDAOImpl(conn);

            // DAO 호출 (DB 조회)
            UserDTO loginUser = dao.selectByIdAndPwd(userId, password);

            return loginUser;

        } finally {
            if (conn != null) conn.close();
        }
    }

    // 회원가입
    public int join(UserDTO user, int[] termsIds) {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false); 

            UserDAO dao = new UserDAOImpl(conn);

            // 1) 중복검사
            if (dao.existsByUserId(user.getUser_id())) return 0;
            if (dao.existsByNickname(user.getNickname())) return 0;
            if (dao.existsByEmail(user.getEmail())) return 0;
            if (dao.existsByPhone(user.getPhone_no())) return 0;
            
            user.setPhone_no(user.getPhone_no().replaceAll("[^0-9]", ""));


            // user insert
            int inserted = dao.insert(user);
            if (inserted != 1) {
                conn.rollback();
                return 0;
            }

            // 약관 insert (선택된 termsIds만)
            if (termsIds != null && termsIds.length > 0) {
                int cnt = dao.insertUserTermsBatch(user.getUser_id(), termsIds);

                // 넣은 개수 검증(선택한 개수와 같아야 정상)
                if (cnt != termsIds.length) {
                    conn.rollback();
                    return 0;
                }
            }

            conn.commit(); // 전부 성공
            return 1;

        } catch (SQLException | NamingException e) {
            try { if (conn != null) conn.rollback(); } catch (Exception ignore) {}
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception ignore) {}
        }
    }
	
	
	
	public boolean isUserIdTaken(String userId) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).existsByUserId(userId);
        } finally {
            if (conn != null) conn.close();
        }
    }

    public boolean isNicknameTaken(String nickname) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).existsByNickname(nickname);
        } finally {
            if (conn != null) conn.close();
        }
    }

    public boolean isEmailTaken(String email) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).existsByEmail(email);
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    public boolean isPhoneTaken(String phone) throws Exception {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return new UserDAOImpl(conn).existsByPhone(phone);
        }
    }

    
    
    public String findUserIdByPhone(String name, String phone) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            String normalizePhone = normalizePhone(phone);
            
            
            return new UserDAOImpl(conn).findUserIdByPhone(name, normalizePhone);
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    // 전화번호 하이픈 공백 제거
    private String normalizePhone(String phone) {
    	if(phone == null) return null;
    	return phone.replaceAll("[^0-9]", "");
    }

    public String findUserIdByEmail(String name, String email) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).findUserIdByEmail(name, email);
        } finally {
            if (conn != null) conn.close();
        }
    }

    public int resetPasswordByUserIdAndPhoneAndName(String userId, String tempPwd, String name, String phone) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            UserDAO dao = new UserDAOImpl(conn); 
            String normalizePhone = normalizePhone(phone);
            UserDTO user =  dao.selectByUserIdAndNameAndPhone(userId, name, normalizePhone);
            
            if(user == null) {
            	return 0;
            }
            return dao.resetPwd(userId, tempPwd);
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    
    public UserDTO getUserById(String userId) throws Exception {
    	Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();

            
            return new UserDAOImpl(conn).selectByUserId(userId);


        } finally {
            if (conn != null) conn.close();
        }
    	
    }
    
    public List<UserDTO> getUserList() throws Exception {
    	Connection conn = null;
    	try {
    		conn= ConnectionProvider.getConnection();
    		
    		return new UserDAOImpl(conn).getUserList();
    		
    		
    	} finally {
			if(conn != null) conn.close();
		}
    }
    
    public int deleteUser(String userId) throws Exception{
    	Connection conn = null;
    	try {
    		conn = ConnectionProvider.getConnection();
    		return new UserDAOImpl(conn).deleteUser(userId);
    	}finally {
			if(conn != null) conn.close();
		}
    }
    
    public boolean checkPassword(String userId, String pwd) throws Exception{
    	Connection conn = null;
    	try {
    		conn = ConnectionProvider.getConnection();
    		return new UserDAOImpl(conn).checkPassword(userId, pwd);
    		
    	}finally {			
    		if(conn != null) conn.close();
		}
    	
    }
    
}
