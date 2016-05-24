package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Agenda_sc
 */
public interface Agenda_scLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public void setEstado(java.lang.Integer newEstado);
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
	 * Get accessor for persistent attribute: fecha_reagm
	 */
	public java.sql.Timestamp getFecha_reagm();
	/**
	 * Set accessor for persistent attribute: fecha_reagm
	 */
	public void setFecha_reagm(java.sql.Timestamp newFecha_reagm);
	/**
	 * Get accessor for persistent attribute: fecha_mod
	 */
	public java.sql.Timestamp getFecha_mod();
	/**
	 * Set accessor for persistent attribute: fecha_mod
	 */
	public void setFecha_mod(java.sql.Timestamp newFecha_mod);
	/**
	 * Get accessor for persistent attribute: nombre_contratista
	 */
	public java.lang.String getNombre_contratista();
	/**
	 * Set accessor for persistent attribute: nombre_contratista
	 */
	public void setNombre_contratista(java.lang.String newNombre_contratista);
	/**
	 * Get accessor for persistent attribute: cod_franqueo
	 */
	public java.lang.String getCod_franqueo();
	/**
	 * Set accessor for persistent attribute: cod_franqueo
	 */
	public void setCod_franqueo(java.lang.String newCod_franqueo);
	/**
	 * Get accessor for persistent attribute: quiebre
	 */
	public java.lang.String getQuiebre();
	/**
	 * Set accessor for persistent attribute: quiebre
	 */
	public void setQuiebre(java.lang.String newQuiebre);
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getTmp_agenda_sc();
	/**
	 * This method was generated for supporting the relationship role named tmp_agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTmp_agenda_sc(java.util.Collection aTmp_agenda_sc);
}