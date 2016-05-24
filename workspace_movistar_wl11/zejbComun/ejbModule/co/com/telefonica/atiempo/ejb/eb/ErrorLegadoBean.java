package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: ErrorLegado
 */
public abstract class ErrorLegadoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoKey ejbCreate(
		java.lang.Long id)
		throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id)
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
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Long newId);
	/**
	 * Get accessor for persistent attribute: idCausa
	 */
	public abstract java.lang.Long getIdCausa();
	/**
	 * Set accessor for persistent attribute: idCausa
	 */
	public abstract void setIdCausa(java.lang.Long newIdCausa);
	/**
	 * Get accessor for persistent attribute: idTipoError
	 */
	public abstract java.lang.Long getIdTipoError();
	/**
	 * Set accessor for persistent attribute: idTipoError
	 */
	public abstract void setIdTipoError(java.lang.Long newIdTipoError);
	/**
	 * Get accessor for persistent attribute: numeroTr
	 */
	public abstract java.lang.String getNumeroTr();
	/**
	 * Set accessor for persistent attribute: numeroTr
	 */
	public abstract void setNumeroTr(java.lang.String newNumeroTr);
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCatalogo_causal(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named tipoerror.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.TipoErrorLocal getTipoerror();
	/**
	 * This method was generated for supporting the relationship role named tipoerror.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTipoerror(
		co.com.telefonica.atiempo.ejb.eb.TipoErrorLocal aTipoerror);
	/**
	 * Get accessor for persistent attribute: codigoError
	 */
	public abstract java.lang.String getCodigoError();
	/**
	 * Set accessor for persistent attribute: codigoError
	 */
	public abstract void setCodigoError(java.lang.String newCodigoError);
	/**
	 * Get accessor for persistent attribute: plataforma
	 */
	public abstract java.lang.String getPlataforma();
	/**
	 * Set accessor for persistent attribute: plataforma
	 */
	public abstract void setPlataforma(java.lang.String newPlataforma);
}
