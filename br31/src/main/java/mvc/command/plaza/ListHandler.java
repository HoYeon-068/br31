package mvc.command.plaza;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("> Plaza.ListHandler process()...");
		
		String category = request.getParameter("category");
		
		Connection conn = ConnectionProvider.getConnection();
		PlazaDAO dao = new PlazaDAOImpl(conn);
		List<PlazaSelectDTO> list = null;
		
		try {
			
			if (category == null || category.equals("ALL")) {
				list = dao.select();
				System.out.println("> Plaza.ListHandler select()...GET");
			} else {
				list = dao.select(category);
				System.out.println("> Plaza.ListHandler select(category)...GET");
			}
			
			
			
			request.setAttribute("list", list);
		} catch (Exception e) {
			System.out.println("> Plaza.ListHandler process()...GET Exception");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		
		return "/WEB-INF/views/play/plaza/list.jsp";
	}

}
