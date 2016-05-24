package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Deco_tarjeta
 */
public interface Deco_tarjetaLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Deco_tarjetaLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Deco_tarjeta
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocal create(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
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
	 * Finds an instance using a key for Entity Bean: Deco_tarjeta
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Deco_tarjetaLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Deco_tarjeta
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaLocal create(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException;
	public java.util.Collection findPeticion(java.lang.Long cod_ave_cd)
		throws javax.ejb.FinderException;
}
