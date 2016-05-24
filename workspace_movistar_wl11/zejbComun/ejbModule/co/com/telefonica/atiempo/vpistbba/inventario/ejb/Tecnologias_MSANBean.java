package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Bean implementation class for Enterprise Bean: Tecnologias_MSAN
 */
public abstract class Tecnologias_MSANBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANKey ejbCreate(
		java.lang.String equipo,
		java.lang.String tecnologia) throws javax.ejb.CreateException {
		setEquipo(equipo);
		setTecnologia(tecnologia);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String equipo,
		java.lang.String tecnologia) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: equipo
	 */
	public abstract java.lang.String getEquipo();
	/**
	 * Set accessor for persistent attribute: equipo
	 */
	public abstract void setEquipo(java.lang.String newEquipo);
	/**
	 * Get accessor for persistent attribute: tecnologia
	 */
	public abstract java.lang.String getTecnologia();
	/**
	 * Set accessor for persistent attribute: tecnologia
	 */
	public abstract void setTecnologia(java.lang.String newTecnologia);
	/**
	 * Get accessor for persistent attribute: threeparty
	 */
	public abstract java.lang.String getThreeparty();
	/**
	 * Set accessor for persistent attribute: threeparty
	 */
	public abstract void setThreeparty(java.lang.String newThreeparty);
	/**
	 * Get accessor for persistent attribute: cw
	 */
	public abstract java.lang.String getCw();
	/**
	 * Set accessor for persistent attribute: cw
	 */
	public abstract void setCw(java.lang.String newCw);
	/**
	 * Get accessor for persistent attribute: ct
	 */
	public abstract java.lang.String getCt();
	/**
	 * Set accessor for persistent attribute: ct
	 */
	public abstract void setCt(java.lang.String newCt);
	/**
	 * Get accessor for persistent attribute: dc
	 */
	public abstract java.lang.String getDc();
	/**
	 * Set accessor for persistent attribute: dc
	 */
	public abstract void setDc(java.lang.String newDc);
	/**
	 * Get accessor for persistent attribute: callhold
	 */
	public abstract java.lang.String getCallhold();
	/**
	 * Set accessor for persistent attribute: callhold
	 */
	public abstract void setCallhold(java.lang.String newCallhold);
	/**
	 * Get accessor for persistent attribute: conf
	 */
	public abstract java.lang.String getConf();
	/**
	 * Set accessor for persistent attribute: conf
	 */
	public abstract void setConf(java.lang.String newConf);
}