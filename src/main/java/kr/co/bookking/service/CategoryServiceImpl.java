package kr.co.bookking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bookking.VO.CategoryVO;
import kr.co.bookking.dao.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO dao;
	
	@Override
	public List<CategoryVO> getCategoryList() throws Exception{
		return dao.getCategoryList();
	}

}
