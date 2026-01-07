package mvc.persistence.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.user.UserDTO;

public class UserDAOImpl implements UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private UserDTO vo = null;

	// 1. 생성자 DI 
	public UserDAOImpl() {
		super(); 
	} 
	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	// 2. Setter DI
	public void setConn(Connection conn) {
		this.conn = conn;
	} 
	public Connection getConn() {
		return conn;
	}
	
	
//	관리자 페이지에서 사용할 전체 회원 조회
	@Override
	public List<UserDTO> select() throws SQLException {
		String sql = " SELECT \"user_id\", \"password\", \"name\", \"phone_no\", \"email\", \"join_date\", \"admin\", \"gender\", \"birth\", \"nickname\" "
					+ "FROM \"user\" ";
		
		ArrayList<UserDTO> list = null;
		
		String user_id, password, name, phone_no, email, gender, nickname;
		Date join_date, birth;
		int admin;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<UserDTO>();
				do {
					user_id = rs.getString("user_id");
					password = rs.getString("password");
					name = rs.getString("name");
					phone_no = rs.getString("phone_no");
					email = rs.getString("email");
					join_date = rs.getDate("join_date");
					admin = rs.getInt("admin");
					gender = rs.getString("gender");
					birth = rs.getDate("birth");
					nickname = rs.getString("nickname");
					
					vo = new UserDTO().builder()
							.user_id(user_id)
							.password(password)
							.name(name)
							.phone_no(phone_no)
							.email(email)
							.join_date(join_date)
							.admin(admin)
							.gender(gender)
							.birth(birth)
							.nickname(nickname)
							.build();
					list.add(vo);
					
				}while(rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	// 로그인
	@Override
	public UserDTO selectByIdAndPwd(String userId, String pwd) throws SQLException {
		String sql = " SELECT \"user_id\", \"password\", \"name\", \"email\" "
					+ " FROM \"user\" "
					+ " WHERE \"user_id\" = ? AND \"password\" = ? ";
		
		String user_id, password, name, email;
		
		UserDTO loginUser = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginUser = new UserDTO();
				loginUser.setUser_id(rs.getString("user_id"));
				loginUser.setName(rs.getString("name"));
				loginUser.setEmail(rs.getString("email"));
			}
			
		}finally {
			if (rs != null) rs.close();
		    if (pstmt != null) pstmt.close();
		}
		
		
		
		return loginUser;
	}
	
	// 아이디찾기
	@Override
	public String findUserIdByPhone(String name, String phone) throws SQLException {
		String sql = "SELECT \"user_id\""
					+ " FROM \"user\" "
					+ " WHERE \"name\"=? AND REPLACE (\"phone_no\", '-', '') =?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, phone);
	        rs = pstmt.executeQuery();
	        if (rs.next()) return rs.getString("user_id");
	        return null;
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	}
	@Override
	public String findUserIdByEmail(String name, String email) throws SQLException {
		String sql = "SELECT \"user_id\""
					+ " FROM \"user\" "
					+ " WHERE \"name\"=? AND \"email\"=?";
	    try { 
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, email);
	        rs = pstmt.executeQuery();
	        if (rs.next()) return rs.getString("user_id");
	        return null;
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	}
	
	// 비밀번호 재발급
	@Override
	public int resetPwd(String userId, String tempPwd) throws SQLException {
		String sql = "UPDATE \"user\" "
					+ " SET \"password\"=? "
					+ " WHERE \"user_id\"=?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, tempPwd);
	        pstmt.setString(2, userId);
	        return pstmt.executeUpdate();
	    } finally {
	        if (pstmt != null) pstmt.close();
	    }
	}
	
	// 아이디 중복확인
	@Override
	public boolean existsByUserId(String userId) throws SQLException {
		String sql = "SELECT COUNT(*) "
					+ " FROM \"user\" " 
					+ " WHERE \"user_id\" = ?";
	    try {
	    	
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        rs = pstmt.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	        return false;
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	}
	
	// 닉네임 중복확인
	@Override
	public boolean existsByNickname(String nickname) throws SQLException {
		String sql = "SELECT COUNT(*) "
					+ " FROM \"user\" "
					+ " WHERE \"nickname\" = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, nickname);
	        rs = pstmt.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	        return false;
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	}
	
	// 이메일 중복확인
	@Override
	public boolean existsByEmail(String email) throws SQLException {
		String sql = "SELECT COUNT(*) "
					+ " FROM \"user\" "
					+ " WHERE \"email\" = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, email);
	        rs = pstmt.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	        return false;
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	}
	
	
	// 휴대폰 번호 중복확인
	@Override
	public boolean existsByPhone(String phone) throws SQLException {
		String sql = "SELECT COUNT(*)"
					+ " FROM \"user\" "
					+ " WHERE \"phone_no\" = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone.replaceAll("[^0-9]", ""));
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1) > 0;
			return false;
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();			
		}
		
	}
	
	
	// 회원가입
	@Override
	public int insert(UserDTO user) throws SQLException {
	    String sql = " INSERT INTO \"user\" "
	               + " (\"user_id\", \"password\", \"name\", \"phone_no\", \"email\", \"join_date\", \"admin\", \"gender\", \"birth\", \"nickname\") "
	               + " VALUES (?, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?) ";

	    int result = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user.getUser_id());
	        pstmt.setString(2, user.getPassword());
	        pstmt.setString(3, user.getName());
	        pstmt.setString(4, user.getPhone_no());
	        pstmt.setString(5, user.getEmail());
	        pstmt.setString(6, user.getGender());

	        if (user.getBirth() != null) pstmt.setDate(7, new java.sql.Date(user.getBirth().getTime()));
	        else pstmt.setNull(7, java.sql.Types.DATE);

	        pstmt.setString(8, user.getNickname());

	        result = pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return result;
	}
	
	public int insertUserTermsBatch(String userId, int[] termsIds) throws SQLException {
	    String sql = "INSERT INTO \"user_terms\" (\"user_id\", \"terms_id\", \"agreed_yn\", \"agreed_at\") "
	               + "VALUES (?, ?, 1, SYSDATE)";
	    int[] counts;
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        for (int tid : termsIds) {
	            pstmt.setString(1, userId);
	            pstmt.setInt(2, tid);
	            pstmt.addBatch();
	        }
	        counts = pstmt.executeBatch();
	    }
	    return counts.length; // 넣은 개수
	}

	
	
	@Override
	public boolean checkPassword(String userId, String oldPwd) throws SQLException {
	    String sql = "SELECT 1 FROM \"user\" WHERE \"user_id\"=? AND \"password\"=?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, userId);
	        pstmt.setString(2, oldPwd);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next();
	        }
	    }
	}

	@Override
	public int updatePassword(String userId, String newPwd) throws SQLException {
	    String sql = "UPDATE \"user\" SET \"password\"=? WHERE \"user_id\"=?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, newPwd);
	        pstmt.setString(2, userId);
	        return pstmt.executeUpdate();
	    }
	}

	@Override
	public boolean isNicknameAvailable(String myUserId, String nickname) throws SQLException {
	    // 내 아이디 제외하고 같은 닉네임 존재하면 false
	    String sql = "SELECT 1 FROM \"user\" WHERE \"nickname\"=? AND \"user_id\"<>?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, nickname);
	        pstmt.setString(2, myUserId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return !rs.next();
	        }
	    }
	}

	@Override
	public boolean isEmailAvailable(String myUserId, String email) throws SQLException {
	    String sql = "SELECT 1 FROM \"user\" WHERE \"email\"=? AND \"user_id\"<>?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, email);
	        pstmt.setString(2, myUserId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return !rs.next();
	        }
	    }
	}

	@Override
	public int updateProfile(String userId, String nickname, String email, String phoneNo, String profileImgPath) throws SQLException {
	    String sql = "UPDATE \"user\" "
	               + "SET \"nickname\"=?, \"email\"=?, \"phone_no\"=?, \"profile_img_path\"=? "
	               + "WHERE \"user_id\"=?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, nickname);
	        pstmt.setString(2, email);
	        pstmt.setString(3, phoneNo);
	        pstmt.setString(4, profileImgPath);
	        pstmt.setString(5, userId);
	        return pstmt.executeUpdate();
	    }
	}

	@Override
	public UserDTO selectByUserId(String userId) throws SQLException {
	    String sql = "SELECT \"user_id\", \"name\", \"nickname\", \"email\", \"phone_no\", \"profile_img_path\" "
	               + "FROM \"user\" WHERE \"user_id\"=?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, userId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (!rs.next()) return null;
	            UserDTO dto = new UserDTO();
	            dto.setUser_id(rs.getString("user_id"));
	            dto.setName(rs.getString("name"));
	            dto.setNickname(rs.getString("nickname"));
	            dto.setEmail(rs.getString("email"));
	            dto.setPhone_no(rs.getString("phone_no"));
	            dto.setProfile_img_path(rs.getString("profile_img_path"));
	            return dto;
	        }
	    }
	}
	
	@Override
	public UserDTO selectByUserIdAndNameAndPhone(String userId, String name, String phone) throws SQLException {
		String sql = "SELECT \"user_id\", \"name\", \"phone_no\" "
					+ " FROM \"user\" "
					+ " WHERE \"user_id\" = ? AND \"name\"=? AND REPLACE(\"phone_no\", '-', '')=? ";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(!rs.next()) return null;
				UserDTO dto = new UserDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("phone_no"));
				return dto;
			}
		}
		
	}
	

	

	
	
	
}
