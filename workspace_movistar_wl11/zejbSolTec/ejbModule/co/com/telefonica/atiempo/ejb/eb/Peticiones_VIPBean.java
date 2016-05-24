package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Bean implementation class for Enterprise Bean: Peticiones_VIP
 */
public abstract class Peticiones_VIPBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPKey ejbCreate(
		java.lang.Long correlativo) throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long correlativo)
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
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: localidad
	 */
	public abstract java.lang.String getLocalidad();
	/**
	 * Set accessor for persistent attribute: localidad
	 */
	public abstract void setLocalidad(java.lang.String newLocalidad);
	/**
	 * Get accessor for persistent attribute: reitero
	 */
	public abstract java.lang.String getReitero();
	/**
	 * Set accessor for persistent attribute: reitero
	 */
	public abstract void setReitero(java.lang.String newReitero);
	/**
	 * Get accessor for persistent attribute: infancia
	 */
	public abstract java.lang.String getInfancia();
	/**
	 * Set accessor for persistent attribute: infancia
	 */
	public abstract void setInfancia(java.lang.String newInfancia);
	/**
	 * Get accessor for persistent attribute: segmento
	 */
	public abstract java.lang.String getSegmento();
	/**
	 * Set accessor for persistent attribute: segmento
	 */
	public abstract void setSegmento(java.lang.String newSegmento);
	/**
	 * Get accessor for persistent attribute: subsegmento
	 */
	public abstract java.lang.String getSubsegmento();
	/**
	 * Set accessor for persistent attribute: subsegmento
	 */
	public abstract void setSubsegmento(java.lang.String newSubsegmento);
}