package com.example.practicesbb.global;

import org.springframework.beans.factory.annotation.Value;

public class AppConfig {
	private static String activeProfile;

	@Value("${spring.profiles.active}")
	public void setActiveProfile(String value) {
		activeProfile = value;
	}

	public static boolean isNotProd() {
		return isProd() == false;
	}

	public static boolean isProd() {
		return activeProfile.equals("prod");
	}

	public static boolean isNotDev() {
		return isDev() == false;
	}

	public static boolean isDev() {
		return activeProfile.equals("dev");
	}

	public static boolean isNotTest() {
		return isDev() == false;
	}

	public static boolean isTest() {
		return activeProfile.equals("test");
	}

}