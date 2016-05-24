package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Altapctv
 */
public interface AltapctvLocal extends javax.ejb.EJBLocalObject {
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
}
