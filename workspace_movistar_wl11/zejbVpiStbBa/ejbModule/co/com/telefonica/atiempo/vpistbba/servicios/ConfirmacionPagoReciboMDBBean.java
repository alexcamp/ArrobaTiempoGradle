package co.com.telefonica.atiempo.vpistbba.servicios;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * Bean implementation class for Enterprise Bean: ConfirmacionPagoReciboMDB
 */
public class ConfirmacionPagoReciboMDBBean
	extends
		co.com.telefonica.atiempo.utiles.MDServicioBean {

	private static Logger logger=Logger.getLogger(ConfirmacionPagoReciboMDBBean.class);
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
	 */
	public IServicio getServicio() {
		String correlativo = super.correlationID;
		//logger.debug("El correlativo en ConfirmacionPagoReciboMDBBean es:"+correlativo);
		return new ConfirmacionPagoReciboServicio(correlativo);
	}
}