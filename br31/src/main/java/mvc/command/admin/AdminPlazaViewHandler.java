package mvc.command.admin;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaViewDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class AdminPlazaViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int seq = Integer.parseInt(request.getParameter("seq"));

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);

        try {
            PlazaViewDTO dto = dao.adminView(seq);
            request.setAttribute("dto", dto);

            request.setAttribute("contentPage", "/WEB-INF/views/admin/plaza/view.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return "/WEB-INF/views/admin/admin_layout.jsp";
	}

}
