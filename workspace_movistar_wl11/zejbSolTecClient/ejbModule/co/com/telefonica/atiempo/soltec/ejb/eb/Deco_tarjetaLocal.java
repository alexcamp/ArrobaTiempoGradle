package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Deco_tarjeta
 */
public interface Deco_tarjetaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
	/**
	 * Get accessor for persistent attribute: codigo_error
	 */
	public java.lang.Integer getCodigo_error();
	/**
	 * Set accessor for persistent attribute: codigo_error
	 */
	public void setCodigo_error(java.lang.Integer newCodigo_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.Integer getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.Integer newAccion);
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

	public boolean estaActivo();
	public boolean estaEliminado();
	
	
	public boolean esActivacionFallida();
	public boolean esEliminacionFallida();
	public boolean esEsperaDeActivacion();
	public boolean esEsperaDeEliminacion();
	public String getDescEstado();
	public boolean estaEnCas();
	public boolean esEsperaDeEliminacionDanhado();
	public boolean esEliminacionDanhadoFallida();
	public boolean estaEliminadoDanhado();
	public boolean esAccionNoRecuperado();
	public boolean esAccionNoRecuperadoResNoOk();
	public boolean esAccionNoRecuperadoResOk();
	public Integer getIdEstado();
	public boolean isOriginal();
	/**
	 * Get accessor for persistent attribute: original
	 */
	public java.lang.Integer getOriginal();
	/**
	 * Set accessor for persistent attribute: original
	 */
	public void setOriginal(java.lang.Integer newOriginal);
	/**
	 * Get accessor for persistent attribute: marca_hora
	 */
	public java.sql.Timestamp getMarca_hora();
	/**
	 * Set accessor for persistent attribute: marca_hora
	 */
	public void setMarca_hora(java.sql.Timestamp newMarca_hora);
	/**
	 * Get accessor for persistent attribute: deco_reference
	 */
	public java.lang.String getDeco_reference();
	/**
	 * Set accessor for persistent attribute: deco_reference
	 */
	public void setDeco_reference(java.lang.String newDeco_reference);
	/**
	 * Get accessor for persistent attribute: deco_marca
	 */
	public java.lang.String getDeco_marca();
	/**
	 * Set accessor for persistent attribute: deco_marca
	 */
	public void setDeco_marca(java.lang.String newDeco_marca);
	/**
	 * Get accessor for persistent attribute: serial_deco
	 */
	public java.lang.String getSerial_deco();
	/**
	 * Set accessor for persistent attribute: serial_deco
	 */
	public void setSerial_deco(java.lang.String newSerial_deco);
	/**
	 * Get accessor for persistent attribute: serial_tarjeta
	 */
	public java.lang.String getSerial_tarjeta();
	/**
	 * Set accessor for persistent attribute: serial_tarjeta
	 */
	public void setSerial_tarjeta(java.lang.String newSerial_tarjeta);
	/**
	 * Get accessor for persistent attribute: codigo_deco
	 */
	public java.lang.String getCodigo_deco();
	/**
	 * Set accessor for persistent attribute: codigo_deco
	 */
	public void setCodigo_deco(java.lang.String newCodigo_deco);
	/**
	 * Get accessor for persistent attribute: flag_pet_curso
	 */
	public java.lang.String getFlag_pet_curso();
	/**
	 * Set accessor for persistent attribute: flag_pet_curso
	 */
	public void setFlag_pet_curso(java.lang.String newFlag_pet_curso);
	/**
	 * Get accessor for persistent attribute: serial_ddtv
	 */
	public java.lang.String getSerial_ddtv();
	/**
	 * Set accessor for persistent attribute: serial_ddtv
	 */
	public void setSerial_ddtv(java.lang.String newSerial_ddtv);
	/**
	 * Get accessor for persistent attribute: marca_ddtv
	 */
	public java.lang.String getMarca_ddtv();
	/**
	 * Set accessor for persistent attribute: marca_ddtv
	 */
	public void setMarca_ddtv(java.lang.String newMarca_ddtv);
	/**
	 * Get accessor for persistent attribute: modelo_ddtv
	 */
	public java.lang.String getModelo_ddtv();
	/**
	 * Set accessor for persistent attribute: modelo_ddtv
	 */
	public void setModelo_ddtv(java.lang.String newModelo_ddtv);
}
