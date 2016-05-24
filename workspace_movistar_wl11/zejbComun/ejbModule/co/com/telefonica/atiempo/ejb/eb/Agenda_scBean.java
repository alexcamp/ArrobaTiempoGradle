package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Agenda_sc
 */
public abstract class Agenda_scBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scKey ejbCreate(
		java.lang.String id_actuacion) throws javax.ejb.CreateException {
		setId_actuacion(id_actuacion);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String id_actuacion)
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
	 * Get accessor for persistent attribute: id_actuacion
	 */
	public abstract java.lang.String getId_actuacion();
	/**
	 * Set accessor for persistent attribute: id_actuacion
	 */
	public abstract void setId_actuacion(java.lang.String newId_actuacion);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: fecha_reagm
	 */
	public abstract java.sql.Timestamp getFecha_reagm();
	/**
	 * Set accessor for persistent attribute: fecha_reagm
	 */
	public abstract void setFecha_reagm(java.sql.Timestamp newFecha_reagm);
	/**
	 * Get accessor for persistent attribute: fecha_mod
	 */
	public abstract java.sql.Timestamp getFecha_mod();
	/**
	 * Set accessor for persistent attribute: fecha_mod
	 */
	public abstract void setFecha_mod(java.sql.Timestamp newFecha_mod);
	/**
	 * Get accessor for persistent attribute: nombre_contratista
	 */
	public abstract java.lang.String getNombre_contratista();
	/**
	 * Set accessor for persistent attribute: nombre_contratista
	 */
	public abstract void setNombre_contratista(
		java.lang.String newNombre_contratista);
	/**
	 * Get accessor for persistent attribute: cod_franqueo
	 */
	public abstract java.lang.String getCod_franqueo();
	/**
	 * Set accessor for persistent attribute: cod_franqueo
	 */
	public abstract void setCod_franqueo(java.lang.String newCod_franqueo);
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public abstract java.lang.String getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public abstract void setQuiebre(java.lang.String newQuiebre);
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getTmp_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTmp_agenda_sc(java.util.Collection aTmp_agenda_sc);
}