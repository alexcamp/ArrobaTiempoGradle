/*
 * Created on 12-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudInformacionTecnicaBAServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		//		Mapeo variables
		 TR014S tr014s = (TR014S) obj[0];

		 // Invocar Servicio.
		 RecursosBAInterfaces recursos = new RecursosBADelegate();
		 recursos.recepcionConfiguracionActualBA(tr014s);
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		TR014S tr014s = (TR014S) XMLUtilities.unmarshall(mensaje);
		Object[] obj = new Object[1];
		obj[0] = tr014s;
		
		return obj;

	}

}
