package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Excepcion_tipo_agendamiento
 */
public abstract class Excepcion_tipo_agendamientoBean
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
		.Excepcion_tipo_agendamientoKey ejbCreate(java.lang.Long exta_id)
		throws javax.ejb.CreateException {
		setExta_id(exta_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long exta_id)
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
	 * Get accessor for persistent attribute: exta_id
	 */
	public abstract java.lang.Long getExta_id();
	/**
	 * Set accessor for persistent attribute: exta_id
	 */
	public abstract void setExta_id(java.lang.Long newExta_id);
	/**
	 * Get accessor for persistent attribute: tiag_id
	 */
	public abstract java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public abstract void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public abstract java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public abstract void setCodigo_agencia(java.lang.String newCodigo_agencia);
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
	 * Get accessor for persistent attribute: operacion_comercial
	 */
	public abstract java.lang.String getOperacion_comercial();
	/**
	 * Set accessor for persistent attribute: operacion_comercial
	 */
	public abstract void setOperacion_comercial(
		java.lang.String newOperacion_comercial);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: armario
	 */
	public abstract java.lang.String getArmario();
	/**
	 * Set accessor for persistent attribute: armario
	 */
	public abstract void setArmario(java.lang.String newArmario);
	/**
	 * Get accessor for persistent attribute: punto_venta
	 */
	public abstract java.lang.String getPunto_venta();
	/**
	 * Set accessor for persistent attribute: punto_venta
	 */
	public abstract void setPunto_venta(java.lang.String newPunto_venta);
	/**
	 * Get accessor for persistent attribute: tica
	 */
	public abstract java.lang.String getTica();
	/**
	 * Set accessor for persistent attribute: tica
	 */
	public abstract void setTica(java.lang.String newTica);
	/**
	 * Get accessor for persistent attribute: dias_habiles
	 */
	public abstract java.lang.String getDias_habiles();
	/**
	 * Set accessor for persistent attribute: dias_habiles
	 */
	public abstract void setDias_habiles(java.lang.String newDias_habiles);
	/**
	 * Get accessor for persistent attribute: tiempo_minimo
	 */
	public abstract java.lang.Integer getTiempo_minimo();
	/**
	 * Set accessor for persistent attribute: tiempo_minimo
	 */
	public abstract void setTiempo_minimo(java.lang.Integer newTiempo_minimo);
	/**
	 * Get accessor for persistent attribute: tiempo_maximo
	 */
	public abstract java.lang.Integer getTiempo_maximo();
	/**
	 * Set accessor for persistent attribute: tiempo_maximo
	 */
	public abstract void setTiempo_maximo(java.lang.Integer newTiempo_maximo);
}
