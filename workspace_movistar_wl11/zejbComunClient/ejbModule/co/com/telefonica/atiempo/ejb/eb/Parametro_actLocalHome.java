package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Parametro_act
 */
public interface Parametro_actLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Parametro_act
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Parametro_actLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Parametro_actLocal create(
		java.lang.Long act_cod_cd) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Parametro_act
	 */
	public co.com.telefonica.atiempo.ejb.eb.Parametro_actLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Parametro_actKey primaryKey)
		throws javax.ejb.FinderException;
}