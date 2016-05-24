package co.com.telefonica.atiempo.vpistbba.servicios;
/**
 * Local Home interface for Enterprise Bean: Estructura_Interfaz
 */
public interface Estructura_InterfazLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Estructura_Interfaz
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/vpistbba/servicios/Estructura_InterfazLocalHome";
	
	public co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocal create(
		java.lang.String aci_tr,
		java.lang.Long aci_ap_id,
		java.lang.String aci_tag) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Estructura_Interfaz
	 */
	public co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazLocal findByPrimaryKey(
		co.com.telefonica.atiempo.vpistbba.servicios.Estructura_InterfazKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByArray(
		java.lang.String aci_tr,
		java.lang.Long aci_ap_id,
		java.lang.String aci_arreglo) throws javax.ejb.FinderException;
	public java.util.Collection findByAci_id(java.lang.String aci_tr, java.lang.Long aci_ap_id) throws javax.ejb.FinderException;
}