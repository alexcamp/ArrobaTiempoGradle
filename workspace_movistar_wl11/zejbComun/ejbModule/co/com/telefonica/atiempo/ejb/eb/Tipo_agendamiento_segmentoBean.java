package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_agendamiento_segmento
 */
public abstract class Tipo_agendamiento_segmentoBean
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
		.Tipo_agendamiento_segmentoKey ejbCreate(
		java.lang.Long tasg_id)
		throws javax.ejb.CreateException {
		setTasg_id(tasg_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long tasg_id)
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
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Tipo_agendamiento_segmentoKey ejbCreate(
		java.lang.Long tasg_id,
		java.lang.Integer grse_id,
		java.lang.Long tiag_id)
		throws javax.ejb.CreateException {
		setTasg_id(tasg_id);
		setGrse_id(grse_id);
		setTiag_id(tiag_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long tasg_id,
		java.lang.Integer grse_id,
		java.lang.Long tiag_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: tasg_id
	 */
	public abstract java.lang.Long getTasg_id();
	/**
	 * Set accessor for persistent attribute: tasg_id
	 */
	public abstract void setTasg_id(java.lang.Long newTasg_id);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: tiag_id
	 */
	public abstract java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public abstract void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: oper_comercial
	 */
	public abstract java.lang.String getOper_comercial();
	/**
	 * Set accessor for persistent attribute: oper_comercial
	 */
	public abstract void setOper_comercial(java.lang.String newOper_comercial);
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
