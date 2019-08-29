package kr.co.bookking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.dao.BookDAO;

@Service
public class BookListServiceImpl implements BookListService{
	
	@Autowired
	BookDAO dao;
	
	@Override
	public List<BookVO> getBookListByCategoryId(int categoryId) throws Exception{
		return dao.getBookListByCategoryId(categoryId);
	}
}
