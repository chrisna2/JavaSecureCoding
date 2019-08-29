package kr.co.bookking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.OrderGoodsVO;
import kr.co.bookking.dao.OrderGoodsDAO;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

	@Autowired
	OrderGoodsDAO dao;
	
	@Override
	public int createOrderGoods(OrderGoodsVO vo) throws Exception {
		return dao.createOrderGoods(vo);
	}

	@Override
	public List<OrderGoodsVO> getOrderGoodsList(int orderId) throws Exception {
		return dao.getOrderGoodsList(orderId);
	}

	@Override
	public List<Map<String, String>> getGoodsList(int orderId) throws Exception {
		return dao.getGoodsList(orderId);
	}

}
