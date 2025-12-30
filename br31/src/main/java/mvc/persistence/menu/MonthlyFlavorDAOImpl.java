package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.domain.menu.IceNutritionDTO;
import mvc.domain.menu.MonthlyFlavorDTO;
import mvc.domain.menu.ProductDTO;

public class MonthlyFlavorDAOImpl implements MonthlyFlavorDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MonthlyFlavorDTO vo=null;
	
	// 1. 생성자 DI 
	public MonthlyFlavorDAOImpl() {
		super(); 
	} 
	public MonthlyFlavorDAOImpl(Connection conn) {
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
	
	
	//수정예정
	@Override
	public MonthlyFlavorDTO select() throws SQLException {
		String msql = "SELECT * " +
	             "FROM ( " +
	             "    SELECT * " +
	             "    FROM \"monthly_flavor\" " +
	             "    ORDER BY \"monthly_flavor_id\" DESC " +
	             ") " +
	             "WHERE ROWNUM = 1";
		
		
		int monthly_flavor_id,products_id;
		String poster_path,youtube_path,img_path;
		

		try {			
			pstmt = conn.prepareStatement(msql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
					// seq, title, writer, email, writedate, readed
				monthly_flavor_id = rs.getInt("monthly_flavor_id");
				poster_path = rs.getString("poster_path");
				youtube_path = rs.getString("youtube_path");
				products_id=rs.getInt("products_id");
				img_path=rs.getString("img_path");
				
				vo=MonthlyFlavorDTO.builder()
						.monthly_flavor_id(monthly_flavor_id)
						.poster_path(poster_path)
						.youtube_path(youtube_path)
						.products_id(products_id)
						.img_path(img_path)
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
	
}
