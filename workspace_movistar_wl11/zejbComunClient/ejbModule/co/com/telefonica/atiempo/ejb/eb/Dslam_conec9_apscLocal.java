package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Dslam_conec9_apsc
 */
public interface Dslam_conec9_apscLocal extends javax.ejb.EJBLocalObject {
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
	 * Get accessor for persistent attribute: tipo_dslam
	 */
	public java.lang.String getTipo_dslam();
	/**
	 * Set accessor for persistent attribute: tipo_dslam
	 */
	public void setTipo_dslam(java.lang.String newTipo_dslam);
	/**
	 * Get accessor for persistent attribute: ip
	 */
	public java.lang.String getIp();
	/**
	 * Set accessor for persistent attribute: ip
	 */
	public void setIp(java.lang.String newIp);
}
