package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Sla_st
 */
public interface Sla_stLocalHome extends javax.ejb.EJBLocalHome {

	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Sla_stLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Sla_st
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocal create(
		java.lang.Long cod_sgm,
		java.lang.Long cod_sb_sgm,
		java.lang.String ide_pro_cmr,
		java.lang.Long tipo_loc) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Sla_st
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey primaryKey)
		throws javax.ejb.FinderException;
}
