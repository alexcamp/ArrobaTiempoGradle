package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Controlvisita
 */
public interface ControlvisitaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: fechahora_llegada
	 */
	public java.sql.Timestamp getFechahora_llegada();
	/**
	 * Set accessor for persistent attribute: fechahora_llegada
	 */
	public void setFechahora_llegada(java.sql.Timestamp newFechahora_llegada);
	/**
	 * Get accessor for persistent attribute: fechahora_salida
	 */
	public java.sql.Timestamp getFechahora_salida();
	/**
	 * Set accessor for persistent attribute: fechahora_salida
	 */
	public void setFechahora_salida(java.sql.Timestamp newFechahora_salida);
	/**
	 * Get accessor for persistent attribute: fechahora_registro
	 */
	public java.sql.Timestamp getFechahora_registro();
	/**
	 * Set accessor for persistent attribute: fechahora_registro
	 */
	public void setFechahora_registro(java.sql.Timestamp newFechahora_registro);
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
	 * @param string
	 */
	public void setTecnico(String string);
	/**
	 * Get accessor for persistent attribute: tecnico
	 */
	public java.lang.String getTecnico();
	/**
	 * Get accessor for persistent attribute: cod_caudem
	 */
	public java.lang.Long getCod_caudem();
	/**
	 * Set accessor for persistent attribute: cod_caudem
	 */
	public void setCod_caudem(java.lang.Long newCod_caudem);
	/**
	 * Get accessor for persistent attribute: ps_id
	 */
	public java.lang.Long getPs_id();
	/**
	 * Set accessor for persistent attribute: ps_id
	 */
	public void setPs_id(java.lang.Long newPs_id);
}
