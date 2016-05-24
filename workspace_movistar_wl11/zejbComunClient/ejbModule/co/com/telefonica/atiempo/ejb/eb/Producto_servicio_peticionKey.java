package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Producto_servicio_peticion
 */
public class Producto_servicio_peticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: correlativo
	 */
	public java.lang.Long correlativo;
	/**
	 * Implementation field for persistent attribute: fk_psp_pet_peti_numero
	 */
	public java.lang.Long fk_psp_pet_peti_numero;
	/**
	 * Creates an empty key for Entity Bean: Producto_servicio_peticion
	 */
	public Producto_servicio_peticionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Producto_servicio_peticion
	 */
	public Producto_servicio_peticionKey(
		java.lang.Long correlativo,
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argFk_psp_pet) {
		this.correlativo = correlativo;
		privateSetFk_psp_petKey(argFk_psp_pet);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionKey) {
			co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionKey o =
				(co
					.com
					.telefonica
					.atiempo
					.ejb
					.eb
					.Producto_servicio_peticionKey) otherKey;
			return (
				(this.correlativo.equals(o.correlativo))
					&& (this
						.fk_psp_pet_peti_numero
						.equals(o.fk_psp_pet_peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (correlativo.hashCode() + fk_psp_pet_peti_numero.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionKey getFk_psp_petKey() {
		co.com.telefonica.atiempo.ejb.eb.PeticionKey temp =
			new co.com.telefonica.atiempo.ejb.eb.PeticionKey();
		boolean fk_psp_pet_NULLTEST = true;
		fk_psp_pet_NULLTEST &= (fk_psp_pet_peti_numero == null);
		temp.peti_numero = fk_psp_pet_peti_numero;
		if (fk_psp_pet_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_psp_pet.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_psp_petKey(
		co.com.telefonica.atiempo.ejb.eb.PeticionKey inKey) {
		boolean fk_psp_pet_NULLTEST = (inKey == null);
		fk_psp_pet_peti_numero =
			(fk_psp_pet_NULLTEST) ? null : inKey.peti_numero;
	}
}
