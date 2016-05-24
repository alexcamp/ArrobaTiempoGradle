package co.com.telefonica.atiempo.vpistbba.inventario.ejb;
/**
 * Key class for Entity Bean: Tecnologias_MSAN
 */
public class Tecnologias_MSANKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: equipo
	 */
	public java.lang.String equipo;
	/**
	 * Implementation field for persistent attribute: tecnologia
	 */
	public java.lang.String tecnologia;
	/**
	 * Creates an empty key for Entity Bean: Tecnologias_MSAN
	 */
	public Tecnologias_MSANKey() {
	}
	/**
	 * Creates a key for Entity Bean: Tecnologias_MSAN
	 */
	public Tecnologias_MSANKey(
		java.lang.String equipo,
		java.lang.String tecnologia) {
		this.equipo = equipo;
		this.tecnologia = tecnologia;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANKey) {
			co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANKey o = (co.com.telefonica.atiempo.vpistbba.inventario.ejb.Tecnologias_MSANKey) otherKey;
			return ((this.equipo.equals(o.equipo)) && (this.tecnologia
				.equals(o.tecnologia)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (equipo.hashCode() + tecnologia.hashCode());
	}
}