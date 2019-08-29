package kr.co.bookking.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.bookking.dao.CartDAO;

@Component
public class CartScheduler {
	@Autowired
	CartDAO dao;
	
	/**
	 * 30일이 경과된 상품 자동삭제
	 * 오전 9시 35분에 메소드 호출 됨
	 */
	@Scheduled(cron="0 35 09 * * *")
	public void dayDelete() throws Exception {
		System.out.println("오전 9시 35분에 메소드 호출 ");
		dao.deleteCartTimeout();
	}
}
