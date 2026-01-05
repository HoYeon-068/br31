package mvc.persistence.store;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.store.StoreDTO;


public interface StoreDAO {
	
	//최대 100개까지 조회
	String select() throws SQLException;
	
	String select(String[] service_info) throws SQLException;
	
}
