package co.com.telefonica.atiempo.soltec.ejb.eb;

/**
 * Local Home interface for Enterprise Bean: Tmp_deco_tarjeta
 */
public interface Tmp_deco_tarjetaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_deco_tarjeta
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Tmp_deco_tarjetaLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Tmp_deco_tarjeta
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tmp_deco_tarjetaLocal create(
		java.lang.Long id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tmp_deco_tarjeta
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Tmp_deco_tarjetaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Tmp_deco_tarjetaKey primaryKey)
		throws javax.ejb.FinderException;

	public co
	.com
	.telefonica
	.atiempo
	.soltec
	.ejb
	.eb
	.Tmp_deco_tarjetaLocal create(
		Long id,
		Peticion_stLocal pet,
		String xml)
		throws javax.ejb.CreateException;
		
	public java.util.Collection findByNroPeticion(java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
}
