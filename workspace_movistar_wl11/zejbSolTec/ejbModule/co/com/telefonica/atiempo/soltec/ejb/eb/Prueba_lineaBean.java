package co.com.telefonica.atiempo.soltec.ejb.eb;

import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;

/**
 * Bean implementation class for Enterprise Bean: Prueba_linea
 */
public abstract class Prueba_lineaBean implements javax.ejb.EntityBean {
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
		
	public co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaKey ejbCreate(
				java.lang.Long id_prueba,Peticion_stLocal peticion_stLocal,Catalago_prueba_lineaLocal cat,Long usua_id)
				throws javax.ejb.CreateException {
				setId_prueba(id_prueba);
				setUsua_id(usua_id);
				return null;
			}
	
	
	public void ejbPostCreate(java.lang.Long id_prueba,Peticion_stLocal peticion_stLocal,Catalago_prueba_lineaLocal cat,Long usua_id)
			throws javax.ejb.CreateException {
				setPeticion_st(peticion_stLocal);
				setCatalago_prueba_linea(cat);
		}

	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_prueba)
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
	 * Get accessor for persistent attribute: id_prueba
	 */
	public abstract java.lang.Long getId_prueba();
	/**
	 * Set accessor for persistent attribute: id_prueba
	 */
	public abstract void setId_prueba(java.lang.Long newId_prueba);
	/**
	 * Get accessor for persistent attribute: observacion
	 */
	public abstract java.lang.String getObservacion();
	/**
	 * Set accessor for persistent attribute: observacion
	 */
	public abstract void setObservacion(java.lang.String newObservacion);
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
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaKey ejbCreate(
		java.lang.Long id_prueba)
		throws javax.ejb.CreateException {
		setId_prueba(id_prueba);
		return null;
	}
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.actividades
		.cancelacion
		.ejb
		.sb
		.Catalago_prueba_lineaLocal getCatalago_prueba_linea();
	/**
	 * This method was generated for supporting the relationship role named catalago_prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCatalago_prueba_linea(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.actividades
			.cancelacion
			.ejb
			.sb
			.Catalago_prueba_lineaLocal aCatalago_prueba_linea);
}
