package com.telefonica_chile.bandeja.helpers;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.SegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoKey;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocal;
import co.com.telefonica.atiempo.ejb.eb.SubsegmentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.servicios.publicador.DatosPeticion;
import com.telefonica_chile.comun.ComunInterfaces;

public class BandejaFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private BintegradaLocalHome homeBandeja;
	private LocalidadLocalHome localidadHome;
	private DepartamentoLocalHome departamentoHome;
	private CentralLocalHome centralHome;
	private PeticionLocalHome peticionHome;
	
	public BintegradaLocalHome getBandejaHome() throws FactoryException {
		if (homeBandeja != null)
			return homeBandeja;

		try {
			homeBandeja = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando home bandeja: " + BintegradaLocalHome.JNDI_NAME, e);
			throw new FactoryException("No se pudo recuperar peticiones", e);
		}
		return homeBandeja;
	}

	public void agregaPeticion(DatosPeticion dp) throws ParametrosIncorrectosException, FactoryException, RegistroDuplicadoException, NamingException, FinderException {
		AplicacionFactory aplicacionFactory = new AplicacionFactory();
		UsuarioFactory usuarioFactory = new UsuarioFactory();
		ActividadFactory actividadFactory = new ActividadFactory();

		AplicacionLocal aplicacionEntity = null;
		UsuarioLocal usuarioEntity = null;
		ActividadLocal actividadEntity = null;
		SegmentoLocal segmentoEntity = null;
		SubsegmentoLocal subSegmentoEntity=null;
		LocalidadLocal localidadLocal=null;
		DepartamentoLocal departamentoLocal=null;
		CentralLocal centralLocal=null;
		String agencia = null;
		if (dp.getCodigoAgencia() == null){
			agencia = "0000";
		}
		else{
			agencia = dp.getCodigoAgencia();
		}
		try {
			//Departamento, Localidad
			localidadHome=(LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			departamentoHome=(DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			localidadLocal=localidadHome.findByPrimaryKey(new LocalidadKey(dp.getCodigoLocalidad()));
			departamentoLocal=departamentoHome.findByPrimaryKey(new DepartamentoKey(dp.getCodigoDepartamento()));

			aplicacionEntity = aplicacionFactory.getAplicacionPorNombre(dp.getNombreAplicacion());
//			usuarioEntity = usuarioFactory.getUsuarioPorLogin(dp.getUsernameResponsable());
			//Cambio. El usuario se busca por ID para publicar y no por Login.
			usuarioEntity = usuarioFactory.getUsuarioPorId(dp.getIdResponsable());
			actividadEntity = actividadFactory.getActividadPorCodigoActividadCodigoAplicacion(dp.getCodigoActividad(),dp.getNombreAplicacion());
		} catch (RegistroNoEncontradoException e) {	
			throw new ParametrosIncorrectosException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();	
			throw new ParametrosIncorrectosException(e.getMessage());
		}	
		
		try
		{
//			TODO: El segmento puede ser nulo
			if (dp.getSegmentoCliente() != null)
			{
				//log.debug("Publicando Segmento" + dp.getSegmentoCliente());
				SegmentoFactory segmentoFactory = new SegmentoFactory();
				segmentoEntity = segmentoFactory.getSegmentoPorCodigo(dp.getSegmentoCliente());
			}
		}
		catch(RegistroNoEncontradoException e)
		{
			segmentoEntity=null;
		}
		//El SubSegmento puede ser nulo		
		try
		{
			if(dp.getSubSegmentoCliente()!=null)
			{
				//log.debug("Publicando SubSegmento"+dp.getSubSegmentoCliente());
				SubsegmentoLocalHome subsegmentoLocalHome=(SubsegmentoLocalHome) HomeFactory.getHome(SubsegmentoLocalHome.JNDI_NAME);
				subSegmentoEntity=subsegmentoLocalHome.findByPrimaryKey(new SubsegmentoKey(new Long(dp.getSubSegmentoCliente())));
			}
		}
		catch(Exception e)
		{
			subSegmentoEntity=null;
		}
		//La Central puede ser Nula		
		try
		{
//			log.debug("Publicando en Central:" + dp.getCodigoCentral());
			if (dp.getCodigoCentral()!= null)
			{
				centralHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				centralLocal=centralHome.findByPrimaryKey(new CentralKey(dp.getCodigoCentral()));
			}
			//else
			//	log.debug("No tengo el Valor de Central en Datos de Publicacion");
		}
		catch(FinderException e)
		{	
			centralLocal=null;
		}
		try{
		
		if (dp.getNumeroPeticion() == null ){
			throw new ParametrosIncorrectosException("Falta el Numero de Peticion para insertar en la bandeja");
		}
//		TODO: revisar que venga fecha compromiso, la bd no admite null
		else if( dp.getFechaCompromiso() == null ){
			throw new ParametrosIncorrectosException("Falta la Fecha de Compromiso para insertar en la bandeja");
		}
//		else if( dp.getFechaCompromisoSec() == null ){
//			throw new ParametrosIncorrectosException("Falta la Fecha de Compromiso Secundaria para insertar en la bandeja");
//		}
		else if( dp.getNombreCliente() == null ){
			throw new ParametrosIncorrectosException("Falta el Nombre del Cliente para insertar en la bandeja");
		}
		else if( dp.getApellidosCliente() == null ){
			throw new ParametrosIncorrectosException("Faltan los Apellidos del Cliente para insertar en la bandeja");
		}
		else if (dp.getSegmentoCliente() == null ){
		// El segmento puede ser nulo				
			segmentoEntity=null;
		}
		else if ( dp.getFamiliaPS() == null ){
			throw new ParametrosIncorrectosException("Falta Familia PS para insertar en la bandeja");
		}
		else if ( dp.getUrlDetalle() == null ) {
			throw new ParametrosIncorrectosException("Falta URL Detalle para insertar en la bandeja");
		}

		// PATCH: Los nombres no deben ser mayor a 30 ni el apellidos a 40.
		if ( dp.getNombreCliente().length() > 30 )
			dp.setNombreCliente( dp.getNombreCliente().substring(0, 29) );
		if ( dp.getApellidosCliente().length() > 40 )
			dp.setApellidosCliente( dp.getApellidosCliente().substring(0, 39) );


		// TODO: MEJORA, revisamos si ya exsite el registro en la BD.
		// si existe, solo modifico, si no inserto.
		Date inidate = new Date();
		AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEntity.getPrimaryKey();
		ActividadKey actividadKey=(ActividadKey) actividadEntity.getPrimaryKey();
		Long idAplicacion = aplicacionKey.ap_id;
		Long idActividad = actividadKey.act_id;
		
		Collection peticiones = buscaPeticiones(idAplicacion, dp.getNumeroPeticion(), idActividad);
		if ( log.isDebugEnabled() )
			log.debug("Publicado Select: [" + dp.getNumeroPeticion() + "," + dp.getCodigoActividad() + "," + (new Date().getTime()-inidate.getTime()) + "]");

		if ( peticiones.size() > 0 )
		{
			for (Iterator it = peticiones.iterator(); it.hasNext(); ) {
				BintegradaLocal b = (BintegradaLocal) it.next();
				b.setBi_visible( new Integer(1) );
				b.setBi_fecha_apertura( null );
				b.setBi_fecha_asignacion( new Fecha().getTimestamp() );
				b.setBi_estado_peticion( dp.getEstadoPeticion() );
				b.setBi_hora_desde( dp.getHoraDesde() );
				b.setBi_hora_hasta( dp.getHoraHasta() );
				b.setBi_id_tipo_agendamiento( dp.getIdTipoAgendamiento() );
				b.setFk_bi_usuario( usuarioEntity );
				b.setBi_url_detalle( dp.getUrlDetalle() );
				if(dp.getFamiliaPS()!=null)
				{
					b.setBi_url_detalle(dp.getUrlDetalle()+"&familiaPs="+dp.getFamiliaPS());
				}
				if(dp.getEstadoPeticion()!=null)
				{
					b.setBi_estado_peticion(dp.getEstadoPeticion());
					b.setBi_url_detalle(dp.getUrlDetalle()+"&estadoPet="+dp.getEstadoPeticion());
				}
			}
			if ( log.isDebugEnabled() )
				log.debug("Publicado Update: [" + dp.getNumeroPeticion() + "," + dp.getCodigoActividad() + "," + (new Date().getTime()-inidate.getTime()) + "]");

			//Se hizo l update, por lo tanto no es necesario seguir con el create
			return;
		}

		BintegradaLocal bandejaEntity = creaPeticion(
			aplicacionEntity, usuarioEntity, actividadEntity, dp);

		if (dp.getServicioCliente() != null)
			bandejaEntity.setBi_cliente_servicio(dp.getServicioCliente());
		if (dp.getAreaCliente() != null)
			bandejaEntity.setBi_cliente_area(dp.getAreaCliente());
		if (dp.getRutCliente() != null)
			bandejaEntity.setBi_cliente_rut(dp.getRutCliente());
		if (dp.getRutdvCliente() != null)
			bandejaEntity.setBi_cliente_rutdv(dp.getRutdvCliente());
		if (dp.getFechaInicio() != null)
			bandejaEntity.setBi_fecha_inicio(new Timestamp(dp.getFechaInicio().getTime()));
		dp.setFechaApertura(new Fecha().getDate());
		if (dp.getFechaAsignacion() != null)
			bandejaEntity.setBi_fecha_asignacion(new Timestamp(dp.getFechaAsignacion().getTime()));
		if (segmentoEntity != null)
		{
			bandejaEntity.setFk_bint_segmento(segmentoEntity);
			bandejaEntity.setSegm_descripcion(segmentoEntity.getSegm_descripcion());
		}
		if( subSegmentoEntity!=null)
		{
			bandejaEntity.setSubsegm_descripcion(subSegmentoEntity.getDescripcion());
		}
		if (dp.getEstadoPeticion() != null)
			bandejaEntity.setBi_estado_peticion(dp.getEstadoPeticion());
		if (dp.getTipoTrabajo() != null)
			bandejaEntity.setBi_tipo_trabajo(dp.getTipoTrabajo());
		if (dp.getIdGrupoSegmento() != null)
			bandejaEntity.setBi_grupo_segmento(new Long(dp.getIdGrupoSegmento().intValue()));
		
		bandejaEntity.setBi_nro_peticion_atis(dp.getNroPeticionAtis());
		
		if(dp.getCategoriaOpCo()!=null)
			bandejaEntity.setTica_id(dp.getCategoriaOpCo());
		
		bandejaEntity.setBi_agrupaciones(dp.getAgrupaciones());
				
		if(dp.getCodigoLocalidad()!=null)
		{
			bandejaEntity.setLocalidad(localidadLocal);
			bandejaEntity.setDesc_localidad(localidadLocal.getDescripcion_localidad());
		}
		
		if(dp.getCodigoDepartamento()!=null)
		{
			bandejaEntity.setDepartamento(departamentoLocal);	
		}
		
		if (dp.getCodigoCentral()!=null)
		{
			if(centralLocal!=null)
			{
				bandejaEntity.setDesc_central(centralLocal.getDesc_central());
				bandejaEntity.setCentral(centralLocal);
			}
		}
		
		if(dp.getFamiliaPS()!=null)
		{
			bandejaEntity.setBi_url_detalle(bandejaEntity.getBi_url_detalle()+"&familiaPs="+dp.getFamiliaPS());
		}

		// Para el agendamiento
		if (dp.getIdTipoAgendamiento() != null)
			bandejaEntity.setBi_id_tipo_agendamiento(dp.getIdTipoAgendamiento());
		if (dp.getIdRango() != null)
			bandejaEntity.setBi_id_rango(dp.getIdRango());
		if (dp.getHoraDesde() != null)
			bandejaEntity.setBi_hora_desde(dp.getHoraDesde());
		if (dp.getHoraHasta() != null)
			bandejaEntity.setBi_hora_hasta(dp.getHoraHasta());
			
		if(dp.getEstadoPeticion()!=null)
		{
			bandejaEntity.setBi_estado_peticion(dp.getEstadoPeticion());
			bandejaEntity.setBi_url_detalle(bandejaEntity.getBi_url_detalle()+"&estadoPet="+dp.getEstadoPeticion());
		}
		//Agregamos los datos para los Filtros.
		HashMap vv = getDatosVariables( dp );
		if ( actividadEntity.getFk_act_rol() != null )
		{
			RolKey rolKey=(RolKey) actividadEntity.getFk_act_rol().getPrimaryKey();
			bandejaEntity.setRol_id(rolKey.rol_id);
		}
		
		//FALTA MODIFICAR ENTITY...
		CampoVariableFactory campoFactory = new CampoVariableFactory();
		campoFactory.actualizaValoresVariables(bandejaEntity, dp.getValoresVariables());
		}catch(Exception e){
			e.printStackTrace();
			log.error("Problemas al Publicar bandejaEntity (" + dp.getNumeroPeticion() + ")", e);
			
		}

	}


	public boolean actualizaPeticion(DatosPeticion dp) throws ParametrosIncorrectosException, FactoryException, CriterioBusquedaAmbiguoException {
		if (dp.getNombreAplicacion() == null || dp.getNumeroPeticion() == null)
			throw new ParametrosIncorrectosException("Al menos debe venir nombre de aplicacion y numero de peticion");
		
		Long idAplicacion;
		try {
			AplicacionFactory aplicacionFactory = new AplicacionFactory();
			AplicacionLocal aplicacionEntity = aplicacionFactory.getAplicacionPorNombre(dp.getNombreAplicacion());
			AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEntity.getPrimaryKey();
			idAplicacion = aplicacionKey.ap_id;
		} catch (RegistroNoEncontradoException e) {
			throw new ParametrosIncorrectosException(e.getMessage());
		}
		
		Long idActividad = null;
		if (dp.getCodigoActividad() != null) {
			ActividadFactory activFactory = new ActividadFactory();
			ActividadLocal actividadEntity;
			try {
				actividadEntity = activFactory.getActividadPorCodigoActividadCodigoAplicacion(dp.getCodigoActividad(), dp.getNombreAplicacion());
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
			idActividad = (Long) actividadEntity.getPrimaryKey();
		}
		BandejaFactory bandejaFactory = new BandejaFactory();
		Collection listaPeticiones = bandejaFactory.buscaPeticiones(idAplicacion, dp.getNumeroPeticion(), idActividad);
		if (listaPeticiones.size() > 1)
			throw new CriterioBusquedaAmbiguoException("Se encontro mas de una peticion con el criterio de busqueda dado");

		if (listaPeticiones.size() == 0)
			return false;

		BintegradaLocal peticion = (BintegradaLocal) listaPeticiones.iterator().next();
		actualizaDatosPeticion(dp, peticion);
		
		return true;
	}


	private void actualizaDatosPeticion(DatosPeticion dp, BintegradaLocal peticion)
			throws FactoryException, ParametrosIncorrectosException {

		if (dp.getCodigoActividadNueva() != null) {
			ActividadFactory activFactory = new ActividadFactory();
			ActividadLocal actividadEntity;
			try {
				actividadEntity = activFactory.getActividadPorCodigoActividadCodigoAplicacion(dp.getCodigoActividadNueva(), dp.getNombreAplicacion());
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
			peticion.setFk_bi_act(actividadEntity);
		}
		
		if (dp.getSegmentoCliente() != null) {
			SegmentoFactory segmFactory = new SegmentoFactory();
			SegmentoLocal segmentoEntity;
			try {
				segmentoEntity = segmFactory.getSegmentoPorCodigo(dp.getSegmentoCliente());
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
			peticion.setFk_bint_segmento(segmentoEntity);
		}
		
		if (dp.getApellidosCliente() != null)
			peticion.setBi_cliente_apellidos(dp.getApellidosCliente());
		if (dp.getAreaCliente() != null)
			peticion.setBi_cliente_area(dp.getAreaCliente());
		if (dp.getFamiliaPS() != null) {
			// Cortamos el largo por si se pasas.
			String famPS = ( dp.getFamiliaPS().length() > 15 ) ? dp.getFamiliaPS().substring(0, 14) : dp.getFamiliaPS();
			peticion.setBi_familia_ps( famPS );
		}
		
		if(dp.getCategoriaOpCo()!=null)
			peticion.setTica_id(dp.getCategoriaOpCo());
			
		if (dp.getFechaApertura() != null)
			peticion.setBi_fecha_apertura(new Timestamp(dp.getFechaApertura().getTime()));
		if (dp.getFechaAsignacion() != null)
			peticion.setBi_fecha_asignacion(new Timestamp(dp.getFechaAsignacion().getTime()));
		if (dp.getFechaCompromiso() != null)//TODO Fecha compromiso
			peticion.setBi_fecha_compromiso(new Timestamp(dp.getFechaCompromiso().getTime()));
		if (dp.getFechaCompromisoSec() != null)//TODO Fecha compromiso
			peticion.setBi_fecha_compromiso_sec(new Timestamp(dp.getFechaCompromisoSec().getTime()));
		if (dp.getFechaInicio() != null)
			peticion.setBi_fecha_inicio(new Timestamp(dp.getFechaInicio().getTime()));
		if (dp.getNombreCliente() != null)
			peticion.setBi_cliente_nombre(dp.getNombreCliente());
		if (dp.getPuntaje() != null)
			peticion.setBi_puntaje(dp.getPuntaje());
		if (dp.getRutCliente() != null)
			peticion.setBi_cliente_rut(dp.getRutCliente());
		if (dp.getRutdvCliente() != null)
			peticion.setBi_cliente_rutdv(dp.getRutdvCliente());
		if (dp.getServicioCliente() != null)
			peticion.setBi_cliente_servicio(dp.getServicioCliente());
		if (dp.getUrlDetalle() != null)
			peticion.setBi_url_detalle(dp.getUrlDetalle());
		if (dp.getUsernameResponsable() != null) {
			UsuarioFactory usuarioFactory = new UsuarioFactory();
			UsuarioLocal usuarioEntity;
			try {
				usuarioEntity = usuarioFactory.getUsuarioPorLogin(dp.getUsernameResponsable());
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
			peticion.setFk_bi_usuario(usuarioEntity);
		}
		if (dp.getCodigoCentral()!=null)
		{
			try
			{
				CentralLocalHome centralLocalHome=(CentralLocalHome) HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
				CentralLocal centralLocal=centralLocalHome.findByPrimaryKey(new CentralKey(dp.getCodigoCentral()));
				peticion.setDesc_central(centralLocal.getDesc_central());
			}
			catch(FinderException fe)
			{
				log.debug("Central No encontrada");
			}
			catch(Exception e)
			{
				log.debug("Error al Setear la Central");
			}
		}
		if (dp.getValoresVariables() != null) {
			CampoVariableFactory campoFactory = new CampoVariableFactory();
			campoFactory.actualizaValoresVariables(peticion, dp.getValoresVariables());
		}
	}

	public int cuentaPublicacionesVisiblesIgualImplCorrelID(Long nroPeticion, String nomAplicacion, String codActividad, String actImplCorrelID) throws ParametrosIncorrectosException, FactoryException{
		int cantidadPublic=0;

		if (nroPeticion == null)
			throw new ParametrosIncorrectosException("Falta numero de peticion");

		AplicacionFactory apFactory = new AplicacionFactory();
		Long idAplicacion;
		try {
			AplicacionKey aplicacionKey=(AplicacionKey) apFactory.getAplicacionPorNombre(nomAplicacion).getPrimaryKey();
			idAplicacion = aplicacionKey.ap_id;
		} catch (RegistroNoEncontradoException e) {
			throw new ParametrosIncorrectosException(e.getMessage());
		} 

		if (idAplicacion == null)
			throw new ParametrosIncorrectosException("Falta nombre de aplicacion");

		Long idActividad = null;
		if (codActividad != null) {
			ActividadFactory actFactory = new ActividadFactory();
			try {
				ActividadKey actividadKey=(ActividadKey) actFactory.getActividadPorCodigoActividadCodigoAplicacion(codActividad, nomAplicacion).getPrimaryKey();
				idActividad = actividadKey.act_id ;
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
		}

		Collection peticiones = buscaPeticiones(idAplicacion, nroPeticion, idActividad);

		for (Iterator it = peticiones.iterator(); it.hasNext(); ) {
			BintegradaLocal b = (BintegradaLocal) it.next();

//			Recupero el id instancia y valido si estoy cerrando la instancia correcta
			String urlBI=b.getBi_url_detalle();
			String aBuscar="instanciaActividad=";
			String idTemp = urlBI.substring(urlBI.indexOf(aBuscar) + aBuscar.length() );
			String idFinal = idTemp.substring(0,idTemp.indexOf("&"));
			String idDecode = URLDecoder.decode(idFinal);
			
			boolean elimina=true;
			log.debug("Elimina bandeja peticion:" + nroPeticion +  " Instancia en Web:" + actImplCorrelID + " Instancia en BD:" + idDecode);
			if(actImplCorrelID!=null && !"".equals(actImplCorrelID.trim())){
				if(!idDecode.equals(actImplCorrelID.trim())){
					elimina=false;
				}
				
			}
			if(elimina){
				cantidadPublic++;
			}
		}
		
		return cantidadPublic;
	}
	
	public int eliminaPeticiones(DatosPeticion dp) throws ParametrosIncorrectosException, FactoryException {
		if (dp.getNombreAplicacion() == null)
			throw new ParametrosIncorrectosException("Falta nombre de aplicacion");
		if (dp.getNumeroPeticion() == null)
		 	throw new ParametrosIncorrectosException("Falta numero de peticion");
		
		AplicacionFactory apFactory = new AplicacionFactory();
		Long idAplicacion;
		try {
			AplicacionKey aplicacionKey=(AplicacionKey) apFactory.getAplicacionPorNombre(dp.getNombreAplicacion()).getPrimaryKey();
			idAplicacion = aplicacionKey.ap_id;
		} catch (RegistroNoEncontradoException e) {
			throw new ParametrosIncorrectosException(e.getMessage());
		} 

		Long idActividad = null;
		if (dp.getCodigoActividad() != null) {
			ActividadFactory actFactory = new ActividadFactory();
			try {
				ActividadKey actividadKey=(ActividadKey) actFactory.getActividadPorCodigoActividadCodigoAplicacion(dp.getCodigoActividad(), dp.getNombreAplicacion()).getPrimaryKey();
				idActividad = actividadKey.act_id ;
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
		}

		Collection peticiones = buscaPeticiones(idAplicacion, dp.getNumeroPeticion(), idActividad);

		int eliminados = 0;
		for (Iterator it = peticiones.iterator(); it.hasNext(); ) {
			BintegradaLocal b = (BintegradaLocal) it.next();
			// No borramos fisicamente la Bandeja Integrada.
			// Solo realizamos el Update.
//			Recupero el id instancia y valido si estoy cerrando la instancia correcta
//			String urlBI=b.getBi_url_detalle();
//			String aBuscar="instanciaActividad=";
//			String idTemp = urlBI.substring(urlBI.indexOf(aBuscar) + aBuscar.length() );
//			String idFinal = idTemp.substring(0,idTemp.indexOf("&"));
//			String idDecode = URLDecoder.decode(idFinal);
			
//			boolean elimina=true;
//			log.debug("Elimina bandeja peticion:" + dp.getNumeroPeticion() +  " Instancia en Web:" + dp.getActImplCorrelID() + " Instancia en BD:" + idDecode);
//			if(dp.getActImplCorrelID()!=null && !"".equals(dp.getActImplCorrelID().trim())){
//				if(!idDecode.equals(dp.getActImplCorrelID().trim())){
//					elimina=false;
//				}
//				
//			}
//			if(elimina){
				b.setBi_visible( new Integer(2) );
				eliminados++;
//			}
/*
			try {
				eliminaValores(b);
			} catch (Exception e) {
				log.error("No se pudo borrar registro en ValorVariable: " +
					"[" + b.getPrimaryKey() + "," + dp.getNumeroPeticion() 
					+ "," + dp.getCodigoActividad() + "," + dp.getNombreAplicacion() + "]", e);
				throw new FactoryException("No se pudo borrar registro en ValorVariable:" +
					"[" + b.getPrimaryKey() + "," + dp.getNumeroPeticion() 
					+ "," + dp.getCodigoActividad() + "," + dp.getNombreAplicacion() + "]", e);
			}
			try {
				b.remove();
				eliminados++;
			} catch (Exception e) {
				log.error("No se pudo borrar registro en Bandeja: " +
					"[" + b.getPrimaryKey() + "," + dp.getNumeroPeticion() 
					+ "," + dp.getCodigoActividad() + "," + dp.getNombreAplicacion() + "]", e);
				throw new FactoryException("No se pudo borrar registro en Bandeja:" +
					"[" + b.getPrimaryKey() + "," + dp.getNumeroPeticion() 
					+ "," + dp.getCodigoActividad() + "," + dp.getNombreAplicacion() + "]", e);
			}
*/
				}
		
		return eliminados;
	}

//debe venir aplicacion, peticion, actividad y usuario para hacer bien el cambio de usuario
	public int cambiaUsuario(DatosPeticion dp) throws ParametrosIncorrectosException, FactoryException {
		if (dp.getNombreAplicacion() == null)
			throw new ParametrosIncorrectosException("Falta nombre de aplicacion");
		if (dp.getNumeroPeticion() == null)
			throw new ParametrosIncorrectosException("Falta numero de peticion");
		
		AplicacionFactory apFactory = new AplicacionFactory();
		Long idAplicacion;
		try {
			AplicacionKey aplicacionKey=(AplicacionKey) apFactory.getAplicacionPorNombre(dp.getNombreAplicacion()).getPrimaryKey();
			idAplicacion = aplicacionKey.ap_id;
		} catch (RegistroNoEncontradoException e) {
			throw new ParametrosIncorrectosException(e.getMessage());
		} 

		Long idActividad = null;
		if (dp.getCodigoActividad() != null) {
			ActividadFactory actFactory = new ActividadFactory();
			try {
				ActividadKey actividadKey=(ActividadKey) actFactory.getActividadPorCodigoActividadCodigoAplicacion(dp.getCodigoActividad(), dp.getNombreAplicacion()).getPrimaryKey();
				idActividad = actividadKey.act_id ;
			} catch (RegistroNoEncontradoException e) {
				throw new ParametrosIncorrectosException(e.getMessage());
			}
		}
		
		UsuarioFactory usuarioFactory = new UsuarioFactory();
		UsuarioLocal usuarioEntity;
		try {
			usuarioEntity = usuarioFactory.getUsuarioPorId(dp.getIdResponsable());
		} catch (RegistroNoEncontradoException e) {
			throw new ParametrosIncorrectosException(e.getMessage());
		}

		Collection peticiones = buscaPeticiones(idAplicacion, dp.getNumeroPeticion(), idActividad);

		int cambiados = 0;
		for (Iterator it = peticiones.iterator(); it.hasNext(); ) {
			BintegradaLocal b = (BintegradaLocal) it.next();
			b.setFk_bi_usuario(usuarioEntity);
			cambiados++;
		}
		return cambiados;
	}

	/* 
	 * Elimino los valores variables asociados a esta peticion.
	 */
	private void eliminaValores(BintegradaLocal b) throws RemoveException {
		ArrayList lista = new ArrayList();
		for (Iterator it = b.getValor_variable().iterator(); it.hasNext(); ) {
			Valor_variableLocal vvE = (Valor_variableLocal)it.next();
			//lista.add(vvE);
		//}
		// TODO: ¿Por Que vuelve a Iterar para Borrar los Valores Variables?
		//for (Iterator it = lista.iterator(); it.hasNext(); ) {
		//	ValorVariableEntityLocal vvE = (ValorVariableEntityLocal)it.next();
			vvE.remove();
		}
	}

	public Collection buscaPeticiones(Long idAplicacion, Long nroPeticion) throws FactoryException {
		try {
			return getBandejaHome().findByIdAplicacionNroPeticion(idAplicacion, nroPeticion);
		} catch (FinderException e) {
		}
		return new ArrayList();
	}

	public Collection buscaPeticiones(Long idAplicacion, Long nroPeticion, Long idActividad) throws FactoryException {
		try {
			if (idActividad != null)//Busca las visibles
				return getBandejaHome().findByIdAplicacionNroPeticionIdActividad(idAplicacion, nroPeticion, idActividad);
			else
				return getBandejaHome().findByIdAplicacionNroPeticion(idAplicacion, nroPeticion);
		} catch (FinderException e) {
		}
		return new ArrayList();
	}

	private BintegradaLocal creaPeticion(
		AplicacionLocal aplicacionEntity,
		UsuarioLocal usuarioEntity, ActividadLocal actividadEntity,
		DatosPeticion dp) throws FactoryException, RegistroDuplicadoException {
			
		AplicacionKey aplicacionKey=(AplicacionKey) aplicacionEntity.getPrimaryKey();
		ActividadKey actividadKey=(ActividadKey) actividadEntity.getPrimaryKey();
		Long idAplicacion = aplicacionKey.ap_id ;
		Long idActividad = actividadKey.act_id ;

//		TODO: Esta validacion ya se hace en el metodo agregaPeticion. Por eso se comentó
//		Collection lista = buscaPeticiones(idAplicacion, dp.getNumeroPeticion(), idActividad);
//		if (lista.size() > 0)
//			throw new RegistroDuplicadoException("Ya existe un registro con aplicacion/numpeticion/actividad dados");

		try {

			// Parche para que no se pase por largo Maximo. 
			String famPS = "";
			if (dp.getFamiliaPS() != null) {
				// Cortamos el largo por si se pasa.
				famPS = ( dp.getFamiliaPS().length() > 200 ) ? dp.getFamiliaPS().substring(0, 199) : dp.getFamiliaPS();
			}

//			TODO: se obtendrá el siguiente valor de una secuencia para BIntegrada.
			DBManager manager;
			manager=new DBManager();
			manager.setDataSource(DBManager.JDBC_VPISTBBA);
			Long bi_id=new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_BINTEGRADA"));
			
			//para obtener timestamp
			Fecha fComp = new Fecha();
			fComp.setTime(dp.getFechaCompromiso());
//			CR17031 pcawen
			Fecha fCompSec = new Fecha();
			if (dp.getFechaCompromisoSec() != null)
				fCompSec.setTime(dp.getFechaCompromisoSec());
			
			BintegradaLocal bandejaEntity = getBandejaHome().create(bi_id,dp.getNumeroPeticion(),
											fComp.getTimestamp(),fCompSec.getTimestamp(), dp.getCategoria(), dp.getNombreCliente(),dp.getApellidosCliente(),
											dp.getFamiliaPS(),dp.getUrlDetalle(),dp.getPuntaje(),aplicacionEntity,
											actividadEntity,usuarioEntity);
			if(actividadEntity.getAct_codigo().equals(ComunInterfaces.ACT_INSTALAR_TOA))
				bandejaEntity.setBi_visible(new Integer(2));
			return bandejaEntity;
//			BintegradaLocal bandejaEntity = getBandejaHome().create(
//						aplicacionEntity, usuarioEntity, dp.getNumeroPeticion(), 0, dp.getFechaCompromiso(),
//						dp.getNombreCliente(), dp.getApellidosCliente(), famPS,
//						dp.getUrlDetalle(), dp.getPuntaje(), actividadEntity);
//			return bandejaEntity;
//			return null;
		} catch (Exception e) {
			log.error("Problemas creando bandejaEntity (" + dp.getNumeroPeticion() + ")", e);
			throw new FactoryException("Problemas creando bandejaEntity (" + dp.getNumeroPeticion() + "): " + e);
		}
	}
	
	private HashMap getDatosVariables(DatosPeticion dp) {
		HashMap map = new HashMap();
		if (dp==null || dp.getValoresVariables()==null)
			return map;
		String[][] aux = dp.getValoresVariables();
		for (int i=0; i<aux.length; i++) {
			String nombre = aux[i][0];
			String valor = aux[i][1];
			if ( nombre!=null && valor!=null )
				map.put( nombre, valor );
		}
		
		return map;
	}
}
