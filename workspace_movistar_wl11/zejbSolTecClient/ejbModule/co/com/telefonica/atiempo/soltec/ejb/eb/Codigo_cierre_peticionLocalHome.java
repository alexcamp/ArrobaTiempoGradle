package co.com.telefonica.atiempo.soltec.ejb.eb;

import java.sql.Timestamp;

/**
 * Local Home interface for Enterprise Bean: Codigo_cierre_peticion
 */
public interface Codigo_cierre_peticionLocalHome
	extends javax.ejb.EJBLocalHome {

	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Codigo_cierre_peticionLocalHome";	
	
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierre_peticionLocal create(java.lang.Long id_cierre,Long idUser,Long idAct, Peticion_stLocal peticion, Codigo_cierreLocal cierre, Timestamp fecha)
		throws javax.ejb.CreateException;

	/**
	 * Creates an instance from a key for Entity Bean: Codigo_cierre_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierre_peticionLocal create(java.lang.Long id_cierre)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Codigo_cierre_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_cierre_peticionLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Codigo_cierre_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByNumeroPeticion(java.lang.Long numeroPeticion) throws javax.ejb.FinderException;
	public java.util.Collection findByNumeroPeticionIDDesc(java.lang.Long idPeticion) throws javax.ejb.FinderException;
}
