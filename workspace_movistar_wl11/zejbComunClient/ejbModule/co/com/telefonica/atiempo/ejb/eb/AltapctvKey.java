package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Altapctv
 */
public class AltapctvKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: peticion_peti_numero
	 */
	public java.lang.Long peticion_peti_numero;
	/**
	 * Creates an empty key for Entity Bean: Altapctv
	 */
	public AltapctvKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.AltapctvKey) {
			co.com.telefonica.atiempo.ejb.eb.AltapctvKey o =
				(co.com.telefonica.atiempo.ejb.eb.AltapctvKey) otherKey;
			return ((this.peticion_peti_numero.equals(o.peticion_peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (peticion_peti_numero.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Altapctv
	 */
	public AltapctvKey(
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticion) {
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
