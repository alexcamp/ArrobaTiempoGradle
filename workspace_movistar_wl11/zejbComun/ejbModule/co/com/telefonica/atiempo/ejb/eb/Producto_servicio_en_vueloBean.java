package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Producto_servicio_en_vuelo
 */
public abstract class Producto_servicio_en_vueloBean
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
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicio_en_vueloKey ejbCreate(
		java.lang.Long peti_numero,
		java.lang.Long correlativo) throws javax.ejb.CreateException {
		setPeti_numero(peti_numero);
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long peti_numero,
		java.lang.Long correlativo) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: pspe_cantidad
	 */
	public abstract java.lang.Integer getPspe_cantidad();
	/**
	 * Set accessor for persistent attribute: pspe_cantidad
	 */
	public abstract void setPspe_cantidad(java.lang.Integer newPspe_cantidad);
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public abstract java.lang.Integer getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public abstract void setOpco_id(java.lang.Integer newOpco_id);
	/**
	 * Get accessor for persistent attribute: tipo_peticion
	 */
	public abstract java.lang.String getTipo_peticion();
	/**
	 * Set accessor for persistent attribute: tipo_peticion
	 */
	public abstract void setTipo_peticion(java.lang.String newTipo_peticion);
	/**
	 * Get accessor for persistent attribute: ps_id_ant
	 */
	public abstract java.lang.String getPs_id_ant();
	/**
	 * Set accessor for persistent attribute: ps_id_ant
	 */
	public abstract void setPs_id_ant(java.lang.String newPs_id_ant);
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
}