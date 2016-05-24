package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Parametros_PGC
 */
public interface Parametros_PGCLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Parametros_PGC
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Parametros_PGCLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Parametros_PGC
	 */
	public co.com.telefonica.atiempo.ejb.eb.Parametros_PGCLocal create(
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Parametros_PGC
	 */
	public co.com.telefonica.atiempo.ejb.eb.Parametros_PGCLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Parametros_PGCKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByReinSegSub(
		java.lang.Long segmento,
		java.lang.Long subSegmento,
		java.lang.Long reintento) throws javax.ejb.FinderException;
}