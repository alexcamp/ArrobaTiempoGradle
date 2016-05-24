package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local interface for Enterprise Bean: Tmp_Notificacion_SAP
 */
public interface Tmp_Notificacion_SAPLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: cod_pet_cd
	 */
	public java.lang.Long getCod_pet_cd();
	/**
	 * Set accessor for persistent attribute: cod_pet_cd
	 */
	public void setCod_pet_cd(java.lang.Long newCod_pet_cd);
	/**
	 * Get accessor for persistent attribute: fec_ingreso
	 */
	public java.sql.Timestamp getFec_ingreso();
	/**
	 * Set accessor for persistent attribute: fec_ingreso
	 */
	public void setFec_ingreso(java.sql.Timestamp newFec_ingreso);
	/**
	 * Get accessor for persistent attribute: mensaje
	 */
	public java.lang.String getMensaje();
	/**
	 * Set accessor for persistent attribute: mensaje
	 */
	public void setMensaje(java.lang.String newMensaje);
}