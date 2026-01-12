package mvc.command.ccm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;   // ★ 이 줄이 핵심

public class CcmHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        return "/WEB-INF/views/information-center/customer/ccm.jsp";
    }
}
