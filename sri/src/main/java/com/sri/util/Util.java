package com.sri.util;

/**
 * The utility for the project
 * */
public class Util {
	public static int randomNumber() {
		int n = 0;
		for (int i = 0; i < 8; i++) {
			n *= 10;
			n += (int)(Math.random() * 10);
		}
		return n;
	}
}

