package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_estado
 */
public interface Mensaje_estadoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_estado
	 */
	public java.lang.String getNombre_estado();
	/**
	 * Set accessor for persistent attribute: nombre_estado
	 */
	public void setNombre_estado(java.lang.String newNombre_estado);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_linea();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_linea(
		java.util.Collection aMensaje_estado_linea);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_ba();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_ba.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_ba(java.util.Collection aMensaje_estado_ba);
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMensaje_estado_tv();
	/**
	 * This method was generated for supporting the relationship role named mensaje_estado_tv.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMensaje_estado_tv(java.util.Collection aMensaje_estado_tv);
}
