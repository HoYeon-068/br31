package mvc.command.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.ConsultingDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ConsultingListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Connection conn = ConnectionProvider.getConnection();
		PlazaDAO dao = new PlazaDAOImpl(conn);
		
		 try {
	            List<ConsultingDTO> list = dao.selectAdminList();
	            request.setAttribute("list", list);
	            request.setAttribute("contentPage", "/WEB-INF/views/admin/consulting/list.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            conn.close();
	        }
		
		
		 return "/WEB-INF/views/admin/admin_layout.jsp";
	}

}
