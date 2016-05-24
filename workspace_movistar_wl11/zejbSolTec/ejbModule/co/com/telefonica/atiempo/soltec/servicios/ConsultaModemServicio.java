/*
 * Created on 28-11-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR022S;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author lcaldera
 *
 */
public class ConsultaModemServicio extends ServicioBasico
{

	protected void procesar(Object[] obj) throws ATiempoAppEx
	{
		TR022S tr022s = (TR022S) obj[0];
		
		RecursosBAInterfaces recursosBAInterfaces  = new RecursosBADelegate();
		recursosBAInterfaces.actualizaModemPorUtilizar(tr022s);
		
	}

	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx
	{
		Object[] obj = new Object[1];
		TR022S tr022s = (TR022S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr022s;
		return obj;
	}

}
