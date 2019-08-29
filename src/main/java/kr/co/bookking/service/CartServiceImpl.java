package kr.co.bookking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.CartVO;
import kr.co.bookking.dao.CartDAO;
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartDAO dao;
	
	//카트 리스트
	@Override
	public List<Map<String, String>> getCartList (String userId) throws Exception {
		return dao.getCartList (userId);
	}

	//카트에 물품추가
	@Override
	public int addCart(String userId, int stock, int bookId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("userId", userId);
		params.put("cartStock", stock);
		params.put("bookId", bookId);
		return dao.addCart(params);
	}

	//카트에 담긴 상품추가
	@Override
	public int isExistedCart(String userId, int bookId) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("bookId", bookId);
		return dao.isExistedCart(params);
	}

	//카트에 담긴 상품삭제
	@Override
	public int deleteCart(int bookId, String userId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId",userId);
		params.put("bookId",bookId);
		return dao.deleteCart(params);
	}
	
	//카트 상품 수량변경 update
	@Override
	public int updateCartList(CartVO params) throws Exception {
		return dao.updateCartList(params);
	}
	
	//결제완료시 장바구니에 담겨있는 상품리스트 삭제
	@Override
	public void deleteOrderCartList(String userId) throws Exception {
		dao.deleteOrderCartList(userId);
	}

	

}
