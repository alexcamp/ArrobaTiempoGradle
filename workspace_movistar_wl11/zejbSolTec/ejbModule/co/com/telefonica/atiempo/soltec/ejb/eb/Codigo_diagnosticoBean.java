package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Codigo_diagnostico
 */
public abstract class Codigo_diagnosticoBean implements javax.ejb.EntityBean {
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
		.Codigo_diagnosticoKey ejbCreate(
		java.lang.String id_def_aper)
		throws javax.ejb.CreateException {
		setId_def_aper(id_def_aper);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String id_def_aper)
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
	 * Get accessor for persistent attribute: id_def_aper
	 */
	public abstract java.lang.String getId_def_aper();
	/**
	 * Set accessor for persistent attribute: id_def_aper
	 */
	public abstract void setId_def_aper(java.lang.String newId_def_aper);
	/**
	 * Get accessor for persistent attribute: cod_tipo_def
	 */
	public abstract java.lang.String getCod_tipo_def();
	/**
	 * Set accessor for persistent attribute: cod_tipo_def
	 */
	public abstract void setCod_tipo_def(java.lang.String newCod_tipo_def);
	/**
	 * Get accessor for persistent attribute: desc_tipo_def
	 */
	public abstract java.lang.String getDesc_tipo_def();
	/**
	 * Set accessor for persistent attribute: desc_tipo_def
	 */
	public abstract void setDesc_tipo_def(java.lang.String newDesc_tipo_def);
	/**
	 * Get accessor for persistent attribute: desc_def_aper
	 */
	public abstract java.lang.String getDesc_def_aper();
	/**
	 * Set accessor for persistent attribute: desc_def_aper
	 */
	public abstract void setDesc_def_aper(java.lang.String newDesc_def_aper);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_st(java.util.Collection aPeticion_st);
}
