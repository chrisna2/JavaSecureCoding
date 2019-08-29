package kr.co.bookking.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class OrderGoodsDAOTest {

	@Autowired
	OrderGoodsDAO dao;
	
	@Test
	public void testGetOrderGoodsList() {
		System.out.println(dao.getOrderGoodsList(1).toString());
		assertEquals(3, dao.getOrderGoodsList(1).size());
		assertEquals(14000, dao.getOrderGoodsList(1).get(0).getOrderGoodsPrice());
	}

}
