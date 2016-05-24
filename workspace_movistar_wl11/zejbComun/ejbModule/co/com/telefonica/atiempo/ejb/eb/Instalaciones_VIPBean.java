package co.com.telefonica.atiempo.ejb.eb;


/**
 * Bean implementation class for Enterprise Bean: Instalaciones_VIP
 */
public abstract class Instalaciones_VIPBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey ejbCreate(
			java.lang.Long atiempo, java.lang.Long atis,String idpc,String idpcTV,
			String codLocalidad,String codDPTO,Integer espeId, String ticaId) throws javax.ejb.CreateException {
		
		setAtis(atis);
		setAtiempo(atiempo);
		setIdpc(idpc);
		setIdpcTV(idpcTV);
		setCod_dpto(codDPTO);
		setCod_localidad(codLocalidad);
		setEspe_id(espeId);
		setTica_id(ticaId);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long atiempo, java.lang.Long atis,String idpc,String idpcTV,
			String codLocalidad,String codDPTO,Integer espeId, String ticaId)
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
	 * Get accessor for persistent attribute: atis
	 */
	public abstract java.lang.Long getAtis();
	/**
	 * Set accessor for persistent attribute: atis
	 */
	public abstract void setAtis(java.lang.Long newAtis);
	/**
	 * Get accessor for persistent attribute: atiempo
	 */
	public abstract java.lang.Long getAtiempo();
	/**
	 * Set accessor for persistent attribute: atiempo
	 */
	public abstract void setAtiempo(java.lang.Long newAtiempo);
	/**
	 * Get accessor for persistent attribute: idpc
	 */
	public abstract java.lang.String getIdpc();
	/**
	 * Set accessor for persistent attribute: idpc
	 */
	public abstract void setIdpc(java.lang.String newIdpc);
	/**
	 * Get accessor for persistent attribute: idpcTV
	 */
	public abstract java.lang.String getIdpcTV();
	/**
	 * Set accessor for persistent attribute: idpcTV
	 */
	public abstract void setIdpcTV(java.lang.String newIdpcTV);
	/**
	 * Get accessor for persistent attribute: fecha_ingreso
	 */
	public abstract java.sql.Timestamp getFecha_ingreso();
	/**
	 * Set accessor for persistent attribute: fecha_ingreso
	 */
	public abstract void setFecha_ingreso(java.sql.Timestamp newFecha_ingreso);
	/**
	 * Get accessor for persistent attribute: fecha_fin
	 */
	public abstract java.sql.Timestamp getFecha_fin();
	/**
	 * Set accessor for persistent attribute: fecha_fin
	 */
	public abstract void setFecha_fin(java.sql.Timestamp newFecha_fin);
	/**
	 * Get accessor for persistent attribute: cod_localidad
	 */
	public abstract java.lang.String getCod_localidad();
	/**
	 * Set accessor for persistent attribute: cod_localidad
	 */
	public abstract void setCod_localidad(java.lang.String newCod_localidad);
	/**
	 * Get accessor for persistent attribute: cod_dpto
	 */
	public abstract java.lang.String getCod_dpto();
	/**
	 * Set accessor for persistent attribute: cod_dpto
	 */
	public abstract void setCod_dpto(java.lang.String newCod_dpto);
	/**
	 * Get accessor for persistent attribute: espe_id
	 */
	public abstract java.lang.Integer getEspe_id();
	/**
	 * Set accessor for persistent attribute: espe_id
	 */
	public abstract void setEspe_id(java.lang.Integer newEspe_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public abstract java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public abstract void setTica_id(java.lang.String newTica_id);
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
}