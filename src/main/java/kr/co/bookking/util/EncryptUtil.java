package kr.co.bookking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	public static String encryptPwd(String password) {
		String shaPwd = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] bs = md.digest();
			StringBuffer sb = new StringBuffer();
			
			for(int i =0; i<bs.length; i++) {
				sb.append(Integer.toString( (bs[i]&0xff)+0x100, 16).substring(1));
			}
			shaPwd = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shaPwd;
	}
	

}
