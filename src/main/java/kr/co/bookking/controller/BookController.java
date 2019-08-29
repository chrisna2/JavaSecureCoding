package kr.co.bookking.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bookking.VO.BookVO;
import kr.co.bookking.VO.CategoryVO;
import kr.co.bookking.VO.ProductCommentVO;
import kr.co.bookking.VO.QnaVO;
import kr.co.bookking.service.BookService;
import kr.co.bookking.service.CategoryService;
import kr.co.bookking.service.QnaService;
import kr.co.bookking.util.Pagination;

@Controller
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BookService service;
	
	@Autowired
	QnaService qnaService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	/* 상세페이지 컨트롤러*/
	@RequestMapping(value="/{bookId}")
	public String showBookDetail (Model model, @PathVariable("bookId") int bookId, String page, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("loginId");
		BookVO book = service.searchBookById(bookId);
		service.plusCount(bookId);
		int totalCount = service.showTotalCount(bookId);
		List<ProductCommentVO> list = null;
		Pagination pagination = null;
		if(page==null){
			list = service.showComment(bookId, 1);
			pagination = new Pagination(5, 5, totalCount, 1);
		}else{
			list = service.showComment(bookId, Integer.parseInt(page));
			pagination = new Pagination(5, 5, totalCount,  Integer.parseInt(page));
		}
		pagination.paginate();
		model.addAttribute("userId", userId);
		model.addAttribute("total", totalCount);
		model.addAttribute("list", list);
		model.addAttribute("book", book);
		model.addAttribute("pagenate", pagination.toHtml("",""));
		model.addAttribute(request.getSession(false));
		//Q&A
		List<QnaVO> qnaList = qnaService.getQnaListByBookId(bookId);
		model.addAttribute("qnaList",qnaList);
		//카테고리
		List<CategoryVO> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		
		model.addAttribute("contentFile", "detail/detail.jsp");
		return "home";
	}
	
	/* Ajax paging */
	@RequestMapping(value="/pagenate/{bookId}", produces = "application/text; charset=utf8", method = RequestMethod.GET)
	public @ResponseBody String paginateCmt (Model model, @PathVariable("bookId") int bookId, String page) throws Exception {
		int totalCount = service.showTotalCount(bookId);
		Pagination pagination = new Pagination(5, 5, totalCount,  Integer.parseInt(page));
		pagination.paginate();
		String pagenate = pagination.toHtml("","");
		return pagenate;
	}
	
	/* Ajax comment List  */
	@RequestMapping(value="/cmtList/{bookId}", method = RequestMethod.GET)
	public @ResponseBody List<ProductCommentVO> showCmtList (Model model, @PathVariable("bookId") int bookId, String page) throws Exception {
		List<ProductCommentVO> list = service.showComment(bookId, Integer.parseInt(page));
		return list;
	}
	
}
