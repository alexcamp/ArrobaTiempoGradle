package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Tutor_web
 */
public abstract class Tutor_webBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey ejbCreate(
		java.lang.Long peti_numero,
		java.lang.Integer correlativo) throws javax.ejb.CreateException {
		setPeti_numero(peti_numero);
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long peti_numero,
		java.lang.Integer correlativo) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: license_code
	 */
	public abstract java.lang.String getLicense_code();
	/**
	 * Set accessor for persistent attribute: license_code
	 */
	public abstract void setLicense_code(java.lang.String newLicense_code);
	/**
	 * Get accessor for persistent attribute: cancelation_date
	 */
	public abstract java.lang.String getCancelation_date();
	/**
	 * Set accessor for persistent attribute: cancelation_date
	 */
	public abstract void setCancelation_date(
		java.lang.String newCancelation_date);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Integer getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Integer newCorrelativo);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Tutor_webKey ejbCreate(
		java.lang.Integer correlativo,
		java.lang.Long peti_numero) throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		setPeti_numero(peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer correlativo,
		java.lang.Long peti_numero) throws javax.ejb.CreateException {
	}
}