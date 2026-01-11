package mvc.command.inquiry;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.inquiry.InquiryListDTO;
import mvc.domain.user.UserDTO;
import mvc.persistence.inquiry.InquiryListDAO;

public class InquiryListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login/login.do");
            return null;
        }

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        String userId = loginUser.getUser_id(); // ← 여기 핵심

        InquiryListDAO dao = new InquiryListDAO();
        List<InquiryListDTO> list = dao.selectList(userId);

        request.setAttribute("list", list);

        return "/WEB-INF/views/information-center/consulting/myvoc_list.jsp";
    }
}
