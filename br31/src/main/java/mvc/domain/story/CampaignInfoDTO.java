package mvc.domain.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignInfoDTO {
	public int campaign_activity_info_id;
	public String campaign_activity_ym;
	public String campaign_activity_description;
	public String campaign_activity_title;
	public String campaign_activity_img_path;
	public int campaign_id;
	public String campaign_name;
}
