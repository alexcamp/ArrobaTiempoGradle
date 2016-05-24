package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Causal_peticion
 */
public interface Causal_peticionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_actividad
	 */
	public java.lang.Long getCod_actividad();
	/**
	 * Set accessor for persistent attribute: cod_actividad
	 */
	public void setCod_actividad(java.lang.Long newCod_actividad);
	/**
	 * Get accessor for persistent attribute: novedad
	 */
	public java.lang.String getNovedad();
	/**
	 * Set accessor for persistent attribute: novedad
	 */
	public void setNovedad(java.lang.String newNovedad);
	/**
	 * Get accessor for persistent attribute: fecha_inicio
	 */
	public java.lang.String getFecha_inicio();
	/**
	 * Set accessor for persistent attribute: fecha_inicio
	 */
	public void setFecha_inicio(java.lang.String newFecha_inicio);
	/**
	 * Get accessor for persistent attribute: fecha_termino
	 */
	public java.lang.String getFecha_termino();
	/**
	 * Set accessor for persistent attribute: fecha_termino
	 */
	public void setFecha_termino(java.lang.String newFecha_termino);
	/**
	 * Get accessor for persistent attribute: id_causal_peticion
	 */
	public java.lang.Long getId_causal_peticion();
	/**
	 * Set accessor for persistent attribute: id_causal_peticion
	 */
	public void setId_causal_peticion(java.lang.Long newId_causal_peticion);
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalogo_causal(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named estado_ps.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Estado_psLocal getEstado_ps();
	/**
	 * This method was generated for supporting the relationship role named estado_ps.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setEstado_ps(
		co.com.telefonica.atiempo.ejb.eb.Estado_psLocal anEstado_ps);
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Estado_ps_peticionLocal getEstado_ps_peticion();
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setEstado_ps_peticion(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Estado_ps_peticionLocal anEstado_ps_peticion);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUsuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal anUsuario);
}
