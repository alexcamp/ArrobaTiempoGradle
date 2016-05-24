package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Empresa
 */
public abstract class EmpresaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.EmpresaKey ejbCreate(
		java.lang.Long empr_id)
		throws javax.ejb.CreateException {
		setEmpr_id(empr_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long empr_id)
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
	 * Get accessor for persistent attribute: empr_id
	 */
	public abstract java.lang.Long getEmpr_id();
	/**
	 * Set accessor for persistent attribute: empr_id
	 */
	public abstract void setEmpr_id(java.lang.Long newEmpr_id);
	/**
	 * Get accessor for persistent attribute: empresa_nombre
	 */
	public abstract java.lang.String getEmpresa_nombre();
	/**
	 * Set accessor for persistent attribute: empresa_nombre
	 */
	public abstract void setEmpresa_nombre(java.lang.String newEmpresa_nombre);
	/**
	 * Get accessor for persistent attribute: empresa_descripcion
	 */
	public abstract java.lang.String getEmpresa_descripcion();
	/**
	 * Set accessor for persistent attribute: empresa_descripcion
	 */
	public abstract void setEmpresa_descripcion(
		java.lang.String newEmpresa_descripcion);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUsuario(java.util.Collection anUsuario);
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTecnico();
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTecnico(java.util.Collection aTecnico);
}
