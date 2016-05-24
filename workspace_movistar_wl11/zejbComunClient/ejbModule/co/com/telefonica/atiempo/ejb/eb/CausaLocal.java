package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Causa
 */
public interface CausaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: caus_codigo
	 */
	public java.lang.String getCaus_codigo();
	/**
	 * Set accessor for persistent attribute: caus_codigo
	 */
	public void setCaus_codigo(java.lang.String newCaus_codigo);
	/**
	 * Get accessor for persistent attribute: caus_nombre
	 */
	public java.lang.String getCaus_nombre();
	/**
	 * Set accessor for persistent attribute: caus_nombre
	 */
	public void setCaus_nombre(java.lang.String newCaus_nombre);
	/**
	 * Get accessor for persistent attribute: caus_codigo_ivr
	 */
	public java.lang.String getCaus_codigo_ivr();
	/**
	 * Set accessor for persistent attribute: caus_codigo_ivr
	 */
	public void setCaus_codigo_ivr(java.lang.String newCaus_codigo_ivr);
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausa_cierre();
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausa_cierre(java.util.Collection aCausa_cierre);
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBitacora_peticion();
	/**
	 * This method was generated for supporting the relationship role named bitacora_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBitacora_peticion(java.util.Collection aBitacora_peticion);
}
