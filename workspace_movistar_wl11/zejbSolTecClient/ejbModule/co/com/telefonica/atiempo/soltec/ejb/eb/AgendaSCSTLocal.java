package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: AgendaSCST
 */
public interface AgendaSCSTLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_actuacion
	 */
	public java.lang.String getId_actuacion();
	/**
	 * Set accessor for persistent attribute: id_actuacion
	 */
	public void setId_actuacion(java.lang.String newId_actuacion);
	/**
	 * Get accessor for persistent attribute: id_peticion_st
	 */
	public java.lang.Long getId_peticion_st();
	/**
	 * Set accessor for persistent attribute: id_peticion_st
	 */
	public void setId_peticion_st(java.lang.Long newId_peticion_st);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: mensaje_act
	 */
	public java.lang.String getMensaje_act();
	/**
	 * Set accessor for persistent attribute: mensaje_act
	 */
	public void setMensaje_act(java.lang.String newMensaje_act);
	/**
	 * Get accessor for persistent attribute: cierre_por_error
	 */
	public java.lang.Integer getCierre_por_error();
	/**
	 * Set accessor for persistent attribute: cierre_por_error
	 */
	public void setCierre_por_error(java.lang.Integer newCierre_por_error);
	/**
	 * Get accessor for persistent attribute: fecha_ingreso
	 */
	public java.sql.Timestamp getFecha_ingreso();
	/**
	 * Set accessor for persistent attribute: fecha_ingreso
	 */
	public void setFecha_ingreso(java.sql.Timestamp newFecha_ingreso);
	/**
	 * Get accessor for persistent attribute: nombre_contratista
	 */
	public java.lang.String getNombre_contratista();
	/**
	 * Set accessor for persistent attribute: nombre_contratista
	 */
	public void setNombre_contratista(java.lang.String newNombre_contratista);
	/**
	 * Get accessor for persistent attribute: cod_franqueo
	 */
	public java.lang.String getCod_franqueo();
	/**
	 * Set accessor for persistent attribute: cod_franqueo
	 */
	public void setCod_franqueo(java.lang.String newCod_franqueo);
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public java.lang.String getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public void setQuiebre(java.lang.String newQuiebre);
	/**
	 * Get accessor for persistent attribute: fecha_mod
	 */
	public java.sql.Timestamp getFecha_mod();
	/**
	 * Set accessor for persistent attribute: fecha_mod
	 */
	public void setFecha_mod(java.sql.Timestamp newFecha_mod);
}
