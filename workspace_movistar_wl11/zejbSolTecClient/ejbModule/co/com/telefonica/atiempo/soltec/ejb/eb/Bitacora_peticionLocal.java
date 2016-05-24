package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Bitacora_peticion
 */
public interface Bitacora_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: caus_id
	 */
	public java.lang.Long getCaus_id();
	/**
	 * Set accessor for persistent attribute: caus_id
	 */
	public void setCaus_id(java.lang.Long newCaus_id);
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
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: bipe_observacion
	 */
	public java.lang.String getBipe_observacion();
	/**
	 * Set accessor for persistent attribute: bipe_observacion
	 */
	public void setBipe_observacion(java.lang.String newBipe_observacion);
}
