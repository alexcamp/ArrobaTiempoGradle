package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Conector
 */
public abstract class ConectorBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ConectorKey ejbCreate(
		java.lang.Integer cod_conector)
		throws javax.ejb.CreateException {
		setCod_conector(cod_conector);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer cod_conector)
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
	 * Get accessor for persistent attribute: cod_conector
	 */
	public abstract java.lang.Integer getCod_conector();
	/**
	 * Set accessor for persistent attribute: cod_conector
	 */
	public abstract void setCod_conector(java.lang.Integer newCod_conector);
	/**
	 * Get accessor for persistent attribute: nombre_conector
	 */
	public abstract java.lang.String getNombre_conector();
	/**
	 * Set accessor for persistent attribute: nombre_conector
	 */
	public abstract void setNombre_conector(
		java.lang.String newNombre_conector);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_linea();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_linea(
		java.util.Collection aMensaje_estado_linea);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_ba();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_ba(
		java.util.Collection aMensaje_estado_ba);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMensaje_estado_tv();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado_tv(
		java.util.Collection aMensaje_estado_tv);
}
