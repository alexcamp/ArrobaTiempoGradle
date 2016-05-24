package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Controlvisita
 */
public abstract class ControlvisitaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey ejbCreate(
		java.sql.Timestamp fechahora_registro,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setFechahora_registro(fechahora_registro);
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK = (co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
			.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.sql.Timestamp fechahora_registro,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setPeticion(argPeticion);
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
	 * Get accessor for persistent attribute: fechahora_llegada
	 */
	public abstract java.sql.Timestamp getFechahora_llegada();
	/**
	 * Set accessor for persistent attribute: fechahora_llegada
	 */
	public abstract void setFechahora_llegada(
		java.sql.Timestamp newFechahora_llegada);
	/**
	 * Get accessor for persistent attribute: fechahora_salida
	 */
	public abstract java.sql.Timestamp getFechahora_salida();
	/**
	 * Set accessor for persistent attribute: fechahora_salida
	 */
	public abstract void setFechahora_salida(
		java.sql.Timestamp newFechahora_salida);
	/**
	 * Get accessor for persistent attribute: fechahora_registro
	 */
	public abstract java.sql.Timestamp getFechahora_registro();
	/**
	 * Set accessor for persistent attribute: fechahora_registro
	 */
	public abstract void setFechahora_registro(
		java.sql.Timestamp newFechahora_registro);
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
	 * Get accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract java.lang.Long getPeticion_peti_numero();
	/**
	 * Set accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract void setPeticion_peti_numero(
		java.lang.Long newPeticion_peti_numero);
	
	/**
	 * Get accessor for persistent attribute: tecnico
	 */
	public abstract java.lang.String getTecnico();
	/**
	 * Set accessor for persistent attribute: tecnico
	 */
	public abstract void setTecnico(java.lang.String newTecnico);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey ejbCreate(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException {
		setFechahora_registro(fechahora_registro);
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_peti_numero) throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: cod_caudem
	 */
	public abstract java.lang.Long getCod_caudem();
	/**
	 * Set accessor for persistent attribute: cod_caudem
	 */
	public abstract void setCod_caudem(java.lang.Long newCod_caudem);
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public abstract java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public abstract void setPs_id(java.lang.Long newPs_id);
}
