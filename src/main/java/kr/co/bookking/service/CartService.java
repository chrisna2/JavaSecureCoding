package kr.co.bookking.service;

import java.util.List;
import java.util.Map;

import kr.co.bookking.VO.CartVO;

public interface CartService {
	
	//cart 리스트
	public List<Map<String, String>>getCartList (String userId) throws Exception;
	
	//cart 상품 등록
	public int addCart(String userId, int stock, int bookId) throws Exception;
	
	//카트에 담긴 상품 확인
	public int isExistedCart(String userId, int bookId) throws Exception;
	
	//상품 삭제
	public int deleteCart(int bookId, String userId) throws Exception;
	
	//카드 수량 변경 update
	public int updateCartList(CartVO params) throws Exception;
	
	//결제완료시 장바구니에 담겨있는 상품리스트 삭제
	public void deleteOrderCartList(String userId) throws Exception;
}
