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
		String sql = " SELECT user_id, password, name, phone_no, email, join_date, admin, gender, birth, nickname "
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
		String sql = " SELECT user_id, password "
					+ " FROM \"user\" "
					+ " WHERE user_id = ? AND password = ? ";
		
		
		
		
		return null;
	}
	
	// 아이디찾기
	@Override
	public String findUserIdByPhone(String name, String phone) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findUserIdByEmail(String name, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 비밀번호 재발급
	@Override
	public int resetPwd(String userId, String tempPwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 아이디 중복확인
	@Override
	public boolean existsByUserId(String userId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 닉네임 중복확인
	@Override
	public boolean existsByNickname(String nickname) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 이메일 중복확인
	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 회원가입
	@Override
	public int insert(UserDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 마이페이지 화면
	@Override
	public UserDTO selectByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 프로필 수정
	@Override
	public int updateProfile(UserDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 비밀번호 수정
	@Override
	public int updatePassword(String userId, String newPwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
