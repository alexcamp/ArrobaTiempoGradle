/*
 * Created on Apr 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.senders;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author guido
 *
 */
public class SenderFactory {
	/**
	 * Retorna el sender de TR a utilizar. 
	 * Este metodo es un servicio de factory, que en este caso sirve para los envios de Altamira, 
	 * pero pueden haber mas formas de retornar/calcular el sender especifico que sirva para 
	 * la operación comercial que corresponda. 
	 */
	public static SenderAbstract getSender(String atiempoServiceName) {
		SenderTr601Base senderTr601 = null;
		if (ComunInterfaces.ALT_SERVICIO_CAMBIO_NUMERO.equals(atiempoServiceName)) {
			senderTr601 =  new SenderTr601CambioNumero();
		} else if (ComunInterfaces.ALT_SERVICIO_BAJA_ABONADO.equals(atiempoServiceName) || ComunInterfaces.ALT_SERVICIO_BAJA_PERIODIFICACION.equals(atiempoServiceName)) {
				senderTr601 =  new SenderTr601Baja();
		} else {
			senderTr601 = new SenderTr601Base();
		}
		senderTr601.setATiempoServiceName(atiempoServiceName);
		return senderTr601;
	}
}
