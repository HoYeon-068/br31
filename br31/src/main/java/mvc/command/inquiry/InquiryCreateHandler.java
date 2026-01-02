package mvc.command.inquiry;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.inquiry.InquiryDTO;
import mvc.persistence.inquiry.InquiryDAO;

public class InquiryCreateHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return "/views/information-center/consulting/myvoc-create.jsp";
        }

        request.setCharacterEncoding("UTF-8");

        String postPw = request.getParameter("post_pw");
        String postPwConfirm = request.getParameter("post_pw_confirm");

        if (!postPw.equals(postPwConfirm)) {
            request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "/views/information-center/consulting/myvoc-create.jsp";
        }

        // 발생일시 조합
        String occurDateStr =
            request.getParameter("occur_date") + " " +
            request.getParameter("occur_hour") + ":" +
            request.getParameter("occur_min");

        Date occurDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(occurDateStr);

        // 전화번호 조합
        String phone =
            request.getParameter("phone1") + "-" +
            request.getParameter("phone2") + "-" +
            request.getParameter("phone3");

        // 이메일 조합
        String email =
            request.getParameter("email_id") + "@" +
            request.getParameter("email_domain");

        InquiryDTO dto = new InquiryDTO();
        dto.setCounselType(request.getParameter("counsel_type"));
        dto.setDetailType(request.getParameter("detail_type"));
        dto.setTitle(request.getParameter("title"));
        dto.setOccurDate(occurDate);
        dto.setContent(request.getParameter("content"));
        dto.setName(request.getParameter("name"));
        dto.setPhone(phone);
        dto.setEmail(email);
        dto.setPostPw(postPw);

        // 비회원 고정 user_id
        dto.setUserId("GUEST");

        // 매장 선택 안 했을 수 있음
        String storeId = request.getParameter("store_id");
        dto.setStoreId(storeId == null || storeId.isEmpty() ? null : Long.parseLong(storeId));

        InquiryDAO dao = new InquiryDAO();
        dao.insert(dto);

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(
            "<script>" +
            "alert('상담 내용이 정상적으로 저장 되었습니다. 고객님께서 남겨주신 E-mail 또는 연락처를 통해 신속히 답변드릴 수 있도록 하겠습니다.(주말, 공휴일에 남겨주신 글은 평일에 확인,답변 드리는 점 양해 부탁드립니다.) 감사합니다.');" +
            "location.href='" + request.getContextPath() + "/customer/list.do';" +
            "</script>"
        );
        return null;

}
}
