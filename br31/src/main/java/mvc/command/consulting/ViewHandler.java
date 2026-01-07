package mvc.command.consulting;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.consulting.NewStoreBoardDTO;
import mvc.domain.consulting.PageVO;
import mvc.persistence.consulting.NewStoreBoardDAO;
import mvc.persistence.consulting.NewStoreBoardDAOImpl;

public class ViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ViewHandler");
		
		Connection conn = ConnectionProvider.getConnection();
		NewStoreBoardDAO dao = new NewStoreBoardDAOImpl(conn);
		
		NewStoreBoardDTO dto=null;
		
		int seq=Integer.parseInt(request.getParameter("seq"));
		
		
		try {
			//list = dao.select();
			dto=dao.selectOne(seq);
		} catch (Exception e) {
			System.out.println("> consulting ListHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		request.setAttribute("dto", dto);
		return "/views/information-center/consulting/store-view.jsp";
	}
	
}
