package mvc.command.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.CategoryDTO;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.CategoryDAO;
import mvc.persistence.menu.CategoryDAOImpl;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;
import mvc.persistence.menu.ProductTagDAO;
import mvc.persistence.menu.ProductTagDAOImpl;

public class ProductWriteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductWriteHandler");
		
		String requestMethod = request.getMethod();
		Connection conn = ConnectionProvider.getConnection();
		
		 java.util.List<CategoryDTO> category = null;
		 ProductDTO vo=null;
		
		if (requestMethod.equals("GET")) {
		    try {
		    	CategoryDAO cDao=new CategoryDAOImpl(conn);
			    category=cDao.select();
			    
			} catch (Exception e) {
				System.out.println("> ProductWriteHandler.process() Exception...");
				e.printStackTrace();
			}finally {
				conn.close();
			}
		    
		    request.setAttribute("category", category);
			return "/WEB-INF/views/admin/product/write.jsp";
			
		} else if (requestMethod.equals("POST") ){
			
			
			String uploadPath = request.getServletContext()
			        .getRealPath("/resources/images/upload/product/main");
			
			// /resources/images/upload/ckeditor/

			int maxSize = 10 * 1024 * 1024; // 10MB

			MultipartRequest multi = new MultipartRequest(
			    request,
			    uploadPath,
			    maxSize,
			    "UTF-8",
			    new DefaultFileRenamePolicy()
			);


			String product_name = multi.getParameter("productName");
			String english_name = multi.getParameter("englishName");
			String description = multi.getParameter("description");
			String bg_color     = multi.getParameter("bgColor");
			String span_color   = multi.getParameter("fontColor");
			
			
			
			
			// 숫자
			int category_id = Integer.parseInt(multi.getParameter("categoryId"));

			// price는 nullable
			String priceParam = multi.getParameter("price");
			Integer price = (priceParam == null || priceParam.isBlank())
			        ? 0
			        : Integer.parseInt(priceParam);
			
			
			String[] tags = multi.getParameterValues("tags");
			// null / 빈값 제거 추천
			List<String> tagList = new ArrayList<>();
			if (tags != null) {
			    for (String tag : tags) {
			        if (tag != null && !tag.trim().isEmpty()) {
			            tagList.add(tag.trim());
			        }
			    }
			}
			
			
			
			String productImg = multi.getFilesystemName("productImg"); // 필수
			String posterImg  = multi.getFilesystemName("posterImg");  // 선택
			
			String productImgPath=null;
			String posterImgPath=null;
			
			if (productImg != null) {
			    productImgPath = "/resources/images/upload/product/main/" + productImg;
			}

			if (posterImg != null) {
			    posterImgPath = "/resources/images/upload/product/main/" + posterImg;
			}
			
			int products_id;
			
			
			
			try {
		    	ProductDAO pDao=new ProductDAOImpl(conn);
			    ProductTagDAO tDao=new ProductTagDAOImpl(conn);
		    	
		    	vo=new ProductDTO().builder()
		    			.category_id(category_id)
		    			.product_name(product_name)
		    			.english_name(english_name)
		    			.description(description)
		    			.bg_color(bg_color)
		    			.span_color(span_color)
		    			.poster_path(posterImgPath)
		    			.img_path(productImgPath)
		    			.price(price)
		    			.product_status("판매중")
		    			.build();
		    	
		    	
		    	pDao.insert(vo);
		    	products_id=pDao.getProductSeqNum();
		    	
		    	
		    	
		    	for (int i = 0; i < tags.length; i++) {
		    		tDao.insert(products_id, tags[i]);
				}
		    	
		    	
			} catch (Exception e) {
				System.out.println("> ProductWriteHandler.process() Exception...");
				e.printStackTrace();
			}finally {
				conn.close();
			}
			
			String location = request.getContextPath() + "/admin/main.do?view=productList";
			response.sendRedirect(location);
		}
		
		return null;
	}
	
}
