package mvc.persistence.consulting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.consulting.BizFaqDTO;
import mvc.domain.menu.IngredientDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;

public class BizFaqDAOImpl implements BizFaqDAO{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private BizFaqDTO vo = null;
	
	// 1. 생성자 DI 
	public BizFaqDAOImpl() {
		super(); 
	} 
	public BizFaqDAOImpl(Connection conn) {
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
	public List<BizFaqDTO> select() throws SQLException {
		
		String sql = "SELECT * FROM \"biz_faq\"";


		ArrayList<BizFaqDTO> list = null;

		int products_id;
		String product_name,sub_title,img_path,bg_color,tags,span_color,category_name; 
		
		
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BizFaqDTO>();
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

					vo = new BizFaqDTO().builder()
							.biz_faq_id()

					list.add(vo);

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
