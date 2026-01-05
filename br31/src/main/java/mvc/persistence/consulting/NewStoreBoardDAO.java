package mvc.persistence.consulting;

import java.sql.SQLException;
import java.util.List;


import mvc.domain.consulting.NewStoreBoardDTO;


public interface NewStoreBoardDAO {
	
	//글 가져오기
	List<NewStoreBoardDTO> select() throws SQLException;
	
	//글 하나 가져오기(상세보기)
	NewStoreBoardDTO selectOne(int seq) throws SQLException;
	
	//글 가져오기 페이징 처리 o
	List<NewStoreBoardDTO> select(int currentPage, int numberPerPage, String loc) throws SQLException;
	
	//페이지 수 반환
	int getTotalPages(int numberPerPage) throws SQLException;
	
	int getTotalPages(int numberPerPage,String loc) throws SQLException;
	
	// 전체 count 테이블 조회값이 몇개 있는지 반환
	int getTotalNum(String loc) throws SQLException;
	
	int getTotalNum() throws SQLException;
	
}
