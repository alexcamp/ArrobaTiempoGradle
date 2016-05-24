package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Ciclos_facturacion
 */
public abstract class Ciclos_facturacionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionKey ejbCreate(
		java.lang.Integer ciclo) throws javax.ejb.CreateException {
		setCiclo(ciclo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer ciclo)
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
	 * Get accessor for persistent attribute: dia_inicial
	 */
	public abstract java.lang.Integer getDia_inicial();
	/**
	 * Set accessor for persistent attribute: dia_inicial
	 */
	public abstract void setDia_inicial(java.lang.Integer newDia_inicial);
	/**
	 * Get accessor for persistent attribute: dia_final
	 */
	public abstract java.lang.Integer getDia_final();
	/**
	 * Set accessor for persistent attribute: dia_final
	 */
	public abstract void setDia_final(java.lang.Integer newDia_final);
	/**
	 * Get accessor for persistent attribute: ciclo
	 */
	public abstract java.lang.Integer getCiclo();
	/**
	 * Set accessor for persistent attribute: ciclo
	 */
	public abstract void setCiclo(java.lang.Integer newCiclo);
	/**
	 * Get accessor for persistent attribute: corte
	 */
	public abstract java.lang.Integer getCorte();
	/**
	 * Set accessor for persistent attribute: corte
	 */
	public abstract void setCorte(java.lang.Integer newCorte);
}