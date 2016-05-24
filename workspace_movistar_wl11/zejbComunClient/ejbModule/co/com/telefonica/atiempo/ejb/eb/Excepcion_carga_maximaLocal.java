package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Excepcion_carga_maxima
 */
public interface Excepcion_carga_maximaLocal extends javax.ejb.EJBLocalObject {
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
	 * Get accessor for persistent attribute: carga_maxima
	 */
	public java.lang.Integer getCarga_maxima();
	/**
	 * Set accessor for persistent attribute: carga_maxima
	 */
	public void setCarga_maxima(java.lang.Integer newCarga_maxima);
	/**
	 * Get accessor for persistent attribute: dia_especifico
	 */
	public java.sql.Timestamp getDia_especifico();
	/**
	 * Set accessor for persistent attribute: dia_especifico
	 */
	public void setDia_especifico(java.sql.Timestamp newDia_especifico);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public void setCodigo_agencia(java.lang.String newCodigo_agencia);
}
