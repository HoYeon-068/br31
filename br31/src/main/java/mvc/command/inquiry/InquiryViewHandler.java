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

        // 🔹 inquiry_id 파라미터
        Long inquiryId = Long.parseLong(request.getParameter("inquiry_id"));

        InquiryViewDAO dao = new InquiryViewDAO();
        InquiryViewDTO dto = dao.selectOne(inquiryId);

        // 🔹 존재하지 않는 글 방어
        if (dto == null) {
            return "redirect:/inquiry/list.do";
        }

        request.setAttribute("dto", dto);

        return "/WEB-INF/views/information-center/consulting/myvoc_view.jsp";
    }
}
