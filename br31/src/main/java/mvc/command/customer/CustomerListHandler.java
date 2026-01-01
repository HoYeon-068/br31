package mvc.command.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class CustomerListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 고객센터 메인은 데이터 처리 없음
        return "/views/information-center/customer/list.jsp";
    }
}
