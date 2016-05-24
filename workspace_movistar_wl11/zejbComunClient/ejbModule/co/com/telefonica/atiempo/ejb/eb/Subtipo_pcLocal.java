package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Subtipo_pc
 */
public interface Subtipo_pcLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_subtipo
	 */
	public java.lang.String getNombre_subtipo();
	/**
	 * Set accessor for persistent attribute: nombre_subtipo
	 */
	public void setNombre_subtipo(java.lang.String newNombre_subtipo);
	/**
	 * Get accessor for persistent attribute: descripcion_subtipo
	 */
	public java.lang.String getDescripcion_subtipo();
	/**
	 * Set accessor for persistent attribute: descripcion_subtipo
	 */
	public void setDescripcion_subtipo(java.lang.String newDescripcion_subtipo);
	/**
	 * This method was generated for supporting the relationship role named tipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal getTipo_pc();
	/**
	 * This method was generated for supporting the relationship role named tipo_pc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTipo_pc(
		co.com.telefonica.atiempo.ejb.eb.Tipo_pcLocal aTipo_pc);
}
