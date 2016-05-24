package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Regla_area_ges_cod_estado
 */
public class Regla_area_ges_cod_estadoKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_regla_area_estado
	 */
	public java.lang.Long id_regla_area_estado;
	/**
	 * Creates an empty key for Entity Bean: Regla_area_ges_cod_estado
	 */
	public Regla_area_ges_cod_estadoKey() {
	}
	/**
	 * Creates a key for Entity Bean: Regla_area_ges_cod_estado
	 */
	public Regla_area_ges_cod_estadoKey(java.lang.Long id_regla_area_estado) {
		this.id_regla_area_estado = id_regla_area_estado;
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
				.Regla_area_ges_cod_estadoKey) {
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Regla_area_ges_cod_estadoKey o =
				(co
					.com
					.telefonica
					.atiempo
					.soltec
					.ejb
					.eb
					.Regla_area_ges_cod_estadoKey) otherKey;
			return ((this.id_regla_area_estado.equals(o.id_regla_area_estado)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_regla_area_estado.hashCode());
	}
}
