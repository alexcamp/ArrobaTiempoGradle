package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
/**
 * Local interface for Enterprise Bean: Catalago_prueba_linea
 */
public interface Catalago_prueba_lineaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public void setDescripcion(java.lang.String newDescripcion);
	/**
	 * Get accessor for persistent attribute: grupo_trabajo
	 */
	public java.lang.String getGrupo_trabajo();
	/**
	 * Set accessor for persistent attribute: grupo_trabajo
	 */
	public void setGrupo_trabajo(java.lang.String newGrupo_trabajo);
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPrueba_linea();
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPrueba_linea(java.util.Collection aPrueba_linea);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Familia_producto_servicio_stLocal getFamilia_producto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
	/**
	 * Get accessor for persistent attribute: tipoIncidencia
	 */
	public java.lang.String getTipoIncidencia();
	/**
	 * Set accessor for persistent attribute: tipoIncidencia
	 */
	public void setTipoIncidencia(java.lang.String newTipoIncidencia);
}
