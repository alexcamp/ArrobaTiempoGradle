package com.telefonica_chile.vpistbba.bitacora.session;


import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/**
 * Local interface for Enterprise Bean: Bitacora
 */
public interface BitacoraLocal extends javax.ejb.EJBLocalObject {
//	public ArrayList obtenerDatosBita(Long idPeticion, String idAcceso);
	public boolean crearRegistroBitacora(
		Long idPeticion,
		Long idActividad,
		Long idUsuario) throws ATiempoAppEx;
	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario) throws ATiempoAppEx;
	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		String obs) throws ATiempoAppEx;
	public boolean crearRegistroBitacora(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		Integer idEstado) throws ATiempoAppEx;
	public boolean crearRegistroBitacoraCerrado(
		Long idPeticion,
		Long idActividad,
		Long idUsuario,
		String obs,
		Integer idEstado) throws ATiempoAppEx;
	public boolean crearRegistroBitacora(Long idPeticion, Long idActividad, Long idUsuario, String obs) throws ATiempoAppEx;
	public boolean realizoActividad(Long idPeticion, Long idActividad);	
	public boolean cierra(Long idPetico,Long idAct,Long idUsuarioCierre,String obs, Long idCausa, boolean overwriteObs);	
	public ArrayList getListadoBitacora(Long idPeticion);
	public boolean actualizaObservacionBitacora(
		Long idPetico,
		Long idAct,
		String obs, boolean overwriteObs);
}
