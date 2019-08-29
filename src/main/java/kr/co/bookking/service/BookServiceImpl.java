package kr.co.bookking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.ProductCommentVO;
import kr.co.bookking.dao.BookDAO;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO dao;
	
	/* bookId를 이용해서 책 정보 리턴*/
	@Override
	public BookVO searchBookById(int bookId) throws Exception {
		return dao.searchBookById(bookId);
	}

	/* 상세페이지 방문 시 히트 수 증가 */
	@Override
	public int plusCount(int bookId) throws Exception {
		return dao.plusCount(bookId);
	}

	/* 상세페이지 방문 시 히트 수 증가 */
	@Override
	public List<ProductCommentVO> showComment(int bookId, int page) throws Exception {
		return dao.showComment(bookId, page);
	}

	/* 페이지네이션을 위한 도서후기 토탈카운트 리턴*/
	@Override
	public int showTotalCount(int bookId) throws Exception {
		return dao.showTotalCount(bookId);
	}

	@Override
	public List<BookVO> getBestBookList() throws Exception {
		return dao.getBestBookList();
	}

}
