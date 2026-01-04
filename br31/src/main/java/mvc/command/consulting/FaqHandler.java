package mvc.command.consulting;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.consulting.BizFaqDTO;
import mvc.persistence.consulting.BizFaqDAO;
import mvc.persistence.consulting.BizFaqDAOImpl;

public class FaqHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FaqHandler");
		
		Connection conn = ConnectionProvider.getConnection();
		BizFaqDAO dao = new BizFaqDAOImpl(conn);
		
		java.util.List<BizFaqDTO> list = null;
		
		try {
			list = dao.select();
			
			/*
			 * for (int i = 0; i < list.size(); i++) {
			 * 
			 * System.out.println(list.get(i).getProduct_name()); }
			 */
			
		} catch (Exception e) {
			System.out.println("> menu ListHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("activeMenu", "faq");
		
		return "/views/information-center/consulting/faq.jsp";
	}
	
}
