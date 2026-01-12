package mvc.command.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.admin_faq.AdminFaqDTO;
import mvc.persistence.admin_faq.AdminFaqDAO;

public class AdminFaqEditHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AdminFaqDAO dao = new AdminFaqDAO();

        // GET → 수정 화면
        if (request.getMethod().equalsIgnoreCase("GET")) {

            String faqIdParam = request.getParameter("faqId");
            AdminFaqDTO dto = null;

            if (faqIdParam != null) {
                Long faqId = Long.parseLong(faqIdParam);
                dto = dao.selectOne(faqId);
            }

            request.setAttribute("dto", dto);
            request.setAttribute(
                "contentPage",
                "/WEB-INF/views/admin/faq/edit.jsp"
            );

            return "/WEB-INF/views/admin/admin_layout.jsp";
        }

     // POST → 저장 처리
        request.setCharacterEncoding("UTF-8");

        String faqIdParam = request.getParameter("faqId");

        AdminFaqDTO dto = new AdminFaqDTO();

        if (faqIdParam != null && !faqIdParam.isEmpty()) {
            dto.setFaqId(Long.parseLong(faqIdParam));
        }

        dto.setQuestion(request.getParameter("question"));
        dto.setAnswer(request.getParameter("answer"));

        if (dto.getFaqId() == null) {
            dao.insert(dto);
        } else {
            dao.update(dto);
        }

        // 🔥 여기
        response.sendRedirect(
            request.getContextPath() + "/admin/faq/list.do"
        );
        return null;

    }
}