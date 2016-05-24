package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: ElementoNoSerializado
 */
public abstract class ElementoNoSerializadoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ElementoNoSerializadoKey ejbCreate(
		java.lang.Long id,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setId(id);
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK = (co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
			.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long id,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setPeticion(argPeticion);
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
	 * Get accessor for persistent attribute: tipoElemento
	 */
	public abstract java.lang.Long getTipoElemento();
	/**
	 * Set accessor for persistent attribute: tipoElemento
	 */
	public abstract void setTipoElemento(java.lang.Long newTipoElemento);
	/**
	 * Get accessor for persistent attribute: tipoInventario
	 */
	public abstract java.lang.String getTipoInventario();
	/**
	 * Set accessor for persistent attribute: tipoInventario
	 */
	public abstract void setTipoInventario(java.lang.String newTipoInventario);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.Integer getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Integer newAccion);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public abstract java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public abstract void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: psId
	 */
	public abstract java.lang.Long getPsId();
	/**
	 * Set accessor for persistent attribute: psId
	 */
	public abstract void setPsId(java.lang.Long newPsId);
	/**
	 * Get accessor for persistent attribute: cantidad
	 */
	public abstract java.lang.Long getCantidad();
	/**
	 * Set accessor for persistent attribute: cantidad
	 */
	public abstract void setCantidad(java.lang.Long newCantidad);
	/**
	 * Get accessor for persistent attribute: marca
	 */
	public abstract java.lang.String getMarca();
	/**
	 * Set accessor for persistent attribute: marca
	 */
	public abstract void setMarca(java.lang.String newMarca);
	/**
	 * Get accessor for persistent attribute: modelo
	 */
	public abstract java.lang.String getModelo();
	/**
	 * Set accessor for persistent attribute: modelo
	 */
	public abstract void setModelo(java.lang.String newModelo);
	/**
	 * Get accessor for persistent attribute: numDocSap
	 */
	public abstract java.lang.String getNumDocSap();
	/**
	 * Set accessor for persistent attribute: numDocSap
	 */
	public abstract void setNumDocSap(java.lang.String newNumDocSap);
	/**
	 * Get accessor for persistent attribute: fecContSap
	 */
	public abstract java.lang.String getFecContSap();
	/**
	 * Set accessor for persistent attribute: fecContSap
	 */
	public abstract void setFecContSap(java.lang.String newFecContSap);
	/**
	 * Get accessor for persistent attribute: claseMovSap
	 */
	public abstract java.lang.String getClaseMovSap();
	/**
	 * Set accessor for persistent attribute: claseMovSap
	 */
	public abstract void setClaseMovSap(java.lang.String newClaseMovSap);
	/**
	 * Get accessor for persistent attribute: posDocSap
	 */
	public abstract java.lang.Integer getPosDocSap();
	/**
	 * Set accessor for persistent attribute: posDocSap
	 */
	public abstract void setPosDocSap(java.lang.Integer newPosDocSap);
	/**
	 * Get accessor for persistent attribute: numMaterialSap
	 */
	public abstract java.lang.String getNumMaterialSap();
	/**
	 * Set accessor for persistent attribute: numMaterialSap
	 */
	public abstract void setNumMaterialSap(java.lang.String newNumMaterialSap);
	/**
	 * Get accessor for persistent attribute: centroSap
	 */
	public abstract java.lang.String getCentroSap();
	/**
	 * Set accessor for persistent attribute: centroSap
	 */
	public abstract void setCentroSap(java.lang.String newCentroSap);
	/**
	 * Get accessor for persistent attribute: almacenSap
	 */
	public abstract java.lang.String getAlmacenSap();
	/**
	 * Set accessor for persistent attribute: almacenSap
	 */
	public abstract void setAlmacenSap(java.lang.String newAlmacenSap);
	/**
	 * Get accessor for persistent attribute: codLoteSap
	 */
	public abstract java.lang.String getCodLoteSap();
	/**
	 * Set accessor for persistent attribute: codLoteSap
	 */
	public abstract void setCodLoteSap(java.lang.String newCodLoteSap);
	/**
	 * Get accessor for persistent attribute: undMedidaSap
	 */
	public abstract java.lang.String getUndMedidaSap();
	/**
	 * Set accessor for persistent attribute: undMedidaSap
	 */
	public abstract void setUndMedidaSap(java.lang.String newUndMedidaSap);
	/**
	 * Get accessor for persistent attribute: centrCostSap
	 */
	public abstract java.lang.String getCentrCostSap();
	/**
	 * Set accessor for persistent attribute: centrCostSap
	 */
	public abstract void setCentrCostSap(java.lang.String newCentrCostSap);
	/**
	 * Get accessor for persistent attribute: areaFuncSap
	 */
	public abstract java.lang.String getAreaFuncSap();
	/**
	 * Set accessor for persistent attribute: areaFuncSap
	 */
	public abstract void setAreaFuncSap(java.lang.String newAreaFuncSap);
	/**
	 * Get accessor for persistent attribute: elementPepSap
	 */
	public abstract java.lang.String getElementPepSap();
	/**
	 * Set accessor for persistent attribute: elementPepSap
	 */
	public abstract void setElementPepSap(java.lang.String newElementPepSap);
	/**
	 * Get accessor for persistent attribute: flagMatSap
	 */
	public abstract java.lang.String getFlagMatSap();
	/**
	 * Set accessor for persistent attribute: flagMatSap
	 */
	public abstract void setFlagMatSap(java.lang.String newFlagMatSap);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Long newId);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ElementoNoSerializadoKey ejbCreate(
		java.lang.Long id,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException {
		setId(id);
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long id,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract java.lang.Long getPeticion_peti_numero();
	/**
	 * Set accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract void setPeticion_peti_numero(
		java.lang.Long newPeticion_peti_numero);
}