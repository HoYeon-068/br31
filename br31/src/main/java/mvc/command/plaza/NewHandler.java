package mvc.command.plaza;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.command.CommandHandler;
import mvc.domain.plaza.PlazaWriteDTO;
import mvc.domain.user.UserDTO;
import mvc.persistence.plaza.PlazaDAOImpl;

public class NewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String requsetMethod = request.getMethod();
		
		if (requsetMethod.equals("GET")) {
			
			return "/WEB-INF/views/play/plaza/new.jsp";
			
		} else if (requsetMethod.equals("POST")) {
			HttpSession session = request.getSession(false);
			UserDTO loginUser = (session == null) ? null : (UserDTO) session.getAttribute("loginUser");
			if (loginUser == null) {
			    response.sendRedirect(request.getContextPath() + "/login/login.do");
			    return null;
			}
		    
		    String userId = loginUser.getUser_id();

		    String uploadPath = request.getServletContext()
		            .getRealPath("/resources/upload/plaza");
		    System.out.println("uploadPath = " + uploadPath);
		    int maxSize = 15 * 1024 * 1024; // 15MB

		    MultipartRequest mr = new MultipartRequest(
		        request,
		        uploadPath,
		        maxSize,
		        "UTF-8",
		        new DefaultFileRenamePolicy()
		    );

		    Connection conn = ConnectionProvider.getConnection();
		    conn.setAutoCommit(false);

		    try {
		        PlazaDAOImpl dao = new PlazaDAOImpl(conn);

		        createPlazaAndFiles(request, mr, 1, userId, dao);

		        conn.commit();

		        response.sendRedirect(
		            request.getContextPath() + "/play/plaza/list.do"
		        );
		        return null;

		    } catch (Exception e) {
		        conn.rollback();
		        throw e;
		    } finally {
		        JdbcUtil.close(conn);
		    }
		}
	
		
		return null;
	}

	private int createPlazaAndFiles(
	        HttpServletRequest request,
	        MultipartRequest mr,
	        int plazaCategoryId,
	        String userId,
	        PlazaDAOImpl dao
	) throws Exception {

	    String title = mr.getParameter("title");
	    String content = mr.getParameter("content");

	    int isAuthorPublic = "Y".equals(mr.getParameter("is_applicant")) ? 0 : 1;
	    int personalInfoConsent = "Y".equals(mr.getParameter("is_policy")) ? 1 : 0;
	    int termsAgreement = "Y".equals(mr.getParameter("is_terms")) ? 1 : 0;
	    int ideaOfferAgreement = "Y".equals(mr.getParameter("is_copyright")) ? 1 : 0;

	    PlazaWriteDTO pdto = new PlazaWriteDTO();
	    pdto.setTitle(title);
	    pdto.setContent(content);
	    pdto.setIsAuthorPublic(isAuthorPublic);
	    pdto.setUserId(userId);
	    pdto.setPersonalInfoConsent(personalInfoConsent);
	    pdto.setTermsAgreement(termsAgreement);
	    pdto.setPlazaCategoryId(plazaCategoryId);
	    pdto.setIdeaOfferAgreement(ideaOfferAgreement);

	    int plazaId = dao.insertPlazaReturnId(pdto);

	    for (int i = 1; i <= 3; i++) {
	        String inputName = "attached_file_" + i;
	        String saved = mr.getFilesystemName(inputName);
	        String orig = mr.getOriginalFileName(inputName);

	        if (saved != null) {
	            dao.insertPlazaFile(plazaId, saved, orig);
	        }
	    }

	    return plazaId;
	}

}
