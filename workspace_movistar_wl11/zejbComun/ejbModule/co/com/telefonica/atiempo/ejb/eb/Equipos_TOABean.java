package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Equipos_TOA
 */
public abstract class Equipos_TOABean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Equipos_TOAKey ejbCreate(
		java.lang.Long codigo_ps,
		java.lang.Long operacion_comercial) throws javax.ejb.CreateException {
		setCodigo_ps(codigo_ps);
		setOperacion_comercial(operacion_comercial);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long codigo_ps,
		java.lang.Long operacion_comercial) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: codigo_ps
	 */
	public abstract java.lang.Long getCodigo_ps();
	/**
	 * Set accessor for persistent attribute: codigo_ps
	 */
	public abstract void setCodigo_ps(java.lang.Long newCodigo_ps);
	/**
	 * Get accessor for persistent attribute: operacion_comercial
	 */
	public abstract java.lang.Long getOperacion_comercial();
	/**
	 * Set accessor for persistent attribute: operacion_comercial
	 */
	public abstract void setOperacion_comercial(
		java.lang.Long newOperacion_comercial);
	/**
	 * Get accessor for persistent attribute: obligatorio
	 */
	public abstract java.lang.String getObligatorio();
	/**
	 * Set accessor for persistent attribute: obligatorio
	 */
	public abstract void setObligatorio(java.lang.String newObligatorio);
	/**
	 * Get accessor for persistent attribute: familia
	 */
	public abstract java.lang.String getFamilia();
	/**
	 * Set accessor for persistent attribute: familia
	 */
	public abstract void setFamilia(java.lang.String newFamilia);
	/**
	 * Get accessor for persistent attribute: tipo_equipo
	 */
	public abstract java.lang.String getTipo_equipo();
	/**
	 * Set accessor for persistent attribute: tipo_equipo
	 */
	public abstract void setTipo_equipo(java.lang.String newTipo_equipo);
}