package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Menu_perfil
 */
public interface Menu_perfilLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Menu_perfil
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Menu_perfilLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Menu_perfilLocal create(
		co.com.telefonica.atiempo.ejb.eb.PerfilLocal argFk_menperf_perf,
		co.com.telefonica.atiempo.ejb.eb.MenuLocal argFk_menperf_men)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Menu_perfil
	 */
	public co.com.telefonica.atiempo.ejb.eb.Menu_perfilLocal create(
		java.lang.Long fk_menperf_perf_perf_id,
		java.lang.Long fk_menperf_men_mn_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Menu_perfil
	 */
	public co.com.telefonica.atiempo.ejb.eb.Menu_perfilLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Menu_perfilKey primaryKey)
		throws javax.ejb.FinderException;
}
