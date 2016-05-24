package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Causal_ps_oc_actividad
 */
public interface Causal_ps_oc_actividadLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Causal_ps_oc_actividad
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Causal_ps_oc_actividadLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Causal_ps_oc_actividadLocal create(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causal_ps_oc_actividad
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Causal_ps_oc_actividadLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Causal_ps_oc_actividadKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByFamPsOcACt(
		java.lang.Long idFamiliaPs,
		java.lang.Long codActividad,
		java.lang.Long codOc)
		throws javax.ejb.FinderException;
}
