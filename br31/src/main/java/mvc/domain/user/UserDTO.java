package mvc.domain.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mvc.domain.menu.ProductDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private String user_id;
	private String password;
	private String name;
	private String phone_no;  // 앞에 0이 사라져서 String
	private String email;
	private Date join_date;
	private int admin;
	private String gender;
	private Date birth;
	private String nickname;
	

}
