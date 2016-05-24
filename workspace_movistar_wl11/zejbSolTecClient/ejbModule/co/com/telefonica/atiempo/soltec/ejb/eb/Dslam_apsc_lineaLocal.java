package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Dslam_apsc_linea
 */
public interface Dslam_apsc_lineaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ip
	 */
	public java.lang.String getIp();
	/**
	 * Set accessor for persistent attribute: ip
	 */
	public void setIp(java.lang.String newIp);
	/**
	 * Get accessor for persistent attribute: tipo_dslam
	 */
	public java.lang.String getTipo_dslam();
	/**
	 * Set accessor for persistent attribute: tipo_dslam
	 */
	public void setTipo_dslam(java.lang.String newTipo_dslam);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
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
			.soltec
			.ejb
			.eb
			.Recursos_linea_basicaLocal aRecursos_linea_basica);
}
