package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Campo_variable
 */
public interface Campo_variableLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cv_nombre_int
	 */
	public java.lang.String getCv_nombre_int();
	/**
	 * Set accessor for persistent attribute: cv_nombre_int
	 */
	public void setCv_nombre_int(java.lang.String newCv_nombre_int);
	/**
	 * Get accessor for persistent attribute: cv_nombre_ext
	 */
	public java.lang.String getCv_nombre_ext();
	/**
	 * Set accessor for persistent attribute: cv_nombre_ext
	 */
	public void setCv_nombre_ext(java.lang.String newCv_nombre_ext);
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCampo_rol();
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCampo_rol(java.util.Collection aCampo_rol);
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCampo_usuario();
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCampo_usuario(java.util.Collection aCampo_usuario);
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getValor_variable();
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setValor_variable(java.util.Collection aValor_variable);
}
