package com.telefonica_chile.vpistbba.grabacion;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Ciclos_facturacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionDelegate;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.PuntosCreLocalHome;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocal;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocalHome;
import com.telefonica_chile.comun.ComunInterfaces;

public abstract class DefaultGrabador implements Grabador {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
//	protected transient Logger log = LoggerFactory.getLogger(getClass());
//	
//	protected HttpServletRequest request = null;
//	protected String idProceso = null;
//	protected String instanciaProceso = null;
//	protected String codigoActividad = null;
//	protected String instanciaActividad = null;
//	
//	//By Pa-T!, el ídolo pop por antonomasia
//	private boolean terminarActividad = true;
//	
//	
//	protected abstract IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx;
//
//	public final void grabar(HttpServletRequest request) {
//		//log.debug("Usuario de cierre : " + request.getAttribute("USUARIO_CIERRE"));
//		this.request = request;
//		this.setCamposActividadFromRequest(request);
//		//Lo primero es ejecutar la lógica...
//		this.ejecutarLogica(request);
//		if (this.isTerminarActividad()) {
//			String msgErr = "";
//			try {
//				IActividadEJB iActiv = this.getActividad();
//				msgErr += iActiv.getCodigoActividad() + "," +
//					iActiv.getNumeroPeticion() + "," +
//					iActiv.getActImplCorrelID() ;
//		
//				Long id_usuario = (Long) request.getAttribute("USUARIO_CIERRE");
//				iActiv.setIdUserCierre( id_usuario );
//				iActiv.terminar();
//			} catch (TnProcesoExcepcion e) {
//				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
//			} catch (ATiempoAppEx e) {
//				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
//			}
//		}
//		else
//		{
//			log.debug("\n<=============ACTIVIDAD NO TERMINADA=============>");
//		}
//	
//	}
//	public final void grabarSinTerminar(HttpServletRequest request) {
//			this.request = request;
//			this.setCamposActividadFromRequest(request);
//			//Lo primero es ejecutar la lógica...
//			this.ejecutarLogicaSinTerminar(request);
//	}
//
//	protected void setCamposActividadFromRequest(HttpServletRequest request) {
//		this.idProceso = request.getParameter("template");
//		this.instanciaActividad = request.getParameter("instanciaActividad");
//		this.instanciaProceso = request.getParameter("instanciaProceso");
//		this.codigoActividad = 	request.getParameter("codigoActividad");
//	}
//
//	protected abstract void ejecutarLogica(HttpServletRequest request);
//	protected abstract void ejecutarLogicaSinTerminar(HttpServletRequest request);
//
//
//	/* (non-Javadoc)
//	 * @see com.telefonica_chile.atiempo.Grabador#getRequest()
//	 */
//	public HttpServletRequest getRequest() {
//		return this.request;
//	}
//
//	/**
//	 * @return
//	 */
//	public boolean isTerminarActividad() {
//		return terminarActividad;
//	}
//
//	/**
//	 * @param b
//	 */
//	public void setTerminarActividad(boolean b) {
//		terminarActividad = b;
//	}
//
//	private PublicadorBandejaLocal getPublicadorBandeja() throws NamingException, CreateException, RemoteException {
//		PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
//		return publicadorHome.create();
//	}

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected HttpServletRequest request = null;
	protected String idProceso = null;
	protected String instanciaProceso = null;
	protected String codigoActividad = null;
	protected String instanciaActividad = null;
	private String observacion="";
	
	protected Map datosModificados = new HashMap();

	
	//By Pa-T!, el ídolo pop por antonomasia
	private boolean terminarActividad = true;
	
	private List cods_cancelar = null;
	
	
	protected abstract IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx;

	public final void grabar(HttpServletRequest request) {
		
		//log.debug("Usuario de cierre : " + request.getAttribute("USUARIO_CIERRE"));
		this.request = request;
		this.setCamposActividadFromRequest(request);
		
		//Lo primero es ejecutar la lógica...
		this.ejecutarLogica(request);
		if (this.isTerminarActividad()) {
			String msgErr = "";
			try {
				IActividadEJB iActiv = this.getActividad();
				String tipo_canal = null;
				String tipo_cre[] = null;
				String fecha = null;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar c = Calendar.getInstance();
		        PeticionLocalHome peticion = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		        PeticionLocal peticionlocal = null;
		        DecoModemPeticionLocalHome home = (DecoModemPeticionLocalHome) HomeFactory.getHome(DecoModemPeticionLocalHome.JNDI_NAME);
		        String mes = null;
				DBManager dbSeq = new DBManager ();
				dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
				Long numPeti = new Long(request.getParameter("folio").toString());
				if(request.getParameter("codigoActividad")!= null && 
						(request.getParameter("codigoActividad").equals(ComunInterfaces.ACT_DESINSTALAR_CRE)
						|| request.getParameter("codigoActividad").equals(ComunInterfaces.ACT_DESINSTALAR_MTZ))){
					LocalidadLocalHome localidadHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
					peticionlocal = peticion.findByPrimaryKey(new PeticionKey(numPeti));
					LocalidadLocal localidadLocal = localidadHome.findByCod_loc(peticionlocal.getCod_ext_loc_cd());
					Ciclos_facturacionLocalHome cicloshome = (Ciclos_facturacionLocalHome) HomeFactory.getHome(Ciclos_facturacionLocalHome.JNDI_NAME);
					if(localidadLocal.getCre()!= null)
						tipo_cre = localidadLocal.getCre().split(",");
					if(tipo_cre[0].equals("1"))
						tipo_canal = "CRE";
					if(tipo_cre[0].equals("2"))
						tipo_canal = "Centro Mensajeria";
					if(tipo_cre[0].equals("3"))
						tipo_canal = "Call center Interrapisimo";
					PuntosCreLocalHome puntoCRE = (PuntosCreLocalHome) HomeFactory.getHome(PuntosCreLocalHome.JNDI_NAME);
					PuntosCreLocal puntoCRELocal = puntoCRE.findbyPk(tipo_canal,localidadLocal.getNombre_provincia(),localidadLocal.getNombre_localidad());
					long consecutivo = dbSeq.seqNextValLong ("CORRELATIVO_DECOMODEM_PETICION");
					DecoModemPeticionLocal local = home.create(new Long(consecutivo));
					local.setPeticion(numPeti.toString());
					local.setId_act(new Long(request.getParameter("idactividad").toString()));
					local.setHorariospuntaten(puntoCRELocal.getHorario());
					local.setDireccionpuntaten(puntoCRELocal.getDireccion());
					local.setCiclo(new Long(peticionlocal.getInf_cicl_fac()));
					Ciclos_facturacionLocal ciclosLocal = cicloshome.findByPrimary(Integer.parseInt(peticionlocal.getInf_cicl_fac()));
					if(peticionlocal.getPeti_id_instancia().equals("TV"))
						local.setNum_doc("TV LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_TV"));
					if(peticionlocal.getPeti_id_instancia().equals("LBBA"))
						local.setNum_doc("BA LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_BA"));
					if(peticionlocal.getPeti_id_instancia().equals("LBBATV"))
						local.setNum_doc("TV LIR"+dbSeq.seqNextValLong ("CORRELATIVO_REFERENCIA_PETICION_TRIO"));
					try {
						c.setTime(df.parse(peticionlocal.getPeti_fecha_ingreso().toString().substring(0,10)));
					} catch (ParseException e) {
						// TODO Bloque catch generado automáticamente
						log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
					}
					if(c.get(Calendar.MONTH)+1>=12) {
						mes = "1";
					} else
						mes = ""+(peticionlocal.getPeti_fecha_ingreso().getMonth()+2);
					fecha = ciclosLocal.getDia_final()+"-"+mes+"-"+c.get(Calendar.YEAR);
					local.setFechamaxentrega(fecha);
					local.setEstadopet(new Long(1));
				}
				
				if(request.getParameter("codigoActividad")!= null 
						&&( request.getParameter("codigoActividad").equals(ComunInterfaces.ACT_CONTROL_DESINSTALAR_CRE)
								||request.getParameter("codigoActividad").equals(ComunInterfaces.ACT_CONTROL__DESINSTALAR_MTZ))){
					String horario = null;
					String dirreccion = null;
					String fechaMax = null;
					Long ciclo = null;
					Long idAct = null;
					String registro[]= null;
					String nomDoc = null;
					peticionlocal = peticion.findByPrimaryKey(new PeticionKey(numPeti));
					if(request.getParameter("documentos_entregados")!= null && request.getParameter("documentos_entregados")!= ""){
						log.debug("Guardando Documentos para la peticiòn");
						String documento[]= request.getParameter("documentos_entregados").split(",");
						peticionlocal.setFecha_entrega(new Fecha(documento[1]+":00","dd/MM/yyyy HH:mm:ss").getTimestamp());
						peticionlocal.setNombre_entrega(documento[2]);
						peticionlocal.setCedula(documento[3]);
						peticionlocal.setEstrategia(documento[4]);
						peticionlocal.setTipo_desconcilia(documento[5]);
					}
					log.debug("Entro a guardar datos de la peticiòn"+numPeti);
					if(request.getParameter("elemen_adicionales")!= null)
						registro= request.getParameter("elemen_adicionales").split(",");
					Collection local = home.findByPeticion(numPeti.toString());
					for(Iterator listaPet = local.iterator();listaPet.hasNext();){
						DecoModemPeticionLocal petLocal = (DecoModemPeticionLocal) listaPet.next();
						horario = petLocal.getHorariospuntaten();
						dirreccion = petLocal.getDireccionpuntaten();
						fechaMax = petLocal.getFechamaxentrega();
						ciclo = petLocal.getCiclo();
						idAct = new Long(request.getParameter("idactividad").toString());
						nomDoc = petLocal.getNum_doc();
						petLocal.remove();
					}
					int i = 0;
					log.debug("Guardando decos y mòdems para la peticiòn");
					DecoModemPeticionLocal petLocal = null;
					while(i<registro.length && registro.length>=6){
						if(i%6==0){
							long consecutivo = dbSeq.seqNextValLong ("CORRELATIVO_DECOMODEM_PETICION");
							petLocal = home.create(new Long(consecutivo));
						}
						petLocal.setSerial(registro[i]);
						petLocal.setMarca(registro[++i]);
						petLocal.setModelo(registro[++i]);
						petLocal.setCastID(registro[++i]);
						petLocal.setDescripcion(registro[++i]);
						petLocal.setTipo(new Long(registro[++i]));
						i++;
						petLocal.setCiclo(ciclo);
						petLocal.setDireccionpuntaten(dirreccion);
						petLocal.setFechamaxentrega(fechaMax);
						petLocal.setHorariospuntaten(horario);
						petLocal.setId_act(idAct);
						petLocal.setPeticion(numPeti.toString());
						petLocal.setNum_doc(nomDoc);
						
					}
					
					log.debug("Guardando Accesorios para la peticiòn");
					i=0;
					String registroAccesorios[]= null;
					if(request.getParameter("acces_adicionales")!= null && request.getParameter("acces_adicionales")!= "")
						registroAccesorios= request.getParameter("acces_adicionales").split(",");
					while(i<registroAccesorios.length&&registroAccesorios.length>=5){
						if(i%5==0){
							long consecutivo = dbSeq.seqNextValLong ("CORRELATIVO_DECOMODEM_PETICION");
							petLocal = home.create(new Long(consecutivo));
						}
						petLocal.setModelo(registroAccesorios[i]);
						petLocal.setSerial(registroAccesorios[++i]);
						petLocal.setMarca(registroAccesorios[++i]);
						//petLocal.setCastID(registro[++i]);
						petLocal.setDescripcion(registroAccesorios[++i]);
						petLocal.setTipo(new Long(registroAccesorios[++i]));
						i++;
						petLocal.setCiclo(ciclo);
						petLocal.setDireccionpuntaten(dirreccion);
						petLocal.setFechamaxentrega(fechaMax);
						petLocal.setHorariospuntaten(horario);
						petLocal.setId_act(idAct);
						petLocal.setPeticion(numPeti.toString());
						petLocal.setNum_doc(nomDoc);
						
					}
					grabaControlVisita(request);
				}
				ActividadEJBDTO actDto = iActiv.getActividadEJBDTO(new Long(this.instanciaProceso),this.codigoActividad,this.instanciaActividad,null);
				
				String observaciones = this.observacion+request.getParameter("observaciones");
				if(request.getParameter("usuariobound")!=null)
					observaciones+=":Realizado por "+request.getParameter("usuariobound");
				log.debug("observaciones:" +observaciones);
				String causa = request.getParameter("causa");
				Long idCausa = null;
				try {
					idCausa = new Long(causa);
				} catch (NumberFormatException e) {
				}

				actDto.setObservacion( observaciones );
				actDto.setIdCausaCierre( idCausa );
		
//				IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, null);
//				GrabadorComun.setMapDatosFromRequest(actividad, this.getRequest());
				for (Iterator iter = datosModificados.keySet().iterator(); iter.hasNext();) {
					String o = (String) iter.next();
					actDto.setDato(o, (String) datosModificados.get(o));
				}
				
				msgErr += actDto.getCodigoActividad() + "," +
					actDto.getNumeroPeticion() + "," +
					actDto.getActImplCorrelID() ;
				
				Long id_usuario = (Long) request.getAttribute("USUARIO_CIERRE");
				actDto.setIdUsuarioCierre( id_usuario );

				//CR26362 - adocarmo - inicio
				/* 
				 * CR 00024071 - 2009/04/22 - 4
				 * 		Control sobre cancelación de trasladoSoloBA - German P.
				 */
				/*
				String idCierre = request.getParameter("cierre");
				
				cargarCodigosCancelacion();
				Integer nrCierre = new Integer(idCierre);
				if (cods_cancelar.contains(nrCierre)){ 
					// la opcion es "Cancelar" -> pregunto si es trasladoSoloBA
					try{
						Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
						Traslado_solobaLocal traslado_solobaLocal = null;
						try{
							// pregunto si es peticion_alta
							traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(actDto.getNumeroPeticion());
							traslado_solobaLocal.setPeti_baja(null);
						} catch (FinderException e) {
							// no es peticion_alta -> pregunto si es peticion_baja
							try{
								traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(actDto.getNumeroPeticion());
								traslado_solobaLocal.setPeti_alta(null);
							} catch (FinderException e1) {
								// no es soloBA, no hago nada
							}
						}
					}catch(NamingException e){
						log.error("",e);
					}
				}				
				*/
				//CR26362 - adocarmo - fin
				
				
				
				iActiv.terminar(actDto);
			} catch (TnProcesoExcepcion e) {
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			} catch (ATiempoAppEx e) {
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			}catch (NamingException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			} catch (NumberFormatException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			} catch (CreateException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			}catch (FinderException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			}catch (EJBException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			} catch (RemoveException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			} catch (FechaException e) {
				// TODO Bloque catch generado automáticamente
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e);
			}
		}
		else
		{
			log.debug("\n<=============ACTIVIDAD NO TERMINADA=============>");
		}
	
	}
	/**
	 * @param request2
	 */
	private void grabaControlVisita(HttpServletRequest request2) {
		// TODO Apéndice de método generado automáticamente
		try
		{
			ArrayList listaVisitas = new ArrayList();

			//Gustavo - CR 25403

			String numVisitas = (request.getParameter("numeroVisitas"));	
			log.debug("Numero de Visitas " + numVisitas);
			if(numVisitas!=null){
					for(int i=1;i<=new Integer(numVisitas).intValue();i++){
						if(request.getParameter("fechaHoraVisitaDesde_"+i)!=null){
							Long petiNumero = new Long(request.getParameter("folio"));
							Fecha fechaHoraVisitaDesde=new Fecha(request.getParameter("fechaHoraVisitaDesde_"+i),"dd/MM/yyyy HH:mm");
							Fecha fechaHoraVisitaHasta=new Fecha(request.getParameter("fechaHoraVisitaHasta_"+i),"dd/MM/yyyy HH:mm");
							Long psPeticion = new Long(request.getParameter("psPet_"+i));
							String codTecnico = request.getParameter("tecnico_"+i);
							Long codCauDem = new Long(request.getParameter("causademora_"+i));
							log.debug("Llene toda la informacion ");
							log.debug("Peticion " + petiNumero);
							log.debug("psPeticion " + psPeticion);
							log.debug("fechaHoraVisitaDesde " + fechaHoraVisitaDesde);
							log.debug("fechaHoraVisitaHasta " + fechaHoraVisitaHasta);
							log.debug("tecnico " + codTecnico);
							log.debug("causademora " + codCauDem);
							InformacionControlVisitaDTO informacionControlVisitaDto = new InformacionControlVisitaDTO();
							informacionControlVisitaDto.setPeticion(petiNumero);
							informacionControlVisitaDto.setFechaLlegada(fechaHoraVisitaDesde);
							informacionControlVisitaDto.setFechaSalida(fechaHoraVisitaHasta);
							informacionControlVisitaDto.setCodigoTecnico(codTecnico);
							informacionControlVisitaDto.setCodigoPs(psPeticion.toString());
							informacionControlVisitaDto.setCodCausaDemora(codCauDem.toString());
							listaVisitas.add(informacionControlVisitaDto);
						}
						else{
							log.debug("No estan registradas las visitas");
						}
					}
					InstalacionServicesInterface servicesInterfaces = new InstalacionDelegate();
					servicesInterfaces.grabarControlVisita(listaVisitas);
			}
			//Gustavo - CR 25403 - Fin
		}
		catch(Exception e)
		{
			log.debug(e);
		}
	}

	public final void grabarSinTerminar(HttpServletRequest request) {
			this.request = request;
			this.setCamposActividadFromRequest(request);
			//Lo primero es ejecutar la lógica...
			this.ejecutarLogicaSinTerminar(request);
	}

	protected void setCamposActividadFromRequest(HttpServletRequest request) {
		this.idProceso = request.getParameter("template");
		this.instanciaActividad = request.getParameter("instanciaActividad");
		this.instanciaProceso = request.getParameter("instanciaProceso");
		this.codigoActividad = 	request.getParameter("codigoActividad");
	}

	protected abstract void ejecutarLogica(HttpServletRequest request);
	protected abstract void ejecutarLogicaSinTerminar(HttpServletRequest request);


	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.Grabador#getRequest()
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * @return
	 */
	public boolean isTerminarActividad() {
		return terminarActividad;
	}

	/**
	 * @param b
	 */
	public void setTerminarActividad(boolean b) {
		terminarActividad = b;
	}

	private PublicadorBandejaLocal getPublicadorBandeja() throws NamingException, CreateException, RemoteException {
		PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
		return publicadorHome.create();
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}
	
	private void cargarCodigosCancelacion(){
		if (cods_cancelar == null){
			cods_cancelar = new ArrayList();
			cods_cancelar.add(new Integer(36));
			cods_cancelar.add(new Integer(94));
			cods_cancelar.add(new Integer(95));
			cods_cancelar.add(new Integer(109));
		}
	}

}
