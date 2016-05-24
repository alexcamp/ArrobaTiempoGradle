package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tecnico
 */
public abstract class TecnicoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.TecnicoKey ejbCreate(
		java.lang.String cod_tecnico)
		throws javax.ejb.CreateException {
		setCod_tecnico(cod_tecnico);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_tecnico)
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
	 * Get accessor for persistent attribute: nombre
	 */
	public abstract java.lang.String getNombre();
	/**
	 * Set accessor for persistent attribute: nombre
	 */
	public abstract void setNombre(java.lang.String newNombre);
	/**
	 * Get accessor for persistent attribute: apellido
	 */
	public abstract java.lang.String getApellido();
	/**
	 * Set accessor for persistent attribute: apellido
	 */
	public abstract void setApellido(java.lang.String newApellido);
	/**
	 * Get accessor for persistent attribute: cod_tecnico
	 */
	public abstract java.lang.String getCod_tecnico();
	/**
	 * Set accessor for persistent attribute: cod_tecnico
	 */
	public abstract void setCod_tecnico(java.lang.String newCod_tecnico);
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
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.EmpresaLocal getEmpresa();
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEmpresa(
		co.com.telefonica.atiempo.ejb.eb.EmpresaLocal anEmpresa);
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public abstract java.lang.Short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public abstract void setHabilitado(java.lang.Short newHabilitado);
}
