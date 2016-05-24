package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Servicio_basico_supl_conec6_apsc_linea
 */
public interface Servicio_basico_supl_conec6_apsc_lineaLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: codigo_ps
	 */
	public java.lang.String getCodigo_ps();
	/**
	 * Set accessor for persistent attribute: codigo_ps
	 */
	public void setCodigo_ps(java.lang.String newCodigo_ps);
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
}
