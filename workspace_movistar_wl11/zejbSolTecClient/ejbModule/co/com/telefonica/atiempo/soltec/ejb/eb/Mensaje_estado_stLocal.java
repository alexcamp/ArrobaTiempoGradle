package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_estado_st
 */
public interface Mensaje_estado_stLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
	/**
	 * Get accessor for persistent attribute: cod_conector
	 */
	public java.lang.Integer getCod_conector();
	/**
	 * Set accessor for persistent attribute: cod_conector
	 */
	public void setCod_conector(java.lang.Integer newCod_conector);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public java.lang.String getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public void setFecha_cierre(java.lang.String newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: cod_estado
	 */
	public java.lang.Integer getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public void setCod_estado(java.lang.Integer newCod_estado);
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
	 * Get accessor for persistent attribute: area
	 */
	public java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public void setArea(java.lang.Integer newArea);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.String newAccion);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public java.lang.String getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public void setTelefono(java.lang.String newTelefono);
	/**
	 * Get accessor for persistent attribute: serial
	 */
	public java.lang.String getSerial();
	/**
	 * Set accessor for persistent attribute: serial
	 */
	public void setSerial(java.lang.String newSerial);
	/**
	 * Get accessor for persistent attribute: observaciones
	 */
	public java.lang.String getObservaciones();
	/**
	 * Set accessor for persistent attribute: observaciones
	 */
	public void setObservaciones(java.lang.String newObservaciones);
	/**
	 * Get accessor for persistent attribute: appNumber
	 */
	public java.lang.String getAppNumber();
	/**
	 * Set accessor for persistent attribute: appNumber
	 */
	public void setAppNumber(java.lang.String newAppNumber);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public void setReintentos(java.lang.Long newReintentos);
}
