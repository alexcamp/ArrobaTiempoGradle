/*
 * Creado el 28/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

import co.com.telefonica.atiempo.ejb.eb.ACommunicatorWSBeanLocal;
import co.com.telefonica.atiempo.ejb.eb.ACommunicatorWSBeanLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
;


/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CommunicatorWSDelegate implements CommunicatorWSInterfaces{

	private ACommunicatorWSBeanLocal servicios;

	public CommunicatorWSDelegate() throws ATiempoAppEx {
		try {
			servicios = ((ACommunicatorWSBeanLocalHome) HomeFactory.getHome(ACommunicatorWSBeanLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.TOA.CommunucatorWSInterfaces#recibirMensaje(co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase)
	 */
	public String recibirMensaje(ITRxxxBase trXXXe) {
		// TODO Apéndice de método generado automáticamente
		return servicios.recibirMensaje(trXXXe);
	}
	
	public String enviarMensajeEstructurado(ITRxxxBase trXXXe, String trXXXeXML, String trXXXeClassName) {
		// TODO Apéndice de método generado automáticamente
		return servicios.enviarMensajeEstructurado(trXXXe, trXXXeXML,trXXXeClassName);
	}
}
