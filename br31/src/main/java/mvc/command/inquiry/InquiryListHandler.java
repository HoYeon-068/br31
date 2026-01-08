package mvc.command.inquiry;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.inquiry.InquiryListDTO;
import mvc.persistence.inquiry.InquiryListDAO;

public class InquiryListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String userId = (String) request.getSession().getAttribute("loginUser");

        if (userId == null) {
            userId = "GUEST";
        }

        InquiryListDAO dao = new InquiryListDAO();
        List<InquiryListDTO> list = dao.selectList(userId);

        request.setAttribute("list", list);

        return "/WEB-INF/views/information-center/consulting/myvoc_list.jsp";
    }
}
