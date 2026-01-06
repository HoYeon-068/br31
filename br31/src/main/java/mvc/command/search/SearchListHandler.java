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

        // 1. 파라미터 수신 (null + 공백 방어)
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }

        // 2. 디버그 로그 (지금 단계에선 유지)
        System.out.println("### HANDLER keyword >>> [" + keyword + "]");

     // 3. category 파라미터 수신
     // categoryId 파라미터 수신 (숫자 기반)
        String categoryIdStr = request.getParameter("categoryId");
        Integer categoryId = null;

        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdStr);
        }


        // 4. DAO 호출 (분기)
        ProductDAO dao = new ProductDAO();
        List<ProductDTO> list;
        int totalCount;

        if (categoryId == null) {
            list = dao.search(keyword);
            totalCount = dao.getTotalCount(keyword);
        } else {
            list = dao.search(keyword, categoryId);
            totalCount = dao.getTotalCount(keyword, categoryId);
        }


        // 4. 결과 확인용 로그 (중요)
        System.out.println("### search result size = " + list.size());
        System.out.println("### totalCount = " + totalCount);

        // 5. JSP 전달
        request.setAttribute("list", list);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("keyword", keyword); // 검색어 유지용

        // 6. 포워딩
        return "/views/search/list.jsp";
    }
}
