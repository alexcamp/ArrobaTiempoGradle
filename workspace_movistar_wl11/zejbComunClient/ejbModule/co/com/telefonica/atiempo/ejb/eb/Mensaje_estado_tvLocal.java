package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_estado_tv
 */
public interface Mensaje_estado_tvLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
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
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.sql.Timestamp getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.sql.Timestamp newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public java.sql.Timestamp getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public void setFecha_cierre(java.sql.Timestamp newFecha_cierre);

	//CR22115 - adocarmo - Inicio	
	/**
	 * Get accessor for persistent attribute: es_refresh
	 */
	public abstract java.lang.Short getEs_refresh();
	/**
	 * Set accessor for persistent attribute: es_refresh
	 */
	public abstract void setEs_refresh(java.lang.Short newEs_refresh);
	//CR22115 - adocarmo - Fin
	
}
