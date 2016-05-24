package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_estado_ba
 */
public interface Mensaje_estado_baLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public java.lang.String getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public void setFecha_cierre(java.lang.String newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: cod_actividad_generadora
	 */
	public java.lang.String getCod_actividad_generadora();
	/**
	 * Set accessor for persistent attribute: cod_actividad_generadora
	 */
	public void setCod_actividad_generadora(
		java.lang.String newCod_actividad_generadora);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: area
	 */
	public java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public void setArea(java.lang.Integer newArea);
	/**
	 * Get accessor for persistent attribute: id_error
	 */
	public java.lang.String getId_error();
	/**
	 * Set accessor for persistent attribute: id_error
	 */
	public void setId_error(java.lang.String newId_error);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public void setDesc_error(java.lang.String newDesc_error);
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
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estadoLocal getMensaje_estado();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal aMensaje_estado);
	/**
	 * This method was generated for supporting the relationship role named conector.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ConectorLocal getConector();
	/**
	 * This method was generated for supporting the relationship role named conector.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setConector(
		co.com.telefonica.atiempo.ejb.eb.ConectorLocal aConector);
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRecursos_ba();
	/**
	 * This method was generated for supporting the relationship role named recursos_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRecursos_ba(java.util.Collection aRecursos_ba);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.String newAccion);
	/**
	 * Get accessor for persistent attribute: cod_sub_estado
	 */
	public java.lang.Integer getCod_sub_estado();
	/**
	 * Set accessor for persistent attribute: cod_sub_estado
	 */
	public void setCod_sub_estado(java.lang.Integer newCod_sub_estado);
}
