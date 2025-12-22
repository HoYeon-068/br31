package mvc.command.menu;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("menu ListHandler.java()..");
		
		Connection conn = ConnectionProvider.getConnection();
		ProductDAO dao = new ProductDAOImpl(conn);

		java.util.List<ProductDTO> list = null;
		
		
		
		try {
			list = dao.select();
			System.out.println("성공");
		} catch (Exception e) {
			System.out.println("> menu ListHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return "/views/menu/list.jsp";
	}

}
