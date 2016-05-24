package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Bintegrada
 */
public interface BintegradaLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: bi_nro_peticion
	 */
	public java.lang.Long getBi_nro_peticion();
	/**
	 * Set accessor for persistent attribute: bi_nro_peticion
	 */
	public void setBi_nro_peticion(java.lang.Long newBi_nro_peticion);
	/**
	 * Get accessor for persistent attribute: bi_fecha_compromiso
	 */
	public java.sql.Timestamp getBi_fecha_compromiso();
	/**
	 * Set accessor for persistent attribute: bi_fecha_compromiso
	 */
	public void setBi_fecha_compromiso(
		java.sql.Timestamp newBi_fecha_compromiso);
	/**
	 * Get accessor for persistent attribute: bi_cliente_nombre
	 */
	public java.lang.String getBi_cliente_nombre();
	/**
	 * Set accessor for persistent attribute: bi_cliente_nombre
	 */
	public void setBi_cliente_nombre(java.lang.String newBi_cliente_nombre);
	/**
	 * Get accessor for persistent attribute: bi_cliente_apellidos
	 */
	public java.lang.String getBi_cliente_apellidos();
	/**
	 * Set accessor for persistent attribute: bi_cliente_apellidos
	 */
	public void setBi_cliente_apellidos(
		java.lang.String newBi_cliente_apellidos);
	/**
	 * Get accessor for persistent attribute: bi_cliente_servicio
	 */
	public java.lang.String getBi_cliente_servicio();
	/**
	 * Set accessor for persistent attribute: bi_cliente_servicio
	 */
	public void setBi_cliente_servicio(java.lang.String newBi_cliente_servicio);
	/**
	 * Get accessor for persistent attribute: bi_cliente_area
	 */
	public java.lang.String getBi_cliente_area();
	/**
	 * Set accessor for persistent attribute: bi_cliente_area
	 */
	public void setBi_cliente_area(java.lang.String newBi_cliente_area);
	/**
	 * Get accessor for persistent attribute: bi_cliente_rut
	 */
	public java.lang.String getBi_cliente_rut();
	/**
	 * Set accessor for persistent attribute: bi_cliente_rut
	 */
	public void setBi_cliente_rut(java.lang.String newBi_cliente_rut);
	/**
	 * Get accessor for persistent attribute: bi_cliente_rutdv
	 */
	public java.lang.String getBi_cliente_rutdv();
	/**
	 * Set accessor for persistent attribute: bi_cliente_rutdv
	 */
	public void setBi_cliente_rutdv(java.lang.String newBi_cliente_rutdv);
	/**
	 * Get accessor for persistent attribute: bi_familia_ps
	 */
	public java.lang.String getBi_familia_ps();
	/**
	 * Set accessor for persistent attribute: bi_familia_ps
	 */
	public void setBi_familia_ps(java.lang.String newBi_familia_ps);
	/**
	 * Get accessor for persistent attribute: bi_url_detalle
	 */
	public java.lang.String getBi_url_detalle();
	/**
	 * Set accessor for persistent attribute: bi_url_detalle
	 */
	public void setBi_url_detalle(java.lang.String newBi_url_detalle);
	/**
	 * Get accessor for persistent attribute: bi_puntaje
	 */
	public java.lang.Integer getBi_puntaje();
	/**
	 * Set accessor for persistent attribute: bi_puntaje
	 */
	public void setBi_puntaje(java.lang.Integer newBi_puntaje);
	/**
	 * Get accessor for persistent attribute: bi_fecha_inicio
	 */
	public java.sql.Timestamp getBi_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: bi_fecha_inicio
	 */
	public void setBi_fecha_inicio(java.sql.Timestamp newBi_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: bi_fecha_apertura
	 */
	public java.sql.Timestamp getBi_fecha_apertura();
	/**
	 * Set accessor for persistent attribute: bi_fecha_apertura
	 */
	public void setBi_fecha_apertura(java.sql.Timestamp newBi_fecha_apertura);
	/**
	 * Get accessor for persistent attribute: bi_fecha_asignacion
	 */
	public java.sql.Timestamp getBi_fecha_asignacion();
	/**
	 * Set accessor for persistent attribute: bi_fecha_asignacion
	 */
	public void setBi_fecha_asignacion(
		java.sql.Timestamp newBi_fecha_asignacion);
	/**
	 * Get accessor for persistent attribute: bi_estado_peticion
	 */
	public java.lang.Integer getBi_estado_peticion();
	/**
	 * Set accessor for persistent attribute: bi_estado_peticion
	 */
	public void setBi_estado_peticion(java.lang.Integer newBi_estado_peticion);
	/**
	 * Get accessor for persistent attribute: bi_tipo_trabajo
	 */
	public java.lang.Integer getBi_tipo_trabajo();
	/**
	 * Set accessor for persistent attribute: bi_tipo_trabajo
	 */
	public void setBi_tipo_trabajo(java.lang.Integer newBi_tipo_trabajo);
	/**
	 * Get accessor for persistent attribute: bi_visible
	 */
	public java.lang.Integer getBi_visible();
	/**
	 * Set accessor for persistent attribute: bi_visible
	 */
	public void setBi_visible(java.lang.Integer newBi_visible);
	/**
	 * Get accessor for persistent attribute: bi_grupo_segmento
	 */
	public java.lang.Long getBi_grupo_segmento();
	/**
	 * Set accessor for persistent attribute: bi_grupo_segmento
	 */
	public void setBi_grupo_segmento(java.lang.Long newBi_grupo_segmento);
	/**
	 * Get accessor for persistent attribute: bi_id_tipo_agendamiento
	 */
	public java.lang.Long getBi_id_tipo_agendamiento();
	/**
	 * Set accessor for persistent attribute: bi_id_tipo_agendamiento
	 */
	public void setBi_id_tipo_agendamiento(
		java.lang.Long newBi_id_tipo_agendamiento);
	/**
	 * Get accessor for persistent attribute: bi_hora_desde
	 */
	public java.lang.String getBi_hora_desde();
	/**
	 * Set accessor for persistent attribute: bi_hora_desde
	 */
	public void setBi_hora_desde(java.lang.String newBi_hora_desde);
	/**
	 * Get accessor for persistent attribute: bi_hora_hasta
	 */
	public java.lang.String getBi_hora_hasta();
	/**
	 * Set accessor for persistent attribute: bi_hora_hasta
	 */
	public void setBi_hora_hasta(java.lang.String newBi_hora_hasta);
	/**
	 * Get accessor for persistent attribute: bi_id_rango
	 */
	public java.lang.Integer getBi_id_rango();
	/**
	 * Set accessor for persistent attribute: bi_id_rango
	 */
	public void setBi_id_rango(java.lang.Integer newBi_id_rango);
	/**
	 * Get accessor for persistent attribute: rol_id
	 */
	public java.lang.Long getRol_id();
	/**
	 * Set accessor for persistent attribute: rol_id
	 */
	public void setRol_id(java.lang.Long newRol_id);
	/**
	 * Get accessor for persistent attribute: tica_id
	 */
	public java.lang.String getTica_id();
	/**
	 * Set accessor for persistent attribute: tica_id
	 */
	public void setTica_id(java.lang.String newTica_id);
	/**
	 * This method was generated for supporting the relationship role named fk_bi_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.ActividadLocal getFk_bi_act();
	/**
	 * This method was generated for supporting the relationship role named fk_bi_act.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_bi_act(
		co.com.telefonica.atiempo.ejb.eb.ActividadLocal aFk_bi_act);
	/**
	 * This method was generated for supporting the relationship role named fk_bintegrada_ap.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
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
	public void setFk_bintegrada_ap(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal aFk_bintegrada_ap);
	/**
	 * This method was generated for supporting the relationship role named fk_bi_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.UsuarioLocal getFk_bi_usuario();
	/**
	 * This method was generated for supporting the relationship role named fk_bi_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_bi_usuario(
		co.com.telefonica.atiempo.ejb.eb.UsuarioLocal aFk_bi_usuario);
	/**
	 * This method was generated for supporting the relationship role named fk_bint_segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.SegmentoLocal getFk_bint_segmento();
	/**
	 * This method was generated for supporting the relationship role named fk_bint_segmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_bint_segmento(
		co.com.telefonica.atiempo.ejb.eb.SegmentoLocal aFk_bint_segmento);
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getValor_variable();
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setValor_variable(java.util.Collection aValor_variable);
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal getDepartamento();
	/**
	 * This method was generated for supporting the relationship role named departamento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setDepartamento(
		co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal aDepartamento);
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.LocalidadLocal getLocalidad();
	/**
	 * This method was generated for supporting the relationship role named localidad.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setLocalidad(
		co.com.telefonica.atiempo.ejb.eb.LocalidadLocal aLocalidad);
	/**
	 * This method was generated for supporting the relationship role named central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.CentralLocal getCentral();
	/**
	 * This method was generated for supporting the relationship role named central.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCentral(
		co.com.telefonica.atiempo.ejb.eb.CentralLocal aCentral);
	/**
	 * Get accessor for persistent attribute: bi_nro_peticion_atis
	 */
	public java.lang.Long getBi_nro_peticion_atis();
	/**
	 * Set accessor for persistent attribute: bi_nro_peticion_atis
	 */
	public void setBi_nro_peticion_atis(java.lang.Long newBi_nro_peticion_atis);
	/**
	 * Get accessor for persistent attribute: bi_agrupaciones
	 */
	public java.lang.String getBi_agrupaciones();
	/**
	 * Set accessor for persistent attribute: bi_agrupaciones
	 */
	public void setBi_agrupaciones(java.lang.String newBi_agrupaciones);
	/**
	 * Get accessor for persistent attribute: desc_localidad
	 */
	public java.lang.String getDesc_localidad();
	/**
	 * Set accessor for persistent attribute: desc_localidad
	 */
	public void setDesc_localidad(java.lang.String newDesc_localidad);
	/**
	 * Get accessor for persistent attribute: desc_central
	 */
	public java.lang.String getDesc_central();
	/**
	 * Set accessor for persistent attribute: desc_central
	 */
	public void setDesc_central(java.lang.String newDesc_central);
	/**
	 * Get accessor for persistent attribute: segm_descripcion
	 */
	public java.lang.String getSegm_descripcion();
	/**
	 * Set accessor for persistent attribute: segm_descripcion
	 */
	public void setSegm_descripcion(java.lang.String newSegm_descripcion);
	/**
	 * Get accessor for persistent attribute: subsegm_descripcion
	 */
	public java.lang.String getSubsegm_descripcion();
	/**
	 * Set accessor for persistent attribute: subsegm_descripcion
	 */
	public void setSubsegm_descripcion(java.lang.String newSubsegm_descripcion);
	/**
	 * Get accessor for persistent attribute: bi_fecha_compromiso_sec
	 */
	public java.sql.Timestamp getBi_fecha_compromiso_sec();
	/**
	 * Set accessor for persistent attribute: bi_fecha_compromiso_sec
	 */
	public void setBi_fecha_compromiso_sec(
		java.sql.Timestamp newBi_fecha_compromiso_sec);
	/**
	 * Get accessor for persistent attribute: bi_cod_cat
	 */
	public java.lang.String getBi_cod_cat();
	/**
	 * Set accessor for persistent attribute: bi_cod_cat
	 */
	public void setBi_cod_cat(java.lang.String newBi_cod_cat);
}
