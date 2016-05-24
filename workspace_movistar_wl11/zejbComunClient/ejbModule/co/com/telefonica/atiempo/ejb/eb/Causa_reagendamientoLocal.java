package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Causa_reagendamiento
 */
public interface Causa_reagendamientoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: care_descripcion
	 */
	public java.lang.String getCare_descripcion();
	/**
	 * Set accessor for persistent attribute: care_descripcion
	 */
	public void setCare_descripcion(java.lang.String newCare_descripcion);
	/**
	 * Get accessor for persistent attribute: care_codigo
	 */
	public java.lang.String getCare_codigo();
	/**
	 * Set accessor for persistent attribute: care_codigo
	 */
	public void setCare_codigo(java.lang.String newCare_codigo);
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTecnico_peticion();
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTecnico_peticion(java.util.Collection aTecnico_peticion);
}
