package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Bean implementation class for Enterprise Bean: Homologacion_ps_sva_ba
 */
public abstract class Homologacion_ps_sva_baBean
	implements
		javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Homologacion_ps_sva_baKey ejbCreate(
		java.lang.Long ps_ba,
		java.lang.Long ps_sva) throws javax.ejb.CreateException {
		setPs_ba(ps_ba);
		setPs_sva(ps_sva);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ps_ba, java.lang.Long ps_sva)
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
	 * Get accessor for persistent attribute: ps_ba
	 */
	public abstract java.lang.Long getPs_ba();
	/**
	 * Set accessor for persistent attribute: ps_ba
	 */
	public abstract void setPs_ba(java.lang.Long newPs_ba);
	/**
	 * Get accessor for persistent attribute: ps_sva
	 */
	public abstract java.lang.Long getPs_sva();
	/**
	 * Set accessor for persistent attribute: ps_sva
	 */
	public abstract void setPs_sva(java.lang.Long newPs_sva);
	/**
	 * Get accessor for persistent attribute: ps_homologado
	 */
	public abstract java.lang.Long getPs_homologado();
	/**
	 * Set accessor for persistent attribute: ps_homologado
	 */
	public abstract void setPs_homologado(java.lang.Long newPs_homologado);
}