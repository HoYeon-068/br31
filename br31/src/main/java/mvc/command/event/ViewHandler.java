package mvc.command.event;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.event.EventDTO;
import mvc.persistence.event.EventDAO;
import mvc.persistence.event.EventDAOImpl;

public class ViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    int seq = Integer.parseInt(request.getParameter("seq"));

	    Connection conn = ConnectionProvider.getConnection();
	    EventDAO dao = new EventDAOImpl(conn);
	    List<EventDTO> ongoingList = dao.selectOngoing(seq);

	    try {
	        EventDTO dto = dao.view(seq);

	        // store_scope가 INCLUDE/EXCLUDE일 때만 매장 조회
	        List<String> storeList = new ArrayList<>();
	        boolean hasStoreButton = false;

	        if (dto != null) {
	            String scope = dto.getStoreScope(); // NONE/INCLUDE/EXCLUDE or null

	            if (scope != null && !"NONE".equals(scope)) {
	                storeList = dao.selectStoreNames(seq);

	                hasStoreButton = (storeList != null && !storeList.isEmpty());
	            }
	        }
	        
	        long now = System.currentTimeMillis();
	        for (EventDTO e : ongoingList) {
	            if (e.getEndDate() != null) {
	                long diff = e.getEndDate().getTime() - now;
	                int dday = (int) Math.ceil(diff / (1000.0 * 60 * 60 * 24));
	                e.setDday(dday);
	            }
	        }

	        request.setAttribute("dto", dto);
	        request.setAttribute("storeList", storeList);
	        request.setAttribute("hasStoreButton", hasStoreButton);
	        request.setAttribute("ongoingList", ongoingList);

	    } finally {
	        conn.close();
	    }

	    return "/views/play/event/view.jsp";
	}


}
