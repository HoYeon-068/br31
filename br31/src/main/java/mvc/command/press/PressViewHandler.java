package mvc.command.press;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.press.PressDTO;
import mvc.persistence.press.PressDAO;

public class PressViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("id")); // 🔥 통일

        PressDAO dao = new PressDAO();
        PressDTO dto = dao.selectOne(id); // pr_id 기준

        request.setAttribute("dto", dto);

        return "/WEB-INF/views/information-center/press/view.jsp";
    }
}
