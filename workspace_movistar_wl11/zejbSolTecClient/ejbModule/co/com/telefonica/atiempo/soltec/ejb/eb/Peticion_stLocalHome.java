package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Peticion_st
 */
public interface Peticion_stLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_st
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Peticion_stLocalHome";
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal create(
		java.lang.Long cod_ave_cd, Integer estado)
		throws javax.ejb.CreateException ;
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_st
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal create(
		java.lang.Long cod_ave_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Peticion_st
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByCodAveOriSn(java.lang.Long codAveOriSn) throws javax.ejb.FinderException;
	public java.util.Collection findNumClieDv(
		java.lang.String numero,
		java.lang.String dv)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIDPC(java.lang.String idpc) throws javax.ejb.FinderException;
	
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal findByIdPcTVIDE(
		java.lang.String idpctv,
		java.lang.String ide_pro_cmr_cd,
		java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal findByIdPcIDE(
		java.lang.String idpc,
		java.lang.String id_pro_cmr_cd,
		java.lang.Long cod_ave_cd) throws javax.ejb.FinderException;
}
