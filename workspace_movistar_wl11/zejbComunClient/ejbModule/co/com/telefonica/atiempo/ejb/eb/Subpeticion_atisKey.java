package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Subpeticion_atis
 */
public class Subpeticion_atisKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_sub_cd
	 */
	public java.lang.Integer cod_sub_cd;
	/**
	 * Implementation field for persistent attribute: fk_agru_sub_cod_agr_sub_nu
	 */
	public java.lang.Integer fk_agru_sub_cod_agr_sub_nu;
	/**
	 * Implementation field for persistent attribute: fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public java.lang.Long fk_agru_sub_fk_pet_agrupacion_cod_pet_cd;
	/**
	 * Creates an empty key for Entity Bean: Subpeticion_atis
	 */
	public Subpeticion_atisKey() {
	}
	/**
	 * Creates a key for Entity Bean: Subpeticion_atis
	 */
	public Subpeticion_atisKey(
		java.lang.Integer cod_sub_cd,
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey argFk_agru_sub) {
		this.cod_sub_cd = cod_sub_cd;
		privateSetFk_agru_subKey(argFk_agru_sub);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey) {
			co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey o =
				(co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey) otherKey;
			return (
				(this.cod_sub_cd.equals(o.cod_sub_cd))
					&& (this
						.fk_agru_sub_cod_agr_sub_nu
						.equals(o.fk_agru_sub_cod_agr_sub_nu))
					&& (this
						.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
						.equals(o.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			cod_sub_cd.hashCode()
				+ fk_agru_sub_cod_agr_sub_nu.hashCode()
				+ fk_agru_sub_fk_pet_agrupacion_cod_pet_cd.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_agru_sub.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Agrupacion_atisKey getFk_agru_subKey() {
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey();
		boolean fk_agru_sub_NULLTEST = true;
		fk_agru_sub_NULLTEST &= (fk_agru_sub_cod_agr_sub_nu == null);
		temp.cod_agr_sub_nu = fk_agru_sub_cod_agr_sub_nu;
		fk_agru_sub_NULLTEST
			&= (fk_agru_sub_fk_pet_agrupacion_cod_pet_cd == null);
		temp.fk_pet_agrupacion_cod_pet_cd =
			fk_agru_sub_fk_pet_agrupacion_cod_pet_cd;
		if (fk_agru_sub_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_agru_sub.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_agru_subKey(
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey inKey) {
		boolean fk_agru_sub_NULLTEST = (inKey == null);
		fk_agru_sub_cod_agr_sub_nu =
			(fk_agru_sub_NULLTEST) ? null : inKey.cod_agr_sub_nu;
		fk_agru_sub_fk_pet_agrupacion_cod_pet_cd =
			(fk_agru_sub_NULLTEST) ? null : inKey.fk_pet_agrupacion_cod_pet_cd;
	}
}
