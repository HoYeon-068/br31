package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.menu.ProductTagDTO;

public class ProductTagDAOImpl implements ProductTagDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ProductTagDTO vo = null;

	// 1. 생성자 DI 
	public ProductTagDAOImpl() {
		super(); 
	} 
	public ProductTagDAOImpl(Connection conn) {
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
	public List<ProductTagDTO> select(int products_num) throws SQLException {
		String sql = "SELECT * \r\n"
				+ "FROM \"product_tag\"\r\n"
				+" WHERE \"products_id\"=?";

		ArrayList<ProductTagDTO> list = null;

		int product_tag_id,products_id;
		String tag;

		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, products_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<ProductTagDTO>();
				do {
					// seq, title, writer, email, writedate, readed
					product_tag_id=rs.getInt("product_tag_id");
					products_id = rs.getInt("products_id");
					tag=rs.getString("tag");

					vo = new ProductTagDTO().builder()
							.product_tag_id(product_tag_id)
							.products_id(products_id)
							.tag(tag)
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
	
}
