package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: DecoModemPeticion
 */
public abstract class DecoModemPeticionBean implements javax.ejb.EntityBean {
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
	public java.lang.Long ejbCreate(java.lang.Long correlativo)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long correlativo)
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
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: peticion
	 */
	public abstract java.lang.String getPeticion();
	/**
	 * Set accessor for persistent attribute: peticion
	 */
	public abstract void setPeticion(java.lang.String newPeticion);
	/**
	 * Get accessor for persistent attribute: serial
	 */
	public abstract java.lang.String getSerial();
	/**
	 * Set accessor for persistent attribute: serial
	 */
	public abstract void setSerial(java.lang.String newSerial);
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
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
	/**
	 * Get accessor for persistent attribute: horariospuntaten
	 */
	public abstract java.lang.String getHorariospuntaten();
	/**
	 * Set accessor for persistent attribute: horariospuntaten
	 */
	public abstract void setHorariospuntaten(
		java.lang.String newHorariospuntaten);
	/**
	 * Get accessor for persistent attribute: direccionpuntaten
	 */
	public abstract java.lang.String getDireccionpuntaten();
	/**
	 * Set accessor for persistent attribute: direccionpuntaten
	 */
	public abstract void setDireccionpuntaten(
		java.lang.String newDireccionpuntaten);
	/**
	 * Get accessor for persistent attribute: fechamaxentrega
	 */
	public abstract java.lang.String getFechamaxentrega();
	/**
	 * Set accessor for persistent attribute: fechamaxentrega
	 */
	public abstract void setFechamaxentrega(java.lang.String newFechamaxentrega);
	/**
	 * Get accessor for persistent attribute: ciclo
	 */
	public abstract java.lang.Long getCiclo();
	/**
	 * Set accessor for persistent attribute: ciclo
	 */
	public abstract void setCiclo(java.lang.Long newCiclo);
	/**
	 * Get accessor for persistent attribute: id_act
	 */
	public abstract java.lang.Long getId_act();
	/**
	 * Set accessor for persistent attribute: id_act
	 */
	public abstract void setId_act(java.lang.Long newId_act);
	/**
	 * Get accessor for persistent attribute: tipo
	 */
	public abstract java.lang.Long getTipo();
	/**
	 * Set accessor for persistent attribute: tipo
	 */
	public abstract void setTipo(java.lang.Long newTipo);
	/**
	 * Get accessor for persistent attribute: castID
	 */
	public abstract java.lang.String getCastID();
	/**
	 * Set accessor for persistent attribute: castID
	 */
	public abstract void setCastID(java.lang.String newCastID);
	/**
	 * Get accessor for persistent attribute: num_doc
	 */
	public abstract java.lang.String getNum_doc();
	/**
	 * Set accessor for persistent attribute: num_doc
	 */
	public abstract void setNum_doc(java.lang.String newNum_doc);
	/**
	 * Get accessor for persistent attribute: estadopet
	 */
	public abstract java.lang.Long getEstadopet();
	/**
	 * Set accessor for persistent attribute: estadopet
	 */
	public abstract void setEstadopet(java.lang.Long newEstadopet);
}