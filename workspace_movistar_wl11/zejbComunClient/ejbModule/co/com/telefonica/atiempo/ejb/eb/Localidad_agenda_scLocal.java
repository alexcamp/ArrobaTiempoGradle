package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Localidad_agenda_sc
 */
public interface Localidad_agenda_scLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aLocalidad);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
}