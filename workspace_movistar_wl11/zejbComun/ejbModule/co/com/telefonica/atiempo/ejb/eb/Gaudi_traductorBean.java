package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Gaudi_traductor
 */
public abstract class Gaudi_traductorBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Gaudi_traductorKey ejbCreate(
		java.lang.Long id_correlativo)
		throws javax.ejb.CreateException {
		setId_correlativo(id_correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_correlativo)
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
	 * Get accessor for persistent attribute: id_correlativo
	 */
	public abstract java.lang.Long getId_correlativo();
	/**
	 * Set accessor for persistent attribute: id_correlativo
	 */
	public abstract void setId_correlativo(java.lang.Long newId_correlativo);
	/**
	 * Get accessor for persistent attribute: codigo
	 */
	public abstract java.lang.String getCodigo();
	/**
	 * Set accessor for persistent attribute: codigo
	 */
	public abstract void setCodigo(java.lang.String newCodigo);
	/**
	 * Get accessor for persistent attribute: valor_vpi
	 */
	public abstract java.lang.String getValor_vpi();
	/**
	 * Set accessor for persistent attribute: valor_vpi
	 */
	public abstract void setValor_vpi(java.lang.String newValor_vpi);
	/**
	 * Get accessor for persistent attribute: valor_gaudi
	 */
	public abstract java.lang.String getValor_gaudi();
	/**
	 * Set accessor for persistent attribute: valor_gaudi
	 */
	public abstract void setValor_gaudi(java.lang.String newValor_gaudi);
	/**
	 * Get accessor for persistent attribute: codigo_vpi
	 */
	public abstract java.lang.String getCodigo_vpi();
	/**
	 * Set accessor for persistent attribute: codigo_vpi
	 */
	public abstract void setCodigo_vpi(java.lang.String newCodigo_vpi);
}
