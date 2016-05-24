package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Regla
 */
public interface ReglaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRegla_area_ges_cod_estado();
	/**
	 * This method was generated for supporting the relationship role named regla_area_ges_cod_estado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRegla_area_ges_cod_estado(
		java.util.Collection aRegla_area_ges_cod_estado);
}
