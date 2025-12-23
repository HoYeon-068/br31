package com.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, 
urlPatterns = 
{ "/days09/admin/*", "/days09/board/write.jsp",
		"/days09/board/delete.jsp" }

)*/
public class LoginCheckFilter extends HttpFilter implements Filter {

	public LoginCheckFilter() {
		super();

	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoginCheck init()...");
		
		
		/*
		 * System.out.println("DispatcherServlet.init().."); String mappingPath=
		 * this.getInitParameter("mappingPath"); String
		 * realPath=this.getServletContext().getRealPath(mappingPath);
		 * 
		 * System.out.println(mappingPath); System.out.println(realPath);
		 */
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("> LoginCheckFilter.doFilter()...");

		
		HttpServletRequest jrequest = (HttpServletRequest) request;
		HttpServletResponse jresponse = (HttpServletResponse) response;
		
		jrequest.getRequestURL();
		
		
		
		
		String logonId = null;
		boolean isLogon = false;

		HttpSession session = jrequest.getSession(false);
		if (session != null) {

			logonId = (String) session.getAttribute("auth");
			if (logonId != null) {
				isLogon = true;
			}
		}

		if (isLogon) {
			chain.doFilter(request, response);
		} else {
			
			String referer=jrequest.getRequestURI();
			session.setAttribute("referer", referer);
			// 로그인 하지 않고 글쓰기, 관리지 메뉴 사용 X -> 로그인페이지로 이동
			String location = "/jspPro/days09/member/logon.jsp";
			jresponse.sendRedirect(location);
		}

	}

	public void destroy() {
		System.out.println("LoginCheck destroy()...");

	}

}
