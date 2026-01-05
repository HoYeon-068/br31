package mvc.persistence.plaza;

import java.util.List;

import mvc.domain.plaza.ConsultingDTO;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.domain.plaza.PlazaViewDTO;

public interface PlazaDAO {
	// plaza 리스트
	List<PlazaSelectDTO> select();
	
	// 카테고리로 리스트 보기
	List<PlazaSelectDTO> select(String categoryId);
	
	// 상세보기
	PlazaViewDTO view(int seq);
	
	// 컨설팅 insert
	int insertConsulting(ConsultingDTO dto);
	
	// 아이스크림 맛 제안 insert
	// int insertNew()
}
