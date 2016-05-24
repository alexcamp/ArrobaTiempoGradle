package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Flujo_prod_serv_oper_com
 */
public interface Flujo_prod_serv_oper_comLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Flujo_prod_serv_oper_comLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal create(java.lang.Integer flps_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Flujo_prod_serv_oper_com
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Flujo_prod_serv_oper_comKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Flujo_prod_serv_oper_comLocal findByProdServOperCom(
			java.lang.Long psId,
			java.lang.Long opComer)
		throws javax.ejb.FinderException;
}
