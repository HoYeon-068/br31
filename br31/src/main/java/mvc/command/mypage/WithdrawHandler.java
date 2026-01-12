package mvc.command.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.service.user.UserService;

public class WithdrawHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.do");
		    return null;
		}

		String password = request.getParameter("password");
		if (password == null || password.isBlank()) {
		    request.setAttribute("error", "비밀번호를 입력해주세요.");
		    return "/WEB-INF/views/mypage/withdraw.jsp";
		}

		// 비밀번호 검증(서비스에 맞게)
		boolean ok = new UserService().checkPassword(loginUser.getUser_id(), password);
		if (!ok) {
		    request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
		    return "/WEB-INF/views/mypage/withdraw.jsp";
		}

		// 관리자 계정은 탈퇴 금지
		if ("admin_master".equalsIgnoreCase(loginUser.getUser_id())) {
		    request.setAttribute("error", "관리자 계정은 탈퇴할 수 없습니다.");
		    return "/WEB-INF/views/mypage/withdraw.jsp";
		}

		// 삭제 실행
		new UserService().deleteUser(loginUser.getUser_id());

		// 4) 로그아웃 처리
		request.getSession().invalidate();

		// 5) 완료 페이지
		response.sendRedirect(request.getContextPath() + "/mypage/withdrawResult.do");
		return null;

	}

}
