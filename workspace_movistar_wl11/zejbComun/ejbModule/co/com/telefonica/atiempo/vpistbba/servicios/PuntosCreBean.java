package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: PuntosCre
 */
public abstract class PuntosCreBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreClave ejbCreate(
		java.lang.Long id) throws javax.ejb.CreateException {
		setId(id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id)
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
	 * Get accessor for persistent attribute: tipo_canal
	 */
	public abstract java.lang.String getTipo_canal();
	/**
	 * Set accessor for persistent attribute: tipo_canal
	 */
	public abstract void setTipo_canal(java.lang.String newTipo_canal);
	/**
	 * Get accessor for persistent attribute: zona
	 */
	public abstract java.lang.String getZona();
	/**
	 * Set accessor for persistent attribute: zona
	 */
	public abstract void setZona(java.lang.String newZona);
	/**
	 * Get accessor for persistent attribute: ciudad
	 */
	public abstract java.lang.String getCiudad();
	/**
	 * Set accessor for persistent attribute: ciudad
	 */
	public abstract void setCiudad(java.lang.String newCiudad);
	/**
	 * Get accessor for persistent attribute: direccion
	 */
	public abstract java.lang.String getDireccion();
	/**
	 * Set accessor for persistent attribute: direccion
	 */
	public abstract void setDireccion(java.lang.String newDireccion);
	/**
	 * Get accessor for persistent attribute: horario
	 */
	public abstract java.lang.String getHorario();
	/**
	 * Set accessor for persistent attribute: horario
	 */
	public abstract void setHorario(java.lang.String newHorario);
	/**
	 * Get accessor for persistent attribute: responsable
	 */
	public abstract java.lang.String getResponsable();
	/**
	 * Set accessor for persistent attribute: responsable
	 */
	public abstract void setResponsable(java.lang.String newResponsable);
	/**
	 * Get accessor for persistent attribute: emite_carta
	 */
	public abstract java.lang.String getEmite_carta();
	/**
	 * Set accessor for persistent attribute: emite_carta
	 */
	public abstract void setEmite_carta(java.lang.String newEmite_carta);
	/**
	 * Get accessor for persistent attribute: id
	 */
	public abstract java.lang.Long getId();
	/**
	 * Set accessor for persistent attribute: id
	 */
	public abstract void setId(java.lang.Long newId);
}