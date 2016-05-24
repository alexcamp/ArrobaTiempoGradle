package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Elemento_Peticion
 */
public class Elemento_PeticionKey implements java.io.Serializable {
    static final long serialVersionUID = 3206093459760846163L;
    /**
     * Implementation field for persistent attribute: serial
     */
    public java.lang.String serial;
    public java.lang.Long peticion_st_cod_ave_cd;
    /**
     * Creates an empty key for Entity Bean: Elemento_Peticion
     */
    public Elemento_PeticionKey() {
    }
    /**
     * Creates a key for Entity Bean: Elemento_Peticion
     */
    public Elemento_PeticionKey(
        java.lang.String serial,
        co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey argPeticion_st) {
        this.serial = serial;
        privateSetPeticion_stKey(argPeticion_st);
    }
    /**
     * Returns true if both keys are equal.
     */
    public boolean equals(java.lang.Object otherKey) {
        if (otherKey instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionKey) {
            co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionKey o = (co.com.telefonica.atiempo.soltec.ejb.eb.Elemento_PeticionKey) otherKey;
            return ((this.serial.equals(o.serial)) && (this.peticion_st_cod_ave_cd
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
    public void privateSetPeticion_stKey(
    		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey inKey) {
		boolean peticion_st_NULLTEST = (inKey == null);
		peticion_st_cod_ave_cd =
			(peticion_st_NULLTEST) ? null : inKey.cod_ave_cd;
    }
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey getPeticion_stKey() {
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey temp =
			new co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey();
		boolean peticion_st_NULLTEST = true;
		peticion_st_NULLTEST &= (peticion_st_cod_ave_cd == null);
		temp.cod_ave_cd = peticion_st_cod_ave_cd;
		if (peticion_st_NULLTEST)
			temp = null;
		return temp;
	}
}
