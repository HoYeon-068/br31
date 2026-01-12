package mvc.command.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;

public class MyPageViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);
		
		return "/WEB-INF/views/mypage/mypage.jsp";
	}

}
