package mvc.command.plaza;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class LikeHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }

        response.setContentType("application/json; charset=UTF-8");

        HttpSession session = request.getSession(false);
        UserDTO loginUser = (session == null) ? null : (UserDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            PrintWriter out = response.getWriter();
            out.print("{\"success\":false,\"needLogin\":true}");
            out.flush();
            return null;
        }

        String userId = loginUser.getUser_id();

        String plazaIdParam = request.getParameter("seq");
        int plazaId = Integer.parseInt(plazaIdParam);

        Connection conn = ConnectionProvider.getConnection();
        conn.setAutoCommit(false);

        boolean liked = false;

        try {
            PlazaDAOImpl dao = new PlazaDAOImpl(conn);

            if (dao.existsLike(plazaId, userId)) {
                dao.deleteLike(plazaId, userId);
                liked = false;
            } else {
                dao.insertLike(plazaId, userId);
                liked = true;
            }

            conn.commit();

            PrintWriter out = response.getWriter();
            out.print("{\"success\":true,\"liked\":" + liked + "}");
            out.flush();

            return null;

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();

            PrintWriter out = response.getWriter();
            out.print("{\"success\":false}");
            out.flush();

            return null;

        } finally {
            JdbcUtil.close(conn);
        }
    }
}
