package co.com.telefonica.atiempo.ejb.eb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Bean implementation class for Enterprise Bean: Rol
 */
public abstract class RolBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.RolKey ejbCreate(
		java.lang.Long rol_id) throws javax.ejb.CreateException {
		setRol_id(rol_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long rol_id)
		throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: rol_id
	 */
	public abstract java.lang.Long getRol_id();
	/**
	 * Set accessor for persistent attribute: rol_id
	 */
	public abstract void setRol_id(java.lang.Long newRol_id);
	/**
	 * Get accessor for persistent attribute: id_tipo_relacion
	 */
	public abstract java.lang.Integer getId_tipo_relacion();
	/**
	 * Set accessor for persistent attribute: id_tipo_relacion
	 */
	public abstract void setId_tipo_relacion(
		java.lang.Integer newId_tipo_relacion);
	/**
	 * Get accessor for persistent attribute: isp_id
	 */
	public abstract java.lang.Long getIsp_id();
	/**
	 * Set accessor for persistent attribute: isp_id
	 */
	public abstract void setIsp_id(java.lang.Long newIsp_id);
	/**
	 * Get accessor for persistent attribute: rol_nombre
	 */
	public abstract java.lang.String getRol_nombre();
	/**
	 * Set accessor for persistent attribute: rol_nombre
	 */
	public abstract void setRol_nombre(java.lang.String newRol_nombre);
	/**
	 * Get accessor for persistent attribute: rol_ve_sabana
	 */
	public abstract java.lang.String getRol_ve_sabana();
	/**
	 * Set accessor for persistent attribute: rol_ve_sabana
	 */
	public abstract void setRol_ve_sabana(java.lang.String newRol_ve_sabana);
	/**
	 * Get accessor for persistent attribute: rol_codigo
	 */
	public abstract java.lang.String getRol_codigo();
	/**
	 * Set accessor for persistent attribute: rol_codigo
	 */
	public abstract void setRol_codigo(java.lang.String newRol_codigo);
	/**
	 * Get accessor for persistent attribute: push_app_id
	 */
	public abstract java.lang.Long getPush_app_id();
	/**
	 * Set accessor for persistent attribute: push_app_id
	 */
	public abstract void setPush_app_id(java.lang.Long newPush_app_id);
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getActividad();
	/**
	 * This method was generated for supporting the relationship role named actividad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad(java.util.Collection anActividad);
	/**
	 * This method was generated for supporting the relationship role named fk_ap_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.AplicacionLocal getFk_ap_2_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_ap_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_ap_2_rol(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_ap_2_rol);
	/**
	 * This method was generated for supporting the relationship role named accion_masiva.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getAccion_masiva();
	/**
	 * This method was generated for supporting the relationship role named accion_masiva.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setAccion_masiva(java.util.Collection anAccion_masiva);
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRol_habilidad();
	/**
	 * This method was generated for supporting the relationship role named rol_habilidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRol_habilidad(java.util.Collection aRol_habilidad);
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCampo_rol();
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCampo_rol(java.util.Collection aCampo_rol);
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getJer_usuario_rol();
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setJer_usuario_rol(
		java.util.Collection aJer_usuario_rol);
	/**
	 * This method was generated for supporting the relationship role named usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getUsuario_rol();
	/**
	 * This method was generated for supporting the relationship role named usuario_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUsuario_rol(java.util.Collection anUsuario_rol);
	
	public Collection getCampovariableentity()
	{
		ArrayList arrayList=new ArrayList();
		for(Iterator iterator=getCampo_rol().iterator();iterator.hasNext();)
		{
			Campo_rolLocal campo_rolLocal=(Campo_rolLocal) iterator.next();
			arrayList.add(campo_rolLocal.getFk_camrol_2_cam());
		}
		return arrayList;
	}
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getHorario();
	/**
	 * This method was generated for supporting the relationship role named horario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setHorario(java.util.Collection aHorario);
}
