package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Wf_instancia_actividad
 */
public interface Wf_instancia_actividadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
	/**
	 * Get accessor for persistent attribute: id_proceso
	 */
	public java.lang.String getId_proceso();
	/**
	 * Set accessor for persistent attribute: id_proceso
	 */
	public void setId_proceso(java.lang.String newId_proceso);
	/**
	 * Get accessor for persistent attribute: id_instancia_proceso
	 */
	public java.lang.Long getId_instancia_proceso();
	/**
	 * Set accessor for persistent attribute: id_instancia_proceso
	 */
	public void setId_instancia_proceso(java.lang.Long newId_instancia_proceso);
	/**
	 * Get accessor for persistent attribute: codigo_actividad
	 */
	public java.lang.String getCodigo_actividad();
	/**
	 * Set accessor for persistent attribute: codigo_actividad
	 */
	public void setCodigo_actividad(java.lang.String newCodigo_actividad);
	/**
	 * Get accessor for persistent attribute: xmlparams
	 */
	public java.lang.String getXmlparams();
	/**
	 * Set accessor for persistent attribute: xmlparams
	 */
	public void setXmlparams(java.lang.String newXmlparams);
	/**
	 * Get accessor for persistent attribute: fecha_activacion
	 */
	public java.sql.Timestamp getFecha_activacion();
	/**
	 * Set accessor for persistent attribute: fecha_activacion
	 */
	public void setFecha_activacion(java.sql.Timestamp newFecha_activacion);
}
