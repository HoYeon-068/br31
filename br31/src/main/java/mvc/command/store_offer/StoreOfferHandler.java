package mvc.command.store_offer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class StoreOfferHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("StoreOfferHandler");
		
		return "/views/information-center/store-offer/offer.jsp";
	}
	
}
