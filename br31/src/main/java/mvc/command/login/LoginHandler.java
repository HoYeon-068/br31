package mvc.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class LoginHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getMethod().equalsIgnoreCase("GET")) {
            String referer = request.getHeader("Referer");

            // 로그인/회원가입 페이지 referer면 제외
            if (referer != null &&
                !referer.contains("/login/login.do") &&
                !referer.contains("/join")) {
                request.setAttribute("redirectUrl", referer);
            }

            return "/views/login/login.jsp";
        }

        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        String redirectUrl = request.getParameter("redirectUrl");

        UserDTO loginUser = userService.login(userId, password);

        if (loginUser == null) {
            request.setAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
            request.setAttribute("redirectUrl", redirectUrl); // hidden 유지
            request.setAttribute("userId", userId);           // 아이디 유지(선택)
            return "/views/login/login.jsp"; // forward
        }

        // 로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        // redirectUrl 없으면 기본 이동
        if (redirectUrl == null || redirectUrl.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/story/history.do");
            return null;
        }

        // referer는 보통 절대경로(http://...)라서 그대로 리다이렉트 가능
        response.sendRedirect(redirectUrl);
        return null;
    }


}
