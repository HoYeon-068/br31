package mvc.command.mypage;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.persistence.user.UserDAO;
import mvc.persistence.user.UserDAOImpl;

public class PwdChangeHandler implements CommandHandler {

  @Override
  public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/plain; charset=UTF-8");

    HttpSession session = request.getSession(false);
    if (session == null) {
      response.getWriter().print("NO_SESSION");
      response.getWriter().flush();
      return null;
    }

    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.getWriter().print("NO_LOGIN");
      response.getWriter().flush();
      return null;
    }

    String userId = loginUser.getUser_id();
    String oldPwd = request.getParameter("oldPassword");
    String newPwd = request.getParameter("newPassword");

    if (oldPwd == null || oldPwd.trim().isEmpty() || newPwd == null || newPwd.trim().isEmpty()) {
      response.getWriter().print("EMPTY");
      response.getWriter().flush();
      return null;
    }

    Connection conn = null;
    try {
      conn = ConnectionProvider.getConnection();
      UserDAO dao = new UserDAOImpl(conn);

      boolean okOld = dao.checkPassword(userId, oldPwd);
      if (!okOld) {
        response.getWriter().print("WRONG_OLD");
        response.getWriter().flush();
        return null;
      }

      int updated = dao.updatePassword(userId, newPwd);
      response.getWriter().print(updated == 1 ? "OK" : "FAIL");
      response.getWriter().flush();
      return null;

    } catch (Exception e) {
      e.printStackTrace(); // 톰캣 콘솔 확인용
      response.getWriter().print("ERROR");
      response.getWriter().flush();
      return null;

    } finally {
      if (conn != null) try { conn.close(); } catch(Exception ignore){}
    }
  }
}
