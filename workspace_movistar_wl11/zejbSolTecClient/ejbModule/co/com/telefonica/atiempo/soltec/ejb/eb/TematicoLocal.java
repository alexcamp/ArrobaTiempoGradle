package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tematico
 */
public interface TematicoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_tematico
	 */
	public java.lang.String getCod_tematico();
	/**
	 * Set accessor for persistent attribute: cod_tematico
	 */
	public void setCod_tematico(java.lang.String newCod_tematico);
	/**
	 * Get accessor for persistent attribute: origen
	 */
	public java.lang.String getOrigen();
	/**
	 * Set accessor for persistent attribute: origen
	 */
	public void setOrigen(java.lang.String newOrigen);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public void setCorrelativo(java.lang.Long newCorrelativo);
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
}
