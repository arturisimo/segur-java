package com.sgj.web.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Util {
	
	private static final int logRounds = 10;

	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	}
	
	
}
