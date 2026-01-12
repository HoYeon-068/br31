package mvc.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class FindIdEmailHandler implements CommandHandler {

    private UserService userService = new UserService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if (request.getMethod().equalsIgnoreCase("GET")) {
    	    return "/WEB-INF/views/login/findId.jsp";
    	}
    	
    	
    	
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String userId = userService.findUserIdByEmail(name, email);
        

        if (userId != null) {
        	UserDTO user = userService.getUserById(userId);
            request.setAttribute("name", user.getName());
            request.setAttribute("foundUserId", userId);
            return "/WEB-INF/views/login/findIdResult.jsp";
        }
        request.setAttribute("activeTab", "email");
        request.setAttribute("eMessage", "일치하는 회원 정보가 없습니다.");
        return "/WEB-INF/views/login/findId.jsp";
    }
}
