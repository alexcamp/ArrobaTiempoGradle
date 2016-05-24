/*
 * Created on Jun 01, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.interfaces.atiempo.TR611S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.MDServicioBean;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author mfmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfirmacionPagoReciboServicio extends ServicioBasico {
	protected String correlationID;
	private static Logger logger=Logger.getLogger(ConfirmacionPagoReciboServicio.class);
	
	ConfirmacionPagoReciboServicio(String correlationID){
		super();
		//logger.debug("El correlativo en ConfirmacionPagoReciboServicio es:"+correlationID);
		this.correlationID = correlationID;
	}
	
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR611S tr611s= (TR611S) obj[0];
        EquipoDelegate eDelegate = new EquipoDelegate();
        eDelegate.recibirConfirmacionPagosTVSola(tr611s, this.correlationID);	
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR611S tr611s = (TR611S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr611s;
		return obj;
	}
}
