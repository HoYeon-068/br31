package mvc.command.plaza;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class NewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String requsetMethod = request.getMethod();
		
		if (requsetMethod.equals("GET")) {
			
			return "/WEB-INF/views/play/plaza/new.jsp";
			
		} else if (requsetMethod.equals("POST")) {
			
		}
		
		return null;
	}

}
