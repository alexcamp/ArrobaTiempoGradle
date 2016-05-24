package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Traslado_soloba
 */
public abstract class Traslado_solobaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Traslado_solobaKey ejbCreate(
		java.lang.Long id_trasoba)
		throws javax.ejb.CreateException {
		setId_trasoba(id_trasoba);
		setCierre_alta("N");
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_trasoba)
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
	 * Get accessor for persistent attribute: id_trasoba
	 */
	public abstract java.lang.Long getId_trasoba();
	/**
	 * Set accessor for persistent attribute: id_trasoba
	 */
	public abstract void setId_trasoba(java.lang.Long newId_trasoba);
	/**
	 * Get accessor for persistent attribute: num_ide_nu_baja
	 */
	public abstract java.lang.String getNum_ide_nu_baja();
	/**
	 * Set accessor for persistent attribute: num_ide_nu_baja
	 */
	public abstract void setNum_ide_nu_baja(
		java.lang.String newNum_ide_nu_baja);
	/**
	 * Get accessor for persistent attribute: linea_anterior
	 */
	public abstract java.lang.String getLinea_anterior();
	/**
	 * Set accessor for persistent attribute: linea_anterior
	 */
	public abstract void setLinea_anterior(java.lang.String newLinea_anterior);
	/**
	 * Get accessor for persistent attribute: cierre_alta
	 */
	public abstract java.lang.String getCierre_alta();
	/**
	 * Set accessor for persistent attribute: cierre_alta
	 */
	public abstract void setCierre_alta(java.lang.String newCierre_alta);
	/**
	 * Get accessor for persistent attribute: peti_alta
	 */
	public abstract java.lang.Long getPeti_alta();
	/**
	 * Set accessor for persistent attribute: peti_alta
	 */
	public abstract void setPeti_alta(java.lang.Long newPeti_alta);
	/**
	 * Get accessor for persistent attribute: peti_baja
	 */
	public abstract java.lang.Long getPeti_baja();
	/**
	 * Set accessor for persistent attribute: peti_baja
	 */
	public abstract void setPeti_baja(java.lang.Long newPeti_baja);
}
