package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Empresa
 */
public interface EmpresaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: empresa_nombre
	 */
	public java.lang.String getEmpresa_nombre();
	/**
	 * Set accessor for persistent attribute: empresa_nombre
	 */
	public void setEmpresa_nombre(java.lang.String newEmpresa_nombre);
	/**
	 * Get accessor for persistent attribute: empresa_descripcion
	 */
	public java.lang.String getEmpresa_descripcion();
	/**
	 * Set accessor for persistent attribute: empresa_descripcion
	 */
	public void setEmpresa_descripcion(java.lang.String newEmpresa_descripcion);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUsuario(java.util.Collection anUsuario);
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTecnico();
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTecnico(java.util.Collection aTecnico);
}
