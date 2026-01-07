package mvc.command.mypage;

import java.util.Random;
import javax.servlet.http.*;

import mvc.command.CommandHandler;

public class PhoneSendCodeHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String phone = request.getParameter("phone_no");

        String code = String.format("%06d", new Random().nextInt(1000000));

        HttpSession session = request.getSession();
        session.setAttribute("MYPAGE_PHONE_AUTH_PHONE", phone);
        session.setAttribute("MYPAGE_PHONE_AUTH_CODE", code);
        session.setMaxInactiveInterval(60 * 5);

        System.out.println("[마이페이지 휴대폰 인증] " + phone + " => " + code);

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().print("SENT");
        return null;
    }
}
