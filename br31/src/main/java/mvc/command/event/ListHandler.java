package mvc.command.event;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.event.EventDTO;
import mvc.persistence.event.EventDAO;
import mvc.persistence.event.EventDAOImpl;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("> event.ListHandler process()...");
		
		String category = request.getParameter("category");
		
		Connection conn = ConnectionProvider.getConnection();
		EventDAO dao = new EventDAOImpl(conn);
		List<EventDTO> list = null;
		
		try {
			if (category == null || category.equals("ALL")) {
				list = dao.select();
				System.out.println("> event List select().... ");
			} else {
				list = dao.select(category);
				System.out.println("> event List select(category).... ");
			}
			
			// D-Day 계산
			long now = System.currentTimeMillis();
		    for (EventDTO e : list) {
		        if (e.getEndDate() != null) {
		            long diff = e.getEndDate().getTime() - now;
		            int dday = (int) Math.ceil(diff / (1000.0 * 60 * 60 * 24));
		            e.setDday(dday);
		        }
		    }
			
			request.setAttribute("list", list);
			
		} catch (Exception e) {
			System.out.println("> event List select().... Exception ");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return "/views/play/event/list.jsp";
	}
	
}
