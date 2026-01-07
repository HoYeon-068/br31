package mvc.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class FindIdPhoneHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if (request.getMethod().equalsIgnoreCase("GET")) {
    	    return "/WEB-INF/views/login/findId.jsp";
    	}
    	
        String name = request.getParameter("name");
        String phone = request.getParameter("phone_no");

        String userId = userService.findUserIdByPhone(name, phone);



        if (userId != null) {
        	UserDTO user = userService.getUserById(userId);
            request.setAttribute("name", user.getName());
            request.setAttribute("foundUserId", userId);
            return "/WEB-INF/views/login/findIdResult.jsp";
        }

        request.setAttribute("activeTab", "phone");
        request.setAttribute("pMessage", "일치하는 회원 정보가 없습니다.");
        return "/WEB-INF/views/login/findId.jsp";
    }
}
