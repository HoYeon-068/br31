package mvc.command.mypage;

import javax.servlet.http.*;

import mvc.command.CommandHandler;

public class PhoneVerifyCodeHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String phone = request.getParameter("phone_no");
        String code = request.getParameter("code");

        HttpSession session = request.getSession(false);

        String savedPhone = session == null ? null :
            (String) session.getAttribute("MYPAGE_PHONE_AUTH_PHONE");
        String savedCode = session == null ? null :
            (String) session.getAttribute("MYPAGE_PHONE_AUTH_CODE");

        boolean ok = phone != null && code != null
                && phone.equals(savedPhone)
                && code.equals(savedCode);

        if (ok) {
            session.setAttribute("MYPAGE_PHONE_AUTH_OK", true);
        }

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().print(ok ? "OK" : "FAIL");
        return null;
    }
}
