package mvc.command.menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.IceNutritionDTO;
import mvc.domain.menu.IngredientDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.IceNutritionDAO;
import mvc.persistence.menu.IceNutritionDAOImpl;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;

public class ViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Connection conn = ConnectionProvider.getConnection();
		IceNutritionDAO iceN_dao=new IceNutritionDAOImpl(conn);
		ProductDAO product_dao = new ProductDAOImpl(conn);
		
		MenuViewDTO menuViewDTO=null;
		IceNutritionDTO iceNutritionDTO=null;
		ProductDTO productDTO=null;
		
		int seq=Integer.parseInt(request.getParameter("seq"));
		
		
		try {
			iceNutritionDTO = iceN_dao.select(seq);
			productDTO=product_dao.selectOne(seq);
			List<IngredientDTO> list=product_dao.selectIngredient(seq);
			
			menuViewDTO= MenuViewDTO.builder()
						 .products_id(productDTO.getProducts_id())
						 .category_id(productDTO.getCategory_id())
						 .category_name(productDTO.getCategory_name())
						 .product_name(productDTO.getProduct_name())
						 .english_name(productDTO.getEnglish_name())
						 .price(productDTO.getPrice())
						 .description(productDTO.getDescription())
						 .poster_path(productDTO.getPoster_path())
						 .img_path(productDTO.getImg_path())
						 .bg_color(productDTO.getBg_color())
						 .iceNutritionDTO(iceNutritionDTO)
						 .ingredientDTO(list)
						 .build();
			
		} catch (Exception e) {
			System.out.println("> menu ViewHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		request.setAttribute("dto", menuViewDTO);
		
		return "/views/menu/view.jsp";
	}

}
