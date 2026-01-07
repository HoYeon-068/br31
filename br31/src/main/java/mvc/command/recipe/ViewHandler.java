package mvc.command.recipe;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.recipe.RecipeViewDTO;
import mvc.persistence.recipe.RecipeDAO;
import mvc.persistence.recipe.RecipeDAOImpl;

public class ViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("> Recipe.ViewHandler process()...");

        int recipeId = Integer.parseInt(request.getParameter("recipeId"));

        Connection conn = ConnectionProvider.getConnection();
        RecipeDAO dao = new RecipeDAOImpl(conn);

        try {
            RecipeViewDTO dto = dao.view(recipeId);

            request.setAttribute("dto", dto);

        } catch (Exception e) {
            System.out.println("> Recipe.ViewHandler process() Exception");
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return "/WEB-INF/views/play/recipe/view.jsp";
    }
}
