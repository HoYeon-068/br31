package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import mvc.domain.menu.IceNutritionDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;

public class IceNutritionDAOImpl implements IceNutritionDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private IceNutritionDTO vo=null;
	
	// 1. 생성자 DI 
	public IceNutritionDAOImpl() {
		super(); 
	} 
	public IceNutritionDAOImpl(Connection conn) {
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
	public IceNutritionDTO select(int products_num) throws SQLException {
		String sql = "SELECT * \r\n"
				+ "FROM \"ice_nutrition\"\r\n"
				+" WHERE \"products_id\"=?";

		
		int ice_nutrition_id,products_id,serving_size,calories,sugar
		    ,protein,saturated_fat,sodium;
		String allergens;
		

		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, products_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
					// seq, title, writer, email, writedate, readed
				ice_nutrition_id = rs.getInt("ice_nutrition_id");
				products_id = rs.getInt("products_id");
				serving_size = rs.getInt("serving_size");
				calories = rs.getInt("calories");
				sugar = rs.getInt("sugar");
				protein = rs.getInt("protein");
				saturated_fat = rs.getInt("saturated_fat");
				sodium = rs.getInt("sodium");
				allergens = rs.getString("allergens");

					vo = new IceNutritionDTO().builder()
							.ice_nutrition_id(ice_nutrition_id)
							.products_id(products_id)
							.serving_size(serving_size)
							.calories(calories)
							.sugar(sugar)
							.protein(protein)
							.saturated_fat(saturated_fat)
							.sodium(sodium)
							.allergens(allergens)
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
