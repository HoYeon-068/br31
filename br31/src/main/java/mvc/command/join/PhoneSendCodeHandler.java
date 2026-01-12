package mvc.command.join;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.service.user.UserService;

public class PhoneSendCodeHandler implements CommandHandler {

	private UserService userService = new UserService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String phone = request.getParameter("phone_no");
        

        // 6자리 코드 생성
        String code = String.format("%06d", new Random().nextInt(1000000));
        
        if (userService.isPhoneTaken(phone)) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().print("DUPLICATE");
            return null;
        }

        

        HttpSession session = request.getSession();
        session.setAttribute("PHONE_AUTH_PHONE", phone);
        session.setAttribute("PHONE_AUTH_CODE", code);
        session.setMaxInactiveInterval(60 * 5); // 5분

        // 실제 SMS 전송 대신 콘솔 출력
        System.out.println("[휴대폰 인증코드] " + phone + " => " + code);

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().print("SENT");
        return null;
    }
}
