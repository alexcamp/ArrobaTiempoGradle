package co.com.telefonica.atiempo.vpistbba.instalacion.ejb;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.CausaDTO;
import co.com.atiempo.dto.Elemento_PeticionVpiDTO;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraKey;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocal;
import co.com.telefonica.atiempo.ejb.eb.Causa_demoraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoKey;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocal;
import co.com.telefonica.atiempo.ejb.eb.Causa_reagendamientoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocal;
import co.com.telefonica.atiempo.ejb.eb.ControlvisitaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAgendamientoDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionControlVisitaDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InstalacionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.RangoDTO;
import co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;

/**
 * Bean implementation class for Enterprise Bean: InstalacionServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InstalacionServicesBean
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
	implements InstalacionServicesInterface {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#obtenerInfoInstalacion()
	 */
	 
	 /* (non-Javadoc)
	  * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	  */
//	  Los home
	//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
//	 private RangoLocalHome rangoHome;
//	 private Causa_reagendamientoLocalHome causaReagendamientoHome;
//	 private Tecnico_peticionLocalHome tecnicoPeticionHome;
//	 private TecnicoLocalHome tecnicoHome;
//	 private ControlvisitaLocalHome controlvisitaHome;
//	 private PeticionLocalHome peticionHome;
	 
	 //el dbmanager
	 private DBManager manager;
	 
	 public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException
	 {
		super.setSessionContext(ctx);
		manager = new DBManager ();
		manager.setDataSource(DBManager.JDBC_VPISTBBA);
		buscaHome ();
	 }
	 
	private void buscaHome()
	{
//		try
//		{
//			rangoHome=(RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
//			causaReagendamientoHome=(Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
//			tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
//			controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
//			tecnicoHome=(TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
//			peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//		}
//		catch(NamingException namingException)
//		{
//			log.error("Error en la inicializacion de los homes:"+namingException);
//		}
		}

	private static Logger log=Logger.getLogger(InstalacionServicesBean.class);
	 
	 
	 
	public InstalacionDTO obtenerInfoInstalacion() throws ATiempoAppEx {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#instalar()
	 */
	public void instalar() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#controlarInstalacion()
	 */
	public void controlarInstalacion() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#desinstalar()
	 */
	public void desinstalar() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#controlarDesinstalacion()
	 */
	public void controlarDesinstalacion() throws ATiempoAppEx {
	
	}
	
	
	public ArrayList listaRangosAgendamiento()
	{
		try
		{
			ArrayList auxRango=new ArrayList();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			RangoLocalHome rangoHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			Collection c = rangoHome.findAll();
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
			log.error(" Creacion de Local Home Nulls",e);
			return new ArrayList();	
		}
		catch (FinderException e)
		{
			return new ArrayList();
		}
	}
	public ArrayList listaCausasReagendamiento()
	{
		try
		{
			ArrayList auxCausa=new ArrayList();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Causa_reagendamientoLocalHome causaReagendamientoHome = (Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
			Collection c = causaReagendamientoHome.findAll();
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
			log.error(" Creacion de Local Home Nulls",e);
			return new ArrayList();
		} catch (FinderException e) {
			return new ArrayList();
		}
	}
	
	public ArrayList getListaAsignaciones(Long nroPeticion, UsuarioWeb usuario)
	{
		try
		{
			ArrayList listaAsig=new ArrayList();
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Tecnico_peticionLocalHome tecnicoPeticionHome= (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal cpLocal = null;
			Collection c = tecnicoPeticionHome.finderByPeticionyAp( nroPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
			//log.debug("Encontrado Asignacion Peticion [" + nroPeticion + "," + c.size() + "]");
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
		log.error(" Creacion de Local Home Nulls",e);
		return new ArrayList();
	} catch (FinderException e) {
		return new ArrayList();
	}
	}
	
	public InformacionAgendamientoDTO recuperaAsignacion(Long nroPeticion)
	{
		//log.debug("Recupera Asignacion para Peticion:"+nroPeticion);
		InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal local=tecnicoPeticionHome.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
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
			infoAgenda.setUsuario( local.getNom_usua_logueado());
			infoAgenda.setFechaAsignacion( local.getFecha_agendamiento());
			// CR21938 - ana santos - fin
			
			return infoAgenda;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			e.printStackTrace();
			return infoAgenda;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return infoAgenda;
		}
	}

	// CR21938 - ana santos - inicio
	public InformacionAgendamientoDTO grabarAsignacion(String nroPeticion,String codTecnico,String fComp,String nroRango,String nroCare, UsuarioWeb usuario)
	// CR21938 - ana santos - fin
	{
		InformacionAgendamientoDTO infoAgenda = new InformacionAgendamientoDTO();

		String horaDesde = "";
		String horaHasta = "";

		Integer idCare = new Integer(Utiles.sinNull(nroCare, "0"));
		Long idPeticion = new Long(Utiles.sinNull(nroPeticion, "0"));
		Integer idRango = new Integer( Utiles.sinNull(nroRango, "0") );
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDia = null;
		try {
			fechaDia = sdf.parse( fComp );
		} catch (ParseException e1) {
		}

		infoAgenda.setCdpeticion( nroPeticion );

		// CR21938 - ana santos - inicio
		infoAgenda.setUsuario(usuario.getNombre());
		Date d = new Date();
		infoAgenda.setFechaAsignacion(d);
		// CR21938 - ana santos - fin

		// Busco Datos del Rango.
		RangoLocal rLocal = null;
		if ( idRango.intValue() != 0) {
			try
			{
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				RangoLocalHome rangoHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
				rLocal = rangoHome.findByPrimaryKey(new RangoKey( idRango ));
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
				//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
				Causa_reagendamientoLocalHome causaReagendamientoHome = (Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
				crLocal = causaReagendamientoHome.findByPrimaryKey(new Causa_reagendamientoKey(idCare));
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
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			TecnicoLocal tecLocal = tecnicoHome.findByPrimaryKey( new TecnicoKey( codTecnico ) );
			infoAgenda.setIdTecnicoInicial( codTecnico );
			infoAgenda.setNombreTecnicoInicial( tecLocal.getNombre() + " " + tecLocal.getApellido() );
	
			// Creamos el Nuevo Registro
			// Primero dejo como Reagendado el compromiso anterior.
	
			Collection c = tecnicoPeticionHome.finderByPeticionyAp( idPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ) );
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				Tecnico_peticionLocal tecPetiLocal = (Tecnico_peticionLocal) it.next();				
				if ( tecPetiLocal.getEstado()==null || tecPetiLocal.getEstado().intValue() == 1 ) {
					tecPetiLocal.setEstado( new Integer(0) );
					tecPetiLocal.setCausa_reagendamiento( crLocal );
				}
			}
	
			// Creo el Registro en la BD.
			//Long id = new Long(1);
	
			Long idTecnicoPeticion = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_TECNICO_PETICION"));
			//log.debug("Creando Tecnico");
			Tecnico_peticionLocal tecPetiLocal = tecnicoPeticionHome.create(idTecnicoPeticion, idPeticion, tecLocal);
			tecPetiLocal.setEstado(new Integer(1));
			tecPetiLocal.setFecha(new Timestamp(fechaDia.getTime()));
			tecPetiLocal.setHora_desde(horaDesde);
			tecPetiLocal.setHora_hasta(horaHasta);
			tecPetiLocal.setRango(rLocal);
			
			// CR21938 - ana santos - inicio
			tecPetiLocal.setNom_usua_logueado(usuario.getUsername());
			//Timestamp t = new Timestamp(d.getYear(),d.getMonth(),d.getDay(),d.getHours(),d.getMinutes(),d.getSeconds(),d.get);
			Timestamp t = new Timestamp(d.getTime());
			tecPetiLocal.setFecha_agendamiento(t);
			// CR21938 - ana santos - fin

			tecPetiLocal.setAp_id(new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID")));
			
			//log.debug("Tecnico Creado");
	
		} catch (NamingException e) {
			log.debug(e);
		} catch (FinderException e) {
			log.debug(e);
		} catch (CreateException e) {
			log.debug(e);
		}		
		
		return infoAgenda;
	}
	
	public void actualizarTecnico(String idTecnico,Long nroPeticion,UsuarioWeb usuario) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			TecnicoLocal tecLocal = tecnicoHome.findByPrimaryKey( new TecnicoKey( idTecnico ) );
			Tecnico_peticionLocal tecnico_peticionLocal=tecnicoPeticionHome.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
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
			e.printStackTrace();
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch (FinderException e)
		{
			e.printStackTrace();
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
	}
	
	public String tecnicoAsignadoPeticion(Long nroPeticion) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			Tecnico_peticionLocal tecnico_peticionLocal=tecnicoPeticionHome.findByPetiAsigAp(nroPeticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
			TecnicoKey key=(TecnicoKey) tecnico_peticionLocal.getTecnico().getPrimaryKey();
			return key.cod_tecnico;
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(FinderException e)
		{
			return null;
		}
	}

	public void actualizaAgenda(String fComp, String nroRango, String nroCare, Long peticion) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			Causa_reagendamientoLocalHome causaReagendamientoHome=(Causa_reagendamientoLocalHome) HomeFactory.getHome(Causa_reagendamientoLocalHome.JNDI_NAME);
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
			RangoLocalHome rangoHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
			
			RangoLocal rLocal=rangoHome.findByPrimaryKey(new RangoKey(new Integer(nroRango)));
			Causa_reagendamientoLocal causa=causaReagendamientoHome.findByPrimaryKey(new Causa_reagendamientoKey(new Integer(nroCare)));
			Tecnico_peticionLocal tecnico_peticionLocal=tecnicoPeticionHome.findByPetiAsigAp(peticion,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
			tecnico_peticionLocal.setEstado( new Integer(0) );
			tecnico_peticionLocal.setCausa_reagendamiento( causa );
					
			Long idTecnicoPeticion = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_TECNICO_PETICION"));
			//log.debug("Creando Tecnico");
			Tecnico_peticionLocal tecPetiLocal = tecnicoPeticionHome.create(idTecnicoPeticion, peticion, tecnico_peticionLocal.getTecnico());
			tecPetiLocal.setEstado(new Integer(1));
			tecPetiLocal.setFecha(new Fecha(fComp,"dd/MM/yyyy").getTimestamp());
			tecPetiLocal.setHora_desde(rLocal.getHora_desde());
			tecPetiLocal.setHora_hasta(rLocal.getHora_hasta());
			tecPetiLocal.setRango(rLocal);

		}
		catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(FinderException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (FechaException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,e);
		}
		catch (CreateException e)
		{
			log.debug(e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
		
	}

	public void grabarControlVisita(Long petiNumero, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			ControlvisitaLocalHome controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				
			//creo tecnicoPeticionLocal
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);

			ControlvisitaLocal local=null;
			PeticionLocal peticion=null;
			PeticionKey peticionKey=new PeticionKey(petiNumero);
			try
			{
				peticion=peticionHome.findByPrimaryKey(peticionKey);
			}
			catch(FinderException finderException)
			{
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			}
			local=controlvisitaHome.create(new Fecha().getTimestamp(),peticion);
			local.setFechahora_llegada(fechaHoraVisitaDesde.getTimestamp());
			local.setFechahora_salida(fechaHoraVisitaHasta.getTimestamp());
			
			//busco el tecnico
			Collection colTecnico = null;
			try {
				PeticionKey psetKey=(PeticionKey) peticion.getPrimaryKey();
				
				colTecnico = tecnicoPeticionHome.finderByPeticionyAp(psetKey.peti_numero,new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID")));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EJBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FinderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (Iterator it=colTecnico.iterator(); it.hasNext(); ) {
				Tecnico_peticionLocal tecPetiLocal = (Tecnico_peticionLocal) it.next();
				if ( tecPetiLocal.getEstado()==null || tecPetiLocal.getEstado().intValue() == 1 ) {
					local.setTecnico(tecPetiLocal.getTecnico().getCod_tecnico());
				}
			}

		}
		catch(NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(CreateException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
	}

	public void grabarControlVisita(ArrayList listaVisitas) throws ATiempoAppEx
	{
		try
		{
			
			//Gustavo - CR 25403
			ControlvisitaLocalHome controlvisitaHome = (ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			if(controlvisitaHome==null)
				controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			if(peticionHome==null)
				peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				
			ControlvisitaLocal local=null;
			PeticionLocal peticion=null;
			for(Iterator i=listaVisitas.iterator();i.hasNext();){
				InformacionControlVisitaDTO informacionControlVisitaDto = (InformacionControlVisitaDTO) i.next();
				PeticionKey peticionKey=new PeticionKey(informacionControlVisitaDto.getPeticion());
				try
				{
					peticion=peticionHome.findByPrimaryKey(peticionKey);
				}
				catch(FinderException finderException)
				{
					throw new ATiempoAppEx(ATiempoAppEx.FINDER);
				}
				Long psId = new Long(informacionControlVisitaDto.getCodigoPs());
				local=controlvisitaHome.create(new Fecha().getTimestamp(),peticion);
				local.setFechahora_llegada(informacionControlVisitaDto.getFechaLlegada().getTimestamp());
				local.setFechahora_salida(informacionControlVisitaDto.getFechaSalida().getTimestamp());
				local.setPs_id(new Long(informacionControlVisitaDto.getCodigoPs()));
				local.setTecnico(informacionControlVisitaDto.getCodigoTecnico());
				local.setCod_caudem(new Long(informacionControlVisitaDto.getCodCausaDemora()));
				log.debug("Grabada la Visita para Ps " + informacionControlVisitaDto.getCodigoPs());
				
				//Gustavo - CR 25403 - Fin
			}
		}
		catch(NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(CreateException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
	}
	
	public InformacionControlVisitaDTO asignaTecnicoVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora)
	// CR21938 - ana santos - fin
	{
		// Si hay Causa de Reagendamiento, busco el entiti.
		InformacionControlVisitaDTO infoTecnicoVisita = new InformacionControlVisitaDTO();

		Long idPeticion = new Long(Utiles.sinNull(nroPeticion, "0"));

		infoTecnicoVisita.setPeticion(idPeticion );
		infoTecnicoVisita.setSFechaLlegada(fLlegada);
		infoTecnicoVisita.setSFechaSalida(fSalida);

		try {
			//TCS - CR25137 - Eliminación Homes de Instancia - Performance Assessment - atipoldi - May 21, 2009
			TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);

			//Buscamos el Tecnico Asociado.
				if(tecnicoHome==null)
					tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				if(tecnicoPeticionHome==null)
					tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
				TecnicoLocal tecLocal = tecnicoHome.findByPrimaryKey( new TecnicoKey( codTecnico ) );
				infoTecnicoVisita.setCodigoTecnico(codTecnico );
				infoTecnicoVisita.setNombreTecnicoInicial(tecLocal.getNombre() + " " + tecLocal.getApellido() );
			 
				Causa_demoraLocalHome causaDemoraHome = (Causa_demoraLocalHome)HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME);
				if(codCausaDemora!=null && codCausaDemora!="" && !"".equalsIgnoreCase(codCausaDemora) ){
					if(causaDemoraHome==null)
						causaDemoraHome=(Causa_demoraLocalHome) HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME) ;
					log.debug("Aca voy a cargar las causas de demora para el resultado");
					Causa_demoraLocal causaDemoraLocal = causaDemoraHome.findByPrimaryKey(new Causa_demoraKey(new Long(codCausaDemora)));
					infoTecnicoVisita.setCodCausaDemora(codCausaDemora);
					infoTecnicoVisita.setNomCausaDemora(causaDemoraLocal.getDescripcion_caudem());
				}
				else{
					infoTecnicoVisita.setCodCausaDemora("");
					infoTecnicoVisita.setNomCausaDemora("");
				}
				Producto_servicioLocalHome  productoServicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				if(productoServicioHome==null)
					productoServicioHome=(Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				log.debug("Aca voy a buscar los ps para pasar el resultado ");
				Producto_servicioLocal productoServicioLocal = productoServicioHome.findByPrimaryKey(new Producto_servicioKey(new Long(codPsPeticion)));
				infoTecnicoVisita.setCodigoPs(codPsPeticion);
				log.debug("Código PS" +infoTecnicoVisita.getCodigoPs());
				infoTecnicoVisita.setNomPS(productoServicioLocal.getPs_nombre());
				log.debug("Nombre PS " + infoTecnicoVisita.getNomPS());
			
			
			
		} catch (NamingException e) {
			log.debug("Aqui hay una excepcion Naming" + e);
		} catch (FinderException e) {
			log.debug("Aqui hay una excepcion Finder " + e);
		}
		if(infoTecnicoVisita!=null){
			log.debug("Retorna DTO con valores");
			return infoTecnicoVisita;
		}
		else{
			log.debug("Retorna DTO vacio");
			return null;
		}
	}

	public ArrayList getListaVisita(String nroPeticion,String codTecnico,String fLlegada,String fSalida,String codPsPeticion,String codCausaDemora)
	{
		try
		{
			ArrayList listaVisita=new ArrayList();
//			if(tecnicoPeticionHome==null)
//				tecnicoPeticionHome= (Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
//			Tecnico_peticionLocal cpLocal = null;
//			Collection c = tecnicoPeticionHome.finderByPeticionyAp( new Long(nroPeticion),new Long(ApplicationConfig.getVariable("APP_VPISTBBA_ID") ));
//			//log.debug("Encontrado Asignacion Peticion [" + nroPeticion + "," + c.size() + "]");
//			for (Iterator it=c.iterator(); it.hasNext(); ) {
//			cpLocal = (Tecnico_peticionLocal) it.next();
//			if ( cpLocal.getFecha() != null) {
				InformacionControlVisitaDTO infoTecnicoVisita = new InformacionControlVisitaDTO();
				infoTecnicoVisita.setPeticion(new Long(nroPeticion) );
				infoTecnicoVisita.setSFechaLlegada(fLlegada);
				infoTecnicoVisita.setSFechaSalida(fSalida);
				
				TecnicoLocalHome tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);

				if(tecnicoHome==null)
					tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				if(tecnicoPeticionHome==null)
					tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
				TecnicoLocal tecLocal = tecnicoHome.findByPrimaryKey( new TecnicoKey( codTecnico ) );
				infoTecnicoVisita.setCodigoTecnico(codTecnico );
				infoTecnicoVisita.setNombreTecnicoInicial(tecLocal.getNombre() + " " + tecLocal.getApellido() );
			
				Causa_demoraLocalHome causaDemoraHome = (Causa_demoraLocalHome)HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME);
				if(codCausaDemora!=null && codCausaDemora!="" && !"".equalsIgnoreCase(codCausaDemora) ){
					if(causaDemoraHome==null)
						causaDemoraHome=(Causa_demoraLocalHome) HomeFactory.getHome(Causa_demoraLocalHome.JNDI_NAME) ;
					Causa_demoraLocal causaDemoraLocal = causaDemoraHome.findByPrimaryKey(new Causa_demoraKey(new Long(codCausaDemora)));
					infoTecnicoVisita.setCodCausaDemora(codCausaDemora);
					infoTecnicoVisita.setNomCausaDemora(causaDemoraLocal.getDescripcion_caudem());
				}
				else{
					infoTecnicoVisita.setCodCausaDemora("");
					infoTecnicoVisita.setNomCausaDemora("");
				}
			
				Producto_servicioLocalHome  productoServicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				if(productoServicioHome==null)
					productoServicioHome=(Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal productoServicioLocal = productoServicioHome.findByPrimaryKey(new Producto_servicioKey(new Long(codPsPeticion)));
				infoTecnicoVisita.setCodigoPs(codPsPeticion);
				infoTecnicoVisita.setNomPS(productoServicioLocal.getPs_nombre());

				listaVisita.add( infoTecnicoVisita );
//			}
//		}
		return listaVisita;
	} catch (NamingException e) {
		return new ArrayList();
	} catch (FinderException e) {
		return new ArrayList();
	}
	}
	
	public InformacionControlVisitaDTO asignaTecnicoVisita(Long idPeticion, HttpServletRequest request) {
		log.debug("viene del controlador");
		String codTecnico = request.getParameter("asigna_codTecnico");
		String fecLlegada = request.getParameter("asigna_fechaLlegada");
		String fecSalida = request.getParameter("asigna_fechaSalida");
		String codPsPeticion = request.getParameter("asigna_codPsPeticion");
		String codCausaDemora = request.getParameter("asigna_codCausaDemora");
		if(codTecnico!=null){
			InformacionControlVisitaDTO inControlVisita = asignaTecnicoVisita(idPeticion.toString(), codTecnico, fecLlegada, fecSalida, codPsPeticion, codCausaDemora);
			log.debug("Retorna DTO con valores");
			return inControlVisita;
		}
		else{
			log.debug("Retorna DTO null");
			return null;
			
		}

	}
	
	public Elemento_PeticionVpiDTO grabarInternetEquipado(String nroPeticion, String tipoEquipo, String serialEquipo, UsuarioWeb usuario, String ps_id)  throws ATiempoAppEx{
		Elemento_PeticionLocal elementoPeticion = null;
		Elemento_PeticionVpiDTO elementoFinal = new Elemento_PeticionVpiDTO();
		
		try{
    		Elemento_PeticionLocalHome elemento_peticionLocalHome = (Elemento_PeticionLocalHome) HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
    		PeticionKey peticion_Key = new PeticionKey(new Long(nroPeticion));
    		PeticionLocalHome peticion_LocalHome =  (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticion_Local = peticion_LocalHome.findByPrimaryKey(peticion_Key); 
			
			elementoPeticion = (Elemento_PeticionLocal)elemento_peticionLocalHome.create(serialEquipo, peticion_Local, new Long(0), Short.valueOf("2"), tipoEquipo, "1", new Long(tipoEquipo), Long.valueOf(ps_id));
			elementoFinal.setSerial(elementoPeticion.getSerial());
			elementoFinal.setPeti_numero(Long.valueOf(nroPeticion));
			elementoFinal.setTipo_equipo(elementoPeticion.getTipo_equipo());
			
			
    	} catch (NamingException e) {
            log.debug("Creacion de Local Home Nulls: " + e);
        }catch(FinderException e){
        	log.debug("No encontro elemento: " + e);
		}catch(CreateException e){
        	log.debug("No se pudo crear objeto: " + e);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return elementoFinal;
	}	
}
