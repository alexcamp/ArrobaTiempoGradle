package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Codigo_cierre_peticion
 */
public interface Codigo_cierre_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public java.sql.Timestamp getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public void setFecha(java.sql.Timestamp newFecha);
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
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierreLocal getCodigo_cierre();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCodigo_cierre(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Codigo_cierreLocal aCodigo_cierre);
}
