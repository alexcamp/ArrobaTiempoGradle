package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Agrupacion_segmento
 */
public interface Agrupacion_segmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Agrupacion_segmento
	 */
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Agrupacion_segmentoLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoLocal create(java.lang.Long codigo_segmento) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Agrupacion_segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoLocal findByPrimaryKey(co.com.telefonica.atiempo.ejb.eb.Agrupacion_segmentoKey primaryKey)
		throws javax.ejb.FinderException;

	public java.util.Collection findAll() throws javax.ejb.FinderException;
}
