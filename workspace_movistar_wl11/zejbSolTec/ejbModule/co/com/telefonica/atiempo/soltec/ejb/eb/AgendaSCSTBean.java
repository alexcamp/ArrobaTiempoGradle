package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: AgendaSCST
 */
public abstract class AgendaSCSTBean implements javax.ejb.EntityBean {
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
	public java.lang.String ejbCreate(java.lang.String id_actuacion)
		throws javax.ejb.CreateException {
		setId_actuacion(id_actuacion);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String id_actuacion)
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
	 * Get accessor for persistent attribute: id_actuacion
	 */
	public abstract java.lang.String getId_actuacion();
	/**
	 * Set accessor for persistent attribute: id_actuacion
	 */
	public abstract void setId_actuacion(java.lang.String newId_actuacion);
	/**
	 * Get accessor for persistent attribute: id_peticion_st
	 */
	public abstract java.lang.Long getId_peticion_st();
	/**
	 * Set accessor for persistent attribute: id_peticion_st
	 */
	public abstract void setId_peticion_st(java.lang.Long newId_peticion_st);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: mensaje_act
	 */
	public abstract java.lang.String getMensaje_act();
	/**
	 * Set accessor for persistent attribute: mensaje_act
	 */
	public abstract void setMensaje_act(java.lang.String newMensaje_act);
	/**
	 * Get accessor for persistent attribute: cierre_por_error
	 */
	public abstract java.lang.Integer getCierre_por_error();
	/**
	 * Set accessor for persistent attribute: cierre_por_error
	 */
	public abstract void setCierre_por_error(
		java.lang.Integer newCierre_por_error);
	/**
	 * Get accessor for persistent attribute: fecha_ingreso
	 */
	public abstract java.sql.Timestamp getFecha_ingreso();
	/**
	 * Set accessor for persistent attribute: fecha_ingreso
	 */
	public abstract void setFecha_ingreso(java.sql.Timestamp newFecha_ingreso);
	/**
	 * Get accessor for persistent attribute: nombre_contratista
	 */
	public abstract java.lang.String getNombre_contratista();
	/**
	 * Set accessor for persistent attribute: nombre_contratista
	 */
	public abstract void setNombre_contratista(
		java.lang.String newNombre_contratista);
	/**
	 * Get accessor for persistent attribute: cod_franqueo
	 */
	public abstract java.lang.String getCod_franqueo();
	/**
	 * Set accessor for persistent attribute: cod_franqueo
	 */
	public abstract void setCod_franqueo(java.lang.String newCod_franqueo);
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public abstract java.lang.String getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public abstract void setQuiebre(java.lang.String newQuiebre);
	/**
	 * Get accessor for persistent attribute: fecha_mod
	 */
	public abstract java.sql.Timestamp getFecha_mod();
	/**
	 * Set accessor for persistent attribute: fecha_mod
	 */
	public abstract void setFecha_mod(java.sql.Timestamp newFecha_mod);
}
