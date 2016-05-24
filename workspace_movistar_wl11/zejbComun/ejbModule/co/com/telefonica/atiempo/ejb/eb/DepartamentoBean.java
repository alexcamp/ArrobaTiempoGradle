package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Departamento
 */
public abstract class DepartamentoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.DepartamentoKey ejbCreate(
		java.lang.String cod_dpt)
		throws javax.ejb.CreateException {
		setCod_dpt(cod_dpt);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String cod_dpt)
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
	 * Get accessor for persistent attribute: nombre_region
	 */
	public abstract java.lang.String getNombre_region();
	/**
	 * Set accessor for persistent attribute: nombre_region
	 */
	public abstract void setNombre_region(java.lang.String newNombre_region);
	/**
	 * Get accessor for persistent attribute: nombre_departamento
	 */
	public abstract java.lang.String getNombre_departamento();
	/**
	 * Set accessor for persistent attribute: nombre_departamento
	 */
	public abstract void setNombre_departamento(
		java.lang.String newNombre_departamento);
	/**
	 * Get accessor for persistent attribute: descripcion_departamento
	 */
	public abstract java.lang.String getDescripcion_departamento();
	/**
	 * Set accessor for persistent attribute: descripcion_departamento
	 */
	public abstract void setDescripcion_departamento(
		java.lang.String newDescripcion_departamento);
	/**
	 * Get accessor for persistent attribute: cod_dpt
	 */
	public abstract java.lang.String getCod_dpt();
	/**
	 * Set accessor for persistent attribute: cod_dpt
	 */
	public abstract void setCod_dpt(java.lang.String newCod_dpt);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDireccion_atis(
		java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getMunicipio();
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setMunicipio(java.util.Collection aMunicipio);
}
