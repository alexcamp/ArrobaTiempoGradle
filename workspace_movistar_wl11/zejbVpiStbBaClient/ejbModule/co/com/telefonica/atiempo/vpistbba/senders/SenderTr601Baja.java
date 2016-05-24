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
 */
public class SenderTr601Baja extends SenderTr601Base {

	private static Logger log = LoggerFactory.getLogger(SenderTr601Baja.class);
	
	public void setParticularData(Object tr) throws ATiempoAppEx, NamingException, FinderException {
		TR601E tr601e = (TR601E) tr;
		tr601e.setPhoneNumber(getPhoneService());
		// Para las bajas de altamira el PS no va en la tr, 
		//aunque igual se necesita para crear el registro mensajeEstado
		//tr601e.setPsCode(getPsCode());
		//tr601e.setCycle(getCicloFacturacion()); // ciclo facturacion + use type son para homolagacion de broker
		//tr601e.setUseType(getUseType());
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.senders.SenderTr601Base#isTipoOpAcordePsPrimario(java.lang.String)
	 * Ver documentacion en el metodo de la clase base
	 */
	protected boolean isTipoOpAcordePsPrimario(String tipoOp) {
		if (tipoOp == null) {
			return false;
		}
		if (tipoOp.equals("B") || tipoOp.equals("BCP")) {
			return true;
		} else {
			return false;
		}
	}
}
