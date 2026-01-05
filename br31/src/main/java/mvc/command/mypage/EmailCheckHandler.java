package mvc.command.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class EmailCheckHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");

        response.setContentType("application/json; charset=UTF-8");

        if (email == null || email.isBlank()) {
            response.getWriter().print("{\"count\":1}");
            return null;
        }

        //  본인 이메일이면 중복 아님 처리
        HttpSession session = request.getSession(false);
        if(session != null){
            UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
            if(loginUser != null && email.equals(loginUser.getEmail())){
                response.getWriter().print("{\"count\":0}");
                return null;
            }
        }

        boolean taken = userService.isEmailTaken(email);
        response.getWriter().print("{\"count\":" + (taken ? 1 : 0) + "}");
        return null;
    }
}
