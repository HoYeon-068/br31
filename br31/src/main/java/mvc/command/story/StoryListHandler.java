package mvc.command.story;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.story.StoryDTO;
import mvc.persistence.story.StoryDAO;
import mvc.persistence.story.StoryDAOImpl;

public class StoryListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
Connection conn = ConnectionProvider.getConnection();
		
		
		StoryDAO dao = new StoryDAOImpl(conn);
		
		java.util.List<StoryDTO> list = null;
		
		try {
			list = dao.storyList();
			request.setAttribute("storyList", list);
			System.out.println("size=" + (list == null ? "null" : list.size()));
		} catch (Exception e) {
			System.out.println(" > StoryListHandler.process() Exception...");
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return "/WEB-INF/views/story/story.jsp";
	}
	
}
