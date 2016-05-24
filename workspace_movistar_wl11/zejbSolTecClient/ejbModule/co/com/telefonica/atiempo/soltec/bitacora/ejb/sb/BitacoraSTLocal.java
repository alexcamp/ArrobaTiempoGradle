package co.com.telefonica.atiempo.soltec.bitacora.ejb.sb;

import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * Local interface for Enterprise Bean: BitacoraST
 */
public interface BitacoraSTLocal extends javax.ejb.EJBLocalObject {
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
	public boolean cierra(Long idPetico,Long idAct,Long idUsuarioCierre,String obs, Long idCausa);	
	public ArrayList getListadoBitacora(Long idPeticion);
	/**
	 * @param numeroPeticion
	 * @param idActividad
	 * @param observacion
	 * @param overwriteObs
	 * @return
	 */
	public boolean actualizaObservacionBitacora(Long numeroPeticion, Long idActividad, String observacion, boolean overwriteObs);	
}
