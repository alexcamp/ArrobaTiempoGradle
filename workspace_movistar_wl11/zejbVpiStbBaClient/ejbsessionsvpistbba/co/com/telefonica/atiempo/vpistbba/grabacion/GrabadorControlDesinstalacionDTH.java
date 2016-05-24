package co.com.telefonica.atiempo.vpistbba.grabacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.DecoTarDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionDelegate;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.vpistbba.grabacion.GrabadorComun;


public class GrabadorControlDesinstalacionDTH extends GrabadorComun
{

	protected void grabarDatosPropios(HttpServletRequest request)
	{
		try
		{
			log.debug("Entre a GrabadorControlDesinstalacionDTH");
			ArrayList listaPares=new ArrayList();	
			Long petiNumero=new Long(request.getParameter("folio"));
			Integer cantidadAccionPostDTH=new Integer(request.getParameter("cantidadAccionPostDTH"));
			for(int i=0;i<cantidadAccionPostDTH.intValue();i++)
			{
				String par=request.getParameter("par_"+i);
				Integer accion=new Integer(request.getParameter("accion_"+i));
				if(par!=null && accion!=null)
				{
					String deco=par.substring(0,par.indexOf("_"));
					String tarjeta=par.substring(par.indexOf("_")+1);
					DecoTarDTO decoTarDTO=new DecoTarDTO(deco,tarjeta);
					decoTarDTO.setAccion(accion);
					log.debug("Grabando Accion para :"+decoTarDTO.toString());
					listaPares.add(decoTarDTO);
				}
			}
			RecursosTVDelegate delegate=new RecursosTVDelegate();
			delegate.actualizaAccionParesPostVenta(petiNumero,listaPares);
			grabaControlVisita(request);
		}
		catch(Exception e)
		{
			log.debug("Excepcion",e);
		}	
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
			}
			//Gustavo - CR 25403 - Fin
		}
		catch(Exception e)
		{
			log.debug(e);
		}
	
	}
}
