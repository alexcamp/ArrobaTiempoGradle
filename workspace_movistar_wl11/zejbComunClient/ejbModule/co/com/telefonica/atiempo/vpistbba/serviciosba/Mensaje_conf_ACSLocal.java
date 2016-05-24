package co.com.telefonica.atiempo.vpistbba.serviciosba;
/**
 * Local interface for Enterprise Bean: Mensaje_conf_ACS
 */
public interface Mensaje_conf_ACSLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: xml
	 */
	public java.lang.String getXml();
	/**
	 * Set accessor for persistent attribute: xml
	 */
	public void setXml(java.lang.String newXml);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: fecha_ingreso
	 */
	public java.sql.Timestamp getFecha_ingreso();
	/**
	 * Set accessor for persistent attribute: fecha_ingreso
	 */
	public void setFecha_ingreso(java.sql.Timestamp newFecha_ingreso);
	/**
	 * Get accessor for persistent attribute: serial_modem
	 */
	public java.lang.String getSerial_modem();
	/**
	 * Set accessor for persistent attribute: serial_modem
	 */
	public void setSerial_modem(java.lang.String newSerial_modem);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.String newAccion);
}