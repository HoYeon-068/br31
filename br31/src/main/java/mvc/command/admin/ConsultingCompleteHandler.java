package mvc.command.admin;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ConsultingCompleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/admin/consulting/list.do");
            return null;
        }

        int consultingId = Integer.parseInt(idParam);

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);

        try {
            dao.updateAdminStatus(consultingId, 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        response.sendRedirect(request.getContextPath() + "/admin/consulting/view.do?id=" + consultingId);
        return null;
	}

}
