package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Valor_variable
 */
public interface Valor_variableLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: valor
	 */
	public java.lang.String getValor();
	/**
	 * Set accessor for persistent attribute: valor
	 */
	public void setValor(java.lang.String newValor);
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal getFk_valor_bi();
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_valor_bi(
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal aFk_valor_bi);
	/**
	 * This method was generated for supporting the relationship role named fk_valcampo_campo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_valcampo_campo();
	/**
	 * This method was generated for supporting the relationship role named fk_valcampo_campo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_valcampo_campo(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal aFk_valcampo_campo);
}
