package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Flujo_cambio_producto
 */
public interface Flujo_cambio_productoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: prse_id_orig
	 */
	public java.lang.Long getPrse_id_orig();
	/**
	 * Set accessor for persistent attribute: prse_id_orig
	 */
	public void setPrse_id_orig(java.lang.Long newPrse_id_orig);
	/**
	 * Get accessor for persistent attribute: prse_id_dest
	 */
	public java.lang.Long getPrse_id_dest();
	/**
	 * Set accessor for persistent attribute: prse_id_dest
	 */
	public void setPrse_id_dest(java.lang.Long newPrse_id_dest);
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.FlujoLocal getFlujo();
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo(co.com.telefonica.atiempo.ejb.eb.FlujoLocal aFlujo);
}
