package kr.co.bookking.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bookking.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	CartService cartService;
	
	// cart 리스트
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartView(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginId");
		List<Map<String, String>> list = cartService.getCartList(userId);
		
		model.addAttribute("cartList", list);
		model.addAttribute("pageType", "cart");
		model.addAttribute("contentFile", "cart/cart.jsp");
		return "home";
	}
	
	// cart 추가
	@RequestMapping(value = "/addCart", method = RequestMethod.GET)
	public @ResponseBody int addCart(int bookId,  int stock, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인된 사용자ID
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginId");
		model.addAttribute("loginId", userId);
		
		int count = cartService.isExistedCart(userId, bookId);	 //이미등록된 상품인지 확인
		
		//상품이 등록되어 있지 않으면 0 , 등록되어있으면 1
		if(count == 0){
			cartService.addCart(userId, stock, bookId);
		}
		return count;
	}
	
	//카트 상품 삭제
	@RequestMapping(value="/deleteCart", method=RequestMethod.GET)
	public @ResponseBody int deleteCart(int bookId, HttpServletRequest request) throws Exception{
		logger.debug("" + bookId);
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginId");
		int result = cartService.deleteCart(bookId, userId);
		return result;
	}
	
	
}
