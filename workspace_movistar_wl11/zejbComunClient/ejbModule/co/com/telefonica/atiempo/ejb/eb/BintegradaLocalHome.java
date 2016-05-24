package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Bintegrada
 */
public interface BintegradaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Bintegrada
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/BintegradaLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal create(
		java.lang.Long bi_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Bintegrada
	 */
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.BintegradaKey primaryKey)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.BintegradaLocal findPeticionActividadUsuario(
			java.lang.Long nroPeticion,
			java.lang.Long idActividad,
			java.lang.Long idUsuario)
		throws javax.ejb.FinderException;
	public java.util.Collection findListByPeticion(java.lang.Long nroPeticion) throws javax.ejb.FinderException;
	public java.util.Collection findIdUsuario(java.lang.Long idUsuario, java.lang.Long rolId) throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.BintegradaLocal findByIdActIdPetiAp(
		java.lang.Long idActividad,
		java.lang.Long nroPeticion,
		java.lang.Long idAplicacion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdAplicacionNroPeticion(java.lang.Long idAplicacion, java.lang.Long nroPeticion) throws javax.ejb.FinderException;
	public java.util.Collection findVisiblesByIdAplicacionNroPeticion(
		java.lang.Long idAplicacion,
		java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal findByNroPeticion(
		java.lang.Long nroPeticion)
		throws javax.ejb.FinderException;
	public java.util.Collection findByIdAplicacionNroPeticionIdActividad(
		java.lang.Long idAplicacion,
		java.lang.Long nroPeticion,
		java.lang.Long idActividad)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal create(
		java.lang.Long bi_id,
		java.lang.Long bi_nro_peticion,
		java.sql.Timestamp bi_fecha_compromiso,
//		cr17031 pcawen
		java.sql.Timestamp bi_fecha_compromiso_sec,
		java.lang.String bi_cod_cat,
		java.lang.String bi_cliente_nombre,
		java.lang.String bi_cliente_apellidos,
		java.lang.String bi_familia_ps,
		java.lang.String bi_url_detalle,
		java.lang.Integer bi_puntaje,
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal argFk_bintegrada_ap,
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal argFk_bi_act,
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal argFk_bi_usuario)
		throws javax.ejb.CreateException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.BintegradaLocal findByUsuarioPetVisible(
		java.lang.Long usuaActual,
		java.lang.Long nroPet)
		throws javax.ejb.FinderException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.BintegradaLocal findByVisiblePetApl(
		java.lang.Long nroPet,
		java.lang.Long apId)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal findByIdApliNroPeticionIdActiVisibleDos(java.lang.Long idAplicacion, java.lang.Long nroPeticion, java.lang.Long idActividad) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal findLastDateByPetiNum(
		java.lang.Long bi_nro_peticion) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.BintegradaLocal findByMaxAtivity(java.lang.Long idPeticion, java.lang.Long idActividad) throws javax.ejb.FinderException;
}
