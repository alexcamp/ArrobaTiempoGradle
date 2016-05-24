package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Grupo_segmento
 */
public interface Grupo_segmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Grupo_segmento
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Grupo_segmentoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoLocal create(
		java.lang.Integer grse_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Grupo_segmento
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Grupo_segmentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Grupo_segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Grupo_segmentoLocal create(
		java.lang.Integer grse_id,
		java.lang.String grse_nombre)
		throws javax.ejb.CreateException;
	public java.util.Collection findAllOrderByPorcentaje() throws javax.ejb.FinderException;
}
