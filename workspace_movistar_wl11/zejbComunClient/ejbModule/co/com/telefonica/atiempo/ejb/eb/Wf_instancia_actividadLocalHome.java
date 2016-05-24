package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Wf_instancia_actividad
 */
public interface Wf_instancia_actividadLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Wf_instancia_actividad
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Wf_instancia_actividadLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocal create(
		java.lang.String id_instancia_actividad)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Wf_instancia_actividad
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Wf_instancia_actividadLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Wf_instancia_actividadKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Wf_instancia_actividad
	 */
	public co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocal create(
		java.lang.String id_instancia_actividad,
		java.lang.String id_proceso,
		java.lang.Long id_instancia_proceso,
		java.lang.String codigo_actividad,
		java.lang.String xmlparams)
		throws javax.ejb.CreateException;
		
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Wf_instancia_actividadLocal findInstanciaByProcesoTemplateActividad(
			java.lang.String idProceso,
			java.lang.Long idInstanciaProceso,
			java.lang.String codigoActividad)
		throws javax.ejb.FinderException;
}
