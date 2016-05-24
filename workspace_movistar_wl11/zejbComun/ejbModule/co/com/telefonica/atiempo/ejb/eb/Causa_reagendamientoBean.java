package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Causa_reagendamiento
 */
public abstract class Causa_reagendamientoBean
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
	public co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoKey ejbCreate(
		java.lang.Integer care_id)
		throws javax.ejb.CreateException {
		setCare_id(care_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer care_id)
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
	 * Get accessor for persistent attribute: care_id
	 */
	public abstract java.lang.Integer getCare_id();
	/**
	 * Set accessor for persistent attribute: care_id
	 */
	public abstract void setCare_id(java.lang.Integer newCare_id);
	/**
	 * Get accessor for persistent attribute: care_descripcion
	 */
	public abstract java.lang.String getCare_descripcion();
	/**
	 * Set accessor for persistent attribute: care_descripcion
	 */
	public abstract void setCare_descripcion(
		java.lang.String newCare_descripcion);
	/**
	 * Get accessor for persistent attribute: care_codigo
	 */
	public abstract java.lang.String getCare_codigo();
	/**
	 * Set accessor for persistent attribute: care_codigo
	 */
	public abstract void setCare_codigo(java.lang.String newCare_codigo);
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTecnico_peticion();
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTecnico_peticion(
		java.util.Collection aTecnico_peticion);
}
