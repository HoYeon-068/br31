package mvc.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import mvc.command.CommandHandler;
import mvc.domain.notice.NoticeDTO;
import mvc.persistence.notice.NoticeDAO;

public class NoticeListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    int pageSize = 10;   // 한 페이지당 글 수
	    int currentPage = 1;

	    String pageParam = request.getParameter("page");
	    if (pageParam != null) {
	        currentPage = Integer.parseInt(pageParam);
	    }

	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;

	    NoticeDAO dao = new NoticeDAO();

	    List<NoticeDTO> list = dao.selectList(startRow, endRow);
	    int totalCount = dao.getTotalCount();

	    int totalPage = (int) Math.ceil((double) totalCount / pageSize);

	    request.setAttribute("list", list);
	    request.setAttribute("totalCount", totalCount); 
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);

	    return "/views/information-center/notice/list.jsp";
	}

}
