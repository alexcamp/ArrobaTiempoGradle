package com.tecnonautica.utiles.web;

import javax.servlet.http.HttpServletRequest;

public class UtilRequest {
	private HttpServletRequest request;

	public UtilRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getParameter(String name) {
		return getParameter(name, "");
	}
	
	public String getParameter(String name, String def) {
		String val = request.getParameter(name);
		if (val == null)
			val = def;
		return val;
	}
}
