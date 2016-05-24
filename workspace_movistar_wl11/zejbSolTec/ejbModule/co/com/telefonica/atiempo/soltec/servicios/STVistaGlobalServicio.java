/*
 * Created on 28-11-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR025S;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author lcaldera
 *
 */
public class STVistaGlobalServicio extends ServicioBasico
{

	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR025S tr025s= (TR025S) obj[0];
		/*Llamado al Delegate indicado para procesar la respuesta*/
		EquipoSTLocal eDelegate = new EquipoSTDelegate();
        eDelegate.procesarRespuestaVistaGlobalST(tr025s);	
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR025S tr025s = (TR025S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr025s;
		return obj;
	}

}
