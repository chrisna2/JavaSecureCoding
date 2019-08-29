package kr.co.bookking.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.bookking.VO.UserVO;

public interface UserService {
	
	/*회원가입*/
	public int signup(UserVO user) throws Exception ;
	
	/*아이디 중복체크*/
	public String isDuplication(String userId) throws Exception;
	
	/*로그인*/
	public String login(String userId, String userPw) throws Exception;
	
	/* 사용자 정보 가져오기*/
	public UserVO getUserInfo(String userId)throws Exception;

	/* 사용자 정보 수정하기 */
	public int changeUserInfo(UserVO userVO) throws Exception;
	
	/* 사용자 비밀번호 수정하기 */
	public int changeUserPassword(Map<String, Object> params) throws Exception;
	
	/* 사용자 비밀번호 가져오기 */
	public String getUserPassword(String userId) throws Exception;
}
