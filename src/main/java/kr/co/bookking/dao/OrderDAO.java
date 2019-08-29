package kr.co.bookking.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.OrderVO;

@Repository
public class OrderDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	/* 새로운 order 생성 */
	public int createOrder(OrderVO vo) throws Exception{
		return template.insert("order.createOrder", vo);
	}
	
	/* 상품결제 완료시 판매된 상품의 판매수량 update*/
	public int updateSellCount(BookVO vo) throws Exception{
		return template.update("order.updateSellCount", vo);
	}
	
	/* 사용자 오더 정보 가져오기 */
	public List<Integer> getOrderList(String userId) throws Exception{
		return template.selectList("order.getOrderList", userId);
	}
	
}
