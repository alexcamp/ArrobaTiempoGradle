package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tmp_modem
 */
public interface Tmp_modemLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: xml
	 */
	public java.lang.String getXml();
	/**
	 * Set accessor for persistent attribute: xml
	 */
	public void setXml(java.lang.String newXml);
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
}
