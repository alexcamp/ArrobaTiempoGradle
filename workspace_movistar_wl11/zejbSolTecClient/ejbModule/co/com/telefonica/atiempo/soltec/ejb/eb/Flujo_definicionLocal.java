package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Flujo_definicion
 */
public interface Flujo_definicionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: act_acti_id
	 */
	public java.lang.Integer getAct_acti_id();
	/**
	 * Set accessor for persistent attribute: act_acti_id
	 */
	public void setAct_acti_id(java.lang.Integer newAct_acti_id);
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal getFlujo();
	/**
	 * This method was generated for supporting the relationship role named flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo(
		co.com.telefonica.atiempo.soltec.ejb.eb.FlujoLocal aFlujo);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Actividad_flujoLocal getActividad_flujo();
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad_flujo(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
}
