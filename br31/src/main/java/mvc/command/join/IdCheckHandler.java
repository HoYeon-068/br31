package mvc.command.join;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.service.user.UserService;

public class IdCheckHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = trim(request.getParameter("user_id"));
        Map<String, Boolean> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        if (userId == null || userId.isEmpty()) {
            errors.put("user_id", Boolean.TRUE);
            return null;
        }
        
        UserService userService = new UserService();
        boolean taken = userService.isUserIdTaken(userId);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print("{\"count\":" + (taken ? 1 : 0) + "}");
        return null;
    }

    private String trim(String str) {
        return str == null ? null : str.trim();
    }
	
	
}
