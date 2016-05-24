package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Deco_Tarjeta_Info_Sap
 */
public interface Deco_Tarjeta_Info_SapLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Deco_Tarjeta_Info_SapLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Deco_Tarjeta_Info_Sap
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocal create(
		java.lang.String id_elemento,
		java.lang.Long peti_numero) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Deco_Tarjeta_Info_Sap
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Deco_Tarjeta_Info_SapKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.Long petiNumero)
		throws javax.ejb.FinderException;
}