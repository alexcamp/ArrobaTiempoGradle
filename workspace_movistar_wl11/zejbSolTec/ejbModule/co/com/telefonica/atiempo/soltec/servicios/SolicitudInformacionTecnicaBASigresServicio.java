/*
 * Created on Jun 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author 804233
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudInformacionTecnicaBASigresServicio extends ServicioBasico {

	protected void procesar(Object[] obj) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			//		Mapeo variables
			 TR035S tr035s = (TR035S) obj[0];

			 // Invocar Servicio.
			 RecursosBAInterfaces recursos = new RecursosBADelegate();
			 recursos.recepcionConfiguracionActualBASigres(tr035s);
	
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
		 */
		protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			TR035S tr035s = (TR035S) XMLUtilities.unmarshall(mensaje);
			Object[] obj = new Object[1];
			obj[0] = tr035s;
		
			return obj;

		}


}
