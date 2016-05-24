package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Dslam_apsc_linea
 */
public abstract class Dslam_apsc_lineaBean implements javax.ejb.EntityBean {
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
		.soltec
		.ejb
		.eb
		.Dslam_apsc_lineaKey ejbCreate(
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
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Dslam_apsc_lineaKey ejbCreate()
		throws javax.ejb.CreateException {
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate() throws javax.ejb.CreateException {
	}
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Recursos_linea_basicaLocal getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRecursos_linea_basica(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Recursos_linea_basicaLocal aRecursos_linea_basica);
}
