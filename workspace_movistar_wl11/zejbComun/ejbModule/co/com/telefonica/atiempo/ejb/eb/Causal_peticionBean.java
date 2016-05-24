package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Causal_peticion
 */
public abstract class Causal_peticionBean implements javax.ejb.EntityBean {
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
	
	public co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey ejbCreate(
		java.lang.Long id_causal_peticion)
		throws javax.ejb.CreateException {
		setId_causal_peticion(id_causal_peticion);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long id_causal_peticion)
		throws javax.ejb.CreateException {
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Causal_peticionKey ejbCreate(Long id,Catalogo_causalLocal catalogo_causalLocal,Estado_psLocal estado_psLocal,Estado_ps_peticionLocal estado_ps_peticionLocal,UsuarioLocal usuarioLocal) throws javax.ejb.CreateException
	{
		setId_causal_peticion(id);
		return null;	
	}
	
	public void ejbPostCreate(Long id,Catalogo_causalLocal catalogo_causalLocal,Estado_psLocal estado_psLocal,Estado_ps_peticionLocal estado_ps_peticionLocal,UsuarioLocal usuarioLocal) throws javax.ejb.CreateException
	{
		setCatalogo_causal(catalogo_causalLocal);
		setEstado_ps(estado_psLocal);
		setEstado_ps_peticion(estado_ps_peticionLocal);
		setUsuario(usuarioLocal);			
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
	 * Get accessor for persistent attribute: cod_actividad
	 */
	public abstract java.lang.Long getCod_actividad();
	/**
	 * Set accessor for persistent attribute: cod_actividad
	 */
	public abstract void setCod_actividad(java.lang.Long newCod_actividad);
	/**
	 * Get accessor for persistent attribute: novedad
	 */
	public abstract java.lang.String getNovedad();
	/**
	 * Set accessor for persistent attribute: novedad
	 */
	public abstract void setNovedad(java.lang.String newNovedad);
	/**
	 * Get accessor for persistent attribute: fecha_inicio
	 */
	public abstract java.lang.String getFecha_inicio();
	/**
	 * Set accessor for persistent attribute: fecha_inicio
	 */
	public abstract void setFecha_inicio(java.lang.String newFecha_inicio);
	/**
	 * Get accessor for persistent attribute: fecha_termino
	 */
	public abstract java.lang.String getFecha_termino();
	/**
	 * Set accessor for persistent attribute: fecha_termino
	 */
	public abstract void setFecha_termino(java.lang.String newFecha_termino);
	/**
	 * Get accessor for persistent attribute: id_causal_peticion
	 */
	public abstract java.lang.Long getId_causal_peticion();
	/**
	 * Set accessor for persistent attribute: id_causal_peticion
	 */
	public abstract void setId_causal_peticion(
		java.lang.Long newId_causal_peticion);
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Catalogo_causalLocal getCatalogo_causal();
	/**
	 * This method was generated for supporting the relationship role named catalogo_causal.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCatalogo_causal(
		co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal aCatalogo_causal);
	/**
	 * This method was generated for supporting the relationship role named estado_ps.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Estado_psLocal getEstado_ps();
	/**
	 * This method was generated for supporting the relationship role named estado_ps.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEstado_ps(
		co.com.telefonica.atiempo.ejb.eb.Estado_psLocal anEstado_ps);
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Estado_ps_peticionLocal getEstado_ps_peticion();
	/**
	 * This method was generated for supporting the relationship role named estado_ps_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEstado_ps_peticion(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Estado_ps_peticionLocal anEstado_ps_peticion);
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getUsuario();
	/**
	 * This method was generated for supporting the relationship role named usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUsuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal anUsuario);
	
	

}
