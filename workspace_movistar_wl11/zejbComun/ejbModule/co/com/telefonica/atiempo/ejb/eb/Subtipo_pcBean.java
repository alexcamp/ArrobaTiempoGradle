package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Subtipo_pc
 */
public abstract class Subtipo_pcBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Subtipo_pcKey ejbCreate(
		java.lang.Integer id_subtipo_pc)
		throws javax.ejb.CreateException {
		setId_subtipo_pc(id_subtipo_pc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id_subtipo_pc)
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
	 * Get accessor for persistent attribute: id_subtipo_pc
	 */
	public abstract java.lang.Integer getId_subtipo_pc();
	/**
	 * Set accessor for persistent attribute: id_subtipo_pc
	 */
	public abstract void setId_subtipo_pc(java.lang.Integer newId_subtipo_pc);
	/**
	 * Get accessor for persistent attribute: nombre_subtipo
	 */
	public abstract java.lang.String getNombre_subtipo();
	/**
	 * Set accessor for persistent attribute: nombre_subtipo
	 */
	public abstract void setNombre_subtipo(java.lang.String newNombre_subtipo);
	/**
	 * Get accessor for persistent attribute: descripcion_subtipo
	 */
	public abstract java.lang.String getDescripcion_subtipo();
	/**
	 * Set accessor for persistent attribute: descripcion_subtipo
	 */
	public abstract void setDescripcion_subtipo(
		java.lang.String newDescripcion_subtipo);
	/**
	 * This method was generated for supporting the relationship role named tipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal getTipo_pc();
	/**
	 * This method was generated for supporting the relationship role named tipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTipo_pc(
		co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal aTipo_pc);
}
