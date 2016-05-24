package co.com.telefonica.atiempo.soltec.ejb.eb;

import java.sql.Timestamp;

/**
 * Bean implementation class for Enterprise Bean: Codigo_cierre_peticion
 */
public abstract class Codigo_cierre_peticionBean
	implements javax.ejb.EntityBean {
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
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierre_peticionKey ejbCreate(java.lang.Long id_cierre,Long idUser,Long idAct, Peticion_stLocal peticion, Codigo_cierreLocal cierre, Timestamp fecha)
		throws javax.ejb.CreateException {
		setId_cierre(id_cierre);
		setAct_id(idAct);
		setUsua_id(idUser);
		setFecha(fecha);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_cierre,Long idUser,Long idAct, Peticion_stLocal peticion, Codigo_cierreLocal cierre, Timestamp fecha)
		throws javax.ejb.CreateException {
			setPeticion_st(peticion);
			setCodigo_cierre(cierre);
	}
	
	/**
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierre_peticionKey ejbCreate(java.lang.Long id_cierre)
		throws javax.ejb.CreateException {
		setId_cierre(id_cierre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_cierre)
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
	 * Get accessor for persistent attribute: id_cierre
	 */
	public abstract java.lang.Long getId_cierre();
	/**
	 * Set accessor for persistent attribute: id_cierre
	 */
	public abstract void setId_cierre(java.lang.Long newId_cierre);
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public abstract java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public abstract void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public abstract java.sql.Timestamp getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public abstract void setFecha(java.sql.Timestamp newFecha);
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
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierreLocal getCodigo_cierre();
	/**
	 * This method was generated for supporting the relationship role named codigo_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCodigo_cierre(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Codigo_cierreLocal aCodigo_cierre);
}
