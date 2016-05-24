package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Localizacion
 */
public abstract class LocalizacionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionKey ejbCreate(
		java.lang.Integer cod_loc)
		throws javax.ejb.CreateException {
		setCod_loc(cod_loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer cod_loc)
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
	 * Get accessor for persistent attribute: cod_loc
	 */
	public abstract java.lang.Integer getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.Integer newCod_loc);
	/**
	 * Get accessor for persistent attribute: localizacion
	 */
	public abstract java.lang.String getLocalizacion();
	/**
	 * Set accessor for persistent attribute: localizacion
	 */
	public abstract void setLocalizacion(java.lang.String newLocalizacion);
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCodigo_cierre();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_cierre(java.util.Collection aCodigo_cierre);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal getFamilia_producto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
}
