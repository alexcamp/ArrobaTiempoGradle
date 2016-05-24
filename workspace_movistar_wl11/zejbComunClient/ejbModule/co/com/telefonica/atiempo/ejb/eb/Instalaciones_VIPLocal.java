package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Instalaciones_VIP
 */
public interface Instalaciones_VIPLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: atis
	 */
	public java.lang.Long getAtis();
	/**
	 * Set accessor for persistent attribute: atis
	 */
	public void setAtis(java.lang.Long newAtis);
	/**
	 * Get accessor for persistent attribute: idpc
	 */
	public java.lang.String getIdpc();
	/**
	 * Set accessor for persistent attribute: idpc
	 */
	public void setIdpc(java.lang.String newIdpc);
	/**
	 * Get accessor for persistent attribute: idpcTV
	 */
	public java.lang.String getIdpcTV();
	/**
	 * Set accessor for persistent attribute: idpcTV
	 */
	public void setIdpcTV(java.lang.String newIdpcTV);
	/**
	 * Get accessor for persistent attribute: fecha_ingreso
	 */
	public java.sql.Timestamp getFecha_ingreso();
	/**
	 * Set accessor for persistent attribute: fecha_ingreso
	 */
	public void setFecha_ingreso(java.sql.Timestamp newFecha_ingreso);
	/**
	 * Get accessor for persistent attribute: fecha_fin
	 */
	public java.sql.Timestamp getFecha_fin();
	/**
	 * Set accessor for persistent attribute: fecha_fin
	 */
	public void setFecha_fin(java.sql.Timestamp newFecha_fin);
	/**
	 * Get accessor for persistent attribute: cod_localidad
	 */
	public java.lang.String getCod_localidad();
	/**
	 * Set accessor for persistent attribute: cod_localidad
	 */
	public void setCod_localidad(java.lang.String newCod_localidad);
	/**
	 * Get accessor for persistent attribute: cod_dpto
	 */
	public java.lang.String getCod_dpto();
	/**
	 * Set accessor for persistent attribute: cod_dpto
	 */
	public void setCod_dpto(java.lang.String newCod_dpto);
	/**
	 * Get accessor for persistent attribute: espe_id
	 */
	public java.lang.Integer getEspe_id();
	/**
	 * Set accessor for persistent attribute: espe_id
	 */
	public void setEspe_id(java.lang.Integer newEspe_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public void setTica_id(java.lang.String newTica_id);
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