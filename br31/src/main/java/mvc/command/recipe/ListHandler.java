package mvc.command.recipe;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.PageVO;
import mvc.domain.recipe.RecipeListDTO;
import mvc.persistence.recipe.RecipeDAO;
import mvc.persistence.recipe.RecipeDAOImpl;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    System.out.println("> Recipe.ListHandler process()...");

	    String category = request.getParameter("category");
	    String pageParam = request.getParameter("page");

	    int currentPage = 1;
	    if (pageParam != null) currentPage = Integer.parseInt(pageParam);

	    int numberPerPage = 6;
	    int numberOfPageBlock = 5;

	    Connection conn = ConnectionProvider.getConnection();
	    RecipeDAO dao = new RecipeDAOImpl(conn);

	    try {
	        Integer categoryId = null;

	        // ✅ 여기서만 분기
	        if (category != null && !category.equalsIgnoreCase("ALL")) {
	            categoryId = Integer.parseInt(category);  // 1,2,3,4
	        }

	        int totalCount = dao.count(categoryId);
	        PageVO pageVO = new PageVO(currentPage, numberPerPage, numberOfPageBlock, totalCount);

	        List<RecipeListDTO> list =
	            dao.selectList(categoryId, pageVO.getOffset(), pageVO.getNumberPerPage());

	        request.setAttribute("list", list);
	        request.setAttribute("pageVO", pageVO);
	        request.setAttribute("category", category == null ? "ALL" : category);

	    } finally {
	        conn.close();
	    }

	    return "/WEB-INF/views/play/recipe/list.jsp";
	}

}
