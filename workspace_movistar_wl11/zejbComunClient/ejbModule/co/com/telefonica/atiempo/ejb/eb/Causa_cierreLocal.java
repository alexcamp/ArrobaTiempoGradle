package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Causa_cierre
 */
public interface Causa_cierreLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ambi_id
	 */
	public java.lang.Integer getAmbi_id();
	/**
	 * Set accessor for persistent attribute: ambi_id
	 */
	public void setAmbi_id(java.lang.Integer newAmbi_id);
	/**
	 * This method was generated for supporting the relationship role named fk_ciac_2_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Cierre_actividadLocal getFk_ciac_2_caci();
	/**
	 * This method was generated for supporting the relationship role named fk_ciac_2_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_ciac_2_caci(
		co.com.telefonica.atiempo.ejb.eb.Cierre_actividadLocal aFk_ciac_2_caci);
	/**
	 * This method was generated for supporting the relationship role named fk_caus_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.CausaLocal getFk_caus_caci();
	/**
	 * This method was generated for supporting the relationship role named fk_caus_caci.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_caus_caci(
		co.com.telefonica.atiempo.ejb.eb.CausaLocal aFk_caus_caci);
}
