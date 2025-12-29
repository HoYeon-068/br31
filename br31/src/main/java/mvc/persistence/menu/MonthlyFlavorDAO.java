package mvc.persistence.menu;

import java.sql.SQLException;

import mvc.domain.menu.MonthlyFlavorDTO;

public interface MonthlyFlavorDAO {
	public MonthlyFlavorDTO select() throws SQLException;
}
