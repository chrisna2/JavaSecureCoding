package kr.co.bookking.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.OrderGoodsVO;

@Repository
public class OrderGoodsDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	/* 새로운 orderGoods 정보생성 */
	public int createOrderGoods(OrderGoodsVO vo) throws Exception{
		return template.insert("orderGoods.createOrder", vo);
	}
	
	/* order_id를 이용해서 해당 order의 상품 목록 반환*/
	public List<OrderGoodsVO> getOrderGoodsList(int orderId) throws Exception{
		return template.selectList("orderGoods.getOrderGoodsList", orderId);
	}
	
	/* order_id를 이용해서 해당 order의 상품 목록 반환*/
	public List<Map<String, String>> getGoodsList (int orderId) throws Exception{
		return template.selectList("orderGoods.getGoodsList", orderId);
	}
}
