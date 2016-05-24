package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Controlvisita
 */
public class ControlvisitaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fechahora_registro
	 */
	public java.sql.Timestamp fechahora_registro;
	/**
	 * Implementation field for persistent attribute: peticion_st_cod_ave_cd
	 */
	public java.lang.Long peticion_st_cod_ave_cd;
	/**
	 * Creates an empty key for Entity Bean: Controlvisita
	 */
	public ControlvisitaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.ControlvisitaKey) otherKey;
			return (
				(this.fechahora_registro.equals(o.fechahora_registro))
					&& (this
						.peticion_st_cod_ave_cd
						.equals(o.peticion_st_cod_ave_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fechahora_registro.hashCode() + peticion_st_cod_ave_cd.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Controlvisita
	 */
	public ControlvisitaKey(
		java.sql.Timestamp fechahora_registro,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stKey argPeticion_st) {
		this.fechahora_registro = fechahora_registro;
		privateSetPeticion_stKey(argPeticion_st);
	}
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stKey getPeticion_stKey() {
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey temp =
			new co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey();
		boolean peticion_st_NULLTEST = true;
		peticion_st_NULLTEST &= (peticion_st_cod_ave_cd == null);
		temp.cod_ave_cd = peticion_st_cod_ave_cd;
		if (peticion_st_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetPeticion_stKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey inKey) {
		boolean peticion_st_NULLTEST = (inKey == null);
		peticion_st_cod_ave_cd =
			(peticion_st_NULLTEST) ? null : inKey.cod_ave_cd;
	}
}
