package kr.co.bookking.util;

public class EncapsulationTest {
	
	public static void main(String[] args) {
		Role role = Role.getInstance();
		System.out.println(role.list());
		
		String[] adminList = {"guest"};
		
		//public 한 getter setter 매서드를 둘경우  배열이나 컬랙션 은 주소를 전달하기 때문에 private 멤버일지라도 읽고 쓰는것이 가능하
		adminList = role.getRole();
		
		adminList[0] = "fakeGuest";
		System.out.println(role.list());
		
		System.out.println("실제 바꾼 값 : "+role.list(adminList));
		
		
	}
	
	
	
	

}
