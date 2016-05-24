package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Controlvisita
 */
public interface ControlvisitaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Controlvisita
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/ControlvisitaLocalHome";
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocal create(
		java.sql.Timestamp fechahora_registro,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Controlvisita
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.ControlvisitaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.ControlvisitaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Controlvisita
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocal create(
		java.sql.Timestamp fechahora_registro,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException;
	public java.util.Collection findByCodAveCd(java.lang.Long codAveCd) throws javax.ejb.FinderException;
}
