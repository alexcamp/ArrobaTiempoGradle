/*
 * Created on Feb 28, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TRSMSEDatos extends AgendaServiceHeader {
	
	private String numero;
	private String texto;
	
	public int hashCode(){
		return numero.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TRSMSEDatos){
			TRSMSEDatos other = (TRSMSEDatos) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(numero,other.numero)
				&& EqualityUtilities.equals(texto,other.texto);
		}else{
			return false;
		}
	}
	
	/**
	 * @return Returns the numero.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return Returns the texto.
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto The texto to set.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
