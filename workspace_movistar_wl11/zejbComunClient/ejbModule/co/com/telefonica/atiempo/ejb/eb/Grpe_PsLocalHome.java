package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Grpe_Ps
 */
public interface Grpe_PsLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Grpe_Ps
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Grpe_PsLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Grpe_Ps
	 */
	public co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal create(
		java.lang.Long ps_Id,
		java.lang.Long grpe_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Grpe_Ps
	 */
	public co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Grpe_PsKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal findGrupoPs(
		java.lang.Long ps_id,
		java.lang.Long grupo_id)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal findGrupoByPS(java.lang.Long ps_id) throws javax.ejb.FinderException;
}
