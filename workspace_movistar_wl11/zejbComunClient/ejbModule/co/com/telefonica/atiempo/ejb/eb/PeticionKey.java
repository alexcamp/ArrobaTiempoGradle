package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Peticion
 */
public class PeticionKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: peti_numero
	 */
	public java.lang.Long peti_numero;
	/**
	 * Creates an empty key for Entity Bean: Peticion
	 */
	public PeticionKey() {
	}
	/**
	 * Creates a key for Entity Bean: Peticion
	 */
	public PeticionKey(java.lang.Long peti_numero) {
		this.peti_numero = peti_numero;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.PeticionKey) {
			co.com.telefonica.atiempo.ejb.eb.PeticionKey o =
				(co.com.telefonica.atiempo.ejb.eb.PeticionKey) otherKey;
			return ((this.peti_numero.equals(o.peti_numero)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (peti_numero.hashCode());
	}
	
	public String toString(){
		return String.valueOf(peti_numero);
	}
}
