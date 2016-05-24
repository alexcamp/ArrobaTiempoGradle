package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Subsegmento
 */
public interface SubsegmentoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal getSegmento();
	/**
	 * This method was generated for supporting the relationship role named segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setSegmento(
		co.com.telefonica.atiempo.ejb.eb.SegmentoLocal aSegmento);
}
