package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: ElementoNoSerializado
 */
public interface ElementoNoSerializadoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: tipoElemento
	 */
	public java.lang.Long getTipoElemento();
	/**
	 * Set accessor for persistent attribute: tipoElemento
	 */
	public void setTipoElemento(java.lang.Long newTipoElemento);
	/**
	 * Get accessor for persistent attribute: tipoInventario
	 */
	public java.lang.String getTipoInventario();
	/**
	 * Set accessor for persistent attribute: tipoInventario
	 */
	public void setTipoInventario(java.lang.String newTipoInventario);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public java.lang.Integer getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public void setAccion(java.lang.Integer newAccion);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: psId
	 */
	public java.lang.Long getPsId();
	/**
	 * Set accessor for persistent attribute: psId
	 */
	public void setPsId(java.lang.Long newPsId);
	/**
	 * Get accessor for persistent attribute: cantidad
	 */
	public java.lang.Long getCantidad();
	/**
	 * Set accessor for persistent attribute: cantidad
	 */
	public void setCantidad(java.lang.Long newCantidad);
	/**
	 * Get accessor for persistent attribute: marca
	 */
	public java.lang.String getMarca();
	/**
	 * Set accessor for persistent attribute: marca
	 */
	public void setMarca(java.lang.String newMarca);
	/**
	 * Get accessor for persistent attribute: modelo
	 */
	public java.lang.String getModelo();
	/**
	 * Set accessor for persistent attribute: modelo
	 */
	public void setModelo(java.lang.String newModelo);
	/**
	 * Get accessor for persistent attribute: numDocSap
	 */
	public java.lang.String getNumDocSap();
	/**
	 * Set accessor for persistent attribute: numDocSap
	 */
	public void setNumDocSap(java.lang.String newNumDocSap);
	/**
	 * Get accessor for persistent attribute: fecContSap
	 */
	public java.lang.String getFecContSap();
	/**
	 * Set accessor for persistent attribute: fecContSap
	 */
	public void setFecContSap(java.lang.String newFecContSap);
	/**
	 * Get accessor for persistent attribute: claseMovSap
	 */
	public java.lang.String getClaseMovSap();
	/**
	 * Set accessor for persistent attribute: claseMovSap
	 */
	public void setClaseMovSap(java.lang.String newClaseMovSap);
	/**
	 * Get accessor for persistent attribute: posDocSap
	 */
	public java.lang.Integer getPosDocSap();
	/**
	 * Set accessor for persistent attribute: posDocSap
	 */
	public void setPosDocSap(java.lang.Integer newPosDocSap);
	/**
	 * Get accessor for persistent attribute: numMaterialSap
	 */
	public java.lang.String getNumMaterialSap();
	/**
	 * Set accessor for persistent attribute: numMaterialSap
	 */
	public void setNumMaterialSap(java.lang.String newNumMaterialSap);
	/**
	 * Get accessor for persistent attribute: centroSap
	 */
	public java.lang.String getCentroSap();
	/**
	 * Set accessor for persistent attribute: centroSap
	 */
	public void setCentroSap(java.lang.String newCentroSap);
	/**
	 * Get accessor for persistent attribute: almacenSap
	 */
	public java.lang.String getAlmacenSap();
	/**
	 * Set accessor for persistent attribute: almacenSap
	 */
	public void setAlmacenSap(java.lang.String newAlmacenSap);
	/**
	 * Get accessor for persistent attribute: codLoteSap
	 */
	public java.lang.String getCodLoteSap();
	/**
	 * Set accessor for persistent attribute: codLoteSap
	 */
	public void setCodLoteSap(java.lang.String newCodLoteSap);
	/**
	 * Get accessor for persistent attribute: undMedidaSap
	 */
	public java.lang.String getUndMedidaSap();
	/**
	 * Set accessor for persistent attribute: undMedidaSap
	 */
	public void setUndMedidaSap(java.lang.String newUndMedidaSap);
	/**
	 * Get accessor for persistent attribute: centrCostSap
	 */
	public java.lang.String getCentrCostSap();
	/**
	 * Set accessor for persistent attribute: centrCostSap
	 */
	public void setCentrCostSap(java.lang.String newCentrCostSap);
	/**
	 * Get accessor for persistent attribute: areaFuncSap
	 */
	public java.lang.String getAreaFuncSap();
	/**
	 * Set accessor for persistent attribute: areaFuncSap
	 */
	public void setAreaFuncSap(java.lang.String newAreaFuncSap);
	/**
	 * Get accessor for persistent attribute: elementPepSap
	 */
	public java.lang.String getElementPepSap();
	/**
	 * Set accessor for persistent attribute: elementPepSap
	 */
	public void setElementPepSap(java.lang.String newElementPepSap);
	/**
	 * Get accessor for persistent attribute: flagMatSap
	 */
	public java.lang.String getFlagMatSap();
	/**
	 * Set accessor for persistent attribute: flagMatSap
	 */
	public void setFlagMatSap(java.lang.String newFlagMatSap);
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
}