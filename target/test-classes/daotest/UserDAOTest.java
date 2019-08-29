package kr.co.bookking.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserDAOTest {

	@Autowired
	UserDAO dao;
	
	/*
	@Test
	public void testSignup() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDuplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetUserInfo() {
		assertEquals("아이유", dao.getUserInfo("iu").getUserName());
		assertEquals(2222, dao.getUserInfo("iu").getUserCellphone2());
	}

}
