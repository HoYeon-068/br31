package mvc.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.notice.NoticeDTO;
import mvc.persistence.notice.NoticeDAO;

public class NoticeViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("id")); // 🔥 통일

        NoticeDAO dao = new NoticeDAO();
        NoticeDTO dto = dao.selectOne(id); // NOTICE_ID 기준

        request.setAttribute("dto", dto);

        return "/views/information-center/notice/view.jsp";
    }
}
