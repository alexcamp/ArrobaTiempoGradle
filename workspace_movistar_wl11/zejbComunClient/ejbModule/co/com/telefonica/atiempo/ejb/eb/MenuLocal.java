package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Menu
 */
public interface MenuLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: mn_descripcion
	 */
	public java.lang.String getMn_descripcion();
	/**
	 * Set accessor for persistent attribute: mn_descripcion
	 */
	public void setMn_descripcion(java.lang.String newMn_descripcion);
	/**
	 * Get accessor for persistent attribute: mn_url
	 */
	public java.lang.String getMn_url();
	/**
	 * Set accessor for persistent attribute: mn_url
	 */
	public void setMn_url(java.lang.String newMn_url);
	/**
	 * Get accessor for persistent attribute: mn_id_padre
	 */
	public java.lang.Long getMn_id_padre();
	/**
	 * Set accessor for persistent attribute: mn_id_padre
	 */
	public void setMn_id_padre(java.lang.Long newMn_id_padre);
	/**
	 * Get accessor for persistent attribute: mn_orden
	 */
	public java.lang.Integer getMn_orden();
	/**
	 * Set accessor for persistent attribute: mn_orden
	 */
	public void setMn_orden(java.lang.Integer newMn_orden);
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMenu_perfil();
	/**
	 * This method was generated for supporting the relationship role named menu_perfil.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMenu_perfil(java.util.Collection aMenu_perfil);
}
