package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Estado_ps
 */
public interface Estado_psLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: descripcion_estado_cierre
	 */
	public java.lang.String getDescripcion_estado_cierre();
	/**
	 * Set accessor for persistent attribute: descripcion_estado_cierre
	 */
	public void setDescripcion_estado_cierre(
		java.lang.String newDescripcion_estado_cierre);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausal_peticion(java.util.Collection aCausal_peticion);
}
