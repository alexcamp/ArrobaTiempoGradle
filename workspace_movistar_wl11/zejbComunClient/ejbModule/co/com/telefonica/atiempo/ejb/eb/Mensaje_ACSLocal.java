package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_ACS
 */
public interface Mensaje_ACSLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: xml
	 */
	public java.lang.String getXml();
	/**
	 * Set accessor for persistent attribute: xml
	 */
	public void setXml(java.lang.String newXml);
	/**
	 * Set accessor for persistent attribute: id
	 */
	public void setId(java.lang.Long newId);
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
}
