package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Excepcion_carga_segmento
 */
public abstract class Excepcion_carga_segmentoBean
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
		.Excepcion_carga_segmentoKey ejbCreate(
		java.lang.Long excs_id)
		throws javax.ejb.CreateException {
		setExcs_id(excs_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long excs_id)
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
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: excs_id
	 */
	public abstract java.lang.Long getExcs_id();
	/**
	 * Set accessor for persistent attribute: excs_id
	 */
	public abstract void setExcs_id(java.lang.Long newExcs_id);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: porcentaje
	 */
	public abstract java.lang.Integer getPorcentaje();
	/**
	 * Set accessor for persistent attribute: porcentaje
	 */
	public abstract void setPorcentaje(java.lang.Integer newPorcentaje);
	/**
	 * Get accessor for persistent attribute: porcentaje_minimo
	 */
	public abstract java.lang.Integer getPorcentaje_minimo();
	/**
	 * Set accessor for persistent attribute: porcentaje_minimo
	 */
	public abstract void setPorcentaje_minimo(
		java.lang.Integer newPorcentaje_minimo);
}
