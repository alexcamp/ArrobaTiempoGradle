package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Excepcion_tipo_agendamiento
 */
public interface Excepcion_tipo_agendamientoLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: tiag_id
	 */
	public java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public void setCodigo_agencia(java.lang.String newCodigo_agencia);
	/**
	 * Get accessor for persistent attribute: codigo_familia_ps
	 */
	public java.lang.Long getCodigo_familia_ps();
	/**
	 * Set accessor for persistent attribute: codigo_familia_ps
	 */
	public void setCodigo_familia_ps(java.lang.Long newCodigo_familia_ps);
	/**
	 * Get accessor for persistent attribute: operacion_comercial
	 */
	public java.lang.String getOperacion_comercial();
	/**
	 * Set accessor for persistent attribute: operacion_comercial
	 */
	public void setOperacion_comercial(java.lang.String newOperacion_comercial);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: armario
	 */
	public java.lang.String getArmario();
	/**
	 * Set accessor for persistent attribute: armario
	 */
	public void setArmario(java.lang.String newArmario);
	/**
	 * Get accessor for persistent attribute: punto_venta
	 */
	public java.lang.String getPunto_venta();
	/**
	 * Set accessor for persistent attribute: punto_venta
	 */
	public void setPunto_venta(java.lang.String newPunto_venta);
	/**
	 * Get accessor for persistent attribute: tica
	 */
	public java.lang.String getTica();
	/**
	 * Set accessor for persistent attribute: tica
	 */
	public void setTica(java.lang.String newTica);
	/**
	 * Get accessor for persistent attribute: dias_habiles
	 */
	public java.lang.String getDias_habiles();
	/**
	 * Set accessor for persistent attribute: dias_habiles
	 */
	public void setDias_habiles(java.lang.String newDias_habiles);
	/**
	 * Get accessor for persistent attribute: tiempo_minimo
	 */
	public java.lang.Integer getTiempo_minimo();
	/**
	 * Set accessor for persistent attribute: tiempo_minimo
	 */
	public void setTiempo_minimo(java.lang.Integer newTiempo_minimo);
	/**
	 * Get accessor for persistent attribute: tiempo_maximo
	 */
	public java.lang.Integer getTiempo_maximo();
	/**
	 * Set accessor for persistent attribute: tiempo_maximo
	 */
	public void setTiempo_maximo(java.lang.Integer newTiempo_maximo);
}
