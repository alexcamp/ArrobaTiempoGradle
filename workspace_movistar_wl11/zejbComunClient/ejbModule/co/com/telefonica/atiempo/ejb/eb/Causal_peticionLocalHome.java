package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Causal_peticion
 */
public interface Causal_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Causal_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Causal_peticionLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Causal_peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal create(
		java.lang.Long id_causal_peticion)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Causal_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Causal_peticionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal create(
		Long id,
		Catalogo_causalLocal catalogo_causalLocal,
		Estado_psLocal estado_psLocal,
		Estado_ps_peticionLocal estado_ps_peticionLocal,
		UsuarioLocal usuarioLocal)
		throws javax.ejb.CreateException;
	public java.util.Collection findCausalesDePsOrderDes(java.lang.Long correlativoEstadoPs) throws javax.ejb.FinderException;
	public java.util.Collection findCausalesDePsOrdenDesByAct(
		java.lang.Long correlativo,
		java.lang.Long codActividad)
		throws javax.ejb.FinderException;
}
