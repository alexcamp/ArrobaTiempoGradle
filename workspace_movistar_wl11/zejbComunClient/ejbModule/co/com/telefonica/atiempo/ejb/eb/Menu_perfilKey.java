package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Menu_perfil
 */
public class Menu_perfilKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: fk_menperf_men_mn_id
	 */
	public java.lang.Long fk_menperf_men_mn_id;
	/**
	 * Implementation field for persistent attribute: fk_menperf_perf_perf_id
	 */
	public java.lang.Long fk_menperf_perf_perf_id;
	/**
	 * Creates an empty key for Entity Bean: Menu_perfil
	 */
	public Menu_perfilKey() {
	}
	/**
	 * Creates a key for Entity Bean: Menu_perfil
	 */
	public Menu_perfilKey(
		co.com.telefonica.atiempo.ejb.eb.PerfilKey argFk_menperf_perf,
		co.com.telefonica.atiempo.ejb.eb.MenuKey argFk_menperf_men) {
		privateSetFk_menperf_perfKey(argFk_menperf_perf);
		privateSetFk_menperf_menKey(argFk_menperf_men);
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey) {
			co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey o =
				(co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey) otherKey;
			return (
				(this
					.fk_menperf_perf_perf_id
					.equals(o.fk_menperf_perf_perf_id))
					&& (this.fk_menperf_men_mn_id.equals(o.fk_menperf_men_mn_id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (
			fk_menperf_perf_perf_id.hashCode()
				+ fk_menperf_men_mn_id.hashCode());
	}
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.MenuKey getFk_menperf_menKey() {
		co.com.telefonica.atiempo.ejb.eb.MenuKey temp =
			new co.com.telefonica.atiempo.ejb.eb.MenuKey();
		boolean fk_menperf_men_NULLTEST = true;
		fk_menperf_men_NULLTEST &= (fk_menperf_men_mn_id == null);
		temp.mn_id = fk_menperf_men_mn_id;
		if (fk_menperf_men_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_menperf_menKey(
		co.com.telefonica.atiempo.ejb.eb.MenuKey inKey) {
		boolean fk_menperf_men_NULLTEST = (inKey == null);
		fk_menperf_men_mn_id = (fk_menperf_men_NULLTEST) ? null : inKey.mn_id;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PerfilKey getFk_menperf_perfKey() {
		co.com.telefonica.atiempo.ejb.eb.PerfilKey temp =
			new co.com.telefonica.atiempo.ejb.eb.PerfilKey();
		boolean fk_menperf_perf_NULLTEST = true;
		fk_menperf_perf_NULLTEST &= (fk_menperf_perf_perf_id == null);
		temp.perf_id = fk_menperf_perf_perf_id;
		if (fk_menperf_perf_NULLTEST)
			temp = null;
		return temp;
	}
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void privateSetFk_menperf_perfKey(
		co.com.telefonica.atiempo.ejb.eb.PerfilKey inKey) {
		boolean fk_menperf_perf_NULLTEST = (inKey == null);
		fk_menperf_perf_perf_id =
			(fk_menperf_perf_NULLTEST) ? null : inKey.perf_id;
	}
}
