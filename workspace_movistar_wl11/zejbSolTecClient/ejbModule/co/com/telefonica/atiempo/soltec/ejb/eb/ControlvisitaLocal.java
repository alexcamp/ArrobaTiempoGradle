package co.com.telefonica.atiempo.soltec.ejb.eb;


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
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
	/**
	 * @param string
	 */
	public void setTecnico(String string);
	/**
	 * @param local
	 */

	/**
	 * Get accessor for persistent attribute: tecnico
	 */
	public java.lang.String getTecnico();
}
