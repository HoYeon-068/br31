package mvc.command.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class AdminPlazaListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);

        try {
            List<PlazaSelectDTO> list = dao.adminSelect(category);
            request.setAttribute("list", list);
            request.setAttribute("category", category == null ? "ALL" : category);

            request.setAttribute("contentPage", "/WEB-INF/views/admin/plaza/list.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return "/WEB-INF/views/admin/admin_layout.jsp";
	}

}
