package co.com.telefonica.atiempo.vpistbba.accion_masiva.ejb.sb;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.ejb.Stateless;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.EmpresaDTO;
import co.com.atiempo.dto.EquipoDTO;
import co.com.atiempo.dto.EstadoPsPeticionDTO;
import co.com.atiempo.dto.ModemVpiDTO;
import co.com.atiempo.dto.ProductoServicioPeticionDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Causal_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_psKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_psLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionKey;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoKey;
import co.com.telefonica.atiempo.ejb.eb.RangoLocal;
import co.com.telefonica.atiempo.ejb.eb.RangoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoKey;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGService;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionPeticionDTO;
import co.com.telefonica.atiempo.vpistbba.dto.OdsListDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.genera_archivo.business.GeneraArchivoBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.genera_archivo.business.GeneraArchivoBusinessInterface;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocal;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: AccionMasivaServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
@Stateless
public class AccionMasivaServicesBean
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter implements AccionMasivaInterface{

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	public static final String queue = "jms/WF/VPISTBBA_ACCION_MASIVA_Q";
	private PeticionLocalHome petHome;
	private Causal_peticionLocalHome causalPetHome;
	private Estado_psLocalHome estadoPsHome;
	//	CR 10394 14/05/2008
	private Tecnico_peticionLocalHome tecnicoPeticionHome;
	private DBManager manager;
	private RangoLocalHome rangoHome;
	private TecnicoLocalHome tecnicoHome;
	

	public void ejecutaAccion(AccionMasivaMSGDTO aMDTO) throws ATiempoAppEx {
		Integer codigoAccion = aMDTO.getCodigoAccion();
		
//		Inicio @Trace		
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();
		CurrentExecution cExecution = null;
		TraceOperation traceOp = null;
		try
		{
			cExecution = traceManager.newCurrentExecution();						
			cExecution.init();
			traceOp = traceManager.newOperation(TraceManager.OP_EJECUCION_MASIVA_UNITARIA);
			traceOp.init(log);
			traceOp.setColumn(TraceManager.COL_TIPO_OPERACION, TraceAdapter.COL_TIPO_OP_INTERNA);
			traceOp.setColumn(TraceManager.COL_NUM_PET_ATIEMPO,aMDTO.getNumeroPeticion());
		  	traceManager.traceOpStart(traceOp);
			
			int codigoCierreGral = new Integer(VpistbbaConfig.getVariable("CODIGO_AM_CIERRE_GRAL")).intValue();
			int codigoCierrePGI = new Integer(VpistbbaConfig.getVariable("CODIGO_AM_CIERRE_PGI")).intValue();
			int codigoAccionMasivaCentral=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_CENTRAL")).intValue();
			int codigoAccionMasivaODSVPI=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_VPI")).intValue();
			int codigoAccionMasivaODSIN=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_IN")).intValue();
			int codigoAccionMasivaODSOUT=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_OUT")).intValue();
			int codigoAccionMasivaRepTerreno=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_TERRENO")).intValue();
			int codigoDesinstalacion=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_DESINSTALACION")).intValue();
			int codigoCtrlDesinstalacion=new Integer(VpistbbaConfig.getVariable("CODIGO_AM_CONTROL_DESINSTALACION")).intValue();
			
			if (codigoAccion.intValue()==codigoCierreGral)
			{
				String nomApp = ApplicationConfig.APP_VPISTBBA;
				
				PublicadorBandejaLocalHome pubBH = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
				PublicadorBandejaLocal pubL= pubBH.create();
			
				int cuentaPublicaciones=0;
			
				try{
					cuentaPublicaciones = pubL.cuentaPublicacionesVisiblesIgualImplCorrelID(aMDTO.getNumeroPeticion(),nomApp,aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
				}catch(Exception e){
					log.debug("Error al contar publicacions igual idCorrelativoidWF. Peticion:" + aMDTO.getNumeroPeticion() + " Actividad:" +aMDTO.getCodigoActividad());
					cuentaPublicaciones=0;
				}
			
				if (cuentaPublicaciones < 1){//Si no tengo nada que despublicar, esta dando jugo el usuario
					log.debug("No hago el cierre masivo de Peticion:" + aMDTO.getNumeroPeticion() + " Actividad:" +aMDTO.getCodigoActividad());
					//No hago la accion masiva
					return;
				}
				
				//Cierre masivo generico que no setea variables
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(aMDTO.getCodigoActividad());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				actividadEJB.terminar(actDto);
			}
			else if(codigoAccion.intValue()==codigoCierrePGI)
			{
				String nomApp = ApplicationConfig.APP_VPISTBBA;
				
				PublicadorBandejaLocalHome pubBH = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
				PublicadorBandejaLocal pubL= pubBH.create();
			
				int cuentaPublicaciones=0;
			
				try{
					cuentaPublicaciones = pubL.cuentaPublicacionesVisiblesIgualImplCorrelID(aMDTO.getNumeroPeticion(),nomApp,aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
				}catch(Exception e){
					log.debug("Error al contar publicacions igual idCorrelativoidWF. Peticion:" + aMDTO.getNumeroPeticion() + " Actividad:" +aMDTO.getCodigoActividad());
					cuentaPublicaciones=0;
				}
			
				if (cuentaPublicaciones < 1){//Si no tengo nada que despublicar, esta dando jugo el usuario
					log.debug("No hago el cierre masivo PGI de Peticion:" + aMDTO.getNumeroPeticion() + " Actividad:" +aMDTO.getCodigoActividad());
					//No hago la accion masiva
					return;
				}
				//Cierre masivo generico que soluciona quiebres de pgi y cierra la actividad
				boolean solucionable=procesaQuiebres(aMDTO.getNumeroPeticion());
				if(!solucionable)
					return; 
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
//				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(aMDTO.getNumeroPeticion(), aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(aMDTO.getCodigoActividad());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok,"S");
				actividadEJB.terminar(actDto);
			}
			//CR 10394 -Pablo Cawen- Inicio - 14/05/2008
			//Cierre masivo para actividades de desinstalacion
			else if(codigoAccion.intValue()==codigoDesinstalacion){
				Long nroPet = aMDTO.getNumeroPeticion();
				manager = new DBManager ();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
				if(tecnicoPeticionHome==null)
					tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
				Long idTecnicoPeticion = new Long(manager.seqNextValLong("ATIEMPO.CORRELATIVO_TECNICO_PETICION"));
				if(tecnicoHome==null)
					tecnicoHome = (TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
				TecnicoLocal tecLocal = tecnicoHome.findByPrimaryKey( new TecnicoKey( "Retiro" ) );
				Tecnico_peticionLocal tecPetiLocal = tecnicoPeticionHome.create(idTecnicoPeticion, nroPet, tecLocal);
				tecPetiLocal.setFecha(new Fecha().getTimestamp());
				if(rangoHome==null)
					rangoHome = (RangoLocalHome) HomeFactory.getHome(RangoLocalHome.JNDI_NAME);
				RangoLocal rLocal=rangoHome.findByPrimaryKey(new RangoKey(new Integer(1)));
				tecPetiLocal.setHora_desde(rLocal.getHora_desde());
				tecPetiLocal.setHora_hasta(rLocal.getHora_hasta());
				tecPetiLocal.setRango(rLocal);
				tecPetiLocal.setEstado(new Integer(1));
				tecPetiLocal.setAp_id(new Long(3)); //VPI
				
				//Cierre masivo para actividades de desinstalacion
		  		ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
		 		IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(aMDTO.getCodigoActividad());
		 		ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"S");
		 		actividadEJB.terminar(actDto);
			}
			//Cierre masivo para actividades de control de desinstalacion
			else if(codigoAccion.intValue()==codigoCtrlDesinstalacion){
				//Cargar valores por defecto en la instalacion 
				String qry = "SELECT DISTINCT CAUSAL_PETICION.ID_CAUSAL_PETICION " +
					"FROM ATIEMPO.ESTADO_PS_PETICION AS ESTADO_PS_PETICION, ATIEMPO.CAUSAL_PETICION AS CAUSAL_PETICION " +
					"WHERE ESTADO_PS_PETICION.PETI_NUMERO = ? AND ESTADO_PS_PETICION.CORRELATIVO = CAUSAL_PETICION.CORRELATIVO";
				Connection conn=null;
				PreparedStatement pstmt=null;
//				Statement stmt=null;
				ResultSet rs=null;
				try
				{
					conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
					conn.setReadOnly(true);
					conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
					conn.setAutoCommit(true);

					pstmt=conn.prepareStatement(qry);
					long nroPet = aMDTO.getNumeroPeticion().longValue();
					pstmt.setLong(1,nroPet);
					rs=pstmt.executeQuery();
					
					while(rs.next()){
						long idCausalPeticion = rs.getLong("ID_CAUSAL_PETICION");
						SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						String fecTermino = formateador.format(new Date());
						String qry2 = "UPDATE CAUSAL_PETICION "+
							"SET FECHA_TERMINO = ?	WHERE ID_CAUSAL_PETICION = ?";
						pstmt=conn.prepareStatement(qry2);
						pstmt.setString(1,fecTermino);
						pstmt.setLong(2,idCausalPeticion);
						rs=pstmt.executeQuery();
						
					}
						
				}
				finally
				{
					try
					{
						if(rs!=null) rs.close();
//						if(stmt!=null) stmt.close();
						if(pstmt!=null) pstmt.close();
						if(conn!=null) conn.close();
					}
					catch(Exception e)
					{
						log.error("Exception",e);
					}
				}
				
				
//				Cierre masivo para actividades de control de desinstalacion
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(aMDTO.getCodigoActividad());
				ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				actividadEJB.terminar(actDto);
				
			}
//			CR 10394 -Pablo Cawen- Fin - 14/05/2008
			else if(codigoAccion.intValue()==codigoAccionMasivaCentral)
			{
				procesarArchivoCentralUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}
			else if(codigoAccion.intValue()==codigoAccionMasivaODSVPI)
			{
				procesarArchivoODSVpiUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}
			else if(codigoAccion.intValue()==codigoAccionMasivaODSIN)
			{
				procesarArchivoODSInUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}
			else if(codigoAccion.intValue()==codigoAccionMasivaODSOUT)
			{
				procesarArchivoODSOutUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}
			else if(codigoAccion.intValue()==codigoAccionMasivaRepTerreno)
			{
				procesarReporteTerrenoUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}
		}
		catch (Exception e)
		{
			log.debug("Error en Accion Masiva.",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.EXCEPTION);
		}finally{
			if(traceManager!=null){
				traceManager.traceOpEnd(traceOp);
				traceManager.closeCurrentExecution(cExecution);
			}
		}
		
	}
	
	private static final String sqlCantidadATer="select count(a.peti_numero) as cantidad from vpistbba.peticion a where a.ESPE_ID=2 " +
		" and a.PETI_FECHA_TERMINO>? and a.PETI_FECHA_TERMINO<? " +
		" and A.TICA_ID=?";
	
	private static final String sqlCantidadATer2="select count(a.peti_numero) as cantidad from vpistbba.peticion a where a.ESPE_ID=2 " +
		" and a.PETI_FECHA_TERMINO>? and a.PETI_FECHA_TERMINO<? ";
		
		
//		TODO -- Se agregaron las columnas para obtener los nuevos tels de contacto -- CR-10120	
	private static final String sqlSacaReporteTer=" SELECT * FROM (" +
		" select T.PETI_NUMERO,T.COD_PET_CD,T.PETI_FECHA_INGRESO,T.COD_DPT,T.COD_LOC," +
		"T.COD_SGM_CTA_CD,T.COD_SBG_CTA_CD," +
		" T.NOM_DS,T.PRI_APE_DS,T.SEG_APE_DS," +
		"T.NOM_SLO_NO,T.TEL_COT_DS,T.TEL_COT_DS_1,T.TEL_COT_DS_2,T.NOM_INT_DS,T.PETI_FECHA_INGRESO,T.PETI_FECHA_TERMINO," +
		" T.PRI_APE_INT_DS,T.SEG_APE_INT_DS,T.NOM_CAL_DS," +
		"T.NUM_CAL_NU,T.DSC_CMP_PRI_DS,T.DSC_CMP_SEG_DS," +
		"T.PETI_ID_INSTANCIA,T.TICA_ID,row_number() " +
		"OVER (ORDER BY T.PETI_NUMERO ASC)  AS ROW " +
		"FROM VPISTBBA.PETICION T " +
		"WHERE T.TICA_ID=? AND T.PETI_FECHA_TERMINO>? AND T.PETI_FECHA_TERMINO<? ) " +
		"SUBTABLA WHERE ROW >=? AND ROW <=? ";
//		TODO -- Se agregaron las columnas para obtener los nuevos tels de contacto -- CR-10120	
	private static final String sqlSacaReporteTer2="SELECT * FROM (" +
		" select T.PETI_NUMERO,T.COD_PET_CD,T.PETI_FECHA_INGRESO,T.COD_DPT,T.COD_LOC," +
		"T.COD_SGM_CTA_CD,T.COD_SBG_CTA_CD," +
		" T.NOM_DS,T.PRI_APE_DS,T.SEG_APE_DS," +
		"T.NOM_SLO_NO,T.TEL_COT_DS,T.TEL_COT_DS_1,T.TEL_COT_DS_2,T.NOM_INT_DS,T.PETI_FECHA_INGRESO,T.PETI_FECHA_TERMINO," +
		" T.PRI_APE_INT_DS,T.SEG_APE_INT_DS,T.NOM_CAL_DS," +
		"T.NUM_CAL_NU,T.DSC_CMP_PRI_DS,T.DSC_CMP_SEG_DS," +
		"T.PETI_ID_INSTANCIA,T.TICA_ID,row_number() " +
		"OVER (ORDER BY T.PETI_NUMERO ASC)  AS ROW " +
		"FROM VPISTBBA.PETICION T " +
		"WHERE T.PETI_FECHA_TERMINO>? AND T.PETI_FECHA_TERMINO<? ) " +
		"SUBTABLA WHERE ROW >=? AND ROW <=?";


	private static final String sqlModem=" SELECT T.SERIAL FROM VPISTBBA.MODEM T WHERE T.PETI_NUMERO=? ";
	
	private static final String sqlSacaDecoTarjetasActivas=" SELECT T.ID_TARJETA, T.ID_DECO "+
		" FROM ATIEMPO.DECO_TARJETA T WHERE T.PETI_NUMERO=?" +
		" AND T.ESTADO=1 AND T.CODIGO_ERROR=0 AND T.ACCION=1 ";
	private static final String sqlSacaDecoTarjetas="SELECT T.ID_TARJETA, T.ID_DECO "+
		" FROM ATIEMPO.DECO_TARJETA T WHERE T.PETI_NUMERO=? AND T.ESTADO=1 AND T.CODIGO_ERROR=0";
	
	
	private ModemVpiDTO recuperaModems(long nroPeticion,Connection conn)	throws Exception
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			
			pstmt=conn.prepareStatement(sqlModem);
			pstmt.setLong(1,nroPeticion);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ModemVpiDTO modemVpiDTO=new ModemVpiDTO();
				modemVpiDTO.setSerial(rs.getString("SERIAL"));
				return modemVpiDTO;
			}
			return null;
		}
		finally
		{
			if(pstmt!=null) pstmt.close();
		}
	}
	
	private ArrayList recuperaDecoTarjetasActivas(long nroPeticion,Connection conn) throws Exception
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			ArrayList retorno=new ArrayList();
			pstmt=conn.prepareStatement(sqlSacaDecoTarjetasActivas);
			pstmt.setLong(1,nroPeticion);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				EquipoDTO eDto = new EquipoDTO();
				eDto.setIdDeco( rs.getString("ID_DECO") );
				eDto.setIdTarjeta( rs.getString("ID_TARJETA") );
				retorno.add(eDto);
			}
			return retorno;
		}
		finally
		{
			if(pstmt!=null) pstmt.close();
		}
	}
	
	private ArrayList recuperaDecoTarjetas(long nroPeticion,Connection conn) throws Exception
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			ArrayList retorno=new ArrayList();
			pstmt=conn.prepareStatement(sqlSacaDecoTarjetas);
			pstmt.setLong(1,nroPeticion);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				EquipoDTO eDto = new EquipoDTO();
				eDto.setIdDeco( rs.getString("ID_DECO") );
				eDto.setIdTarjeta( rs.getString("ID_TARJETA") );
				retorno.add(eDto);
			}
			return retorno;
		}
		finally
		{
			if(pstmt!=null) pstmt.close();
		}
	}
		
	public void procesarReporteTerrenoUsuario(Long idUser,String valores,String archiPre) throws Exception
	{
		String usuario=null;
		String fe1=null;
		String fe2=null;
		String tipo=null;
		Long cantidadUnica=null;
		StringTokenizer stringTokenizer=new StringTokenizer(valores,"#");
		int i=0;
		while(stringTokenizer.hasMoreTokens())
		{
			String token=stringTokenizer.nextToken();
			if(i==0)
				usuario=token;
			if(i==1)
				fe1=token;
			if(i==2)
				fe2=token;
			if(i==3)
				tipo=token;
			if(i==4)
				cantidadUnica=new Long(token);
			i++;
		}
		Fecha fecha1=new Fecha(fe1,"dd/MM/yyyy");
		Fecha fecha2=new Fecha(fe2,"dd/MM/yyyy");
		fecha2.addHour(24);
		String ruta="";
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;
		long cantidadParcela=new Long(VpistbbaConfig.getVariable("CANT_PARCE_TER")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_TER");
		int validarArchivoReciente=new Integer(VpistbbaConfig.getVariable("VAL_ARCHI_TER")).intValue();
		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;
		log.debug("Tiene Previo:"+tienePrevio);
		if(validarArchivoReciente==1 && tienePrevio==false)
		{
			boolean valor=yaTieneArchivoRecienteTer(usuario,1); 
			log.debug("Valor Control:"+valor);
			if(valor)
				return;
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		try
		{
			FileWriter fi=null;
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"ATer_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				fi=new FileWriter(ruta);
			}
			conn=DBManager.getConnection(DBManager.JDBC_VPISTBBA);

			//CR 16440 - Gustavo - Seteo conexi�n conn como readOnly y nivel de aislamiento
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			if(cantidadUnica==null)
			{
				if(!tipo.equals("0"))
					pstmt=conn.prepareStatement(sqlCantidadATer);
				else
					pstmt=conn.prepareStatement(sqlCantidadATer2);
	
				pstmt.setTimestamp(1,fecha1.getTimestamp());
				pstmt.setTimestamp(2,fecha2.getTimestamp());

				if(!tipo.equals("0"))
					pstmt.setString(3,tipo);

				rs=pstmt.executeQuery();
				if(rs.next())
					cantidad=rs.getLong("cantidad");
				cantidadUnica=new Long(cantidad);
			}
			else
				cantidad=cantidadUnica.longValue();
				
			log.debug("Cantidad:"+cantidad);
			valordesde=new Long(desde).longValue()+1;
			valorfinal=new Long(desde).longValue()+1+new Long(cantidadParcela).longValue();
			log.debug("cantidad +:"+valorfinal);
			if(cantidad>valorfinal)
				tieneSiguiente=true;
			
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
	
			if(!tipo.equals("0"))
			{
				pstmt=conn.prepareStatement(sqlSacaReporteTer);
				pstmt.setString(1,tipo);
				pstmt.setTimestamp(2,fecha1.getTimestamp());
				pstmt.setTimestamp(3,fecha2.getTimestamp());
				log.debug("valordesde:"+valordesde);
				log.debug("valorfinal:"+valorfinal);
				pstmt.setLong(4,valordesde);
				pstmt.setLong(5,valorfinal);
			}
			else
			{
				pstmt=conn.prepareStatement(sqlSacaReporteTer2);
				pstmt.setTimestamp(1,fecha1.getTimestamp());
				pstmt.setTimestamp(2,fecha2.getTimestamp());
				
				log.debug("valordesde:"+valordesde);
				log.debug("valorfinal:"+valorfinal);
				pstmt.setLong(3,valordesde);
				pstmt.setLong(4,valorfinal);
				
			}
			
	
			rs=pstmt.executeQuery();
			if(!tienePrevio)
			{
				StringBuffer buffer=new StringBuffer("")
				.append("NRO PETI_ATIS"+sep)
				.append("NRO PETI ATIEMPO"+sep)
				.append("COD DEPARTAMENTO"+sep)
				.append("DEPARTAMENTO"+sep)
				.append("COD LOCALIDAD"+sep)
				.append("LOCALIDAD"+sep)
				.append("COD CONTRATISTA"+sep)
				.append("CONTRATISTA"+sep)
				.append("COD SEGMENTO CUENTA"+sep)
				.append("SEGMENTO CUENTA"+sep)
				.append("COD SUB-SEGMENTO CUENTA"+sep)
				.append("SUB-SEGMENTO CUENTA"+sep)
				.append("NOMBRE CLIENTE"+sep)
				.append("TELEFONO CONTACTO CLIENTE"+sep)
//				TODO -- Dos nuevos cabezales para los tel de contacto -- Pablo L -- CR-10120
				.append("SEGUNDO TELEFONO DE CONTACTO"+sep)
				.append("TERCER TELEFONO DE CONTACTO"+sep)
				//TODO -- Fin cabezales
				
				.append("NOMBRE INTERLOCUTOR"+sep)
				.append("TELEFONO CONTACTO INTERLOCUTOR"+sep)
				.append("DIRECCION DE INSTALACION"+sep)
				.append("TIPO OPERACION COMERCIAL"+sep)
				.append("FAMILIA"+sep)
				
				.append("FECHA INI PET."+sep)
				.append("FECHA FIN PET."+sep)
				
				.append("ID PS"+sep)
				.append("DESCRIPCION PS"+sep)
				.append("ID OPCO"+sep)
				.append("OPERACION COMERCIAL"+sep)
				.append("FAMILIA PS"+sep)
				.append("FECHA INSTALACION"+sep)
				.append("COD_CAUSAL"+sep)
				.append("DESCRIPCION_CAUSAL"+sep)
				.append("ACTIVIDAD_QUIEBRE"+sep)
				.append("NOVEDAD"+sep)
				.append("COD_ESTADO_CIERRE"+sep)
				
				.append("SERIAL MODEM"+sep)
				.append("SERIAL DECO"+sep)
				.append("SERIAL TARJETA"+sep)
				
				.append("\n");
				fi.write(buffer.toString());
			}

			while(rs.next())
			{
				long petiNumero=rs.getLong("PETI_NUMERO");
				Long peLong=new Long(petiNumero);
				
				String tica=rs.getString("TICA_ID");

				String nombre=(String)sinNull(rs.getString("NOM_DS")," ");
				String direc=(String)sinNull(rs.getString("NOM_CAL_DS")," ");
				direc+=" "+(String)sinNull(rs.getString("NUM_CAL_NU")," ");
				direc+=" "+(String)sinNull(rs.getString("DSC_CMP_PRI_DS")," ");
				direc+=" "+(String)sinNull(rs.getString("DSC_CMP_SEG_DS")," ");
				nombre+=(String)sinNull(rs.getString("PRI_APE_DS")," ");
				nombre+=(String)sinNull(rs.getString("SEG_APE_DS")," ");
								
				String nombre2=(String)sinNull(rs.getString("NOM_INT_DS")," ");
				nombre2+=(String)sinNull(rs.getString("PRI_APE_INT_DS")," ");
				nombre2+=(String)sinNull(rs.getString("SEG_APE_INT_DS")," ");
				
				String departamento=recuperaDescDpt(rs.getString("COD_DPT"),conn);
				String localidad=recuperaDescLoc(rs.getString("COD_LOC"),conn);
				
				
				EmpresaDTO empresaDTO=recuperaEmpresa(petiNumero,conn);
				String EMPR_ID="";
				String EMPRESA_DESCRIPCION="";
				
				String segmentoDesc=recuperaDescSegmento(rs.getLong("COD_SGM_CTA_CD"),conn);
				String subsegmentoDesc=recuperaDescSubSegmento(rs.getLong("COD_SGM_CTA_CD"),conn);
				
				if(empresaDTO!=null)
				{
					EMPR_ID=empresaDTO.getEmprId().toString();
					EMPRESA_DESCRIPCION=empresaDTO.getEmpresaDescripcion();
				}
				
				StringBuffer buffer2=new StringBuffer("")
				.append(rs.getString("COD_PET_CD")+sep)
				.append(petiNumero+sep)
				.append(rs.getString("COD_DPT")+sep)
				.append(departamento+sep)
				.append(rs.getString("COD_LOC")+sep)
				.append(localidad+sep)
				.append(EMPR_ID+sep)
				.append(EMPRESA_DESCRIPCION+sep)
				.append(rs.getString("COD_SGM_CTA_CD")+sep)
				.append(segmentoDesc+sep)
				.append(rs.getString("COD_SBG_CTA_CD")+sep)
				.append(subsegmentoDesc+sep)
				.append(nombre+sep)
				.append(sinNull(rs.getString("TEL_COT_DS"),sep))
//				TODO -- Obtener los 2 nuevos tels de la consulta -- Pablo L -- CR-10120
				.append(sinNull(rs.getString("TEL_COT_DS_1"),sep))
				.append(sinNull(rs.getString("TEL_COT_DS_2"),sep))
				// TODO -- Fin obtener -- Pablo L
				.append(nombre2+sep)
				.append(sinNull(rs.getString("TEL_COT_DS"),sep))
				.append(direc+sep)
				.append(descTipoOp(rs.getString("TICA_ID"))+sep)
				.append(rs.getString("PETI_ID_INSTANCIA")+sep)
			
				.append(formatFecha(rs.getTimestamp("PETI_FECHA_INGRESO"))+sep)
				.append(formatFecha(rs.getTimestamp("PETI_FECHA_TERMINO"))+sep);
				
				ArrayList listaPsPet=recuperaPsPet(petiNumero,conn);
				ModemVpiDTO modem=recuperaModems(petiNumero,conn);
				ArrayList listaParejas=null;
				
				if(tica!=null && tica.equals("A"))
					listaParejas=recuperaDecoTarjetasActivas(petiNumero,conn);
				else
					listaParejas=recuperaDecoTarjetas(petiNumero,conn);
					
				
				int x=0;
				for(Iterator iterator=listaPsPet.iterator();iterator.hasNext();)
				{
					StringBuffer buffer3=new StringBuffer("");
					
					ProductoServicioPeticionDTO pspPetDto=(ProductoServicioPeticionDTO) iterator.next();
					buffer3=buffer3.append(pspPetDto.getPsId()+sep)
					.append(pspPetDto.getNombrePS()+sep)
					.append(pspPetDto.getOpcoId()+sep)
					.append(pspPetDto.getDescOperComer()+sep)
					.append(pspPetDto.getPsDesFam()+sep)
					.append(formatFecha(pspPetDto.getPspeFechaFin())+sep)
					;
					if(pspPetDto.getListaCausales().size()>0)
					{
						EstadoPsPeticionDTO estadoPsPeticionDTO=(EstadoPsPeticionDTO) pspPetDto.getListaCausales().iterator().next();
						buffer3=buffer3.append(estadoPsPeticionDTO.getCodCausa()+sep)
						.append(estadoPsPeticionDTO.getDescCausa()+sep)
						.append(estadoPsPeticionDTO.getDescAct()+sep)
						.append(estadoPsPeticionDTO.getNovedad()+sep)
						.append(estadoPsPeticionDTO.getCodEstadoCierre()+sep);
					}
					else
					{
						buffer3=buffer3.append(""+sep)
						.append(""+sep)
						.append(""+sep)
						.append(""+sep)
						.append(""+sep);
					}
					if(modem!=null && x<1)
						buffer3=buffer3.append(modem.getSerial()+sep);
					else
						buffer3=buffer3.append(""+sep);
					if(listaParejas.size()>0 && x<listaParejas.size())
					{
						EquipoDTO eDto=(EquipoDTO) listaParejas.get(x);
						buffer3=buffer3.append(eDto.getIdDeco()+sep);
						buffer3=buffer3.append(eDto.getIdTarjeta()+sep);
					}
					else
					{
						buffer3=buffer3.append(""+sep);
						buffer3=buffer3.append(""+sep);
					}
					x++;
					fi.write(buffer2.toString());
					fi.write(buffer3.toString());
					fi.write("\n");
				}
			}
	
			fi.close();
			log.debug("tiene Siguiente:"+tieneSiguiente);
			if(tieneSiguiente )
			{
				ArrayList pet=new ArrayList();
				AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
				aM.setNumeroPeticion(idUser);//Se pone el id del usuario
				aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_TERRENO")));
				String param=fe1+"#"+fe2+"#"+tipo+"#"+cantidadUnica;
				aM.setCodigoActividad(usuario+"#"+param);
				aM.setInstanciaActividad(ruta+"#"+valorfinal);
				pet.add(aM);
				enviaAccion(pet);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("Exception",e);
			throw e;
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				log.error("Exception",e);
			}
		}
	}
	
	private static final String sqlDescSubSeg=" select sub.DESCRIPCION from atiempo.subsegmento sub where sub.SUBSEGM_ID=? ";

	private String recuperaDescSubSegmento(long codSubSeg, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt=conn.prepareStatement(sqlDescSubSeg);
			pstmt.setLong(1,codSubSeg);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString("DESCRIPCION");
			return "";
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}

	private static final String sqlDescSegmento=" select seg.SEGM_DESCRIPCION from atiempo.segmento seg where seg.SEGM_ID=?  ";
	private String recuperaDescSegmento(long codSeg, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt=conn.prepareStatement(sqlDescSegmento);
			pstmt.setLong(1,codSeg);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString("SEGM_DESCRIPCION");
			return "";
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}

	private static final String sqlRecuperaEmpresa=" select tpet.ID_TECNICO,em.EMPR_ID,em.EMPRESA_DESCRIPCION" +
		"  from agenda.tecnico_peticion tpet,atiempo.empresa em,agenda.tecnico tec " +
		" where tpet.PETI_NUMERO=? and tpet.ESTADO=1 and tec.COD_TECNICO=tpet.ID_TECNICO " +
		" and em.EMPR_ID=tec.EMPR_ID ";
	private EmpresaDTO recuperaEmpresa(long petiNumero, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt=conn.prepareStatement(sqlRecuperaEmpresa);
			pstmt.setLong(1,petiNumero);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				EmpresaDTO empresaDTO=new EmpresaDTO();
				empresaDTO.setEmprId(new Long(rs.getLong("EMPR_ID")));
				empresaDTO.setEmpresaDescripcion(rs.getString("EMPRESA_DESCRIPCION"));
				return empresaDTO;
			}
			return null;
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}

	private static final String sqlDescLoc=" select loca.DESCRIPCION_LOCALIDAD from atiempo.localidad loca where loca.COD_LOC=? ";

	private String recuperaDescLoc(String codLoc, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt=conn.prepareStatement(sqlDescLoc);
			pstmt.setString(1,codLoc);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString("DESCRIPCION_LOCALIDAD");
			return "";
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}

	private static final String sqlDescDpt=" select depa.DESCRIPCION_DEPARTAMENTO from atiempo.departamento depa where depa.COD_DPT=? ";
	private String recuperaDescDpt(String codDepa, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt=conn.prepareStatement(sqlDescDpt);
			pstmt.setString(1,codDepa);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString("DESCRIPCION_DEPARTAMENTO");
			return "";
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}

	private static final String sqlPsPet="select psp.CORRELATIVO,psp.PS_ID,ps.PS_NOMBRE,psp.OPCO_ID,op.OPCO_NOMBRE,fam.FAPS_NOMBRE,psp.PSPE_FECHA_FIN " +
		" from vpistbba.producto_servicio_peticion psp,atiempo.familia_producto_servicio fam," +
		" atiempo.producto_servicio ps,atiempo.operacion_comercial op where psp.PETI_NUMERO=? " +
		" and psp.PS_ID=ps.PS_ID and fam.FAPS_ID=ps.FAPS_ID and op.OPCO_ID=psp.OPCO_ID";
			
	private ArrayList recuperaPsPet(long petiNumero, Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			ArrayList retorno=new ArrayList();
			pstmt=conn.prepareStatement(sqlPsPet);
			pstmt.setLong(1,petiNumero);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ProductoServicioPeticionDTO pspDto=new ProductoServicioPeticionDTO();
				pspDto.setPsId(new Long(rs.getLong("PS_ID")));
				pspDto.setNombrePS(rs.getString("PS_NOMBRE"));
				pspDto.setOpcoId(new Long(rs.getLong("OPCO_ID")));
				pspDto.setDescOperComer(rs.getString("OPCO_NOMBRE"));
				pspDto.setPsDesFam(rs.getString("FAPS_NOMBRE"));
				pspDto.setPspeFechaFin(rs.getTimestamp("PSPE_FECHA_FIN"));
				pspDto.setListaCausales(recuperaCausal(petiNumero,rs.getLong("CORRELATIVO"),conn));
				retorno.add(pspDto);
			}
			return retorno;
		}
		finally
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
	}
	
	private static final String sqlRecuperaCausal=" select eps.COD_CAUSAL,cc.DESCRIPCION_CAUSAL,ac.ACT_DESCRIPCION,eps.COD_ESTADO_CIERRE,eps.NOVEDAD" +
		"  from atiempo.estado_ps_peticion eps,atiempo.catalogo_causal cc,atiempo.actividad ac " +
		"  where eps.PETI_NUMERO=?  and eps.CORRE_PSPET=?  and eps.COD_CAUSAL=cc.COD_CAUSAL  and eps.COD_ACTIVIDAD=ac.ACT_ID ";
	
	private ArrayList recuperaCausal(long petiNumero, long correlativo,Connection conn) throws SQLException
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			ArrayList retorno=new ArrayList();
			pstmt=conn.prepareStatement(sqlRecuperaCausal);
			pstmt.setLong(1,petiNumero);
			pstmt.setLong(2,correlativo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				EstadoPsPeticionDTO epsp=new EstadoPsPeticionDTO();
				epsp.setCodCausa(new Long(rs.getLong("COD_CAUSAL")));
				epsp.setDescCausa(rs.getString("DESCRIPCION_CAUSAL"));
				epsp.setDescAct(rs.getString("ACT_DESCRIPCION"));
				epsp.setCodEstadoCierre(new Integer(rs.getInt("COD_ESTADO_CIERRE")));
				epsp.setNovedad(rs.getString("NOVEDAD"));
				retorno.add(epsp);
			}
			return retorno;
		}
		finally
		{
			if(pstmt!=null) pstmt.close();
		}
	}

	/**
	 * @param timestamp
	 * @return
	 */
	private String descTipoOp(String tipo)
	{
		if(tipo==null)
			return "";
		if(tipo.equals("A"))
			return "ALTA";
		else if(tipo.equals("P"))
			return "POSTVENTA";
		else if(tipo.equals("T"))
			return "TRANSFERENCIA";
		else if(tipo.equals("R"))
			return "RECONEXION";
		else if(tipo.equals("S"))
			return "SUSPENSION";
		return "";
	}
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private String formatFecha(Timestamp fecha)
	{
		if(fecha==null)
			return "";
		return sdf.format(fecha);
	}

	/**
	 * @param usuario
	 * @param i
	 * @return
	 */
	private boolean yaTieneArchivoRecienteTer(String usuario, int tipoArchivo)
	{
		Fecha fechahora=new Fecha();
		String valorRutaMasIsp=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_TER");
		long toleranciaArchivo=new Long(VpistbbaConfig.getVariable("TOLERANCIA_ARCHIVO_TER")).longValue();
		File file=new File(valorRutaMasIsp);
		switch(tipoArchivo)
		{
			case 1://Uno para reporte Terreno
				if(file.isDirectory())
				{
					File[] lista=file.listFiles();
					for(int i=0;i<lista.length;i++)
					{
						File archivo=lista[i];
						if(archivo.isFile())
						{
							String nombre=archivo.getName();
							
							if(!nombre.startsWith("ATer"))
								continue;
							if(nombre.indexOf(usuario)==-1)
								continue;
							nombre=nombre.substring(nombre.lastIndexOf("_")+1,nombre.lastIndexOf(".")-1);
							log.debug("Nuevo Nombre:"+nombre);
							try
							{
								Fecha fecha=new Fecha(nombre,"ddMMyyyyHHmmss");
								Long diferenciaEnHoras=fecha.differenceEnHoras(fechahora);
								log.debug("diferenciaEnHoras"+diferenciaEnHoras);
								if(diferenciaEnHoras.longValue()<toleranciaArchivo)
									return true;
							}
							catch (FechaException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				break;
		}
		return false;
	}

	private static final String sqlCantidadOI=" select count(bi.BI_NRO_PETICION ) as cantidad " +
	" from atiempo.bintegrada bi where bi.ACT_ID in ( 1023,1028, 1030 ) and bi.BI_VISIBLE=1 ";
	
	private static final String sqlCantidadOO=" select count(bi.BI_NRO_PETICION ) as cantidad " +
	" from atiempo.bintegrada bi where bi.ACT_ID =1039 and bi.BI_VISIBLE=1 ";
		
	public void procesarArchivoODSInUsuario(Long idUser, String usuario,String archiPre) throws Exception
	{
		String ruta="";
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;
		long cantidadParcela=new Long(VpistbbaConfig.getVariable("CANT_PARCE")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		int validarArchivoReciente=new Integer(VpistbbaConfig.getVariable("VAL_ARCHI_REN")).intValue();
		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;
		log.debug("Tiene Previo:"+tienePrevio);
		if(validarArchivoReciente==1 && tienePrevio==false)
		{
			boolean valor=yaTieneArchivoReciente(usuario,3); 
			log.debug("Valor Control:"+valor);
			if(valor)
				return;
		}
		
		ArrayList peticiones=new ArrayList();
		Connection conn=null;
		//Gustavo - CR 16440 -Cambio Statement stmt a PreparedStatement pstmt1 
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FileWriter fi=null;
		HashMap petVsAct=new HashMap();
		HashMap petVsEst=new HashMap();
		try
		{
			//Primero determino que peticiones tengo que enviar
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"AODI_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				fi=new FileWriter(ruta);
			}
	
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			pstmt=conn.prepareStatement(sqlCantidadOI);
			
			//pstmt.setLong(1,idUser.longValue());
			
			rs=pstmt.executeQuery();
			if(rs.next())
				cantidad=rs.getLong("cantidad");
			log.debug("Cantidad:"+""+cantidad);
			valordesde=new Long(desde).longValue()+1;
			valorfinal=new Long(desde).longValue()+1+new Long(cantidadParcela).longValue();
			log.debug("cantidad +:"+valorfinal);
			if(cantidad>valorfinal)
				tieneSiguiente=true;
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			

			//Gustavo - CR 16440 - Modifico la cadena para ejecutar de manera correcta el PreparedStatement
			String sqlSacaPeticionBandejaOdsInVisible="select * from (select bi.BI_NRO_PETICION,AA.ACT_DESCRIPCION,bi.BI_ESTADO_PETICION,row_number() over (order by bi.BI_NRO_PETICION asc ) as row" +
				" from atiempo.bintegrada bi,ATIEMPO.ACTIVIDAD AA where bi.ACT_ID in ( 1023,1028, 1030 ) and bi.BI_VISIBLE=1 and bi.AP_ID=3 " +
				" AND AA.ACT_ID=BI.ACT_ID ) subtabla where row >= ? and row <= ?";
			
			//Gustavo - CR 16440 - Ejecuto pstmt1 como PreparedStatement
			pstmt1=conn.prepareStatement(sqlSacaPeticionBandejaOdsInVisible);
			pstmt1.setLong(1,valordesde);
			pstmt1.setLong(2,valorfinal);
			rs=pstmt1.executeQuery();
			while(rs.next())
			{
				Long petId=new Long(rs.getLong("BI_NRO_PETICION"));
				peticiones.add(petId);
				petVsAct.put(petId,rs.getString("ACT_DESCRIPCION"));
				petVsEst.put(petId,rs.getString("BI_ESTADO_PETICION"));
			}
		}
		catch(Exception e)
		{
			log.error("Exception",e);
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt1!=null) pstmt1.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
		
			}
		}
		GeneraArchivoBusinessInterface gaInterface = new GeneraArchivoBusinessDelegate();
		ArrayList listaDto = gaInterface.getDatosODSOutBound( peticiones );
		if(!tienePrevio)
		{
			StringBuffer buffer=new StringBuffer("")
			.append("N�PETATIS" + sep)							//1
			.append("N�PETATIEMPO" + sep)						//2
			.append("ACTIVIDAD" + sep)						//2a
			.append("FLUJO" + sep)						//2b
			// CR10317 - ana - Inicio - 9May
			.append("NOMBRE" + sep)
			.append("NUMERO IDENTIFICACION" + sep)
			//	CR10317 - ana - Inicio - 9May
			.append("ODS" + sep)									//3
			.append("DEPARTAMENTO"+sep)							//4
			.append("LOCALIDAD"+sep)								//5
			.append("IDENTIFICADOR PC" + sep)					//6
			.append("FAMILIA PS" + sep)					//6a
			.append("TIPO OPCO" + sep)					//6b
			.append("FEDEVIS" + sep)								//7
			.append("JORNADA DESDE"+sep)							//8
			.append("JORNADA HASTA"+sep)						//8
			.append("NOMBRE CLIENTE"+sep)						//9
			.append("NUMERO IDENTIFICACION"+sep)					//10
			.append("CANAL"+sep)									//11
			.append("TELEFONO CONTACTO"+sep)						//12
//			TODO -- Dos nuevos cabezales para los tel de contacto -- Pablo L -- CR-10120
			.append("SEGUNDO TELEFONO DE CONTACTO"+sep)
			.append("TERCER TELEFONO DE CONTACTO"+sep)
			//TODO -- Fin cabezales		
			.append("TIPO DE USO"+sep)							//13
			.append("SEGMENTO"+sep)								//14
			.append("SUBSEGMENTO"+sep)							//15
			.append("FECHA REGISTRO"+sep)						//16
			.append("FECHA COMPROMISO"+sep)						//17
			.append("DIRECCION INSTALACION"+sep)					//18
			.append("BARRIO"+sep)								//19
			.append("CENTRAL D"+sep)								//20
			.append("NUMERO ABONADO D"+sep)						//21
			//		
			.append("Plan Televisi�n" + sep)						//85a
			.append("Plan Banda Ancha" + sep)					//85b
			//		
			.append("Id PS" + sep)								//86
			.append("Descripcion PS" + sep)						//87
			.append("Id Operacion Comercial" + sep)				//88
			.append("Descripcion Operacion Comercial" + sep)		//89
			.append("Cod Quiebre/Novedad"+sep)					//89a
			.append("Desc Quiebre/Novedad"+sep)					//89b
			.append("Observacion Quiebre/Novedad"+sep)			//89c
			//		
			.append("\n");

			fi.write(buffer.toString());
		}
		InformacionPeticionDTO informacionPeticionDTO  = null;
		for (Iterator iterator=listaDto.iterator();iterator.hasNext();)
		{
			OdsListDTO listDTO=(OdsListDTO) iterator.next();
			String estadoPet=(String)petVsEst.get(listDTO.getIdPeticion());
			String esp="";
			if(estadoPet!=null && estadoPet.equals("1"))
				esp="1";
			if(estadoPet!=null && estadoPet.equals("3"))
				esp="2";
			informacionPeticionDTO = listDTO.getInformacionPeticionDTO();
			StringBuffer buffer2=new StringBuffer("")
			.append( informacionPeticionDTO.getPeticionAtis() + sep)						//1
			.append( listDTO.getIdPeticion()+ sep)												//2
			.append( petVsAct.get(listDTO.getIdPeticion())+ sep)								//2a
			.append( esp+ sep)								//2b
			.append( sinNull(listDTO.getInformacionTecnicaDTO().LineaNueva.getOds(),sep) )				//3
			.append( sinNull(informacionPeticionDTO.getDscDepartamento(),sep) )			//4
			.append( sinNull(informacionPeticionDTO.getDscLocalidad(),sep) );				//5
			String idPc=informacionPeticionDTO.getIdentificadorSTB();
			if(idPc==null || idPc.equals(""))
				idPc=informacionPeticionDTO.getIdentificadorTV();
			buffer2=buffer2.append( sinNull(idPc,sep))
			.append( sinNull(informacionPeticionDTO.getFamiliaPs(),sep))
			.append( sinNull(informacionPeticionDTO.getTipoOp(),sep))															    //6
			.append( sinNull(listDTO.getFechaVisita(),sep))											//7
			.append( sinNull(listDTO.getHoraIni(),sep))												//8
			.append( sinNull(listDTO.getHoraFin(),sep))												//8
			.append( sinNull(informacionPeticionDTO.getNombreCliente(),sep))				//9
			.append( sinNull(informacionPeticionDTO.getIdCliente(),sep))					//10
			.append( sinNull(informacionPeticionDTO.getDscCanalVta(),sep))				//11
			.append( sinNull(informacionPeticionDTO.getTelContacto(),sep))				//12
//			TODO -- Dos nuevos gets para los tel de contacto -- Pablo L -- CR-10120
			.append(sinNull(informacionPeticionDTO.getSegTelContacto(),sep))
			.append(sinNull(informacionPeticionDTO.getTerTelContacto(),sep))
			//TODO -- Fin gets -- Pablo L
			.append( sinNull(informacionPeticionDTO.getNomTipUsoNoAgrupacionLinea(),sep))//13
			.append( sinNull(informacionPeticionDTO.getDscSegmento(),sep))				//14
			.append( sinNull(informacionPeticionDTO.getDscSubSegmen(),sep))				//15
			.append( sinNull(informacionPeticionDTO.getFecRegistro(),sep))				//16
			.append( sinNull(informacionPeticionDTO.getFecCompromiso(),sep))				//17
			.append( sinNull(informacionPeticionDTO.getDireccion(),sep))					//18
			.append( sinNull(informacionPeticionDTO.getBarrio(),sep))					//19
			.append( sinNull(listDTO.getCentralDes(),sep))											//20
			.append( sinNull(listDTO.getNumAbonDes(),sep))											//21

			.append( sinNull(listDTO.getInfoPlanDTO().getPlanTV(),sep))								//85a
			.append( sinNull(listDTO.getInfoPlanDTO().getPlanBA(),sep));								//85b
			

			for(Iterator iterator2=listDTO.getListadoPS().iterator();iterator2.hasNext();)
			{
				//log.debug("Tama�o resultado PS:"+listDTO.getListadoPS().size());	
				ProductoDTO productoDTO=(ProductoDTO) iterator2.next();
				StringBuffer buffer3=new StringBuffer("")
				.append( sinNull (productoDTO.getId() , sep ) )												//86
				.append( sinNull(productoDTO.getNombreProducto() , sep ) )									//87
				.append( sinNull(productoDTO.getIdOpComercial() , sep ) )									//88
				.append( sinNull(productoDTO.getOperacionComercial() , sep ))								//89
				.append( sinNull(productoDTO.getCodCausal(),sep) )								//89a
				.append( sinNull(productoDTO.getDescCausal(),sep) )								//89b
				.append( sinNull(productoDTO.getObservacionCausal(),"\n") );						//89c
				fi.write(buffer2.toString());	
				fi.write(buffer3.toString());
			}
		}
		
		fi.close();
		log.debug("tiene Siguiente:"+tieneSiguiente);
		if(tieneSiguiente )
		{
			ArrayList pet=new ArrayList();
			AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
			aM.setNumeroPeticion(idUser);//Se pone el id del usuario
			aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_IN")));
			aM.setCodigoActividad(usuario);
			aM.setInstanciaActividad(ruta+"#"+valorfinal);
			pet.add(aM);
			enviaAccion(pet);
		}
	}
	
	public void procesarArchivoODSOutUsuario(Long idUser, String usuario,String archiPre) throws Exception
	{
		String ruta="";
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;
		long cantidadParcela=new Long(VpistbbaConfig.getVariable("CANT_PARCE")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		int validarArchivoReciente=new Integer(VpistbbaConfig.getVariable("VAL_ARCHI_REN")).intValue();
		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;
		log.debug("Tiene Previo:"+tienePrevio);
		if(validarArchivoReciente==1 && tienePrevio==false)
		{
			boolean valor=yaTieneArchivoReciente(usuario,4); 
			log.debug("Valor Control:"+valor);
			if(valor)
				return;
		}
		ArrayList peticiones=new ArrayList();
		Connection conn=null;
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt1
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FileWriter fi=null;
		HashMap petVsFeA=new HashMap();
		try
		{
			//Primero determino que peticiones tengo que enviar
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"AODO_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				fi=new FileWriter(ruta);
			}
	
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			pstmt=conn.prepareStatement(sqlCantidadOO);
			
			//pstmt.setLong(1,idUser.longValue());
			rs=pstmt.executeQuery();
			if(rs.next())
				cantidad=rs.getLong("cantidad");
			log.debug("Cantidad:"+""+cantidad);
			valordesde=new Long(desde).longValue()+1;
			valorfinal=new Long(desde).longValue()+1+new Long(cantidadParcela).longValue();
			log.debug("cantidad +:"+valorfinal);
			if(cantidad>valorfinal)
				tieneSiguiente=true;
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			
			//Gustavo - CR 16440 - Modifico la sentencia para ejecutar de manera correcta el PreparedStatement
			String sqlSacaPeticionBandejaOdsOutVisible="select * from (select bi.BI_NRO_PETICION,bi.BI_FECHA_ASIGNACION, row_number() over (order by bi.BI_NRO_PETICION asc ) as row " +
			" from atiempo.bintegrada bi where bi.ACT_ID =1039 and bi.BI_VISIBLE=1 and bi.AP_ID=3  ) " +
			" subtabla where row >= ? and row <= ?";

			log.debug(sqlSacaPeticionBandejaOdsOutVisible);
			//Gustavo - CR 16440 - Ejecuto pstmt1 como PreparedStatement
			pstmt1=conn.prepareStatement(sqlSacaPeticionBandejaOdsOutVisible);
			pstmt1.setLong(1,valordesde);
			pstmt1.setLong(2,valorfinal);
			rs=pstmt1.executeQuery();
			while(rs.next())
			{
				Long petId=new Long(rs.getLong("BI_NRO_PETICION"));
				if(rs.getTimestamp("BI_FECHA_ASIGNACION")!=null)
				{
					petVsFeA.put(petId,new Fecha(rs.getTimestamp("BI_FECHA_ASIGNACION")).getFechaconFormato(9));	
				}
				peticiones.add(petId);
			}
		}
		catch(Exception e)
		{
			log.error("Exception",e);
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt1!=null) pstmt1.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
		
			}
		}
		GeneraArchivoBusinessInterface gaInterface = new GeneraArchivoBusinessDelegate();
		ArrayList listaDto = gaInterface.getDatosODSOutBound( peticiones );
		if(!tienePrevio)
		{
			StringBuffer buffer=new StringBuffer("")
			.append("N�PETATIS" + sep)							//1
			.append("N�PETATIEMPO" + sep)						//2
			.append("FECHA APERTURA" + sep)						//2a
			.append("ODS" + sep)									//3
			.append("DEPARTAMENTO"+sep)							//4
			.append("LOCALIDAD"+sep)								//5
			.append("IDENTIFICADOR PC" + sep)					//6
			.append("FAMILIA PS" + sep)					//6a
			.append("TIPO OPCO" + sep)					//6b
			.append("FEDEVIS" + sep)								//7
			.append("JORNADA DESDE"+sep)							//8
			.append("JORNADA HASTA"+sep)						//8
			.append("NOMBRE CLIENTE"+sep)						//9
			.append("NUMERO IDENTIFICACION"+sep)					//10
			.append("CANAL"+sep)									//11
			.append("TELEFONO CONTACTO"+sep)						//12
//			TODO -- Cabezales nuevos tels de contacto -- Pablo L -- CR-10120
			.append("SEGUNDO TELEFONO DE CONTACTO"+sep)						
			.append("TERCER TELEFONO DE CONTACTO"+sep)	
			//TODO -- Fin cabezales -- Pablo L	
			.append("TIPO DE USO"+sep)							//13
			.append("SEGMENTO"+sep)								//14
			.append("SUBSEGMENTO"+sep)							//15
			.append("FECHA REGISTRO"+sep)						//16
			.append("FECHA COMPROMISO"+sep)						//17
			.append("DIRECCION INSTALACION"+sep)					//18
			.append("BARRIO"+sep)								//19
			.append("CENTRAL D"+sep)								//20
			.append("NUMERO ABONADO D"+sep)						//21
			//		
			.append("Plan Televisi�n" + sep)						//85a
			.append("Plan Banda Ancha" + sep)					//85b
			//		
			.append("Id PS" + sep)								//86
			.append("Descripcion PS" + sep)						//87
			.append("Id Operacion Comercial" + sep)				//88
			.append("Descripcion Operacion Comercial" + sep)		//89
			.append("Cod Quiebre/Novedad"+sep)					//89a
			.append("Desc Quiebre/Novedad"+sep)					//89b
			.append("Observacion Quiebre/Novedad"+sep)			//89c
			//		
			.append("\n");

			fi.write(buffer.toString());
		}
		
		InformacionPeticionDTO informacionPeticionDTO = null;
		for (Iterator iterator=listaDto.iterator();iterator.hasNext();)
		{
			OdsListDTO listDTO=(OdsListDTO) iterator.next();
			String feA=(String) petVsFeA.get(listDTO.getIdPeticion());
			if(feA==null)
				feA="";
			informacionPeticionDTO = listDTO.getInformacionPeticionDTO();
			StringBuffer buffer2=new StringBuffer("")
			.append( informacionPeticionDTO.getPeticionAtis() + sep)						//1
			.append( listDTO.getIdPeticion()+ sep)														//2
			.append( feA+ sep)																			//2a
			.append( sinNull(listDTO.getInformacionTecnicaDTO().LineaNueva.getOds(),sep) )				//3
			.append( sinNull(informacionPeticionDTO.getDscDepartamento(),sep) )			//4
			.append( sinNull(informacionPeticionDTO.getDscLocalidad(),sep) );				//5
			String idPc=informacionPeticionDTO.getIdentificadorSTB();
			if(idPc==null || idPc.equals(""))
				idPc=informacionPeticionDTO.getIdentificadorTV();
			buffer2=buffer2.append( sinNull(idPc,sep))
			.append( sinNull(informacionPeticionDTO.getFamiliaPs(),sep))
			.append( sinNull(informacionPeticionDTO.getTipoOp(),sep))															    //6
			.append( sinNull(listDTO.getFechaVisita(),sep))											//7
			.append( sinNull(listDTO.getHoraIni(),sep))												//8
			.append( sinNull(listDTO.getHoraFin(),sep))												//8
			.append( sinNull(informacionPeticionDTO.getNombreCliente(),sep))				//9
			.append( sinNull(informacionPeticionDTO.getIdCliente(),sep))					//10
			.append( sinNull(informacionPeticionDTO.getDscCanalVta(),sep))				//11
			.append( sinNull(informacionPeticionDTO.getTelContacto(),sep))				//12
//			TODO -- Se agregaron los nuevos tels al buffer -- Pablo L -- CR-10120
			.append( sinNull(informacionPeticionDTO.getSegTelContacto(),sep))				
			.append( sinNull(informacionPeticionDTO.getTerTelContacto(),sep))				
			 // TODO -- Fin tels -- Pablo L
			.append( sinNull(informacionPeticionDTO.getNomTipUsoNoAgrupacionLinea(),sep))//13
			.append( sinNull(informacionPeticionDTO.getDscSegmento(),sep))				//14
			.append( sinNull(informacionPeticionDTO.getDscSubSegmen(),sep))				//15
			.append( sinNull(informacionPeticionDTO.getFecRegistro(),sep))				//16
			.append( sinNull(informacionPeticionDTO.getFecCompromiso(),sep))				//17
			.append( sinNull(informacionPeticionDTO.getDireccion(),sep))					//18
			.append( sinNull(informacionPeticionDTO.getBarrio(),sep))					//19
			.append( sinNull(listDTO.getCentralDes(),sep))											//20
			.append( sinNull(listDTO.getNumAbonDes(),sep))											//21

			.append( sinNull(listDTO.getInfoPlanDTO().getPlanTV(),sep))								//85a
			.append( sinNull(listDTO.getInfoPlanDTO().getPlanBA(),sep));								//85b
			

			for(Iterator iterator2=listDTO.getListadoPS().iterator();iterator2.hasNext();)
			{
				//log.debug("Tama�o resultado PS:"+listDTO.getListadoPS().size());	
				ProductoDTO productoDTO=(ProductoDTO) iterator2.next();
				StringBuffer buffer3=new StringBuffer("")
				.append( sinNull (productoDTO.getId() , sep ) )												//86
				.append( sinNull(productoDTO.getNombreProducto() , sep ) )									//87
				.append( sinNull(productoDTO.getIdOpComercial() , sep ) )									//88
				.append( sinNull(productoDTO.getOperacionComercial() , sep ))								//89
				.append( sinNull(productoDTO.getCodCausal(),sep) )								//89a
				.append( sinNull(productoDTO.getDescCausal(),sep) )								//89b
				.append( sinNull(productoDTO.getObservacionCausal(),"\n") );						//89c
				fi.write(buffer2.toString());	
				fi.write(buffer3.toString());
			}
		}
		
		fi.close();
		log.debug("tiene Siguiente:"+tieneSiguiente);
		if(tieneSiguiente )
		{
			ArrayList pet=new ArrayList();
			AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
			aM.setNumeroPeticion(idUser);//Se pone el id del usuario
			aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_OUT")));
			aM.setCodigoActividad(usuario);
			aM.setInstanciaActividad(ruta+"#"+valorfinal);
			pet.add(aM);
			enviaAccion(pet);
		}
		
	}
	
	
	
//	private static final String sqlSacaPeticionBandejaOdsOutVisible="select bi.BI_NRO_PETICION " +
//	" from atiempo.bintegrada bi where bi.ACT_ID =1039 and bi.BI_VISIBLE=1 " ;
	private static final String sqlCantidadOD=" select count(bi.BI_NRO_PETICION ) as cantidad " +
		" from atiempo.bintegrada bi where bi.ACT_ID in ( 1023,1022,1030,1027,1028,1029 ) and bi.BI_VISIBLE=1 " +
		" and bi.USUA_ID=? ";
	private static final String sqlSacaPeticionFlujo="SELECT T.PETI_NUMERO, T.OPCO_ID, T.ACTI_ID, T.PRSE_ID " +
					" FROM VPISTBBA.PETICION_FLUJO T,ATIEMPO.ACTIVIDAD AA " +
					" WHERE T.PETI_NUMERO=? AND AA.ACTI_ID=T.ACTI_ID AND AA.ACT_ID=?";
	
	public void procesarArchivoODSVpiUsuario(Long idUser, String usuario,String archiPre) throws Exception
	{
		String ruta="";
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;
		long cantidadParcela=new Long(VpistbbaConfig.getVariable("CANT_PARCE")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		
		String usa_chulo=VpistbbaConfig.getVariable("USA_CHULO");
		boolean usaChulo=false;
		if(usa_chulo!=null && usa_chulo.equals("S"))
			usaChulo=true;
		
		int validarArchivoReciente=new Integer(VpistbbaConfig.getVariable("VAL_ARCHI_REN")).intValue();
		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;
		log.debug("Tiene Previo:"+tienePrevio);
		if(validarArchivoReciente==1 && tienePrevio==false)
		{
			boolean valor=yaTieneArchivoReciente(usuario,2); 
			log.debug("Valor Control:"+valor);
			if(valor)
				return;
		}
		ArrayList peticiones=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		//Gustavo - CR 16440 - Cambio Statement stmt a PreparedStatement pstmt1
		PreparedStatement pstmt1=null;
		ResultSet rs=null;
		FileWriter fi=null;
		HashMap petVsAct=new HashMap(); 
		HashMap petVsEst=new HashMap();
		HashMap petVsIdAct=new HashMap();
		//	CR10317 - ana santos - 8/May - Inicio//
		HashMap petVsNomTec=new HashMap();
		HashMap petVsIdTec=new HashMap();
		//	CR10317 - ana santos - 8/May - Inicio//

		try
		{
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"AODS_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				fi=new FileWriter(ruta);
			}
			
			
			//Primero determino que peticiones tengo que enviar
			
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			pstmt=conn.prepareStatement(sqlCantidadOD);
			
			pstmt.setLong(1,idUser.longValue());
			rs=pstmt.executeQuery();
			if(rs.next())
				cantidad=rs.getLong("cantidad");
			log.debug("Cantidad:"+""+cantidad);
			valordesde=new Long(desde).longValue()+1;
			valorfinal=new Long(desde).longValue()+1+new Long(cantidadParcela).longValue();
			log.debug("cantidad +:"+valorfinal);
			if(cantidad>valorfinal)
				tieneSiguiente=true;
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			//	CR10317 - ana santos - 8/May - Inicio
			//	modifico la consulta para agregar los campos C�digo T�cnico asignado a la �ltima agenda activa y 
			//Nombre del T�cnico asignado a la �ltima agenda activa
			// 1 --> agenda activa, 0 --> reagendado

			//Gustavo - CR 16440 - Modifico la consulta para ser ejecutada como PreparedStatement
			String sqlSacaPeticionBandejaOdsVPIVisible=" select * from ( select tc.NOMBRE ,tc.COD_TECNICO," +			
			"bi.BI_NRO_PETICION,bi.ACT_ID,AA.ACT_DESCRIPCION,bi.BI_ESTADO_PETICION, row_number() over( order by  bi.BI_NRO_PETICION asc  ) as row " +			
			" from agenda.TECNICO tc, agenda.TECNICO_PETICION tc_pet, atiempo.bintegrada bi,ATIEMPO.ACTIVIDAD AA where tc.COD_TECNICO = tc_pet.ID_TECNICO " +			
			" and tc_pet.ESTADO = 1 and bi.ACT_ID in ( 1023,1022,1030,1027,1028,1029 )" +			
			" and bi.BI_VISIBLE=1  AND BI.ACT_ID=AA.ACT_ID and bi.USUA_ID= ? ) subtabla where row >= ? and row <= ?";

			// CR10371 - ana santos - 8/May - Fin
			log.debug(sqlSacaPeticionBandejaOdsVPIVisible);
			
			//Gustavo - CR 16440 - Ejecuto pstmt1 como PreparedStatement
			pstmt1=conn.prepareStatement(sqlSacaPeticionBandejaOdsVPIVisible);
			pstmt1.setLong(1,idUser.longValue());
			pstmt1.setLong(2,valordesde);
			pstmt1.setLong(3,valorfinal);
			rs=pstmt1.executeQuery();
			while(rs.next())
			{
				Long petId=new Long(rs.getLong("BI_NRO_PETICION"));
				peticiones.add(petId);
				petVsAct.put(petId,rs.getString("ACT_DESCRIPCION"));
				petVsEst.put(petId,rs.getString("BI_ESTADO_PETICION"));
				petVsIdAct.put(petId,rs.getString("ACT_ID"));
				//	CR10317 - ana santos - 8/May
				petVsNomTec.put(petId,rs.getString("NOMBRE"));
				petVsIdTec.put(petId,rs.getString("COD_TECNICO"));
				//	CR10371 - ana santos - 8/May - Fin
			}
		}
		catch(Exception e)
		{
			log.error("Exception",e);
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt1!=null) pstmt1.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				
			}
		}
		GeneraArchivoBusinessInterface gaInterface = new GeneraArchivoBusinessDelegate();
		log.debug("Antes de getdatosODS:"+new Fecha().getFechaconFormato());
		ArrayList listaDto=gaInterface.getDatosODSChulo(petVsIdAct);
		log.debug("Despues de getdatosODS:"+new Fecha().getFechaconFormato());
		if(!tienePrevio)
		{
			StringBuffer buffer=new StringBuffer("")
			.append("N�PETATIS" + sep)							//1
			.append("N�PETATIEMPO" + sep)						//2
			.append("ACTIVIDAD" + sep)							//2a
			.append("FLUJO"+sep)                                //2b
			//	CR10317 - ana santos - 8/May
			.append("NOMBRE TECNICO"+sep)
			.append("NUMERO IDENTIFICACION"+sep)
			.append("OBSERVACION PETICION"+sep)
			//	CR10371 - ana santos - 8/May - Fin		
			.append("ODS" + sep)								//3
			.append("DEPARTAMENTO"+sep)							//4
			.append("LOCALIDAD"+sep)								//5
			.append("IDENTIFICADOR PC" + sep)					//6
			.append("FAMILIA PS" + sep)					//6a
			.append("TIPO OPCO" + sep)					//6b
			.append("FEDEVIS" + sep)								//7
			.append("JORNADA DESDE"+sep)							//8
			.append("JORNADA HASTA"+sep)							//8
			.append("NOMBRE CLIENTE"+sep)						//9
			.append("NUMERO IDENTIFICACION"+sep)					//10
			.append("CANAL"+sep)									//11
			.append("TELEFONO CONTACTO"+sep)						//12
//			TODO -- Cabezales para los nuevos tels de contacto -- Pablo L -- CR-10120
			.append("SEGUNDO TELEFONO DE CONTACTO"+sep)						
			.append("TERCER TELEFONO DE CONTACTO"+sep)						
			//TODO -- Fin cabezales -- Pablo L 	
			.append("TIPO DE USO"+sep)							//13
			.append("SEGMENTO"+sep)								//14
			.append("SUBSEGMENTO"+sep)							//15
			.append("FECHA REGISTRO"+sep)						//16
			.append("FECHA COMPROMISO"+sep)						//17
			.append("DIRECCION INSTALACION"+sep)					//18
			.append("BARRIO"+sep)								//19
			.append("CENTRAL D"+sep)								//20a
			.append("CENTRAL DESC D"+sep)						//20b
			.append("NUMERO ABONADO D"+sep)						//21
			.append("LEN D"+sep)									//22
			.append("LEN ANTERIOR D"+sep)						//23
			.append("POSICION HORIZONTAL D"+sep)				//23a
			.append("DISTRIBUIDOR D"+sep)						//24
			.append("ARMARIO D"+sep)								//25
			.append("LISTON D"+sep)								//26
			.append("PAR LISTON D"+sep)							//27
			.append("CABLE D"+sep)								//28
			.append("PAR CABLE D"+sep)							//29
			.append("DIRECCION DISTRIBUIDOR D"+sep)				//30
			.append("TIPO CAJA "+sep)							//31
			.append("CAJA D"+sep)								//32
			.append("PAR CAJA D"+sep)							//33
			.append("DIRECCION CAJA D"+sep)						//34
			.append("PUERTO D"+sep)								//35
			.append("POTS D"+sep)								//36
			.append("ADSL D"+sep)								//37
			.append("MASCARA D"+sep)								//38
			.append("DIRECCION IP DSLAM D"+sep)					//39
			.append("DIRECCION IP LAN CLIENT D"+sep)				//40
			.append("DIRECCION IP WAN D"+sep)					//41
			.append("FRAME D"+sep)								//42
			.append("VPIVCI CLIENTE D"+sep)						//43
			.append("VPIVCI RED D"+sep)							//44
			.append("TARJETA D"+sep)								//45
			.append("CUENTA CORREO PADRE D"+sep)					//46
			.append("NUMERO DE PCS D"+sep)						//47
			.append("SISTEMA OPERATIVO D"+sep)					//48
			.append("PUERTO USB D"+sep)							//49
			.append("TARJETA RED D"+sep)							//50
			.append("MEMORIA RAM D"+sep)							//51
			.append("DISCO DURO D"+sep)							//52
			.append("CENTRAL O"+sep)								//53a
			.append("CENTRAL DESC O"+sep)						//53b
			.append("NUMERO DE ABONADO O"+sep)					//54
			.append("LEN O"+sep)									//55
			.append("LEN ANTERIOR O"+sep)						//56
			.append("POSICION HORIZONTAL O"+sep)				//56a
			.append("DISTRIBUIDOR O"+sep)						//57
			.append("ARMARIO O"+sep)								//58
			.append("LISTON O"+sep)								//59
			.append("PAR LISTON O"+sep)							//60
			.append("CABLE O"+sep)								//61
			.append("PAR CABLE O"+sep)							//62
			.append("DIRECCION DISTRIBUIDOR O"+sep)				//63
			.append("TIPO CAJA O"+sep)							//64
			.append("CAJA O"+sep)								//65
			.append("PAR CAJA O"+sep)							//66
			.append("DIRECCION CAJA"+sep)						//67
			.append("PUERTO O"+sep)								//68
			.append("POTS O"+sep)								//69
			.append("ADSL O"+sep)								//70
			.append("MASCARA O"+sep)								//71
			.append("DIRECCION IP DSLAM O"+sep)					//72
			.append("DIRECCION IP LAN CLIENT O"+sep)				//73
			.append("DIRECCION IP WAN O"+sep)					//74
			.append("FRAME 0"+sep)								//75
			.append("VPIVCI CLIENTE O"+sep)						//76
			.append("VPIVCI RED O"+sep)							//77
			.append("TARJETA O"+sep)								//78
			.append("CUENTA CORREO PADRE O"+sep)					//79
			.append("NUMERO DE PCS O"+sep)						//80
			.append("SISTEMA OPERATIVO O"+sep)					//81
			.append("PUERTO USB O"+sep)							//82
			.append("TARJETA DE RED O"+sep)						//83
			.append("MEMORIA RAM O"+sep)							//84
			.append("DISCO DURO O"+sep)							//85

			.append("Plan Televisi�n" + sep)						//85a
			.append("Plan Banda Ancha" + sep)					//85b

			.append("Id PS" + sep)								//86
			.append("Descripcion PS" + sep)						//87
			.append("Id Operacion Comercial" + sep)				//88
			.append("Descripcion Operacion Comercial" + sep)		//89

			.append("Cod Quiebre/Novedad"+sep)					//89i
			.append("Desc Quiebre/Novedad"+sep)					//89ii
			.append("Observacion Quiebre/Novedad"+sep)			//89iii
			
			;
			
			if(usaChulo)
				buffer=buffer.append("Gestionar"+sep);

			buffer=buffer.append("N�Serie Deco"+ sep)
			.append("Tipo de Deco"+ sep)						//89a
			.append("N�Serie Tarjeta"+ sep)						//89b

			.append("\n");

			fi.write(buffer.toString());
		}
		InformacionPeticionDTO informacionPeticionDTO = null;
		for (Iterator iterator=listaDto.iterator();iterator.hasNext();)
		{
			OdsListDTO listDTO=(OdsListDTO) iterator.next();
			String estadoPet=(String)petVsEst.get(listDTO.getIdPeticion());
			String esp="";
			if(estadoPet!=null && estadoPet.equals("1"))
				esp="1";
			if(estadoPet!=null && estadoPet.equals("3"))
				esp="2";
			informacionPeticionDTO = listDTO.getInformacionPeticionDTO();
			StringBuffer buffer2=new StringBuffer("")
			.append( informacionPeticionDTO.getPeticionAtis() + sep)						//1
			.append( listDTO.getIdPeticion()+ sep)													//2
			.append( petVsAct.get(listDTO.getIdPeticion())+ sep)													//2a
			.append( esp + sep)																//2b
			//	CR10317 - ana santos - 9/May- Inicio - agrego nombre del tecnico e identificacion
			.append(petVsNomTec.get(listDTO.getIdPeticion())+ sep)
			.append(petVsIdTec.get(listDTO.getIdPeticion())+ sep)
			.append( sinNull(informacionPeticionDTO.getObservacion(),sep))
			//	CR10317 - ana santos - 9/May- Fin
			.append( sinNull(listDTO.getInformacionTecnicaDTO().LineaNueva.getOds(),sep))				//3
			.append( sinNull(informacionPeticionDTO.getDscDepartamento(),sep))			//4
			.append( sinNull(informacionPeticionDTO.getDscLocalidad(),sep));				//5
			String idPc=informacionPeticionDTO.getIdentificadorSTB();
			if(idPc==null || idPc.equals(""))
				idPc=informacionPeticionDTO.getIdentificadorTV();
			buffer2=buffer2.append( sinNull(idPc,sep))															    //6
			.append( sinNull(informacionPeticionDTO.getFamiliaPs(),sep))
			.append( sinNull(informacionPeticionDTO.getTipoOp(),sep))
			.append( sinNull(listDTO.getFechaVisita(),sep))
			//.append( sinNull(listDTO.getFechaVisita(),sep))											//7
			.append( sinNull(listDTO.getHoraIni(),sep))												//8
			.append( sinNull(listDTO.getHoraFin(),sep))												//8
			.append( sinNull(informacionPeticionDTO.getNombreCliente(),sep))				//9
			.append( sinNull(informacionPeticionDTO.getIdCliente(),sep))					//10
			.append( sinNull(informacionPeticionDTO.getDscCanalVta(),sep))				//11
			.append( sinNull(informacionPeticionDTO.getTelContacto(),sep))				//12
//			TODO	Agregar los nuevos tels al buffer -- Pablo L -- CR-10120
			.append( sinNull(informacionPeticionDTO.getSegTelContacto(),sep))				
			.append( sinNull(informacionPeticionDTO.getTerTelContacto(),sep))
			//TODO Fin tels -- Pablo L	
			.append( sinNull(informacionPeticionDTO.getNomTipUsoNoAgrupacionLinea(),sep))//13
			.append( sinNull(informacionPeticionDTO.getDscSegmento(),sep))				//14
			.append( sinNull(informacionPeticionDTO.getDscSubSegmen(),sep))				//15
			.append( sinNull(informacionPeticionDTO.getFecRegistro(),sep))				//16
			.append( sinNull(informacionPeticionDTO.getFecCompromiso(),sep))				//17
			.append( sinNull(informacionPeticionDTO.getDireccion(),sep))					//18
			.append( sinNull(informacionPeticionDTO.getBarrio(),sep))					//19
			.append( sinNull(listDTO.getCentralDes(),sep))											//20a
			.append( sinNull(listDTO.getCentralDesDesc(),sep))										//20b
			.append( sinNull(listDTO.getNumAbonDes(),sep))											//21
			.append( sinNull(listDTO.getLenDes(),sep))												//22
			.append( sinNull(listDTO.getLenAntDes(),sep))												//23
			.append( sinNull(listDTO.getPosicionHorizontalDes(),sep) )								//23a
			.append( sinNull(listDTO.getDistDes(),sep))												//24
			.append( sinNull(listDTO.getArmarioDes(),sep))											//25
			.append( sinNull(listDTO.getListonDes(),sep))												//26
			.append( sinNull(listDTO.getParListonDes(),sep))											//27
			.append( sinNull(listDTO.getCableDes(),sep))												//28
			.append( sinNull(listDTO.getParCableDes(),sep))											//29
			.append( sinNull(listDTO.getDirecDistDes(),sep))											//30
			.append( sinNull(listDTO.getTipoCajaDes(),sep))											//31
			.append( sinNull(listDTO.getCajaDes(),sep))												//32
			.append( sinNull(listDTO.getParCajaDes(),sep))											//33
			.append( sinNull(listDTO.getDirecCajaDes(),sep))											//34
			.append( sinNull(listDTO.getPuertoDes(),sep))												//35
			.append( sinNull(listDTO.getPotsDes(),sep))												//36
			.append( sinNull(listDTO.getAdslDes(),sep))												//37
			.append( sinNull(listDTO.getMascaraDes(),sep))											//38
			.append( sinNull(listDTO.getDirecIpDslamDes(),sep))										//39
			.append( sinNull(listDTO.getDirecIpLanCliDes(),sep))										//40
			.append( sinNull(listDTO.getDirecIpWanDes(),sep))											//41
			.append( sinNull(listDTO.getFrameDes(),sep))												//42
			.append( sinNull(listDTO.getVpivciCliDes(),sep))											//43
			.append( sinNull(listDTO.getVpivciRedDes(),sep))											//44
			.append( sinNull(listDTO.getTarjetaDes(),sep))											//45
			.append( sinNull(listDTO.getCtaCorreoPaDes(),sep))										//46
			.append( sinNull(listDTO.getNumPcDes(),sep))												//47
			.append( sinNull(listDTO.getSisOpDes(),sep))												//48
			.append( sinNull(listDTO.getPtoUsbDes(),sep))												//49
			.append( sinNull(listDTO.getTarRedDes(),sep))												//50
			.append( sinNull(listDTO.getMemoriaRamDes(),sep))											//51
			.append( sinNull(listDTO.getDiscDuroDes(),sep))											//52
			.append( sinNull(listDTO.getCentralOri(),sep))											//53a
			.append( sinNull(listDTO.getCentralOriDesc(),sep))										//53b
			.append( sinNull(listDTO.getNumAbonOri(),sep))											//54
			.append( sinNull(listDTO.getLenOri(),sep))												//55
			.append( sinNull(listDTO.getLenAntOri(),sep))												//56
			.append( sinNull(listDTO.getPosicionHorizontalOri(),sep ) )								//56a
			.append( sinNull(listDTO.getDistOri(),sep))												//57
			.append( sinNull(listDTO.getArmarioOri(),sep))											//58
			.append( sinNull(listDTO.getListonOri(),sep))												//59
			.append( sinNull(listDTO.getParListonOri(),sep))											//60
			.append( sinNull(listDTO.getCableOri(),sep))												//61
			.append( sinNull(listDTO.getParCableOri(),sep))											//62
			.append( sinNull(listDTO.getDirecDistOri(),sep))											//63
			.append( sinNull(listDTO.getTipoCajaOri(),sep))											//64
			.append( sinNull(listDTO.getCajaOri(),sep)	)											//65
			.append( sinNull(listDTO.getParCajaOri(),sep)	)										//66
			.append( sinNull(listDTO.getDirecCajaOri(),sep))											//67
			.append( sinNull(listDTO.getPuertoOri(),sep)	)											//68
			.append( sinNull(listDTO.getPotsOri(),sep))												//69
			.append( sinNull(listDTO.getAdslOri(),sep))												//70
			.append( sinNull(listDTO.getMascaraOri(),sep))											//71
			.append( sinNull(listDTO.getDirecIpDslamOri(),sep))										//72
			.append( sinNull(listDTO.getDirecIpLanCliOri(),sep))										//73
			.append( sinNull(listDTO.getDirecIpWanOri(),sep))											//74
			.append( sinNull(listDTO.getFrameOri(),sep))												//75
			.append( sinNull(listDTO.getVpivciCliOri(),sep))											//76
			.append( sinNull(listDTO.getVpivciRedOri(),sep))											//77
			.append( sinNull(listDTO.getTarjetaOri(),sep))											//78
			.append( sinNull(listDTO.getCtaCorreoPaOri(),sep))									//79
			.append( sinNull(listDTO.getNumPcOri(),sep))												//80
			.append( sinNull(listDTO.getSisOpOri(),sep))												//81
			.append( sinNull(listDTO.getPtoUsbOri(),sep))												//82
			.append( sinNull(listDTO.getTarRedOri(),sep))												//83
			.append( sinNull(listDTO.getMemoriaRamOri(),sep))											//84
			.append( sinNull(listDTO.getDiscDuroOri(),sep))											//85
	
			.append( sinNull(listDTO.getInfoPlanDTO().getPlanTV(),sep))								//85a
			.append( sinNull(listDTO.getInfoPlanDTO().getPlanBA(),sep))								;//85b
	
			int i=0;
			for(Iterator iterator2=listDTO.getListadoPS().iterator();iterator2.hasNext();)
			{
				//log.debug("Tama�o resultado PS:"+listDTO.getListadoPS().size());	
				ProductoDTO productoDTO=(ProductoDTO) iterator2.next();
				StringBuffer buffer3=new StringBuffer()
				.append( productoDTO.getId() + sep )												//86
				.append( productoDTO.getNombreProducto() + sep )									//87
				.append( productoDTO.getIdOpComercial() + sep )									//88
				.append( productoDTO.getOperacionComercial() + sep )								//89
				.append( sinNull(productoDTO.getCodCausal(),sep) )								//89i
				.append( sinNull(productoDTO.getDescCausal(),sep) )								//89ii
				.append( sinNull(productoDTO.getObservacionCausal(),sep) );						//89iii
				if(usaChulo)
					buffer3=buffer3.append(logicaChulo(productoDTO)+sep);
				if(listDTO.getListadoDecos().size()>i)
				{
					EquipoDTO equipoDTO=(EquipoDTO) listDTO.getListadoDecos().get(i);
					buffer3=buffer3.append(equipoDTO.getIdDeco()+sep)
					.append(equipoDTO.getDecoReference()+sep)
					.append(equipoDTO.getIdTarjeta()+sep);
				}
				else
				{
					buffer3=buffer3.append(""+sep)
					.append(""+sep);
				}
				i++; 
				buffer3=buffer3.append("\n");
				fi.write(buffer2.toString());
				fi.write(buffer3.toString());
			}
		}
		
		fi.close();
		
		log.debug("tiene Siguiente:"+tieneSiguiente);
		if(tieneSiguiente )
		{
			ArrayList pet=new ArrayList();
			AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
			aM.setNumeroPeticion(idUser);//Se pone el id del usuario
			aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_VPI")));
			aM.setCodigoActividad(usuario);
			aM.setInstanciaActividad(ruta+"#"+valorfinal);
			pet.add(aM);
			enviaAccion(pet);
		}
	}
	
	/**
	 * @param productoDTO
	 * @return
	 */
	private String logicaChulo(ProductoDTO productoDTO)
	{
		if(productoDTO.isLlamaAlaActividad() && productoDTO.isEstaOK())
			return "SI";
		if(!productoDTO.isLlamaAlaActividad() && productoDTO.isEstaOK())
			return "";
		return "NO";
	}

	private String sinNull(Object valor,String sep)
	{
		if(valor==null)
			return ""+sep;
		return valor.toString()+sep;
	}


	private static final String sep="|";
	
	private static final String sqlCantidadAC=" select count(pet.PETI_NUMERO) as cantidad " +
		" from vpistbba.peticion pet,vpistbba.bitacora_peticion bi, " +
		" vpistbba.producto_servicio_peticion psp,atiempo.producto_servicio ps, " +
		" atiempo.familia_producto_servicio fam " +
		" where pet.PETI_NUMERO=bi.PETI_NUMERO and bi.USUA_ID=? " +
		" and bi.BIPE_FECHA_FIN is null and psp.PETI_NUMERO=pet.PETI_NUMERO " +
		" and ps.PS_ID=psp.PS_ID and fam.FAPS_ID=ps.FAPS_ID and fam.FAPS_ID in (1,300) ";

		private static final String sqlSacaCentral="select * from (select de.DESCRIPCION_DEPARTAMENTO,loc.NOMBRE_LOCALIDAD,cen.COD_CENTRAL, " +
			" cen.DESC_CENTRAL,pet.PETI_NUMERO,PET.COD_PET_CD,pet.TICA_ID," +
			" pet.NUM_IDE_NU_STB,re.LEN,re.LEN_ANTERIOR,ps.PS_ID,ps.PS_NOMBRE,op.OPCO_ID,op.OPCO_NOMBRE," +
			" pet.ESPE_ID,pet.PETI_FECHA_INGRESO,pet.PETI_ID_INSTANCIA,re.ODS_APSC,seg.SEGM_ID, " +
		
		
			//	CR10317 - ana santos - 2/May
			//	modifico la consulta para agregar los campos canal, C�digo del subsegmento cuenta, 
			//	Observaci�n y Descripci�n del subsegmento cuenta 		
			" pet.COD_CNL_VEN_CD, subseg.SUBSEGM_ID, pet.PETI_OBSERVACION , subseg.DESCRIPCION ," +		

			" seg.SEGM_DESCRIPCION, ag.COD_TIP_USO_CD,ag.NOM_TIP_USO_NO ,row_number() over (order by pet.peti_numero asc ) as row " +
			" from vpistbba.peticion pet,vpistbba.bitacora_peticion bi, " +
			" vpistbba.producto_servicio_peticion psp,atiempo.producto_servicio ps," +
			"	atiempo.familia_producto_servicio fam,atiempo.central cen," +
			" atiempo.recursos_linea_basica re,atiempo.departamento de," +
			" atiempo.localidad loc,atiempo.operacion_comercial op,atiempo.segmento seg," +
		
			//	CR10317 - ana santos - 2/May - agrego tabla Subsegmento		
			"atiempo.subsegmento subseg,"+		

			" vpistbba.subpeticion_atis sb,vpistbba.agrupacion_atis ag" +
			" where pet.PETI_NUMERO=bi.PETI_NUMERO" +
			" and sb.COD_PET_CD=psp.COD_PET_CD" +
			" and sb.COD_AGR_SUB_NU=psp.COD_AGR_SUB_NU" +
			" and sb.COD_SUB_CD=psp.COD_SUB_CD" +
			" and ag.COD_PET_CD=sb.COD_PET_CD" +
			" and ag.COD_AGR_SUB_NU=sb.COD_AGR_SUB_NU" +
			" and bi.USUA_ID=? and bi.BIPE_FECHA_FIN is null" +
			" and psp.PETI_NUMERO=pet.PETI_NUMERO" +
			" and ps.PS_ID=psp.PS_ID and fam.FAPS_ID=ps.FAPS_ID" +
			" and cen.COD_CENTRAL=pet.COD_CENTRAL and re.PETI_NUMERO=pet.PETI_NUMERO" +
			" and de.COD_DPT=pet.COD_DPT and loc.COD_LOC=pet.COD_LOC" +
			" and fam.FAPS_ID in (1,300) and op.OPCO_ID=psp.OPCO_ID" +
			" and seg.SEGM_ID=pet.COD_SGM_CTA_CD "+
		
			//	CR10317 - ana santos - 2/May - 
			"and subseg.SUBSEGM_ID=pet.COD_SBG_CTA_CD) " +		
		
			"subtabla where row >=? and row <=?";
	
	public void procesarArchivoCentralUsuario(Long idUser,String usuario,String archiPre) throws Exception
	{
		String ruta="";
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;
		long cantidadParcela=new Long(VpistbbaConfig.getVariable("CANT_PARCE")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		int validarArchivoReciente=new Integer(VpistbbaConfig.getVariable("VAL_ARCHI_REN")).intValue();
		//		CR10317 - ana santos - 2/May - Inicio
		String usa_chulo=VpistbbaConfig.getVariable("USA_CHULO");
		/**
		 * Variable para el campo gestionar 2008-00010317
		 */
		
		boolean usaChulo=false;
		
		if(usa_chulo!=null && usa_chulo.equals("S"))
			usaChulo=true;			
		//		CR10317 - ana santos - 2/May - Fin

		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;
		log.debug("Tiene Previo:"+tienePrevio);
		if(validarArchivoReciente==1 && tienePrevio==false)
		{
			boolean valor=yaTieneArchivoReciente(usuario,1); 
			log.debug("Valor Control:"+valor);
			if(valor)
				return;
		}
		//log.debug(urlReportes);
		Connection conn=null;
		PreparedStatement pstmt=null;
		//		CR10317 - ana santos - 2/May - Inicio
		PreparedStatement pstmt_chulo=null;
		//		CR10317 - ana santos - 2/May - Fin	
		Statement stmt=null;
		ResultSet rs=null;
		try
		{
			FileWriter fi=null;
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"Acen_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				fi=new FileWriter(ruta);
			}
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			pstmt=conn.prepareStatement(sqlCantidadAC);
			pstmt.setLong(1,idUser.longValue());
			rs=pstmt.executeQuery();
			if(rs.next())
				cantidad=rs.getLong("cantidad");
			log.debug("Cantidad:"+""+cantidad);
			valordesde=new Long(desde).longValue()+1;
			valorfinal=new Long(desde).longValue()+1+new Long(cantidadParcela).longValue();
			log.debug("cantidad +:"+valorfinal);
			if(cantidad>valorfinal)
				tieneSiguiente=true;
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			
			pstmt=conn.prepareStatement(sqlSacaCentral);
			pstmt.setLong(1,idUser.longValue());
			pstmt.setLong(2,valordesde);
			pstmt.setLong(3,valorfinal);
			
			rs=pstmt.executeQuery();
			if(!tienePrevio)
			{
				StringBuffer buffer=new StringBuffer("")
				.append("Departamento"+sep)
				.append("Localidad"+sep)
				.append("Central"+sep)
				.append("Descripcion Central"+sep)
				.append("Segmento"+sep)
				.append("Segmento Desc"+sep)
				//	CR10317 - ana santos - Inicio - 2/May - agrego las dos columnas de Subsegmento, canal y observacion
				.append("Subsegmento"+sep)
				.append("Subsegmento Desc"+sep)
				.append("Canal"+sep)
				.append("Observacion"+sep)
				//	CR10317 - ana santos - Fin - 2/May
				.append("Nro Peticion Atis"+sep)
				.append("Nro Peticion Atiempo"+sep)
				.append("Tipo OpCo"+sep)
				.append("Fecha"+sep)
				.append("Identificador"+sep)
				.append("ODS"+sep)
				.append("Len"+sep)
				.append("Len Anterior"+sep)
				.append("Id PS"+sep)
				.append("Descripcion PS"+sep)
				.append("Familia PS"+sep)
				.append("Id Operacion Comercial"+sep)
				.append("Descripcion Operacion Comercial"+sep)
				.append("Cod Tipo Uso"+sep)
				.append("Desc Tipo Uso"+sep)
				.append("Flujo"+sep);
				/**
				 * Adicion del campo getionar 2008-00010317
				 */
				//	CR10317 - ana santos - 2/May - Inicio
				if(usaChulo)
				buffer=buffer.append("Gestionar"+sep);				
				buffer=buffer.append("\n");
				//	CR10317 - ana santos - 2/May - Fin
				fi.write(buffer.toString());
			}
			
			while(rs.next())
			{
				String DESCRIPCION_DEPARTAMENTO=rs.getString("DESCRIPCION_DEPARTAMENTO");
				String NOMBRE_LOCALIDAD=rs.getString("NOMBRE_LOCALIDAD");
				String COD_CENTRAL=rs.getString("COD_CENTRAL");
				String DESC_CENTRAL=rs.getString("DESC_CENTRAL");
				String PETI_NUMERO=rs.getString("PETI_NUMERO");
				String COD_PET_CD=rs.getString("COD_PET_CD");
				String TICA_ID=rs.getString("TICA_ID");
				String PETI_FECHA_INGRESO=rs.getString("PETI_FECHA_INGRESO");
				String NUM_IDE_NU_STB=rs.getString("NUM_IDE_NU_STB");
				String ODS_APSC=rs.getString("ODS_APSC");
				String LEN=rs.getString("LEN");
				String LEN_ANTERIOR=rs.getString("LEN_ANTERIOR");
				String PS_ID=rs.getString("PS_ID");
				String FAMILIA_PS=rs.getString("PETI_ID_INSTANCIA");
				String PS_NOMBRE=rs.getString("PS_NOMBRE");
				String OPCO_ID=rs.getString("OPCO_ID");
				String OPCO_NOMBRE=rs.getString("OPCO_NOMBRE");
				String COD_TIP_USO_CD=rs.getString("COD_TIP_USO_CD");
				String NOM_TIP_USO_NO=rs.getString("NOM_TIP_USO_NO");
				
				String ESPE_ID=rs.getString("ESPE_ID");
				String esp="";
				if(ESPE_ID!=null && ESPE_ID.equals("1"))
					esp="1";
				if(ESPE_ID!=null && ESPE_ID.equals("3"))
					esp="2";
				String SEGM_ID=rs.getString("SEGM_ID");
				String SEGM_DESCRIPCION=rs.getString("SEGM_DESCRIPCION");
				//	CR10317 - ana santos - Inicio - 2/May - agrego las columnas de Subsegmento, canal y observacion						
				String SUBSEGM_ID=rs.getString("SUBSEGM_ID");
				String SUBSEGM_DESCRIPCION=rs.getString("DESCRIPCION");
				String COD_CNL_VEN_CD=rs.getString("COD_CNL_VEN_CD");
				String PETI_OBSERVACION=rs.getString("PETI_OBSERVACION");
				//	CR10317 - ana santos - Fin - 2/May					
				StringBuffer buffer2=new StringBuffer("")
				.append(sinNull(DESCRIPCION_DEPARTAMENTO,sep))
				.append(sinNull(NOMBRE_LOCALIDAD,sep))
				.append(sinNull(COD_CENTRAL,sep))
				.append(sinNull(DESC_CENTRAL,sep))
				.append(sinNull(SEGM_ID,sep))
				.append(sinNull(SEGM_DESCRIPCION,sep))
				//	CR10317 - ana santos - Inicio - 2/May - agrego las columnas de Subsegmento, canal y observacion
				.append(sinNull(SUBSEGM_ID,sep))
				.append(sinNull(SUBSEGM_DESCRIPCION,sep))
				.append(sinNull(COD_CNL_VEN_CD,sep))
				.append(sinNull(PETI_OBSERVACION,sep))
				//	CR10317 - ana santos - Fin - 2/May				
				.append(sinNull(COD_PET_CD,sep))
				.append(sinNull(PETI_NUMERO,sep))
				.append(sinNull(TICA_ID,sep))
				.append(sinNull(PETI_FECHA_INGRESO,sep))
				.append(sinNull(NUM_IDE_NU_STB,sep))
				.append(sinNull(ODS_APSC,sep))
				.append(sinNull(LEN,sep))
				.append(sinNull(LEN_ANTERIOR,sep))
				.append(sinNull(PS_ID,sep))
				.append(sinNull(PS_NOMBRE,sep))
				.append(sinNull(FAMILIA_PS,sep))
				.append(sinNull(OPCO_ID,sep))
				
				.append(sinNull(OPCO_NOMBRE,sep))
				.append(sinNull(COD_TIP_USO_CD,sep))
				.append(sinNull(NOM_TIP_USO_NO,sep))
				
				.append(sinNull(esp,sep));
				
				//	CR10317 - ana santos - 2/May - Inicio	
				if(usaChulo){
					pstmt_chulo = conn.prepareStatement("select * from VPISTBBA.PETICION_FLUJO o where o.acti_id= (select acti_id from atiempo.actividad where act_id in ( select act_id from vpistbba.bitacora_peticion where peti_numero = ? and bipe_fecha_fin is null)) and o.peti_numero=? and o.prse_id=? and o.opco_id=?");
			
					//pstmt_chulo.setInt(1,Integer.parseInt(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_CENTRAL")));
					pstmt_chulo.setLong(1,Long.parseLong(PETI_NUMERO)); 
					pstmt_chulo.setLong(2,Long.parseLong(PETI_NUMERO));
					pstmt_chulo.setLong(3,Long.parseLong(PS_ID));					
					pstmt_chulo.setLong(4,Long.parseLong(OPCO_ID));					
					ResultSet rs_chulo = pstmt_chulo.executeQuery();
					int peticiones_chulo=0;
					while(rs_chulo.next()){
						log.debug("peticiones_chulo = " + peticiones_chulo);
						peticiones_chulo++;
						log.debug("peticiones_chulo = " + peticiones_chulo);						
					}
					rs_chulo.close();	
					String gestionar ="";
					if(peticiones_chulo>0){
						gestionar = "SI";
					}else{
						gestionar = "NO";
					}
					buffer2=buffer2.append(gestionar+sep);
				}						
				buffer2=buffer2.append("\n");
				//	CR10317 - ana santos - 2/May - Fin
				//fi.write(buffer.toString());
				fi.write(buffer2.toString());
			}
			
			fi.close();
			log.debug("tiene Siguiente:"+tieneSiguiente);
			if(tieneSiguiente )
			{
				ArrayList pet=new ArrayList();
				AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
				aM.setNumeroPeticion(idUser);//Se pone el id del usuario
				aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_ARCHIVO_CENTRAL")));
				aM.setCodigoActividad(usuario);
				aM.setInstanciaActividad(ruta+"#"+valorfinal);
				pet.add(aM);
				enviaAccion(pet);
			}
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				log.error("Exception",e);
			}
		}
	}

	private boolean yaTieneArchivoReciente(String usuario, int tipoArchivo)
	{
		log.debug("Validando Archivo Reciente");
		Fecha fechahora=new Fecha();
		String valorRutaMasIsp=VpistbbaConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		long toleranciaArchivo=new Long(VpistbbaConfig.getVariable("TOLERANCIA_ARCHIVO")).longValue();
		File file=new File(valorRutaMasIsp);
		switch(tipoArchivo)
		{
			case 1://Uno para el archivo de centrales Acen_JENNIFER.SUAREZ_27092007163235.txt 
				if(file.isDirectory())
				{
					File[] lista=file.listFiles();
					for(int i=0;i<lista.length;i++)
					{
						File archivo=lista[i];
						if(archivo.isFile())
						{
							String nombre=archivo.getName();
							
							if(!nombre.startsWith("Acen"))
								continue;
//							if(nombre.startsWith("AODS"))
//								continue;
//							if(nombre.startsWith("AODI"))
//								continue;
//							if(nombre.startsWith("AODO"))
//								continue;
//							if(nombre.startsWith("ASTODS"))
//								continue;								
							if(nombre.indexOf(usuario)==-1)
								continue;
							nombre=nombre.substring(nombre.lastIndexOf("_")+1,nombre.lastIndexOf(".")-1);
							log.debug("Nuevo Nombre:"+nombre);
							try
							{
								Fecha fecha=new Fecha(nombre,"ddMMyyyyHHmmss");
								Long diferenciaEnHoras=fecha.differenceEnHoras(fechahora);
								log.debug("diferenciaEnHoras"+diferenciaEnHoras);
								if(diferenciaEnHoras.longValue()<toleranciaArchivo)
									return true;
							}
							catch (FechaException e)
							{
								e.printStackTrace();
							}							
						}
					}
				}
				break;
			case 2://Dos para el archivo de ODS AODS_ADMIN.VPI_28092007202234.txt 
				if(file.isDirectory())
				{
					File[] lista=file.listFiles();
					for(int i=0;i<lista.length;i++)
					{

						File archivo=lista[i];
						if(archivo.isFile())
						{
							String nombre=archivo.getName();
							if(!nombre.startsWith("AODS"))
								continue;
//							if(nombre.startsWith("Acen"))
//								continue;
//							if(nombre.startsWith("AODI"))
//								continue;
//							if(nombre.startsWith("AODO"))
//								continue;
//							if(nombre.startsWith("ASTODS"))
//								continue;								
							if(nombre.indexOf(usuario)==-1)
								continue;
							nombre=nombre.substring(nombre.lastIndexOf("_")+1,nombre.lastIndexOf(".")-1);
							log.debug("Nuevo Nombre:"+nombre);
							try
							{
								Fecha fecha=new Fecha(nombre,"ddMMyyyyHHmmss");
								Long diferenciaEnHoras=fecha.differenceEnHoras(fechahora);
								log.debug("diferenciaEnHoras"+diferenciaEnHoras);
								if(diferenciaEnHoras.longValue()<toleranciaArchivo)
									return true;
							}
							catch (FechaException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				break;
			case 3://In para el archivo de ODS AODI_28092007202234.txt
				if(file.isDirectory())
				{
					File[] lista=file.listFiles();
					for(int i=0;i<lista.length;i++)
					{

						File archivo=lista[i];
						if(archivo.isFile())
						{
							String nombre=archivo.getName();
							if(!nombre.startsWith("AODI"))
								continue;							
//							if(nombre.startsWith("Acen"))
//								continue;
//							if(nombre.startsWith("AODS"))
//								continue;
//							if(nombre.startsWith("AODO"))
//								continue;
//							if(nombre.startsWith("ASTODS"))
//								continue;								
							nombre=nombre.substring(nombre.lastIndexOf("_")+1,nombre.lastIndexOf(".")-1);
							log.debug("Nuevo Nombre:"+nombre);
							try
							{
								Fecha fecha=new Fecha(nombre,"ddMMyyyyHHmmss");
								Long diferenciaEnHoras=fecha.differenceEnHoras(fechahora);
								log.debug("diferenciaEnHoras"+diferenciaEnHoras);
								if(diferenciaEnHoras.longValue()<toleranciaArchivo)
									return true;
							}
							catch (FechaException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				break;
			case 4://Out para el archivo de ODS AODO_28092007202234.txt
				if(file.isDirectory())
				{
					File[] lista=file.listFiles();
					for(int i=0;i<lista.length;i++)
					{

						File archivo=lista[i];
						if(archivo.isFile())
						{
							String nombre=archivo.getName();
							if(!nombre.startsWith("AODO"))
								continue;
//							if(nombre.startsWith("Acen"))
//								continue;
//							if(nombre.startsWith("AODS"))
//								continue;
//							if(nombre.startsWith("AODI"))
//								continue;
//							if(nombre.startsWith("ASTODS"))
//								continue;								
							nombre=nombre.substring(nombre.lastIndexOf("_")+1,nombre.lastIndexOf(".")-1);
							log.debug("Nuevo Nombre:"+nombre);
							try
							{
								Fecha fecha=new Fecha(nombre,"ddMMyyyyHHmmss");
								Long diferenciaEnHoras=fecha.differenceEnHoras(fechahora);
								log.debug("diferenciaEnHoras"+diferenciaEnHoras);
								if(diferenciaEnHoras.longValue()<toleranciaArchivo)
									return true;
							}
							catch (FechaException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				break;
		}
		return false;
	}
	
	private Object sinNull(String obj, String sep)
	{
		if(obj==null)
			return ""+sep;
		return obj.trim()+sep;
	}

	public boolean procesaQuiebres(Long nroPet) throws Exception
	{
		boolean unaSolucion=false;
		String codigosCausalesCierre=VpistbbaConfig.getVariable("CAUSALES_CIERRE_AUTO");
		if(codigosCausalesCierre==null)
			return false;
		StringTokenizer stringTokenizer=new StringTokenizer(codigosCausalesCierre,";");
		ArrayList codigosCausales=new ArrayList();
		while(stringTokenizer.hasMoreTokens())
		{
			String token=stringTokenizer.nextToken();
			codigosCausales.add(token);
		}
		if(petHome==null)
			petHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		if(causalPetHome==null)
			causalPetHome=(Causal_peticionLocalHome) HomeFactory.getHome(Causal_peticionLocalHome.JNDI_NAME);
		if(estadoPsHome==null)
			estadoPsHome=(Estado_psLocalHome) HomeFactory.getHome(Estado_psLocalHome.JNDI_NAME);
		Estado_psLocal estado_psLocal=estadoPsHome.findByPrimaryKey(new Estado_psKey(new Long(0)));
		PeticionLocal local=petHome.findByPrimaryKey(new PeticionKey(nroPet));
		Collection estado_ps_peticion = null;
		for(Iterator iterator=local.getProducto_servicio_peticion().iterator();iterator.hasNext();)
		{
			Producto_servicio_peticionLocal ps=(Producto_servicio_peticionLocal) iterator.next();
			estado_ps_peticion = ps.getEstado_ps_peticion();
			if(estado_ps_peticion==null)
				continue; 
			if(estado_ps_peticion!=null && estado_ps_peticion.size()==0)
				continue;
			Estado_ps_peticionLocal estado=(Estado_ps_peticionLocal) estado_ps_peticion.iterator().next();
			Long codCausa=estado.getCod_causal();
			if(codigosCausales.contains(codCausa.toString()))
			{
				Estado_ps_peticionKey estado_ps_peticionKey=(Estado_ps_peticionKey) estado.getPrimaryKey();
				if(estado.getCausal_peticion()!=null)
				{
					Collection lista=causalPetHome.findCausalesDePsOrderDes(estado_ps_peticionKey.correlativo);
					if(lista!=null && lista.size()>0)
					{
						Causal_peticionLocal causal_peticionLocal=(Causal_peticionLocal) lista.iterator().next();
						causal_peticionLocal.setEstado_ps(estado_psLocal);
						causal_peticionLocal.setNovedad("(Solucionado por Accion Masiva)");
						Fecha f=new Fecha(new Date());
						causal_peticionLocal.setFecha_termino(f.getFechaconFormato(9));
					}
				}
				estado.setCod_estado_cierre(new Integer(0));
				estado.setNovedad("(Solucionado por Accion Masiva)");
				unaSolucion=true;
			}
			else
				continue;
		}
		if(unaSolucion)
			return true;
		return false;
	}

	public void enviaAccion(ArrayList peticiones) throws ATiempoAppEx{
		try{
			Iterator iter = peticiones.iterator();
			AccionMasivaMSGService aMS = new AccionMasivaMSGService();
			while (iter.hasNext()){
				AccionMasivaMSGDTO aM = (AccionMasivaMSGDTO)iter.next();
				log.debug("Enviando mensaje accion masiva Peticion:" + aM.getNumeroPeticion());
				String mensaje = aMS.marshall(aM);
				this.enviaMensaje(mensaje, aM.getNumeroPeticion());
			}
		}catch(Exception e)
		{
			log.debug("Error al enviar mensaje de accion Masiva",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	
	private void enviaMensaje(String mensaje, Long numeroPeticion) throws ATiempoAppEx{
		try{
			WFSessionLocal fmc;
			InitialContext ic = new InitialContext();
			WFSessionLocalHome home = (WFSessionLocalHome) HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
			fmc = home.create();
			fmc.enviarMensaje(queue, mensaje, numeroPeticion, WFSessionLocal.FLUJO_VPI);
		}catch(Exception e){
			log.debug("Error al enviar mensaje de accion Masiva",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

}
