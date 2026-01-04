package mvc.command.story;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.story.CampaignDTO;
import mvc.domain.story.CampaignInfoDTO;
import mvc.persistence.story.CampaignDAO;
import mvc.persistence.story.CampaignDAOImpl;
import mvc.persistence.story.CampaignInfoDAO;
import mvc.persistence.story.CampaignInfoDAOImpl;

public class CampaignInfoHandler implements CommandHandler {

	 @Override
	    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	        Connection conn = null;
	        try {
	            conn = ConnectionProvider.getConnection();

	            CampaignDAO campaignDao = new CampaignDAOImpl(conn);
	            CampaignInfoDAO infoDao = new CampaignInfoDAOImpl(conn);

	            List<CampaignDTO> campaignList = campaignDao.campaignList();
	            List<CampaignInfoDTO> infoAll = infoDao.campaignInfoAll();

	            request.setAttribute("campaignList", campaignList);
	            request.setAttribute("infoAll", infoAll);

	            return "/views/story/be-better.jsp";

	        } finally {
	            if (conn != null) conn.close();
	        }
	    }
}
