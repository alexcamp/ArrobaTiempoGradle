package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local interface for Enterprise Bean: Usuario
 */
public interface UsuarioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: usua_login
	 */
	public java.lang.String getUsua_login();
	/**
	 * Set accessor for persistent attribute: usua_login
	 */
	public void setUsua_login(java.lang.String newUsua_login);
	/**
	 * Get accessor for persistent attribute: usua_nombre
	 */
	public java.lang.String getUsua_nombre();
	/**
	 * Set accessor for persistent attribute: usua_nombre
	 */
	public void setUsua_nombre(java.lang.String newUsua_nombre);
	/**
	 * Get accessor for persistent attribute: usua_direccion
	 */
	public java.lang.String getUsua_direccion();
	/**
	 * Set accessor for persistent attribute: usua_direccion
	 */
	public void setUsua_direccion(java.lang.String newUsua_direccion);
	/**
	 * Get accessor for persistent attribute: usua_email
	 */
	public java.lang.String getUsua_email();
	/**
	 * Set accessor for persistent attribute: usua_email
	 */
	public void setUsua_email(java.lang.String newUsua_email);
	/**
	 * Get accessor for persistent attribute: usua_telefono
	 */
	public java.lang.String getUsua_telefono();
	/**
	 * Set accessor for persistent attribute: usua_telefono
	 */
	public void setUsua_telefono(java.lang.String newUsua_telefono);
	/**
	 * Get accessor for persistent attribute: usua_rut
	 */
	public java.lang.String getUsua_rut();
	/**
	 * Set accessor for persistent attribute: usua_rut
	 */
	public void setUsua_rut(java.lang.String newUsua_rut);
	/**
	 * Get accessor for persistent attribute: usua_cargo
	 */
	public java.lang.String getUsua_cargo();
	/**
	 * Set accessor for persistent attribute: usua_cargo
	 */
	public void setUsua_cargo(java.lang.String newUsua_cargo);
	/**
	 * Get accessor for persistent attribute: usua_habilitado
	 */
	public java.lang.String getUsua_habilitado();
	/**
	 * Set accessor for persistent attribute: usua_habilitado
	 */
	public void setUsua_habilitado(java.lang.String newUsua_habilitado);
	/**
	 * Get accessor for persistent attribute: usua_password
	 */
	public java.lang.String getUsua_password();
	/**
	 * Set accessor for persistent attribute: usua_password
	 */
	public void setUsua_password(java.lang.String newUsua_password);
	/**
	 * Get accessor for persistent attribute: usua_idca
	 */
	public java.lang.String getUsua_idca();
	/**
	 * Set accessor for persistent attribute: usua_idca
	 */
	public void setUsua_idca(java.lang.String newUsua_idca);
	/**
	 * Get accessor for persistent attribute: usua_fecha_alta
	 */
	public java.sql.Date getUsua_fecha_alta();
	/**
	 * Set accessor for persistent attribute: usua_fecha_alta
	 */
	public void setUsua_fecha_alta(java.sql.Date newUsua_fecha_alta);
	/**
	 * Get accessor for persistent attribute: esus_id
	 */
	public java.lang.Long getEsus_id();
	/**
	 * Set accessor for persistent attribute: esus_id
	 */
	public void setEsus_id(java.lang.Long newEsus_id);
	/**
	 * Get accessor for persistent attribute: esus_ultima_actualizacion
	 */
	public java.sql.Timestamp getEsus_ultima_actualizacion();
	/**
	 * Set accessor for persistent attribute: esus_ultima_actualizacion
	 */
	public void setEsus_ultima_actualizacion(
		java.sql.Timestamp newEsus_ultima_actualizacion);
	/**
	 * Get accessor for persistent attribute: usua_nombres
	 */
	public java.lang.String getUsua_nombres();
	/**
	 * Set accessor for persistent attribute: usua_nombres
	 */
	public void setUsua_nombres(java.lang.String newUsua_nombres);
	/**
	 * Get accessor for persistent attribute: usua_ape_paterno
	 */
	public java.lang.String getUsua_ape_paterno();
	/**
	 * Set accessor for persistent attribute: usua_ape_paterno
	 */
	public void setUsua_ape_paterno(java.lang.String newUsua_ape_paterno);
	/**
	 * Get accessor for persistent attribute: usua_ape_materno
	 */
	public java.lang.String getUsua_ape_materno();
	/**
	 * Set accessor for persistent attribute: usua_ape_materno
	 */
	public void setUsua_ape_materno(java.lang.String newUsua_ape_materno);
	/**
	 * Get accessor for persistent attribute: usua_num_rut
	 */
	public java.lang.Long getUsua_num_rut();
	/**
	 * Set accessor for persistent attribute: usua_num_rut
	 */
	public void setUsua_num_rut(java.lang.Long newUsua_num_rut);
	/**
	 * Get accessor for persistent attribute: usua_dv_rut
	 */
	public java.lang.String getUsua_dv_rut();
	/**
	 * Set accessor for persistent attribute: usua_dv_rut
	 */
	public void setUsua_dv_rut(java.lang.String newUsua_dv_rut);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getHabilidad_usuario();
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setHabilidad_usuario(java.util.Collection aHabilidad_usuario);
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCampo_usuario();
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCampo_usuario(java.util.Collection aCampo_usuario);
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getJer_usuario_rol();
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setJer_usuario_rol(java.util.Collection aJer_usuario_rol);
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getJer_usuario_rol_1();
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setJer_usuario_rol_1(java.util.Collection aJer_usuario_rol_1);
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPerfil_usuario();
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPerfil_usuario(java.util.Collection aPerfil_usuario);
	/**
	 * This method was generated for supporting the relationship role named usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getUsuario_rol();
	/**
	 * This method was generated for supporting the relationship role named usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUsuario_rol(java.util.Collection anUsuario_rol);
	/**
	 * This method was generated for supporting the relationship role named usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getUsuario_rol_1();
	/**
	 * This method was generated for supporting the relationship role named usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setUsuario_rol_1(java.util.Collection anUsuario_rol_1);
	/**
	 * Metodo para retornar los Campos Variables
	 */
	public Collection getCampos();
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.EmpresaLocal getEmpresa();
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setEmpresa(
		co.com.telefonica.atiempo.ejb.eb.EmpresaLocal anEmpresa);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCausal_peticion(java.util.Collection aCausal_peticion);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalidad(java.util.Collection aLocalidad);
}
