package mvc.command.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.persistence.admin_faq.AdminFaqDAO;

public class AdminFaqDeleteHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Long faqId = Long.parseLong(request.getParameter("faqId"));

        AdminFaqDAO dao = new AdminFaqDAO();
        dao.delete(faqId);

        response.sendRedirect(
            request.getContextPath() + "/admin/faq/list.do"
        );
        return null;
    }
}
