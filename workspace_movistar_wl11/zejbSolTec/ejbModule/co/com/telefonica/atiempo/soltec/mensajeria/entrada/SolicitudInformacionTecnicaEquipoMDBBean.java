package co.com.telefonica.atiempo.soltec.mensajeria.entrada;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.soltec.servicios.SolicitudInformacionTecnicaEquipoServicio;

/**
 * Bean implementation class for Enterprise Bean: SolicitudInformacionTecnicaEquipoMDB
 */
public class SolicitudInformacionTecnicaEquipoMDBBean
extends co.com.telefonica.atiempo.utiles.MDServicioBean
implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
private javax.ejb.MessageDrivenContext fMessageDrivenCtx;
/**
 * @see co.com.telefonica.atiempo.utiles.MDServicioBean#getServicio()
 */
public IServicio getServicio() {
	return new SolicitudInformacionTecnicaEquipoServicio();
}
}
