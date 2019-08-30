package kr.co.bookking.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//CSRF토큰 발행, 세션, request
public class CSRFTokenManager {

	public static String getTokenFromSession(HttpSession session) {
		
		String token = null;
		
		token = (String)session.getAttribute("csrf");
		
		if(token == null) {
			token=UUID.randomUUID().toString();
			session.setAttribute("csrf", token);
		}
		
		return token;
		
	}
	
	public static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter("csrf");
	}
	
}
