package mvc.command.press;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.press.PressDTO;
import mvc.persistence.press.PressDAO;

public class PressViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int pr_id = Integer.parseInt(request.getParameter("pr_id"));

        PressDAO dao = new PressDAO();
        PressDTO dto = dao.selectOne(pr_id);

        request.setAttribute("dto", dto);

        return "/views/information-center/press/view.jsp";
    }
}
