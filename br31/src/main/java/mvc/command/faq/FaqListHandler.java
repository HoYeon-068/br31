package mvc.command.faq;

import java.util.List;
import javax.servlet.http.*;
import mvc.command.CommandHandler;
import mvc.domain.faq.FaqDTO;
import mvc.persistence.faq.FaqDAO;

public class FaqListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        int pageSize = 10;
        int blockSize = 5;

        int currentPage = 1;
        int categoryId = 0; // 0=전체 기본
        String cat = request.getParameter("category");
        if (cat != null && !cat.isBlank()) {
            categoryId = Integer.parseInt(cat);
        }


        String pageParam = request.getParameter("page");
        if (pageParam != null) currentPage = Integer.parseInt(pageParam);

        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;

        FaqDAO dao = new FaqDAO();

        List<FaqDTO> list = dao.selectList(categoryId, startRow, endRow);
        int totalCount = dao.getTotalCount(categoryId);
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
        int endPage = Math.min(startPage + blockSize - 1, totalPage);

        request.setAttribute("list", list);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("categoryId", categoryId);

        return "/views/information-center/faq/list.jsp";
    }
}
