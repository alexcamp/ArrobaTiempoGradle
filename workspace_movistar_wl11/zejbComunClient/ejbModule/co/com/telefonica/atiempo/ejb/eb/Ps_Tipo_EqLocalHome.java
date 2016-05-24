package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Ps_Tipo_Eq
 */
public interface Ps_Tipo_EqLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Ps_Tipo_Eq
	 */
	public static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Ps_Tipo_EqLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal create(
		java.lang.Integer ps_id,
		java.lang.Integer id_tipo_eq) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Ps_Tipo_Eq
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey primaryKey)
		throws javax.ejb.FinderException;
    public co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal findTipoByPs(
        long ps_id) throws javax.ejb.FinderException;
	public java.util.Collection findByIdTipoEquipo(java.lang.Integer id_tipo_eq) throws javax.ejb.FinderException;
}
