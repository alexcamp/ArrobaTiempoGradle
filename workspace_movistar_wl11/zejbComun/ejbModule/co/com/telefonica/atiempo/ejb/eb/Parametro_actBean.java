package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Parametro_act
 */
public abstract class Parametro_actBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Parametro_actKey ejbCreate(
		java.lang.Long act_cod_cd) throws javax.ejb.CreateException {
		setAct_cod_cd(act_cod_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long act_cod_cd)
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
	 * Get accessor for persistent attribute: act_cod_cd
	 */
	public abstract java.lang.Long getAct_cod_cd();
	/**
	 * Set accessor for persistent attribute: act_cod_cd
	 */
	public abstract void setAct_cod_cd(java.lang.Long newAct_cod_cd);
	/**
	 * Get accessor for persistent attribute: act_descripcion
	 */
	public abstract java.lang.String getAct_descripcion();
	/**
	 * Set accessor for persistent attribute: act_descripcion
	 */
	public abstract void setAct_descripcion(java.lang.String newAct_descripcion);
	/**
	 * Get accessor for persistent attribute: act_tipo_actuacion
	 */
	public abstract java.lang.String getAct_tipo_actuacion();
	/**
	 * Set accessor for persistent attribute: act_tipo_actuacion
	 */
	public abstract void setAct_tipo_actuacion(
		java.lang.String newAct_tipo_actuacion);
	/**
	 * Get accessor for persistent attribute: act_prioridad
	 */
	public abstract java.lang.Long getAct_prioridad();
	/**
	 * Set accessor for persistent attribute: act_prioridad
	 */
	public abstract void setAct_prioridad(java.lang.Long newAct_prioridad);
}