package mvc.command.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class FlavorHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("FlavorHandler");
		return "/views/store/flavor.jsp";
	}
	
}
