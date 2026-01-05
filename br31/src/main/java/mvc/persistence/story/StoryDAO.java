package mvc.persistence.story;

import java.sql.SQLException;
import java.util.List;

import mvc.domain.story.StoryDTO;

public interface StoryDAO {
	List<StoryDTO> storyList();
}
