package co.com.telefonica.atiempo.ejb.eb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.tecnonautica.utiles.db.DBManager;

/**
 * Bean implementation class for Enterprise Bean: Usuario
 */
public abstract class UsuarioBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.UsuarioKey ejbCreate(
		java.lang.Long usua_id) throws javax.ejb.CreateException {
		setUsua_id(usua_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long usua_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public UsuarioKey ejbCreate(String usua_login,String usua_nombre) throws javax.ejb.CreateException
	{
		DBManager manager=new DBManager();
		manager.setDataSource(DBManager.JDBC_VPISTBBA);
		Long usua_id=new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_USUARIO"));
		setUsua_id(usua_id);
		setUsua_login(usua_login);
		setUsua_nombre(usua_nombre);
		return null;
	}
	
	public void ejbPostCreate(String usua_login,String usua_nombre) throws javax.ejb.CreateException
	{
		
	}
		
	public co.com.telefonica.atiempo.ejb.eb.UsuarioKey ejbCreate(
		java.lang.Long usua_id,
		java.lang.String usua_login,
		java.lang.String usua_nombre)
		throws javax.ejb.CreateException {
		setUsua_id(usua_id);
		setUsua_login(usua_login);
		setUsua_nombre(usua_nombre);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long usua_id,
		java.lang.String usua_login,
		java.lang.String usua_nombre)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: usua_login
	 */
	public abstract java.lang.String getUsua_login();
	/**
	 * Set accessor for persistent attribute: usua_login
	 */
	public abstract void setUsua_login(java.lang.String newUsua_login);
	/**
	 * Get accessor for persistent attribute: usua_nombre
	 */
	public abstract java.lang.String getUsua_nombre();
	/**
	 * Set accessor for persistent attribute: usua_nombre
	 */
	public abstract void setUsua_nombre(java.lang.String newUsua_nombre);
	/**
	 * Get accessor for persistent attribute: usua_direccion
	 */
	public abstract java.lang.String getUsua_direccion();
	/**
	 * Set accessor for persistent attribute: usua_direccion
	 */
	public abstract void setUsua_direccion(java.lang.String newUsua_direccion);
	/**
	 * Get accessor for persistent attribute: usua_email
	 */
	public abstract java.lang.String getUsua_email();
	/**
	 * Set accessor for persistent attribute: usua_email
	 */
	public abstract void setUsua_email(java.lang.String newUsua_email);
	/**
	 * Get accessor for persistent attribute: usua_telefono
	 */
	public abstract java.lang.String getUsua_telefono();
	/**
	 * Set accessor for persistent attribute: usua_telefono
	 */
	public abstract void setUsua_telefono(java.lang.String newUsua_telefono);
	/**
	 * Get accessor for persistent attribute: usua_rut
	 */
	public abstract java.lang.String getUsua_rut();
	/**
	 * Set accessor for persistent attribute: usua_rut
	 */
	public abstract void setUsua_rut(java.lang.String newUsua_rut);
	/**
	 * Get accessor for persistent attribute: usua_cargo
	 */
	public abstract java.lang.String getUsua_cargo();
	/**
	 * Set accessor for persistent attribute: usua_cargo
	 */
	public abstract void setUsua_cargo(java.lang.String newUsua_cargo);
	/**
	 * Get accessor for persistent attribute: usua_habilitado
	 */
	public abstract java.lang.String getUsua_habilitado();
	/**
	 * Set accessor for persistent attribute: usua_habilitado
	 */
	public abstract void setUsua_habilitado(
		java.lang.String newUsua_habilitado);
	/**
	 * Get accessor for persistent attribute: usua_password
	 */
	public abstract java.lang.String getUsua_password();
	/**
	 * Set accessor for persistent attribute: usua_password
	 */
	public abstract void setUsua_password(java.lang.String newUsua_password);
	/**
	 * Get accessor for persistent attribute: usua_idca
	 */
	public abstract java.lang.String getUsua_idca();
	/**
	 * Set accessor for persistent attribute: usua_idca
	 */
	public abstract void setUsua_idca(java.lang.String newUsua_idca);
	/**
	 * Get accessor for persistent attribute: usua_fecha_alta
	 */
	public abstract java.sql.Date getUsua_fecha_alta();
	/**
	 * Set accessor for persistent attribute: usua_fecha_alta
	 */
	public abstract void setUsua_fecha_alta(java.sql.Date newUsua_fecha_alta);
	/**
	 * Get accessor for persistent attribute: esus_id
	 */
	public abstract java.lang.Long getEsus_id();
	/**
	 * Set accessor for persistent attribute: esus_id
	 */
	public abstract void setEsus_id(java.lang.Long newEsus_id);
	/**
	 * Get accessor for persistent attribute: esus_ultima_actualizacion
	 */
	public abstract java.sql.Timestamp getEsus_ultima_actualizacion();
	/**
	 * Set accessor for persistent attribute: esus_ultima_actualizacion
	 */
	public abstract void setEsus_ultima_actualizacion(
		java.sql.Timestamp newEsus_ultima_actualizacion);
	/**
	 * Get accessor for persistent attribute: usua_nombres
	 */
	public abstract java.lang.String getUsua_nombres();
	/**
	 * Set accessor for persistent attribute: usua_nombres
	 */
	public abstract void setUsua_nombres(java.lang.String newUsua_nombres);
	/**
	 * Get accessor for persistent attribute: usua_ape_paterno
	 */
	public abstract java.lang.String getUsua_ape_paterno();
	/**
	 * Set accessor for persistent attribute: usua_ape_paterno
	 */
	public abstract void setUsua_ape_paterno(
		java.lang.String newUsua_ape_paterno);
	/**
	 * Get accessor for persistent attribute: usua_ape_materno
	 */
	public abstract java.lang.String getUsua_ape_materno();
	/**
	 * Set accessor for persistent attribute: usua_ape_materno
	 */
	public abstract void setUsua_ape_materno(
		java.lang.String newUsua_ape_materno);
	/**
	 * Get accessor for persistent attribute: usua_num_rut
	 */
	public abstract java.lang.Long getUsua_num_rut();
	/**
	 * Set accessor for persistent attribute: usua_num_rut
	 */
	public abstract void setUsua_num_rut(java.lang.Long newUsua_num_rut);
	/**
	 * Get accessor for persistent attribute: usua_dv_rut
	 */
	public abstract java.lang.String getUsua_dv_rut();
	/**
	 * Set accessor for persistent attribute: usua_dv_rut
	 */
	public abstract void setUsua_dv_rut(java.lang.String newUsua_dv_rut);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getHabilidad_usuario();
	/**
	 * This method was generated for supporting the relationship role named habilidad_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setHabilidad_usuario(
		java.util.Collection aHabilidad_usuario);
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCampo_usuario();
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCampo_usuario(java.util.Collection aCampo_usuario);
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
	 * This method was generated for supporting the relationship role named jer_usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getJer_usuario_rol_1();
	/**
	 * This method was generated for supporting the relationship role named jer_usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setJer_usuario_rol_1(
		java.util.Collection aJer_usuario_rol_1);
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getPerfil_usuario();
	/**
	 * This method was generated for supporting the relationship role named perfil_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPerfil_usuario(
		java.util.Collection aPerfil_usuario);
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
	/**
	 * This method was generated for supporting the relationship role named usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getUsuario_rol_1();
	/**
	 * This method was generated for supporting the relationship role named usuario_rol_1.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setUsuario_rol_1(java.util.Collection anUsuario_rol_1);
	
	public Collection getCampos()
	{
		ArrayList arrayList=new ArrayList();
		for(Iterator iterator=this.getCampo_usuario().iterator();iterator.hasNext();)
		{
			Campo_usuarioLocal campo_usuarioLocal=(Campo_usuarioLocal) iterator.next();
			arrayList.add(campo_usuarioLocal.getFk_camusu_camvar());
		}
		return arrayList;	
	}
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.EmpresaLocal getEmpresa();
	/**
	 * This method was generated for supporting the relationship role named empresa.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setEmpresa(
		co.com.telefonica.atiempo.ejb.eb.EmpresaLocal anEmpresa);
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCausal_peticion();
	/**
	 * This method was generated for supporting the relationship role named causal_peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausal_peticion(
		java.util.Collection aCausal_peticion);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalidad(java.util.Collection aLocalidad);
}
