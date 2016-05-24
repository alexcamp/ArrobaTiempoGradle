package co.com.telefonica.atiempo.ejb.eb;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

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
	public TematicoKey ejbCreate(Long id_tematico,String origen,Long petinumero,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		setCod_tematico(codtematico);
		setOrigen(origen);
		setId_tematico(id_tematico);
		if(correlativo!=null)
			setCorrelativo(correlativo);
		return null;
	}
	
	public TematicoKey ejbCreate(Long id_tematico,String origen,PeticionLocal pet,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		setId_tematico(id_tematico);
		setCod_tematico(codtematico);
		setOrigen(origen);
		if(correlativo!=null)
			setCorrelativo(correlativo);
		return null;
	}
	
	public void ejbPostCreate(Long id_tematico,String origen,PeticionLocal pet,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		setPeticion(pet);
	}
	
	public void ejbPostCreate(Long id_tematico,String origen,Long petinumero,String codtematico,Long correlativo) throws javax.ejb.CreateException
	{
		try
		{
			PeticionKey peticionKey=new PeticionKey(petinumero);
			PeticionLocalHome home=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal local=home.findByPrimaryKey(peticionKey);
			setPeticion(local);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new javax.ejb.CreateException(e.getMessage());
		}
	}
	
	
	public co.com.telefonica.atiempo.ejb.eb.TematicoKey ejbCreate(
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
	 * Get accessor for persistent attribute: id_tematico
	 */
	public abstract java.lang.Long getId_tematico();
	/**
	 * Set accessor for persistent attribute: id_tematico
	 */
	public abstract void setId_tematico(java.lang.Long newId_tematico);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
}
