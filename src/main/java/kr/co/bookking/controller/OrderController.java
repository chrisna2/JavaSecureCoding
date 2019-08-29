package kr.co.bookking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.CartVO;
import kr.co.bookking.VO.OrderGoodsVO;
import kr.co.bookking.VO.OrderVO;
import kr.co.bookking.VO.UserVO;
import kr.co.bookking.service.BookService;
import kr.co.bookking.service.CartService;
import kr.co.bookking.service.OrderGoodsService;
import kr.co.bookking.service.OrderService;
import kr.co.bookking.service.UserService;
import kr.co.bookking.util.ArrayUtil;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderGoodsService orderGoodsService;
	
	
	@RequestMapping(value="/books")
	public String goCheckOut(Model model, int bookId, int cartStock, HttpServletRequest request) throws Exception {
		BookVO vo = bookService.searchBookById(bookId);
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("loginId");
		UserVO userVo = userService.getUserInfo(userId);
		model.addAttribute("userVo", userVo);
		model.addAttribute("cartStock", cartStock);
		model.addAttribute("book", vo);
		model.addAttribute("contentFile", "order/checkout.jsp");
		return "home";
	}
	
	@RequestMapping(value="/cart")
	public String goCheckOutCart(Model model, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("loginId");
		List<Map<String, String>> list = (List<Map<String,String>>)cartService.getCartList(userId);
		UserVO userVo = userService.getUserInfo(userId);
		model.addAttribute("userVo", userVo);
		model.addAttribute("cartList", list);
		model.addAttribute("contentFile", "order/checkout.jsp");
		return "home";
	}
	
	@RequestMapping(value="/order", produces = "application/json; charset=utf8", method = RequestMethod.POST)
	public String orderItems(Model model, @RequestBody String list, HttpServletRequest request) throws Exception{
		System.out.println(list.toString());
		
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginId");
		
		JSONArray obj = new JSONArray(list);
		JSONObject jsonobject = null;
		Map<String, String> map = new HashMap<String, String>();
		List<String> bookList = new ArrayList<String>();
		for (int i = 0; i < obj.length(); i++) {
		    jsonobject = obj.getJSONObject(i);
		    if(jsonobject.getString("name").equals("orderBookId")||jsonobject.getString("name").equals("orderStock")||jsonobject.getString("name").equals("orderPrice")){
		    	bookList.add(jsonobject.getString("value"));
		    }else{
		    	 map.put(jsonobject.getString("name"), jsonobject.getString("value"));
		    }
		}
		OrderVO vo = new OrderVO();
		vo.setOrderAddr1(map.get("orderAddr1"));
		vo.setOrderAddr2(map.get("orderAddr2"));
		vo.setOrderUserId(map.get("orderUserId"));
		vo.setOrderZip(Integer.parseInt(map.get("orderZip")));
		vo.setOrderEmail(map.get("orderEmail"));
		vo.setOrderConsingee(map.get("orderConsingee"));
		vo.setOrderCellphone1(Integer.parseInt(map.get("orderCellphone1")));
		vo.setOrderCellphone2(Integer.parseInt(map.get("orderCellphone2")));
		vo.setOrderCellphone3(Integer.parseInt(map.get("orderCellphone3")));
		vo.setOrderMemo(map.get("orderMemo"));
		vo.setOrderDeliveryCharge(Integer.parseInt(map.get("orderDeliveryCharge")));
		vo.setOrderTotalPrice(Integer.parseInt(map.get("orderTotalPrice")));
		vo.setOrderPaymentKind(map.get("orderPaymentKind"));
		System.out.println(vo.toString());
		orderService.createOrder(vo);
		ArrayUtil util = new ArrayUtil();
		List<List<String>> result = util.split(bookList, 3);
		OrderGoodsVO goodsVo = new OrderGoodsVO();
		//판매된 상품 수량 update를 위한 BookVo생성
		BookVO bookVo = new BookVO();
		int orderResult = 0;	 //결제 성공여부
		for (int i = 0; i < result.size(); i++) {
			goodsVo.setOrderGoodsId(Integer.parseInt(result.get(i).get(0)));
			goodsVo.setOrderGoodsStock(Integer.parseInt(result.get(i).get(1)));
			goodsVo.setOrderGoodsPrice(Integer.parseInt(result.get(i).get(2)));
			orderResult = orderGoodsService.createOrderGoods(goodsVo);
			
			//상품ID, 상품수량
			if(orderResult == 1){
			bookVo.setBookId(Integer.parseInt(result.get(i).get(0)));
			bookVo.setBookStock(Integer.parseInt(result.get(i).get(1)));
			orderService.updateSellCount(bookVo);
			}
		}
		
		//결제가 성공했을때 카트에담긴 상품 삭제
		if(orderResult == 1){
			cartService.deleteOrderCartList(userId);
		}
		
		return "home";
	}
	
	
	// 카트에 담긴 상품 수량 변경 update
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public @ResponseBody boolean updateCartList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> prams) throws Exception {

		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginId");
		
		//jsp에서 보낸 list를 map형식으로 받아 list에 담기
		List<CartVO> list = new ArrayList<CartVO>();
		for (Map<String, Object> m : prams) {
			String value = (String) m.get("value");
			CartVO cart = new CartVO();
			String[] result = value.split(",");
			cart.setBookId(Integer.parseInt(result[0]));
			cart.setCartStock(Integer.parseInt(result[1]));
			cart.setCartUserId(userId);
			list.add(cart);

		}
		
		//list에 담긴 값을 update시킴
		for (CartVO vo : list) {
			 cartService.updateCartList(vo);
		}
		return true;
	}
}
