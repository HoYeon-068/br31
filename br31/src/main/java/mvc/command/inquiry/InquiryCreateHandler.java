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

        // GET → 작성 화면
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        }

        request.setCharacterEncoding("UTF-8");

        /* 1. 필수 선택값 검증 */
        if (request.getParameter("counsel_type") == null
                || request.getParameter("counsel_type").isEmpty()
                || request.getParameter("detail_type") == null
                || request.getParameter("detail_type").isEmpty()) {

            request.setAttribute("error", "상담유형과 내용유형을 선택해주세요.");
            return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        }

        /* 2. 비밀번호 검증 */
        String postPw = request.getParameter("post_pw");
        String postPwConfirm = request.getParameter("post_pw_confirm");

        if (postPw == null || postPwConfirm == null || !postPw.equals(postPwConfirm)) {
            request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "/WEB-INF/views/information-center/consulting/myvoc_create.jsp";
        }

        /* 3. DTO 생성 */
        InquiryCreateDTO dto = new InquiryCreateDTO();

        /* 발생일시 */
        String occurDateStr =
                request.getParameter("occur_date") + " " +
                request.getParameter("occur_hour") + ":" +
                request.getParameter("occur_min");

        Date occurDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(occurDateStr);
        dto.setOccurDate(occurDate);

        /* 전화번호 */
        String p1 = request.getParameter("phone1");
        String p2 = request.getParameter("phone2");
        String p3 = request.getParameter("phone3");

        if (p1 != null && !p1.isEmpty()
                && p2 != null && !p2.isEmpty()
                && p3 != null && !p3.isEmpty()) {
            dto.setPhone(p1 + "-" + p2 + "-" + p3);
        }

        /* 이메일 */
        String emailId = request.getParameter("email_id");
        String emailDomain = request.getParameter("email_domain");

        if (emailId != null && !emailId.isEmpty()
                && emailDomain != null && !emailDomain.isEmpty()) {
            dto.setEmail(emailId + "@" + emailDomain);
        }

        /* 기본 정보 */
        dto.setCounselType(request.getParameter("counsel_type"));
        dto.setDetailType(request.getParameter("detail_type"));
        dto.setTitle(request.getParameter("title"));
        dto.setContent(request.getParameter("content"));
        dto.setName(request.getParameter("name"));
        dto.setPostPw(postPw);

        /* 회원 / 비회원 구분 */
        Object loginObj = request.getSession().getAttribute("loginUser");

        if (loginObj != null) {
            mvc.domain.user.UserDTO loginUser =
                (mvc.domain.user.UserDTO) loginObj;

            dto.setUserId(loginUser.getUser_id()); // 로그인 사용자
        } else {
            dto.setUserId("GUEST"); // 비회원 시연
        }


        /* 🔹 매장 (시연용 store_id) */
        String storeIdParam = request.getParameter("store_id");
        if (storeIdParam == null || storeIdParam.isEmpty()) {
            dto.setStoreId(1L); // 🔥 시연용 더미 매장 ID
        } else {
            dto.setStoreId(Long.parseLong(storeIdParam));
        }

        /* DB 저장 */
        InquiryCreateDAO dao = new InquiryCreateDAO();
        dao.insert(dto);

        /* 완료 후 이동 */
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(
            "<script>" +
            "alert('상담 내용이 정상적으로 저장 되었습니다.');" +
            "location.href='" + request.getContextPath() + "/customer/list.do';" +
            "</script>"
        );

        return null;
    }
}
