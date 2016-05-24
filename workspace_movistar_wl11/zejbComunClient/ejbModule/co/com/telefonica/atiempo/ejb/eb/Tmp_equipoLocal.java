package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Tmp_equipo
 */
public interface Tmp_equipoLocal extends javax.ejb.EJBLocalObject {
    /**
     * Get accessor for persistent attribute: id
     */
    public java.lang.Long getId();
    /**
     * Set accessor for persistent attribute: id
     */
    public void setId(java.lang.Long newId);
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
}
