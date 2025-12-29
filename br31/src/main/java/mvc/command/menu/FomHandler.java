package mvc.command.menu;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.MonthlyFlavorDTO;
import mvc.persistence.menu.MonthlyFlavorDAO;
import mvc.persistence.menu.MonthlyFlavorDAOImpl;

public class FomHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		MonthlyFlavorDAO dao=new MonthlyFlavorDAOImpl(conn);
		
		MonthlyFlavorDTO monthlyFlavorDTO=dao.select();
		
		request.setAttribute("dto", monthlyFlavorDTO);
		
		return "/views/menu/fom.jsp";
	}
	
}
