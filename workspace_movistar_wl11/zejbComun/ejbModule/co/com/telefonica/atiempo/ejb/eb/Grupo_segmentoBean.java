package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Grupo_segmento
 */
public abstract class Grupo_segmentoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey ejbCreate(
		java.lang.Integer grse_id)
		throws javax.ejb.CreateException {
		setGrse_id(grse_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer grse_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey ejbCreate(
		java.lang.Integer grse_id,
		java.lang.String grse_nombre)
		throws javax.ejb.CreateException {
		setGrse_id(grse_id);
		setGrse_nombre(grse_nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer grse_id,
		java.lang.String grse_nombre)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: grse_nombre
	 */
	public abstract java.lang.String getGrse_nombre();
	/**
	 * Set accessor for persistent attribute: grse_nombre
	 */
	public abstract void setGrse_nombre(java.lang.String newGrse_nombre);
	/**
	 * Get accessor for persistent attribute: grse_porcentaje
	 */
	public abstract java.lang.Integer getGrse_porcentaje();
	/**
	 * Set accessor for persistent attribute: grse_porcentaje
	 */
	public abstract void setGrse_porcentaje(
		java.lang.Integer newGrse_porcentaje);
	/**
	 * Get accessor for persistent attribute: grse_porcentaje_minimo
	 */
	public abstract java.lang.Integer getGrse_porcentaje_minimo();
	/**
	 * Set accessor for persistent attribute: grse_porcentaje_minimo
	 */
	public abstract void setGrse_porcentaje_minimo(
		java.lang.Integer newGrse_porcentaje_minimo);
}
