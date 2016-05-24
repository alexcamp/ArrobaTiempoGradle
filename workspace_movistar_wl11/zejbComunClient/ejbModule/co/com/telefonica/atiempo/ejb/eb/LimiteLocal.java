package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Limite
 */
public interface LimiteLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: li_limite_rojo
	 */
	public java.lang.Integer getLi_limite_rojo();
	/**
	 * Set accessor for persistent attribute: li_limite_rojo
	 */
	public void setLi_limite_rojo(java.lang.Integer newLi_limite_rojo);
	/**
	 * Get accessor for persistent attribute: li_limite_amarillo
	 */
	public java.lang.Integer getLi_limite_amarillo();
	/**
	 * Set accessor for persistent attribute: li_limite_amarillo
	 */
	public void setLi_limite_amarillo(java.lang.Integer newLi_limite_amarillo);
	/**
	 * Get accessor for persistent attribute: li_limite_negro
	 */
	public java.lang.Integer getLi_limite_negro();
	/**
	 * Set accessor for persistent attribute: li_limite_negro
	 */
	public void setLi_limite_negro(java.lang.Integer newLi_limite_negro);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal anActividad);
}
