package mvc.command.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.search.ProductDTO;
import mvc.persistence.search.ProductDAO;

public class SearchListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String keyword = request.getParameter("keyword");
        if (keyword == null) keyword = "";

        ProductDAO dao = new ProductDAO();

        List<ProductDTO> list = dao.search(keyword);
        int totalCount = dao.getTotalCount(keyword);

        request.setAttribute("list", list);
        request.setAttribute("totalCount", totalCount);

        return "/views/search/list.jsp";
    }
}
