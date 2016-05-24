package ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS;
/**
 * Local interface for Enterprise Bean: CaracteristicaPS
 */
public interface CaracteristicaPSLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: caracteristicaPS
	 */
	public java.lang.String getCaracteristicaPS();
	/**
	 * Set accessor for persistent attribute: caracteristicaPS
	 */
	public void setCaracteristicaPS(java.lang.String newCaracteristicaPS);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal getProducto_servicio();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio(
		co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal aProducto_servicio);
	/**
	 * Get accessor for persistent attribute: psPeticion
	 */
	public java.lang.Long getPsPeticion();
	/**
	 * Set accessor for persistent attribute: psPeticion
	 */
	public void setPsPeticion(java.lang.Long newPsPeticion);
	/**
	 * Get accessor for persistent attribute: psId
	 */
	public java.lang.Long getPsId();
	/**
	 * Set accessor for persistent attribute: psId
	 */
	public void setPsId(java.lang.Long newPsId);
}