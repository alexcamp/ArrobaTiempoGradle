package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Catalogo_causal
 */
public abstract class Catalogo_causalBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey ejbCreate(
		java.lang.Long cod_causal)
		throws javax.ejb.CreateException {
		setCod_causal(cod_causal);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long cod_causal)
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
	 * Get accessor for persistent attribute: cod_causal
	 */
	public abstract java.lang.Long getCod_causal();
	/**
	 * Set accessor for persistent attribute: cod_causal
	 */
	public abstract void setCod_causal(java.lang.Long newCod_causal);
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public abstract java.lang.Integer getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public abstract void setQuiebre(java.lang.Integer newQuiebre);
	/**
	 * Get accessor for persistent attribute: descripcion_causal
	 */
	public abstract java.lang.String getDescripcion_causal();
	/**
	 * Set accessor for persistent attribute: descripcion_causal
	 */
	public abstract void setDescripcion_causal(
		java.lang.String newDescripcion_causal);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Actividad_flujoLocal getActividad_flujo();
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad_flujo(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_peticion(
		java.util.Collection aCausal_peticion);
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_ps_oc_actividad();
	/**
	 * This method was generated for supporting the relationship role named causal_ps_oc_actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_ps_oc_actividad(
		java.util.Collection aCausal_ps_oc_actividad);
	/**
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ErrorLegadoLocal getErrorlegado();
	/**
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setErrorlegado(
		co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal anErrorlegado);
	/**
	 * Get accessor for persistent attribute: gestionable
	 */
	public abstract java.lang.Short getGestionable();
	/**
	 * Set accessor for persistent attribute: gestionable
	 */
	public abstract void setGestionable(java.lang.Short newGestionable);
}
