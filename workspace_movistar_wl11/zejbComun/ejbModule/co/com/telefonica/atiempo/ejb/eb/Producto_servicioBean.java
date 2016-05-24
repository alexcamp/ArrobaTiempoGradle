package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Producto_servicio
 */
public abstract class Producto_servicioBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey ejbCreate(
		java.lang.Long ps_id) throws javax.ejb.CreateException {
		setPs_id(ps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ps_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey ejbCreate(
		java.lang.Long ps_id,
		java.lang.Long empr_id,
		java.lang.Integer ambi_id,
		Familia_producto_servicioLocal fpslocal,
		java.lang.String ps_nombre,
		java.lang.Short ps_es_facturable,
		java.lang.Short ps_permite_gestion_tecnico,
		java.lang.Short ps_pco_obligatorio)
		throws javax.ejb.CreateException {
		setPs_id(ps_id);
		setEmpr_id(empr_id);
		setAmbi_id(ambi_id);
		setPs_nombre(ps_nombre);
		setPs_es_facturable(ps_es_facturable);
		setPs_permite_gestion_tecnico(ps_permite_gestion_tecnico);
		setPs_pco_obligatorio(ps_pco_obligatorio);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long ps_id,
		java.lang.Long empr_id,
		java.lang.Integer ambi_id,
		Familia_producto_servicioLocal fpslocal,
		java.lang.String ps_nombre,
		java.lang.Short ps_es_facturable,
		java.lang.Short ps_permite_gestion_tecnico,
		java.lang.Short ps_pco_obligatorio)
		throws javax.ejb.CreateException {
			setFamilia_producto_servicio(fpslocal);
	}
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: empr_id
	 */
	public abstract java.lang.Long getEmpr_id();
	/**
	 * Set accessor for persistent attribute: empr_id
	 */
	public abstract void setEmpr_id(java.lang.Long newEmpr_id);
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public abstract java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public abstract void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * Get accessor for persistent attribute: grps_id
	 */
	public abstract java.lang.Long getGrps_id();
	/**
	 * Set accessor for persistent attribute: grps_id
	 */
	public abstract void setGrps_id(java.lang.Long newGrps_id);
	/**
	 * Get accessor for persistent attribute: ps_nombre
	 */
	public abstract java.lang.String getPs_nombre();
	/**
	 * Set accessor for persistent attribute: ps_nombre
	 */
	public abstract void setPs_nombre(java.lang.String newPs_nombre);
	/**
	 * Get accessor for persistent attribute: ps_es_facturable
	 */
	public abstract java.lang.Short getPs_es_facturable();
	/**
	 * Set accessor for persistent attribute: ps_es_facturable
	 */
	public abstract void setPs_es_facturable(
		java.lang.Short newPs_es_facturable);
	/**
	 * Get accessor for persistent attribute: ps_permite_gestion_tecnico
	 */
	public abstract java.lang.Short getPs_permite_gestion_tecnico();
	/**
	 * Set accessor for persistent attribute: ps_permite_gestion_tecnico
	 */
	public abstract void setPs_permite_gestion_tecnico(
		java.lang.Short newPs_permite_gestion_tecnico);
	/**
	 * Get accessor for persistent attribute: ps_observacion
	 */
	public abstract java.lang.String getPs_observacion();
	/**
	 * Set accessor for persistent attribute: ps_observacion
	 */
	public abstract void setPs_observacion(java.lang.String newPs_observacion);
	/**
	 * Get accessor for persistent attribute: ps_vigente
	 */
	public abstract java.lang.Short getPs_vigente();
	/**
	 * Set accessor for persistent attribute: ps_vigente
	 */
	public abstract void setPs_vigente(java.lang.Short newPs_vigente);
	/**
	 * Get accessor for persistent attribute: ps_pco_obligatorio
	 */
	public abstract java.lang.Short getPs_pco_obligatorio();
	/**
	 * Set accessor for persistent attribute: ps_pco_obligatorio
	 */
	public abstract void setPs_pco_obligatorio(
		java.lang.Short newPs_pco_obligatorio);
	/**
	 * Get accessor for persistent attribute: ps_comando_activacion
	 */
	public abstract java.lang.String getPs_comando_activacion();
	/**
	 * Set accessor for persistent attribute: ps_comando_activacion
	 */
	public abstract void setPs_comando_activacion(
		java.lang.String newPs_comando_activacion);
	/**
	 * Get accessor for persistent attribute: habi_id
	 */
	public abstract java.lang.Integer getHabi_id();
	/**
	 * Set accessor for persistent attribute: habi_id
	 */
	public abstract void setHabi_id(java.lang.Integer newHabi_id);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFamilia_producto_servicio(
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
	public abstract java.util.Collection getTraslado();
	/**
	 * This method was generated for supporting the relationship role named traslado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTraslado(java.util.Collection aTraslado);
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getEstado_ps_peticion();
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEstado_ps_peticion(
		java.util.Collection anEstado_ps_peticion);
	/**
	 * Get accessor for persistent attribute: velocidad
	 */
	public abstract java.lang.String getVelocidad();
	/**
	 * Set accessor for persistent attribute: velocidad
	 */
	public abstract void setVelocidad(java.lang.String newVelocidad);
	/**
	 * Get accessor for persistent attribute: inf_fttc
	 */
	public abstract java.lang.Long getInf_fttc();
	/**
	 * Set accessor for persistent attribute: inf_fttc
	 */
	public abstract void setInf_fttc(java.lang.Long newInf_fttc);
	/**
	 * Get accessor for persistent attribute: fa_ps
	 */
	public abstract java.lang.String getFa_ps();
	/**
	 * Set accessor for persistent attribute: fa_ps
	 */
	public abstract void setFa_ps(java.lang.String newFa_ps);
}
