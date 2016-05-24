package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Zonas_atendimiento
 */
public abstract class Zonas_atendimientoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Zonas_atendimientoKey ejbCreate(
		java.lang.Long id_zona_atendimiento)
		throws javax.ejb.CreateException {
		setId_zona_atendimiento(id_zona_atendimiento);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_zona_atendimiento)
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
	 * Get accessor for persistent attribute: id_zona_atendimiento
	 */
	public abstract java.lang.Long getId_zona_atendimiento();
	/**
	 * Set accessor for persistent attribute: id_zona_atendimiento
	 */
	public abstract void setId_zona_atendimiento(
		java.lang.Long newId_zona_atendimiento);
	/**
	 * Get accessor for persistent attribute: ip
	 */
	public abstract java.lang.String getIp();
	/**
	 * Set accessor for persistent attribute: ip
	 */
	public abstract void setIp(java.lang.String newIp);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
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
			.ejb
			.eb
			.Recursos_linea_basicaLocal aRecursos_linea_basica);
	/**
	 * Get accessor for persistent attribute: id_conector
	 */
	public abstract java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public abstract void setId_conector(java.lang.Long newId_conector);
}
