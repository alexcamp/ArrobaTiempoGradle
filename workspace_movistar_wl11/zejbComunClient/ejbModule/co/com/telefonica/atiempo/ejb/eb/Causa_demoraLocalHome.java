package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Causa_demora
 */
public interface Causa_demoraLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Causa_demora
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Causa_demoraLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocal create(
		java.lang.Long cod_caudem) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causa_demora
	 */
	public co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey primaryKey)
		throws javax.ejb.FinderException;
	
	public java.util.Collection findAllCausasDemora()
		throws javax.ejb.FinderException;
}