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
            return "/views/login/pwdReset.jsp";
        }

        String userId = request.getParameter("user_id");
        // 필요하면 email도 같이 받아서 검증 로직 추가 가능
        // String email = request.getParameter("email");

        String tempPwd = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        int result = userService.resetPassword(userId, tempPwd);

        if (result == 1) {
            // 실제 메일 전송 대신 
            System.out.println("[임시비밀번호] " + userId + " => " + tempPwd);

            UserDTO user = userService.getUserById(userId);
            request.setAttribute("name", user.getName());
            request.setAttribute("tempPwd", tempPwd);
            return "/views/login/pwdChangeResult.jsp";
        }

        request.setAttribute("message", "비밀번호 재발급 실패(아이디 확인)");
        return "/views/login/pwdReset.jsp";
    }
}
