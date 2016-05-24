package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Elemento_Peticion
 */
public class Elemento_PeticionKey implements java.io.Serializable {
    static final long serialVersionUID = 3206093459760846163L;
    /**
     * Implementation field for persistent attribute: serial
     */
    public java.lang.String serial;
    public java.lang.Long peticion_peti_numero;
    /**
     * Creates an empty key for Entity Bean: Elemento_Peticion
     */
    public Elemento_PeticionKey() {
    }
    /**
     * Creates a key for Entity Bean: Elemento_Peticion
     */
    public Elemento_PeticionKey(		java.lang.String serial,
    	co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticion) {
		this.serial = serial;
		privateSetPeticionKey(argPeticion);
    }
    /**
     * Returns true if both keys are equal.
     */
    public boolean equals(java.lang.Object otherKey) {
        if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey) {
            co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey o = (co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey) otherKey;
            return ((this.serial.equals(o.serial)));
        }
        return false;
    }
    /**
     * Returns the hash code for the key.
     */
    public int hashCode() {
        return (serial.hashCode());
    }
    /**
     * Get accessor for persistent attribute: serial
     */
    public java.lang.String getSerial() {
        return serial;
    }
    /**
     * Set accessor for persistent attribute: serial
     */
    public void setSerial(java.lang.String newSerial) {
        serial = newSerial;
    }
    public void privateSetPeticionKey(
    		co.com.telefonica.atiempo.ejb.eb.PeticionKey inKey) {
    		boolean peticion_NULLTEST = (inKey == null);
    		peticion_peti_numero = (peticion_NULLTEST) ? null : inKey.peti_numero;
    }
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
}
