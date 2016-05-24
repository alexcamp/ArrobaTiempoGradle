package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Conexion_Interfaz
 */
public abstract class Conexion_InterfazBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey ejbCreate(
		java.lang.String interfaz,
		java.lang.String propiedad) throws javax.ejb.CreateException {
		setInterfaz(interfaz);
		setPropiedad(propiedad);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String interfaz,
		java.lang.String propiedad) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: interfaz
	 */
	public abstract java.lang.String getInterfaz();
	/**
	 * Set accessor for persistent attribute: interfaz
	 */
	public abstract void setInterfaz(java.lang.String newInterfaz);
	/**
	 * Get accessor for persistent attribute: propiedad
	 */
	public abstract java.lang.String getPropiedad();
	/**
	 * Set accessor for persistent attribute: propiedad
	 */
	public abstract void setPropiedad(java.lang.String newPropiedad);
	/**
	 * Get accessor for persistent attribute: valor
	 */
	public abstract java.lang.String getValor();
	/**
	 * Set accessor for persistent attribute: valor
	 */
	public abstract void setValor(java.lang.String newValor);
}