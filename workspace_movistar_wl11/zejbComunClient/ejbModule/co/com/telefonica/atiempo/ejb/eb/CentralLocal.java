package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Central
 */
public interface CentralLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: cod_depto
	 */
	public java.lang.String getCod_depto();
	/**
	 * Set accessor for persistent attribute: cod_depto
	 */
	public void setCod_depto(java.lang.String newCod_depto);
	/**
	 * Get accessor for persistent attribute: cod_localidad
	 */
	public java.lang.String getCod_localidad();
	/**
	 * Set accessor for persistent attribute: cod_localidad
	 */
	public void setCod_localidad(java.lang.String newCod_localidad);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * Get accessor for persistent attribute: central_eoc_apsc
	 */
	public java.lang.Long getCentral_eoc_apsc();
	/**
	 * Set accessor for persistent attribute: central_eoc_apsc
	 */
	public void setCentral_eoc_apsc(java.lang.Long newCentral_eoc_apsc);
}
