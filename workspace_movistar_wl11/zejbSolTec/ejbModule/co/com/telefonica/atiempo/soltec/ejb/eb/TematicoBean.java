package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Tematico
 */
public abstract class TematicoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.TematicoKey ejbCreate(
		java.lang.Long id_tematico)
		throws javax.ejb.CreateException {
		setId_tematico(id_tematico);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_tematico)
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
	 * Get accessor for persistent attribute: cod_tematico
	 */
	public abstract java.lang.String getCod_tematico();
	/**
	 * Set accessor for persistent attribute: cod_tematico
	 */
	public abstract void setCod_tematico(java.lang.String newCod_tematico);
	/**
	 * Get accessor for persistent attribute: origen
	 */
	public abstract java.lang.String getOrigen();
	/**
	 * Set accessor for persistent attribute: origen
	 */
	public abstract void setOrigen(java.lang.String newOrigen);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: id_tematico
	 */
	public abstract java.lang.Long getId_tematico();
	/**
	 * Set accessor for persistent attribute: id_tematico
	 */
	public abstract void setId_tematico(java.lang.Long newId_tematico);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);

	public TematicoKey ejbCreate(Long id_tematico,String origen,Peticion_stLocal pet,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		setId_tematico(id_tematico);
		setCod_tematico(codtematico);
		setOrigen(origen);
		if(correlativo!=null)
			setCorrelativo(correlativo);
		return null;
	}
	
	public void ejbPostCreate(Long id_tematico,String origen,Peticion_stLocal pet,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		setPeticion_st(pet);
	}		
}
