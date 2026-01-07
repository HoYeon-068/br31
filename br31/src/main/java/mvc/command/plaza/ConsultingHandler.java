package mvc.command.plaza;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.command.CommandHandler;
import mvc.domain.plaza.ConsultingDTO;
import mvc.persistence.plaza.PlazaDAO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class ConsultingHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String requestMethod = request.getMethod();

        if ("GET".equalsIgnoreCase(requestMethod)) {
            return "/WEB-INF/views/play/plaza/consulting.jsp";
        } else if ("POST".equalsIgnoreCase(requestMethod)) {

            request.setCharacterEncoding("UTF-8");

            int personalInfoConsent = parseIntOrDefault(request.getParameter("personalInfoConsent"), 0);

            String name = nvl(request.getParameter("contact_form_username"));

            String tel1 = nvl(request.getParameter("contact_form_tel1"));
            String tel2 = nvl(request.getParameter("contact_form_tel2"));
            String tel3 = nvl(request.getParameter("contact_form_tel3"));
            String tel = joinTel(tel1, tel2, tel3);

            String email = nvl(request.getParameter("contact_from_email"));

            String sido = nvl(request.getParameter("sido"));
            String gugun = nvl(request.getParameter("gugun"));
            String preferredRegion = (sido + " " + gugun).trim();

            String content = nvl(request.getParameter("other_inquiry_details"));

            // 주소
            String zipcode = request.getParameter("zipcode");
            String addr = nvl(request.getParameter("addr"));
            String addrDetail = nvl(request.getParameter("addr_detail"));
            String address = (addr + " " + addrDetail).trim();

            // 면적 (평수) 
            String storeArea = toStoreArea(nvl(request.getParameter("pyeong"))); 

            // 희망 시기
            String openPeriod = nvl(request.getParameter("store_open_period"));
            String openPeriodEtc = nvl(request.getParameter("store_open_period_etc"));
            String desiredOpeningDate = "기타".equals(openPeriod) ? openPeriodEtc : openPeriod;

            // 투자 금액
            String amount = nvl(request.getParameter("store_open_amount"));
            String amountEtc = nvl(request.getParameter("store_open_amount_etc"));
            String investmentAmount = "기타".equals(amount) ? amountEtc : amount;

            // 연락가능시간
            String fromH = nvl(request.getParameter("contact_from_hour"));
            String fromM = nvl(request.getParameter("contact_from_minute"));
            String toH = nvl(request.getParameter("contact_to_hour"));
            String toM = nvl(request.getParameter("contact_to_minute"));
            String time = buildTime(fromH, fromM, toH, toM);

            ConsultingDTO dto = ConsultingDTO.builder()
                    .investmentAmount(investmentAmount)
                    .desiredOpeningDate(desiredOpeningDate)
                    .storeArea(storeArea)
                    .personalInfoConsent(personalInfoConsent)
                    .content(content)
                    .name(name)
                    .tel(tel)
                    .time(time)
                    .email(email)
                    .preferredRegion(preferredRegion)
                    .zipcode(zipcode)
                    .address(address)
                    .status(0)
                    .build();

            // ===== 3) DB 저장 =====
            Connection conn = null;
            try {
                conn = ConnectionProvider.getConnection();
                PlazaDAO dao = new PlazaDAOImpl(conn);

                int rowCount = dao.insertConsulting(dto);

                if (rowCount == 1) {
                    response.sendRedirect(request.getContextPath() + "/WEB-INF/views/play/plaza/list.do");
                    return null; 
                } else {
                    request.setAttribute("error", "등록에 실패했습니다. 다시 시도해주세요.");
                    request.setAttribute("dto", dto);
                    return "/WEB-INF/views/play/plaza/consulting.jsp";
                }

            } finally {
                JdbcUtil.close(conn);
            }
        }

        return null;
    }


    private static String nvl(String s) {
        return s == null ? "" : s.trim();
    }

    private static int parseIntOrDefault(String s, int def) {
        try {
            if (s == null || s.trim().isEmpty()) return def;
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return def;
        }
    }

    private static String joinTel(String t1, String t2, String t3) {
        if (t1.isEmpty() && t2.isEmpty() && t3.isEmpty()) return "";
        return t1 + "-" + t2 + "-" + t3;
    }

    private static String buildTime(String fromH, String fromM, String toH, String toM) {
        if (fromH.isEmpty() || fromM.isEmpty() || toH.isEmpty() || toM.isEmpty()) return "";
        return fromH + ":" + fromM + " ~ " + toH + ":" + toM;
    }

    private static String toStoreArea(String pyeong) {
        if (pyeong == null || pyeong.isEmpty()) return "";
        if ("30".equals(pyeong)) return "30평이상";
        if ("40".equals(pyeong)) return "40평이상";
        if ("50".equals(pyeong)) return "50평이상";
        return pyeong;
    }
}
