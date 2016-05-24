package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tmp_agenda_sc
 */
public interface Tmp_agenda_scLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: xml
	 */
	public java.lang.String getXml();
	/**
	 * Set accessor for persistent attribute: xml
	 */
	public void setXml(java.lang.String newXml);
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
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal getAgenda_sc();
	/**
	 * This method was generated for supporting the relationship role named agenda_sc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAgenda_sc(
		co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal anAgenda_sc);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: id_schedule
	 */
	public java.lang.String getId_schedule();
	/**
	 * Set accessor for persistent attribute: id_schedule
	 */
	public void setId_schedule(java.lang.String newId_schedule);
}