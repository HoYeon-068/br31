package mvc.domain.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {
	public int campaign_id;
	public String campaign_name;
	public String campaign_description;
	public String campaign_summary;
	public String campaign_img_path;
	public String campaign_subtitle;
}
