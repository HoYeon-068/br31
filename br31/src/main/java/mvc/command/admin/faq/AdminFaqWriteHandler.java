package mvc.command.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.admin_faq.AdminFaqDTO;
import mvc.persistence.admin_faq.AdminFaqDAO;

public class AdminFaqWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AdminFaqDAO dao = new AdminFaqDAO();

		// GET → 등록 화면
		if (request.getMethod().equalsIgnoreCase("GET")) {

			request.setAttribute(
					"contentPage",
					"/WEB-INF/views/admin/faq/write.jsp"
					);

			return "/WEB-INF/views/admin/admin_layout.jsp";
		}

		// POST → 등록 처리
		request.setCharacterEncoding("UTF-8");

		System.out.println(">>> AdminFaqWriteHandler POST 진입");

		AdminFaqDTO dto = new AdminFaqDTO();
		dto.setQuestion(request.getParameter("question"));
		dto.setAnswer(request.getParameter("answer"));

		System.out.println("question = " + request.getParameter("question"));
		System.out.println("answer = " + request.getParameter("answer"));

		dto.setFaqCategoryId(1L);
		System.out.println("faqCategoryId = " + dto.getFaqCategoryId());

		int result = dao.insert(dto);
		System.out.println("FAQ INSERT result = " + result);

		response.sendRedirect(
				request.getContextPath() + "/admin/faq/list.do"
				);
		return null;

	}
}
