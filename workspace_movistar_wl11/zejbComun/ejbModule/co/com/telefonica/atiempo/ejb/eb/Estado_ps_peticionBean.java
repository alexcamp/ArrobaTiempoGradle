package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Estado_ps_peticion
 */
public abstract class Estado_ps_peticionBean implements javax.ejb.EntityBean {
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
	
		
	
	public co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey ejbCreate(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		return null;
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey ejbCreate(Long correlativo,Producto_servicioLocal producto_servicioLocal,Producto_servicio_peticionLocal producto_servicio_peticionLocal)
			throws javax.ejb.CreateException {
				setCorrelativo(correlativo);
			return null;
		}
		
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(Long correlativo,Producto_servicioLocal producto_servicioLocal,Producto_servicio_peticionLocal producto_servicio_peticionLocal)
		throws javax.ejb.CreateException
	{
		setProducto_servicio(producto_servicioLocal);
		setProducto_servicio_peticion(producto_servicio_peticionLocal);
	}
	
	public void ejbPostCreate(java.lang.Long correlativo)
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
	 * Get accessor for persistent attribute: cod_estado_cierre
	 */
	public abstract java.lang.Integer getCod_estado_cierre();
	/**
	 * Set accessor for persistent attribute: cod_estado_cierre
	 */
	public abstract void setCod_estado_cierre(
		java.lang.Integer newCod_estado_cierre);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicioLocal getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicioLocal aProducto_servicio);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_peticion(
		java.util.Collection aCausal_peticion);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Producto_servicio_peticionLocal getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_peticion(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Producto_servicio_peticionLocal aProducto_servicio_peticion);
	/**
	 * Get accessor for persistent attribute: cod_causal
	 */
	public abstract java.lang.Long getCod_causal();
	/**
	 * Set accessor for persistent attribute: cod_causal
	 */
	public abstract void setCod_causal(java.lang.Long newCod_causal);
	/**
	 * Get accessor for persistent attribute: novedad
	 */
	public abstract java.lang.String getNovedad();
	/**
	 * Set accessor for persistent attribute: novedad
	 */
	public abstract void setNovedad(java.lang.String newNovedad);
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	/**
	 * Get accessor for persistent attribute: cod_actividad
	 */
	public abstract java.lang.Long getCod_actividad();
	/**
	 * Set accessor for persistent attribute: cod_actividad
	 */
	public abstract void setCod_actividad(java.lang.Long newCod_actividad);
}
