package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Aplicacion
 */
public abstract class AplicacionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.AplicacionKey ejbCreate(
		java.lang.Long ap_id)
		throws javax.ejb.CreateException {
		setAp_id(ap_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ap_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionKey ejbCreate(
		java.lang.Long ap_id,
		java.lang.String ap_nombre)
		throws javax.ejb.CreateException {
		setAp_id(ap_id);
		setAp_nombre(ap_nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ap_id, java.lang.String ap_nombre)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: ap_id
	 */
	public abstract java.lang.Long getAp_id();
	/**
	 * Set accessor for persistent attribute: ap_id
	 */
	public abstract void setAp_id(java.lang.Long newAp_id);
	/**
	 * Get accessor for persistent attribute: ap_nombre
	 */
	public abstract java.lang.String getAp_nombre();
	/**
	 * Set accessor for persistent attribute: ap_nombre
	 */
	public abstract void setAp_nombre(java.lang.String newAp_nombre);
	/**
	 * Get accessor for persistent attribute: ap_url_reasignacion
	 */
	public abstract java.lang.String getAp_url_reasignacion();
	/**
	 * Set accessor for persistent attribute: ap_url_reasignacion
	 */
	public abstract void setAp_url_reasignacion(
		java.lang.String newAp_url_reasignacion);
	/**
	 * Get accessor for persistent attribute: ap_url_base
	 */
	public abstract java.lang.String getAp_url_base();
	/**
	 * Set accessor for persistent attribute: ap_url_base
	 */
	public abstract void setAp_url_base(java.lang.String newAp_url_base);
	/**
	 * Get accessor for persistent attribute: ap_url_supervisor
	 */
	public abstract java.lang.String getAp_url_supervisor();
	/**
	 * Set accessor for persistent attribute: ap_url_supervisor
	 */
	public abstract void setAp_url_supervisor(
		java.lang.String newAp_url_supervisor);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad(java.util.Collection anActividad);
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRol();
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRol(java.util.Collection aRol);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getHorario();
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setHorario(java.util.Collection aHorario);
}
