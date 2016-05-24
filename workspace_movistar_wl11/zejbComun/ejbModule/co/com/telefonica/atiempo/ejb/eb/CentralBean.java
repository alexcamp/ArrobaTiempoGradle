package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Central
 */
public abstract class CentralBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.CentralKey ejbCreate(
		java.lang.Long cod_central)
		throws javax.ejb.CreateException {
		setCod_central(cod_central);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_central)
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
	 * Get accessor for persistent attribute: cod_central
	 */
	public abstract java.lang.Long getCod_central();
	/**
	 * Set accessor for persistent attribute: cod_central
	 */
	public abstract void setCod_central(java.lang.Long newCod_central);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public abstract java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public abstract void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: cod_depto
	 */
	public abstract java.lang.String getCod_depto();
	/**
	 * Set accessor for persistent attribute: cod_depto
	 */
	public abstract void setCod_depto(java.lang.String newCod_depto);
	/**
	 * Get accessor for persistent attribute: cod_localidad
	 */
	public abstract java.lang.String getCod_localidad();
	/**
	 * Set accessor for persistent attribute: cod_localidad
	 */
	public abstract void setCod_localidad(java.lang.String newCod_localidad);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(java.util.Collection aPeticion);
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
	 * Get accessor for persistent attribute: central_eoc_apsc
	 */
	public abstract java.lang.Long getCentral_eoc_apsc();
	/**
	 * Set accessor for persistent attribute: central_eoc_apsc
	 */
	public abstract void setCentral_eoc_apsc(java.lang.Long newCentral_eoc_apsc);
}
