package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Deco_Tarjeta_Info_Sap
 */
public class Deco_Tarjeta_Info_SapKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id_elemento
	 */
	public java.lang.String id_elemento;
	/**
	 * Implementation field for persistent attribute: cod_ave_cd
	 */
	public java.lang.Long cod_ave_cd;
	/**
	 * Creates an empty key for Entity Bean: Deco_Tarjeta_Info_Sap
	 */
	public Deco_Tarjeta_Info_SapKey() {
	}
	/**
	 * Creates a key for Entity Bean: Deco_Tarjeta_Info_Sap
	 */
	public Deco_Tarjeta_Info_SapKey(
		java.lang.String id_elemento,
		java.lang.Long cod_ave_cd) {
		this.id_elemento = id_elemento;
		this.cod_ave_cd = cod_ave_cd;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey o = (co.com.telefonica.atiempo.soltec.ejb.eb.Deco_Tarjeta_Info_SapKey) otherKey;
			return ((this.id_elemento.equals(o.id_elemento)) && (this.cod_ave_cd
				.equals(o.cod_ave_cd)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id_elemento.hashCode() + cod_ave_cd.hashCode());
	}
}