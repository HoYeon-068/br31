package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.menu.IceNutritionDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;

public class ProductDAOImpl implements ProductDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ProductDTO vo = null;
	private MenuListDTO menuListVo=null;
	private MenuViewDTO menuViewDTO=null;
	
	// 1. 생성자 DI 
	public ProductDAOImpl() {
		super(); 
	} 
	public ProductDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	// 2. Setter DI
	public void setConn(Connection conn) {
		this.conn = conn;
	} 
	public Connection getConn() {
		return conn;
	}
	
	

	@Override
	public ProductDTO selectOne(int product_num) throws SQLException {
		String sql = "SELECT * \r\n"
				+ "FROM \"products\"\r\n"
				+" WHERE \"products_id\"=?";

		
		

		int products_id,category_id,price;
		String product_name, english_name, sub_title,description
		,product_status,img_path,bg_color,span_color,poster_path;
		Date release_date;

		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
					System.out.println("값 있음"+product_num);
					// seq, title, writer, email, writedate, readed
					products_id = rs.getInt("products_id");
					category_id = rs.getInt("category_id");
					price = rs.getInt("price");
					product_name = rs.getString("product_name");
					english_name = rs.getString("english_name");
					sub_title = rs.getString("sub_title");
					description = rs.getString("description");
					product_status = rs.getString("product_status");
					img_path = rs.getString("img_path");
					bg_color = rs.getString("bg_color");
					span_color=rs.getString("span_color");
					poster_path = rs.getString("poster_path");
					release_date = rs.getDate("release_date");

					vo = new ProductDTO().builder()
							.products_id(products_id)
							.category_id(category_id)
							.price(price)
							.product_name(product_name)
							.english_name(english_name)
							.sub_title(sub_title)
							.description(description)
							.product_status(product_status)
							.img_path(img_path)
							.bg_color(bg_color)
							.span_color(bg_color)
							.poster_path(poster_path)
							.release_date(release_date)
							.build();

			}

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 

		return vo;
	}
	@Override
	public List<MenuListDTO> selectList(String category) throws SQLException {
		String condition = "";

		if ("B".equals(category)) {
		    condition = " OR p.\"category_id\" = ? ";
		}
		
		System.out.println(category);
		
		String sql =
			    "SELECT " +
			    "    p.\"products_id\", " +
			    "    p.\"product_name\", " +
			    "    p.\"sub_title\", " +
			    "    p.\"img_path\", " +
			    "    p.\"bg_color\", " +
			    "    p.\"span_color\", " +
			    "    LISTAGG('#' || pt.\"tag\", ' ') " +
			    "        WITHIN GROUP (ORDER BY pt.\"tag\") AS \"tags\" " +
			    "FROM \"products\" p " +
			    "LEFT JOIN \"product_tag\" pt " +
			    "    ON p.\"products_id\" = pt.\"products_id\" " +
			    "WHERE p.\"category_id\" = ? " +
			    condition +
			    "GROUP BY " +
			    "    p.\"products_id\", " +
			    "    p.\"product_name\", " +
			    "    p.\"sub_title\", " +
			    "    p.\"img_path\", " +
			    "    p.\"bg_color\", " +
			    "    p.\"span_color\"";

		ArrayList<MenuListDTO> list = null;

		int products_id;
		String product_name,sub_title,img_path,bg_color,tags,span_color; 
		
		
		int category_id=0;
		
		switch (category) {
		case "A":
			category_id=1;
			break;
		case "B":
			category_id=2;
			break;
		case "C":
			category_id=4;
			break;
		case "D":
			category_id=5;
			break;
		case "E":
			category_id=6;
			break;
		case "F":
			category_id=7;
			break;
		default:
			System.out.println("카테고리 switch문 오류");
			break;
		}
		
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category_id);
			if (!condition.equals("")) {
				pstmt.setInt(2, 3);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<MenuListDTO>();
				do {
					// seq, title, writer, email, writedate, readed
					products_id = rs.getInt("products_id");
					product_name = rs.getString("product_name");
					sub_title = rs.getString("sub_title");
					img_path = rs.getString("img_path");
					bg_color = rs.getString("bg_color");
					tags = rs.getString("tags");
					span_color=rs.getString("span_color");

					menuListVo = new MenuListDTO().builder()
							.products_id(products_id)
							.product_name(product_name)
							.sub_title(sub_title)
							.img_path(img_path)
							.bg_color(bg_color)
							.tags(tags)
							.span_color(span_color)
							.build();

					list.add(menuListVo);

				} while (rs.next());

			}

		} catch (SQLException e) { 
			System.out.println("오류 ProductDAO");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 

		return list;
	}
	
	
}
