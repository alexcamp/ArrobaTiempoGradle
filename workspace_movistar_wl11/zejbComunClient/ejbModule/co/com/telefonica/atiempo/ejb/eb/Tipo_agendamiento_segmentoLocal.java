package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tipo_agendamiento_segmento
 */
public interface Tipo_agendamiento_segmentoLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: tiag_id
	 */
	public java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: oper_comercial
	 */
	public java.lang.String getOper_comercial();
	/**
	 * Set accessor for persistent attribute: oper_comercial
	 */
	public void setOper_comercial(java.lang.String newOper_comercial);
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
