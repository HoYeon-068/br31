package mvc.command.menu;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.ProductDTO;
import mvc.persistence.menu.ProductDAO;
import mvc.persistence.menu.ProductDAOImpl;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("menu ListHandler.java()..");
		
		Connection conn = ConnectionProvider.getConnection();
		ProductDAO dao = new ProductDAOImpl(conn);
		
		
		String category=request.getParameter("category")!=null?
				 request.getParameter("category"):"D";
		
		java.util.List<MenuListDTO> list = null;
		
		String title=null,description=null;
		
		switch (category) {
		case "A":
			title="Ice Cream";
			description="한 입에 물면 달콤하게 사르르 녹는 아이스크림.<br>"
					+ "당신이 어떤 기분이든 그 아이스크림을 따라 당신의 기분은 아마 달콤해졌을 거예요.";
			break;
		case "B":
			title="Prepack";
			description="한 입에 물면 달콤하게 사르르 녹는 아이스크림.<br>"
					+ "당신이 어떤 기분이든 그 아이스크림을 따라 당신의 기분은 아마 달콤해졌을 거예요.";
			break;
		case "C":
			title="Ice Cream Cake";
			description="축하하고 싶은 날에도, 위로가 필요한 날에도, 그 모든 순간 함께 할 아이스크림 케이크.<br>"
					+ "달콤한 아이스크림 케이크로 당신의 특별한 날을 더욱더 특별하게 만들어드릴게요.";
			break;
		case "D":
			title="Dessert";
			description="아이스크림을 더욱 맛있고 특별하게 즐길 수 있는 배스킨라빈스의 아이스 디저트!<br>"
					+ "쫀득한 모찌부터 달콤한 마카롱까지 다양한 디저트를 만나보세요.";
			break;

		case "E":
			title="Beverage";
			description="짜릿하게 시원한 블라스트부터 아이스크림을 듬뿍넣고 갈아만든 쉐이크까지!<br>"
					+ "배스킨라빈스만의 특별함을 음료로 만나보세요.";
			break;
		case "F":
			title="Coffee";
			description="진한 카카오의 향과 풍부한 바디감의 배스킨라빈스 대표 커피 'Café Bris'<br>"
					+ "일상에 모든 순간을 카페브리즈와 함께하세요.";
			break;
			
			
		default:
			break;
		}
		
		
		try {
			list = dao.selectList(category);
			
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
		request.setAttribute("title", title);
		request.setAttribute("category", category);
		request.setAttribute("description", description);
		String location=category.equals("E")||category.equals("F")?"/views/menu/list_subcategory.jsp":"/views/menu/list.jsp";
		return location;
	}

}
