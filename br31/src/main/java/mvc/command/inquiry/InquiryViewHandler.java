package mvc.command.inquiry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.inquiry.InquiryViewDTO;
import mvc.persistence.inquiry.InquiryViewDAO;

public class InquiryViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Long inquiryId =
            Long.parseLong(request.getParameter("inquiry_id"));

        InquiryViewDAO dao = new InquiryViewDAO();
        InquiryViewDTO dto = dao.selectOne(inquiryId);

        if (dto == null) {
            response.sendRedirect(
                request.getContextPath() + "/inquiry/list.do"
            );
            return null;
        }

        request.setAttribute("dto", dto);
        return "/WEB-INF/views/information-center/consulting/myvoc_view.jsp";
    }
}
