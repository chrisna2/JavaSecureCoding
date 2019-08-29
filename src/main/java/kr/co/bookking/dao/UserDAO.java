package kr.co.bookking.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookking.VO.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	/* 사용자 회원가입 */
	public int signup(UserVO user) throws Exception{
	return template.insert("user.signup", user);
	}
	
	/*아이디 중복검사*/
	public String isDuplication(String userId) throws Exception{
		return template.selectOne("user.isDuplication", userId);
	}
	
	/* 로그인 */
	public String login(Map<String, String> params) throws Exception{
		return template.selectOne("user.login", params);
	}
	
	/* 사용자 정보 가져오기*/
	public UserVO getUserInfo(String userId)throws Exception{
		return template.selectOne("user.getUserInfo", userId);
	}
	
	/* 사용자 정보 수정하기 */
	public int changeUserInfo(UserVO userVO) throws Exception{
		return template.update("user.changeUserInfo", userVO);
	}
	
	/* 사용자 비밀번호 수정하기 */
	public int changeUserPassword(Map<String, Object> params) throws Exception{
		return template.update("user.changeUserPassword", params);
	}
	
	/* 사용자 비밀번호 가져오기 */
	public String getUserPassword(String userId) throws Exception{
		return template.selectOne("user.getUserPassword", userId);
	}
}
