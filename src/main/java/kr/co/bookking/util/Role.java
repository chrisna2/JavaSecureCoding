package kr.co.bookking.util;

import java.util.Arrays;

public class Role {
	
	private String[] roles = {"admin","user","manager"};
	
	private static Role role;
	
	public static Role getInstance(){
		
		if (role==null) {
			role = new Role();
		}
		return role;
	}

	public String[] getRole() {
		//이렇게 하면 메모리 주소를 리턴하게 된다.
		//return roles;
		
		//따라서 주소를 반환하는 것이 아닌 값을 복사해서 반환하도록 하게 한다.
		return roles.clone();
		
	}

	public void setRole(Role role) {
		Role.role = role;
	}
	
	public String list() {
		
		String result = "";
		
		for(String a : roles) {
			result += "|"+a;
		}
		return result;
	}
	public String list(String[] p_roles) {
		
		String result = "";
		
		for(String a : p_roles) {
			result += "|"+a;
		}
		return result;
	}
	
}
