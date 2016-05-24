package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local interface for Enterprise Bean: Tutor_web
 */
public interface Tutor_webLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: license_code
	 */
	public java.lang.String getLicense_code();
	/**
	 * Set accessor for persistent attribute: license_code
	 */
	public void setLicense_code(java.lang.String newLicense_code);
	/**
	 * Get accessor for persistent attribute: cancelation_date
	 */
	public java.lang.String getCancelation_date();
	/**
	 * Set accessor for persistent attribute: cancelation_date
	 */
	public void setCancelation_date(java.lang.String newCancelation_date);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public java.lang.Integer getCorrelativo();
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
}