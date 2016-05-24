package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_SMS_ACS
 */
public interface Mensaje_SMS_ACSLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id
	 */
	public java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public void setId(java.lang.Long newId);
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
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.sql.Timestamp getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.sql.Timestamp newFecha_envio);
	/**
	 * Get accessor for persistent attribute: usuario
	 */
	public java.lang.String getUsuario();
	/**
	 * Set accessor for persistent attribute: usuario
	 */
	public void setUsuario(java.lang.String newUsuario);
}
