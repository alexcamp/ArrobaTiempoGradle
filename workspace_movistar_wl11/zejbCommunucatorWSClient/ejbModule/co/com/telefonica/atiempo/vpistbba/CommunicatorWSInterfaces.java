/*
 * Creado el 28/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;

import com.telefonica_chile.comun.ComunInterfaces;



/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface CommunicatorWSInterfaces extends ComunInterfaces{

	public String recibirMensaje(ITRxxxBase trXXXe);
	
	public String enviarMensajeEstructurado(ITRxxxBase trXXXe, String trXXXeXML, String trXXXeClassName);
}
