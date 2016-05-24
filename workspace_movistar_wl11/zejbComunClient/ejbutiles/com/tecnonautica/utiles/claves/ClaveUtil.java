package com.tecnonautica.utiles.claves;

import java.util.Random;

public class ClaveUtil {
	public static final int LARGO_CLAVE = 8;

	private Random r = new Random();

	public String generaClave() {
		return generaClave(LARGO_CLAVE);
	}

	public String generaClave(int largo) {
		String[] clases = new String[] {
			"abcdefghijklmnopqrstuvwxyz",
			"1234567890",
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
		};
		StringBuffer clave = new StringBuffer();
		for (int i = 0; i < largo; i++) {
			String clase = clases[r.nextInt(clases.length)];
			char c = clase.charAt(r.nextInt(clase.length()));
			clave.append(c);
		}
		return clave.toString();
	}

	public static void main(String[] args) {
		ClaveUtil c = new ClaveUtil();
		for (int i = 0; i < 10; i++)
			System.out.println(c.generaClave());
	}
}
