package mvc.persistence.story;

import java.util.List;

import mvc.domain.story.CampaignDTO;

public interface CampaignDAO {
	List<CampaignDTO> campaignList();
}
