package mvc.command.plaza;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.domain.user.UserDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("> Plaza.ListHandler process()...");

        String category = request.getParameter("category");

        HttpSession session = request.getSession(false);
        UserDTO loginUser = (session == null) ? null : (UserDTO) session.getAttribute("loginUser");
        String loginUserId = (loginUser == null) ? null : loginUser.getUser_id();

        Connection conn = ConnectionProvider.getConnection();
        PlazaDAO dao = new PlazaDAOImpl(conn);
        List<PlazaSelectDTO> list = null;

        try {

            boolean withLike = (loginUserId != null && !loginUserId.isBlank());

            if (category == null || category.equals("ALL")) {
                if (withLike) {
                    list = dao.select(loginUserId, true);
                    System.out.println("> Plaza.ListHandler select(loginUserId, withLike)...GET");
                } else {
                    list = dao.select();
                    System.out.println("> Plaza.ListHandler select()...GET");
                }
            } else {
                if (withLike) {
                    list = dao.select(category, loginUserId, true);
                    System.out.println("> Plaza.ListHandler select(category, loginUserId, withLike)...GET");
                } else {
                    list = dao.select(category);
                    System.out.println("> Plaza.ListHandler select(category)...GET");
                }
            }

            request.setAttribute("list", list);

        } catch (Exception e) {
            System.out.println("> Plaza.ListHandler process()...GET Exception");
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return "/WEB-INF/views/play/plaza/list.jsp";
    }
}
