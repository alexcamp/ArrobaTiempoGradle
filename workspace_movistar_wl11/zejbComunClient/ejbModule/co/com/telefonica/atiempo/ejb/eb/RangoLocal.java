package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Rango
 */
public interface RangoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public java.lang.Short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public void setHabilitado(java.lang.Short newHabilitado);
	/**
	 * Get accessor for persistent attribute: nombre_rango
	 */
	public java.lang.String getNombre_rango();
	/**
	 * Set accessor for persistent attribute: nombre_rango
	 */
	public void setNombre_rango(java.lang.String newNombre_rango);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: hora_desde
	 */
	public java.lang.String getHora_desde();
	/**
	 * Set accessor for persistent attribute: hora_desde
	 */
	public void setHora_desde(java.lang.String newHora_desde);
	/**
	 * Get accessor for persistent attribute: hora_hasta
	 */
	public java.lang.String getHora_hasta();
	/**
	 * Set accessor for persistent attribute: hora_hasta
	 */
	public void setHora_hasta(java.lang.String newHora_hasta);
	/**
	 * Get accessor for persistent attribute: codigo_rango
	 */
	public java.lang.String getCodigo_rango();
	/**
	 * Set accessor for persistent attribute: codigo_rango
	 */
	public void setCodigo_rango(java.lang.String newCodigo_rango);
	/**
	 * Get accessor for persistent attribute: id_padre
	 */
	public java.lang.Integer getId_padre();
	/**
	 * Set accessor for persistent attribute: id_padre
	 */
	public void setId_padre(java.lang.Integer newId_padre);
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTecnico_peticion();
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTecnico_peticion(java.util.Collection aTecnico_peticion);
}
