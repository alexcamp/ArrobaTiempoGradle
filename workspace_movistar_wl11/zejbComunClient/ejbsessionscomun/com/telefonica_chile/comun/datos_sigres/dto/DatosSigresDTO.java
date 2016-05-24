/*
 * Created on 02-nov-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.comun.datos_sigres.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosSigresDTO implements Serializable {

	private String horizontalMDF;
	private String verticalMDF;
	private String posicionMDF;
	private String modemGatewayIp;
	private String modemWanIp;
	private ArrayList listaIp;
	private ArrayList listaBitacora;
	
	
	public DatosSigresDTO(){
		this.horizontalMDF="";
		this.verticalMDF="";
		this.posicionMDF="";
		this.modemGatewayIp="";
		this.modemWanIp="";
		this.listaIp=new ArrayList();
		this.listaBitacora=new ArrayList();
	}
	
	public void addIp(String ipSigres){
		this.listaIp.add(ipSigres);
	}
	
	public void addBitacora(BitacoraSigresDTO bitSigres){
		this.listaBitacora.add(bitSigres);
	}

	/**
	 * @return
	 */
	public String getHorizontalMDF() {
		return horizontalMDF;
	}

	/**
	 * @return
	 */
	public String getModemGatewayIp() {
		return modemGatewayIp;
	}

	/**
	 * @return
	 */
	public String getModemWanIp() {
		return modemWanIp;
	}

	/**
	 * @return
	 */
	public String getPosicionMDF() {
		return posicionMDF;
	}

	/**
	 * @return
	 */
	public String getVerticalMDF() {
		return verticalMDF;
	}

	/**
	 * @param string
	 */
	public void setHorizontalMDF(String string) {
		horizontalMDF = string;
	}

	/**
	 * @param string
	 */
	public void setModemGatewayIp(String string) {
		modemGatewayIp = string;
	}

	/**
	 * @param string
	 */
	public void setModemWanIp(String string) {
		modemWanIp = string;
	}

	/**
	 * @param string
	 */
	public void setPosicionMDF(String string) {
		posicionMDF = string;
	}

	/**
	 * @param string
	 */
	public void setVerticalMDF(String string) {
		verticalMDF = string;
	}

	/**
	 * @return
	 */
	public ArrayList getListaIp() {
		return listaIp;
	}

	/**
	 * @param list
	 */
	public void setListaIp(ArrayList list) {
		listaIp = list;
	}

	/**
	 * @return
	 */
	public ArrayList getListaBitacora() {
		return listaBitacora;
	}

	/**
	 * @param list
	 */
	public void setListaBitacora(ArrayList list) {
		listaBitacora = list;
	}

}
