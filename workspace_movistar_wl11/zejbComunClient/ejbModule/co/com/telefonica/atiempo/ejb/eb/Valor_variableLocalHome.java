package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Valor_variable
 */
public interface Valor_variableLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Valor_variable
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Valor_variableLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Valor_variable
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal create(
		java.lang.Short fk_valcampo_campo_cv_id,
		java.lang.Long fk_valor_bi_bi_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Valor_variable
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Valor_variableLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Valor_variableKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Valor_variable
	 */
	public co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Campo_variableLocal argFk_valcampo_campo,
		co.com.telefonica.atiempo.ejb.eb.BintegradaLocal argFk_valor_bi,
		java.lang.String valor)
		throws javax.ejb.CreateException;
	public java.util.Collection findByNombreAndIdBi(int cvId, java.lang.Long biId) throws javax.ejb.FinderException;
}
