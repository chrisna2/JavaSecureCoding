package kr.co.bookking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.OrderVO;
import kr.co.bookking.dao.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO dao;
	
	@Override
	public int createOrder(OrderVO vo) throws Exception {
		return dao.createOrder(vo);
	}
	
	/* 상품결제 완료시 판매된 상품의 판매수량 update*/
	@Override
	public int updateSellCount(BookVO vo) throws Exception {
		return dao.updateSellCount(vo);
	}

	@Override
	public List<Integer> getOrderList(String userId) throws Exception {
		return dao.getOrderList(userId);
	}

}
