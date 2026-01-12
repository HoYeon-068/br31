package mvc.persistence.menu;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.menu.ProductTagDTO;



public interface ProductTagDAO {
	List<ProductTagDTO> select(int products_num) throws SQLException;
	
	int insert(int products_id,String tag)throws SQLException;
}
