package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

public class ActualizacionInventarioTVServicio extends ServicioBasico
{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx
	{
		RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
		recursosTVDelegate.recibeActualizacionInventarioTV((TR018S) obj[0]);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR018S tr018s = (TR018S) XMLUtilities.unmarshall(mensaje);
        
		Object[] obj = new Object[1] ;
        
		obj [0] = tr018s ;
        
		return obj;
	}

}
