package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Localizacion
 */
public interface LocalizacionLocalHome extends javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/LocalizacionLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Localizacion
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionLocal create(
		java.lang.Integer cod_loc)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Localizacion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.LocalizacionLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAll() throws javax.ejb.FinderException;
	public java.util.Collection findByFAPSID(java.lang.Long faps_id)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionLocal findByPrimarykey(java.lang.Integer cod_loc) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.LocalizacionLocal findbyLocalizacion(java.lang.String localizacion) throws javax.ejb.FinderException;
}
