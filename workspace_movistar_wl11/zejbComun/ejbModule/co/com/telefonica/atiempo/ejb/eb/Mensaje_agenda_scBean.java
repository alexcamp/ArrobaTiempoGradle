package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Mensaje_agenda_sc
 */
public abstract class Mensaje_agenda_scBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scKey ejbCreate(
		java.lang.Long correlativo) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public abstract java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public abstract void setFecha_envio(java.lang.String newFecha_envio);
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
	 * Get accessor for persistent attribute: id_agenda
	 */
	public abstract java.lang.String getId_agenda();
	/**
	 * Set accessor for persistent attribute: id_agenda
	 */
	public abstract void setId_agenda(java.lang.String newId_agenda);
	/**
	 * Get accessor for persistent attribute: error
	 */
	public abstract java.lang.String getError();
	/**
	 * Set accessor for persistent attribute: error
	 */
	public abstract void setError(java.lang.String newError);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public abstract java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public abstract void setDesc_error(java.lang.String newDesc_error);
	/**
	 * Get accessor for persistent attribute: tipo_mensaje
	 */
	public abstract java.lang.String getTipo_mensaje();
	/**
	 * Set accessor for persistent attribute: tipo_mensaje
	 */
	public abstract void setTipo_mensaje(java.lang.String newTipo_mensaje);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: cod_estado
	 */
	public abstract java.lang.Integer getCod_estado();
	/**
	 * Set accessor for persistent attribute: cod_estado
	 */
	public abstract void setCod_estado(java.lang.Integer newCod_estado);
	/**
	 * Get accessor for persistent attribute: apptNumber
	 */
	public abstract java.lang.String getApptNumber();
	/**
	 * Set accessor for persistent attribute: apptNumber
	 */
	public abstract void setApptNumber(java.lang.String newApptNumber);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public abstract java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public abstract void setReintentos(java.lang.Long newReintentos);
}