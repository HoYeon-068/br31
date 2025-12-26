package mvc.persistence.menu;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.menu.ProductDTO;

public interface ProductDAO {
	List<ProductDTO> select() throws SQLException;
}
