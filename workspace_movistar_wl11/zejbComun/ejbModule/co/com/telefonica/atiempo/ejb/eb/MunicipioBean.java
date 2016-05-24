package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Municipio
 */
public abstract class MunicipioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.MunicipioKey ejbCreate(
		java.lang.String cod_mun)
		throws javax.ejb.CreateException {
		setCod_mun(cod_mun);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_mun)
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
	 * Get accessor for persistent attribute: cod_mun
	 */
	public abstract java.lang.String getCod_mun();
	/**
	 * Set accessor for persistent attribute: cod_mun
	 */
	public abstract void setCod_mun(java.lang.String newCod_mun);
	/**
	 * Get accessor for persistent attribute: nombre_municipio
	 */
	public abstract java.lang.String getNombre_municipio();
	/**
	 * Set accessor for persistent attribute: nombre_municipio
	 */
	public abstract void setNombre_municipio(
		java.lang.String newNombre_municipio);
	/**
	 * Get accessor for persistent attribute: descripcion_municipio
	 */
	public abstract java.lang.String getDescripcion_municipio();
	/**
	 * Set accessor for persistent attribute: descripcion_municipio
	 */
	public abstract void setDescripcion_municipio(
		java.lang.String newDescripcion_municipio);
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.DepartamentoLocal getDepartamento();
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDepartamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aDepartamento);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalidad(java.util.Collection aLocalidad);
}
