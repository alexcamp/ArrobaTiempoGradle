package co.com.telefonica.atiempo.vpistbba.actividades.cancelacion.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalKey;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocal;
import co.com.telefonica.atiempo.ejb.eb.Catalogo_causalLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Group1;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest1;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.CierrePeticionATISMQ;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAnulacionATIS
 */
public class AAnulacionATISBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	log.debug("INICIO AUTOMATICO, AnulacionATIS");

		try
		{
			DBManager dbSeq=new DBManager();
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
			
			Long causalAuto=new Long(VpistbbaConfig.getVariable("COD_CAU_IMPO_TEC"));
			
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estado_ps_peticionHome=(Estado_ps_peticionLocalHome) HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			Estado_psLocalHome estado_psHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
			UsuarioLocalHome usuarioHome=(UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Causal_peticionLocalHome causal_peticionHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
			
			PeticionLocal petiBaja=peticionHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			for(Iterator iterator=petiBaja.getProducto_servicio_peticion().iterator();iterator.hasNext();)
			{
				Producto_servicio_peticionLocal psBaja=(Producto_servicio_peticionLocal) iterator.next();
				Catalogo_causalKey catalogo_causalKey=new Catalogo_causalKey(causalAuto);
				Catalogo_causalLocal catalogo_causalLocal=catalogo_causalHome.findByPrimaryKey(catalogo_causalKey);

				long correlativo=dbSeq.seqNextValLong("CORRELATIVO_ESTADO_PS_PETICION");
				Estado_ps_peticionLocal estadoPS=estado_ps_peticionHome.create(new Long(correlativo),psBaja.getProducto_servicio(),psBaja);
				estadoPS.setCod_causal(catalogo_causalKey.cod_causal);
				estadoPS.setCod_estado_cierre(new Integer(3));
				estadoPS.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				estadoPS.setCod_actividad(new Long(1007));
	
				Estado_psKey estado_psKey=new Estado_psKey(new Long(3));
				Estado_psLocal estado_psLocal=estado_psHome.findByPrimaryKey(estado_psKey);
				UsuarioLocal usuarioLocal=usuarioHome.findByPrimaryKey(new UsuarioKey(new Long(0)));
				long id_causal_peticion=dbSeq.seqNextValLong("CORRELATIVO_CAUSAL_PETICION");
				Causal_peticionLocal causal_peticionLocal=causal_peticionHome.create(new Long(id_causal_peticion),catalogo_causalLocal,estado_psLocal,estadoPS,usuarioLocal);
				Fecha fecha=new Fecha();
				causal_peticionLocal.setFecha_inicio(fecha.getFechaconFormato(9));
				causal_peticionLocal.setFecha_termino(fecha.getFechaconFormato(9));
				causal_peticionLocal.setNovedad(catalogo_causalLocal.getDescripcion_causal());
				causal_peticionLocal.setCod_actividad(new Long(1007));
			}
//
			PeticionesDelegate delegate=new PeticionesDelegate();
			
			act.setDato(DATOS_ATVPISTBBA.CANCELACION.recep_es_ok_anulacion, "S");
			
			// CR26362 - adocarmo - inicio
			// Si entre a esta actividad es porque se canceló una peticion de alta solo BA que es par de la peticion actual
			// agrego una observacion en la bitacora
			
			Long petiAlta =delegate.petiAltaAsociada(act.getNumeroPeticion()); 
			if (petiAlta !=null) {
				//log.debug(">>>>>>>>>>ENTRE A ANULAR LA BAJAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!");
				act.setObservacion("La petición se anula ya que la peticion de ALTA por traslado " + petiAlta + " se canceló");
			}
			
			// CR26362 - adocarmo - fin
			
			petiBaja.setEspe_id(new Integer(ComunInterfaces.estadoPeticionCancelada));
			enviaMensajeCierreTecnicoAnulacion(petiBaja.getFk_01_pet_atis(),act);
			
		} catch (Exception e)
		{
			act.setDato(DATOS_ATVPISTBBA.CANCELACION.recep_es_ok_anulacion, "N");
			log.debug("Se registro un problema. ",e);
		}
		
		log.debug("FIN AUTOMATICO, AnulacionATIS");		
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion
	{
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setRealizarTerminoInmediato(true);
	}
	
	
	private void enviaMensajeCierreTecnicoAnulacion (Peticion_atisLocal peticionAtis, ActividadEJBDTO act) throws ATiempoAppEx
	{

		Peticion_atisLocalHome peticionATISHome=null;
		PeticionLocalHome peticionHome=null;
		Agrupacion_atisLocalHome agrupacionHome=null;
		Familia_producto_servicioLocalHome familiaPsLocalHome=null;
		try
		{
			peticionATISHome = (Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			agrupacionHome = (Agrupacion_atisLocalHome) HomeFactory.getHome(Agrupacion_atisLocalHome.JNDI_NAME);
			familiaPsLocalHome = (Familia_producto_servicioLocalHome) HomeFactory.getHome(Familia_producto_servicioLocalHome.JNDI_NAME);
		}
		catch (NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	            
		TR001E tr001e = new TR001E () ;
		DBManager dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		
		Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
    
		tr001e.setId (idCorrelativoMensaje.toString ()) ;
    
		Peticion_atisKey atisRequestKey = (Peticion_atisKey) peticionAtis.getPrimaryKey () ;
    
		long atisRequestNumber = atisRequestKey.cod_pet_cd.longValue () ;
    
		tr001e.setAtisRequestNumber (atisRequestNumber) ;
    
		List groups = new ArrayList (peticionAtis.getAgrupacion_atis ().size ()) ;
    
		tr001e.setGroups (groups) ;
    
		Iterator iterAgrupacion = peticionAtis.getAgrupacion_atis ().iterator () ;
    
		while (iterAgrupacion.hasNext ())
		{
			Agrupacion_atisLocal agrupacion = (Agrupacion_atisLocal) iterAgrupacion.next () ;
			Agrupacion_atisKey agrupacion_atisKey=(Agrupacion_atisKey) agrupacion.getPrimaryKey();
         
			boolean esInternetConmutado=false;

			if(agrupacion.getTip_pro_cmr_cd()!=null && agrupacion.getTip_pro_cmr_cd().intValue()==new Integer(VpistbbaConfig.getVariable("TIP_PC_IC")).intValue())
				esInternetConmutado=true;
			
			Group1 group1 = new Group1 () ;
        
			int atisGroupNumber = ((Agrupacion_atisKey) agrupacion.getPrimaryKey ()).cod_agr_sub_nu.intValue () ;
		
			log.debug("Cierre Peticion Atis[" + atisRequestNumber + "]. Agrupacion:" + atisGroupNumber);
        
			group1.setAtisGroupNumber (atisGroupNumber) ;
        
			List subRequests = new ArrayList (agrupacion.getSubpeticion_atis ().size ()) ;
        
			group1.setSubRequests (subRequests) ;
        
			// usado para saber que tipo de agrupacion es (Telefono o TV)
			Integer tipoAgrupacion = ComunInterfaces.tipoAgrupacionNoDeterminado ;
        
			// el primer ps en la Agrupacion (y cuya familia determina el tipo de Agrupacion)
			Producto_servicio_peticionLocal psPeticionPrimerPs = null ;
        
			Iterator iterSubPeticion = agrupacion.getSubpeticion_atis ().iterator () ;
        
			while (iterSubPeticion.hasNext ())
			{
				Subpeticion_atisLocal subPeticion = (Subpeticion_atisLocal) iterSubPeticion.next () ;
            
				SubRequest1 subRequest1 = new SubRequest1 () ;
            
				Subpeticion_atisKey subPeticionKey = (Subpeticion_atisKey) subPeticion.getPrimaryKey () ;
            
				int atisSubRequestNumber = subPeticionKey.cod_sub_cd.intValue () ;
            
				log.debug("Cierre Peticion Atis[" + atisRequestNumber + "]. Subpeticion:" + atisSubRequestNumber);
				subRequest1.setAtisSubRequestNumber (atisSubRequestNumber) ;
            
            
				Collection pss = subPeticion.getProducto_servicio_peticion () ;
            
				// chequea que la relacion Subpeticion con Producto_servicio_peticion es de 1 a 1
            
				if (pss.size () == 0)
				{
					throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]: No encontre el producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
            
				else if (pss.size () > 1)
				{
					throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]: Encontre mas de un producto_servicio_peticon asociado a la sub peticion ATIS: peticion_ATIS/agrupacion_ATIS/sub_peticion_ATIS = " + atisRequestNumber + "/" + atisGroupNumber + "/" + atisSubRequestNumber) ;
				}
            
				//
            
				Producto_servicio_peticionLocal psPeticion = (Producto_servicio_peticionLocal) (pss.iterator ().next ()) ;
            
				try
				{
					llenaSubRequest1 (subRequest1, psPeticion) ;
				}
				catch (FinderException e2)
				{
					e2.printStackTrace();
					throw new ATiempoAppEx(ATiempoAppEx.FINDER,e2);
				}catch (NamingException e)
				{
					e.printStackTrace();
					throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
				}
            
				subRequests.add (subRequest1) ;


				if (psPeticionPrimerPs == null)
				{
					psPeticionPrimerPs = psPeticion ;
                
					Producto_servicioLocal ps = psPeticion.getProducto_servicio () ;
                
					// busca la familia "madre" (STB, BA o TV)
                
					Familia_producto_servicioLocal familiaPs = ps.getFamilia_producto_servicio () ;
                
					while (familiaPs.getFaps_id_padre () != null && familiaPs.getFaps_id_padre ().longValue () != 0)
					{
						Familia_producto_servicioKey key = new Familia_producto_servicioKey (familiaPs.getFaps_id_padre ()) ;
                    
						try
						{
							familiaPs = familiaPsLocalHome.findByPrimaryKey (key) ;
						}
						catch (FinderException e1)
						{
							e1.printStackTrace();
							throw new ATiempoAppEx(ATiempoAppEx.FINDER,e1);
						}
					}
                
					//
                
					long idFamilia = ((Familia_producto_servicioKey) familiaPs.getPrimaryKey ()).faps_id.longValue () ;
                
					if (idFamilia == ComunInterfaces.familiaLinea || idFamilia == ComunInterfaces.familiaBandaAncha || idFamilia==ComunInterfaces.familiaPcLinea ||  idFamilia==ComunInterfaces.familiaIP || idFamilia==ComunInterfaces.familiaPcBA || idFamilia == ComunInterfaces.familiaAsistencia)
					{
						tipoAgrupacion = ComunInterfaces.tipoAgrupacionLinea ;
					}//TODO PVR validar se agrego  familia
					else if (idFamilia==ComunInterfaces.familiaPcTV || idFamilia==ComunInterfaces.familiaDecoTV ||idFamilia==ComunInterfaces.familiaDecoPVRTV || idFamilia==ComunInterfaces.familiaTematicoTV || idFamilia==ComunInterfaces.familiaPaqueteTV || idFamilia==ComunInterfaces.familiaTV)
					{
						tipoAgrupacion = ComunInterfaces.tipoAgrupacionTV ;
					}else if(idFamilia==ComunInterfaces.familiaIC){
						
						tipoAgrupacion = ComunInterfaces.tipoAgrupacionIC;
					}else if (idFamilia==ComunInterfaces.familiaIntEquipado){
						
						tipoAgrupacion = ComunInterfaces.tipoAgrupacionIT;
					}else if(idFamilia==ComunInterfaces.familiaMantenimiento){
						
						tipoAgrupacion = ComunInterfaces.tipoAgrupacionMNT;
					}
					 //RQ Napster SE ADICIONA ESTE IF PARA LA FAMILIA NAPSTER 316 dcardena
					//RQ se modifica napster por SVAs
                else if (idFamilia==ComunInterfaces.familiaPS_SVA)
                {
                	if(agrupacion.getNum_ide_nu ().startsWith("TV"))
                	{
                	
                		tipoAgrupacion= ComunInterfaces.tipoAgrupacionTV;
                	}else{
                	                 	
                	tipoAgrupacion= ComunInterfaces.tipoAgrupacionLinea;
                	}
//                	REQ BA NAKED adicion familia PC naked
                }else if(idFamilia==ComunInterfaces.familiaBandaAnchaNaked || idFamilia==ComunInterfaces.familiaPcBANaked){
                	tipoAgrupacion= ComunInterfaces.tipoAgrupacionBA;
                }
				}
			}
        
			// algunos chequeos simples
        
			if (tipoAgrupacion .equals (ComunInterfaces.tipoAgrupacionNoDeterminado))
				throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]. No se puede determinar tipo de agrupacion (Linea o TV)") ;
        
			// en caso de TV se retorna el "TV" + num_ide_nu
			if (tipoAgrupacion .equals (ComunInterfaces.tipoAgrupacionTV))
			{
				String numIdeNu = agrupacion.getNum_ide_nu () ;
            
				if (numIdeNu == null)
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
				 else if(numIdeNu.equals(""))
				 {
					numIdeNu="TV"+"_"+atisRequestKey.cod_pet_cd+"_"+agrupacion_atisKey.cod_agr_sub_nu;
				 }
            
				group1.setComercialProductIdNumber (numIdeNu) ;
			}
        
			// en caso de linea se retorna el numero de telefono nuevo (si es distinto de null) o el actual si no
			// cuidado en la bd: "anterior" es equivalente a "actual" y "asignado" es equivalente a "nuevo"
			else if (tipoAgrupacion .equals ( ComunInterfaces.tipoAgrupacionLinea))
			{
				// busca el recurso de linea basica
				if(esInternetConmutado)
				{
					for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
					{
						Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
						Iterator iterCarac=subpeticion_atisLocal.getSubpeticion_caracteristicas().iterator();
						while (iterCarac.hasNext())
						{
							Subpeticion_caracteristicasLocal subpeticion_caracteristicasLocal = (Subpeticion_caracteristicasLocal)iterCarac.next();
							Subpeticion_caracteristicasKey spk = (Subpeticion_caracteristicasKey) subpeticion_caracteristicasLocal.getPrimaryKey();
							Long codFatherEmail=new Long (VpistbbaConfig.getVariable("USUACC"));
							if (spk.cod_crc_cd.longValue()== codFatherEmail.longValue())
							{
								log.debug("Informacion : Se obtuvo Father Email " + subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								group1.setComercialProductIdNumber(subpeticion_caracteristicasLocal.getVal_ral_crc_cd());
								break;
							}
						}	
					}
				}
				else
				{
					PeticionLocal peticionVPI = psPeticionPrimerPs.getFk_psp_pet () ;
            
					Collection colRecursosLineaBasica = peticionVPI.getRecursos_linea_basica () ;

					if (colRecursosLineaBasica.size () == 0)
					{
						String esMuv=peticionVPI.getComercialProductIdentification();
						log.debug(esMuv);
						if(esMuv!=null && !esMuv.equals(VpistbbaConfig.getVariable("MM")))
							group1.setComercialProductIdNumber(peticionVPI.obtenerCPINAGrupacionOriginal());
					}

					else if (colRecursosLineaBasica.size () > 1)
						throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]. Hay mas de un registro en recursos_linea_basica") ;
					if(colRecursosLineaBasica.size()==1)
					{
						Recursos_linea_basicaLocal rlb = (Recursos_linea_basicaLocal) colRecursosLineaBasica.iterator ().next () ;

						//Ver el caso especial de Traslado.
						boolean esTraslado=esAgrupacionDeTraslado(agrupacion);
						if(esTraslado)
						{
							log.debug("Procesando Cierre de Traslado");
							ArrayList tiposOpCom=retornaTiposOperAgrupacion(agrupacion);
							if(tiposOpCom.contains("ACP") || tiposOpCom.contains("A"))
							{
								//Agrupacion de Alta
								if (rlb.getTelefono_asg () != null)
								{
									group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
								}
								else
									group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
								if(rlb.getCont_linea_nueva()!=null)
									group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
							}
							else
							{
								if (rlb.getTelefono_ant () != null)
									group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
								else
									group1.setComercialProductIdNumber(agrupacion.getNum_ide_nu());
								if(rlb.getCont_linea_act()!=null)
									group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
							}
						}
						else
						{
							log.debug("Procesando Cierre de NO Traslado");
							if (rlb.getTelefono_asg () != null)
							{
								group1.setComercialProductIdNumber (rlb.getTelefono_asg ().toString ()) ;
							}
							else if (rlb.getTelefono_ant () != null)
							{
								group1.setComercialProductIdNumber (rlb.getTelefono_ant ().toString ()) ;
							}
							else
								log.debug("Cierre Peticion Atis[" + atisRequestNumber + "]. Registro en recursos_linea_basica no tiene ni el telefono asignado (o nuevo) o el telefono anterior (o actual), se enviara al cierre sin el numero telefono") ;
							if(rlb.getCont_linea_act()!=null)
								group1.setPulseQuantityFrom(rlb.getCont_linea_act().longValue());
							if(rlb.getCont_linea_nueva()!=null)
								group1.setPulseQuantityTo(rlb.getCont_linea_nueva().longValue());
						}
						// retorna el telefono nuevo si existe si no el actual
						if(group1.getComercialProductIdNumber()==null)
							group1.setComercialProductIdNumber("");	
					}
					else
					{
						group1.setComercialProductIdNumber (peticionVPI.getNum_ide_nu_stb()) ;
						group1.setPulseQuantityFrom(0);
						group1.setPulseQuantityTo(0);
					}	
				}
			}
			else if (tipoAgrupacion==ComunInterfaces.tipoAgrupacionIT){
            	log.debug("Entro a procesar el cierre para venta de equipos en AAnulacionATISBean");
            	for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();){
            		Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
            		Subpeticion_atisKey subpeticionAtisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
        			
            		log.debug("Recorre las subpeticion:"+subpeticionAtisKey.cod_sub_cd+","+subpeticionAtisKey.fk_agru_sub_cod_agr_sub_nu+","+subpeticionAtisKey.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd);
            		
            		Agrupacion_atisLocal agrupacionAtisLocal = subpeticion_atisLocal.getFk_agru_sub();
            		Peticion_atisLocal peticionAtisLocal = agrupacionAtisLocal.getFk_pet_agrupacion();
            		Collection peticiones = peticionAtisLocal.getPeticion();
            		            		
            		for(Iterator iteratorPeticiones=peticiones.iterator();iteratorPeticiones.hasNext();){
            			PeticionLocal peticionLocal = (PeticionLocal) iteratorPeticiones.next();
            			PeticionKey peticionKey = (PeticionKey)peticionLocal.getPrimaryKey();
            			log.debug("Recorre la peticion: "+peticionKey.peti_numero);
            			
            			Collection elementos =peticionLocal.getElemento_peticion();
            			if(elementos.isEmpty()){
            				group1.setComercialProductIdNumber(peticionLocal.getIdentificadorOriLinea());
            			}
            			for(Iterator iteratorElementos=elementos.iterator();iteratorElementos.hasNext();){
                			Elemento_PeticionLocal elementoPeticionLocal=(Elemento_PeticionLocal) iteratorElementos.next();
                			log.debug("Los valores a comparar son elementoPeticion.getPs_id: "+elementoPeticionLocal.getPs_id().longValue()
                					+" con  subpeticion_atis.getCod_pro_ser_cd:" + subpeticion_atisLocal.getCod_pro_ser_cd().longValue());
                			
                			if (elementoPeticionLocal.getPs_id().longValue() == subpeticion_atisLocal.getCod_pro_ser_cd().longValue()){
                				log.debug("El elemento se encontró y se procede a asignarlo en la operación comercial");
                				group1.setComercialProductIdNumber(elementoPeticionLocal.getSerial());
            				}else{
            					log.debug("El elemento no conincide y se procede a análizar el siguiente");
            				}
                		}
           			}            		
        		}
            }
			else if (tipoAgrupacion==ComunInterfaces.tipoAgrupacionPS_GVP){
				try{
					PeticionLocal petiLocal=peticionHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
					group1.setComercialProductIdNumber(petiLocal.getNum_ide_nu_mp());
				}catch (FinderException e) {
					// TODO Bloque catch generado automáticamente
					throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
				}
				
			}
			else
				throw new ATiempoAppEx ("Cierre Peticion Atis[" + atisRequestNumber + "]. Tipo de agrupacion desconocido: no se sabe que enviar como numero de producto comercial") ;
        
			 /*mfmendez - no se envia campo comercial-product-identification-number*/
            if(group1.getComercialProductIdNumber() == null)
            	group1.setComercialProductIdNumber("");
            
			groups.add (group1) ;
		}
//		envia el mensaje
		log.debug("Cierre Peticion Atis[" + atisRequestNumber + "]. Envio Mensaje de Cierre.") ;
		new CierrePeticionATISMQ ().enviarMensaje (tr001e) ;
		for(Iterator iterator=peticionAtis.getPeticion().iterator();iterator.hasNext();)
		{
			PeticionLocal local=(PeticionLocal) iterator.next();
			PeticionKey key=(PeticionKey) local.getPrimaryKey();
			if(key.peti_numero.longValue()!=act.getNumeroPeticion().longValue())
				local.setEspe_id(new Integer(ComunInterfaces.estadoPeticionTerminada));
		}
	}
	
	private void llenaSubRequest1 (SubRequest1 subRequest1, Producto_servicio_peticionLocal psPeticion ) throws ATiempoAppEx, NamingException, FinderException
	{
		Catalogo_causalLocalHome catalogo_causalHome=(Catalogo_causalLocalHome) HomeFactory.getHome(Catalogo_causalLocalHome.JNDI_NAME);
		Collection colEstadoPsPeticion = psPeticion.getEstado_ps_peticion () ;
    
//		if (colEstadoPsPeticion.size () > 1)//aunque tenga mas de uno siempre voy a sacar el primero.
//		{
//			Producto_servicio_peticionKey key = (Producto_servicio_peticionKey) psPeticion.getPrimaryKey () ;
//            
//			String llave = "" + key.fk_psp_pet_peti_numero.longValue () + " - " + key.correlativo ;
//            
//			throw new ATiempoAppEx ("producto_servicio_peticion tiene mas de 1 estado: " + llave)  ;
//		}
    
		String errorType = null;
    
		log.debug("EstadoPsPeticionSize:"+colEstadoPsPeticion.size());
    
		if (colEstadoPsPeticion.size () == 0)
		{
			errorType = OK ;
    
		}
    
		else
		{
			Estado_ps_peticionLocal estadoPsPeticion =  (Estado_ps_peticionLocal) (colEstadoPsPeticion.iterator ().next ()) ;
        
			Integer codEstadoCierre = estadoPsPeticion.getCod_estado_cierre () ;
        
			if (codEstadoCierre == null || codEstadoCierre.intValue () == ComunInterfaces.estadoCierreOk)
			{
				errorType = OK ;
            
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
            
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError(estadoPsPeticion.getCod_causal ().toString ()) ;

					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;

					llaveCausa.cod_causal = codigoCausal ;

					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;

					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
        
			else if (codEstadoCierre.intValue () == ComunInterfaces.estadoCierreNovedad)
			{
				errorType = OK_NOVEDAD ;
            
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
            
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError (estadoPsPeticion.getCod_causal ().toString ()) ;

					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;

					llaveCausa.cod_causal = codigoCausal ;

					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;

					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
        
			else if (codEstadoCierre.intValue () == ComunInterfaces.estadoCierreError)
			{
				errorType = NO_OK ;
            
				Long codigoCausal = estadoPsPeticion.getCod_causal () ;
            
				if (codigoCausal != null)
				{
					subRequest1.setSubRequestCodeError (estadoPsPeticion.getCod_causal ().toString ()) ;
                
					Catalogo_causalKey llaveCausa = new Catalogo_causalKey () ;
                
					llaveCausa.cod_causal = codigoCausal ;
                
					Catalogo_causalLocal catalogCausa = catalogo_causalHome.findByPrimaryKey (llaveCausa) ;
                
					subRequest1.setSubRequestDescriptionError (catalogCausa.getDescripcion_causal ()) ;
				}
				else
				{
					subRequest1.setSubRequestCodeError ("") ;
					subRequest1.setSubRequestDescriptionError ("") ;
				}
			}
        
			// TODO ver si seria mejor mandar una excepcion
			else
			{
				errorType = OK ;
			}
		}
    
		subRequest1.setSubRequestTypeError (errorType) ;
    
    
		if (OK.equals (errorType))
		{
		   subRequest1.setSubRequestCodeError ("") ;
		   subRequest1.setSubRequestDescriptionError ("") ;
		}
		// TODO ver como llenar
		// subRequest1.setReservedEquipmentCode () ;
    
		subRequest1.setSubRequestEndTime(new Fecha().getTimestamp()) ;
	}
	
	private boolean esAgrupacionDeTraslado(Agrupacion_atisLocal agrupacion)
	{
	   for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
	   {
		   Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
		   Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) subpeticion_atisLocal.getProducto_servicio_peticion().iterator().next();
		   Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
		   if(operacion_comercialLocal.getOpco_tras()!=null)
		   {
			   if(operacion_comercialLocal.getOpco_tras().equals("T"))
				   return true;
		   }
	   }
	   return false;
	}
	
	private ArrayList retornaTiposOperAgrupacion(Agrupacion_atisLocal agrupacion)
	{
		ArrayList retorno=new ArrayList();
		for(Iterator iterator=agrupacion.getSubpeticion_atis().iterator();iterator.hasNext();)
		{
			Subpeticion_atisLocal subpeticion_atisLocal=(Subpeticion_atisLocal) iterator.next();
			Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) subpeticion_atisLocal.getProducto_servicio_peticion().iterator().next(); 
			Operacion_comercialLocal operacion_comercialLocal=producto_servicio_peticionLocal.getOperacion_comercial();
			if(operacion_comercialLocal.getOpco_tras()!=null)
			{
				if(operacion_comercialLocal.getOpco_tras().equals("T"))
				{
					if(operacion_comercialLocal.getOpco_tipo()!=null)
						retorno.add(operacion_comercialLocal.getOpco_tipo());
				}
			}
		}
		return retorno;
	}
	
	private static final String OK = "00" ;
	private static final String OK_NOVEDAD = "04" ;
	private static final String NO_OK = "08" ;
}
