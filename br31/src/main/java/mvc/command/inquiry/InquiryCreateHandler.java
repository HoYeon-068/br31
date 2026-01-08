package mvc.command.inquiry;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.inquiry.InquiryCreateDTO;
import mvc.persistence.inquiry.InquiryCreateDAO;

public class InquiryCreateHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        }

        request.setCharacterEncoding("UTF-8");
        
        if (request.getParameter("counsel_type") == null
        	    || request.getParameter("counsel_type").isEmpty()
        	    || request.getParameter("detail_type") == null
        	    || request.getParameter("detail_type").isEmpty()) {

        	    request.setAttribute("error", "상담유형과 내용유형을 선택해주세요.");
        	    return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        	}


        String postPw = request.getParameter("post_pw");
        String postPwConfirm = request.getParameter("post_pw_confirm");

        if (postPw == null || postPwConfirm == null || !postPw.equals(postPwConfirm)) {
            request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        }

        // 🔹 DTO는 반드시 먼저 생성
        InquiryCreateDTO dto = new InquiryCreateDTO();

        // 발생일시
        String occurDateStr =
            request.getParameter("occur_date") + " " +
            request.getParameter("occur_hour") + ":" +
            request.getParameter("occur_min");
        Date occurDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(occurDateStr);
        dto.setOccurDate(occurDate);

        // 전화번호
        String p1 = request.getParameter("phone1");
        String p2 = request.getParameter("phone2");
        String p3 = request.getParameter("phone3");

        if (p1 != null && !p1.isEmpty()
            && p2 != null && !p2.isEmpty()
            && p3 != null && !p3.isEmpty()) {
            dto.setPhone(p1 + "-" + p2 + "-" + p3);
        }

        // 이메일
        String emailId = request.getParameter("email_id");
        String emailDomain = request.getParameter("email_domain");

        if (emailId != null && !emailId.isEmpty()
            && emailDomain != null && !emailDomain.isEmpty()) {
            dto.setEmail(emailId + "@" + emailDomain);
        }

        dto.setCounselType(request.getParameter("counsel_type"));
        dto.setDetailType(request.getParameter("detail_type"));
        dto.setTitle(request.getParameter("title"));
        dto.setContent(request.getParameter("content"));
        dto.setName(request.getParameter("name"));
        dto.setPostPw(postPw);

        // 비회원
        dto.setUserId("GUEST");

        String storeId = request.getParameter("store_id");
        dto.setStoreId(storeId == null || storeId.isEmpty() ? null : Long.parseLong(storeId));

        InquiryCreateDAO dao = new InquiryCreateDAO();
        dao.insert(dto);

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(
            "<script>" +
            "alert('상담 내용이 정상적으로 저장 되었습니다. 고객님께서 남겨주신 E-mail 또는 연락처를 통해 신속히 답변드릴 수 있도록 하겠습니다. (주말, 공휴일에 남겨주신 글은 평일에 확인, 답변 드리는 점 양해 부탁드립니다.) 감사합니다.');" +
            "location.href='" + request.getContextPath() + "/customer/list.do';" +
            "</script>"
        );
        return null;
    }

}
