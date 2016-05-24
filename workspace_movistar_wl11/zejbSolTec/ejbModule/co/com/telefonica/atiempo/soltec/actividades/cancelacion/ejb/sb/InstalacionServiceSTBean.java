package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.CausaDTO;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoKey;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocal;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.soltec.dto.RangoDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;

/**
 * Bean implementation class for Enterprise Bean: InstalacionServiceST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InstalacionServiceSTBean 
				extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter {
					
	private javax.ejb.SessionContext mySessionCtx;
	
	private static Logger log=Logger.getLogger(InstalacionServiceSTBean.class);
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
	
	public InformacionAgendamientoDTO grabarAsignacion(String nroPeticion,String codTecnico,String fComp,String nroRango,String nroCare, UsuarioWeb usuario) throws java.text.ParseException
	{
		DBManager manager=new DBManager ();
		manager.setDataSource (DBManager.JDBC_VPISTBBA);
		InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();

		String horaDesde = "";
		String horaHasta = "";

		Integer idCare = new Integer(Utiles.sinNull(nroCare, "0"));
		Long idPeticion = new Long(Utiles.sinNull(nroPeticion, "0"));
		Integer idRango = new Integer( Utiles.sinNull(nroRango, "0") );
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDia = null;
		fechaDia = sdf.parse( fComp );

		infoAgenda.setCdpeticion( nroPeticion );

		// Busco Datos del Rango.
		RangoLocal rLocal = null;
		if ( idRango.intValue() != 0) {
			try {
				RangoLocalHome rHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
				rLocal = rHome.findByPrimaryKey(new RangoKey( idRango ));
				horaDesde = rLocal.getHora_desde();
				horaHasta = rLocal.getHora_hasta();
			} catch (NamingException e) {
			} catch (FinderException e) {
			}
		}
		// Si hay Causa de Reagendamiento, busco el entiti.
		Causa_reagendamientoLocal crLocal = null;
		if ( idCare.intValue()!=0 ) {
			try {
				Causa_reagendamientoLocalHome crHome = (Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
				crLocal = crHome.findByPrimaryKey(new Causa_reagendamientoKey(idCare));
				infoAgenda.setDescripcionCausa( crLocal.getCare_descripcion() );
			} catch (NamingException e) {
			} catch (FinderException e) {
			}
		}
		infoAgenda.setFechaCompromiso( fechaDia );
		infoAgenda.setHoraDesde( horaDesde );
		infoAgenda.setHoraHasta( horaHasta );
		try {
			//Buscamos el Tecnico Asociado.
			TecnicoLocalHome tecHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			TecnicoLocal tecLocal = tecHome.findByPrimaryKey( new TecnicoKey( codTecnico ) );
			infoAgenda.setIdTecnicoInicial( codTecnico );
			infoAgenda.setNombreTecnicoInicial( tecLocal.getNombre() + " " + tecLocal.getApellido() );
	
			// Creamos el Nuevo Registro
			Tecnico_peticionLocalHome tecPetiHome = (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			// Primero dejo como Reagendado el compromiso anterior.
	
			Collection c = tecPetiHome.finderByPeticionyAp( idPeticion,new Long(ApplicationConfig.getVariable("APP_ATST_ID") ));
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				Tecnico_peticionLocal tecPetiLocal = (Tecnico_peticionLocal) it.next();				
				if ( tecPetiLocal.getEstado()==null || tecPetiLocal.getEstado().intValue() == 1 ) {
					tecPetiLocal.setEstado( new Integer(0) );
					tecPetiLocal.setCausa_reagendamiento( crLocal );
				}
			}
	
			// Creo el Registro en la BD.
	
			Long idTecnicoPeticion = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_TECNICO_PETICION"));
			log.debug("Creando Tecnico");
			Tecnico_peticionLocal tecPetiLocal = tecPetiHome.create(idTecnicoPeticion, idPeticion, tecLocal);
			
			tecPetiLocal.setEstado(new Integer(1));
			
			//adocarmo - CR12844 - 23/05/08 - inicio
			//tecPetiLocal.setFecha(new Fecha().getTimestamp());
			tecPetiLocal.setFecha(new Timestamp(fechaDia.getTime()));
			//adocarmo - CR12844 - 23/05/08 - fin			

			tecPetiLocal.setHora_desde(horaDesde);
			tecPetiLocal.setHora_hasta(horaHasta);
			tecPetiLocal.setRango(rLocal);
			
			tecPetiLocal.setAp_id(new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
	
			// CR21938 - ana santos - inicio
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			tecPetiLocal.setFecha_agendamiento(t);
			tecPetiLocal.setNom_usua_logueado(usuario.getNombre());
			// CR21938 - ana santos - fin
			
			log.debug("Tecnico Creado");
	
		} catch (NamingException e) {
		} catch (FinderException e) {
		} catch (CreateException e) {
		}		
		
		return infoAgenda;
	}
	
	public ArrayList getListaAsignaciones(Long nroPeticion)
	{
		try
		{
			ArrayList listaAsig=new ArrayList();
			Tecnico_peticionLocalHome cpHome = (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal cpLocal = null;
			Collection c = cpHome.finderByPeticionyAp( nroPeticion,new Long(ApplicationConfig.getVariable("APP_ATST_ID") ));
			log.debug("Encontrado Asignacion Peticion [" + nroPeticion + "," + c.size() + "]");
			for (Iterator it=c.iterator(); it.hasNext(); ) {
			cpLocal = (Tecnico_peticionLocal) it.next();
			if ( cpLocal.getFecha() != null) {
				InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
				infoAgenda.setFechaCompromiso(  new Date(cpLocal.getFecha().getTime()) );
				RangoLocal rgLocal = cpLocal.getRango();
				infoAgenda.setNombreRango( rgLocal.getNombre_rango() );
				infoAgenda.setHoraDesde( rgLocal.getHora_desde() );
				infoAgenda.setHoraHasta( rgLocal.getHora_hasta() );

				// CR21938 - ana santos - inicio
				infoAgenda.setUsuario(cpLocal.getNom_usua_logueado());
				Date d = new Date();
				infoAgenda.setFechaAsignacion(cpLocal.getFecha_agendamiento());
				// CR21938 - ana santos - fin	

				TecnicoLocal tecLocal = cpLocal.getTecnico();
				TecnicoKey tecKey = (TecnicoKey ) tecLocal.getPrimaryKey();
				String nombreTecnico = tecLocal.getNombre() + " " + tecLocal.getApellido();
				infoAgenda.setIdTecnicoInicial( tecKey.cod_tecnico );
				infoAgenda.setNombreTecnicoInicial( nombreTecnico );

				if ( cpLocal.getEstado() != null && cpLocal.getEstado().intValue()==1 )
					infoAgenda.setDescripcionEstado( "Activo");
				else
					infoAgenda.setDescripcionEstado( "Reagendado");

				Causa_reagendamientoLocal crLocal = cpLocal.getCausa_reagendamiento();
				if ( crLocal != null )
					infoAgenda.setDescripcionCausa( crLocal.getCare_descripcion() );

				listaAsig.add( infoAgenda );
			}
		}
		return listaAsig;
	} catch (NamingException e) {
		return new ArrayList();
	} catch (FinderException e) {
		return new ArrayList();
	}
	}
	
	public ArrayList listaCausasReagendamiento()
	{
		try
		{
			ArrayList auxCausa=new ArrayList();
			Causa_reagendamientoLocalHome crHome = (Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
			Collection c = crHome.findAll();
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				Causa_reagendamientoLocal crLocal = (Causa_reagendamientoLocal) it.next();
				CausaDTO crDto = new CausaDTO();
				Causa_reagendamientoKey crKey = (Causa_reagendamientoKey) crLocal.getPrimaryKey();
				crDto.setCausId( crKey.care_id.intValue() );
				crDto.setCausNombre( crLocal.getCare_descripcion() );
				auxCausa.add( crDto );
			}
			return auxCausa;
		} catch (NamingException e) {
			return new ArrayList();
		} catch (FinderException e) {
			return new ArrayList();
		}
	}
	
	public ArrayList listaRangosAgendamiento()
	{
		try
		{
			ArrayList auxRango=new ArrayList();
			RangoLocalHome rHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			Collection c = rHome.findAll();
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				RangoLocal rLocal = (RangoLocal) it.next();
				RangoDTO rDto = new RangoDTO();
				RangoKey rKey = (RangoKey) rLocal.getPrimaryKey();
				rDto.setId( "" + rKey.id_rango );
				rDto.setCodigo( rLocal.getCodigo_rango() );
				rDto.setHoraDesde( rLocal.getHora_desde() );
				rDto.setHoraHasta( rLocal.getHora_hasta() );
				auxRango.add( rDto );
			}
			return auxRango;
		}
		catch (NamingException e)
		{
			return new ArrayList();	
		}
		catch (FinderException e)
		{
			return new ArrayList();
		}
	}

	public InformacionAgendamientoDTO recuperaAsignacion(Long nroPeticion)
	{
		log.debug("Recupera Asignacion para Peticion:"+nroPeticion);
		InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
		try
		{
			Tecnico_peticionLocalHome home=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal local=home.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
			infoAgenda.setCdpeticion( String.valueOf(nroPeticion) );
			Causa_reagendamientoLocal crLocal=local.getCausa_reagendamiento();
			if(crLocal!=null)
				infoAgenda.setDescripcionCausa( crLocal.getCare_descripcion() );
			else
				infoAgenda.setDescripcionCausa( "Cambio Tecnico" );
			infoAgenda.setFechaCompromiso( new Fecha(local.getFecha()).getDate() );
			infoAgenda.setHoraDesde( local.getHora_desde() );
			infoAgenda.setHoraHasta( local.getHora_hasta() );
			TecnicoLocal tecLocal=local.getTecnico();
			TecnicoKey key=(TecnicoKey) tecLocal.getPrimaryKey();
			infoAgenda.setIdTecnicoInicial( key.cod_tecnico );
			infoAgenda.setNombreTecnicoInicial( tecLocal.getNombre() + " " + tecLocal.getApellido() );
			
			// CR21938 - ana santos - inicio
			infoAgenda.setFechaAsignacion(local.getFecha_agendamiento());
			infoAgenda.setUsuario(local.getNom_usua_logueado());
			// CR21938 - ana santos - fin
			return infoAgenda;
		}
		catch(Exception e)
		{
			return infoAgenda;
		}
	}
	
	public void actualizarTecnico(String idTecnico,Long nroPeticion, UsuarioWeb usuario) throws ATiempoAppEx
	{
		TecnicoLocalHome tecHome;
		Tecnico_peticionLocalHome tecnicoPetHome;
		try
		{
			tecHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			tecnicoPetHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			TecnicoLocal tecLocal = tecHome.findByPrimaryKey( new TecnicoKey( idTecnico ) );
			Tecnico_peticionLocal tecnico_peticionLocal=tecnicoPetHome.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
			tecnico_peticionLocal.setTecnico(tecLocal);
			// CR21938 - ana santos - inicio
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			tecnico_peticionLocal.setFecha_agendamiento(t);
			tecnico_peticionLocal.setNom_usua_logueado(usuario.getNombre());
			// CR21938 - ana santos - fin
		}
		catch (NamingException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}
	
	public String tecnicoAsignadoPeticion(Long nroPeticion) throws ATiempoAppEx
	{
		Tecnico_peticionLocalHome tecnicoPetHome;
		try
		{
			tecnicoPetHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal tecnico_peticionLocal=tecnicoPetHome.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
			TecnicoKey key=(TecnicoKey) tecnico_peticionLocal.getTecnico().getPrimaryKey();
			return key.cod_tecnico;
		}
		catch (NamingException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(FinderException e)
		{
			return null;
		}
		}
}
