package mvc.command.myflavor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.myflavor.FlavorDTO;
import mvc.domain.myflavor.MyFlavorCatalog;
import mvc.domain.myflavor.MyFlavorResultDTO;
import mvc.domain.user.UserDTO;

public class RegisterHandler implements CommandHandler{

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String requestMethode = request.getMethod();

        if (requestMethode.equals("GET")) {
            System.out.println("> flavor.RegisterHandler....get()");
            return "/WEB-INF/views/play/myflavor/register.jsp";

        } else if (requestMethode.equals("POST")) {
            request.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession(false);
            UserDTO loginUser = (session == null) ? null : (UserDTO) session.getAttribute("loginUser");
            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/login/login.do");
                return null;
            }
            String writerUserId = loginUser.getUser_id();

            String size = request.getParameter("size");
            String title = request.getParameter("title");
            String[] productSeq = request.getParameterValues("productSeq");

            if (size == null || title == null || productSeq == null) {
                response.sendRedirect(request.getContextPath() +
                        "/play/myflavor/register.do?error=Y");
                return null;
            }

            int seq = ThreadLocalRandom.current().nextInt(1000, 9999);

            List<FlavorDTO> flavors = new ArrayList<>();
            for (String id : productSeq) {
                FlavorDTO f = MyFlavorCatalog.find(id);
                if (f != null) flavors.add(f);
            }

            MyFlavorResultDTO dto = new MyFlavorResultDTO(
                    seq,
                    size,
                    title,
                    null,
                    writerUserId,
                    flavors
            );

 
            session.setAttribute("MYFLAVOR_" + seq, dto);

            response.sendRedirect(request.getContextPath() +
                    "/play/myflavor/register-complete.do?seq=" + seq + "&register=Y");
            return null;
        }

        return null;
    }
}
