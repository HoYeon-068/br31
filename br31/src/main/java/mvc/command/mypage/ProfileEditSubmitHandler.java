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

            String nicknameChecked = trim(request.getParameter("nicknameChecked"));
            String emailChecked    = trim(request.getParameter("emailChecked"));
            String phoneChecked    = trim(request.getParameter("phoneChecked"));

            Boolean phoneAuthOk = (Boolean) session.getAttribute("MYPAGE_PHONE_AUTH_OK");

            String newEmail = null;
            if (!isEmpty(emailId) && !isEmpty(emailDomain)) {
                newEmail = emailId + "@" + emailDomain;
            }

            

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


            conn = ConnectionProvider.getConnection();

            UserDAO dao = new UserDAOImpl(conn);

            // 닉네임 변경 체크
            if (!isEmpty(nickname) && !nickname.equals(loginUser.getNickname())) {
                if (!"true".equalsIgnoreCase(nicknameChecked)) {
                    request.setAttribute("error", "닉네임 중복확인을 해주세요.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
                System.out.println("[ProfileEditSubmit] isNicknameAvailable...");
                if (!dao.isNicknameAvailable(userId, nickname)) {
                    request.setAttribute("error", "이미 사용 중인 닉네임입니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
            } else {
                nickname = loginUser.getNickname();
            }

            // 이메일 변경 체크
            if (!isEmpty(newEmail) && !newEmail.equals(loginUser.getEmail())) {
                if (!"true".equalsIgnoreCase(emailChecked)) {
                    request.setAttribute("error", "이메일 중복확인을 해주세요.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
                System.out.println("[ProfileEditSubmit] isEmailAvailable...");
                if (!dao.isEmailAvailable(userId, newEmail)) {
                    request.setAttribute("error", "이미 사용 중인 이메일입니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
            } else {
                newEmail = loginUser.getEmail();
            }

            // 휴대폰 변경 체크
            String originPhone = loginUser.getPhone_no();
            String curPhone = phoneNo;

            // 비교를 안전하게 (하이픈 제거)
            String oPhone = originPhone == null ? "" : originPhone.replace("-", "").trim();
            String cPhone = curPhone == null ? "" : curPhone.replace("-", "").trim();

            boolean phoneChanged = !isEmpty(cPhone) && !cPhone.equals(oPhone);

            if (phoneChanged) {
                if (!"true".equalsIgnoreCase(phoneChecked)) {
                    request.setAttribute("error", "휴대폰 번호를 변경하셨다면 인증을 완료해야 합니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
                if (phoneAuthOk == null || phoneAuthOk != true) {
                    request.setAttribute("error", "휴대폰 인증이 완료되지 않았습니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
            } else {
                phoneNo = loginUser.getPhone_no(); // 그대로 유지
            }


            // 비밀번호 변경
            boolean wantChangePwd =  !isEmpty(newPwd);
            if (wantChangePwd) {
                dao.updatePassword(userId, newPwd);
            }
            
            
            if (wantChangePwd) {
                System.out.println("[ProfileEditSubmit] checkPassword...");
                boolean okOld = dao.checkPassword(userId, oldPwd);
                if (!okOld) {
                    request.setAttribute("error", "기존 비밀번호가 올바르지 않습니다.");
                    return "/WEB-INF/views/mypage/profileEdit.jsp";
                }
                System.out.println("[ProfileEditSubmit] updatePassword...");
                dao.updatePassword(userId, newPwd);
            }
            
            // 저장하려면 oldPwd 입력
            if (isEmpty(oldPwd)) {
                request.setAttribute("error", "저장하려면 현재 비밀번호를 입력해야 합니다.");
                return "/WEB-INF/views/mypage/profileEdit.jsp";
            }
            
            System.out.println("[ProfileEditSubmit] checkPassword(for save)...");
            boolean okCurrent = dao.checkPassword(userId, oldPwd);
            if (!okCurrent) {
                request.setAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
                return "/WEB-INF/views/mypage/profileEdit.jsp";
            }


            int updated = dao.updateProfile(userId, nickname, newEmail, phoneNo, profilePathToSave);

            UserDTO refreshed = dao.selectByUserId(userId);

            session.setAttribute("loginUser", refreshed);

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
