package mvc.persistence.store;

import java.sql.SQLException;


public interface StoreDAO {
	
	//최대 100개까지 조회
	String select() throws SQLException;
	
	String select(String[] service_info,
	        String store_name,
	        String sido,
	        String sigungu,String[] store_type) throws SQLException;
	
	
}
