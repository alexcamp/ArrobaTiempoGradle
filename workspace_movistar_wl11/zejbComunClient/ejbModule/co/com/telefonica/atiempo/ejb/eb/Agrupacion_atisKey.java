package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Agrupacion_atis
 */
public class Agrupacion_atisKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_agr_sub_nu
	 */
	public java.lang.Integer cod_agr_sub_nu;
	/**
	 * Implementation field for persistent attribute: fk_pet_agrupacion_cod_pet_cd
	 */
	public java.lang.Long fk_pet_agrupacion_cod_pet_cd;
	/**
	 * Creates an empty key for Entity Bean: Agrupacion_atis
	 */
	public Agrupacion_atisKey() {
	}
	/**
	 * Creates a key for Entity Bean: Agrupacion_atis
	 */
	public Agrupacion_atisKey(
		java.lang.Integer cod_agr_sub_nu,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Peticion_atisKey argFk_pet_agrupacion) {
		this.cod_agr_sub_nu = cod_agr_sub_nu;
		privateSetFk_pet_agrupacionKey(argFk_pet_agrupacion);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey) {
			co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey o =
				(co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey) otherKey;
			return (
				(this.cod_agr_sub_nu.equals(o.cod_agr_sub_nu))
					&& (this
						.fk_pet_agrupacion_cod_pet_cd
						.equals(o.fk_pet_agrupacion_cod_pet_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			cod_agr_sub_nu.hashCode()
				+ fk_pet_agrupacion_cod_pet_cd.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_pet_agrupacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Peticion_atisKey getFk_pet_agrupacionKey() {
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey();
		boolean fk_pet_agrupacion_NULLTEST = true;
		fk_pet_agrupacion_NULLTEST &= (fk_pet_agrupacion_cod_pet_cd == null);
		temp.cod_pet_cd = fk_pet_agrupacion_cod_pet_cd;
		if (fk_pet_agrupacion_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_pet_agrupacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_pet_agrupacionKey(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey inKey) {
		boolean fk_pet_agrupacion_NULLTEST = (inKey == null);
		fk_pet_agrupacion_cod_pet_cd =
			(fk_pet_agrupacion_NULLTEST) ? null : inKey.cod_pet_cd;
	}
}
