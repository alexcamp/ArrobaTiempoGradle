package com.telefonica_chile.bandeja.web.utiles.msgbroker;

public class MBCampo {
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	private int align = RIGHT;
	
	private String nombre;
	private int largo;
	private String nullChar;
	private String valor = "";
	
	public MBCampo(String nombre, int largo, String nullChar, int align, String valor) {
		this(nombre, largo, nullChar, align);
		this.valor = valor;
	}
	

	public MBCampo(String nombre, int largo, String nullChar, int align) {
		this.nombre = nombre;
		this.largo = largo;
		this.nullChar = nullChar;
		this.align = align;
		
	}
	
	public String getValue() {
		return valor;
	}
	
	public void setValue(String valor) {
		this.valor = trimmed(valor);
	}

	public int getLargo() {
		return largo;
	}
	
	public String getNullChar() {
		return nullChar;
	}
	
	public String getNombre() {
		return nombre;
	}
	

	public int getAlign() {
		return align;
	}

	public void getAlign(int align) {
		this.align = align;
	}

	public String filled() {
		return filled(valor);
	}
	
	// Rellena con 'nullChar' segun 'align' hasta completar 'largo'
	public String filled(String str) {
		String miValor = str;

		int largoValor = miValor.length();
		int iHasta     = largo - largoValor;
		if (align == LEFT) {
			for(int i=0; i < iHasta; i++)
				miValor = miValor + nullChar;
		} else {
			for(int i=0; i < iHasta; i++)
				miValor = nullChar + miValor;
		}	

		return miValor;
	}
	

	public String trimmed() {
		return trimmed(valor);
	}

	
	public String trimmed(String str) {

		String trim = "";
		if (align == LEFT) {
			trim = rightTrim(str, nullChar.toCharArray()[0]);
		} else {
			trim = leftTrim(str, nullChar.toCharArray()[0]);
		}

		if (trim.length() == 0)
			return nullChar;

		return trim;
	}


	public static String leftTrim(String str, char c) {
		int i = 0;
		int len = str.length();
		while (i < len && str.charAt(i) == c)
			i++;

		return str.substring(i, len);
	}

	public static String rightTrim(String str, char c) {
		int i = str.length() - 1;

		while (i >= 0 && str.charAt(i) == c)
			i--;

		if (i < 0)
			return "";

		return str.substring(0, i + 1);
	}


}

