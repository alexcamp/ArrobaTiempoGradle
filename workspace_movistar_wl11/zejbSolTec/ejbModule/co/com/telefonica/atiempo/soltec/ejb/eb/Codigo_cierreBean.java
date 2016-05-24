package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Codigo_cierre
 */
public abstract class Codigo_cierreBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey ejbCreate(
		java.lang.String cod_cierre)
		throws javax.ejb.CreateException {
		setCod_cierre(cod_cierre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_cierre)
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
	 * Get accessor for persistent attribute: cod_cierre
	 */
	public abstract java.lang.String getCod_cierre();
	/**
	 * Set accessor for persistent attribute: cod_cierre
	 */
	public abstract void setCod_cierre(java.lang.String newCod_cierre);
	/**
	 * Get accessor for persistent attribute: descripcion_cierre
	 */
	public abstract java.lang.String getDescripcion_cierre();
	/**
	 * Set accessor for persistent attribute: descripcion_cierre
	 */
	public abstract void setDescripcion_cierre(
		java.lang.String newDescripcion_cierre);
	/**
	 * Get accessor for persistent attribute: causa_incidencia
	 */
	public abstract java.lang.String getCausa_incidencia();
	/**
	 * Set accessor for persistent attribute: causa_incidencia
	 */
	public abstract void setCausa_incidencia(
		java.lang.String newCausa_incidencia);
	/**
	 * Get accessor for persistent attribute: nombre_cod_cierre
	 */
	public abstract java.lang.String getNombre_cod_cierre();
	/**
	 * Set accessor for persistent attribute: nombre_cod_cierre
	 */
	public abstract void setNombre_cod_cierre(
		java.lang.String newNombre_cod_cierre);
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.LocalizacionLocal getLocalizacion();
	/**
	 * This method was generated for supporting the relationship role named localizacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalizacion(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.LocalizacionLocal aLocalizacion);
	/**
	 * Get accessor for persistent attribute: resp_cliente
	 */
	public abstract java.lang.String getResp_cliente();
	/**
	 * Set accessor for persistent attribute: resp_cliente
	 */
	public abstract void setResp_cliente(java.lang.String newResp_cliente);
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCodigo_cierre_peticion();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_cierre_peticion(
		java.util.Collection aCodigo_cierre_peticion);
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public abstract short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public abstract void setHabilitado(short newHabilitado);
	/**
	 * Get accessor for persistent attribute: obligatorio_marca
	 */
	public abstract short getObligatorio_marca();
	/**
	 * Set accessor for persistent attribute: obligatorio_marca
	 */
	public abstract void setObligatorio_marca(short newObligatorio_marca);
	/**
	 * Get accessor for persistent attribute: obligatorio_modem
	 */
	public abstract short getObligatorio_modem();
	/**
	 * Set accessor for persistent attribute: obligatorio_modem
	 */
	public abstract void setObligatorio_modem(short newObligatorio_modem);
}
