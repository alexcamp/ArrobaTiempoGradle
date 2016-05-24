package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Mapeo_loc_apsc
 */
public class Mapeo_loc_apscKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_loc
	 */
	public java.lang.Long cod_loc;
	/**
	 * Implementation field for persistent attribute: nom_sub_loc
	 */
	public java.lang.String nom_sub_loc;
	/**
	 * Creates an empty key for Entity Bean: Mapeo_loc_apsc
	 */
	public Mapeo_loc_apscKey() {
	}
	/**
	 * Creates a key for Entity Bean: Mapeo_loc_apsc
	 */
	public Mapeo_loc_apscKey(
		java.lang.Long cod_loc,
		java.lang.String nom_sub_loc) {
		this.cod_loc = cod_loc;
		this.nom_sub_loc = nom_sub_loc;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey) {
			co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey o = (co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey) otherKey;
			return ((this.cod_loc.equals(o.cod_loc)) && (this.nom_sub_loc
				.equals(o.nom_sub_loc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_loc.hashCode() + nom_sub_loc.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: cod_loc
	 */
	public java.lang.Long getCod_loc() {
		return cod_loc;
	}
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public void setCod_loc(java.lang.Long newCod_loc) {
		cod_loc = newCod_loc;
	}
	/**
	 * Get accessor for persistent attribute: nom_sub_loc
	 */
	public java.lang.String getNom_sub_loc() {
		return nom_sub_loc;
	}
	/**
	 * Set accessor for persistent attribute: nom_sub_loc
	 */
	public void setNom_sub_loc(java.lang.String newNom_sub_loc) {
		nom_sub_loc = newNom_sub_loc;
	}
}
