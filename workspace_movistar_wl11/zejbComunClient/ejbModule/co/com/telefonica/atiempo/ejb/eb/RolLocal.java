package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local interface for Enterprise Bean: Rol
 */
public interface RolLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_tipo_relacion
	 */
	public java.lang.Integer getId_tipo_relacion();
	/**
	 * Set accessor for persistent attribute: id_tipo_relacion
	 */
	public void setId_tipo_relacion(java.lang.Integer newId_tipo_relacion);
	/**
	 * Get accessor for persistent attribute: isp_id
	 */
	public java.lang.Long getIsp_id();
	/**
	 * Set accessor for persistent attribute: isp_id
	 */
	public void setIsp_id(java.lang.Long newIsp_id);
	/**
	 * Get accessor for persistent attribute: rol_nombre
	 */
	public java.lang.String getRol_nombre();
	/**
	 * Set accessor for persistent attribute: rol_nombre
	 */
	public void setRol_nombre(java.lang.String newRol_nombre);
	/**
	 * Get accessor for persistent attribute: rol_ve_sabana
	 */
	public java.lang.String getRol_ve_sabana();
	/**
	 * Set accessor for persistent attribute: rol_ve_sabana
	 */
	public void setRol_ve_sabana(java.lang.String newRol_ve_sabana);
	/**
	 * Get accessor for persistent attribute: rol_codigo
	 */
	public java.lang.String getRol_codigo();
	/**
	 * Set accessor for persistent attribute: rol_codigo
	 */
	public void setRol_codigo(java.lang.String newRol_codigo);
	/**
	 * Get accessor for persistent attribute: push_app_id
	 */
	public java.lang.Long getPush_app_id();
	/**
	 * Set accessor for persistent attribute: push_app_id
	 */
	public void setPush_app_id(java.lang.Long newPush_app_id);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setActividad(java.util.Collection anActividad);
	/**
	 * This method was generated for supporting the relationship role named fk_ap_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal getFk_ap_2_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_ap_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_ap_2_rol(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_ap_2_rol);
	/**
	 * This method was generated for supporting the relationship role named accion_masiva.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getAccion_masiva();
	/**
	 * This method was generated for supporting the relationship role named accion_masiva.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAccion_masiva(java.util.Collection anAccion_masiva);
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getRol_habilidad();
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRol_habilidad(java.util.Collection aRol_habilidad);
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getCampo_rol();
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCampo_rol(java.util.Collection aCampo_rol);
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
	 * 
	 */
	public Collection getCampovariableentity();
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getHorario();
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setHorario(java.util.Collection aHorario);
}
