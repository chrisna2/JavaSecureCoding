package kr.co.bookking.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.CartVO;

@Repository
public class CartDAO {
	@Autowired
	SqlSessionTemplate template;
	
	//카드 목록
	public List<Map<String, String>> getCartList (String userId) throws Exception{
		
		return template.selectList("cart.getCartList", userId);
	}
	
	//카트에 상품 등록
	public int addCart(Map<String, Object> params) throws Exception{
		return template.insert("cart.addCart", params);
	}
	
	//담겨있는 상품확인
	public int isExistedCart(Map<String, Object> params) throws Exception{
		return template.selectOne("cart.isExistedCart", params);
	}
	
	//상품삭제
	public int deleteCart(Map<String, Object> params){
		return template.delete("cart.deleteCart", params);
	}
	//30일 지난 상품삭제
	public int deleteCartTimeout(){
		return template.delete("cart.deleteCartTimeout");
	}
	
	//카트에 담긴 상품 수량 변경 update
	public int updateCartList(CartVO params){
		return template.update("cart.updateCartList", params);
	}
	
	//결제완료시 장바구니에 담겨있는 상품리스트 삭제
	public void deleteOrderCartList(String userId){
		template.delete("cart.deleteOrderCartList", userId);
	}
}
