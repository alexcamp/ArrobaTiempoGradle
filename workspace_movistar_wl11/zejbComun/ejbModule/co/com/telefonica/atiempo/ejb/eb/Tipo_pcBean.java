package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tipo_pc
 */
public abstract class Tipo_pcBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Tipo_pcKey ejbCreate(
		java.lang.Integer id_tipo_pc)
		throws javax.ejb.CreateException {
		setId_tipo_pc(id_tipo_pc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id_tipo_pc)
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
	 * Get accessor for persistent attribute: id_tipo_pc
	 */
	public abstract java.lang.Integer getId_tipo_pc();
	/**
	 * Set accessor for persistent attribute: id_tipo_pc
	 */
	public abstract void setId_tipo_pc(java.lang.Integer newId_tipo_pc);
	/**
	 * Get accessor for persistent attribute: nombre_pc
	 */
	public abstract java.lang.String getNombre_pc();
	/**
	 * Set accessor for persistent attribute: nombre_pc
	 */
	public abstract void setNombre_pc(java.lang.String newNombre_pc);
	/**
	 * Get accessor for persistent attribute: familia_pc
	 */
	public abstract java.lang.Integer getFamilia_pc();
	/**
	 * Set accessor for persistent attribute: familia_pc
	 */
	public abstract void setFamilia_pc(java.lang.Integer newFamilia_pc);
	/**
	 * This method was generated for supporting the relationship role named subtipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getSubtipo_pc();
	/**
	 * This method was generated for supporting the relationship role named subtipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setSubtipo_pc(java.util.Collection aSubtipo_pc);
}
