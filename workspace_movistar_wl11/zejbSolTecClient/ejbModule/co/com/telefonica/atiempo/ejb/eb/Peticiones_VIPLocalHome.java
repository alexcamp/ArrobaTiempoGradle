package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Peticiones_VIP
 */
public interface Peticiones_VIPLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticiones_VIP
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Peticiones_VIPLocalHome";
	 
	/**
	 * Creates an instance from a key for Entity Bean: Peticiones_VIP
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal create(
		java.lang.Long correlativo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Peticiones_VIP
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal findbyLocSegSUB(
		java.lang.String localidad,
		java.lang.String segmento,
		java.lang.String subsegmento) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal findByLocSeg(
		java.lang.String localidad,
		java.lang.String segmento) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal findByLocSub(
		java.lang.String localidad,
		java.lang.String subsegmento) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticiones_VIPLocal findByLoc(java.lang.String localidad) throws javax.ejb.FinderException;
}