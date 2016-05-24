package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Rango
 */
public abstract class RangoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.RangoKey ejbCreate(
		java.lang.Integer id_rango)
		throws javax.ejb.CreateException {
		setId_rango(id_rango);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer id_rango)
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
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public abstract java.lang.Short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public abstract void setHabilitado(java.lang.Short newHabilitado);
	/**
	 * Get accessor for persistent attribute: nombre_rango
	 */
	public abstract java.lang.String getNombre_rango();
	/**
	 * Set accessor for persistent attribute: nombre_rango
	 */
	public abstract void setNombre_rango(java.lang.String newNombre_rango);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: hora_desde
	 */
	public abstract java.lang.String getHora_desde();
	/**
	 * Set accessor for persistent attribute: hora_desde
	 */
	public abstract void setHora_desde(java.lang.String newHora_desde);
	/**
	 * Get accessor for persistent attribute: hora_hasta
	 */
	public abstract java.lang.String getHora_hasta();
	/**
	 * Set accessor for persistent attribute: hora_hasta
	 */
	public abstract void setHora_hasta(java.lang.String newHora_hasta);
	/**
	 * Get accessor for persistent attribute: codigo_rango
	 */
	public abstract java.lang.String getCodigo_rango();
	/**
	 * Set accessor for persistent attribute: codigo_rango
	 */
	public abstract void setCodigo_rango(java.lang.String newCodigo_rango);
	/**
	 * Get accessor for persistent attribute: id_padre
	 */
	public abstract java.lang.Integer getId_padre();
	/**
	 * Set accessor for persistent attribute: id_padre
	 */
	public abstract void setId_padre(java.lang.Integer newId_padre);
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTecnico_peticion();
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTecnico_peticion(
		java.util.Collection aTecnico_peticion);
}
