package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Producto_servicio
 */
public interface Producto_servicioLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * Get accessor for persistent attribute: empr_id
	 */
	public java.lang.Long getEmpr_id();
	/**
	 * Set accessor for persistent attribute: empr_id
	 */
	public void setEmpr_id(java.lang.Long newEmpr_id);
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * Get accessor for persistent attribute: grps_id
	 */
	public java.lang.Long getGrps_id();
	/**
	 * Set accessor for persistent attribute: grps_id
	 */
	public void setGrps_id(java.lang.Long newGrps_id);
	/**
	 * Get accessor for persistent attribute: ps_nombre
	 */
	public java.lang.String getPs_nombre();
	/**
	 * Set accessor for persistent attribute: ps_nombre
	 */
	public void setPs_nombre(java.lang.String newPs_nombre);
	/**
	 * Get accessor for persistent attribute: ps_es_facturable
	 */
	public java.lang.Short getPs_es_facturable();
	/**
	 * Set accessor for persistent attribute: ps_es_facturable
	 */
	public void setPs_es_facturable(java.lang.Short newPs_es_facturable);
	/**
	 * Get accessor for persistent attribute: ps_permite_gestion_tecnico
	 */
	public java.lang.Short getPs_permite_gestion_tecnico();
	/**
	 * Set accessor for persistent attribute: ps_permite_gestion_tecnico
	 */
	public void setPs_permite_gestion_tecnico(
		java.lang.Short newPs_permite_gestion_tecnico);
	/**
	 * Get accessor for persistent attribute: ps_observacion
	 */
	public java.lang.String getPs_observacion();
	/**
	 * Set accessor for persistent attribute: ps_observacion
	 */
	public void setPs_observacion(java.lang.String newPs_observacion);
	/**
	 * Get accessor for persistent attribute: ps_vigente
	 */
	public java.lang.Short getPs_vigente();
	/**
	 * Set accessor for persistent attribute: ps_vigente
	 */
	public void setPs_vigente(java.lang.Short newPs_vigente);
	/**
	 * Get accessor for persistent attribute: ps_pco_obligatorio
	 */
	public java.lang.Short getPs_pco_obligatorio();
	/**
	 * Set accessor for persistent attribute: ps_pco_obligatorio
	 */
	public void setPs_pco_obligatorio(java.lang.Short newPs_pco_obligatorio);
	/**
	 * Get accessor for persistent attribute: ps_comando_activacion
	 */
	public java.lang.String getPs_comando_activacion();
	/**
	 * Set accessor for persistent attribute: ps_comando_activacion
	 */
	public void setPs_comando_activacion(
		java.lang.String newPs_comando_activacion);
	/**
	 * Get accessor for persistent attribute: habi_id
	 */
	public java.lang.Integer getHabi_id();
	/**
	 * Set accessor for persistent attribute: habi_id
	 */
	public void setHabi_id(java.lang.Integer newHabi_id);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Familia_producto_servicioLocal getFamilia_producto_servicio();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFamilia_producto_servicio(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Familia_producto_servicioLocal aFamilia_producto_servicio);
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTraslado();
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTraslado(java.util.Collection aTraslado);
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getEstado_ps_peticion();
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setEstado_ps_peticion(java.util.Collection anEstado_ps_peticion);
	/**
	 * Get accessor for persistent attribute: velocidad
	 */
	public java.lang.String getVelocidad();
	/**
	 * Set accessor for persistent attribute: velocidad
	 */
	public void setVelocidad(java.lang.String newVelocidad);
	/**
	 * Get accessor for persistent attribute: inf_fttc
	 */
	public java.lang.Long getInf_fttc();
	/**
	 * Set accessor for persistent attribute: inf_fttc
	 */
	public void setInf_fttc(java.lang.Long newInf_fttc);
	/**
	 * Get accessor for persistent attribute: fa_ps
	 */
	public java.lang.String getFa_ps();
	/**
	 * Set accessor for persistent attribute: fa_ps
	 */
	public void setFa_ps(java.lang.String newFa_ps);
}
