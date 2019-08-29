package kr.co.bookking.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookDAOTest {
	
	
	@Autowired
	BookDAO dao;
	
	/*
	@Test
	public void testSearchBookById() {
		assertEquals("허균", dao.searchBookById(3).getBookWriter());
		assertEquals("홍길동전", dao.searchBookById(3).getBookName());
	}
	
	@Test
	public void testPlusCount(){
		dao.plusCount(3);
		assertEquals(3, dao.searchBookById(3).getBookHits());
	}

	*/
	@Test
	public void testShowComment(){
		//assertEquals(4, dao.showComment(4).get(0).getpCommentBookId());
		//assertEquals("test1", dao.showComment(4,1).get(0).getpCommentUserId());
		//assertEquals(4, dao.showComment(4,1).get(2).getpCommentPoint());
		assertEquals(5, dao.showComment(4,2).size());
	}
	
	/*
	@Test
	public void testTotalCount(){
		assertEquals(3, dao.totalCount(4));
	}
	*/
		
}
