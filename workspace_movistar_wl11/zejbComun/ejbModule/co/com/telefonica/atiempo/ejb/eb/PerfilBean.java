package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Perfil
 */
public abstract class PerfilBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.PerfilKey ejbCreate(
		java.lang.Long perf_id)
		throws javax.ejb.CreateException {
		setPerf_id(perf_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long perf_id)
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
	 * Get accessor for persistent attribute: perf_id
	 */
	public abstract java.lang.Long getPerf_id();
	/**
	 * Set accessor for persistent attribute: perf_id
	 */
	public abstract void setPerf_id(java.lang.Long newPerf_id);
	/**
	 * Get accessor for persistent attribute: perf_descripcion
	 */
	public abstract java.lang.String getPerf_descripcion();
	/**
	 * Set accessor for persistent attribute: perf_descripcion
	 */
	public abstract void setPerf_descripcion(
		java.lang.String newPerf_descripcion);
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPerfil_usuario();
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPerfil_usuario(
		java.util.Collection aPerfil_usuario);
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
