package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Atiempo_Interfaz
 */
public abstract class Atiempo_InterfazBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Atiempo_InterfazKey ejbCreate(
		java.lang.String nombre) throws javax.ejb.CreateException {
		setNombre(nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String nombre)
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
	 * Get accessor for persistent attribute: nombre
	 */
	public abstract java.lang.String getNombre();
	/**
	 * Set accessor for persistent attribute: nombre
	 */
	public abstract void setNombre(java.lang.String newNombre);
	/**
	 * Get accessor for persistent attribute: jndi
	 */
	public abstract java.lang.String getJndi();
	/**
	 * Set accessor for persistent attribute: jndi
	 */
	public abstract void setJndi(java.lang.String newJndi);
	/**
	 * Get accessor for persistent attribute: esquema
	 */
	public abstract java.lang.String getEsquema();
	/**
	 * Set accessor for persistent attribute: esquema
	 */
	public abstract void setEsquema(java.lang.String newEsquema);
}