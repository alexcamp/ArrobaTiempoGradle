package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Segmento
 */
public interface SegmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Segmento
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/SegmentoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal create(
		java.lang.Long segm_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.SegmentoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal create(
		java.lang.Long segm_id,
		java.lang.String segm_descripcion,
		java.lang.String segm_codigo)
		throws javax.ejb.CreateException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal findByCodigo(java.lang.String codigo) throws javax.ejb.FinderException;
}
