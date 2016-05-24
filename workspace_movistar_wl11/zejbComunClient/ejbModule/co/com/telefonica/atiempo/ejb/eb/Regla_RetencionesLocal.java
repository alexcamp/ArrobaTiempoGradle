package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Regla_Retenciones
 */
public interface Regla_RetencionesLocal
	extends	javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * Get accessor for persistent attribute: ap_id
	 */
	public java.lang.Long getAp_id();
	/**
	 * Set accessor for persistent attribute: ap_id
	 */
	public void setAp_id(java.lang.Long newAp_id);
	/**
	 * Get accessor for persistent attribute: fecha_desde
	 */
	public java.sql.Timestamp getFecha_desde();
	/**
	 * Set accessor for persistent attribute: fecha_desde
	 */
	public void setFecha_desde(java.sql.Timestamp newFecha_desde);
	/**
	 * Get accessor for persistent attribute: fecha_hasta
	 */
	public java.sql.Timestamp getFecha_hasta();
	/**
	 * Set accessor for persistent attribute: fecha_hasta
	 */
	public void setFecha_hasta(java.sql.Timestamp newFecha_hasta);
	/**
	 * Get accessor for persistent attribute: cod_dpt
	 */
	public java.lang.String getCod_dpt();
	/**
	 * Set accessor for persistent attribute: cod_dpt
	 */
	public void setCod_dpt(java.lang.String newCod_dpt);
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public java.lang.String getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public void setCod_loc(java.lang.String newCod_loc);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public void setTica_id(java.lang.String newTica_id);
	/**
	 * Get accessor for persistent attribute: peti_id_instancia
	 */
	public java.lang.String getPeti_id_instancia();
	/**
	 * Set accessor for persistent attribute: peti_id_instancia
	 */
	public void setPeti_id_instancia(java.lang.String newPeti_id_instancia);
	/**
	 * Get accessor for persistent attribute: regla_id
	 */
	public java.lang.Long getRegla_id();
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public void setRegla_id(java.lang.Long newRegla_id);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
}
