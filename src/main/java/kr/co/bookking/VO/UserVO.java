package kr.co.bookking.VO;

import javax.xml.bind.annotation.XmlRootElement;

public class UserVO {
	String userId, userPassword, userName, userAddr1, userEmail, userBirth, userAddr2;
	int userCellphone1, userCellphone2, userCellphone3, userSex, userMileage,userZip;
	
	public UserVO(){}
	
	

	public UserVO(String userId, String userPassword, String userName, String userAddr1, String userEmail,
			String userBirth, String userAddr2, int userCellphone1, int userCellphone2, int userCellphone3, int userSex,
			int userMileage, int userZip) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddr1 = userAddr1;
		this.userEmail = userEmail;
		this.userBirth = userBirth;
		this.userAddr2 = userAddr2;
		this.userCellphone1 = userCellphone1;
		this.userCellphone2 = userCellphone2;
		this.userCellphone3 = userCellphone3;
		this.userSex = userSex;
		this.userMileage = userMileage;
		this.userZip = userZip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddr1() {
		return userAddr1;
	}

	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserAddr2() {
		return userAddr2;
	}

	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}

	public int getUserCellphone1() {
		return userCellphone1;
	}

	public void setUserCellphone1(int userCellphone1) {
		this.userCellphone1 = userCellphone1;
	}

	public int getUserCellphone2() {
		return userCellphone2;
	}

	public void setUserCellphone2(int userCellphone2) {
		this.userCellphone2 = userCellphone2;
	}

	public int getUserCellphone3() {
		return userCellphone3;
	}

	public void setUserCellphone3(int userCellphone3) {
		this.userCellphone3 = userCellphone3;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public int getUserMileage() {
		return userMileage;
	}

	public void setUserMileage(int userMileage) {
		this.userMileage = userMileage;
	}

	public int getUserZip() {
		return userZip;
	}

	public void setUserZip(int userZip) {
		this.userZip = userZip;
	}

	@Override
	public String toString() {
		return "UserVO [아이디=" + userId + ", PW=" + userPassword + ", 이름=" + userName + ", 주소="
				+ userAddr1 +", 상세주소=" + userAddr2 +", 우편번호=" + userZip + ", 이메일=" + userEmail + ", 생일=" + userBirth + ", 휴대전화1="
				+ userCellphone1 + ", 휴대전화2=" + userCellphone2 + ", 휴대전화3=" + userCellphone3
				+ ", 성별=" + userSex + ", 마일리지=" + userMileage + "]";
	}


	
}
