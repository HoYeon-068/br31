package mvc.persistence.story;

import java.util.List;

import mvc.domain.story.HistoryFlavorDTO;

public interface HistoryFlavorDAO {
	List<HistoryFlavorDTO> listByYear(int year);
	List<HistoryFlavorDTO> listByTheme(int themeId);
}
