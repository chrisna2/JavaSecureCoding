package kr.co.bookking.service;

import java.util.List;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.ProductCommentVO;

public interface BookService {

	/* bookId를 이용해서 책 정보 리턴*/
	public BookVO searchBookById(int bookId) throws Exception;
	
	/* 상세페이지 방문 시 히트 수 증가 */
	public int plusCount(int bookId) throws Exception;
	
	/* bookId를 이용해서 도서후기 리스트 리턴*/
	public List<ProductCommentVO> showComment(int bookId, int page) throws Exception;
	
	/* 페이지네이션을 위한 도서후기 토탈카운트 리턴*/
	public int showTotalCount(int bookId) throws Exception;
	
	// 베스트 셀러 리스트
	public List<BookVO> getBestBookList() throws Exception;
}
