package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Respuesta_conect7_apsc
 */
public abstract class Respuesta_conect7_apscBean
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
		.Respuesta_conect7_apscKey ejbCreate(
		java.lang.Long id_conector)
		throws javax.ejb.CreateException {
		setId_conector(id_conector);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_conector)
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
	 * Get accessor for persistent attribute: id_conector
	 */
	public abstract java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public abstract void setId_conector(java.lang.Long newId_conector);
	/**
	 * Get accessor for persistent attribute: ind_error
	 */
	public abstract java.lang.String getInd_error();
	/**
	 * Set accessor for persistent attribute: ind_error
	 */
	public abstract void setInd_error(java.lang.String newInd_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public abstract java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public abstract void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * This method was generated for supporting the relationship role named f_fk_01_respuesta_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal getF_fk_01_respuesta_();
	/**
	 * This method was generated for supporting the relationship role named f_fk_01_respuesta_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_fk_01_respuesta_(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aF_fk_01_respuesta_);
}
