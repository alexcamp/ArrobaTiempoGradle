package com.telefonica_chile.vpistbba.asignacion.session;
/**
 * Local interface for Enterprise Bean: Asignacion
 */
public interface AsignacionLocal extends javax.ejb.EJBLocalObject {
//	public String obtenerUsuario(
//		String idInstanciaProceso,
//		String codigoActividad);
	/*
	 * Este metodo retorna el usuario que puede atender una Peticion.
	 */
	public Long getIdUsuario(
		String idPeticion,
		Long idAmbito,
		String codigoActividad);
}
