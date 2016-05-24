package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Codigo_cierre
 */
public interface Codigo_cierreLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_cierre
	 */
//	public java.lang.String getCod_cierre();
//	/**
//	 * Set accessor for persistent attribute: cod_cierre
//	 */
//	public void setCod_cierre(java.lang.String newCod_cierre);
	/**
	 * Get accessor for persistent attribute: descripcion_cierre
	 */
	public java.lang.String getDescripcion_cierre();
	/**
	 * Set accessor for persistent attribute: descripcion_cierre
	 */
	public void setDescripcion_cierre(java.lang.String newDescripcion_cierre);
	/**
	 * Get accessor for persistent attribute: causa_incidencia
	 */
	public java.lang.String getCausa_incidencia();
	/**
	 * Set accessor for persistent attribute: causa_incidencia
	 */
	public void setCausa_incidencia(java.lang.String newCausa_incidencia);
	/**
	 * Get accessor for persistent attribute: nombre_cod_cierre
	 */
	public java.lang.String getNombre_cod_cierre();
	/**
	 * Set accessor for persistent attribute: nombre_cod_cierre
	 */
	public void setNombre_cod_cierre(java.lang.String newNombre_cod_cierre);
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.LocalizacionLocal getLocalizacion();
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalizacion(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.LocalizacionLocal aLocalizacion);
	/**
	 * Get accessor for persistent attribute: resp_cliente
	 */
	public java.lang.String getResp_cliente();
	/**
	 * Set accessor for persistent attribute: resp_cliente
	 */
	public void setResp_cliente(java.lang.String newResp_cliente);
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCodigo_cierre_peticion();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCodigo_cierre_peticion(
		java.util.Collection aCodigo_cierre_peticion);
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public void setHabilitado(short newHabilitado);
	/**
	 * Get accessor for persistent attribute: obligatorio_marca
	 */
	public short getObligatorio_marca();
	/**
	 * Set accessor for persistent attribute: obligatorio_marca
	 */
	public void setObligatorio_marca(short newObligatorio_marca);
	/**
	 * Get accessor for persistent attribute: obligatorio_modem
	 */
	public short getObligatorio_modem();
	/**
	 * Set accessor for persistent attribute: obligatorio_modem
	 */
	public void setObligatorio_modem(short newObligatorio_modem);
}
