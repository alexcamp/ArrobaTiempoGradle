package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Catalogo_causal
 */
public interface Catalogo_causalLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Catalogo_causal
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Catalogo_causalLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal create(
		java.lang.Long cod_causal)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Catalogo_causal
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal findByDescripcion(
		java.lang.String descripcion_causal) throws javax.ejb.FinderException;
}
