package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Sucesos
 */
public interface SucesosLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id
	 */
	public java.lang.Integer getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public void setId(java.lang.Integer newId);
	/**
	 * Get accessor for persistent attribute: fecha_inicio
	 */
	public java.sql.Timestamp getFecha_inicio();
	/**
	 * Set accessor for persistent attribute: fecha_inicio
	 */
	public void setFecha_inicio(java.sql.Timestamp newFecha_inicio);
	/**
	 * Get accessor for persistent attribute: fecha_fin
	 */
	public java.sql.Timestamp getFecha_fin();
	/**
	 * Set accessor for persistent attribute: fecha_fin
	 */
	public void setFecha_fin(java.sql.Timestamp newFecha_fin);
	/**
	 * Get accessor for persistent attribute: mensaje
	 */
	public java.lang.String getMensaje();
	/**
	 * Set accessor for persistent attribute: mensaje
	 */
	public void setMensaje(java.lang.String newMensaje);
}