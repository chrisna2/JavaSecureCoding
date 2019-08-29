package kr.co.bookking.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.QnaVO;

@Repository
public class QnaDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	public List<QnaVO> getQnaListByBookId(int bookId) throws Exception{
		return template.selectList("qna.getQnaListByBookId",bookId);
	}
	
	public String getQnaContentByQnaId(int qnaId) throws Exception{
		return template.selectOne("qna.getQnaContentByQnaId",qnaId);
	}
	
	public int insertQnaContent(QnaVO qnaVO) throws Exception{
		return template.insert("qna.insertQnaContent",qnaVO);
	}
}
