package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Area_gestion
 */
public abstract class Area_gestionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Area_gestionKey ejbCreate(
		java.lang.Long id_area_ges)
		throws javax.ejb.CreateException {
		setId_area_ges(id_area_ges);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_area_ges)
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
	 * Get accessor for persistent attribute: id_area_ges
	 */
	public abstract java.lang.Long getId_area_ges();
	/**
	 * Set accessor for persistent attribute: id_area_ges
	 */
	public abstract void setId_area_ges(java.lang.Long newId_area_ges);
	/**
	 * Get accessor for persistent attribute: cod_area_ges
	 */
	public abstract java.lang.Long getCod_area_ges();
	/**
	 * Set accessor for persistent attribute: cod_area_ges
	 */
	public abstract void setCod_area_ges(java.lang.Long newCod_area_ges);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
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
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRegla_area_ges_cod_estado();
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRegla_area_ges_cod_estado(
		java.util.Collection aRegla_area_ges_cod_estado);
}
