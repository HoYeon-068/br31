package mvc.command.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.story.CampaignDTO;
import mvc.persistence.story.CampaignDAO;
import mvc.persistence.story.CampaignDAOImpl;

public class CampaignListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Connection conn = ConnectionProvider.getConnection();
		
		
		CampaignDAO dao = new CampaignDAOImpl(conn);
		
		java.util.List<CampaignDTO> list = null;
		
		try {
			list = dao.campaignList();
			request.setAttribute("campaignList", list);
			System.out.println("size=" + (list == null ? "null" : list.size()));
		} catch (Exception e) {
			System.out.println(" > CampaignListHandler.process() Exception...");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return "/views/story/be-better.jsp";
	}

}
