package mvc.command.admin;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class AdminPlazaStatusHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!"POST".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }

        int seq = Integer.parseInt(request.getParameter("seq"));
        int status = Integer.parseInt(request.getParameter("status"));

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);

        try {
            dao.updatePlazaStatus(seq, status);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        response.sendRedirect(request.getContextPath() + "/admin/plaza/view.do?seq=" + seq);
        return null;
	}

}
