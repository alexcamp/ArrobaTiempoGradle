package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Regla_Retenciones
 */
public abstract class Regla_RetencionesBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesKey ejbCreate(
		java.lang.Long regla_id) throws javax.ejb.CreateException {
		setRegla_id(regla_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long regla_id)
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
	 * Get accessor for persistent attribute: regla_id
	 */
	public abstract java.lang.Long getRegla_id();
	/**
	 * Set accessor for persistent attribute: regla_id
	 */
	public abstract void setRegla_id(java.lang.Long newRegla_id);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
	/**
	 * Get accessor for persistent attribute: ap_id
	 */
	public abstract java.lang.Long getAp_id();
	/**
	 * Set accessor for persistent attribute: ap_id
	 */
	public abstract void setAp_id(java.lang.Long newAp_id);
	/**
	 * Get accessor for persistent attribute: fecha_desde
	 */
	public abstract java.sql.Timestamp getFecha_desde();
	/**
	 * Set accessor for persistent attribute: fecha_desde
	 */
	public abstract void setFecha_desde(java.sql.Timestamp newFecha_desde);
	/**
	 * Get accessor for persistent attribute: fecha_hasta
	 */
	public abstract java.sql.Timestamp getFecha_hasta();
	/**
	 * Set accessor for persistent attribute: fecha_hasta
	 */
	public abstract void setFecha_hasta(java.sql.Timestamp newFecha_hasta);
	/**
	 * Get accessor for persistent attribute: cod_dpt
	 */
	public abstract java.lang.String getCod_dpt();
	/**
	 * Set accessor for persistent attribute: cod_dpt
	 */
	public abstract void setCod_dpt(java.lang.String newCod_dpt);
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public abstract java.lang.String getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.String newCod_loc);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public abstract java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public abstract void setTica_id(java.lang.String newTica_id);
	/**
	 * Get accessor for persistent attribute: peti_id_instancia
	 */
	public abstract java.lang.String getPeti_id_instancia();
	/**
	 * Set accessor for persistent attribute: peti_id_instancia
	 */
	public abstract void setPeti_id_instancia(
		java.lang.String newPeti_id_instancia);
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
