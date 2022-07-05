package com.fundpage.test.util;

import java.util.Base64;

public class EncodePassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String encodedString = "SW5pdGlhbDAj";
		System.out.print(decodeString(encodedString)+'\n');
		
		String pwd = "Fundpage";
		System.out.print(encodeString(pwd)+'\n');
		

	}
	
	private static String decodeString(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	
	private static String encodeString(String originalInput) {
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		return encodedString;
	}

}
