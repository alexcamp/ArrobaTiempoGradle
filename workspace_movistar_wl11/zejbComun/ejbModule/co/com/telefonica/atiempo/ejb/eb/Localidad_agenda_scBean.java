package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Localidad_agenda_sc
 */
public abstract class Localidad_agenda_scBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scKey ejbCreate(
		java.lang.String cod_loc) throws javax.ejb.CreateException {
		setCod_loc(cod_loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_loc)
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
	public abstract java.lang.String getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.String newCod_loc);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.LocalidadLocal getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aLocalidad);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
}