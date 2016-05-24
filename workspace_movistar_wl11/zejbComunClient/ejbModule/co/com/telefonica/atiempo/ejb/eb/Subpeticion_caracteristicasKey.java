package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Subpeticion_caracteristicas
 */
public class Subpeticion_caracteristicasKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_crc_cd
	 */
	public java.lang.Long cod_crc_cd;
	/**
	 * Implementation field for persistent attribute: fk_01_subpetcar_cod_sub_cd
	 */
	public java.lang.Integer fk_01_subpetcar_cod_sub_cd;
	/**
	 * Implementation field for persistent attribute: fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu
	 */
	public java.lang.Integer fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu;
	/**
	 * Implementation field for persistent attribute: fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
	 */
	public java
		.lang
		.Long fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd;
	/**
	 * Creates an empty key for Entity Bean: Subpeticion_caracteristicas
	 */
	public Subpeticion_caracteristicasKey() {
	}
	/**
	 * Creates a key for Entity Bean: Subpeticion_caracteristicas
	 */
	public Subpeticion_caracteristicasKey(
		java.lang.Long cod_crc_cd,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisKey argFk_01_subpetcar) {
		this.cod_crc_cd = cod_crc_cd;
		privateSetFk_01_subpetcarKey(argFk_01_subpetcar);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey) {
			co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Subpeticion_caracteristicasKey) otherKey;
			return (
				(this.cod_crc_cd.equals(o.cod_crc_cd))
					&& (this
						.fk_01_subpetcar_cod_sub_cd
						.equals(o.fk_01_subpetcar_cod_sub_cd))
					&& (this
						.fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu
						.equals(o.fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu))
					&& (this
						.fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
						.equals(
							o
								.fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			cod_crc_cd.hashCode()
				+ fk_01_subpetcar_cod_sub_cd.hashCode()
				+ fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu.hashCode()
				+ fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd
					.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_01_subpetcar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_atisKey getFk_01_subpetcarKey() {
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey temp =
			new co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey();
		boolean fk_01_subpetcar_NULLTEST = true;
		fk_01_subpetcar_NULLTEST &= (fk_01_subpetcar_cod_sub_cd == null);
		temp.cod_sub_cd = fk_01_subpetcar_cod_sub_cd;
		fk_01_subpetcar_NULLTEST
			&= (fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu == null);
		temp.fk_agru_sub_cod_agr_sub_nu =
			fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu;
		fk_01_subpetcar_NULLTEST
			&= (fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd == null);
		temp.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd =
			fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd;
		if (fk_01_subpetcar_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_01_subpetcar.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_01_subpetcarKey(
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey inKey) {
		boolean fk_01_subpetcar_NULLTEST = (inKey == null);
		fk_01_subpetcar_cod_sub_cd =
			(fk_01_subpetcar_NULLTEST) ? null : inKey.cod_sub_cd;
		fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu =
			(fk_01_subpetcar_NULLTEST)
				? null
				: inKey.fk_agru_sub_cod_agr_sub_nu;
		fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd =
			(fk_01_subpetcar_NULLTEST)
				? null
				: inKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd;
	}
	
	
	public java.lang.Long getCod_crc_cd() {
		return cod_crc_cd;
	}
	public void setCod_crc_cd(java.lang.Long cod_crc_cd) {
		this.cod_crc_cd = cod_crc_cd;
	}
}
