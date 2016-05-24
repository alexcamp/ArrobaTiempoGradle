package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: Catalago_prueba_linea
 */
public interface Catalago_prueba_lineaLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Catalago_prueba_linea
	 */
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/actividades/cancelacion/ejb/sb/Catalago_prueba_lineaLocalHome";
	
	/**
	 * Creates an instance from a key for Entity Bean: Catalago_prueba_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.actividades
		.cancelacion
		.ejb
		.sb
		.Catalago_prueba_lineaLocal create(
			java.lang.Integer cod_prueba,
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stLocal argFamilia_producto_servicio_st)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Catalago_prueba_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.actividades
		.cancelacion
		.ejb
		.sb
		.Catalago_prueba_lineaLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.actividades
				.cancelacion
				.ejb
				.sb
				.Catalago_prueba_lineaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByFamilia(java.lang.Long faps_id)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Catalago_prueba_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.actividades
		.cancelacion
		.ejb
		.sb
		.Catalago_prueba_lineaLocal create(
			java.lang.Integer cod_prueba,
			java.lang.Long familia_producto_servicio_st_faps_id)
		throws javax.ejb.CreateException;
	public co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal findByDescription(java.lang.String descripcion, java.lang.Long id_faps) throws javax.ejb.FinderException;
}
