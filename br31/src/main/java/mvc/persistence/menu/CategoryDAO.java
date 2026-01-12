package mvc.persistence.menu;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.menu.CategoryDTO;

public interface CategoryDAO {
	//카테고리 정보 전부 가져오기
	List<CategoryDTO> select() throws SQLException;
}
