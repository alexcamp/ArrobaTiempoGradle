package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Tipo_Eq_Elemento
 */
public interface Tipo_Eq_ElementoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tipo_Eq_Elemento
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Tipo_Eq_ElementoLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal create(
		java.lang.Integer id_tipo_eq,
		java.lang.Integer id_elemento) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Tipo_Eq_Elemento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findElementoByTipo(long id_elemento) throws javax.ejb.FinderException;
	public java.util.Collection findByIdElemento(java.lang.Integer id_elemento)
		throws javax.ejb.FinderException;
}
