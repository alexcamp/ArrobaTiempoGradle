package com.telefonica_chile.bandeja.web.acciones;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.BuscadorPeticionUtiles;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocal;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocalHome;

public class VerBuscadorInboundAcc extends Accion
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		boolean error = false;
		BuscadorPeticionUtiles.cargarComboTipoPc(request);
		if(request.getParameter("folioAtis")==null && request.getParameter("rut")==null && 
					request.getParameter("rutDv")==null && request.getParameter("folioPc")==null)
			return;
		try
		{
			TorreControlSessionLocalHome home=(TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal local=home.create();
			Long folioAtis=null;
			if(request.getParameter("folioAtis")!=null && !request.getParameter("folioAtis").equals(""))
				folioAtis=new Long(request.getParameter("folioAtis"));
			
			String rut = request.getParameter("rut");
			String rutDv = request.getParameter("rutDv");
			
			// CR 00027016
			String folioPC = request.getParameter("folioPc");
			String typePc = request.getParameter("typePc");
			int idTypePc = 0;
			if (typePc != null && !typePc.equals("")){
				idTypePc = Integer.parseInt(typePc);
			}
			
			int codConsulta = BuscadorPeticionUtiles.obtenerCodigoConsulta(folioAtis,rut,rutDv,folioPC,idTypePc);
			log.debug("codConsulta = "+codConsulta);
			if (codConsulta!=-1){
				BuscadorPeticionVpiDTO buscadorPeticion = new BuscadorPeticionVpiDTO(folioAtis,rut,rutDv,folioPC,idTypePc);
				buscadorPeticion.setCodBusqueda(BuscadorPeticionUtiles.COD_BUSCADOR_INBOUND);
				buscadorPeticion.setJoinBIntegrada(true);
				buscadorPeticion.setIdBusqueda(codConsulta);
				
	//			ArrayList peticiones=local.listaBuscadorAvanzadoInBound(buscadorPeticion);
				PeticionesInterfaces pInterface = new PeticionesDelegate();
				ArrayList peticiones = pInterface.buscarPeticiones(buscadorPeticion);
		
				request.setAttribute("peticiones",peticiones);
				
				if (peticiones.size() > 0){
					List listaSugerencias = BuscadorPeticionUtiles.mensajeSugerencias(buscadorPeticion);
					String msgResultado = BuscadorPeticionUtiles.mensajeResultado(buscadorPeticion);
					// Retornar Listado.
					request.setAttribute("msgResultado", msgResultado);
					request.setAttribute("listadoSugerencias", listaSugerencias);
				}else{
					String msg = BuscadorPeticionUtiles.mensajeResultadoVacio(buscadorPeticion);
					request.setAttribute("msgError",msg);				
				}
				
				cargarDatosEmpresa(request);
			}
			else{
				error = true;
			}

		}
		catch (NamingException e)
		{
			error = true;
			log.error("NamingException",e);
		}
		catch (CreateException e)
		{
			error = true;
			log.error("CreateException",e);
		}
		catch (NumberFormatException nf)
		{
			error = true;
			log.error("NumberFormat",nf);
//		} 
//		catch (BandejaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		catch (ATiempoAppEx e) 
		{
			error = true;
			log.error("ATiempoAppEx",e);
		}
		finally
		{
			if (error){
				String msg = "Ha ocurrido un error en la ejecución de la busqueda.";
				request.setAttribute("msgError",msg);
			}
		}
		
	}
	
	
	public void cargarDatosEmpresa(HttpServletRequest request)
	{
		if(request.getParameter("ID_USUARIO")!=null)
		{
			//log.debug("Cargando Datos de Empresa");
			try
			{
				Long idUsuario=new Long(request.getParameter("ID_USUARIO"));
				InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				InformacionEmpresaDTO informacionEmpresaDTO = infoComunColombiaBI.obtenerInformacionEmpresa(idUsuario);
				request.setAttribute("datosEmpresa",informacionEmpresaDTO);
				//log.debug(informacionEmpresaDTO.toString());
			}
			catch (ATiempoAppEx e)
			{
				log.debug("NO Datos de Empresa Cargados");
				log.debug("ATiempoAppEx",e);
			}
	
			//log.debug("Datos de Empresa Cargados");
		}
		else
		{
			log.debug("No tengo el ID del usuario");
		}
	}
	
}
