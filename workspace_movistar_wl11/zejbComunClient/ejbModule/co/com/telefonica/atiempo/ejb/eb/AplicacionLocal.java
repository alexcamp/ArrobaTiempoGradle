package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Aplicacion
 */
public interface AplicacionLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ap_nombre
	 */
	public java.lang.String getAp_nombre();
	/**
	 * Set accessor for persistent attribute: ap_nombre
	 */
	public void setAp_nombre(java.lang.String newAp_nombre);
	/**
	 * Get accessor for persistent attribute: ap_url_reasignacion
	 */
	public java.lang.String getAp_url_reasignacion();
	/**
	 * Set accessor for persistent attribute: ap_url_reasignacion
	 */
	public void setAp_url_reasignacion(java.lang.String newAp_url_reasignacion);
	/**
	 * Get accessor for persistent attribute: ap_url_base
	 */
	public java.lang.String getAp_url_base();
	/**
	 * Set accessor for persistent attribute: ap_url_base
	 */
	public void setAp_url_base(java.lang.String newAp_url_base);
	/**
	 * Get accessor for persistent attribute: ap_url_supervisor
	 */
	public java.lang.String getAp_url_supervisor();
	/**
	 * Set accessor for persistent attribute: ap_url_supervisor
	 */
	public void setAp_url_supervisor(java.lang.String newAp_url_supervisor);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad(java.util.Collection anActividad);
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRol();
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRol(java.util.Collection aRol);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getHorario();
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setHorario(java.util.Collection aHorario);
}
