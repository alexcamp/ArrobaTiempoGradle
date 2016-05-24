/*
 * Created on Feb 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.grabacion;


import java.util.ArrayList;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.ElementoDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.ElementoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocalHome;
import co.com.telefonica.atiempo.reportes.JasperReport;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionDelegate;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;

/**
 * @author jvelasco
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GrabadorControlInstalacion extends GrabadorComun {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */


	/* (non-Javadoc)
	 * @see com.telefonica_chile.vpistbba.grabacion.GrabadorBaseVPISTBBA#grabarDatosPropios(javax.servlet.http.HttpServletRequest)
	 */
	protected void grabarDatosPropios(HttpServletRequest request)
	{
		Long usua_id=null;
		// Aca implemento los datos a grabar de la apntalla de instalador.

		// Grabar los Datos del Tecnico.
		String codigoTecnico = request.getParameter("codigoTecnico");
		log.debug("Grabando Control Instalacion");
		
		grabarEquipos(request);
		if(request.getParameter("ginbound")==null)
			grabarModemsBa(request);
		grabaControlVisita(request);
		grabaNovedadAutomatica(request);
		if(request.getAttribute("Actividad")!= null && request.getAttribute("Actividad").equals("Control Desistalacion CRE / P. Recepcion"))
			grabadorCRE(request);
	}


	/**
	 * @param request
	 */
	private void grabadorCRE(HttpServletRequest request) {
		// TODO Apéndice de método generado automáticamente
		JasperReport reporte =  new JasperReport();
		
	}


	/**
	 * @param request
	 */
	private void grabaNovedadAutomatica(HttpServletRequest request)
	{
		if(request.getParameter("grabarNovedadAuto")!=null)
		{
			try
			{
				Long petiNumero=new Long(request.getParameter("folio"));
				RecursosTVInterfaces interfaces=new RecursosTVDelegate();
				interfaces.revisaYMarcaNovedadAutoTv(petiNumero);
			}
			catch (ATiempoAppEx e)
			{	
				log.error(e);
			}		
		}
		else
			log.info("No le corresponde guardar novedad auto tv");
	}


	private void grabaControlVisita(HttpServletRequest request)
	{
		//Grabacion de fecha de control de visita.
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
					
					//Gustavo - CR 25403 - Fin
			}
//					log.debug("Codigo Tecnico " + informacionControlVisitaDto.getCodigoTecnico());
//					log.debug("Causa Demora " + informacionControlVisitaDto.getCodCausaDemora());
//			if(request.getParameter("fechaHoraVisitaDesde")!=null && request.getParameter("fechaHoraVisitaHasta")!=null)
//			{
//				Fecha fechaHoraVisitaDesde=new Fecha(request.getParameter("fechaHoraVisitaDesde"),"dd/MM/yyyy HH:mm");
//				Fecha fechaHoraVisitaHasta=new Fecha(request.getParameter("fechaHoraVisitaHasta"),"dd/MM/yyyy HH:mm");
//				Long petiNumero=new Long(request.getParameter("folio"));
//				InstalacionServicesInterface servicesInterface =new InstalacionDelegate();
//				servicesInterface.grabarControlVisita(petiNumero,fechaHoraVisitaDesde,fechaHoraVisitaHasta);
//			}
		}
		catch(Exception e)
		{
			log.debug(e.getStackTrace());
			
		}
		
	}

	/**
	 * @param request
	 */
	private void grabarModemsBa(HttpServletRequest request)
	{
		try
		{
			boolean esWiFiUnico = false;
			
			if(request.getParameter("tengoModems")==null)
				return;
			
			if (request.getParameter("esWiFiUnicoText") != null)
				esWiFiUnico = new Boolean (request.getParameter("esWiFiUnicoText")).booleanValue();
			
			ArrayList listaModems=new ArrayList();
			if(request.getParameter("nroTelAsig")==null)
			{
				log.debug("No tengo Nro Telefono Asignado no puedo Grabar Modems");
				return;
			}
		
			Long nroTelAsig=new Long(request.getParameter("nroTelAsig"));
			Long petiNumero=new Long(request.getParameter("folio"));
			
			
			if(nroTelAsig==null || petiNumero==null || nroTelAsig.equals(""))
				return;
			for(int i=1;i<=new Integer(VpistbbaConfig.getVariable("nroModems")).intValue();i++)
			{
				if(request.getParameter("modem"+i)!=null)
				{
					short accion;
					
					String serialModem=request.getParameter("modem"+i);
					if(serialModem.equals("") || serialModem.length()==4)
						continue;
					
					if (!esWiFiUnico){
						accion=new Short(request.getParameter("accion_"+i)).shortValue();
					}else{
						accion=ComunInterfaces.accionWiFiUnico;
					}
					
					ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
					modemVpiDTO.setSerial(serialModem);
					modemVpiDTO.setAccion(accion);
					
					/*
					 *	CR - 00026148 - Jun 26, 2009
					 *		Se agrega Marca - German P.
					 */
					String modem_marca =request.getParameter("marca_"+i);
					if (modem_marca == null){
						modem_marca = "";
					}
					modemVpiDTO.setMarca(modem_marca);

					listaModems.add(modemVpiDTO);
				}	
			}
			RecursosBADelegate delegate=new RecursosBADelegate();
			delegate.grabaModemsBaVpi(petiNumero,nroTelAsig,listaModems);
		}
		catch(NumberFormatException numberFormatException)
		{
			log.debug("No tengo los valores numericos necesario para grabar modem");
			return;
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
		}
	}
	private void grabarEquipos(HttpServletRequest request)
	{   	    
	    Long nroTelAsig = new Long(0);
	   	boolean error=false;	   
	   
        try {
            
        	Long petiNumero=new Long(request.getParameter("folio"));
        
	    boolean exit = false;
	    String equipo = null;
	    ArrayList listaEquipos=new ArrayList();
	    String cantEquipos = request.getParameter("cantEquipos");
	    if(cantEquipos!=null){

            PeticionLocalHome  peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
                        
            PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(petiNumero));
            
            //AT-2544 - Correccion PDTI nroTelAsig = new Long(peticionLocal.getTel_cot_ds());
            try{
            	if(request.getParameter("nroTelAsig")!=null &&  !request.getParameter("nroTelAsig").equals("")){
                	nroTelAsig=new Long(request.getParameter("nroTelAsig"));
                }else if (peticionLocal.getNum_ide_nu_stb()!=null && peticionLocal.getNum_ide_nu_stb().length() > 0){
                	nroTelAsig= new Long(peticionLocal.getNum_ide_nu_stb());
            	}else{
            		String psIntEquipado=VpistbbaConfig.getVariable("PS_INTERNET_EQUIPADO");
    		    	String[] listaIntEquipado=psIntEquipado.split(",");
    			    
    		    	for(int i=1;i<=new Integer(cantEquipos).intValue();i++){
    				    for(int contPsIntEquipado=0;contPsIntEquipado<=listaIntEquipado.length-1;contPsIntEquipado++){
    				    	int psCobroIncInt=Integer.parseInt(listaIntEquipado[contPsIntEquipado]);
    				    	int psAux = new Integer(request.getParameter("ps_"+i)).intValue();
    				    	if ( psAux == psCobroIncInt){
    				    		if (peticionLocal.getTel_cot_ds() != null && peticionLocal.getTel_cot_ds().length()>0){
    				    			nroTelAsig= new Long(peticionLocal.getTel_cot_ds());
    				    		}else{
    				    			nroTelAsig= new Long("0");
    				    		}
    				    	}
    				    }		    	 
    				}
            	}
            }catch(NumberFormatException ex){
            	log.debug("Se asigna 0 al número de teléfono asignado por errores de casteo");
            	nroTelAsig= new Long("0");
            }
            	
	        int contador=0;
	        int encontrados = 0;
	        int i = 0;
	        while(!exit){
	        	equipo = request.getParameter("equipo_"+i);
	        	if (contador==40){//parche Error de Produccion 02/10/2009
	        	    log.debug("No se han podido grabar los equipos para la peticion.. "+ petiNumero+" validar que se encuentran los mismos se fuerza Exception");
	        	    error=true;
	        	    exit = true;        	    
	        	}
	        	if(equipo != null && !equipo.equals("tieneQuiebre")){             
	            	ElementoDTO e = new ElementoDTO();
	            	short accion = Short.parseShort(request.getParameter("acc_"+i)); 	            	
	            	e.setAccion(accion);	            	
	            	e.setSerial(request.getParameter("serial_"+i));
	            	String tipo = request.getParameter("tipo_"+i);  
	            	e.setPeti_numero(request.getParameter("folio"));
	            	/*fmendez - rq6895 - interfaz MM SAP*/
	            	if(request.getParameter("sapmat_"+i) != null)
	            		e.setMaterialSAP(request.getParameter("sapmat_"+i));
	            	
	            	if(request.getParameter("sapplant_"+i) != null)
	            		e.setCentroSAP(request.getParameter("sapplant_"+i));
	            	
	            	if(request.getParameter("sapstor_"+i) != null)
	            		e.setAlmacenSAP(request.getParameter("sapstor_"+i));
	            	
	            	if(request.getParameter("sapnumpur_"+i) != null)
	            		e.setNumDocComprasSAP(request.getParameter("sapnumpur_"+i));
	            	
	            	String numpos = request.getParameter("sapnumpos_"+i);
	            	if(numpos != null && !numpos.equals("")){
	            		e.setNumPosDocComprasSAP(Integer.parseInt(numpos));
	            	}
	            	if(request.getParameter("cellpho_"+i) != null)
	            		e.setNumCelular(request.getParameter("cellpho_"+i));
	            	/*Fin fmendez - rq6895 - interfaz MM SAP*/ 
	            	
	            	/*fmendez - rq5606 - Internet Movil*/
	            	if(request.getParameter("brand_"+i) != null)
	            		e.setMarca(request.getParameter("brand_"+i));
	            	
	            	if(request.getParameter("model_"+i) != null)
	            		e.setModelo(request.getParameter("model_"+i));	            
	            	/*Fin fmendez - rq5606 - Internet Movil*/
	            	
	            	if(tipo!=null && !tipo.equals("")){
	            		ElementoLocalHome elementoHome = null;
	            		ElementoLocal eq= null;
	            		String idPS = request.getParameter("ps_"+i);
	            		Ps_Tipo_EqLocalHome psTipoEqLocalHome = null;
	            		psTipoEqLocalHome = (Ps_Tipo_EqLocalHome) HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
	            		elementoHome = (ElementoLocalHome) HomeFactory.getHome(ElementoLocalHome.JNDI_NAME);
	            		log.debug("TIPO DE  equipos:.. "+ tipo);
	            		eq = (ElementoLocal) elementoHome.findByDescripcion(tipo);
	            		String tipo_eq = eq.getId_elemento().toString();
	        		
	            		Ps_Tipo_EqLocal ps_tipo_eqLocal = psTipoEqLocalHome.findTipoByPs(Long.parseLong(idPS));
	            		String tElemento = ps_tipo_eqLocal.getId_tipo_eq().toString();
	            		e.setTipo_equipo(tElemento);
	            		e.setTipo_elemento(new Long(tipo_eq));
	            		e.setPs(new Long(idPS));
	            		if(idPS !=null && !idPS.equals("")){
	            			Grpe_PsLocalHome grpeLocalHome = (Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
	            			Grpe_PsLocal grupo = grpeLocalHome.findGrupoByPS(new Long(idPS));
	            			if(grupo !=null){
	            				e.setTipo_inventario(grupo.getGrpe_id().toString());
	            			}else{
	            				e.setTipo_inventario("");
	            			}
	            		}else{
	            			e.setTipo_inventario("");
	            		}
	            	}else{
	            	    e.setTipo_equipo(""); 
	            	}	            	
	            	
	            	listaEquipos.add(e);
	            	encontrados++;
	            	
	            	if(encontrados == Integer.parseInt(cantEquipos)){
	            	    exit = true;
	            	}
	        	}
	        	i++;
	        	contador++;
	    	}
	    }		
		try {
		    //parche Error de Produccion 02/10/2009
		    if (!error){
			    EquipoDelegate eDelegate = new EquipoDelegate();
			    if(listaEquipos.size() > 0)
			        eDelegate.grabaEquiposVpi(petiNumero,nroTelAsig,listaEquipos);
		    }
		}catch(ATiempoAppEx at){
		    log.debug("No se han podido grabar los equipos ");
		
		} 
        } catch (NamingException e1) {
            log.debug("No se han podido grabar los equipos ");
            e1.printStackTrace();
        } catch (FinderException e) {
            log.debug("No se han podido grabar los equipos ");
            e.printStackTrace();
        }
        //parche Error de Produccion 02/10/2009
        /*if (error){
            new Long ("");            
        }*/
	   
	}
	
}
