package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Familia_producto_servicio_st
 */
public abstract class Familia_producto_servicio_stBean
	implements javax.ejb.EntityBean {
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
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stKey ejbCreate(java.lang.Long faps_id)
		throws javax.ejb.CreateException {
		setFaps_id(faps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long faps_id)
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
	 * Get accessor for persistent attribute: faps_id
	 */
	public abstract java.lang.Long getFaps_id();
	/**
	 * Set accessor for persistent attribute: faps_id
	 */
	public abstract void setFaps_id(java.lang.Long newFaps_id);
	/**
	 * Get accessor for persistent attribute: faps_nombre
	 */
	public abstract java.lang.String getFaps_nombre();
	/**
	 * Set accessor for persistent attribute: faps_nombre
	 */
	public abstract void setFaps_nombre(java.lang.String newFaps_nombre);
	/**
	 * Get accessor for persistent attribute: faps_codigo
	 */
	public abstract java.lang.String getFaps_codigo();
	/**
	 * Set accessor for persistent attribute: faps_codigo
	 */
	public abstract void setFaps_codigo(java.lang.String newFaps_codigo);
	/**
	 * Get accessor for persistent attribute: faps_prioridad
	 */
	public abstract java.lang.Integer getFaps_prioridad();
	/**
	 * Set accessor for persistent attribute: faps_prioridad
	 */
	public abstract void setFaps_prioridad(java.lang.Integer newFaps_prioridad);
	/**
	 * Get accessor for persistent attribute: faps_id_padre
	 */
	public abstract java.lang.Long getFaps_id_padre();
	/**
	 * Set accessor for persistent attribute: faps_id_padre
	 */
	public abstract void setFaps_id_padre(java.lang.Long newFaps_id_padre);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_st(
		java.util.Collection aProducto_servicio_st);
	/**
	 * Get accessor for persistent attribute: tiempo
	 */
	public abstract java.lang.Integer getTiempo();
	/**
	 * Set accessor for persistent attribute: tiempo
	 */
	public abstract void setTiempo(java.lang.Integer newTiempo);
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getLocalizacion();
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalizacion(java.util.Collection aLocalizacion);
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCatalago_prueba_linea();
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCatalago_prueba_linea(
		java.util.Collection aCatalago_prueba_linea);
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getArea_gestion();
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setArea_gestion(java.util.Collection anArea_gestion);
}
