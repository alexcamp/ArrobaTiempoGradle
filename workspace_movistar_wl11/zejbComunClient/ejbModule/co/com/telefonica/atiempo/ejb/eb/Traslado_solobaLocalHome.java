package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Traslado_soloba
 */
public interface Traslado_solobaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Traslado_soloba
	 */ 
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Traslado_solobaLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal create(
		java.lang.Long id_trasoba)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Traslado_soloba
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Traslado_solobaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Traslado_solobaKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Traslado_solobaLocal findByNumIdNuBaja(
		java.lang.String numIdeNuBaja)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Traslado_solobaLocal findByLineaAnterior(
			java.lang.String lineaAnterior)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Traslado_solobaLocal findByPetiAlta(
		java.lang.Long nroPetAtisAlta)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Traslado_solobaLocal findByPetiBaja(
		java.lang.Long nroPetiBaja)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal findPetiTraslado(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
}
