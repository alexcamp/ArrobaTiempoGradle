package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Mensaje_estado_tv
 */
public abstract class Mensaje_estado_tvBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey ejbCreate(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		
		//CR22115 - adocarmo - Inicio
		// seteo es_refresh en 0 como valor por defecto
		// si en la bd el campo es nullable no necesito agregar esto!!!!!
		setEs_refresh(new Short((short)0));
		//CR22115 - adocarmo - Fin
		
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
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public abstract java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public abstract void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
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
	 * Get accessor for persistent attribute: telefono
	 */
	public abstract java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public abstract void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: area
	 */
	public abstract java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public abstract void setArea(java.lang.Integer newArea);
	/**
	 * Get accessor for persistent attribute: id_error
	 */
	public abstract java.lang.String getId_error();
	/**
	 * Set accessor for persistent attribute: id_error
	 */
	public abstract void setId_error(java.lang.String newId_error);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public abstract java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public abstract void setDesc_error(java.lang.String newDesc_error);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estadoLocal getMensaje_estado();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMensaje_estado(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal aMensaje_estado);
	/**
	 * This method was generated for supporting the relationship role named conector.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ConectorLocal getConector();
	/**
	 * This method was generated for supporting the relationship role named conector.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setConector(
		co.com.telefonica.atiempo.ejb.eb.ConectorLocal aConector);
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

	//CR22115 - adocarmo - Inicio
	/**
	 * Get accessor for persistent attribute: es_refresh
	 */
	public abstract java.lang.Short getEs_refresh();
	/**
	 * Set accessor for persistent attribute: es_refresh
	 */
	public abstract void setEs_refresh(java.lang.Short newEs_refresh);
	//CR22115 - adocarmo - Fin
}
