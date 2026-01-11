package mvc.command.admin;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.CategoryDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.CategoryDAO;
import mvc.persistence.menu.CategoryDAOImpl;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;

public class MainHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		
		
		String view = request.getParameter("view");

		String contentPage=null;
		switch (view == null ? "" : view) {
		  case "productList":
		    contentPage = "/WEB-INF/views/admin/product/list.jsp";
		    ProductDAO dao = new ProductDAOImpl(conn);
		     
		    
		    java.util.List<ProductDTO> list = null;
		   
		    
		    
		    list = dao.select();
		    request.setAttribute("list", list);
		    
		    break;
		    
		  case "productWrite":
			    contentPage = "/WEB-INF/views/admin/product/write.jsp";
			    CategoryDAO cDao=new CategoryDAOImpl(conn);
			    java.util.List<CategoryDTO> category = null;
			    category=cDao.select();
			    request.setAttribute("category", category);
			    break;
		  default:
		    //contentPage = "/WEB-INF/views/admin/dashboard.jsp";
		    break;
		}

		request.setAttribute("contentPage", contentPage);
		return "/WEB-INF/views/admin/admin_layout.jsp";
		
		
	}
	
}
