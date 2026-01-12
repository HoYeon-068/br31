package mvc.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.domain.event.EventDTO;
import mvc.persistence.event.EventDAO;
import mvc.persistence.event.EventDAOImpl;

public class IndexHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("> index.ListHandler process()...");
		
		Connection conn = ConnectionProvider.getConnection();
		EventDAO edao = new EventDAOImpl(conn);
		List<EventDTO> plist = null;
		List<EventDTO> blist = null;
		
		try {
			
			plist = edao.select("1"); // 프로모션
			blist = edao.select("2"); // 제휴혜택
			
			
			request.setAttribute("blist", blist);
			request.setAttribute("plist", plist);
			
		} catch (Exception e) {
			System.out.println("> index List select().... Exception ");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		
		return "/WEB-INF/index.jsp";
	}

}
