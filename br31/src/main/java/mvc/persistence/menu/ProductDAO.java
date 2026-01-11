package mvc.persistence.menu;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.menu.IngredientDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.ProductDTO;

public interface ProductDAO {
	
	//상품 하나를 조회
	ProductDTO selectOne(int category_num) throws SQLException;
	
	//전체 상품 리스트를 가져옴
	List<ProductDTO> select() throws SQLException;
	
	// /menu/list.jsp 화면에 출력될 상품들 정보를 전부 가저옴
	List<MenuListDTO> selectList(String category) throws SQLException;
	
	// 주재료 테이블에 관한 컬럼들을 조회
	List<IngredientDTO> selectIngredient(int products_id) throws SQLException;
	
	ProductDTO getPrev(int categoryId, int productsId) throws SQLException;
	
	ProductDTO getNext(int categoryId, int productsId) throws SQLException;
	
	//상품 삽입
	int insert(ProductDTO dto) throws SQLException;
	
	//방금 생성된 상품의 id값 가져오기
	Integer getProductSeqNum() throws SQLException;
	
}
