package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Mensaje_estado_st
 */
public abstract class Mensaje_estado_stBean implements javax.ejb.EntityBean {
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
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Mensaje_estado_stKey ejbCreate(
		java.lang.Long correlativo)
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public abstract java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public abstract void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
	/**
	 * Get accessor for persistent attribute: cod_conector
	 */
	public abstract java.lang.Integer getCod_conector();
	/**
	 * Set accessor for persistent attribute: cod_conector
	 */
	public abstract void setCod_conector(java.lang.Integer newCod_conector);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public abstract java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public abstract void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public abstract java.lang.String getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public abstract void setFecha_cierre(java.lang.String newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: cod_estado
	 */
	public abstract java.lang.Integer getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public abstract void setCod_estado(java.lang.Integer newCod_estado);
	/**
	 * Get accessor for persistent attribute: cod_actividad_generadora
	 */
	public abstract java.lang.String getCod_actividad_generadora();
	/**
	 * Set accessor for persistent attribute: cod_actividad_generadora
	 */
	public abstract void setCod_actividad_generadora(
		java.lang.String newCod_actividad_generadora);
	/**
	 * Get accessor for persistent attribute: area
	 */
	public abstract java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public abstract void setArea(java.lang.Integer newArea);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.String newAccion);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public abstract java.lang.String getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public abstract void setTelefono(java.lang.String newTelefono);
	/**
	 * Get accessor for persistent attribute: serial
	 */
	public abstract java.lang.String getSerial();
	/**
	 * Set accessor for persistent attribute: serial
	 */
	public abstract void setSerial(java.lang.String newSerial);
	/**
	 * Get accessor for persistent attribute: observaciones
	 */
	public abstract java.lang.String getObservaciones();
	/**
	 * Set accessor for persistent attribute: observaciones
	 */
	public abstract void setObservaciones(java.lang.String newObservaciones);
	/**
	 * Get accessor for persistent attribute: appNumber
	 */
	public abstract java.lang.String getAppNumber();
	/**
	 * Set accessor for persistent attribute: appNumber
	 */
	public abstract void setAppNumber(java.lang.String newAppNumber);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public abstract java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public abstract void setReintentos(java.lang.Long newReintentos);
}
