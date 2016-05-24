package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_peticion_st
 */
public abstract class Tipo_peticion_stBean implements javax.ejb.EntityBean {
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
		.soltec
		.ejb
		.eb
		.Tipo_peticion_stKey ejbCreate(
		java.lang.String id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String id)
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
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.String getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.String newId);
	/**
	 * Get accessor for persistent attribute: tipo_averia
	 */
	public abstract java.lang.String getTipo_averia();
	/**
	 * Set accessor for persistent attribute: tipo_averia
	 */
	public abstract void setTipo_averia(java.lang.String newTipo_averia);
	/**
	 * Get accessor for persistent attribute: cod_ps_averia
	 */
	public abstract java.lang.Long getCod_ps_averia();
	/**
	 * Set accessor for persistent attribute: cod_ps_averia
	 */
	public abstract void setCod_ps_averia(java.lang.Long newCod_ps_averia);
}
