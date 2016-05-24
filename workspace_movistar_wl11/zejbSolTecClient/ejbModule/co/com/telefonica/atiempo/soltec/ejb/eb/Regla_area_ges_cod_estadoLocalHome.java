package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Regla_area_ges_cod_estado
 */
public interface Regla_area_ges_cod_estadoLocalHome
	extends javax.ejb.EJBLocalHome {
		static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Regla_area_ges_cod_estadoLocalHome";		
	/**
	 * Creates an instance from a key for Entity Bean: Regla_area_ges_cod_estado
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Regla_area_ges_cod_estadoLocal create(
			java.lang.Long id_regla_area_estado)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Regla_area_ges_cod_estado
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Regla_area_ges_cod_estadoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Regla_area_ges_cod_estadoKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Regla_area_ges_cod_estadoLocal findByAreaEstado(java.lang.Long cod_area_ges, java.lang.Long faps_id, java.lang.Integer tipo_estado, java.lang.String valor_estado) throws javax.ejb.FinderException;
}
