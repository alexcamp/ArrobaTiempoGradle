package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tipo_pc
 */
public interface Tipo_pcLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_pc
	 */
	public java.lang.String getNombre_pc();
	/**
	 * Set accessor for persistent attribute: nombre_pc
	 */
	public void setNombre_pc(java.lang.String newNombre_pc);
	/**
	 * Get accessor for persistent attribute: familia_pc
	 */
	public java.lang.Integer getFamilia_pc();
	/**
	 * Set accessor for persistent attribute: familia_pc
	 */
	public void setFamilia_pc(java.lang.Integer newFamilia_pc);
	/**
	 * This method was generated for supporting the relationship role named subtipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getSubtipo_pc();
	/**
	 * This method was generated for supporting the relationship role named subtipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setSubtipo_pc(java.util.Collection aSubtipo_pc);
}
