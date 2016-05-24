package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
/**
 * Bean implementation class for Enterprise Bean: Catalago_prueba_linea
 */
public abstract class Catalago_prueba_lineaBean
	implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
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
		.Catalago_prueba_lineaKey ejbCreate(
			java.lang.Integer cod_prueba,
			co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stLocal argFamilia_producto_servicio_st)
		throws javax.ejb.CreateException {
		setCod_prueba(cod_prueba);
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stKey argFamilia_producto_servicio_stPK =
			(co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Familia_producto_servicio_stKey) argFamilia_producto_servicio_st
				.getPrimaryKey();
		setFamilia_producto_servicio_st_faps_id(
			argFamilia_producto_servicio_stPK.faps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_prueba,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal argFamilia_producto_servicio_st)
		throws javax.ejb.CreateException {
		setFamilia_producto_servicio_st(argFamilia_producto_servicio_st);
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * Get accessor for persistent attribute: cod_prueba
	 */
	public abstract java.lang.Integer getCod_prueba();
	/**
	 * Set accessor for persistent attribute: cod_prueba
	 */
	public abstract void setCod_prueba(java.lang.Integer newCod_prueba);
	/**
	 * Get accessor for persistent attribute: descripcion
	 */
	public abstract java.lang.String getDescripcion();
	/**
	 * Set accessor for persistent attribute: descripcion
	 */
	public abstract void setDescripcion(java.lang.String newDescripcion);
	/**
	 * Get accessor for persistent attribute: grupo_trabajo
	 */
	public abstract java.lang.String getGrupo_trabajo();
	/**
	 * Set accessor for persistent attribute: grupo_trabajo
	 */
	public abstract void setGrupo_trabajo(java.lang.String newGrupo_trabajo);
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPrueba_linea();
	/**
	 * This method was generated for supporting the relationship role named prueba_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPrueba_linea(java.util.Collection aPrueba_linea);
	/**
	 * This method was generated for supporting the relationship role named familia_producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
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
	public abstract void setFamilia_producto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Familia_producto_servicio_stLocal aFamilia_producto_servicio_st);
	/**
	 * ejbCreate
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
		.Catalago_prueba_lineaKey ejbCreate(
			java.lang.Integer cod_prueba,
			java.lang.Long familia_producto_servicio_st_faps_id)
		throws javax.ejb.CreateException {
		setCod_prueba(cod_prueba);
		setFamilia_producto_servicio_st_faps_id(familia_producto_servicio_st_faps_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer cod_prueba,
		java.lang.Long familia_producto_servicio_st_faps_id)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: familia_producto_servicio_st_faps_id
	 */
	public abstract java.lang.Long getFamilia_producto_servicio_st_faps_id();
	/**
	 * Set accessor for persistent attribute: familia_producto_servicio_st_faps_id
	 */
	public abstract void setFamilia_producto_servicio_st_faps_id(
		java.lang.Long newFamilia_producto_servicio_st_faps_id);
	/**
	 * Get accessor for persistent attribute: tipoIncidencia
	 */
	public abstract java.lang.String getTipoIncidencia();
	/**
	 * Set accessor for persistent attribute: tipoIncidencia
	 */
	public abstract void setTipoIncidencia(java.lang.String newTipoIncidencia);
}
