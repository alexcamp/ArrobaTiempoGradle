package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Modem
 */
public interface ModemLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/ModemLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal create(
		java.lang.String serial,
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
	 * Finds an instance using a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.ModemKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Modem
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal create(
		java.lang.String serial,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException;
	public java.util.Collection findPeticion(java.lang.Long cod_ave_cd)
		throws javax.ejb.FinderException;
		
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ModemLocal create(
		String serial,
		Peticion_stLocal peticion,
		Long nroTel,
		Short accion)
		throws javax.ejb.CreateException;
}
