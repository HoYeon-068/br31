package mvc.command.plaza;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaViewDTO;
import mvc.domain.user.UserDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ViewHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int seq = Integer.parseInt(request.getParameter("seq"));

        HttpSession session = request.getSession(false);
        UserDTO loginUser = (session == null) ? null : (UserDTO) session.getAttribute("loginUser");
        String loginUserId = (loginUser == null) ? null : loginUser.getUser_id();

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);

        try {
            boolean withLike = (loginUserId != null && !loginUserId.isBlank());

            PlazaViewDTO dto;
            if (withLike) {
                dto = dao.view(seq, loginUserId, true);
            } else {
                dto = dao.view(seq);
            }

            request.setAttribute("dto", dto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return "/WEB-INF/views/play/plaza/view.jsp";
    }
}
