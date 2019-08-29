package kr.co.bookking.service;

import java.util.List;
import java.util.Map;

import kr.co.bookking.VO.OrderGoodsVO;
import kr.co.bookking.VO.OrderVO;

public interface OrderGoodsService {
	
	/* 새로운 orderGoods 정보생성 */
	public int createOrderGoods(OrderGoodsVO vo) throws Exception;
	
	/* order_id를 이용해서 해당 order의 상품 목록 반환*/
	public List<OrderGoodsVO> getOrderGoodsList(int orderId) throws Exception;
	
	/* order_id를 이용해서 해당 order의 상품 목록 반환*/
	public List<Map<String, String>> getGoodsList (int orderId) throws Exception;
}
