package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Carga_maxima
 */
public abstract class Carga_maximaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey ejbCreate(
		java.lang.Long cmax_id)
		throws javax.ejb.CreateException {
		setCmax_id(cmax_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cmax_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Carga_maximaKey ejbCreate(
		java.lang.Long cmax_id,
		java.lang.Long codigo_familia_ps,
		java.lang.String codigo_pcom,
		java.lang.String dia_semana)
		throws javax.ejb.CreateException {
		setCmax_id(cmax_id);
		setCodigo_familia_ps(codigo_familia_ps);
		setCodigo_pcom(codigo_pcom);
		setDia_semana(dia_semana);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long cmax_id,
		java.lang.Long codigo_familia_ps,
		java.lang.String codigo_pcom,
		java.lang.String dia_semana)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: cmax_id
	 */
	public abstract java.lang.Long getCmax_id();
	/**
	 * Set accessor for persistent attribute: cmax_id
	 */
	public abstract void setCmax_id(java.lang.Long newCmax_id);
	/**
	 * Get accessor for persistent attribute: codigo_familia_ps
	 */
	public abstract java.lang.Long getCodigo_familia_ps();
	/**
	 * Set accessor for persistent attribute: codigo_familia_ps
	 */
	public abstract void setCodigo_familia_ps(
		java.lang.Long newCodigo_familia_ps);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: carga_maxima
	 */
	public abstract java.lang.Integer getCarga_maxima();
	/**
	 * Set accessor for persistent attribute: carga_maxima
	 */
	public abstract void setCarga_maxima(java.lang.Integer newCarga_maxima);
	/**
	 * Get accessor for persistent attribute: dia_semana
	 */
	public abstract java.lang.String getDia_semana();
	/**
	 * Set accessor for persistent attribute: dia_semana
	 */
	public abstract void setDia_semana(java.lang.String newDia_semana);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public abstract java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public abstract void setCodigo_agencia(java.lang.String newCodigo_agencia);
}
