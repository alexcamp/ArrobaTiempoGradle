package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Cierre_actividad
 */
public interface Cierre_actividadLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: ciac_nombre
	 */
	public java.lang.String getCiac_nombre();
	/**
	 * Set accessor for persistent attribute: ciac_nombre
	 */
	public void setCiac_nombre(java.lang.String newCiac_nombre);
	/**
	 * Get accessor for persistent attribute: ciac_codigo
	 */
	public java.lang.String getCiac_codigo();
	/**
	 * Set accessor for persistent attribute: ciac_codigo
	 */
	public void setCiac_codigo(java.lang.String newCiac_codigo);
	/**
	 * Get accessor for persistent attribute: ciac_reversa
	 */
	public java.lang.Short getCiac_reversa();
	/**
	 * Set accessor for persistent attribute: ciac_reversa
	 */
	public void setCiac_reversa(java.lang.Short newCiac_reversa);
	/**
	 * Get accessor for persistent attribute: ciac_valor
	 */
	public java.lang.String getCiac_valor();
	/**
	 * Set accessor for persistent attribute: ciac_valor
	 */
	public void setCiac_valor(java.lang.String newCiac_valor);
	/**
	 * Get accessor for persistent attribute: ciac_termino
	 */
	public java.lang.String getCiac_termino();
	/**
	 * Set accessor for persistent attribute: ciac_termino
	 */
	public void setCiac_termino(java.lang.String newCiac_termino);
	/**
	 * This method was generated for supporting the relationship role named fk_cierre_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal getFk_cierre_act();
	/**
	 * This method was generated for supporting the relationship role named fk_cierre_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_cierre_act(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal aFk_cierre_act);
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausa_cierre();
	/**
	 * This method was generated for supporting the relationship role named causa_cierre.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausa_cierre(java.util.Collection aCausa_cierre);
}
