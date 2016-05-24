package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Prueba_linea
 */
public interface Prueba_lineaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: observacion
	 */
	public java.lang.String getObservacion();
	/**
	 * Set accessor for persistent attribute: observacion
	 */
	public void setObservacion(java.lang.String newObservacion);
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
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.actividades
		.cancelacion
		.ejb
		.sb
		.Catalago_prueba_lineaLocal getCatalago_prueba_linea();
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalago_prueba_linea(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.actividades
			.cancelacion
			.ejb
			.sb
			.Catalago_prueba_lineaLocal aCatalago_prueba_linea);
}
