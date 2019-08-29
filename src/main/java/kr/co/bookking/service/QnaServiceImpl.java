package kr.co.bookking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.QnaVO;
import kr.co.bookking.dao.QnaDAO;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	QnaDAO dao;
	
	@Override
	public List<QnaVO> getQnaListByBookId(int bookId) throws Exception{
		return dao.getQnaListByBookId(bookId);
	}
	
	@Override
	public String getQnaContentByQnaId(int qnaId) throws Exception{
		return dao.getQnaContentByQnaId(qnaId);
	}
	
	@Override
	public int insertQnaContent(QnaVO qnaVO) throws Exception {
		return dao.insertQnaContent(qnaVO);
	}	

}
