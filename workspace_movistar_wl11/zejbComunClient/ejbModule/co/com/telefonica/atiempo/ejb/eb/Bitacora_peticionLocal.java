package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Bitacora_peticion
 */
public interface Bitacora_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: bipe_fecha_inicio
	 */
	public java.sql.Timestamp getBipe_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: bipe_fecha_inicio
	 */
	public void setBipe_fecha_inicio(java.sql.Timestamp newBipe_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: bipe_fecha_fin
	 */
	public java.sql.Timestamp getBipe_fecha_fin();
	/**
	 * Set accessor for persistent attribute: bipe_fecha_fin
	 */
	public void setBipe_fecha_fin(java.sql.Timestamp newBipe_fecha_fin);
	/**
	 * Get accessor for persistent attribute: bipe_observacion
	 */
	public java.lang.String getBipe_observacion();
	/**
	 * Set accessor for persistent attribute: bipe_observacion
	 */
	public void setBipe_observacion(java.lang.String newBipe_observacion);
	/**
	 * Get accessor for persistent attribute: usua_id_cierre
	 */
	public java.lang.Long getUsua_id_cierre();
	/**
	 * Set accessor for persistent attribute: usua_id_cierre
	 */
	public void setUsua_id_cierre(java.lang.Long newUsua_id_cierre);
	/**
	 * Get accessor for persistent attribute: bipe_es_reversa
	 */
	public java.lang.Short getBipe_es_reversa();
	/**
	 * Set accessor for persistent attribute: bipe_es_reversa
	 */
	public void setBipe_es_reversa(java.lang.Short newBipe_es_reversa);
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_bipe.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal getFk_acti_2_bipe();
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_bipe.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_acti_2_bipe(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal aFk_acti_2_bipe);
	/**
	 * This method was generated for supporting the relationship role named fk_caus_2_bita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal getFk_caus_2_bita();
	/**
	 * This method was generated for supporting the relationship role named fk_caus_2_bita.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_caus_2_bita(
		co.com.telefonica.atiempo.ejb.eb.CausaLocal aFk_caus_2_bita);
	/**
	 * This method was generated for supporting the relationship role named fk_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getFk_peticion();
	/**
	 * This method was generated for supporting the relationship role named fk_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_peticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_peticion);
	/**
	 * @return
	 */
	public Long getIdActividad();
	public void setIdCausa(Long idCausa);
}
