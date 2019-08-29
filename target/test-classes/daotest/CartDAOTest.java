package kr.co.bookking.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartDAOTest {
	
	@Autowired
	CartDAO dao;
	
	/*@Test
	public void cartListTest(){
		assertEquals("test1", dao.cartList("test1").get(0).get("CART_USER_ID"));
	}
*/
}
