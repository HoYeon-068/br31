package mvc.command.consulting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ConditionHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ConditionHandler");
		
		request.setAttribute("activeMenu", "condition");
		return "/views/information-center/consulting/condition.jsp";
	}
	
}
