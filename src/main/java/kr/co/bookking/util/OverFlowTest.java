package kr.co.bookking.util;

public class OverFlowTest {
	
	public static void main(String[] args) {
		int amount = 500;
		System.out.println(amount);
		
		amount = Integer.MAX_VALUE;

		System.out.println(Integer.toBinaryString(amount));
		System.out.println(amount);
		
		amount = amount + 1;
		
		//오버 플로우 발생
		System.out.println(Integer.toBinaryString(amount));
		System.out.println(amount);
		
		amount = Integer.MIN_VALUE;
		
		System.out.println(Integer.toBinaryString(amount));
		System.out.println(amount);
				
		amount = -1;

		System.out.println(Integer.toBinaryString(amount));
		System.out.println(amount);
		
		/* 이진수 맨 끝에 수는 +/- 부호를 경정하는 숫자 자리이다. */
	}

}
