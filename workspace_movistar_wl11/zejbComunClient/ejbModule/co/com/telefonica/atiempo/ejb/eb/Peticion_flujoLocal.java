package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Peticion_flujo
 */
public interface Peticion_flujoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: prse_id
	 */
	public java.lang.Long getPrse_id();
	/**
	 * Set accessor for persistent attribute: prse_id
	 */
	public void setPrse_id(java.lang.Long newPrse_id);
	/**
	 * Get accessor for persistent attribute: pefl_orden
	 */
	public java.lang.Integer getPefl_orden();
	/**
	 * Set accessor for persistent attribute: pefl_orden
	 */
	public void setPefl_orden(java.lang.Integer newPefl_orden);
	/**
	 * Get accessor for persistent attribute: pefl_estado
	 */
	public java.lang.String getPefl_estado();
	/**
	 * Set accessor for persistent attribute: pefl_estado
	 */
	public void setPefl_estado(java.lang.String newPefl_estado);
	/**
	 * This method was generated for supporting the relationship role named fk_peti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal getFk_peti_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_peti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_peti_2_pefl(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_peti_2_pefl);
	/**
	 * This method was generated for supporting the relationship role named fk_opco_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Operacion_comercialLocal getFk_opco_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_opco_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_opco_2_pefl(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal aFk_opco_2_pefl);
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Actividad_flujoLocal getFk_acti_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_acti_2_pefl(
		co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocal aFk_acti_2_pefl);
	/**
	 * @return
	 */
	public Integer getIdActividad();
}
