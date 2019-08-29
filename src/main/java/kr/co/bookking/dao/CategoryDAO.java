package kr.co.bookking.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.CategoryVO;

@Repository
public class CategoryDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	/* bookId를 이용해서 책 정보 리턴*/
	public List<CategoryVO> getCategoryList() throws Exception{
		return template.selectList("category.selectCategoryList");
	}
}
