/*
 * Created on Apr 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.senders;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.interfaces.atiempo.TR601E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author guido
 *
 */
public class SenderTr601CambioNumero extends SenderTr601Base {

	private static Logger log = LoggerFactory.getLogger(SenderTr601CambioNumero.class);

	public void setParticularData(Object tr) throws ATiempoAppEx, NamingException, FinderException {
		TR601E tr601e = (TR601E) tr;
		tr601e.setPsCode(getPsCode());
		if (isReversa()) {// Segun  especificado en "Altamira Entendimiento.doc":
						 // "Se llama al mismo servicio intercambiando los parámetros de número origen y destino"
			tr601e.setPhoneNumber(getPhoneService());
			tr601e.setNewPhoneNumber(getPhoneServiceAnterior());
		} else {
			tr601e.setPhoneNumber(getPhoneServiceAnterior());
			tr601e.setNewPhoneNumber(getPhoneService());
		}
		tr601e.setCycle(getCicloFacturacion()); // ciclo facturacion + use type son para homolagacion de broker
		tr601e.setUseType(getUseType());
	}
	/**
	 * Devuelve true si el envio y por ende la actividad se inhiben,
	 * es decir que no se ejecuta la actividad.
	 */
	public boolean inhibirEnvio() throws NamingException, FinderException {
		// Si son iguales los telefonos no se ejecuta la actividad
		return equalCurrentPhoneAndOldPhone();
	}
	/** 
	 * Devuelve el mensaje por el cual se inhibe el envio. 
	 */
	public String getMensajeInhibicion() {
		return "Se inhibe la actividad. Los números de teléfono son iguales.";
	}

}
