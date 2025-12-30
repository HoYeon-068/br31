package mvc.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.notice.NoticeDTO;
import mvc.persistence.notice.NoticeDAO;

public class NoticeViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int NOTICE_ID = Integer.parseInt(request.getParameter("NOTICE_ID"));

        NoticeDAO dao = new NoticeDAO();
        NoticeDTO dto = dao.selectOne(NOTICE_ID);

        request.setAttribute("dto", dto);

        return "/views/information-center/notice/view.jsp";

    }
}
