package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Accion_masiva
 */
public interface Accion_masivaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: acma_descripcion
	 */
	public java.lang.String getAcma_descripcion();
	/**
	 * Set accessor for persistent attribute: acma_descripcion
	 */
	public void setAcma_descripcion(java.lang.String newAcma_descripcion);
	/**
	 * Get accessor for persistent attribute: acma_valor
	 */
	public java.lang.String getAcma_valor();
	/**
	 * Set accessor for persistent attribute: acma_valor
	 */
	public void setAcma_valor(java.lang.String newAcma_valor);
	/**
	 * Get accessor for persistent attribute: acma_cierre
	 */
	public java.lang.String getAcma_cierre();
	/**
	 * Set accessor for persistent attribute: acma_cierre
	 */
	public void setAcma_cierre(java.lang.String newAcma_cierre);
	/**
	 * This method was generated for supporting the relationship role named f_fk_rol_2_acma.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getF_fk_rol_2_acma();
	/**
	 * This method was generated for supporting the relationship role named f_fk_rol_2_acma.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setF_fk_rol_2_acma(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aF_fk_rol_2_acma);
}
