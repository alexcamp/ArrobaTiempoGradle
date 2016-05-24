package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Zonas_atendimiento
 */
public interface Zonas_atendimientoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_zona_atendimiento
	 */
	public java.lang.Long getId_zona_atendimiento();
	/**
	 * Set accessor for persistent attribute: id_zona_atendimiento
	 */
	public void setId_zona_atendimiento(java.lang.Long newId_zona_atendimiento);
	/**
	 * Get accessor for persistent attribute: ip
	 */
	public java.lang.String getIp();
	/**
	 * Set accessor for persistent attribute: ip
	 */
	public void setIp(java.lang.String newIp);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Recursos_linea_basicaLocal getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRecursos_linea_basica(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Recursos_linea_basicaLocal aRecursos_linea_basica);
	/**
	 * Get accessor for persistent attribute: id_conector
	 */
	public java.lang.Long getId_conector();
	/**
	 * Set accessor for persistent attribute: id_conector
	 */
	public void setId_conector(java.lang.Long newId_conector);
}
