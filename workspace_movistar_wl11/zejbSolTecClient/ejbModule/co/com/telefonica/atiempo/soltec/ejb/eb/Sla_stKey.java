package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Key class for Entity Bean: Sla_st
 */
public class Sla_stKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: cod_sgm
	 */
	public java.lang.Long cod_sgm;
	/**
	 * Implementation field for persistent attribute: cod_sb_sgm
	 */
	public java.lang.Long cod_sb_sgm;
	/**
	 * Implementation field for persistent attribute: ide_pro_cmr
	 */
	public java.lang.String ide_pro_cmr;
	/**
	 * Implementation field for persistent attribute: tipo_loc
	 */
	public java.lang.Long tipo_loc;
	/**
	 * Creates an empty key for Entity Bean: Sla_st
	 */
	public Sla_stKey() {
	}
	/**
	 * Creates a key for Entity Bean: Sla_st
	 */
	public Sla_stKey(
		java.lang.Long cod_sgm,
		java.lang.Long cod_sb_sgm,
		java.lang.String ide_pro_cmr,
		java.lang.Long tipo_loc) {
		this.cod_sgm = cod_sgm;
		this.cod_sb_sgm = cod_sb_sgm;
		this.ide_pro_cmr = ide_pro_cmr;
		this.tipo_loc = tipo_loc;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey) {
			co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey o = (co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey) otherKey;
			return ((this.cod_sgm.equals(o.cod_sgm))
				&& (this.cod_sb_sgm.equals(o.cod_sb_sgm))
				&& (this.ide_pro_cmr.equals(o.ide_pro_cmr)) && (this.tipo_loc
				.equals(o.tipo_loc)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (cod_sgm.hashCode()
			+ cod_sb_sgm.hashCode()
			+ ide_pro_cmr.hashCode() + tipo_loc.hashCode());
	}
	/**
	 * Get accessor for persistent attribute: cod_sgm
	 */
	public java.lang.Long getCod_sgm() {
		return cod_sgm;
	}
	/**
	 * Set accessor for persistent attribute: cod_sgm
	 */
	public void setCod_sgm(java.lang.Long newCod_sgm) {
		cod_sgm = newCod_sgm;
	}
	/**
	 * Get accessor for persistent attribute: cod_sb_sgm
	 */
	public java.lang.Long getCod_sb_sgm() {
		return cod_sb_sgm;
	}
	/**
	 * Set accessor for persistent attribute: cod_sb_sgm
	 */
	public void setCod_sb_sgm(java.lang.Long newCod_sb_sgm) {
		cod_sb_sgm = newCod_sb_sgm;
	}
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr
	 */
	public java.lang.String getIde_pro_cmr() {
		return ide_pro_cmr;
	}
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr
	 */
	public void setIde_pro_cmr(java.lang.String newIde_pro_cmr) {
		ide_pro_cmr = newIde_pro_cmr;
	}
	/**
	 * Get accessor for persistent attribute: tipo_loc
	 */
	public java.lang.Long getTipo_loc() {
		return tipo_loc;
	}
	/**
	 * Set accessor for persistent attribute: tipo_loc
	 */
	public void setTipo_loc(java.lang.Long newTipo_loc) {
		tipo_loc = newTipo_loc;
	}
}
