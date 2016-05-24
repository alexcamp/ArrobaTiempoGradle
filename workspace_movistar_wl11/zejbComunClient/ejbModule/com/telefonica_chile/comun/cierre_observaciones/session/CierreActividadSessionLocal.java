package com.telefonica_chile.comun.cierre_observaciones.session;

import java.util.ArrayList;
/**
 * Local interface for Enterprise Bean: CierreActividadSession
 */
public interface CierreActividadSessionLocal extends javax.ejb.EJBLocalObject {
	/** M�toodo que retorna CIERRE de acuerdo a la Actividad **/
	public ArrayList recuperaCierre(String actividad,Long idAplicacion);
	public ArrayList recuperaCausa(String actividad, Integer idAmbito,Long idAplicacion);
	public CierreActividadDTO recuperaCierreActividadId(Long id);
	public CierreActividadDTO recuperaCierreActividadCiacNombre(Long idActividad);
	public CierreActividadDTO recuperaCierreActividadProblema(Long idActividad);
	/*retorno registro con idActividad dado y cuyo 	Nombre de cierre tenga
	 * dentro de su string la cadena Termina
	 */
	public CierreActividadDTO getCierreByIdAndNombre(Long idActividad);
	/*retorno registro con idActividad dado y cuyo 	Nombre de cierre tenga
	 * dentro de su string la cadena Solucion
	 */
	public CierreActividadDTO getCierrePendiente(Long idActividad);
}
