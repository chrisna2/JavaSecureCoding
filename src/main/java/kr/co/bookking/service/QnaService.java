package kr.co.bookking.service;

import java.util.List;

import kr.co.bookking.VO.QnaVO;

public interface QnaService {
	
	//선택한 도서의 Q&A 글 목록을 가져온다.
	public List<QnaVO> getQnaListByBookId(int bookId) throws Exception;
	
	//선택한 도서의 Q&A 글 클릭시 질문 내용을 가져온다. 
	public String getQnaContentByQnaId(int qnaId) throws Exception;
	
	//선택한 도서의 Q&A를 작성하여 디비에 넣는다.
	public int insertQnaContent(QnaVO qnaVO) throws Exception;
}
