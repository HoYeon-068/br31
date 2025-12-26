package mvc.persistence.menu;

import java.sql.SQLException;

import mvc.domain.menu.IceNutritionDTO;

public interface IceNutritionDAO {
	public IceNutritionDTO select(int products_id) throws SQLException;
}
