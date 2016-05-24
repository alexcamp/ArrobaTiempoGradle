package co.com.telefonica.atiempo.vpistbba.genera_archivo.ejb.sb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPlanDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPCDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTVDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.OdsListDTO;
import co.com.telefonica.atiempo.vpistbba.genera_archivo.business.GeneraArchivoBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: GeneraArchivo
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class GeneraArchivoBean extends SessionBeanAdapter implements GeneraArchivoBusinessInterface , ComunInterfaces
{
	private InfoComunColombiaBusinessInterface infoInterface;
	private RecursosTVInterfaces recursosInterface;
	private PeticionesInterfaces peticoInterface;
	private InfoComunColombiaBusinessInterface infoComunColombiaBI;
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
	//private Traslado_solobaLocalHome traslado_solobaLocalHome;
		
	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException
	{
		super.setSessionContext(ctx);
		inicia();
		log.debug("Entrando a Session Context de GeneraArchivoBean");
	}
	
	private void inicia()
	{
		try
		{
			infoInterface = new InfoComunColombiaBusinessDelegate();
			recursosInterface = new RecursosTVDelegate();
			peticoInterface =  new PeticionesDelegate();
			infoComunColombiaBI  = new InfoComunColombiaBusinessDelegate();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
		//	traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
		}
		catch (ATiempoAppEx e)
		{
			log.debug("ATiempoAppEx",e);
		}

	}

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public ArrayList getDatosPeticiones(ArrayList listaPeticiones)
	{
		ArrayList plista = new ArrayList();
		if ( listaPeticiones==null )
			return plista; 
		log.debug("Obteniendo datos de "+listaPeticiones.size()+" peticiones");
		try
		{
			for (int i=0; i<listaPeticiones.size(); i++)
			{
				Long idPetico = (Long) listaPeticiones.get(i);
				PeticionDTO pDto = infoInterface.obtenerSimpleInfoPeticion( idPetico ).toPeticionDto();
				pDto.setListaPS( infoInterface.getPsYTipoPcNoRealizado( idPetico ) );
				plista.add( pDto );
			}
		}
		catch (ATiempoAppEx e)
		{
			log.debug("ATiempoAppEx",e);
		}
		
		return plista;
	}


	public ArrayList getDatosODS(ArrayList peticiones)
	{
		try
		{
			log.debug("Obteniendo datos de "+peticiones.size()+" peticiones");
			ArrayList retorno=new ArrayList();
			for(Iterator iterator=peticiones.iterator();iterator.hasNext();)
			{
				OdsListDTO listDTO=new OdsListDTO();
				Long idPeticion=(Long) iterator.next();
				InformacionPeticionDTO informacionPeticionDTO = infoComunColombiaBI.obtenerSimpleInfoPeticion(idPeticion);
				InformacionAdslDTO informacionAdslDTO = infoComunColombiaBI.obtenerInformacionAdsl(idPeticion);
				InformacionAdslDTO informacionAdslActualDTO = infoComunColombiaBI.obtenerInformacionAdslActual(idPeticion);
				InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(idPeticion);
				ArrayList listaAsignaciones = peticoInterface.getListaAsignaciones( idPeticion );
//				String cuentaCorreoPadre=infoComunColombiaBI.obtenerCuentaCorreoPadre(idPeticion);
				InfoPlanDTO infoPlanDTO=new InfoPlanDTO();
				//veo si es traslado solo BA
				Integer trasladoOn=new Integer(0);
				Traslado_solobaLocal traslado_solobaLocal=null;
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Traslado_solobaLocalHome traslado_solobaLocalHome=null;
				try
				{
					traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(idPeticion);
					trasladoOn=new Integer(1);
				}
				catch (FinderException e)
				{
					try
					{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(idPeticion);
						trasladoOn=new Integer(2);
					} catch (FinderException e1) {
						//log.warn("No es traslado BA");
					}
				}catch (NamingException e)
				{
					log.error(" Creacion de Local Home Nulls",e);
				}
				
				String ods="";
				if ( informacionTecnicaDTO!=null )
					ods=informacionTecnicaDTO.LineaNueva.getOds();
				ArrayList listadoPS = infoComunColombiaBI.getProductoServicioMasQuiebres( idPeticion );
				ArrayList listadoPC = getListadoPC(idPeticion, informacionPeticionDTO, listadoPS, informacionTecnicaDTO,infoPlanDTO);
				ArrayList listadoDecos=null;					
				boolean pasaPorActividad=peticoInterface.pasaXActividad(idPeticion,new Integer(idActividadObtenerConfiguracionDTH));
				if(pasaPorActividad)
					listadoDecos = recursosInterface.getListaDecoTarjetas( idPeticion );
				else
					listadoDecos = recursosInterface.getListaDecoTarjetasActivas( idPeticion );
				//log.debug("listado Decos Activos Size:"+listadoDecos.size());
				InformacionTVDTO infoTv = calcularDecoTarjeta( listadoPS );
				listDTO.setIdPeticion(idPeticion);
				listDTO.setTrasladoOn(trasladoOn);
				listDTO.setInformacionPeticionDTO(informacionPeticionDTO);
				listDTO.setInformacionAdslDTO(informacionAdslDTO);
				listDTO.setInformacionAdslActualDTO(informacionAdslActualDTO);
				listDTO.setInformacionTecnicaDTO(informacionTecnicaDTO);
				listDTO.setListaAsignaciones(listaAsignaciones);
				listDTO.setOds(ods);
				listDTO.setListadoPS(listadoPS);
				listDTO.setListadoPC(listadoPC);
				listDTO.setListadoDecos(listadoDecos);
				listDTO.setInfoTv(infoTv);
				String correoUser="";
				if (informacionAdslActualDTO!=null && informacionAdslActualDTO.getFatherEmail()!=null){
					correoUser = informacionAdslActualDTO.getFatherEmail();
				}else{
					if(informacionAdslActualDTO!=null && informacionAdslActualDTO.getUsuarioAcc()!=null)
					{
						correoUser = informacionAdslActualDTO.getUsuarioAcc();
					}
				}
				listDTO.setCuentaCorreoPadre(correoUser);
				listDTO.setInfoPlanDTO(infoPlanDTO);
				retorno.add(listDTO);
			}
//			log.debug("Estoy retornando:"+retorno.size()+" registros");
			return retorno;
		}
		catch(Exception e)
		{
			log.debug(e);
			return new ArrayList();
		}
	}
	
	private ArrayList getListadoPC(Long idPeticion, InformacionPeticionDTO infoPeticionDto, ArrayList listadoPS,InformacionTecnicaDTO informacionTecnicaDTO,InfoPlanDTO infoPlanDTO)
	{
		ArrayList listadoPC = new ArrayList();
		for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
		{
			ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
			//REQ BA NAKED adicion familia PC naked
			if ( psDto.getIdFaps().intValue() == familiaPcLinea ||  psDto.getIdFaps().intValue() == familiaIP || psDto.getIdFaps().intValue() == familiaPcBA || psDto.getIdFaps().intValue() == familiaPcPsBANaked || psDto.getIdFaps().intValue()== familiaPcTV )
			{
				InformacionPCDTO infoPCDto = new InformacionPCDTO();
				infoPCDto.setId( psDto.getId() );
				infoPCDto.setNombrePS( psDto.getNombreProducto() );
				infoPCDto.setTipo( psDto.getDescTipo());
				infoPCDto.setSubTipo(psDto.getDescSubTipo());
				infoPCDto.setDireccionInstalacion( infoPeticionDto.getDireccion() );
				infoPCDto.setCodDepartamento( infoPeticionDto.getCdDepartamento() );
				infoPCDto.setCodLocalidad( infoPeticionDto.getCdLocalidad() );
				infoPCDto.setDepartamento( infoPeticionDto.getDscDepartamento() );
				infoPCDto.setLocalidad( psDto.getDescLocalidad() );
				infoPCDto.setSubLocalidad( psDto.getDescSubLocalidad() );
				infoPCDto.setIdentificadorSTB( informacionTecnicaDTO.LineaNueva.getTelefono() );
				infoPCDto.setIdentificadorTV( infoPeticionDto.getIdentificadorTV() );
				infoPCDto.setFechaHoraCompromiso( infoPeticionDto.getFecCompromiso() );
				listadoPC.add( infoPCDto );
//				REQ BA NAKED adicion familia PC naked
				if(psDto.getIdFaps().intValue() == familiaPcBA||psDto.getIdFaps().intValue() == familiaPcPsBANaked)
					infoPlanDTO.setPlanBA(psDto.getNombreProducto());
				if(psDto.getIdFaps().intValue()== familiaPcTV)
					infoPlanDTO.setPlanTV(psDto.getNombreProducto());
			
			}
		}
		return listadoPC;
	}
	
	
	private InformacionTVDTO calcularDecoTarjeta(ArrayList listadoPS)
	{
		int cantidadDecoAlta = 0;
		int cantidadDecoBaja = 0;
		for (int i=0; listadoPS!=null && i<listadoPS.size(); i++)
		{
			ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
//			TODO PVR validar se agrego  familia
			if ( psDto.getIdFaps().intValue()== familiaDecoTV ||psDto.getIdFaps().intValue()==familiaDecoPVRTV )
			{
				//log.debug("PS DECO: [" + psDto.getId() + "," + psDto.getIdFaps() + "]");
				if ( "A".equals(psDto.getTipoOperacionComercial()) )
					++cantidadDecoAlta;
				else if ( "B".equals(psDto.getTipoOperacionComercial()) )
					++cantidadDecoBaja;
			}
		}
	
		InformacionTVDTO infoTV = new InformacionTVDTO();
		infoTV.setCantidadDecoAlta( cantidadDecoAlta );
		infoTV.setCantidadDecoBaja( cantidadDecoBaja );
	
		return infoTV;
	}

	public ArrayList getDatosODSOutBound(ArrayList peticiones)
	{
		try
		{
			log.debug("Obteniendo datos de "+peticiones.size()+" peticiones");	
			ArrayList retorno=new ArrayList();
			for(Iterator iterator=peticiones.iterator();iterator.hasNext();)
			{
				OdsListDTO listDTO=new OdsListDTO();
				Long idPeticion=(Long) iterator.next();
				InformacionPeticionDTO informacionPeticionDTO = infoComunColombiaBI.obtenerSimpleInfoPeticion(idPeticion);
				InformacionAdslDTO informacionAdslDTO = infoComunColombiaBI.obtenerInformacionAdsl(idPeticion);
				InformacionAdslDTO informacionAdslActualDTO = infoComunColombiaBI.obtenerInformacionAdslActual(idPeticion);
				InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(idPeticion);
				ArrayList listaAsignaciones = peticoInterface.getListaAsignaciones( idPeticion );
//				String cuentaCorreoPadre=infoComunColombiaBI.obtenerCuentaCorreoPadre(idPeticion);
				InfoPlanDTO infoPlanDTO=new InfoPlanDTO();
				//veo si es traslado solo BA
				Integer trasladoOn=new Integer(0);
				Traslado_solobaLocal traslado_solobaLocal=null;
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Traslado_solobaLocalHome traslado_solobaLocalHome=null;
				try
				{
					traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(idPeticion);
					trasladoOn=new Integer(1);
				}
				catch(FinderException e)
				{
					try
					{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(idPeticion);
						trasladoOn=new Integer(2);
					} catch (FinderException e1) {
//						log.warn("No es traslado BA");
					}
				}
				catch (NamingException e)
				{
					log.error(" Creacion de Local Home Nulls",e);
				}
				
				String ods="";
				if ( informacionTecnicaDTO!=null )
					ods=informacionTecnicaDTO.LineaNueva.getOds();
				ArrayList listadoPS = infoComunColombiaBI.getProductoServicioMasQuiebres( idPeticion );
				ArrayList listadoPC = getListadoPC(idPeticion, informacionPeticionDTO, listadoPS, informacionTecnicaDTO,infoPlanDTO);
				ArrayList listadoDecos=null;					
				boolean pasaPorActividad=peticoInterface.pasaXActividad(idPeticion,new Integer(idActividadObtenerConfiguracionDTH));
				if(pasaPorActividad)
					listadoDecos = recursosInterface.getListaDecoTarjetas( idPeticion );
				else
					listadoDecos = recursosInterface.getListaDecoTarjetasActivas( idPeticion );
				//log.debug("listado Decos Activos Size:"+listadoDecos.size());
				
				InformacionTVDTO infoTv = calcularDecoTarjeta( listadoPS );
				
				listDTO.setIdPeticion(idPeticion);
				listDTO.setTrasladoOn(trasladoOn);
				listDTO.setInformacionPeticionDTO(informacionPeticionDTO);
				listDTO.setInformacionAdslDTO(informacionAdslDTO);
				listDTO.setInformacionAdslActualDTO(informacionAdslActualDTO);
				listDTO.setInformacionTecnicaDTO(informacionTecnicaDTO);
				listDTO.setListaAsignaciones(listaAsignaciones);
				listDTO.setOds(ods);
				listDTO.setListadoPS(listadoPS);
				listDTO.setListadoPC(listadoPC);
				listDTO.setListadoDecos(listadoDecos);
				listDTO.setInfoTv(infoTv);
				String correoUser="";
				if (informacionAdslActualDTO!=null &&  informacionAdslActualDTO.getFatherEmail()!=null){
					correoUser = informacionAdslActualDTO.getFatherEmail();
				}else{
					if(informacionAdslActualDTO!=null && informacionAdslActualDTO.getUsuarioAcc()!=null)
					{
						correoUser = informacionAdslActualDTO.getUsuarioAcc();
					}
				}
				listDTO.setCuentaCorreoPadre(correoUser);
				listDTO.setInfoPlanDTO(infoPlanDTO);
				
				retorno.add(listDTO);
			}
			//log.debug("Estoy retornando:"+retorno.size()+" registros");
			return retorno;
		}
		catch(Exception e)
		{
			log.error(e);
			return new ArrayList();
		}
	}

	public ArrayList getDatosODSChulo(HashMap peticiones)
	{	ArrayList retorno=new ArrayList();
		try
		{
			log.debug("Obteniendo datos de "+peticiones.size()+" peticiones");
			for(Iterator iterator=peticiones.keySet().iterator();iterator.hasNext();)
			{
				OdsListDTO listDTO=new OdsListDTO();
				Long idPeticion=(Long) iterator.next();
				InformacionPeticionDTO informacionPeticionDTO = infoComunColombiaBI.obtenerSimpleInfoPeticion(idPeticion);
				InformacionAdslDTO informacionAdslDTO = infoComunColombiaBI.obtenerInformacionAdsl(idPeticion);
				InformacionAdslDTO informacionAdslActualDTO = infoComunColombiaBI.obtenerInformacionAdslActual(idPeticion);
				InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(idPeticion);
				ArrayList listaAsignaciones = peticoInterface.getListaAsignaciones( idPeticion );
//				String cuentaCorreoPadre=infoComunColombiaBI.obtenerCuentaCorreoPadre(idPeticion);
				InfoPlanDTO infoPlanDTO=new InfoPlanDTO();
				//veo si es traslado solo BA
				Integer trasladoOn=new Integer(0);
				Traslado_solobaLocal traslado_solobaLocal=null;
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Traslado_solobaLocalHome traslado_solobaLocalHome=null;
				try
				{
					traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(idPeticion);
					trasladoOn=new Integer(1);
				}
				catch (FinderException e)
				{
					try
					{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(idPeticion);
						trasladoOn=new Integer(2);
					} catch (FinderException e1) {
						//log.warn("No es traslado BA");
					}
				}catch (NamingException e)
				{
					log.error(" Creacion de Local Home Nulls",e);
				}
	
				String ods="";
				if ( informacionTecnicaDTO!=null )
					ods=informacionTecnicaDTO.LineaNueva.getOds();
				ArrayList listadoPS = infoComunColombiaBI.getPSQuiebresYChulo( idPeticion,new Long((String)peticiones.get(idPeticion)) );
				ArrayList listadoPC = getListadoPC(idPeticion, informacionPeticionDTO, listadoPS, informacionTecnicaDTO,infoPlanDTO);
				ArrayList listadoDecos=null;					
				boolean pasaPorActividad=peticoInterface.pasaXActividad(idPeticion,new Integer(idActividadObtenerConfiguracionDTH));
				if(pasaPorActividad)
					listadoDecos = recursosInterface.getListaDecoTarjetas( idPeticion );
				else
					listadoDecos = recursosInterface.getListaDecoTarjetasActivas( idPeticion );
				//log.debug("listado Decos Activos Size:"+listadoDecos.size());
				InformacionTVDTO infoTv = calcularDecoTarjeta( listadoPS );
				listDTO.setIdPeticion(idPeticion);
				listDTO.setTrasladoOn(trasladoOn);
				listDTO.setInformacionPeticionDTO(informacionPeticionDTO);
				listDTO.setInformacionAdslDTO(informacionAdslDTO);
				listDTO.setInformacionAdslActualDTO(informacionAdslActualDTO);
				listDTO.setInformacionTecnicaDTO(informacionTecnicaDTO);
				listDTO.setListaAsignaciones(listaAsignaciones);
				listDTO.setOds(ods);
				listDTO.setListadoPS(listadoPS);
				listDTO.setListadoPC(listadoPC);
				listDTO.setListadoDecos(listadoDecos);
				listDTO.setInfoTv(infoTv);
				String correoUser="";
				if (informacionAdslActualDTO!=null && informacionAdslActualDTO.getFatherEmail()!=null){
					correoUser = informacionAdslActualDTO.getFatherEmail();
				}else{
					if(informacionAdslActualDTO!=null && informacionAdslActualDTO.getUsuarioAcc()!=null)
					{
						correoUser = informacionAdslActualDTO.getUsuarioAcc();
					}
				}
				listDTO.setCuentaCorreoPadre(correoUser);
				listDTO.setInfoPlanDTO(infoPlanDTO);
				retorno.add(listDTO);
			}
//						log.debug("Estoy retornando:"+retorno.size()+" registros");
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return retorno;
		}
	}
}
