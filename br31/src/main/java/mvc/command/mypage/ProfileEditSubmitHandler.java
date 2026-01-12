package mvc.command.mypage;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConnectionProvider;

import mvc.command.CommandHandler;
import mvc.domain.user.UserDTO;
import mvc.persistence.user.UserDAO;
import mvc.persistence.user.UserDAOImpl;

public class ProfileEditSubmitHandler implements CommandHandler {

    public ProfileEditSubmitHandler() {}

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            System.out.println("[ProfileEditSubmit] start");

            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/login.do");
                return null;
            }

            UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/login.do");
                return null;
            }

            String userId = loginUser.getUser_id();

            String nickname    = trim(request.getParameter("nickname"));
            String emailId     = trim(request.getParameter("email_id"));
            String emailDomain = trim(request.getParameter("email_domain"));
            String phoneNo     = trim(request.getParameter("phone_no"));

            String oldPwd      = trim(request.getParameter("oldPassword"));
            String newPwd      = trim(request.getParameter("newPassword"));

            String profileImg  = trim(request.getParameter("profile_img"));

            // 저장하려면 현재 비밀번호 입력 + 검증만 통과하면 됨
            if (isEmpty(oldPwd)) {
                request.setAttribute("error", "저장하려면 현재 비밀번호를 입력해야 합니다.");
                return "/WEB-INF/views/mypage/profileEdit.jsp";
            }

            conn = ConnectionProvider.getConnection();
            UserDAO dao = new UserDAOImpl(conn);

            boolean okCurrent = dao.checkPassword(userId, oldPwd);
            if (!okCurrent) {
                request.setAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
                return "/WEB-INF/views/mypage/profileEdit.jsp";
            }

            // 프로필 이미지 경로 결정
            String profilePathToSave = loginUser.getProfile_img_path();
            if ("A".equalsIgnoreCase(profileImg)) {
                profilePathToSave = "/resources/images/mypage/img_profile_1.png";
            } else if ("B".equalsIgnoreCase(profileImg)) {
                profilePathToSave = "/resources/images/mypage/img_profile_2.png";
            } else {
                if (isEmpty(profilePathToSave)) {
                    profilePathToSave = "/resources/images/mypage/img_profile_1.png";
                }
            }

            // 닉네임: 비어있으면 기존 유지, 변경되면 서버에서 중복만 자동 검사
            if (isEmpty(nickname)) {
                nickname = loginUser.getNickname();
            }
            if (isEmpty(nickname)) {
                // 세션이 비정상인 경우 방어적으로 DB에서 재조회
                UserDTO dbUser = dao.selectByUserId(userId);
                nickname = dbUser.getNickname();
            }
            if (isEmpty(nickname)) {
                request.setAttribute("error", "닉네임 값이 비어있습니다. 다시 시도해주세요.");
                return "/WEB-INF/views/mypage/profileEdit.jsp";
            }
            if (!nickname.equals(loginUser.getNickname())) {
                if (!dao.isNicknameAvailable(userId, nickname)) {
                    request.setAttribute("error", "이미 사용 중인 닉네임입니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
            }

            // 이메일: 입력이 불완전하면 기존 유지, 변경되면 서버에서 중복만 자동 검사
            String newEmail;
            if (!isEmpty(emailId) && !isEmpty(emailDomain)) {
                newEmail = emailId + "@" + emailDomain;
            } else {
                newEmail = loginUser.getEmail();
            }
            if (!newEmail.equals(loginUser.getEmail())) {
                if (!dao.isEmailAvailable(userId, newEmail)) {
                    request.setAttribute("error", "이미 사용 중인 이메일입니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
            }

            // 휴대폰: 비어있으면 기존 유지, 변경되면 인증 강제 없이 저장 가능
            String originPhone = loginUser.getPhone_no();
            String oPhone = originPhone == null ? "" : originPhone.replace("-", "").trim();
            String cPhone = phoneNo == null ? "" : phoneNo.replace("-", "").trim();

            if (isEmpty(cPhone)) {
                phoneNo = loginUser.getPhone_no();
            } else {
            }

            // 새 비밀번호가 있으면 변경 (현재 비밀번호 검증 통과했으니 바로 변경)
            boolean wantChangePwd = !isEmpty(newPwd);
            if (wantChangePwd) {
                dao.updatePassword(userId, newPwd);
            }

            // 최종 프로필 업데이트
            dao.updateProfile(userId, nickname, newEmail, phoneNo, profilePathToSave);

            // 세션 갱신
            UserDTO refreshed = dao.selectByUserId(userId);
            session.setAttribute("loginUser", refreshed);

            // 휴대폰 인증 관련 세션값은 남아있어도 무방하지만, 정리해도 됨
            session.removeAttribute("MYPAGE_PHONE_AUTH_OK");
            session.removeAttribute("MYPAGE_PHONE_AUTH_PHONE");
            session.removeAttribute("MYPAGE_PHONE_AUTH_CODE");

            response.sendRedirect(request.getContextPath() + "/mypage/mypage.do");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("PROFILE_EDIT_ERROR");
            return null;

        } finally {
            if (conn != null) try { conn.close(); } catch(Exception ignore) {}
            System.out.println("[ProfileEditSubmit] end");
        }
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
