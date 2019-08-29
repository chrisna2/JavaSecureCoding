package kr.co.bookking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("인터셉터 클래스 실행");
		HttpSession session = request.getSession(false);
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId == null || loginId == ""){
			
			response.sendRedirect(request.getContextPath() +"/user/userLogin");
			return false;
		}
		System.out.println(loginId);
		return true;
	}

}
