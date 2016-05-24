package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Ocupacion_rango
 */
public interface Ocupacion_rangoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: codigo_familia_ps
	 */
	public java.lang.Long getCodigo_familia_ps();
	/**
	 * Set accessor for persistent attribute: codigo_familia_ps
	 */
	public void setCodigo_familia_ps(java.lang.Long newCodigo_familia_ps);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: dia_especifico
	 */
	public java.sql.Timestamp getDia_especifico();
	/**
	 * Set accessor for persistent attribute: dia_especifico
	 */
	public void setDia_especifico(java.sql.Timestamp newDia_especifico);
	/**
	 * Get accessor for persistent attribute: cantidad_ocupada
	 */
	public java.lang.Integer getCantidad_ocupada();
	/**
	 * Set accessor for persistent attribute: cantidad_ocupada
	 */
	public void setCantidad_ocupada(java.lang.Integer newCantidad_ocupada);
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
}
