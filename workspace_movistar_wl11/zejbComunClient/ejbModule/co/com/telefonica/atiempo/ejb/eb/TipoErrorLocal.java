package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: TipoError
 */
public interface TipoErrorLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal getErrorlegado();
	/**
	 * This method was generated for supporting the relationship role named errorlegado.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setErrorlegado(
		co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal anErrorlegado);
}
