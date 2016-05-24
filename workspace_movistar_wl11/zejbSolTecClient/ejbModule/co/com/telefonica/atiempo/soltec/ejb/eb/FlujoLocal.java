package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Flujo
 */
public interface FlujoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: fluj_codigo
	 */
	public java.lang.String getFluj_codigo();
	/**
	 * Set accessor for persistent attribute: fluj_codigo
	 */
	public void setFluj_codigo(java.lang.String newFluj_codigo);
	/**
	 * Get accessor for persistent attribute: fluj_descripcion
	 */
	public java.lang.String getFluj_descripcion();
	/**
	 * Set accessor for persistent attribute: fluj_descripcion
	 */
	public void setFluj_descripcion(java.lang.String newFluj_descripcion);
	/**
	 * This method was generated for supporting the relationship role named flujo_definicion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getFlujo_definicion();
	/**
	 * This method was generated for supporting the relationship role named flujo_definicion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo_definicion(java.util.Collection aFlujo_definicion);
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getFlujo_prod_serv_oper_com();
	/**
	 * This method was generated for supporting the relationship role named flujo_prod_serv_oper_com.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFlujo_prod_serv_oper_com(
		java.util.Collection aFlujo_prod_serv_oper_com);
}
