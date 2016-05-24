package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Localidad
 */
public abstract class LocalidadBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadKey ejbCreate(
		java.lang.String cod_loc) throws javax.ejb.CreateException {
		setCod_loc(cod_loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_loc)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadKey ejbCreate(
		java.lang.String cod_loc,
		java.lang.String nombre_provincia,
		java.lang.String nombre_municipio,
		java.lang.String nombre_localidad,
		java.lang.String descripcion_localidad,
		java.lang.String ind_presuscr,
		java.lang.String ind_selecc_marcado)
		throws javax.ejb.CreateException {
		setCod_loc(cod_loc);
		setNombre_provincia(nombre_provincia);
		setNombre_municipio(nombre_municipio);
		setNombre_localidad(nombre_localidad);
		setDescripcion_localidad(descripcion_localidad);
		setInd_presuscr(ind_presuscr);
		setInd_selecc_marcado(ind_selecc_marcado);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String cod_loc,
		java.lang.String nombre_provincia,
		java.lang.String nombre_municipio,
		java.lang.String nombre_localidad,
		java.lang.String descripcion_localidad,
		java.lang.String ind_presuscr,
		java.lang.String ind_selecc_marcado)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: nombre_provincia
	 */
	public abstract java.lang.String getNombre_provincia();
	/**
	 * Set accessor for persistent attribute: nombre_provincia
	 */
	public abstract void setNombre_provincia(
		java.lang.String newNombre_provincia);
	/**
	 * Get accessor for persistent attribute: nombre_municipio
	 */
	public abstract java.lang.String getNombre_municipio();
	/**
	 * Set accessor for persistent attribute: nombre_municipio
	 */
	public abstract void setNombre_municipio(
		java.lang.String newNombre_municipio);
	/**
	 * Get accessor for persistent attribute: nombre_localidad
	 */
	public abstract java.lang.String getNombre_localidad();
	/**
	 * Set accessor for persistent attribute: nombre_localidad
	 */
	public abstract void setNombre_localidad(
		java.lang.String newNombre_localidad);
	/**
	 * Get accessor for persistent attribute: descripcion_localidad
	 */
	public abstract java.lang.String getDescripcion_localidad();
	/**
	 * Set accessor for persistent attribute: descripcion_localidad
	 */
	public abstract void setDescripcion_localidad(
		java.lang.String newDescripcion_localidad);
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public abstract java.lang.String getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.String newCod_loc);
	/**
	 * Get accessor for persistent attribute: ind_presuscr
	 */
	public abstract java.lang.String getInd_presuscr();
	/**
	 * Set accessor for persistent attribute: ind_presuscr
	 */
	public abstract void setInd_presuscr(java.lang.String newInd_presuscr);
	/**
	 * Get accessor for persistent attribute: ind_selecc_marcado
	 */
	public abstract java.lang.String getInd_selecc_marcado();
	/**
	 * Set accessor for persistent attribute: ind_selecc_marcado
	 */
	public abstract void setInd_selecc_marcado(
		java.lang.String newInd_selecc_marcado);
	/**
	 * Get accessor for persistent attribute: cod_formato_direccion
	 */
	public abstract java.lang.String getCod_formato_direccion();
	/**
	 * Set accessor for persistent attribute: cod_formato_direccion
	 */
	public abstract void setCod_formato_direccion(
		java.lang.String newCod_formato_direccion);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDireccion_atis(
		java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.MunicipioLocal getMunicipio();
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMunicipio(
		co.com.telefonica.atiempo.ejb.eb.MunicipioLocal aMunicipio);
	/**
	 * Get accessor for persistent attribute: tipo_loc
	 */
	public abstract java.lang.Long getTipo_loc();
	/**
	 * Set accessor for persistent attribute: tipo_loc
	 */
	public abstract void setTipo_loc(java.lang.Long newTipo_loc);
	/**
	 * Get accessor for persistent attribute: granite_code
	 */
	public abstract int getGranite_code();
	/**
	 * Set accessor for persistent attribute: granite_code
	 */
	public abstract void setGranite_code(int newGranite_code);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUsuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal anUsuario);
	/**
	 * This method was generated for supporting the relationship role named localidad_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal getLocalidad_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named localidad_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalidad_agenda_sc(
		co.com.telefonica.atiempo.ejb.eb.Localidad_agenda_scLocal aLocalidad_agenda_sc);
	/**
	 * Get accessor for persistent attribute: cre
	 */
	public abstract java.lang.String getCre();
	/**
	 * Set accessor for persistent attribute: cre
	 */
	public abstract void setCre(java.lang.String newCre);
	/**
	 * Get accessor for persistent attribute: localidad_toa
	 */
	public abstract java.lang.Short getLocalidad_toa();
	/**
	 * Set accessor for persistent attribute: localidad_toa
	 */
	public abstract void setLocalidad_toa(java.lang.Short newLocalidad_toa);
}
