package kr.co.bookking.service;

import java.util.List;

import kr.co.bookking.VO.CategoryVO;

public interface CategoryService {
	
	//메인 화면 진입시, 카테고리 목록 구성하기 위해
	public List<CategoryVO> getCategoryList() throws Exception;
}
