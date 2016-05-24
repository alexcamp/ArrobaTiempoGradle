package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Excepcion_rango
 */
public abstract class Excepcion_rangoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey ejbCreate(
		java.lang.Long exra_id)
		throws javax.ejb.CreateException {
		setExra_id(exra_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long exra_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Excepcion_rangoKey ejbCreate(
		java.lang.Long exra_id,
		java.lang.Integer id_rango,
		java.lang.String codigo_pcom)
		throws javax.ejb.CreateException {
		setExra_id(exra_id);
		setId_rango(id_rango);
		setCodigo_pcom(codigo_pcom);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long exra_id,
		java.lang.Integer id_rango,
		java.lang.String codigo_pcom)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: exra_id
	 */
	public abstract java.lang.Long getExra_id();
	/**
	 * Set accessor for persistent attribute: exra_id
	 */
	public abstract void setExra_id(java.lang.Long newExra_id);
}
