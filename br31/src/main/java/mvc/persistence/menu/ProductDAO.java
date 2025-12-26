package mvc.persistence.menu;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;

public interface ProductDAO {
	ProductDTO selectOne(int category_num) throws SQLException;
	List<MenuListDTO> selectList(String category) throws SQLException;
	
}
