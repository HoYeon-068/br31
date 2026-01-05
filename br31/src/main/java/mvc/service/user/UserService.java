package mvc.service.user;

import java.sql.Connection;
import java.sql.SQLException;

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
            conn.setAutoCommit(false); // ✅ 트랜잭션 시작

            UserDAO dao = new UserDAOImpl(conn);

            // 1) 중복검사
            if (dao.existsByUserId(user.getUser_id())) return 0;
            if (dao.existsByNickname(user.getNickname())) return 0;
            if (dao.existsByEmail(user.getEmail())) return 0;

            // 2) user insert
            int inserted = dao.insert(user);
            if (inserted != 1) {
                conn.rollback();
                return 0;
            }

            // 3) 약관 insert (선택된 termsIds만)
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
    
    
    public String findUserIdByPhone(String name, String phone) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).findUserIdByPhone(name, phone);
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

    public int resetPassword(String userId, String tempPwd) throws Exception {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return new UserDAOImpl(conn).resetPwd(userId, tempPwd);
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
}
