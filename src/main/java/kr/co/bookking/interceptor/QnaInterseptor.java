package kr.co.bookking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.bookking.util.CSRFTokenManager;


//CSRF인터셉트 관련
public class QnaInterseptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Qna 인터셉트 클래스 가동");
		
		String requestCrsf = CSRFTokenManager.getTokenFromRequest(request);
		String sessionCrsf = CSRFTokenManager.getTokenFromSession(request.getSession());
		
		if(requestCrsf == null || sessionCrsf == null){
			System.out.println("csrf 없음 : 방출");
			response.sendRedirect(request.getContextPath() +"/");
			return false;
		}
		
		//추가 로직 : 빼먹은 것들
		if(!requestCrsf.equals(sessionCrsf)){
			System.out.println("세션 crsf와 리퀘스트 crsf가 다름 : 방출 및 세션 삭제");
			request.getSession().removeAttribute("csrf");
			response.sendRedirect(request.getContextPath() +"/");
			return false;
		}
		
		return true;
	}

}
