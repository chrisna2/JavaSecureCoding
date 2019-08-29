package kr.co.bookking.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.CategoryVO;
import kr.co.bookking.service.BookListService;
import kr.co.bookking.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BookListService bookListService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// when click category list in homepage
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	public String getCategoryList(Locale locale, Model model, @PathVariable("categoryId") String categoryId) throws Exception {
		logger.info("################### categoryName: ", categoryId);
		
		List<CategoryVO> categoryList = categoryService.getCategoryList();
		
		model.addAttribute("pageType","bookList");
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("contentFile", "booklist/booklist.jsp");
		
		return "home";
	}
	
	//when click category list in booklist page
	@RequestMapping(value = "/booklist", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public @ResponseBody List<BookVO> getBookListByCategory(String categoryId) throws Exception{
		logger.info("################### categoryName: " + categoryId);
		List<BookVO> bookList = bookListService.getBookListByCategoryId(Integer.parseInt(categoryId));
		return bookList;
	}
}
