package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Modem
 */
public class ModemKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: serial
	 */
	public java.lang.String serial;
	/**
	 * Implementation field for persistent attribute: peticion_st_cod_ave_cd
	 */
	public java.lang.Long peticion_st_cod_ave_cd;
	/**
	 * Creates an empty key for Entity Bean: Modem
	 */
	public ModemKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey o =
				(co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey) otherKey;
			return (
				(this.serial.equals(o.serial))
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
		return (serial.hashCode() + peticion_st_cod_ave_cd.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Modem
	 */
	public ModemKey(
		java.lang.String serial,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stKey argPeticion_st) {
		this.serial = serial;
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
