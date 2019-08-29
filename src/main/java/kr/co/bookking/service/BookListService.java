package kr.co.bookking.service;

import java.util.List;

import kr.co.bookking.VO.BookVO;

public interface BookListService {
	public List<BookVO> getBookListByCategoryId(int categoryId) throws Exception; 
}
