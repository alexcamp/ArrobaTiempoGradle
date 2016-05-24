package co.com.telefonica.atiempo.ejb.eb;

import com.telefonica_chile.atiempo.utiles.Fecha;

/**
 * Bean implementation class for Enterprise Bean: Inventario_dth
 */
public abstract class Inventario_dthBean implements javax.ejb.EntityBean {
	
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
	public co.com.telefonica.atiempo.ejb.eb.Inventario_dthKey ejbCreate(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK =
			(co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
				.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		setEstado("2");//espera
		setMarca_hora(new Fecha().getTimestamp());
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
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
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.String getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.String newEstado);
	/**
	 * Get accessor for persistent attribute: marca_hora
	 */
	public abstract java.sql.Timestamp getMarca_hora();
	/**
	 * Set accessor for persistent attribute: marca_hora
	 */
	public abstract void setMarca_hora(java.sql.Timestamp newMarca_hora);
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
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Inventario_dthKey ejbCreate(
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
	}
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
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
}
