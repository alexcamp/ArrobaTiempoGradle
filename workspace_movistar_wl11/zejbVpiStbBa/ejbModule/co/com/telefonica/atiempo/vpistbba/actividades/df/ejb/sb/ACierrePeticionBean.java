package co.com.telefonica.atiempo.vpistbba.actividades.df.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey;
import co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal;
import co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ACierrePeticion
 */
public class ACierrePeticionBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	log.debug("Cerrando Peticion [" + act.getNumeroPeticion().toString() + "]");

	try {

		
		PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
		boolean esSVATempUnico= peticionesDelegate.esSVATemp(act.getNumeroPeticion());
		if(esSVATempUnico && act.getPsOk().size() == 1){
//			Requerimiento Try And Buy Cesar Remigio
			log.debug("Se inhibe por tener un unìco PS con OC que no se informa");
			act.setObservacion("Se inhibe por tener un unìco PS con OC que no se informa");
			act.setRealizarTerminoInmediato(true);
			
		}else{
		
			peticionesDelegate.cerrarPeticion(act.getNumeroPeticion());
			
		}
		//REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 25/07/2014
		actualizarEspeInstalacionesVIP(act);
		//FIN REQ AVERIAS DE INFANCIA Y REITERADAS
		
	} catch (ATiempoAppEx e) {
		log.debug("Error en Cierre.",e);
		throw new TnProcesoExcepcion("ATiempoAppEx",e);
	}
	log.debug("Peticion Cerrada [" + act.getNumeroPeticion().toString() + "]");

	// FRANCOIS genera el mensaje, aqui solo se hace la llamda a su metodo con el numero de peticion.		

		/**
		 * 0.- Si la peticion de esta actividad no es de tipo AT, 
		 * simplemente termino.
		 */
//
//		Integer estPet = null;
//		String idTica = "";
//		Long idPeticion = this.getNumeroPeticion();

		//		try {
		//
		//			this.setObservacion( "Cerrando Peticion OK" );
		//			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		//			PeticionLocal peticion = peticionHome.findByPrimaryKey(idPeticion);
		//			
		//			boolean reversa = false;
		//			if (this.containsKeyDato("FLUJ_REVERSA") && !this.getDato("FLUJ_REVERSA").equals("")) {
		//				reversa = true;
		//			}
		//
		//			peticion.setFechaModificacion(new Date());
		//			if ( peticion.getFechaTermino() == null )
		//				peticion.setFechaTermino(new Date());
		//			idTica = peticion.getIdTica();
		//  			
		//			Integer idEstado = peticion.getIdEspe();
		//			
		//			EstadoPeticionLocalHome estadoPetHome = (EstadoPeticionLocalHome) HomeFactory.getHome(EstadoPeticionLocalHome.JNDI_NAME);
		//			
		//			if (reversa) {
		//				estPet = new Integer(3);
		//				if ( peticion.getIdEspe()!=null && ( peticion.getIdEspe().intValue()==8 || peticion.getIdEspe().intValue()==9) )
		//					estPet = new Integer(9);
		//				peticion.setEstadopeticion( estadoPetHome.findByPrimaryKey( estPet ) );
		//			}
		//			else{
		//				estPet = new Integer(2); 
		//				peticion.setEstadopeticion(estadoPetHome.findByPrimaryKey( estPet ));
		//				
		//				/******** SE SETEA EL CAMPO REALIZADO, SEGUN TIPO ALTA O BAJA *********/
		//				ProductoServicioPeticionLocalHome prodServPetHome = 
		//					(ProductoServicioPeticionLocalHome) HomeFactory.getHome(
		//						ProductoServicioPeticionLocalHome.JNDI_NAME);
		//				Collection productos = prodServPetHome.findByProdServPet_Variacion(idPeticion);
		//
		//				OperacionComercialLocalHome operComPetHome = 
		//					(OperacionComercialLocalHome) HomeFactory.getHome(
		//						OperacionComercialLocalHome.JNDI_NAME);
		//											
		//				for (Iterator iter = productos.iterator(); iter.hasNext();) {
		//					ProductoServicioPeticionLocal element = (ProductoServicioPeticionLocal) iter.next();
		//					
		//					try{
		//						OperacionComercialLocal operComPet = 
		//							operComPetHome.findByTipo(element.getIdOperacionComercial());
		//		
		//						if (operComPet.getTipo().equals("A")){
		//							element.setRealizado(new Byte("1"));		
		//						}
		//						else{
		//							element.setRealizado(new Byte("-1"));
		//							/*** BUSCA LA ALTA DE LA BAJA ***/
		////							Collection prodServPeticiones = 
		////								prodServPetHome.findByIdPS_IdLinea(element.getIdProductoServicio(),peticion.getIdLinea());
		////							for (Iterator iter1 = prodServPeticiones.iterator(); iter1.hasNext();) {
		////								ProductoServicioPeticionLocal prodServPet = (ProductoServicioPeticionLocal) iter1.next();
		////								prodServPet.setRealizado(new Byte("-2"));
		////							}
		//						}
		//					} catch (FinderException e) {
		//						//NO ES DE BAJA NI ALTA, NO ES ERROR!!!
		//					}
		//				}
		//			}
		//			
		//			PeticionFlujoLocalHome petFlujoHome = 
		//				(PeticionFlujoLocalHome)HomeFactory.getHome
		//					(PeticionFlujoLocalHome.JNDI_NAME);
		//			Collection c=null;
		//			
		//			try {
		//				c = petFlujoHome.findByActividadEstado(idPeticion, new Integer(5));
		//			} catch (FinderException e) {
		//				log.debug("La Peticion" + idPeticion + " ya paso por Instalacion.");
		//			}
		//
		//			idTica = peticion.getIdTica();
		//			
		//			if ( (c==null || c.isEmpty()) && !reversa ) {
		//				//no paso por instalacion y no viene de Cancelacion
		//				log.debug("Mandar Mensaje Termino");
		//				String estado = "TE";
		//				
		//				// Tengo que determinar si hay que enviar el mensaje de Termino.
		//				// Caso en que la Baja ya se aplicó al Inicio del Flujo.
		//				boolean enviarMensaje = true;
		//				if (idEstado!=null && idEstado.intValue()==10) { // Ya se actualizo el Termino.
		//					enviarMensaje = false;
		//				}
		//				if ( enviarMensaje ) {
		//					AnulacionPeticionMACLocalHome avisoHome = 
		//						(AnulacionPeticionMACLocalHome)HomeFactory.getHome(AnulacionPeticionMACLocalHome.JNDI_NAME);
		//					AnulacionPeticionMACLocal aviso = avisoHome.create();
		//					aviso.termino(idPeticion,estado);
		//				}
		//	  			
		//				if ( (idTica.substring(0,1)).equals("B") ){
		//					
		//					try{
		//						//Actualizacion AFAC
		//						ActualizacionAFACLocalHome homeAfac = (ActualizacionAFACLocalHome) HomeFactory.getHome(ActualizacionAFACLocalHome.JNDI_NAME);            
		//						ActualizacionAFACLocal localAfac = homeAfac.create();
		//						localAfac.actualizarRecursos(super.getNumeroPeticion(), new Long("213"));//idActividad Cierre Peticion
		//
		//						//Liberacion APEL
		//						if ( !"S".equals(VpistbbaConfig.getVariable("APEL")) ) {
		//							ActualizacionApelLocalHome home = (ActualizacionApelLocalHome) HomeFactory.getHome(ActualizacionApelLocalHome.JNDI_NAME);            
		//							ActualizacionApelLocal local = home.create();
		//							local.actualizarRecursos(super.getNumeroPeticion(), new Long("213"));
		//						}
		//					} catch (Exception e) {
		//						log.fatal("ERROR al actualizar la peticion en APEL y AFAC.", e);
		//					}
		//				}
		//			}
		//  			
		//			// Obtengo el Tipo de Variacion que Hubo.
		//			peticion.setCausalBaja("-O-");
		//
		//			/*
		//			 * Ahora solo se libera la BT cuando es una cancelacion.
		//			 */
		//			if ("AT".equals(idTica) && reversa) {
		//
		////				*
		////				 * 1.- Busca la petición BT asociada, 
		////				 *     esperando que esté en la actividad "Espero Desbloqueo"
		////				 *
		//
		//				// Busco el otro número de peticion para este mismo cliente
		//				Long lineaDeBaja = peticion.getIdLineaTraslado();
		//
		//				PeticionLocal peticionBT = null;
		//				try {
		//					peticionBT = peticionHome.findByIdLineaBaja(lineaDeBaja);
		//					if ( !"BT".equals(peticionBT.getIdTica()) ) {
		//						log.info("No se Encontro BT [" + idPeticion + "," + lineaDeBaja + "]");
		//						this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//						return;
		//					}
		//				} catch (FinderException ex) {
		//					String error = "No se Encontro BT [" + idPeticion + "," + lineaDeBaja + "] :" + ex.getMessage();
		//					log.warn(error);
		//					this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//					return;
		//				}
		//
		//				//TODO: Verificar que la TICA de la linea trasladada es BT
		//				Long numeroPeticionBT = (Long) peticionBT.getPrimaryKey();
		//
		////				
		////				 * 2.- Envía el mensaje al workflow para terminar la tarea "Espero Desbloqueo"
		////				 *     que estaba pendiente
		////				 * 
		////				 * 3.- Despublico de la bandeja si la tarea "Espero Desbloqueo" asociada estaba
		////				 *     publicada en la bandeja de alguien
		////				 * 
		////				 * Estas 2 cosas se hacen recuperando la WfInstanciaActividad para el proceso de
		////				 * Baja de linea asociado a la BT y terminando esa actividad que había quedado
		////				 * pendiente.
		////				 *
		////				
		//
		//				CerrarPeticionesLocalHome cierraHome =
		//					(CerrarPeticionesLocalHome) HomeFactory.getHome(CerrarPeticionesLocalHome.JNDI_NAME);
		//				CerrarPeticionesLocal cerrar = cierraHome.create();
		//							
		//				// Si "EsperoDesbloqueo" es una actividad manual, el sólo hecho de terminarla la despublica
		//				cerrar.desbloquearActividad(numeroPeticionBT, reversa);
		//				
		//				// 
		//				// Si "Cierre Peticion" es una tarea automatica, no hace falta codigo adicional
		//				// porque no es publicada en la bandeja
		//				//
		//				log.debug(
		//					"Terminado el Cierre Peticion AT para peticion #"
		//						+ idPeticion);
		//			}
		//
		//			this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//			//eliminarRegistroWfInstanciaActividad(idInstanciaProceso);
		//
		//			// Actualizamos APEL cuando la Peticion es NN y no esta cancelada.
		//			if ("S".equals(VpistbbaConfig.getVariable("APEL")) && ("NN".equals(idTica) && !reversa) ) {
		//				//Cambiar numero de APEL.
		//				DatosApelDTO datApel = new DatosApelDTO();
		//				// Buscamos los Datos de la Linea.
		//				Long idLinea = peticion.getIdLinea();
		//				Long idLineaTr = peticion.getIdLineaTraslado();
		//				if ( idLineaTr == null ) {
		//					log.error("Peticion NN sin Linea Traslado [" + idPeticion + "]");
		//					return;
		//				}
		//				
		//				DatosLineaLocalHome lHome = (DatosLineaLocalHome) HomeFactory.getHome(DatosLineaLocalHome.JNDI_NAME);
		//				DatosLineaLocal lLocal = lHome.create();
		//				DTOLinea dtoLinea = lLocal.getDatosLinea( idLinea );
		//				Long idZona = dtoLinea.getIdZona();
		//				String cn = dtoLinea.getCentral();
		//				String zn = "";
		//				zn = (idZona == null) ? "00" : ""+idZona;
		//				
		//				DTOLinea dtoLineaTr = lLocal.getDatosLinea( idLineaTr );
		//				datApel.setArea( dtoLinea.getCodigoArea() );
		//				datApel.setFono( dtoLinea.getNumero() );
		//				datApel.setAreaNuevo( dtoLineaTr.getCodigoArea() );
		//				datApel.setFonoNuevo( dtoLineaTr.getNumero() );
		//				datApel.setTipoConsulta( "2" );
		//				datApel.setZona( zn );
		//				datApel.setCentral( cn );
		//				String os = "";
		//				Long idOS = peticion.getNroOrdenServicio();
		//				os = (idOS == null) ? "" : ""+idOS;
		//				
		//				
		//				datApel.setNroOS( os );
		//				datApel.setTica( "NN" );
		//				ApelSessionLocalHome apelHome = (ApelSessionLocalHome) HomeFactory.getHome(ApelSessionLocalHome.JNDI_NAME);
		//				ApelSessionLocal apelLocal = apelHome.create();
		//				apelLocal.actualizaPar(datApel, "M");
		//			}
		//			
		//		} catch (NamingException e) {
		//			this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//			log.debug("NamingException No se pudo CerrarACtividad [" + idPeticion +"]");
		//		} catch (CreateException e) {
		//			this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//			log.debug("CreateException No se pudo CerrarACtividad [" + idPeticion +"]", e);
		//		} catch (FinderException e) {
		//			this.setDato("FLUJ_CIERRE", "S"); //Termina la Peticion
		//			log.debug("FinderException No se pudo CerrarACtividad [" + idPeticion +"]:" + e.getMessage());
		//		} finally {
		//		}

//		log.info(
//			"Peticion Cerrada [Petico,Tica,Estado] ["
//				+ this.idInstanciaProceso
//				+ ","
//				+ idTica
//				+ ","
//				+ estPet
//				+ "]");
	}


// TODO: ¿En que momento se borra de esta tabla? ¿Es mejor en el momento del cierre de la peticion o en el momento del cierre de actividad? 
//	public void eliminarRegistroWfInstanciaActividad(Long numeroPeticion) {
//		try {
//			log.info(
//				"SE ELIMINARAN REGISTROS DE LA INSTANCIA "
//					+ numeroPeticion
//					+ " EN WF_INSTANCIA_ACTIVIDAD...");
//
//			WFSessionLocalHome wfHome =
//				(WFSessionLocalHome) HomeFactory.getHome(
//					WFSessionLocalHome.JNDI_NAME);
//			WFSessionLocal wfLocal = wfHome.create();
//
//			wfLocal.eliminarInstanciaActividadByPeticion(numeroPeticion);
//
//			log.info("...REGISTROS DE WF_INSTANCIA_ACTIVIDAD ELIMINADOS!!");
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (CreateException e) {
//			e.printStackTrace();
//		} catch (TnProcesoExcepcion e) {
//			e.printStackTrace();
//		}
//	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	
	//REQ AVERIAS DE INFANCIA Y REITERADAS @DCARDENA 25/07/2014
	//funcion que con sulta y actualzia el estado de la peticion en la tabla instalcione_vip
	public void actualizarEspeInstalacionesVIP (ActividadEJBDTO act){
			//try para manejar el finder excetion y el naming exception
		try{
			log.debug("se entra aconsultar la peticion "+act.getNumeroPeticion()+ " en la tabla instalaciones_vip");
			//instanciamos localhomes
			Instalaciones_VIPLocalHome instalaciones_VIPLocalHome = (Instalaciones_VIPLocalHome)HomeFactory.getHome(Instalaciones_VIPLocalHome.JNDI_NAME);
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			//consultamos la peticion en la tabla instalaciones_vip
			Instalaciones_VIPLocal instalaciones_VIPLocal = instalaciones_VIPLocalHome.findByPrimaryKey(new Instalaciones_VIPKey(act.getNumeroPeticion()));
			//si encontramos la peticion hay, consultamos los datos de la peticion 
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			//actualizamos el estado de la peticion en la talba instalaciones_vip
			instalaciones_VIPLocal.setEspe_id(peticionLocal.getEspe_id());
			log.debug("se actualiza el estado de la peticion "+act.getNumeroPeticion()+" en la tabla instalaciones_vip, con el estado"+peticionLocal.getEspe_id());
		}catch( FinderException e){
			log.info("no se encontro registro en la tabla instlaaciones_vip para la peticion"+act.getNumeroPeticion());
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.info("No se pudo instanciar el localHome" +e);
		}
	}
	//FIN REQ AVERIAS DE INFANCIA Y REITERADAS
	
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre,"S");
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		TODO: Envia el mensaje y no espera respuesta
		act.setRealizarTerminoInmediato(true);

	}
}
