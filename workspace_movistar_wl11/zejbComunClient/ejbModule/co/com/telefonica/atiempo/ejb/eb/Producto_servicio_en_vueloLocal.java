package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Producto_servicio_en_vuelo
 */
public interface Producto_servicio_en_vueloLocal
	extends
		javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: pspe_cantidad
	 */
	public java.lang.Integer getPspe_cantidad();
	/**
	 * Set accessor for persistent attribute: pspe_cantidad
	 */
	public void setPspe_cantidad(java.lang.Integer newPspe_cantidad);
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public java.lang.Integer getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public void setOpco_id(java.lang.Integer newOpco_id);
	/**
	 * Get accessor for persistent attribute: tipo_peticion
	 */
	public java.lang.String getTipo_peticion();
	/**
	 * Set accessor for persistent attribute: tipo_peticion
	 */
	public void setTipo_peticion(java.lang.String newTipo_peticion);
	/**
	 * Get accessor for persistent attribute: ps_id_ant
	 */
	public java.lang.String getPs_id_ant();
	/**
	 * Set accessor for persistent attribute: ps_id_ant
	 */
	public void setPs_id_ant(java.lang.String newPs_id_ant);
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public void setCorrelativo(java.lang.Long newCorrelativo);
}