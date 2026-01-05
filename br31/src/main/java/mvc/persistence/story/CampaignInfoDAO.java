package mvc.persistence.story;

import java.util.List;

import mvc.domain.story.CampaignInfoDTO;

public interface CampaignInfoDAO {
	
	List<CampaignInfoDTO> campaignInfoAll();
}
