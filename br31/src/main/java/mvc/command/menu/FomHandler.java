package mvc.command.menu;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.FomViewDTO;
import mvc.domain.menu.IngredientDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.MonthlyFlavorDTO;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.MonthlyFlavorDAO;
import mvc.persistence.menu.MonthlyFlavorDAOImpl;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;

public class FomHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		
		FomViewDTO fomViewDTO=null;
		
		MonthlyFlavorDAO mfDao=new MonthlyFlavorDAOImpl(conn);
		ProductDAO pDao=new ProductDAOImpl(conn);
		
		
		try {
			
			MonthlyFlavorDTO monthlyFlavorDTO=mfDao.select();
			
			int products_id=monthlyFlavorDTO.getProducts_id();
			ProductDTO productDTO=pDao.selectOne(products_id);
			List<IngredientDTO> ingredientDTO=pDao.selectIngredient(products_id);
			
			fomViewDTO=FomViewDTO.builder()
					.monthlyFlavorDTO(monthlyFlavorDTO)
					.productDTO(productDTO)
					.ingredientDTO(ingredientDTO)
					.build();
			
		} catch (Exception e) {
			System.out.println("> menu ViewHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		
		
		request.setAttribute("dto", fomViewDTO);
		
		return "/views/menu/fom.jsp";
	}
	
}
