package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Bean implementation class for Enterprise Bean: Estructura_Interfaz
 */
public abstract class Estructura_InterfazBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey ejbCreate(
		java.lang.String aci_tr,
		java.lang.Long aci_ap_id,
		java.lang.String aci_tag) throws javax.ejb.CreateException {
		setAci_tr(aci_tr);
		setAci_ap_id(aci_ap_id);
		setAci_tag(aci_tag);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String aci_tr,
		java.lang.Long aci_ap_id,
		java.lang.String aci_tag) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: aci_tr
	 */
	public abstract java.lang.String getAci_tr();
	/**
	 * Set accessor for persistent attribute: aci_tr
	 */
	public abstract void setAci_tr(java.lang.String newAci_tr);
	/**
	 * Get accessor for persistent attribute: aci_ap_id
	 */
	public abstract java.lang.Long getAci_ap_id();
	/**
	 * Set accessor for persistent attribute: aci_ap_id
	 */
	public abstract void setAci_ap_id(java.lang.Long newAci_ap_id);
	/**
	 * Get accessor for persistent attribute: aci_tag
	 */
	public abstract java.lang.String getAci_tag();
	/**
	 * Set accessor for persistent attribute: aci_tag
	 */
	public abstract void setAci_tag(java.lang.String newAci_tag);
	/**
	 * Get accessor for persistent attribute: aci_tipo
	 */
	public abstract java.lang.String getAci_tipo();
	/**
	 * Set accessor for persistent attribute: aci_tipo
	 */
	public abstract void setAci_tipo(java.lang.String newAci_tipo);
	/**
	 * Get accessor for persistent attribute: aci_nullable
	 */
	public abstract java.lang.Long getAci_nullable();
	/**
	 * Set accessor for persistent attribute: aci_nullable
	 */
	public abstract void setAci_nullable(java.lang.Long newAci_nullable);
	/**
	 * Get accessor for persistent attribute: aci_arreglo
	 */
	public abstract java.lang.String getAci_arreglo();
	/**
	 * Set accessor for persistent attribute: aci_arreglo
	 */
	public abstract void setAci_arreglo(java.lang.String newAci_arreglo);
	/**
	 * Get accessor for persistent attribute: aci_default_value
	 */
	public abstract java.lang.String getAci_default_value();
	/**
	 * Set accessor for persistent attribute: aci_default_value
	 */
	public abstract void setAci_default_value(
		java.lang.String newAci_default_value);
	/**
	 * Get accessor for persistent attribute: orden
	 */
	public abstract java.lang.Long getOrden();
	/**
	 * Set accessor for persistent attribute: orden
	 */
	public abstract void setOrden(java.lang.Long newOrden);
}