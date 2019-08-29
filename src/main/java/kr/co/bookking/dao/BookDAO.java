package kr.co.bookking.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.ProductCommentVO;

@Repository
public class BookDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	/* bookId를 이용해서 책 정보 리턴*/
	public BookVO searchBookById(int bookId) throws Exception{
		return template.selectOne("book.selectBook", bookId);
	}
	
	/* 상세페이지 방문 시 히트 수 증가 */
	public int plusCount(int bookId) throws Exception{
		return template.update("book.updateCount", bookId);
	}
	
	/* bookId를 이용해서 도서후기 리스트 리턴*/
	public List<ProductCommentVO> showComment(int bookId, int page) throws Exception{
		int reqPage = 5*(page-1);
		RowBounds rowBounds = new RowBounds(reqPage, 5);
		return template.selectList("book.selectCommentList", bookId, rowBounds);
	}
	
	/* 페이지네이션을 위한 도서후기 토탈카운트 리턴*/
	public int showTotalCount(int bookId) throws Exception{
		return template.selectOne("book.totalCount", bookId);
	}
	
	/* 카테고리id를 이용하여 */
	public List<BookVO> getBookListByCategoryId(int categoryId) throws Exception{
		return template.selectList("book.getBookListByCategoryId", categoryId);
	}
	
	/* 베스트셀러 리스트*/
	public List<BookVO> getBestBookList() throws Exception{
		return template.selectList("book.getBestBookList");
	}
}
