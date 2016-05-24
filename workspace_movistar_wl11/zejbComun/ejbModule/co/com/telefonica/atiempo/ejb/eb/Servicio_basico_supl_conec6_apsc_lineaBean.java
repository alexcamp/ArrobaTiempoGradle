package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Servicio_basico_supl_conec6_apsc_linea
 */
public abstract class Servicio_basico_supl_conec6_apsc_lineaBean
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
		.Servicio_basico_supl_conec6_apsc_lineaKey ejbCreate(
			java.lang.Long id_servicio_basico)
		throws javax.ejb.CreateException {
		setId_servicio_basico(id_servicio_basico);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_servicio_basico)
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
	 * Get accessor for persistent attribute: id_servicio_basico
	 */
	public abstract java.lang.Long getId_servicio_basico();
	/**
	 * Set accessor for persistent attribute: id_servicio_basico
	 */
	public abstract void setId_servicio_basico(
		java.lang.Long newId_servicio_basico);
	/**
	 * Get accessor for persistent attribute: codigo_ps
	 */
	public abstract java.lang.String getCodigo_ps();
	/**
	 * Set accessor for persistent attribute: codigo_ps
	 */
	public abstract void setCodigo_ps(java.lang.String newCodigo_ps);
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
}
