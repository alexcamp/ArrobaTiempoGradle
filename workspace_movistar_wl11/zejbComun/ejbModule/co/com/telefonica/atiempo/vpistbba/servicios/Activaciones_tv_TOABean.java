package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Activaciones_tv_TOA
 */
public abstract class Activaciones_tv_TOABean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Activaciones_tv_TOAKey ejbCreate(
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
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: id_actuacion
	 */
	public abstract java.lang.String getId_actuacion();
	/**
	 * Set accessor for persistent attribute: id_actuacion
	 */
	public abstract void setId_actuacion(java.lang.String newId_actuacion);
	/**
	 * Get accessor for persistent attribute: cod_appt_number
	 */
	public abstract java.lang.String getCod_appt_number();
	/**
	 * Set accessor for persistent attribute: cod_appt_number
	 */
	public abstract void setCod_appt_number(java.lang.String newCod_appt_number);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public abstract java.sql.Timestamp getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public abstract void setFecha_envio(java.sql.Timestamp newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public abstract java.sql.Timestamp getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public abstract void setFecha_cierre(java.sql.Timestamp newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.String getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.String newEstado);
	/**
	 * Get accessor for persistent attribute: reintentos
	 */
	public abstract java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public abstract void setReintentos(java.lang.Long newReintentos);
}