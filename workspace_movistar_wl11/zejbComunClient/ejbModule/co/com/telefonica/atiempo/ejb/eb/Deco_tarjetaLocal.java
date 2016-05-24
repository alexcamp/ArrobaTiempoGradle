package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.EquipoDTO;


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
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
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
	 * Get accessor for persistent attribute: opco_id
	 */
	public java.lang.Long getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public void setOpco_id(java.lang.Long newOpco_id);
	/**
	 * Get accessor for persistent attribute: empr_id
	 */
	public java.lang.Long getEmpr_id();
	/**
	 * Set accessor for persistent attribute: empr_id
	 */
	public void setEmpr_id(java.lang.Long newEmpr_id);
	
	public EquipoDTO toEquipo();
	public boolean estaActivo();
	public boolean estaEliminado();
	
	
	public boolean esActivacionFallida();
	public boolean esEliminacionFallida();
	public boolean esEsperaDeActivacion();
	public boolean esEsperaDeEliminacion();
	public String getDescEstado();
	public boolean estaEnCas();
	public boolean esAccionDanhado();
	public boolean esAccionDanhadoResNoOk();
	public boolean esAccionDanhadoResOk();
	public boolean esAccionNoRecuperado();
	public boolean esAccionNoRecuperadoResNoOk();
	public boolean esAccionNoRecuperadoResOk();
	public Integer getIdEstado();
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
	/**
	 * Get accessor for persistent attribute: deco_adicionales
	 */
	public java.lang.Long getDeco_adicionales();
	/**
	 * Set accessor for persistent attribute: deco_adicionales
	 */
	public void setDeco_adicionales(java.lang.Long newDeco_adicionales);
	/**
	 * Get accessor for persistent attribute: fec_ejec_atis 
	 */
	public java.sql.Timestamp getFec_ejec_atis();
	/**
	 * Set accessor for persistent attribute: fec_ejec_atis
	 */
	public void setFec_ejec_atis(java.sql.Timestamp newFec_ejec_atis);
}
