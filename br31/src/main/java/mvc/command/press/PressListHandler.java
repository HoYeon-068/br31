package mvc.command.press;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.press.PressDTO;
import mvc.persistence.press.PressDAO;

public class PressListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    int pageSize = 10;          
	    int currentPage = 1;

	    String pageParam = request.getParameter("page");
	    if (pageParam != null) {
	        currentPage = Integer.parseInt(pageParam);
	    }

	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;

	    PressDAO dao = new PressDAO();

	    List<PressDTO> list = dao.selectList(startRow, endRow);
	    int totalCount = dao.getTotalCount();

	    int totalPage = (int) Math.ceil((double) totalCount / pageSize);
	    
	    int blockSize = 5;   // 한 번에 보여줄 페이지 수 (1~5, 6~10)

	    int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
	    int endPage = startPage + blockSize - 1;

	    if (endPage > totalPage) {
	        endPage = totalPage;
	    }

	    request.setAttribute("list", list);               // 현재 페이지 데이터
	    request.setAttribute("totalCount", totalCount);   // 전체 글 수
	    request.setAttribute("currentPage", currentPage); // 현재 페이지
	    request.setAttribute("totalPage", totalPage);     // 전체 페이지 수
	    request.setAttribute("startPage", startPage);
	    request.setAttribute("endPage", endPage);

	    return "/views/information-center/press/list.jsp";
	}

}
