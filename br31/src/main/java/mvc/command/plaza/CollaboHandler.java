package mvc.command.plaza;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class CollaboHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String requestMethod = request.getMethod();
		
		if (requestMethod.equals("GET")) {
			return "/views/play/plaza/collabo.jsp";
		} else if (requestMethod.equals("POST")) {
			
		}
		
		return null;
	}

}
