package co.com.telefonica.atiempo.ejb.eb;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: Bintegrada
 */
public abstract class BintegradaBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	private static Logger logger=LoggerFactory.getLogger(BintegradaBean.class);
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
	public co.com.telefonica.atiempo.ejb.eb.BintegradaKey ejbCreate(
		java.lang.Long bi_id)
		throws javax.ejb.CreateException {
		setBi_id(bi_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long bi_id)
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
	public co.com.telefonica.atiempo.ejb.eb.BintegradaKey ejbCreate(
		java.lang.Long bi_id,
		java.lang.Long bi_nro_peticion,
		java.sql.Timestamp bi_fecha_compromiso,
//		CR17031 pcawen
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
		throws javax.ejb.CreateException {
//		logger.debug("Datos Publicacion: ID:" + bi_id + ",PET:" + bi_nro_peticion + ",FAP:" + new Fecha().getTimestamp() + ",FCO:" + bi_fecha_compromiso + ",CNOM:" + bi_cliente_nombre + ",CAP:" +bi_cliente_apellidos + ",FPS:" + bi_familia_ps + ",URL:" + bi_url_detalle + ",PUN:" + bi_puntaje);
		setBi_id(bi_id);
		setBi_nro_peticion(bi_nro_peticion);
		setBi_fecha_apertura(new Fecha().getTimestamp());
		setBi_fecha_compromiso(bi_fecha_compromiso);
		//CR17031 pcawen
		setBi_fecha_compromiso_sec(bi_fecha_compromiso_sec);		
		setBi_cod_cat(bi_cod_cat);
		//CR17031 pcawen
		setBi_cliente_nombre(bi_cliente_nombre);
		setBi_cliente_apellidos(bi_cliente_apellidos);
		setBi_familia_ps(bi_familia_ps);
		setBi_url_detalle(bi_url_detalle);
		setBi_puntaje(bi_puntaje);
		setBi_visible(new Integer(1));
		if(argFk_bintegrada_ap!=null && argFk_bi_usuario!=null)
		{
			AplicacionKey aplicacionKey=(AplicacionKey) argFk_bintegrada_ap.getPrimaryKey();
			logger.debug("AP:"+aplicacionKey.ap_id+" "+argFk_bintegrada_ap.getAp_nombre()+"Pet:"+bi_nro_peticion+" USUA:"+argFk_bi_usuario.getUsua_login()+ " Act:" + argFk_bi_act.getAct_codigo());
		}
		else
			logger.error("Faltan datos necesario para la publicacion en la BIntegrada");
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long bi_id,
		java.lang.Long bi_nro_peticion,
		java.sql.Timestamp bi_fecha_compromiso,
//		CR17031 pcawen
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
		throws javax.ejb.CreateException {
		setFk_bintegrada_ap(argFk_bintegrada_ap);
		setFk_bi_act(argFk_bi_act);
		setFk_bi_usuario(argFk_bi_usuario);
	}
	/**
	 * Get accessor for persistent attribute: bi_id
	 */
	public abstract java.lang.Long getBi_id();
	/**
	 * Set accessor for persistent attribute: bi_id
	 */
	public abstract void setBi_id(java.lang.Long newBi_id);
	/**
	 * Get accessor for persistent attribute: bi_nro_peticion
	 */
	public abstract java.lang.Long getBi_nro_peticion();
	/**
	 * Set accessor for persistent attribute: bi_nro_peticion
	 */
	public abstract void setBi_nro_peticion(java.lang.Long newBi_nro_peticion);
	/**
	 * Get accessor for persistent attribute: bi_fecha_compromiso
	 */
	public abstract java.sql.Timestamp getBi_fecha_compromiso();
	/**
	 * Set accessor for persistent attribute: bi_fecha_compromiso
	 */
	public abstract void setBi_fecha_compromiso(
		java.sql.Timestamp newBi_fecha_compromiso);
	/**
	 * Get accessor for persistent attribute: bi_cliente_nombre
	 */
	public abstract java.lang.String getBi_cliente_nombre();
	/**
	 * Set accessor for persistent attribute: bi_cliente_nombre
	 */
	public abstract void setBi_cliente_nombre(
		java.lang.String newBi_cliente_nombre);
	/**
	 * Get accessor for persistent attribute: bi_cliente_apellidos
	 */
	public abstract java.lang.String getBi_cliente_apellidos();
	/**
	 * Set accessor for persistent attribute: bi_cliente_apellidos
	 */
	public abstract void setBi_cliente_apellidos(
		java.lang.String newBi_cliente_apellidos);
	/**
	 * Get accessor for persistent attribute: bi_cliente_servicio
	 */
	public abstract java.lang.String getBi_cliente_servicio();
	/**
	 * Set accessor for persistent attribute: bi_cliente_servicio
	 */
	public abstract void setBi_cliente_servicio(
		java.lang.String newBi_cliente_servicio);
	/**
	 * Get accessor for persistent attribute: bi_cliente_area
	 */
	public abstract java.lang.String getBi_cliente_area();
	/**
	 * Set accessor for persistent attribute: bi_cliente_area
	 */
	public abstract void setBi_cliente_area(
		java.lang.String newBi_cliente_area);
	/**
	 * Get accessor for persistent attribute: bi_cliente_rut
	 */
	public abstract java.lang.String getBi_cliente_rut();
	/**
	 * Set accessor for persistent attribute: bi_cliente_rut
	 */
	public abstract void setBi_cliente_rut(java.lang.String newBi_cliente_rut);
	/**
	 * Get accessor for persistent attribute: bi_cliente_rutdv
	 */
	public abstract java.lang.String getBi_cliente_rutdv();
	/**
	 * Set accessor for persistent attribute: bi_cliente_rutdv
	 */
	public abstract void setBi_cliente_rutdv(
		java.lang.String newBi_cliente_rutdv);
	/**
	 * Get accessor for persistent attribute: bi_familia_ps
	 */
	public abstract java.lang.String getBi_familia_ps();
	/**
	 * Set accessor for persistent attribute: bi_familia_ps
	 */
	public abstract void setBi_familia_ps(java.lang.String newBi_familia_ps);
	/**
	 * Get accessor for persistent attribute: bi_url_detalle
	 */
	public abstract java.lang.String getBi_url_detalle();
	/**
	 * Set accessor for persistent attribute: bi_url_detalle
	 */
	public abstract void setBi_url_detalle(java.lang.String newBi_url_detalle);
	/**
	 * Get accessor for persistent attribute: bi_puntaje
	 */
	public abstract java.lang.Integer getBi_puntaje();
	/**
	 * Set accessor for persistent attribute: bi_puntaje
	 */
	public abstract void setBi_puntaje(java.lang.Integer newBi_puntaje);
	/**
	 * Get accessor for persistent attribute: bi_fecha_inicio
	 */
	public abstract java.sql.Timestamp getBi_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: bi_fecha_inicio
	 */
	public abstract void setBi_fecha_inicio(
		java.sql.Timestamp newBi_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: bi_fecha_apertura
	 */
	public abstract java.sql.Timestamp getBi_fecha_apertura();
	/**
	 * Set accessor for persistent attribute: bi_fecha_apertura
	 */
	public abstract void setBi_fecha_apertura(
		java.sql.Timestamp newBi_fecha_apertura);
	/**
	 * Get accessor for persistent attribute: bi_fecha_asignacion
	 */
	public abstract java.sql.Timestamp getBi_fecha_asignacion();
	/**
	 * Set accessor for persistent attribute: bi_fecha_asignacion
	 */
	public abstract void setBi_fecha_asignacion(
		java.sql.Timestamp newBi_fecha_asignacion);
	/**
	 * Get accessor for persistent attribute: bi_estado_peticion
	 */
	public abstract java.lang.Integer getBi_estado_peticion();
	/**
	 * Set accessor for persistent attribute: bi_estado_peticion
	 */
	public abstract void setBi_estado_peticion(
		java.lang.Integer newBi_estado_peticion);
	/**
	 * Get accessor for persistent attribute: bi_tipo_trabajo
	 */
	public abstract java.lang.Integer getBi_tipo_trabajo();
	/**
	 * Set accessor for persistent attribute: bi_tipo_trabajo
	 */
	public abstract void setBi_tipo_trabajo(
		java.lang.Integer newBi_tipo_trabajo);
	/**
	 * Get accessor for persistent attribute: bi_visible
	 */
	public abstract java.lang.Integer getBi_visible();
	/**
	 * Set accessor for persistent attribute: bi_visible
	 */
	public abstract void setBi_visible(java.lang.Integer newBi_visible);
	/**
	 * Get accessor for persistent attribute: bi_grupo_segmento
	 */
	public abstract java.lang.Long getBi_grupo_segmento();
	/**
	 * Set accessor for persistent attribute: bi_grupo_segmento
	 */
	public abstract void setBi_grupo_segmento(
		java.lang.Long newBi_grupo_segmento);
	/**
	 * Get accessor for persistent attribute: bi_id_tipo_agendamiento
	 */
	public abstract java.lang.Long getBi_id_tipo_agendamiento();
	/**
	 * Set accessor for persistent attribute: bi_id_tipo_agendamiento
	 */
	public abstract void setBi_id_tipo_agendamiento(
		java.lang.Long newBi_id_tipo_agendamiento);
	/**
	 * Get accessor for persistent attribute: bi_hora_desde
	 */
	public abstract java.lang.String getBi_hora_desde();
	/**
	 * Set accessor for persistent attribute: bi_hora_desde
	 */
	public abstract void setBi_hora_desde(java.lang.String newBi_hora_desde);
	/**
	 * Get accessor for persistent attribute: bi_hora_hasta
	 */
	public abstract java.lang.String getBi_hora_hasta();
	/**
	 * Set accessor for persistent attribute: bi_hora_hasta
	 */
	public abstract void setBi_hora_hasta(java.lang.String newBi_hora_hasta);
	/**
	 * Get accessor for persistent attribute: bi_id_rango
	 */
	public abstract java.lang.Integer getBi_id_rango();
	/**
	 * Set accessor for persistent attribute: bi_id_rango
	 */
	public abstract void setBi_id_rango(java.lang.Integer newBi_id_rango);
	/**
	 * Get accessor for persistent attribute: rol_id
	 */
	public abstract java.lang.Long getRol_id();
	/**
	 * Set accessor for persistent attribute: rol_id
	 */
	public abstract void setRol_id(java.lang.Long newRol_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public abstract java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public abstract void setTica_id(java.lang.String newTica_id);
	/**
	 * This method was generated for supporting the relationship role named fk_bi_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ActividadLocal getFk_bi_act();
	/**
	 * This method was generated for supporting the relationship role named fk_bi_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_bi_act(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal aFk_bi_act);
	/**
	 * This method was generated for supporting the relationship role named fk_bintegrada_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.AplicacionLocal getFk_bintegrada_ap();
	/**
	 * This method was generated for supporting the relationship role named fk_bintegrada_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_bintegrada_ap(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_bintegrada_ap);
	/**
	 * This method was generated for supporting the relationship role named fk_bi_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.UsuarioLocal getFk_bi_usuario();
	/**
	 * This method was generated for supporting the relationship role named fk_bi_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_bi_usuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_bi_usuario);
	/**
	 * This method was generated for supporting the relationship role named fk_bint_segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.SegmentoLocal getFk_bint_segmento();
	/**
	 * This method was generated for supporting the relationship role named fk_bint_segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_bint_segmento(
		co.com.telefonica.atiempo.ejb.eb.SegmentoLocal aFk_bint_segmento);
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getValor_variable();
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setValor_variable(
		java.util.Collection aValor_variable);
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.DepartamentoLocal getDepartamento();
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setDepartamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aDepartamento);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.LocalidadLocal getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setLocalidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aLocalidad);
	/**
	 * This method was generated for supporting the relationship role named central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.CentralLocal getCentral();
	/**
	 * This method was generated for supporting the relationship role named central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCentral(
		co.com.telefonica.atiempo.ejb.eb.CentralLocal aCentral);
	/**
	 * Get accessor for persistent attribute: bi_nro_peticion_atis
	 */
	public abstract java.lang.Long getBi_nro_peticion_atis();
	/**
	 * Set accessor for persistent attribute: bi_nro_peticion_atis
	 */
	public abstract void setBi_nro_peticion_atis(
		java.lang.Long newBi_nro_peticion_atis);
	/**
	 * Get accessor for persistent attribute: bi_agrupaciones
	 */
	public abstract java.lang.String getBi_agrupaciones();
	/**
	 * Set accessor for persistent attribute: bi_agrupaciones
	 */
	public abstract void setBi_agrupaciones(
		java.lang.String newBi_agrupaciones);
	/**
	 * Get accessor for persistent attribute: desc_localidad
	 */
	public abstract java.lang.String getDesc_localidad();
	/**
	 * Set accessor for persistent attribute: desc_localidad
	 */
	public abstract void setDesc_localidad(java.lang.String newDesc_localidad);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public abstract java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public abstract void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: segm_descripcion
	 */
	public abstract java.lang.String getSegm_descripcion();
	/**
	 * Set accessor for persistent attribute: segm_descripcion
	 */
	public abstract void setSegm_descripcion(
		java.lang.String newSegm_descripcion);
	/**
	 * Get accessor for persistent attribute: subsegm_descripcion
	 */
	public abstract java.lang.String getSubsegm_descripcion();
	/**
	 * Set accessor for persistent attribute: subsegm_descripcion
	 */
	public abstract void setSubsegm_descripcion(
		java.lang.String newSubsegm_descripcion);
	/**
	 * Get accessor for persistent attribute: bi_fecha_compromiso_sec
	 */
	public abstract java.sql.Timestamp getBi_fecha_compromiso_sec();
	/**
	 * Set accessor for persistent attribute: bi_fecha_compromiso_sec
	 */
	public abstract void setBi_fecha_compromiso_sec(
		java.sql.Timestamp newBi_fecha_compromiso_sec);
	/**
	 * Get accessor for persistent attribute: bi_cod_cat
	 */
	public abstract java.lang.String getBi_cod_cat();
	/**
	 * Set accessor for persistent attribute: bi_cod_cat
	 */
	public abstract void setBi_cod_cat(java.lang.String newBi_cod_cat);
}
