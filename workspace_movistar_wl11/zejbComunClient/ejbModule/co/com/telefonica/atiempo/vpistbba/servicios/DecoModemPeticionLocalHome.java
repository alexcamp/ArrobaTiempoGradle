package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: DecoModemPeticion
 */
public interface DecoModemPeticionLocalHome extends javax.ejb.EJBLocalHome {
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/DecoModemPeticionLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: DecoModemPeticion
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal create(
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: DecoModemPeticion
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal findByPrimaryKey(
		java.lang.Long primaryKey) throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.String peticion) throws javax.ejb.FinderException;
	public java.util.Collection findEquipos(java.lang.String peticion, java.lang.Long tipo) throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByCiclo(java.lang.Long ciclo, java.lang.Long estadopet) throws javax.ejb.FinderException;
}