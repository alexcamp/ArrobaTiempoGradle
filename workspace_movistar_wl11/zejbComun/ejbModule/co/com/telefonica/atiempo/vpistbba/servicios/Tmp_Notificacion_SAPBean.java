package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Tmp_Notificacion_SAP
 */
public abstract class Tmp_Notificacion_SAPBean implements javax.ejb.EntityBean {
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
	public java.lang.Long ejbCreate(java.lang.Long id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id)
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
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Long newId);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: cod_pet_cd
	 */
	public abstract java.lang.Long getCod_pet_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_cd
	 */
	public abstract void setCod_pet_cd(java.lang.Long newCod_pet_cd);
	/**
	 * Get accessor for persistent attribute: fec_ingreso
	 */
	public abstract java.sql.Timestamp getFec_ingreso();
	/**
	 * Set accessor for persistent attribute: fec_ingreso
	 */
	public abstract void setFec_ingreso(java.sql.Timestamp newFec_ingreso);
	/**
	 * Get accessor for persistent attribute: mensaje
	 */
	public abstract java.lang.String getMensaje();
	/**
	 * Set accessor for persistent attribute: mensaje
	 */
	public abstract void setMensaje(java.lang.String newMensaje);
}