package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_agenda_sc
 */
public interface Mensaje_agenda_scLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: cod_actividad_generadora
	 */
	public java.lang.String getCod_actividad_generadora();
	/**
	 * Set accessor for persistent attribute: cod_actividad_generadora
	 */
	public void setCod_actividad_generadora(
		java.lang.String newCod_actividad_generadora);
	/**
	 * Get accessor for persistent attribute: id_agenda
	 */
	public java.lang.String getId_agenda();
	/**
	 * Set accessor for persistent attribute: id_agenda
	 */
	public void setId_agenda(java.lang.String newId_agenda);
	/**
	 * Get accessor for persistent attribute: error
	 */
	public java.lang.String getError();
	/**
	 * Set accessor for persistent attribute: error
	 */
	public void setError(java.lang.String newError);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public void setDesc_error(java.lang.String newDesc_error);
	/**
	 * Get accessor for persistent attribute: tipo_mensaje
	 */
	public java.lang.String getTipo_mensaje();
	/**
	 * Set accessor for persistent attribute: tipo_mensaje
	 */
	public void setTipo_mensaje(java.lang.String newTipo_mensaje);
	/**
	 * Get accessor for persistent attribute: cod_estado
	 */
	public java.lang.Integer getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public void setCod_estado(java.lang.Integer newCod_estado);
	/**
	 * Get accessor for persistent attribute: apptNumber
	 */
	public java.lang.String getApptNumber();
	/**
	 * Set accessor for persistent attribute: apptNumber
	 */
	public void setApptNumber(java.lang.String newApptNumber);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public void setReintentos(java.lang.Long newReintentos);
}