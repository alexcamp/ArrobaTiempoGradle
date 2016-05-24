/*
 * Created on 
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author mfmendez
 *
 */
public class STNotificacionSAPServicio extends ServicioBasico
{

	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR020S tr020s= (TR020S) obj[0];
		/*Llamado al Delegate indicado para procesar la respuesta*/
		EquipoSTLocal eDelegate = new EquipoSTDelegate();
        eDelegate.procesarRespuestaNotificacionVentaMinoristasSAPST(tr020s);	
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR020S tr020s = (TR020S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr020s;
		return obj;
	}

}
