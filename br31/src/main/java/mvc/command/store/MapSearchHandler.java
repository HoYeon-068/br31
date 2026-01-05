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
				
				response.setCharacterEncoding("UTF-8");
		        response.setContentType("application/json; charset=UTF-8");
		        String[] services = request.getParameterValues("service_info");
		        
		        for (int i = 0; i < services.length; i++) {
					System.out.println(services[i]);
				}
		        
				Connection conn = ConnectionProvider.getConnection();
				StoreDAO dao=new StoreDAOImpl(conn); 
				
				String json;
				
				try {
					
					if (services==null) {
						json = dao.select();
						
					}else {
						json = dao.select(services);
					}
					
					return json;
				} catch ( Exception e) {
					System.out.println("ajax 오류");
					return null;
				}
	}

}
