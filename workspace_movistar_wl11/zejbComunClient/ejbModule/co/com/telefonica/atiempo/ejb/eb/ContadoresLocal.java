package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Contadores
 */
public interface ContadoresLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: desde
	 */
	public java.lang.Short getDesde();
	/**
	 * Set accessor for persistent attribute: desde
	 */
	public void setDesde(java.lang.Short newDesde);
	/**
	 * Get accessor for persistent attribute: hasta
	 */
	public java.lang.Short getHasta();
	/**
	 * Set accessor for persistent attribute: hasta
	 */
	public void setHasta(java.lang.Short newHasta);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal getOperacion_comercial();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setOperacion_comercial(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal anOperacion_comercial);
}
