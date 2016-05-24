package co.com.telefonica.atiempo.soltec.ejb.eb;
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
     * This method was generated for supporting the relationship role named peticion_st.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal getPeticion_st();
    /**
     * This method was generated for supporting the relationship role named peticion_st.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public void setPeticion_st(
        co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
}
