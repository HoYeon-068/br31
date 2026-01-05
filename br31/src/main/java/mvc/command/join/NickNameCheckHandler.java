package mvc.command.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.service.user.UserService;

public class NickNameCheckHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String nickname = request.getParameter("nickname");

        if (nickname == null || nickname.isBlank()) {
            return null;
        }

        boolean taken = userService.isNicknameTaken(nickname);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print("{\"count\":" + (taken ? 1 : 0) + "}");
        return null;
    }
}
