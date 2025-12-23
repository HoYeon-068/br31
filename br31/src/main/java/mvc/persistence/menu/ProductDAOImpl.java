package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.ProductDTO;

public class ProductDAOImpl implements ProductDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ProductDTO vo = null;

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
	public List<ProductDTO> select(int category_num) throws SQLException {
		String sql = "SELECT * \r\n"
				+ "FROM \"products\"\r\n"
				+" WHERE \"category_id\"=?";

		ArrayList<ProductDTO> list = null;

		int products_id,category_id,price;
		String product_name, english_name, sub_title,description
		,product_status,img_path,img_s_path,bg_color,poster_path;
		Date release_date;

		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<ProductDTO>();
				do {
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
					img_s_path = rs.getString("img_s_path");
					bg_color = rs.getString("bg_color");
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
							.img_s_path(img_s_path)
							.bg_color(bg_color)
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
	public List<MenuListDTO> selectList(int category_num) throws SQLException {
		
		return null;
	}
	
}
