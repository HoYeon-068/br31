package mvc.command.plaza;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaViewDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		Connection conn = ConnectionProvider.getConnection();
		PlazaDAO dao = new PlazaDAOImpl(conn);
		
		try {
			PlazaViewDTO dto = dao.view(seq);
			
			request.setAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return "/views/play/plaza/view.jsp";
		
	}

}
