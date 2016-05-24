package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Inventario_dth
 */
public interface Inventario_dthLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.String getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.String newEstado);
	/**
	 * Get accessor for persistent attribute: marca_hora
	 */
	public java.sql.Timestamp getMarca_hora();
	/**
	 * Set accessor for persistent attribute: marca_hora
	 */
	public void setMarca_hora(java.sql.Timestamp newMarca_hora);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
}
