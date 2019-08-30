package kr.co.bookking.controller;


import java.util.ArrayList;
import java.util.HashMap;
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

import kr.co.bookking.VO.CategoryVO;
import kr.co.bookking.VO.UserVO;
import kr.co.bookking.service.CategoryService;
import kr.co.bookking.service.OrderGoodsService;
import kr.co.bookking.service.OrderService;
import kr.co.bookking.service.UserService;
import kr.co.bookking.util.EncryptUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderGoodsService orderGoodsService;
	
	//loginView
	@RequestMapping(value="/userLogin", method =RequestMethod.GET)
	public String goLoginView(Model model) throws Exception {
		model.addAttribute("contentFile", "login/login.jsp");
		return "home";
	}
	
	//로그인
		@RequestMapping(value="/loginAction", method=RequestMethod.GET)
		public @ResponseBody String login(HttpServletResponse response, HttpServletRequest request, Model model, String loginId, String loginPw) throws Exception{
			String result = null;
			try {
				
				String EncodePw = EncryptUtil.encryptPwd(loginPw);
				
				result = userService.login(loginId, EncodePw);
				System.out.println(result);
				if(result != null){
					HttpSession session = request.getSession();
					session.setAttribute("loginId", result);
				}
				return "{\"loginId\":\""+result+"\"}";
			} catch (Exception e) {
				System.out.println("XXXXXXXXXXXXXXXXXXX");
				System.out.println(e.getMessage());
				return "{"+"\"msg\":\""+ e.getMessage()+ "\"}";
			}
		}
		
		//로그아웃
		@RequestMapping(value="/logout")
		public String logout(HttpServletRequest request, Model model) throws Exception{
			
			HttpSession session = request.getSession(false);
			session.invalidate();
			List<CategoryVO> categoryList = categoryService.getCategoryList();
			
			model.addAttribute("pageType","index");
			model.addAttribute("categoryId", 0);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("contentFile", "index.jsp");
			return "redirect:/";
		}
		

		
		/*회원가입*/
		@RequestMapping(value="/userSignup", method =RequestMethod.GET)
		public @ResponseBody int signup(Model model, UserVO user, HttpServletResponse response, HttpServletRequest request) throws Exception{
			
			System.out.println(user.toString());
			String ecrypt = EncryptUtil.encryptPwd(user.getUserPassword());
			System.out.println(ecrypt);
			user.setUserPassword(ecrypt);
			
			
			int result = userService.signup(user);
			return result;
		}
		
		/*아이디 중복확인*/
		@RequestMapping(value="/userDuplication")
		@ResponseBody
		public String isDuplication(String userId, Model model) throws Exception{
			String duplicationId = userService.isDuplication(userId);
			return "{\"id\":\""+duplicationId+"\"}";
		}
		
	
	/* 마이페이지 */
	@RequestMapping(value="/mypage")
	public String enterMyPage(Model model, String userId, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(false);
		model.addAttribute("userId",(String)session.getAttribute("loginId"));
		model.addAttribute("contentFile", "mypage/mypage.jsp");
		
		return "home";
	}
	
	/* 사용자 정보를 가져온다. */
	@RequestMapping(value="/getUserInfo", produces = "application/json; charset=utf8")
	public @ResponseBody UserVO getUserInfo(String userId) throws Exception {
		UserVO userVO = userService.getUserInfo(userId);
		System.out.println(userVO);
		
		return userVO;
	}
	
	/* 사용자 정보를 수정한다. */
	@RequestMapping(value="/changeUserInfo", produces = "application/json; charset=utf8")
	public @ResponseBody String changeUserInfo(UserVO userVO) throws Exception{
		System.out.println(userVO);
		
		return String.valueOf(userService.changeUserInfo(userVO));
	}
	
	/* 사용자 비밀번호 수정 팝업 */
	@RequestMapping(value="/popupChangeUserPassword", produces = "application/json; charset=utf8")
	public String popupChangeUserPassword(String userId, Model model) throws Exception{
		System.out.println(userId);
		model.addAttribute("userId", userId);
		
		return "/mypage/changePassword";
	}
	
	/* 사용자 비밀번호를 수정한다. */
	@RequestMapping(value="/changeUserPassword", produces = "application/json; charset=utf8")
	public @ResponseBody String changeUserPassword(String userId, String afterPW, String checkAfterPW) throws Exception{
		System.out.println(userId +" "+" "+afterPW+" "+checkAfterPW);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("afterPW", afterPW);
		params.put("checkAfterPW", checkAfterPW);
		
		return String.valueOf(userService.changeUserPassword(params));
	}
	
	/* 사용자 기존 비밀번호와 일치하는지 확인한다. */
	@RequestMapping(value="/getUserPassword", produces = "application/text; charset=utf8")
	public @ResponseBody String getUserPassword(String userId) throws Exception{
		System.out.println(userId);
		
		return userService.getUserPassword(userId);
	}
	
	/* 주문조회 페이지를 불러온다.*/
	@RequestMapping(value="/orderList")
	public String goOrderListPage(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("loginId");
		List<Integer> orderList = orderService.getOrderList(userId);
		List<Map<String, String>> orderGoodsList = new ArrayList<Map<String,String>>();
		for (int i = 0; i < orderList.size(); i++) {
			List<Map<String, String>> result = orderGoodsService.getGoodsList(orderList.get(i));
			for (int j = 0; j < result.size(); j++) {
				orderGoodsList.add(result.get(j));
			}
		}
		System.out.println(orderGoodsList);
		model.addAttribute("orderGoodsList", orderGoodsList);
		model.addAttribute("contentFile", "mypage/orderList.jsp");
		return "home";
	}

}
