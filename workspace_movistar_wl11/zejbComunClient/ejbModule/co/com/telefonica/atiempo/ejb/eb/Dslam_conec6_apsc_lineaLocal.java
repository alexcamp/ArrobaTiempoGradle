package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Dslam_conec6_apsc_linea
 */
public interface Dslam_conec6_apsc_lineaLocal
	extends javax.ejb.EJBLocalObject {
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
	 * This method was generated for supporting the relationship role named f_fk_dslam_conec6_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Respuesta_conec6_apsc_lineaLocal getF_fk_dslam_conec6_();
	/**
	 * This method was generated for supporting the relationship role named f_fk_dslam_conec6_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_fk_dslam_conec6_(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Respuesta_conec6_apsc_lineaLocal aF_fk_dslam_conec6_);
}
