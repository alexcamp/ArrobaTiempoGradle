package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local interface for Enterprise Bean: Activaciones_tv_TOA
 */
public interface Activaciones_tv_TOALocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_actuacion
	 */
	public java.lang.String getId_actuacion();
	/**
	 * Set accessor for persistent attribute: id_actuacion
	 */
	public void setId_actuacion(java.lang.String newId_actuacion);
	/**
	 * Get accessor for persistent attribute: cod_appt_number
	 */
	public java.lang.String getCod_appt_number();
	/**
	 * Set accessor for persistent attribute: cod_appt_number
	 */
	public void setCod_appt_number(java.lang.String newCod_appt_number);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.sql.Timestamp getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.sql.Timestamp newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public java.sql.Timestamp getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public void setFecha_cierre(java.sql.Timestamp newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.String getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.String newEstado);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public void setReintentos(java.lang.Long newReintentos);
}