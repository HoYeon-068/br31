package mvc.command.store_offer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class RegisterHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("StoreOfferHandler");
		
		String requestMethod = request.getMethod();
		
		if (requestMethod.equals("GET")) {	
			//String offerId = request.getParameter("offerId");
			//request.setAttribute("offerId", offerId);
			
			return "/WEB-INF/views/information-center/store-offer/register.jsp";
			
		} else if (requestMethod.equals("POST") ){
		
			return "/WEB-INF/views/information-center/store-offer/offer.jsp";
		}
		
		return null;
	
	}	
}
