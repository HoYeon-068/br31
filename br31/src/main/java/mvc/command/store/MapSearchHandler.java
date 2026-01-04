package mvc.command.store;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.persistence.store.StoreDAO;
import mvc.persistence.store.StoreDAOImpl;

public class MapSearchHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("> MapSearchHandler .... ");
				
				Connection conn = ConnectionProvider.getConnection();
				StoreDAO dao=new StoreDAOImpl(conn); 
				
				
				try {
					String json = dao.select();
					return json;
				} catch ( Exception e) {
					System.out.println("ajax 오류");
					return null;
				}
	}

}
