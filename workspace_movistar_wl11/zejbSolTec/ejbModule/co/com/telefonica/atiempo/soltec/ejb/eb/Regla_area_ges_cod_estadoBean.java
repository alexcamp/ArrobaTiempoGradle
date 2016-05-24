package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Regla_area_ges_cod_estado
 */
public abstract class Regla_area_ges_cod_estadoBean
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
		.Regla_area_ges_cod_estadoKey ejbCreate(
			java.lang.Long id_regla_area_estado)
		throws javax.ejb.CreateException {
		setId_regla_area_estado(id_regla_area_estado);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_regla_area_estado)
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
	 * Get accessor for persistent attribute: id_regla_area_estado
	 */
	public abstract java.lang.Long getId_regla_area_estado();
	/**
	 * Set accessor for persistent attribute: id_regla_area_estado
	 */
	public abstract void setId_regla_area_estado(
		java.lang.Long newId_regla_area_estado);
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Area_gestionLocal getArea_gestion();
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setArea_gestion(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Area_gestionLocal anArea_gestion);
	/**
	 * This method was generated for supporting the relationship role named codigo_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_stLocal getCodigo_st();
	/**
	 * This method was generated for supporting the relationship role named codigo_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocal aCodigo_st);
	/**
	 * This method was generated for supporting the relationship role named regla.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.ReglaLocal getRegla();
	/**
	 * This method was generated for supporting the relationship role named regla.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRegla(
		co.com.telefonica.atiempo.soltec.ejb.eb.ReglaLocal aRegla);
}
