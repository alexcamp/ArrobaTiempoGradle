package com.telefonica_chile.bandeja.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuDTO implements Serializable {
	
	private Long 	menuId;
	private String 	menuDescripcion = "";
	private String 	menuUrl = "";
	private Long 	menuIdPadre;
	private Long 	apId;
	private Integer menuOrden = new Integer(0);
	private ArrayList menuHijos = new ArrayList();
	
	public Long getApId() {
		return apId;
	}

	public String getMenuDescripcion() {
		return menuDescripcion;
	}

	public Long getMenuId() {
		return menuId;
	}

	public Long getMenuIdPadre() {
		return menuIdPadre;
	}

	public Integer getMenuOrden() {
		return menuOrden;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setApId(Long long1) {
		apId = long1;
	}

	public void setMenuDescripcion(String string) {
		menuDescripcion = string;
	}

	public void setMenuId(Long long1) {
		menuId = long1;
	}

	public void setMenuIdPadre(Long long1) {
		menuIdPadre = long1;
	}

	public void setMenuOrden(Integer integer) {
		menuOrden = integer;
	}

	public void setMenuUrl(String string) {
		menuUrl = string;
	}

	public ArrayList getMenuHijos() {
		return menuHijos;
	}
	
	public void agregaHijo(MenuDTO m) {
		menuHijos.add(m);
	}


	public String toString() {
		return new StringBuffer("[MENU:(")
			.append("id=").append(menuId)
			.append(", descripcion=").append(menuDescripcion)
			.append(", url=").append(menuUrl)
			.append(", idPadre=").append(menuIdPadre)
			.append(", idApp=").append(apId)
			.append(", orden=").append(menuOrden)
			.append(")]").toString();
	}
}
