package mvc.persistence.consulting;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.consulting.BizFaqDTO;


public interface BizFaqDAO {
	
	//전체 FAQ 가져오기
	List<BizFaqDTO> select() throws SQLException;
}
