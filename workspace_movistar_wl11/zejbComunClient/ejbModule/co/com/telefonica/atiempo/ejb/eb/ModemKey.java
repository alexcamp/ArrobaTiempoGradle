package co.com.telefonica.atiempo.ejb.eb;
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
	 * Implementation field for persistent attribute: peticion_peti_numero
	 */
	public java.lang.Long peticion_peti_numero;
	/**
	 * Creates an empty key for Entity Bean: Modem
	 */
	public ModemKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.ModemKey) {
			co.com.telefonica.atiempo.ejb.eb.ModemKey o = (co.com.telefonica.atiempo.ejb.eb.ModemKey) otherKey;
			return ((this.serial.equals(o.serial)) && (this.peticion_peti_numero
				.equals(o.peticion_peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (serial.hashCode() + peticion_peti_numero.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Modem
	 */
	public ModemKey(
		java.lang.String serial,
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticion) {
		this.serial = serial;
		privateSetPeticionKey(argPeticion);
	}
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionKey getPeticionKey() {
		co.com.telefonica.atiempo.ejb.eb.PeticionKey temp =
			new co.com.telefonica.atiempo.ejb.eb.PeticionKey();
		boolean peticion_NULLTEST = true;
		peticion_NULLTEST &= (peticion_peti_numero == null);
		temp.peti_numero = peticion_peti_numero;
		if (peticion_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetPeticionKey(
		co.com.telefonica.atiempo.ejb.eb.PeticionKey inKey) {
		boolean peticion_NULLTEST = (inKey == null);
		peticion_peti_numero = (peticion_NULLTEST) ? null : inKey.peti_numero;
	}
}
