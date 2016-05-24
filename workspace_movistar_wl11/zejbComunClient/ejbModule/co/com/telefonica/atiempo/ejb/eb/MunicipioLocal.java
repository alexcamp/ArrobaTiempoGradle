package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Municipio
 */
public interface MunicipioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_municipio
	 */
	public java.lang.String getNombre_municipio();
	/**
	 * Set accessor for persistent attribute: nombre_municipio
	 */
	public void setNombre_municipio(java.lang.String newNombre_municipio);
	/**
	 * Get accessor for persistent attribute: descripcion_municipio
	 */
	public java.lang.String getDescripcion_municipio();
	/**
	 * Set accessor for persistent attribute: descripcion_municipio
	 */
	public void setDescripcion_municipio(
		java.lang.String newDescripcion_municipio);
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal getDepartamento();
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDepartamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aDepartamento);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalidad(java.util.Collection aLocalidad);
}
