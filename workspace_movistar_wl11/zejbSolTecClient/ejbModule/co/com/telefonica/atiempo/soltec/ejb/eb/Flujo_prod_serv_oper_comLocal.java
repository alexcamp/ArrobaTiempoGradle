package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Flujo_prod_serv_oper_com
 */
public interface Flujo_prod_serv_oper_comLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public java.lang.Integer getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public void setOpco_id(java.lang.Integer newOpco_id);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Producto_servicio_stLocal aProducto_servicio_st);
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal getFlujo();
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo(
		co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal aFlujo);
}
