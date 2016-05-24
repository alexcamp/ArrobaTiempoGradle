package com.telefonica_chile.bandeja.torreControl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocalHome;

import com.tecnonautica.utiles.tablas.Tabla;
import com.tecnonautica.utiles.tablas.TablaException;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;

/**
 * Bean implementation class for Enterprise Bean: TorreControlSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class TorreControlSessionBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2039707967542135891L;

	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	//Metodo que setear dator filtrados
	public Tabla setearDatoTablaPeticion(HashMap filtros, int paginaActual, int paginacion)
		throws BandejaException {
		Tabla tabla = new TablaTorreFiltro(paginaActual);
		tabla.setLargoPagina(paginacion);
		try {
			tabla.retrieve(filtros);
			return tabla;
		} catch (TablaException e) {
			return null;
		}
	}

	/*
	 * Busca Los Datos para llenar el Cuadro de Mando de Torre de Control 
	 */
	public Tabla getDatosCuadroMando(HashMap filtro) {

		try {
			// Obtengo por Agencia
			Tabla tabla = new TablaNuevaTorreControl();

			// Obtenemos los Datos para el Cuadro de Mando.
			tabla.retrieve( filtro );

			return tabla;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public Tabla getDatosCuadroMandoGI(HashMap filtro)
	{
		try {
			// Obtengo por Agencia
			Tabla tabla = new TablaGestionInbound();

			// Obtenemos los Datos para el Cuadro de Mando.
			tabla.retrieve( filtro );

			return tabla;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public Tabla getDatosCuadroMandoGO(HashMap filtro)
	{
		try {
			// Obtengo por Agencia
			Tabla tabla = new TablaGestionOutbound();

			// Obtenemos los Datos para el Cuadro de Mando.
			tabla.retrieve( filtro );

			return tabla;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}

	/*
	 * Obtiene el Listado de los Detalle de Actividad, ordenados por Orden. 
	 */
	public ArrayList getTitulosCuadroMando(HashMap filtro) {

		try {
			TablaNuevaTorreControl tabla = new TablaNuevaTorreControl();

			// Obtenemos los Datos para el Cuadro de Mando.
			ArrayList listaCuadroMando = (ArrayList) tabla.getTitulosCuadroMando(filtro);
			
			return listaCuadroMando;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public ArrayList getTitulosCuadroMandoGI(HashMap filtro)
	{

		try {
			TablaGestionInbound tabla = new TablaGestionInbound();

			// Obtenemos los Datos para el Cuadro de Mando.
			ArrayList listaCuadroMando = (ArrayList) tabla.getTitulosCuadroMando(filtro);
		
			return listaCuadroMando;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public ArrayList getTitulosCuadroMandoGO(HashMap filtro)
	{

		try
		{
			TablaGestionOutbound tabla = new TablaGestionOutbound();

			// Obtenemos los Datos para el Cuadro de Mando.
			ArrayList listaCuadroMando = (ArrayList) tabla.getTitulosCuadroMando(filtro);
	
			return listaCuadroMando;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}

	/*
	 * Obtiene el Listado de los Detalle de Actividad, ordenados por Orden. 
	 */
	public ArrayList getActividadesCuadroMando(HashMap filtro) {

		try {
			TablaNuevaTorreControl tabla = new TablaNuevaTorreControl();

			// Obtenemos los Datos para el Cuadro de Mando.
			ArrayList listaCuadroMando = (ArrayList) tabla.getDetalleActividadTC(filtro);
			
			return listaCuadroMando;

		} catch (TablaException e) {
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public ArrayList getActividadesCuadroMandoGI(HashMap filtro)
	{
		try
		{
			TablaGestionInbound tabla = new TablaGestionInbound();
			ArrayList listaCuadroMando = (ArrayList) tabla.getDetalleActividadTC(filtro);
			return listaCuadroMando;
		}
		catch (TablaException e)
		{
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	public ArrayList getActividadesCuadroMandoGO(HashMap filtro)
	{
		try
		{
			TablaGestionOutbound tabla = new TablaGestionOutbound();
			ArrayList listaCuadroMando = (ArrayList) tabla.getDetalleActividadTC(filtro);
			return listaCuadroMando;
		}
		catch (TablaException e)
		{
			log.error("Error Recuperando Datos de Cuadro de Mando", e);
			return null;
		}
	}
	
	private Peticion_atisLocalHome peticionATISHome;
	private PeticionLocalHome peticionHome;
	private BintegradaLocalHome bintegradaHome;
	
	public ArrayList listaBuscadorAvanzadoInBound(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException
	{
		try
		{
			log.debug("buscador avanzado inbound :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			ArrayList listaPeticiones = new ArrayList();
			if(peticionATISHome==null)
				peticionATISHome=(Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(bintegradaHome==null)
				bintegradaHome=(BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = null;
			
			if ( idPeticionAtis != null )
			{
				Peticion_atisLocal pAtisLocal = peticionATISHome.findByPrimaryKey( new Peticion_atisKey(idPeticionAtis) );
				c = pAtisLocal.getPeticion();
			}
			else
			{ 
				c = peticionHome.findByCliente(rutCli, rutDv);
			}

			if ( c != null ) {
				PeticionLocal pLocal = null;
				PeticionDTO pDto = null;
				PeticionKey pKey = null;
				Peticion_atisKey peticion_atisKey = null;
				for (Iterator it=c.iterator(); it.hasNext(); )
				{
					pLocal = (PeticionLocal) it.next();
					pDto = new PeticionDTO();
					pKey = (PeticionKey) pLocal.getPrimaryKey();
					peticion_atisKey=(Peticion_atisKey) pLocal.getFk_01_pet_atis().getPrimaryKey();
					pDto.setCodPetCd(peticion_atisKey.cod_pet_cd);
					pDto.setAgrupaciones(pLocal.getAgrupacionesString(","));
					pDto.setPetiNumero( pKey.peti_numero );
					pDto.setPetiFechaIngreso( pLocal.getPeti_fecha_ingreso() );
					pDto.setPetiFechaCompromiso( pLocal.getPeti_fecha_compromiso());
					BintegradaLocal local=null;
					try
					{
						local=bintegradaHome.findByVisiblePetApl(pKey.peti_numero,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID")));
						log.debug("Esta En Bandeja!!!");
						pDto.setEstaEnBandeja(true);
						if(local.getFk_bi_act()==null)
							continue;
						ActividadKey actividadKey=(ActividadKey) local.getFk_bi_act().getPrimaryKey();
						log.debug("Actividad Key:"+actividadKey.act_id);
						if(actividadKey.act_id.longValue()!=1023 && actividadKey.act_id.longValue()!=1028 && actividadKey.act_id.longValue()!=1030)
							pDto.setEstaEnActividadPermitida(false);
						else
							pDto.setEstaEnActividadPermitida(true);
						pDto.setUrlBandeja(local.getBi_url_detalle());
						log.debug("Actividad Permitida:"+pDto.isEstaEnActividadPermitida());
					}
					catch(FinderException fff)
					{
						log.debug("No esta en bandeja!!!");
						pDto.setEstaEnBandeja(false);	
					}
					listaPeticiones.add( pDto );
				}
			}
			return listaPeticiones;
		}
		catch(FinderException e)
		{
			log.debug("NO ENCONTRO buscador avanzado inbound :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			return new ArrayList(0);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new BandejaException(e);
		}
	}
	
	public ArrayList listaBuscadorAvanzadoOutBound(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException
	{
		try
		{
			log.debug("buscador avanzado outbound :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			ArrayList listaPeticiones = new ArrayList();
			if(peticionATISHome==null)
				peticionATISHome=(Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(bintegradaHome==null)
				bintegradaHome=(BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = null;
	
			if ( idPeticionAtis != null )
			{
				Peticion_atisLocal pAtisLocal = peticionATISHome.findByPrimaryKey( new Peticion_atisKey(idPeticionAtis) );
				c = pAtisLocal.getPeticion();
			}
			else
			{ 
				c = peticionHome.findByCliente(rutCli, rutDv);
			}

			if ( c != null ) {
				PeticionLocal pLocal = null;
				PeticionDTO pDto = null;
				PeticionKey pKey = null;
				Peticion_atisKey peticion_atisKey = null;
				for (Iterator it=c.iterator(); it.hasNext(); )
				{
					pLocal = (PeticionLocal) it.next();
					pDto = new PeticionDTO();
					pKey = (PeticionKey) pLocal.getPrimaryKey();
					peticion_atisKey=(Peticion_atisKey) pLocal.getFk_01_pet_atis().getPrimaryKey();
					pDto.setCodPetCd(peticion_atisKey.cod_pet_cd);
					pDto.setAgrupaciones(pLocal.getAgrupacionesString(","));
					pDto.setPetiNumero( pKey.peti_numero );
					pDto.setPetiFechaIngreso( pLocal.getPeti_fecha_ingreso() );
					pDto.setPetiFechaCompromiso( pLocal.getPeti_fecha_compromiso());
					BintegradaLocal local=null;
					try
					{
						local=bintegradaHome.findByVisiblePetApl(pKey.peti_numero,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID")));
						log.debug("Esta En Bandeja!!!");
						pDto.setEstaEnBandeja(true);
						if(local.getFk_bi_act()==null)
							continue;
						ActividadKey actividadKey=(ActividadKey) local.getFk_bi_act().getPrimaryKey();
						log.debug("Actividad Key:"+actividadKey.act_id);
						if(actividadKey.act_id.longValue()==1039)
							pDto.setEstaEnActividadPermitida(true);
						else
							pDto.setEstaEnActividadPermitida(false);
						pDto.setUrlBandeja(local.getBi_url_detalle());
						log.debug("Actividad Permitida:"+pDto.isEstaEnActividadPermitida());
					}
					catch(FinderException fff)
					{
						log.debug("No esta en bandeja!!!");
						pDto.setEstaEnBandeja(false);	
					}
					listaPeticiones.add( pDto );
				}
			}
			return listaPeticiones;
		}
		catch(FinderException e)
		{
			log.debug("NO ENCONTRO buscador avanzado outbound :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			return new ArrayList(0);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new BandejaException(e);
		}
	}
	
	
	public ArrayList listaBuscadorAvanzadoGestorOS(Long idPeticionAtis,String rutCli,String rutDv) throws BandejaException
	{
		try
		{
			log.debug("buscador avanzado gestor os :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			ArrayList listaPeticiones = new ArrayList();
			if(peticionATISHome==null)
				peticionATISHome=(Peticion_atisLocalHome) HomeFactory.getHome(Peticion_atisLocalHome.JNDI_NAME);
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(bintegradaHome==null)
				bintegradaHome=(BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			Collection c = null;

			if ( idPeticionAtis != null )
			{
				Peticion_atisLocal pAtisLocal = peticionATISHome.findByPrimaryKey( new Peticion_atisKey(idPeticionAtis) );
				c = pAtisLocal.getPeticion();
			}
			else
			{ 
				c = peticionHome.findByCliente(rutCli, rutDv);
			}

			if ( c != null ) {
				PeticionLocal pLocal = null;
				PeticionDTO pDto = null;
				PeticionKey pKey = null;
				Peticion_atisKey peticion_atisKey = null;
				for (Iterator it=c.iterator(); it.hasNext(); )
				{
					pLocal = (PeticionLocal) it.next();
					pDto = new PeticionDTO();
					pKey = (PeticionKey) pLocal.getPrimaryKey();
					peticion_atisKey=(Peticion_atisKey) pLocal.getFk_01_pet_atis().getPrimaryKey();
					pDto.setCodPetCd(peticion_atisKey.cod_pet_cd);
					pDto.setAgrupaciones(pLocal.getAgrupacionesString(","));
					pDto.setPetiNumero( pKey.peti_numero );
					pDto.setPetiFechaIngreso( pLocal.getPeti_fecha_ingreso() );
					pDto.setPetiFechaCompromiso( pLocal.getPeti_fecha_compromiso());
					BintegradaLocal local=null;
					try
					{
						local=bintegradaHome.findByVisiblePetApl(pKey.peti_numero,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID")));
						log.debug("Esta En Bandeja!!!");
						pDto.setEstaEnBandeja(true);
						if(local.getFk_bi_act()==null)
							continue;
						Long rol=local.getRol_id();
						if(rol==null)
							continue;
						if(rol.longValue()==5000)
							pDto.setEstaEnActividadPermitida(true);
						else
							pDto.setEstaEnActividadPermitida(false);
						pDto.setUrlBandeja(local.getBi_url_detalle());
						log.debug("Actividad Permitida:"+pDto.isEstaEnActividadPermitida());
					}
					catch(FinderException fff)
					{
						log.debug("No esta en bandeja!!!");
						pDto.setEstaEnBandeja(false);	
					}
					listaPeticiones.add( pDto );
				}
			}
			return listaPeticiones;
		}
		catch(FinderException e)
		{
			log.debug("NO ENCONTRO buscador avanzado gestor os :"+idPeticionAtis+"- "+rutCli+ " -"+rutDv);
			return new ArrayList(0);
		}
		catch(Exception e)
		{
			log.debug("Exception",e);
			throw new BandejaException(e);
		}	
	}
}
