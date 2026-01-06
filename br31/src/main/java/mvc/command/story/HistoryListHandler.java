package mvc.command.story;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.story.HistoryFlavorDTO;
import mvc.persistence.story.HistoryFlavorDAO;
import mvc.persistence.story.HistoryFlavorDAOImpl;

public class HistoryListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Connection conn = ConnectionProvider.getConnection();
		
		HistoryFlavorDAO dao = new HistoryFlavorDAOImpl(conn);
		
		
		
		try {
			String releaseYear = request.getParameter("release_year");
			String mkt = request.getParameter("mkt");
			
			if (mkt != null) {
				int themeId = convertMktToThemeId(mkt);
				List<HistoryFlavorDTO> list = dao.listByTheme(themeId);
				request.setAttribute("list", list);
                request.setAttribute("mode", "theme");
                request.setAttribute("mkt", mkt);
				
			} else {
				int year = releaseYear == null ? 2025 : Integer.parseInt(releaseYear);
				List<HistoryFlavorDTO> list = dao.listByYear(year);
				request.setAttribute("list", list);
                request.setAttribute("mode", "year");
                request.setAttribute("release_year", year);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("HistoryListHandler.process() Exception...");
		} finally {
			conn.close();
		}
		
		
		return "/WEB-INF/views/story/history.jsp";
	}
	
	
	private int convertMktToThemeId(String mkt) {
	    switch (mkt) {
	        case "A": return 1;
	        case "B": return 2;
	        case "D": return 3;
	        case "H": return 4;
	        default:  return 1;
	    }
	}

}
