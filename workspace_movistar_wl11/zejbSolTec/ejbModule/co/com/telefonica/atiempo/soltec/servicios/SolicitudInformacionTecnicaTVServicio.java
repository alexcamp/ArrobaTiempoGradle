/*
 * Created on 12-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SolicitudInformacionTecnicaTVServicio extends ServicioBasico {
	
	/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
		 */
		protected void procesar(Object[] obj) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			//		Mapeo variables
			 TR019S tr019s = (TR019S) obj[0];

			 // Invocar Servicio.
			 RecursosTVInterfaces recursos = new RecursosTVDelegate();
			recursos.recepcionInformacionTecnica(tr019s);
		 
	
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
		 */
		protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
			// TODO Auto-generated method stub
		
			TR019S tr019s = (TR019S) XMLUtilities.unmarshall(mensaje);
			Object[] obj = new Object[1];
			obj[0] = tr019s;
		
			return obj;

		}

}
