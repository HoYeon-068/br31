package mvc.command.admin.faq;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.admin_faq.AdminFaqDTO;
import mvc.persistence.admin_faq.AdminFaqDAO;

public class AdminFaqListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

	    AdminFaqDAO dao = new AdminFaqDAO();
	    List<AdminFaqDTO> list = dao.selectList();

	    request.setAttribute("list", list);

	   
	    request.setAttribute("contentPage", "/WEB-INF/views/admin/faq/list.jsp");

	    return "/WEB-INF/views/admin/admin_layout.jsp";
	}

}
