package mvc.command.consulting;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class BrHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BrHandler");
		
		String a="aaaa";
		
		
		System.out.println(a.substring(a.length()));
		
		return "/views/information-center/consulting/br.jsp";
	}
	
}
