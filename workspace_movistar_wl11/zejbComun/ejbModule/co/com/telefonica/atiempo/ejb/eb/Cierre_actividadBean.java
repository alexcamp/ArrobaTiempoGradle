package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Cierre_actividad
 */
public abstract class Cierre_actividadBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey ejbCreate(
		java.lang.Long ciac_id)
		throws javax.ejb.CreateException {
		setCiac_id(ciac_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ciac_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Cierre_actividadKey ejbCreate(
		java.lang.Long ciac_id,
		java.lang.String ciac_nombre,
		java.lang.String ciac_codigo,
		java.lang.Short ciac_reversa,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argFk_cierre_act)
		throws javax.ejb.CreateException {
		setCiac_id(ciac_id);
		setCiac_nombre(ciac_nombre);
		setCiac_codigo(ciac_codigo);
		setCiac_reversa(ciac_reversa);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long ciac_id,
		java.lang.String ciac_nombre,
		java.lang.String ciac_codigo,
		java.lang.Short ciac_reversa,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argFk_cierre_act)
		throws javax.ejb.CreateException {
		setFk_cierre_act(argFk_cierre_act);
	}
	/**
	 * Get accessor for persistent attribute: ciac_id
	 */
	public abstract java.lang.Long getCiac_id();
	/**
	 * Set accessor for persistent attribute: ciac_id
	 */
	public abstract void setCiac_id(java.lang.Long newCiac_id);
	/**
	 * Get accessor for persistent attribute: ciac_nombre
	 */
	public abstract java.lang.String getCiac_nombre();
	/**
	 * Set accessor for persistent attribute: ciac_nombre
	 */
	public abstract void setCiac_nombre(java.lang.String newCiac_nombre);
	/**
	 * Get accessor for persistent attribute: ciac_codigo
	 */
	public abstract java.lang.String getCiac_codigo();
	/**
	 * Set accessor for persistent attribute: ciac_codigo
	 */
	public abstract void setCiac_codigo(java.lang.String newCiac_codigo);
	/**
	 * Get accessor for persistent attribute: ciac_reversa
	 */
	public abstract java.lang.Short getCiac_reversa();
	/**
	 * Set accessor for persistent attribute: ciac_reversa
	 */
	public abstract void setCiac_reversa(java.lang.Short newCiac_reversa);
	/**
	 * Get accessor for persistent attribute: ciac_valor
	 */
	public abstract java.lang.String getCiac_valor();
	/**
	 * Set accessor for persistent attribute: ciac_valor
	 */
	public abstract void setCiac_valor(java.lang.String newCiac_valor);
	/**
	 * Get accessor for persistent attribute: ciac_termino
	 */
	public abstract java.lang.String getCiac_termino();
	/**
	 * Set accessor for persistent attribute: ciac_termino
	 */
	public abstract void setCiac_termino(java.lang.String newCiac_termino);
	/**
	 * This method was generated for supporting the relationship role named fk_cierre_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ActividadLocal getFk_cierre_act();
	/**
	 * This method was generated for supporting the relationship role named fk_cierre_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_cierre_act(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal aFk_cierre_act);
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausa_cierre();
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausa_cierre(java.util.Collection aCausa_cierre);
}
