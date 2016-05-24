package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Direccion_atis
 */
public class Direccion_atisKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_01_direc_atis_cod_agr_sub_nu
	 */
	public java.lang.Integer fk_01_direc_atis_cod_agr_sub_nu;
	/**
	 * Implementation field for persistent attribute: fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd
	 */
	public java.lang.Long fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd;
	/**
	 * Creates an empty key for Entity Bean: Direccion_atis
	 */
	public Direccion_atisKey() {
	}
	/**
	 * Creates a key for Entity Bean: Direccion_atis
	 */
	public Direccion_atisKey(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisKey argFk_01_direc_atis) {
		privateSetFk_01_direc_atisKey(argFk_01_direc_atis);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey) {
			co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey o =
				(co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey) otherKey;
			return (
				(this
					.fk_01_direc_atis_cod_agr_sub_nu
					.equals(o.fk_01_direc_atis_cod_agr_sub_nu))
					&& (this
						.fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd
						.equals(
							o.fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_01_direc_atis_cod_agr_sub_nu.hashCode()
				+ fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_01_direc_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Agrupacion_atisKey getFk_01_direc_atisKey() {
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey();
		boolean fk_01_direc_atis_NULLTEST = true;
		fk_01_direc_atis_NULLTEST &= (fk_01_direc_atis_cod_agr_sub_nu == null);
		temp.cod_agr_sub_nu = fk_01_direc_atis_cod_agr_sub_nu;
		fk_01_direc_atis_NULLTEST
			&= (fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd == null);
		temp.fk_pet_agrupacion_cod_pet_cd =
			fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd;
		if (fk_01_direc_atis_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_01_direc_atis.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_01_direc_atisKey(
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey inKey) {
		boolean fk_01_direc_atis_NULLTEST = (inKey == null);
		fk_01_direc_atis_cod_agr_sub_nu =
			(fk_01_direc_atis_NULLTEST) ? null : inKey.cod_agr_sub_nu;
		fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd =
			(fk_01_direc_atis_NULLTEST)
				? null
				: inKey.fk_pet_agrupacion_cod_pet_cd;
	}
}
