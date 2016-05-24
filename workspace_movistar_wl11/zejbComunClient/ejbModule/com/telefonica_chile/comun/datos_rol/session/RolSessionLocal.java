package com.telefonica_chile.comun.datos_rol.session;
/**
 * Local interface for Enterprise Bean: RolSession
 */
public interface RolSessionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * recuperarRol
	 */
	public com.telefonica_chile.comun.rol.dto.RolDTO recuperarRol(
		java.lang.Long idAct);
	/**
	 * retornaDatosAct
	 */
	public com
		.telefonica_chile
		.comun
		.actividad
		.dto
		.ActividadDTO retornaDatosAct(
		java.lang.Long idAct);
}
