package mvc.command.myflavor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requsetMethod = request.getMethod();
		
		if (requsetMethod.equals("GET")) {
			System.out.println("> flavor.ListHandler...get()...");
			
            return "/views/play/myflavor/list.jsp";
        }

        return null;
	}

}
