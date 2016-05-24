package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Controlvisita
 */
public class ControlvisitaKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: peticion_peti_numero
	 */
	public java.lang.Long peticion_peti_numero;
	/**
	 * Implementation field for persistent attribute: fechahora_registro
	 */
	public java.sql.Timestamp fechahora_registro;
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
			instanceof co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey) {
			co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey o =
				(co.com.telefonica.atiempo.ejb.eb.ControlvisitaKey) otherKey;
			return (
				(this.fechahora_registro.equals(o.fechahora_registro))
					&& (this.peticion_peti_numero.equals(o.peticion_peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fechahora_registro.hashCode() + peticion_peti_numero.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Controlvisita
	 */
	public ControlvisitaKey(
		java.sql.Timestamp fechahora_registro,
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticion) {
		this.fechahora_registro = fechahora_registro;
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
