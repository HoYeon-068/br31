package mvc.command.fairtrade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.fairtrade.FairTradeDTO;
import mvc.persistence.fairtrade.FairTradeDAO;

public class FairTradeViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int noticeId = Integer.parseInt(request.getParameter("noticeId"));

        FairTradeDAO dao = new FairTradeDAO();
        FairTradeDTO dto = dao.selectOne(noticeId);

        // ✅ 3번 게시글 = e-book (noticeNo = 3)
        if ("3".equals(dto.getNoticeNo())) {
            response.sendRedirect(
                "https://www.baskinrobbins.co.kr/ebook/bk250826/#page=1"
            );
            return null; // 반드시 null
        }

        // 나머지는 일반 게시글
        request.setAttribute("dto", dto);
        return "/views/information-center/fairtrade/view.jsp";
    }
}
