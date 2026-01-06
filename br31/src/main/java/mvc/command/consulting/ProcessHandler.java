package mvc.command.consulting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ProcessHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProcessHandler");
		
		request.setAttribute("activeMenu", "process");
		return "/WEB-INF/views/information-center/consulting/process.jsp";
	}
	
}
