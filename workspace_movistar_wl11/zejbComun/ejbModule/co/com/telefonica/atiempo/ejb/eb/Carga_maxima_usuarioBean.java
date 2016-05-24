package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Carga_maxima_usuario
 */
public abstract class Carga_maxima_usuarioBean
	implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioKey ejbCreate(
		java.lang.Long cmu_id)
		throws javax.ejb.CreateException {
		setCmu_id(cmu_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cmu_id)
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
	public co.com.telefonica.atiempo.ejb.eb.Carga_maxima_usuarioKey ejbCreate(
		java.lang.Long cmu_id,
		java.lang.Long usua_id)
		throws javax.ejb.CreateException {
		setCmu_id(cmu_id);
		setUsua_id(usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cmu_id, java.lang.Long usua_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: cmu_id
	 */
	public abstract java.lang.Long getCmu_id();
	/**
	 * Set accessor for persistent attribute: cmu_id
	 */
	public abstract void setCmu_id(java.lang.Long newCmu_id);
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: dia_generico
	 */
	public abstract java.lang.String getDia_generico();
	/**
	 * Set accessor for persistent attribute: dia_generico
	 */
	public abstract void setDia_generico(java.lang.String newDia_generico);
	/**
	 * Get accessor for persistent attribute: carga_maxima
	 */
	public abstract java.lang.Integer getCarga_maxima();
	/**
	 * Set accessor for persistent attribute: carga_maxima
	 */
	public abstract void setCarga_maxima(java.lang.Integer newCarga_maxima);
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
}
