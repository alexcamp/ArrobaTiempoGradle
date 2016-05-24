package co.com.telefonica.atiempo.soltec.ejb.eb;
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaKey ejbCreate(
		java.sql.Timestamp fechahora_registro,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setFechahora_registro(fechahora_registro);
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey argPeticion_stPK =
			(co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Peticion_stKey) argPeticion_st
				.getPrimaryKey();
		setPeticion_st_cod_ave_cd(argPeticion_stPK.cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.sql.Timestamp fechahora_registro,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setPeticion_st(argPeticion_st);
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
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaKey ejbCreate(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
		setFechahora_registro(fechahora_registro);
		setPeticion_st_cod_ave_cd(peticion_st_cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract java.lang.Long getPeticion_st_cod_ave_cd();
	/**
	 * Set accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract void setPeticion_st_cod_ave_cd(
		java.lang.Long newPeticion_st_cod_ave_cd);
	/**
	 * Get accessor for persistent attribute: tecnico
	 */
	public abstract java.lang.String getTecnico();
	/**
	 * Set accessor for persistent attribute: tecnico
	 */
	public abstract void setTecnico(java.lang.String newTecnico);
}
