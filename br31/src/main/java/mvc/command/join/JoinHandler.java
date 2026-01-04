package mvc.command.join;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class JoinHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// GET이면 가입 폼
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return "/views/join/join.jsp";
        }

        // POST면 가입 처리
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String phoneNo = request.getParameter("phone_no");

        // 이메일 합치기
        String emailId = request.getParameter("email_id");
        String emailDomain = request.getParameter("email_domain");

        String email = null;
        if (emailId != null && !emailId.isBlank() &&
            emailDomain != null && !emailDomain.isBlank() &&
            !"직접입력".equals(emailDomain)) {
            email = emailId + "@" + emailDomain;
        } else if (emailId != null && !emailId.isBlank() &&
                   emailDomain != null && !emailDomain.isBlank() &&
                   "직접입력".equals(emailDomain)) {
            // (직접입력 구현했을 때만 사용)
            email = emailId; 
        }

        // 성별
        String gender = request.getParameter("gender"); // M/F/N

        // 생년월일
        String by = request.getParameter("birthY");
        String bm = request.getParameter("birthM");
        String bd = request.getParameter("birthD");

        Date birth = null;
        if (by != null && bm != null && bd != null &&
            !by.isBlank() && !bm.isBlank() && !bd.isBlank()) {

            int mm = Integer.parseInt(bm);
            int dd = Integer.parseInt(bd);

            String birthStr = String.format("%s-%02d-%02d", by, mm, dd);
            birth = Date.valueOf(birthStr);
        }

        // DTO 만들기
        UserDTO user = new UserDTO();
        user.setUser_id(userId);
        user.setPassword(password);
        user.setName(name);
        user.setNickname(nickname);
        user.setPhone_no(phoneNo);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirth(birth);

        // 약관 ids 받기 (체크박스 name="terms_ids")
        String[] arr = request.getParameterValues("terms_ids");
        int[] termsIds = null;

        if (arr != null && arr.length > 0) {
            termsIds = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                termsIds[i] = Integer.parseInt(arr[i]);
            }
        }

        // 서비스 호출 (한 번만!)
        int result = userService.join(user, termsIds);

        if (result == 1) {
        	request.setAttribute("name", user.getName());
        	return "/views/join/joinComplete.jsp";
        }

        request.setAttribute("message", "회원가입에 실패했습니다. (중복이거나 DB 오류)");
        return "/views/join/join.jsp";
    }
}