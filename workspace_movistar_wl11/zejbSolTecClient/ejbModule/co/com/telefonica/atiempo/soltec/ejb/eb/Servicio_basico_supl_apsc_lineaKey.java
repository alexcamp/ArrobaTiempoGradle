package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Servicio_basico_supl_apsc_linea
 */
public class Servicio_basico_supl_apsc_lineaKey
	implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_servicio_basico
	 */
	public java.lang.Long id_servicio_basico;
	/**
	 * Creates an empty key for Entity Bean: Servicio_basico_supl_apsc_linea
	 */
	public Servicio_basico_supl_apsc_lineaKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Servicio_basico_supl_apsc_lineaKey) {
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Servicio_basico_supl_apsc_lineaKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Servicio_basico_supl_apsc_lineaKey) otherKey;
			return ((this.id_servicio_basico.equals(o.id_servicio_basico)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_servicio_basico.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Servicio_basico_supl_apsc_linea
	 */
	public Servicio_basico_supl_apsc_lineaKey(
		java.lang.Long id_servicio_basico) {
		this.id_servicio_basico = id_servicio_basico;
	}
}
