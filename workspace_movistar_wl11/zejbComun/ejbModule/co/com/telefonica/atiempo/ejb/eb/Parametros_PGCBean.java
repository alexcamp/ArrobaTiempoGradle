package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Parametros_PGC
 */
public abstract class Parametros_PGCBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Parametros_PGCKey ejbCreate(
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
	 * Get accessor for persistent attribute: reintentos
	 */
	public abstract java.lang.Long getReintentos();
	/**
	 * Set accessor for persistent attribute: reintentos
	 */
	public abstract void setReintentos(java.lang.Long newReintentos);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: usuario
	 */
	public abstract java.lang.String getUsuario();
	/**
	 * Set accessor for persistent attribute: usuario
	 */
	public abstract void setUsuario(java.lang.String newUsuario);
	/**
	 * Get accessor for persistent attribute: cod_segmento
	 */
	public abstract java.lang.Long getCod_segmento();
	/**
	 * Set accessor for persistent attribute: cod_segmento
	 */
	public abstract void setCod_segmento(java.lang.Long newCod_segmento);
	/**
	 * Get accessor for persistent attribute: cod_subsegmento
	 */
	public abstract java.lang.Long getCod_subsegmento();
	/**
	 * Set accessor for persistent attribute: cod_subsegmento
	 */
	public abstract void setCod_subsegmento(java.lang.Long newCod_subsegmento);
}