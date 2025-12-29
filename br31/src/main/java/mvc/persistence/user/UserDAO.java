package mvc.persistence.user;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.user.UserDTO;

public interface UserDAO  {

	List<UserDTO> select() throws SQLException;
	
	// 로그인
	UserDTO selectByIdAndPwd(String userId, String pwd) throws SQLException;
	
	
	// 아이디 찾기
	String findUserIdByPhone(String name, String phone);
	String findUserIdByEmail(String name, String email);

	// 비밀번호 재발급
	int resetPwd(String userId, String tempPwd );



	// 중복 체크
	boolean existsByUserId(String userId);
	boolean existsByNickname(String nickname);
	boolean existsByEmail(String email);

	// 회원가입
	int insert(UserDTO user);

	// 마이페이지
	UserDTO selectByUserId(String userId);
	int updateProfile(UserDTO user);
	int updatePassword(String userId, String newPwd);

}
