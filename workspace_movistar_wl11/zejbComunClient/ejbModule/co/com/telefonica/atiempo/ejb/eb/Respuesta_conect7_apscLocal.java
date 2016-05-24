package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Respuesta_conect7_apsc
 */
public interface Respuesta_conect7_apscLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ind_error
	 */
	public java.lang.String getInd_error();
	/**
	 * Set accessor for persistent attribute: ind_error
	 */
	public void setInd_error(java.lang.String newInd_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * This method was generated for supporting the relationship role named f_fk_01_respuesta_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estado_lineaLocal getF_fk_01_respuesta_();
	/**
	 * This method was generated for supporting the relationship role named f_fk_01_respuesta_.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_fk_01_respuesta_(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Mensaje_estado_lineaLocal aF_fk_01_respuesta_);
}
