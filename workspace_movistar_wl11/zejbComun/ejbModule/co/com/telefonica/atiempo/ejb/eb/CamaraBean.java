package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Camara
 */
public abstract class CamaraBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.CamaraKey ejbCreate(
		java.lang.Long petiNumero,
		java.lang.String cameraSerial) throws javax.ejb.CreateException {
		setPetiNumero(petiNumero);
		setCameraSerial(cameraSerial);
		setTipoEquipo("");
		setTipoElemento(Long.valueOf("0"));
		setTipoInventario("");
		setAccion(Short.valueOf("0"));
		setTelefono(Long.valueOf("0"));
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long petiNumero,
		java.lang.String cameraSerial) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: petiNumero
	 */
	public abstract java.lang.Long getPetiNumero();
	/**
	 * Set accessor for persistent attribute: petiNumero
	 */
	public abstract void setPetiNumero(java.lang.Long newPetiNumero);
	/**
	 * Get accessor for persistent attribute: cameraSerial
	 */
	public abstract java.lang.String getCameraSerial();
	/**
	 * Set accessor for persistent attribute: cameraSerial
	 */
	public abstract void setCameraSerial(java.lang.String newCameraSerial);
	/**
	 * Get accessor for persistent attribute: cameraCode
	 */
	public abstract java.lang.String getCameraCode();
	/**
	 * Set accessor for persistent attribute: cameraCode
	 */
	public abstract void setCameraCode(java.lang.String newCameraCode);
	/**
	 * Get accessor for persistent attribute: cameraType
	 */
	public abstract java.lang.String getCameraType();
	/**
	 * Set accessor for persistent attribute: cameraType
	 */
	public abstract void setCameraType(java.lang.String newCameraType);
	/**
	 * Get accessor for persistent attribute: cameraBrand
	 */
	public abstract java.lang.String getCameraBrand();
	/**
	 * Set accessor for persistent attribute: cameraBrand
	 */
	public abstract void setCameraBrand(java.lang.String newCameraBrand);
	/**
	 * Get accessor for persistent attribute: cameraDescription
	 */
	public abstract java.lang.String getCameraDescription();
	/**
	 * Set accessor for persistent attribute: cameraDescription
	 */
	public abstract void setCameraDescription(
		java.lang.String newCameraDescription);
	/**
	 * Get accessor for persistent attribute: cameraState
	 */
	public abstract java.lang.Integer getCameraState();
	/**
	 * Set accessor for persistent attribute: cameraState
	 */
	public abstract void setCameraState(java.lang.Integer newCameraState);
	/**
	 * Get accessor for persistent attribute: cameraModel
	 */
	public abstract java.lang.String getCameraModel();
	/**
	 * Set accessor for persistent attribute: cameraModel
	 */
	public abstract void setCameraModel(java.lang.String newCameraModel);
	/**
	 * Get accessor for persistent attribute: tipoEquipo
	 */
	public abstract java.lang.String getTipoEquipo();
	/**
	 * Set accessor for persistent attribute: tipoEquipo
	 */
	public abstract void setTipoEquipo(java.lang.String newTipoEquipo);
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
	public abstract java.lang.Short getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Short newAccion);
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
	 * Get accessor for persistent attribute: numCelular
	 */
	public abstract java.lang.String getNumCelular();
	/**
	 * Set accessor for persistent attribute: numCelular
	 */
	public abstract void setNumCelular(java.lang.String newNumCelular);
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
	 * Get accessor for persistent attribute: flagMapSap
	 */
	public abstract java.lang.String getFlagMapSap();
	/**
	 * Set accessor for persistent attribute: flagMapSap
	 */
	public abstract void setFlagMapSap(java.lang.String newFlagMapSap);
	/**
	 * Get accessor for persistent attribute: caracteristica
	 */
	public abstract java.lang.String getCaracteristica();
	/**
	 * Set accessor for persistent attribute: caracteristica
	 */
	public abstract void setCaracteristica(java.lang.String newCaracteristica);
	/**
	 * Get accessor for persistent attribute: flagPetCurso
	 */
	public abstract java.lang.String getFlagPetCurso();
	/**
	 * Set accessor for persistent attribute: flagPetCurso
	 */
	public abstract void setFlagPetCurso(java.lang.String newFlagPetCurso);
}