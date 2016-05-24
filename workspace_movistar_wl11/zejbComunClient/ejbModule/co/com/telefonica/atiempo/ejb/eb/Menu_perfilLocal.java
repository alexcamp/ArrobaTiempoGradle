package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Menu_perfil
 */
public interface Menu_perfilLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.MenuLocal getFk_menperf_men();
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_men.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_menperf_men(
		co.com.telefonica.atiempo.ejb.eb.MenuLocal aFk_menperf_men);
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.PerfilLocal getFk_menperf_perf();
	/**
	 * This method was generated for supporting the relationship role named fk_menperf_perf.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_menperf_perf(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal aFk_menperf_perf);
}
