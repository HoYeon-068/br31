package mvc.command.login;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.persistence.user.UserDAO;
import mvc.persistence.user.UserDAOImpl;
import mvc.service.user.UserService;

public class PwdResetRequestHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getMethod().equalsIgnoreCase("GET")) {
            return "/WEB-INF/views/login/pwdReset.jsp";
        }

        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone_no");

        String tempPwd = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        int result = userService.resetPasswordByUserIdAndPhoneAndName(userId, tempPwd, name, phone);

        if (result == 1) {
            // 실제 메일 전송 대신 
            System.out.println("[임시비밀번호] " + userId + " => " + tempPwd);

            request.setAttribute("name", name);
            request.setAttribute("tempPwd", tempPwd);
            return "/WEB-INF/views/login/pwdChangeResult.jsp";
        }
        System.out.println("result=" + result);

        request.setAttribute("message", "비밀번호 재발급 실패(아이디/휴대폰번호/이름 확인해 주세요)");
        return "/WEB-INF/views/login/pwdReset.jsp";
    }
}
