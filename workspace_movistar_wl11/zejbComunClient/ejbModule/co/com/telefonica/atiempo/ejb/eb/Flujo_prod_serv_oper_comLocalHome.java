package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo_prod_serv_oper_com
 */
public interface Flujo_prod_serv_oper_comLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Flujo_prod_serv_oper_comLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal create(
		java.lang.Integer flps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Flujo_prod_serv_oper_comKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal create(
		java.lang.Integer flps_id,
		java.lang.Long prse_id,
		java.lang.Long opco_id,
		java.lang.Integer fluj_id)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.ejb.eb.Flujo_prod_serv_oper_comLocal findByProdServOperCom(java.lang.Long idPS, java.lang.Long idOpCom) throws javax.ejb.FinderException;
}
