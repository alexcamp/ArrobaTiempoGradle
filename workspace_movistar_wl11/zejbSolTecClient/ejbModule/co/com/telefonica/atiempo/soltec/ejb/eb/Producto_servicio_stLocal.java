package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Producto_servicio_st
 */
public interface Producto_servicio_stLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ps_nombre
	 */
	public java.lang.String getPs_nombre();
	/**
	 * Set accessor for persistent attribute: ps_nombre
	 */
	public void setPs_nombre(java.lang.String newPs_nombre);
	/**
	 * Get accessor for persistent attribute: ps_observacion
	 */
	public java.lang.String getPs_observacion();
	/**
	 * Set accessor for persistent attribute: ps_observacion
	 */
	public void setPs_observacion(java.lang.String newPs_observacion);
	/**
	 * Get accessor for persistent attribute: ps_vigente
	 */
	public java.lang.Short getPs_vigente();
	/**
	 * Set accessor for persistent attribute: ps_vigente
	 */
	public void setPs_vigente(java.lang.Short newPs_vigente);
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getFlujo_prod_serv_oper_com();
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo_prod_serv_oper_com(
		java.util.Collection aFlujo_prod_serv_oper_com);
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion_flujo();
	/**
	 * This method was generated for supporting the relationship role named peticion_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_flujo(java.util.Collection aPeticion_flujo);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getProducto_servicio_peticion();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setProducto_servicio_peticion(
		java.util.Collection aProducto_servicio_peticion);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal getFamilia_producto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
	/**
	 * Get accessor for persistent attribute: fa_ps
	 */
	public java.lang.String getFa_ps();
	/**
	 * Set accessor for persistent attribute: fa_ps
	 */
	public void setFa_ps(java.lang.String newFa_ps);
}
