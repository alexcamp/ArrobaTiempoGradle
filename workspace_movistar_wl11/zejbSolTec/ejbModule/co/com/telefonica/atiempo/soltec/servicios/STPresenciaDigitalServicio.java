/*
 * Created on 
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author mfmendez
 *
 */
public class STPresenciaDigitalServicio extends ServicioBasico
{

	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR054S tr054s= (TR054S) obj[0];
		/*Llamado al Delegate indicado para procesar la respuesta*/
		RecursosDelegate eDelegate = new RecursosDelegate();
        eDelegate.procesarRespuestaConfiguracionPresenciaDigital(tr054s);	
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR054S tr054s = (TR054S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr054s;
		return obj;
	}

}
