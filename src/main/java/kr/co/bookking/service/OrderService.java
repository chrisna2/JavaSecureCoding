package kr.co.bookking.service;

import java.util.List;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.OrderVO;

public interface OrderService {
	
	/* 새로운 order 생성 */
	public int createOrder(OrderVO vo) throws Exception;
	
	/* 상품결제 완료시 판매된 상품의 판매수량 update*/
	public int updateSellCount(BookVO vo) throws Exception;
	
	/* 사용자 오더 정보 가져오기 */
	public List<Integer> getOrderList(String userId) throws Exception;
}
