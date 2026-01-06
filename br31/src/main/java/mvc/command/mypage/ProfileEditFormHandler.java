package mvc.command.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler; // 너희 프로젝트 기준
import mvc.domain.user.UserDTO;

public class ProfileEditFormHandler implements CommandHandler {

	 @Override
	    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	        HttpSession session = request.getSession(false);
	        

	        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        
	        if(session == null || loginUser == null){
	            response.sendRedirect(request.getContextPath() + "/login.do");
	            return null;
	        }

	        request.setAttribute("loginUser", loginUser);  // ✅ 이거 꼭!
	        return "/WEB-INF/views/mypage/profileEdit.jsp";
	    }
}
