package mvc.domain.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryDTO {
	public int story_id;
	public int story_year;
	public String story_logo_img_path;
	public String story_title;
	public String story_description;
	public String story_img_path;
}
