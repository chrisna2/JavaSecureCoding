package kr.co.bookking.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.CategoryVO;
import kr.co.bookking.service.BookService;
import kr.co.bookking.service.CategoryServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	CategoryServiceImpl service; 
	
	@Autowired
	BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<CategoryVO> categoryList = service.getCategoryList();
		List<BookVO> bestBookList = bookService.getBestBookList();
		logger.debug(bestBookList.toString());
		logger.debug(bestBookList.size()+"");
		
		model.addAttribute("pageType","index");
		model.addAttribute("categoryId", 0);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bestList",bestBookList);
		model.addAttribute("contentFile", "index.jsp");
		
		return "home";
	}
	
//	category_classic.do
}
