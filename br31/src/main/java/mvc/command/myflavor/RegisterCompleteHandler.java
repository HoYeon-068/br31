package mvc.command.myflavor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class RegisterCompleteHandler implements CommandHandler{

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      String seq = request.getParameter("seq");
      
      if (seq == null) {
         response.sendRedirect(request.getContextPath() + "/play/myflavor/list.do");
         return null;
      }
      
      HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("MYFLAVOR_" + seq) == null) {
            response.sendRedirect(request.getContextPath() + "/play/myflavor/list.do");
            return null;
        }

        return "/WEB-INF/views/play/myflavor/register-complete.jsp";
        
        
      
   }

}
