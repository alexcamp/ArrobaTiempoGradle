package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Elemento_agenda_sc
 */
public abstract class Elemento_agenda_scBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Elemento_agenda_scKey ejbCreate(
		java.lang.Integer id_correlativo) throws javax.ejb.CreateException {
		setId_correlativo(id_correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id_correlativo)
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
	 * Get accessor for persistent attribute: id_correlativo
	 */
	public abstract java.lang.Integer getId_correlativo();
	/**
	 * Set accessor for persistent attribute: id_correlativo
	 */
	public abstract void setId_correlativo(java.lang.Integer newId_correlativo);
	/**
	 * Get accessor for persistent attribute: desc_equipo
	 */
	public abstract java.lang.String getDesc_equipo();
	/**
	 * Set accessor for persistent attribute: desc_equipo
	 */
	public abstract void setDesc_equipo(java.lang.String newDesc_equipo);
	/**
	 * Get accessor for persistent attribute: id_elemento_atiempo
	 */
	public abstract java.lang.Integer getId_elemento_atiempo();
	/**
	 * Set accessor for persistent attribute: id_elemento_atiempo
	 */
	public abstract void setId_elemento_atiempo(
		java.lang.Integer newId_elemento_atiempo);
}