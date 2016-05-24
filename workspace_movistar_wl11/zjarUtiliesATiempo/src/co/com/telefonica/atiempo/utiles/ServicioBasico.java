/*
 * Created on 26-feb-07
 */
package co.com.telefonica.atiempo.utiles;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * @author TCS
 */
public abstract class ServicioBasico implements IServicio {

	/**
	 * @see co.com.telefonica.atiempo.intf.IServicio#iniciar(java.lang.String)
	 */
	public void iniciar(String mensaje) throws ATiempoAppEx {
		Object[] obj = convertirMensaje(mensaje);
		procesar(obj);
	}



	protected abstract void procesar(Object[] obj) throws ATiempoAppEx;

	protected abstract Object[] convertirMensaje(String mensaje) throws ATiempoAppEx;

}
