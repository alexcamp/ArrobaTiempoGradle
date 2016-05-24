package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Excepcion_distribucion_carga_maxima
 */
public abstract class Excepcion_distribucion_carga_maximaBean
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
		.ejb
		.eb
		.Excepcion_distribucion_carga_maximaKey ejbCreate(
			java.lang.Long exdcm_id)
		throws javax.ejb.CreateException {
		setExdcm_id(exdcm_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long exdcm_id)
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
	 * Get accessor for persistent attribute: exdcm_id
	 */
	public abstract java.lang.Long getExdcm_id();
	/**
	 * Set accessor for persistent attribute: exdcm_id
	 */
	public abstract void setExdcm_id(java.lang.Long newExdcm_id);
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
	 * Get accessor for persistent attribute: dia_especifico
	 */
	public abstract java.sql.Timestamp getDia_especifico();
	/**
	 * Set accessor for persistent attribute: dia_especifico
	 */
	public abstract void setDia_especifico(
		java.sql.Timestamp newDia_especifico);
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: porcentaje
	 */
	public abstract java.lang.Integer getPorcentaje();
	/**
	 * Set accessor for persistent attribute: porcentaje
	 */
	public abstract void setPorcentaje(java.lang.Integer newPorcentaje);
}
