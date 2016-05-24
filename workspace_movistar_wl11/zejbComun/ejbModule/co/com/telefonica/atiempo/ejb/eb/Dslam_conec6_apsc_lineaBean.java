package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Dslam_conec6_apsc_linea
 */
public abstract class Dslam_conec6_apsc_lineaBean
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
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Dslam_conec6_apsc_lineaKey ejbCreate(
		java.lang.Long id_dslam)
		throws javax.ejb.CreateException {
		setId_dslam(id_dslam);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_dslam)
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
	 * Get accessor for persistent attribute: id_dslam
	 */
	public abstract java.lang.Long getId_dslam();
	/**
	 * Set accessor for persistent attribute: id_dslam
	 */
	public abstract void setId_dslam(java.lang.Long newId_dslam);
	/**
	 * Get accessor for persistent attribute: ip
	 */
	public abstract java.lang.String getIp();
	/**
	 * Set accessor for persistent attribute: ip
	 */
	public abstract void setIp(java.lang.String newIp);
	/**
	 * Get accessor for persistent attribute: tipo_dslam
	 */
	public abstract java.lang.String getTipo_dslam();
	/**
	 * Set accessor for persistent attribute: tipo_dslam
	 */
	public abstract void setTipo_dslam(java.lang.String newTipo_dslam);
	/**
	 * This method was generated for supporting the relationship role named f_fk_dslam_conec6_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conec6_apsc_lineaLocal getF_fk_dslam_conec6_();
	/**
	 * This method was generated for supporting the relationship role named f_fk_dslam_conec6_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_fk_dslam_conec6_(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Respuesta_conec6_apsc_lineaLocal aF_fk_dslam_conec6_);
}
