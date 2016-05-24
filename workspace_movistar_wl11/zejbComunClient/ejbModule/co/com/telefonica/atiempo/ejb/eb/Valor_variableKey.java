package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Valor_variable
 */
public class Valor_variableKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_valor_bi_bi_id
	 */
	public java.lang.Long fk_valor_bi_bi_id;
	/**
	 * Implementation field for persistent attribute: fk_valcampo_campo_cv_id
	 */
	public java.lang.Short fk_valcampo_campo_cv_id;
	/**
	 * Creates an empty key for Entity Bean: Valor_variable
	 */
	public Valor_variableKey() {
	}
	/**
	 * Creates a key for Entity Bean: Valor_variable
	 */
	public Valor_variableKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey argFk_valor_bi) {
		privateSetFk_valcampo_campoKey(argFk_valcampo_campo);
		privateSetFk_valor_biKey(argFk_valor_bi);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Valor_variableKey) {
			co.com.telefonica.atiempo.ejb.eb.Valor_variableKey o =
				(co.com.telefonica.atiempo.ejb.eb.Valor_variableKey) otherKey;
			return (
				(this
					.fk_valcampo_campo_cv_id
					.equals(o.fk_valcampo_campo_cv_id))
					&& (this.fk_valor_bi_bi_id.equals(o.fk_valor_bi_bi_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_valcampo_campo_cv_id.hashCode() + fk_valor_bi_bi_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.BintegradaKey getFk_valor_biKey() {
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey temp =
			new co.com.telefonica.atiempo.ejb.eb.BintegradaKey();
		boolean fk_valor_bi_NULLTEST = true;
		fk_valor_bi_NULLTEST &= (fk_valor_bi_bi_id == null);
		temp.bi_id = fk_valor_bi_bi_id;
		if (fk_valor_bi_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_valor_bi.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_valor_biKey(
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey inKey) {
		boolean fk_valor_bi_NULLTEST = (inKey == null);
		fk_valor_bi_bi_id = (fk_valor_bi_NULLTEST) ? null : inKey.bi_id;
	}
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
		.Campo_variableKey getFk_valcampo_campoKey() {
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Campo_variableKey();
		boolean fk_valcampo_campo_NULLTEST = true;
		fk_valcampo_campo_NULLTEST &= (fk_valcampo_campo_cv_id == null);
		temp.cv_id = fk_valcampo_campo_cv_id;
		if (fk_valcampo_campo_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_valcampo_campo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_valcampo_campoKey(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableKey inKey) {
		boolean fk_valcampo_campo_NULLTEST = (inKey == null);
		fk_valcampo_campo_cv_id =
			(fk_valcampo_campo_NULLTEST) ? null : inKey.cv_id;
	}
}
