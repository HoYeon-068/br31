package mvc.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.service.user.UserService;

public class FindIdEmailHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String userId = userService.findUserIdByEmail(name, email);

        if (userId != null) {
            request.setAttribute("foundUserId", userId);
            return "/views/login/findIdResult.jsp";
        }

        request.setAttribute("message", "일치하는 회원 정보가 없습니다.");
        return "/views/login/findId.jsp";
    }
}
