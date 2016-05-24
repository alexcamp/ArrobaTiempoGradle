package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Departamento
 */
public interface DepartamentoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: nombre_region
	 */
	public java.lang.String getNombre_region();
	/**
	 * Set accessor for persistent attribute: nombre_region
	 */
	public void setNombre_region(java.lang.String newNombre_region);
	/**
	 * Get accessor for persistent attribute: nombre_departamento
	 */
	public java.lang.String getNombre_departamento();
	/**
	 * Set accessor for persistent attribute: nombre_departamento
	 */
	public void setNombre_departamento(java.lang.String newNombre_departamento);
	/**
	 * Get accessor for persistent attribute: descripcion_departamento
	 */
	public java.lang.String getDescripcion_departamento();
	/**
	 * Set accessor for persistent attribute: descripcion_departamento
	 */
	public void setDescripcion_departamento(
		java.lang.String newDescripcion_departamento);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(java.util.Collection aPeticion);
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getDireccion_atis();
	/**
	 * This method was generated for supporting the relationship role named direccion_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDireccion_atis(java.util.Collection aDireccion_atis);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getMunicipio();
	/**
	 * This method was generated for supporting the relationship role named municipio.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setMunicipio(java.util.Collection aMunicipio);
}
