package co.com.telefonica.atiempo.soltec.ejb.eb;
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
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
}
