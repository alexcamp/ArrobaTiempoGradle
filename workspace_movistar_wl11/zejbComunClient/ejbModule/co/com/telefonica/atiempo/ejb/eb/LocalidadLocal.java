package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Localidad
 */
public interface LocalidadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_provincia
	 */
	public java.lang.String getNombre_provincia();
	/**
	 * Set accessor for persistent attribute: nombre_provincia
	 */
	public void setNombre_provincia(java.lang.String newNombre_provincia);
	/**
	 * Get accessor for persistent attribute: nombre_municipio
	 */
	public java.lang.String getNombre_municipio();
	/**
	 * Set accessor for persistent attribute: nombre_municipio
	 */
	public void setNombre_municipio(java.lang.String newNombre_municipio);
	/**
	 * Get accessor for persistent attribute: nombre_localidad
	 */
	public java.lang.String getNombre_localidad();
	/**
	 * Set accessor for persistent attribute: nombre_localidad
	 */
	public void setNombre_localidad(java.lang.String newNombre_localidad);
	/**
	 * Get accessor for persistent attribute: descripcion_localidad
	 */
	public java.lang.String getDescripcion_localidad();
	/**
	 * Set accessor for persistent attribute: descripcion_localidad
	 */
	public void setDescripcion_localidad(
		java.lang.String newDescripcion_localidad);
	/**
	 * Get accessor for persistent attribute: ind_presuscr
	 */
	public java.lang.String getInd_presuscr();
	/**
	 * Set accessor for persistent attribute: ind_presuscr
	 */
	public void setInd_presuscr(java.lang.String newInd_presuscr);
	/**
	 * Get accessor for persistent attribute: ind_selecc_marcado
	 */
	public java.lang.String getInd_selecc_marcado();
	/**
	 * Set accessor for persistent attribute: ind_selecc_marcado
	 */
	public void setInd_selecc_marcado(java.lang.String newInd_selecc_marcado);
	/**
	 * Get accessor for persistent attribute: cod_formato_direccion
	 */
	public java.lang.String getCod_formato_direccion();
	/**
	 * Set accessor for persistent attribute: cod_formato_direccion
	 */
	public void setCod_formato_direccion(
		java.lang.String newCod_formato_direccion);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDireccion_atis(java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.MunicipioLocal getMunicipio();
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMunicipio(
		co.com.telefonica.atiempo.ejb.eb.MunicipioLocal aMunicipio);
	/**
	 * Get accessor for persistent attribute: tipo_loc
	 */
	public java.lang.Long getTipo_loc();
	/**
	 * Set accessor for persistent attribute: tipo_loc
	 */
	public void setTipo_loc(java.lang.Long newTipo_loc);
	/**
	 * Get accessor for persistent attribute: granite_code
	 */
	public int getGranite_code();
	/**
	 * Set accessor for persistent attribute: granite_code
	 */
	public void setGranite_code(int newGranite_code);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUsuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal anUsuario);
	/**
	 * This method was generated for supporting the relationship role named localidad_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal getLocalidad_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named localidad_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalidad_agenda_sc(
		co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal aLocalidad_agenda_sc);
	/**
	 * Get accessor for persistent attribute: cre
	 */
	public java.lang.String getCre();
	/**
	 * Set accessor for persistent attribute: cre
	 */
	public void setCre(java.lang.String newCre);
	/**
	 * Get accessor for persistent attribute: localidad_toa
	 */
	public java.lang.Short getLocalidad_toa();
	/**
	 * Set accessor for persistent attribute: localidad_toa
	 */
	public void setLocalidad_toa(java.lang.Short newLocalidad_toa);
}
