package com.telefonica_chile.comun.actividad.session;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
/**
 * Local interface for Enterprise Bean: ActividadSession
 */
public interface ActividadSessionLocal extends javax.ejb.EJBLocalObject {
	public boolean ActividadesNoGrabables(String codigo, Long idAplicacion);
	/** Métoodo que retorna CIERRE de acuerdo a la Actividad **/
	public ActividadDTO recuperaActividad(String codActividad,Long idAplicacion);
	public ActividadDTO getActividad(Long codActividad);
	public Long getIdActividad(String codigo,Long idAplicacion);
}
