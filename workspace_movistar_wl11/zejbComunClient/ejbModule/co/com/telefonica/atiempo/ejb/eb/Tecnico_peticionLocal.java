package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tecnico_peticion
 */
public interface Tecnico_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public java.sql.Timestamp getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public void setFecha(java.sql.Timestamp newFecha);
	/**
	 * Get accessor for persistent attribute: hora_desde
	 */
	public java.lang.String getHora_desde();
	/**
	 * Set accessor for persistent attribute: hora_desde
	 */
	public void setHora_desde(java.lang.String newHora_desde);
	/**
	 * Get accessor for persistent attribute: hora_hasta
	 */
	public java.lang.String getHora_hasta();
	/**
	 * Set accessor for persistent attribute: hora_hasta
	 */
	public void setHora_hasta(java.lang.String newHora_hasta);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
	/**
	 * This method was generated for supporting the relationship role named causa_reagendamiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Causa_reagendamientoLocal getCausa_reagendamiento();
	/**
	 * This method was generated for supporting the relationship role named causa_reagendamiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausa_reagendamiento(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Causa_reagendamientoLocal aCausa_reagendamiento);
	/**
	 * This method was generated for supporting the relationship role named rango.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RangoLocal getRango();
	/**
	 * This method was generated for supporting the relationship role named rango.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRango(co.com.telefonica.atiempo.ejb.eb.RangoLocal aRango);
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.TecnicoLocal getTecnico();
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTecnico(
		co.com.telefonica.atiempo.ejb.eb.TecnicoLocal aTecnico);
	/**
	 * Get accessor for persistent attribute: ap_id
	 */
	public java.lang.Long getAp_id();
	/**
	 * Set accessor for persistent attribute: ap_id
	 */
	public void setAp_id(java.lang.Long newAp_id);
	
	public java.lang.String getNom_usua_logueado();
	/**
	 * Set accessor for persistent attribute: nom_usua_logueado
	 */
	public void setNom_usua_logueado(java.lang.String newNom_usua_logueado);
	/**
	 * Get accessor for persistent attribute: fecha_agendamiento
	 */
	public java.sql.Timestamp getFecha_agendamiento();
	/**
	 * Set accessor for persistent attribute: fecha_agendamiento
	 */
	public void setFecha_agendamiento(java.sql.Timestamp newFecha_agendamiento);
	
}
