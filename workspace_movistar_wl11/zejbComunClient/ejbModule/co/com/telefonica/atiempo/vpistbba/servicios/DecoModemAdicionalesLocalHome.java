package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: DecoModemAdicionales
 */
public interface DecoModemAdicionalesLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: DecoModemAdicionales
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/DecoModemAdicionalesLocalHome";
	/**
	 * Creates an instance  from a key for Entity Bean: DecoModemAdicionales
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesLocal create(
		java.lang.Long id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: DecoModemAdicionales
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.DecoModemAdicionalesKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByTipo(java.lang.Long tipo) throws javax.ejb.FinderException;
}