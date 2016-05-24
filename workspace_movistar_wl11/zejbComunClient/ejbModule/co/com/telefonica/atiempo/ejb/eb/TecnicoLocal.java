package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tecnico
 */
public interface TecnicoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre
	 */
	public java.lang.String getNombre();
	/**
	 * Set accessor for persistent attribute: nombre
	 */
	public void setNombre(java.lang.String newNombre);
	/**
	 * Get accessor for persistent attribute: apellido
	 */
	public java.lang.String getApellido();
	/**
	 * Set accessor for persistent attribute: apellido
	 */
	public void setApellido(java.lang.String newApellido);
	/**
	 * Get accessor for persistent attribute: cod_tecnico
	 */
	public java.lang.String getCod_tecnico();
	/**
	 * Set accessor for persistent attribute: cod_tecnico
	 */
	public void setCod_tecnico(java.lang.String newCod_tecnico);
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTecnico_peticion();
	/**
	 * This method was generated for supporting the relationship role named tecnico_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTecnico_peticion(java.util.Collection aTecnico_peticion);
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.EmpresaLocal getEmpresa();
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setEmpresa(
		co.com.telefonica.atiempo.ejb.eb.EmpresaLocal anEmpresa);
	/**
	 * Get accessor for persistent attribute: habilitado
	 */
	public java.lang.Short getHabilitado();
	/**
	 * Set accessor for persistent attribute: habilitado
	 */
	public void setHabilitado(java.lang.Short newHabilitado);
}
