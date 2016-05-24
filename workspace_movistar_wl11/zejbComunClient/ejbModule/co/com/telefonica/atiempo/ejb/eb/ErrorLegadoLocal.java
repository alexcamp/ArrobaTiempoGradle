package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: ErrorLegado
 */
public interface ErrorLegadoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: idCausa
	 */
	public java.lang.Long getIdCausa();
	/**
	 * Set accessor for persistent attribute: idCausa
	 */
	public void setIdCausa(java.lang.Long newIdCausa);
	/**
	 * Get accessor for persistent attribute: idTipoError
	 */
	public java.lang.Long getIdTipoError();
	/**
	 * Set accessor for persistent attribute: idTipoError
	 */
	public void setIdTipoError(java.lang.Long newIdTipoError);
	/**
	 * Get accessor for persistent attribute: numeroTr
	 */
	public java.lang.String getNumeroTr();
	/**
	 * Set accessor for persistent attribute: numeroTr
	 */
	public void setNumeroTr(java.lang.String newNumeroTr);
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCatalogo_causal(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named tipoerror.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.TipoErrorLocal getTipoerror();
	/**
	 * This method was generated for supporting the relationship role named tipoerror.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setTipoerror(
		co.com.telefonica.atiempo.ejb.eb.TipoErrorLocal aTipoerror);
	/**
	 * Get accessor for persistent attribute: codigoError
	 */
	public java.lang.String getCodigoError();
	/**
	 * Set accessor for persistent attribute: codigoError
	 */
	public void setCodigoError(java.lang.String newCodigoError);
	/**
	 * Get accessor for persistent attribute: plataforma
	 */
	public java.lang.String getPlataforma();
	/**
	 * Set accessor for persistent attribute: plataforma
	 */
	public void setPlataforma(java.lang.String newPlataforma);
}
