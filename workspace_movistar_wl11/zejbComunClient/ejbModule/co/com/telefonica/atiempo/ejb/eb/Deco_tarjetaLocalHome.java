package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Deco_tarjeta
 */
public interface Deco_tarjetaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Deco_tarjeta
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Deco_tarjetaLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Deco_tarjeta
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal create(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Deco_tarjeta
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.Long petiNumero)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticionOpCoAsc(
		java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Deco_tarjeta
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal create(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaLocal findByReferent(
		java.lang.String serial_deco,
		java.lang.String deco_marca,
		java.lang.String deco_reference,
		java.lang.Long peti_numero) throws javax.ejb.FinderException;
}
