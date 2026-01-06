package mvc.command.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class CateringOrderHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CateringOrderHandler");
		
		return "/WEB-INF/views/store/catering-order.jsp";
	}
	
}
