package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Menu
 */
public abstract class MenuBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.MenuKey ejbCreate(
		java.lang.Long mn_id)
		throws javax.ejb.CreateException {
		setMn_id(mn_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long mn_id)
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
	 * Get accessor for persistent attribute: mn_id
	 */
	public abstract java.lang.Long getMn_id();
	/**
	 * Set accessor for persistent attribute: mn_id
	 */
	public abstract void setMn_id(java.lang.Long newMn_id);
	/**
	 * Get accessor for persistent attribute: mn_descripcion
	 */
	public abstract java.lang.String getMn_descripcion();
	/**
	 * Set accessor for persistent attribute: mn_descripcion
	 */
	public abstract void setMn_descripcion(java.lang.String newMn_descripcion);
	/**
	 * Get accessor for persistent attribute: mn_url
	 */
	public abstract java.lang.String getMn_url();
	/**
	 * Set accessor for persistent attribute: mn_url
	 */
	public abstract void setMn_url(java.lang.String newMn_url);
	/**
	 * Get accessor for persistent attribute: mn_id_padre
	 */
	public abstract java.lang.Long getMn_id_padre();
	/**
	 * Set accessor for persistent attribute: mn_id_padre
	 */
	public abstract void setMn_id_padre(java.lang.Long newMn_id_padre);
	/**
	 * Get accessor for persistent attribute: mn_orden
	 */
	public abstract java.lang.Integer getMn_orden();
	/**
	 * Set accessor for persistent attribute: mn_orden
	 */
	public abstract void setMn_orden(java.lang.Integer newMn_orden);
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMenu_perfil();
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMenu_perfil(java.util.Collection aMenu_perfil);
}
