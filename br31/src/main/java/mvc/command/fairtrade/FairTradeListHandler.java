package mvc.command.fairtrade;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.fairtrade.FairTradeDTO;
import mvc.persistence.fairtrade.FairTradeDAO;

public class FairTradeListHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        FairTradeDAO dao = new FairTradeDAO();
        List<FairTradeDTO> list = dao.selectList();

        request.setAttribute("list", list);
        request.setAttribute("totalCount", list.size());

        return "/views/information-center/fairtrade/list.jsp";
    }
}
