package mvc.command.consulting;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;


import mvc.command.CommandHandler;
import mvc.domain.consulting.NewStoreBoardDTO;
import mvc.domain.consulting.PageVO;
import mvc.persistence.consulting.NewStoreBoardDAO;
import mvc.persistence.consulting.NewStoreBoardDAOImpl;

public class ListHandler implements CommandHandler{
	
	// [1]
		int currentPage = 1;  // 현재 페이지 번호
		int numberPerPage = 10; // 한 페이지에 출력할 게시글 수
		int numberOfPageBlock = 10;  // [1] 2 3 4 5 6 7 8 9 10 >
		int totalRecords = 0;   // 총 레코드 수
		int totalPages = 0 ;    // 총 페이지 수
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListHandler");
		
		String loc=request.getParameter("findword")==null?"":request.getParameter("findword");
		
		try {
			this.currentPage = Integer.parseInt( request.getParameter("currentPage" ) );			
		} catch (Exception e) {
			this.currentPage = 1;
		}

		try {
			this.numberPerPage = Integer.parseInt( request.getParameter("numberPerPage" ) );			
		} catch (Exception e) {
			this.numberPerPage = 10;
		}
		
		
		Connection conn = ConnectionProvider.getConnection();
		NewStoreBoardDAO dao = new NewStoreBoardDAOImpl(conn);
		
		java.util.List<NewStoreBoardDTO> list = null;
		mvc.domain.consulting.PageVO pvo = null;
		
		
		
		try {
			//list = dao.select();
			if (loc.equals("")) {
				pvo = new PageVO(currentPage, numberPerPage, numberOfPageBlock);				
			}else {
				
				pvo = new PageVO(currentPage, numberPerPage, numberOfPageBlock,loc);				
			}
			
			list = dao.select(this.currentPage, this.numberPerPage,loc); // 페이징 처리 O
		} catch (Exception e) {
			System.out.println("> consulting ListHandler.process() Exception...");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		request.setAttribute("activeMenu", "store-list");
		request.setAttribute("list", list);
		request.setAttribute("pvo", pvo);
		
		return "/views/information-center/consulting/store-list.jsp";
	}
	
}
