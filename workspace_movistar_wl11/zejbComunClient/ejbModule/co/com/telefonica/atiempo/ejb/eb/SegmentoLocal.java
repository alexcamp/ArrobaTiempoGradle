package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Segmento
 */
public interface SegmentoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: fase_id
	 */
	public java.lang.Long getFase_id();
	/**
	 * Set accessor for persistent attribute: fase_id
	 */
	public void setFase_id(java.lang.Long newFase_id);
	/**
	 * Get accessor for persistent attribute: segm_descripcion
	 */
	public java.lang.String getSegm_descripcion();
	/**
	 * Set accessor for persistent attribute: segm_descripcion
	 */
	public void setSegm_descripcion(java.lang.String newSegm_descripcion);
	/**
	 * Get accessor for persistent attribute: segm_codigo
	 */
	public java.lang.String getSegm_codigo();
	/**
	 * Set accessor for persistent attribute: segm_codigo
	 */
	public void setSegm_codigo(java.lang.String newSegm_codigo);
	/**
	 * Get accessor for persistent attribute: segm_negocia
	 */
	public java.lang.String getSegm_negocia();
	/**
	 * Set accessor for persistent attribute: segm_negocia
	 */
	public void setSegm_negocia(java.lang.String newSegm_negocia);
	/**
	 * Get accessor for persistent attribute: segm_tipo
	 */
	public java.lang.String getSegm_tipo();
	/**
	 * Set accessor for persistent attribute: segm_tipo
	 */
	public void setSegm_tipo(java.lang.String newSegm_tipo);
	/**
	 * Get accessor for persistent attribute: segm_autoprueba
	 */
	public java.lang.String getSegm_autoprueba();
	/**
	 * Set accessor for persistent attribute: segm_autoprueba
	 */
	public void setSegm_autoprueba(java.lang.String newSegm_autoprueba);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named subsegmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getSubsegmento();
	/**
	 * This method was generated for supporting the relationship role named subsegmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setSubsegmento(java.util.Collection aSubsegmento);
}
