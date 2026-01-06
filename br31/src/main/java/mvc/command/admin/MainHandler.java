package mvc.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class MainHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String view = request.getParameter("view");

		String contentPage=null;
		switch (view == null ? "" : view) {
		  case "productList":
		    contentPage = "/WEB-INF/views/admin/product/list.jsp";
		    break;
		  default:
		    //contentPage = "/WEB-INF/views/admin/dashboard.jsp";
		    break;
		}

		request.setAttribute("contentPage", contentPage);
		return "/WEB-INF/views/admin/admin_layout.jsp";
		
		
	}
	
}
