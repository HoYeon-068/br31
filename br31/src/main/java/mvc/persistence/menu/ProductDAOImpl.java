package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.menu.IceNutritionDTO;
import mvc.domain.menu.IngredientDTO;
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
	private IngredientDTO ingredientDTO=null;
	
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
		String sql = "SELECT * " +
	             "FROM \"products\" p " +
	             "LEFT JOIN \"category\" c " +
	             "ON p.\"category_id\" = c.\"category_id\" " +
	             "WHERE \"products_id\" = ?";

		
		

		int products_id,category_id,price;
		String product_name, english_name, sub_title,description
		,product_status,img_path,bg_color,span_color,poster_path,category_name;
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
					category_name=rs.getString("category_name");
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
							.category_name(category_name)
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
		
		String sql = "SELECT " +
	             "    p.\"products_id\", " +
	             "    p.\"product_name\", " +
	             "    c.\"category_name\", " +
	             "    p.\"sub_title\", " +
	             "    p.\"img_path\", " +             "    p.\"bg_color\", " +
	             "    p.\"span_color\", " +
	             "    T.\"tags\" " +
	             "FROM \"products\" p " +
	             "LEFT JOIN \"category\" c ON p.\"category_id\" = c.\"category_id\" " +
	             "LEFT JOIN ( " +
	             "    SELECT " +
	             "        \"products_id\", " +
	             "        LISTAGG('#' || \"tag\", ' ') WITHIN GROUP (ORDER BY \"tag\") AS \"tags\" " +
	             "    FROM (SELECT DISTINCT \"products_id\", \"tag\" FROM \"product_tag\") " +
	             "    GROUP BY \"products_id\" " +
	             ") T ON p.\"products_id\" = T.\"products_id\" " +
	             "WHERE p.\"category_id\" = ?"+
			    condition;

		ArrayList<MenuListDTO> list = null;

		int products_id;
		String product_name,sub_title,img_path,bg_color,tags,span_color,category_name; 
		
		
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
					category_name=rs.getString("category_name");
					sub_title = rs.getString("sub_title");
					img_path = rs.getString("img_path");
					bg_color = rs.getString("bg_color");
					tags = rs.getString("tags");
					span_color=rs.getString("span_color");

					menuListVo = new MenuListDTO().builder()
							.products_id(products_id)
							.product_name(product_name)
							.category_name(category_name)
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
	@Override
	public List<IngredientDTO> selectIngredient(int seq) throws SQLException {
		
		String sql = "SELECT * " +
	             "FROM \"ingredient\" i " +
	             "RIGHT JOIN \"ingredient_products\" p " +
	             "ON i.\"ingredient_id\" = p.\"ingredient_id\" " +
	             "WHERE \"products_id\" = ?";

		ArrayList<IngredientDTO> list = null;

		int ingredient_products_id,products_id,ingredient_id;
		String ingredient_name,img_path;
		
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<IngredientDTO>();
				do {
					// seq, title, writer, email, writedate, readed
					ingredient_products_id = rs.getInt("ingredient_products_id");
					products_id = rs.getInt("products_id");
					ingredient_id = rs.getInt("ingredient_id");
					
					ingredient_name = rs.getString("ingredient_name");
					img_path = rs.getString("img_path");
					
					

					ingredientDTO = new IngredientDTO().builder()
							.ingredient_products_id(ingredient_products_id)
							.products_id(products_id)
							.ingredient_id(ingredient_id)
							.ingredient_name(ingredient_name)
							.img_path(img_path)
							.build();

					list.add(ingredientDTO);

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
	
	@Override
	public ProductDTO getPrev(int categoryId, int productsId) throws SQLException {

	    String sql;

	    if (categoryId == 2 || categoryId == 3) {
	        sql =
	            "SELECT * FROM ( " +
	            "   SELECT * " +
	            "   FROM \"products\" " +
	            "   WHERE \"category_id\" IN (2, 3) " +
	            "     AND \"products_id\" < ? " +
	            "   ORDER BY \"products_id\" DESC " +
	            ") WHERE ROWNUM = 1";
	    } else {
	        sql =
	            "SELECT * FROM ( " +
	            "   SELECT * " +
	            "   FROM \"products\" " +
	            "   WHERE \"category_id\" = ? " +
	            "     AND \"products_id\" < ? " +
	            "   ORDER BY \"products_id\" DESC " +
	            ") WHERE ROWNUM = 1";
	    }

	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    if (categoryId == 2 || categoryId == 3) {
	        pstmt.setInt(1, productsId);
	    } else {
	        pstmt.setInt(1, categoryId);
	        pstmt.setInt(2, productsId);
	    }

	    ResultSet rs = pstmt.executeQuery();

	    if (rs.next()) {
	        return ProductDTO.builder()
	            .products_id(rs.getInt("products_id"))
	            .category_id(rs.getInt("category_id"))
	            .product_name(rs.getString("product_name"))
	            .english_name(rs.getString("english_name"))
	            .sub_title(rs.getString("sub_title"))
	            .description(rs.getString("description"))
	            .product_status(rs.getString("product_status"))
	            .img_path(rs.getString("img_path"))
	            .bg_color(rs.getString("bg_color"))
	            .span_color(rs.getString("span_color"))
	            .poster_path(rs.getString("poster_path"))
	            .price(rs.getInt("price"))
	            .release_date(rs.getDate("release_date"))
	            .build();
	    }

	    return null;
	}

	
	@Override
	public ProductDTO getNext(int categoryId, int productsId) throws SQLException {

	    String sql;

	    if (categoryId == 2 || categoryId == 3) {
	        sql =
	            "SELECT * FROM ( " +
	            "   SELECT * " +
	            "   FROM \"products\" " +
	            "   WHERE \"category_id\" IN (2, 3) " +
	            "     AND \"products_id\" > ? " +
	            "   ORDER BY \"products_id\" ASC " +
	            ") WHERE ROWNUM = 1";
	    } else {
	        sql =
	            "SELECT * FROM ( " +
	            "   SELECT * " +
	            "   FROM \"products\" " +
	            "   WHERE \"category_id\" = ? " +
	            "     AND \"products_id\" > ? " +
	            "   ORDER BY \"products_id\" ASC " +
	            ") WHERE ROWNUM = 1";
	    }

	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    if (categoryId == 2 || categoryId == 3) {
	        pstmt.setInt(1, productsId);
	    } else {
	        pstmt.setInt(1, categoryId);
	        pstmt.setInt(2, productsId);
	    }

	    ResultSet rs = pstmt.executeQuery();

	    if (rs.next()) {
	        return ProductDTO.builder()
	            .products_id(rs.getInt("products_id"))
	            .category_id(rs.getInt("category_id"))
	            .product_name(rs.getString("product_name"))
	            .english_name(rs.getString("english_name"))
	            .sub_title(rs.getString("sub_title"))
	            .description(rs.getString("description"))
	            .product_status(rs.getString("product_status"))
	            .img_path(rs.getString("img_path"))
	            .bg_color(rs.getString("bg_color"))
	            .span_color(rs.getString("span_color"))
	            .poster_path(rs.getString("poster_path"))
	            .price(rs.getInt("price"))
	            .release_date(rs.getDate("release_date"))
	            .build();
	    }

	    return null;
	}
	@Override
	public List<ProductDTO> select() throws SQLException {
		
		String sql = "SELECT * " +
	             "FROM \"products\" p " +
	             "LEFT JOIN \"category\" c " +
	             "ON p.\"category_id\" = c.\"category_id\" "
	             + "ORDER BY \"products_id\"";

		
		ArrayList<ProductDTO> list = null;
		

		int products_id,category_id,price;
		String product_name, english_name, sub_title,description
		,product_status,img_path,bg_color,span_color,poster_path,category_name;
		Date release_date;

		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				list=new ArrayList<ProductDTO>();
				// seq, title, writer, email, writedate, readed
				
				do {
					products_id = rs.getInt("products_id");
					category_id = rs.getInt("category_id");
					category_name=rs.getString("category_name");
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
							.category_name(category_name)
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
					list.add(vo);
				} while (rs.next());
				
					
					
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

		return list;
	}

	@Override
	public int insert(ProductDTO dto) throws SQLException {
		String sql =
			    "INSERT INTO \"products\" ("
			  + "\"products_id\", "
			  + "\"category_id\", "
			  + "\"product_name\", "
			  + "\"english_name\", "
			  + "\"sub_title\", "
			  + "\"description\", "
			  + "\"product_status\", "
			  + "\"img_path\", "
			  + "\"bg_color\", "
			  + "\"span_color\", "
			  + "\"poster_path\", "
			  + "\"price\", "
			  + "\"release_date\""
			  + ") VALUES ("
			  + "products_seq.NEXTVAL, "
			  + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE"
			  + ")";


		
		
		int rowCount = 0;

		try {
			System.out.println("insert문진입");
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			System.out.println(dto.getCategory_id());
			System.out.println(dto.getProduct_name());
			System.out.println(dto.getEnglish_name());
			System.out.println(dto.getSub_title());
			System.out.println(dto.getDescription());
			System.out.println(dto.getProduct_status());
			System.out.println(dto.getImg_path());
			System.out.println(dto.getBg_color());
			System.out.println(dto.getSpan_color());
			System.out.println(dto.getPoster_path());
			System.out.println(dto.getPrice());
			
			
			pstmt.setInt(1, dto.getCategory_id());
			pstmt.setString(2, dto.getProduct_name());
			pstmt.setString(3, dto.getEnglish_name());
			
			pstmt.setString(4, dto.getSub_title());
			
			pstmt.setString(5, dto.getDescription());
			pstmt.setString(6, dto.getProduct_status());
			pstmt.setString(7, dto.getImg_path());
			pstmt.setString(8, dto.getBg_color());
			pstmt.setString(9, dto.getSpan_color());
			
			pstmt.setString(10, dto.getPoster_path());
			
			
			if (dto.getPrice() != 0) {
			    pstmt.setInt(11, dto.getPrice());
			} else {
			    pstmt.setNull(11, java.sql.Types.INTEGER);
			}
			


			rowCount = pstmt.executeUpdate();

		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 

		return rowCount;
	}
	@Override
	public Integer getProductSeqNum() throws SQLException {
		String sql = "SELECT products_seq.CURRVAL FROM dual";
		Integer products_id=null;
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				products_id=rs.getInt(1);
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

		return products_id;
	}
	@Override
	public int getProductsCount() throws SQLException {
		String sql = "SELECT count(*) FROM \"products\"";
		Integer count=null;
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count=rs.getInt(1);
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

		return count;
	}

	
}
