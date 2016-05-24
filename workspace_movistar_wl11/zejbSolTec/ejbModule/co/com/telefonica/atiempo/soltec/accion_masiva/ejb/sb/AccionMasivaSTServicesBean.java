package co.com.telefonica.atiempo.soltec.accion_masiva.ejb.sb;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.CodigoStLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.CodigoStLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.TipoCodigoStLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.TipoCodigoStLocalHome;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.soltec.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.soltec.dto.CodigoStDto;
import co.com.telefonica.atiempo.soltec.dto.PeticionStMasivaDTO;
import co.com.telefonica.atiempo.soltec.dto.PruebaLineaDTO;
import co.com.telefonica.atiempo.soltec.dto.TipoCodigoDto;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGService;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocal;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocalHome;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AccionMasivaSTServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
@Stateless
@Local
public class AccionMasivaSTServicesBean
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
	implements AccionMasivaSTInterface {

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	public static final String queue = "jms/WF/SOLTEC_ACCION_MASIVA_Q";


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface#ejecutaAccion(co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO)
	 */
	public void ejecutaAccion(AccionMasivaMSGDTO aMDTO) throws ATiempoAppEx {
		Integer codigoAccion = aMDTO.getCodigoAccion();
		try
		{
			log.debug("Ejecutando Accion Masiva.");
			int codigoAccionMasivaODSST=new Integer(STConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_ST")).intValue();
			int codigoCierreGral = new Integer(STConfig.getVariable("CODIGO_AM_CIERRE_GRAL")).intValue();

			if(codigoAccion.intValue()==codigoAccionMasivaODSST)
			{
				//en el codigo de la actividad, viene el usuario para el caso de la ods
				this.procesarArchivoODSSTUsuario(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad());
			}else if (codigoAccion.intValue()==codigoCierreGral)
			{
				String nomApp = ApplicationConfig.APP_ATST;
				
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
				ActividadFactorySTEJB actividadFactorySTEJB = new ActividadFactorySTEJB();
				IActividadEJB actividadSTEJB = actividadFactorySTEJB.getActividad(aMDTO.getCodigoActividad());
				ActividadEJBDTO actDto = actividadSTEJB.getActividadEJBDTO(aMDTO.getNumeroPeticion(),aMDTO.getCodigoActividad(),aMDTO.getInstanciaActividad(),null);
				if(STConfig.getVariable("COD_ACTIVIDAD_CONTROL_ACT_INV_STB").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el reintento de actualizar inventario STB
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"S");
				}else if(STConfig.getVariable("COD_ACTIVIDAD_CONTROL_ACT_INV_BA").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el cierre de actualizar inventario BA
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_BA.control_act_inv_ba,"S");
				}else if(STConfig.getVariable("COD_ACTIVIDAD_CONTROL_ACT_INV_TV").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el cierre de actualizar inventario TV
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_TV.control_act_inv_tv,"S");
				}else if(STConfig.getVariable("COD_ACTIVIDAD_STB_DIAGNOSTICO_MASIVO").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el cierre de diagnostico stb
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb, ""); 
					actDto.setObservacion("Se redirige a Planta Externa Autom�ticamente por acci�n masiva");
					actDto.setRealizarTerminoInmediato(true);
					actDto.setGrabaEnBitacora(false);
				}else if(STConfig.getVariable("COD_ACTIVIDAD_BA_DIAGNOSTICO").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el cierre de actualizar inventario TV
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_BA.solucion_ba, ""+ComunInterfaces.idActividadPEBA); 
					actDto.setObservacion("Se redirige a Planta Externa Autom�ticamente");
					actDto.setRealizarTerminoInmediato(true);
					actDto.setGrabaEnBitacora(false);
				}else if(STConfig.getVariable("COD_ACTIVIDAD_TV_DIAGNOSTICO").equals(aMDTO.getCodigoActividad())){
					//Seteo la variable para el cierre de actualizar inventario TV
					actDto.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_TV.solucion_tv, ""); 
					actDto.setObservacion("Se redirige a Planta Externa Autom�ticamente");
					actDto.setRealizarTerminoInmediato(true);
					actDto.setGrabaEnBitacora(false);
				}
				actividadSTEJB.terminar(actDto);
			}

		}catch (Exception e){
			log.debug("Error en Accion Masiva.",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.EXCEPTION);
		}
	}

//	private static final String sqlSacaPeticionBandejaOdsSTVisible="select bi.BI_NRO_PETICION " +
//	" from atiempo.bintegrada bi where bi.ACT_ID in ( 1056,1061, 1064 ) and bi.BI_VISIBLE=1 " +
//	" and bi.USUA_ID=? ";
	
	private static final String sqlCantidadODS=" select count(bi.BI_NRO_PETICION ) as cantidad " +
	" from atiempo.bintegrada bi where bi.ACT_ID in ( 1056,1061, 1064 ) and bi.BI_VISIBLE=1 " +
	" and bi.USUA_ID=? ";
	
	private static final String sep="|";	
	
	private void procesarArchivoODSSTUsuario(Long idUser, String usuario, String archiPre) throws Exception
	{
		
		boolean tieneSiguiente=false;
		long valordesde=0,valorfinal=0;
		long cantidad=0,desde=0;

		long cantidadParcela=new Long(STConfig.getVariable("CANT_PARCE")).longValue();
		log.debug("Parcela:"+cantidadParcela);
		String urlReportes=STConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		int validarArchivoReciente=new Integer(STConfig.getVariable("VAL_ARCHI_REN")).intValue();
		
		boolean tienePrevio=false;
		if(archiPre!=null && !archiPre.equals(""))
			tienePrevio=true;	
				
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
		String ruta="";
		HashMap petVsAct=new HashMap(); 
		try
		{
//			FileWriter fi=new FileWriter(urlReportes+"AODSST_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt");
			if(tienePrevio)
			{
				desde=new Long(archiPre.substring(archiPre.lastIndexOf("#")+1)).longValue();
				ruta=archiPre.substring(0,archiPre.lastIndexOf("#"));
				log.debug("RUTA pre:"+ruta);
				fi=new FileWriter(ruta,true);
			}
			else
			{
				ruta=urlReportes+"ASTODS_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt";
				log.debug("RUTA:"+ruta);
				fi=new FileWriter(ruta);
			}			
			//Primero determino que peticiones tengo que enviar
			
			conn=DBManager.getConnection(DBManager.JDBC_BANDEJA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			
			pstmt=conn.prepareStatement(sqlCantidadODS);
			
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
			
			//Gustavo - CR 16440 - Modifico la consulta para ejecutarse como PreparedStatement
			String sqlSacaPeticionBandejaOdsVPIVisible=" select * from ( select bi.BI_NRO_PETICION,AA.ACT_DESCRIPCION,bi.BI_ESTADO_PETICION, row_number() over( order by  bi.BI_NRO_PETICION asc  ) as row " +
			" from atiempo.bintegrada bi,ATIEMPO.ACTIVIDAD AA where bi.ACT_ID in ( 1056,1061, 1064 )" +
			" and bi.BI_VISIBLE=1  AND BI.ACT_ID=AA.ACT_ID and bi.USUA_ID= ? ) subtabla where row >= ? and row <= ? ";

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
//				petVsEst.put(petId,rs.getString("BI_ESTADO_PETICION"));
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
				if(pstmt!=null) pstmt.close();
				if(pstmt1!=null) pstmt1.close();
				if(conn!=null) conn.close();
			}
			catch(Exception e)
			{
				
			}
		}
		InfoComunColombiaBusinessInterface iC=new InfoComunColombiaBusinessDelegate();
		ArrayList listaDto=iC.getDatosPeticionMasivo( peticiones );;
//		FileWriter fi=new FileWriter(urlReportes+"AODSST_"+usuario+"_"+new Fecha().getDDMMYYYYHHMMSS()+".txt");
		StringBuffer buffer=new StringBuffer("")
		.append("N�PETATIS" + sep)							//1
		.append("N�PETATIEMPO" + sep)						//2
		.append("ACTIVIDAD" + sep)						//2a
		.append("DEPARTAMENTO"+sep)						//3
		.append("LOCALIDAD"+sep)							//4
		.append("IDENTIFICADOR PC" + sep)					//5
		.append("JORNADA DESDE"+sep)							//6
		.append("JORNADA HASTA"+sep)							//7
		.append("NOMBRE CLIENTE"+sep)						//8
		.append("NUMERO IDENTIFICACION"+sep)					//9
		.append("TELEFONO CONTACTO"+sep)						//10
		.append("FECHA REGISTRO"+sep)						//11
		.append("FECHA COMPROMISO"+sep)						//12
		.append("DIRECCION INSTALACION"+sep)					//13
		.append("CENTRAL D"+sep)								//14a
		.append("CENTRAL DESC D"+sep)						//14b
		.append("NUMERO ABONADO D"+sep)						//15
		.append("LEN D"+sep)									//16
		.append("POSICION HORIZONTAL D"+sep)				//16a
		.append("DISTRIBUIDOR D"+sep)						//17
		.append("ARMARIO D"+sep)								//18
		.append("LISTON D"+sep)								//19
		.append("PAR LISTON D"+sep)							//20
		.append("CABLE D"+sep)								//21
		.append("PAR CABLE D"+sep)							//22
		.append("DIRECCION DISTRIBUIDOR D"+sep)				//23
		.append("CAJA D"+sep)								//24
		.append("PAR CAJA D"+sep)							//25
		.append("DIRECCION CAJA D"+sep)						//26
		.append("PUERTO D"+sep)								//27
		.append("POTS D"+sep)								//28
		.append("ADSL D"+sep)								//29
		.append("MASCARA D"+sep)								//30
		.append("DIRECCION IP DSLAM D"+sep)					//31
		.append("DIRECCION IP LAN CLIENT D"+sep)				//32
		.append("DIRECCION IP WAN D"+sep)					//33
		.append("FRAME D"+sep)								//34
		.append("VPIVCI CLIENTE D"+sep)						//35
		.append("VPIVCI RED D"+sep)							//36			
		.append("ID PS" + sep)							//37
		.append("DESCRIPCION PS" + sep)						//38
		.append("DESCRIPCION RECLAMO" + sep)					//39
		.append("OBSERVACION RECLAMO" + sep)					//40
		.append("FECHA RECLAMO" + sep)						//41
		.append("DESCRIPCION DIAGNOSTICO" + sep)				//42
		.append("OBSERVACION DIAGNOSTICO" + sep)				//43
		.append("FECHA DIAGNOSTICO" + sep)					//44
		//TODO Inicio CR - 13247
		.append("PRIORIDAD" + sep)					
		 //TODO Fin CR - 13247
//		CR17031 pcawen
		.append("SEGMENTO" + sep)
		.append("SUB-SEGMENTO" + sep)
		.append("CATEGORIA" + sep)
//		CR17031 - Fin

		.append("\n");
		
		fi.write(buffer.toString());

		String str = "";
				
		for(Iterator iterator = listaDto.iterator();iterator.hasNext();)
		{
			PeticionStMasivaDTO peticion = (PeticionStMasivaDTO) iterator.next();
				
			str = "";
			str += sinNull(peticion.getCod_ave_cd(),sep);
			str += sinNull(peticion.getCod_ave_cd(),sep);
			str += sinNull(petVsAct.get(peticion.getCod_ave_cd()),sep);
			str += sinNull(peticion.getDescDepartamento() , sep );
			str += sinNull(peticion.getDescLocalidad() , sep );
			if (peticion.getNum_ide_nu()==null || peticion.getNum_ide_nu().equals(""))
			{
				str += sinNull(peticion.getNum_ide_nu_tv() , sep );
			}else{
				str += sinNull(peticion.getNum_ide_nu() , sep );
			}
			str += sinNull(peticion.getNum_ide_nu() , sep );
			str += sinNull(peticion.getHoraDesde() , sep );
			str += sinNull(peticion.getHoraHasta() , sep );
			str += sinNull(peticion.getNom_rte_sn() , sep );
			str += sinNull(peticion.getNum_doc_rte_nu() , sep );
			str += sinNull(peticion.getTel_cot_ds() , sep );
			str += sinNull(peticion.getFec_apt_ave_ts(),sep);
			String fechaCommit= "";
			if(peticion.getFechaCompromiso()!=null){
				Fecha fCom = new Fecha(peticion.getFechaCompromiso());
				fechaCommit = fCom.getFechaconFormato(9);
			}				
			str += sinNull(fechaCommit,sep );
			str += sinNull(peticion.getNom_cal_ds()+ " " + peticion.getNum_cal_nu() + " " + peticion.getDsc_cmp_pri_ds() + " " + peticion.getDsc_cmp_seg_ds(), sep );
			str += sinNull(peticion.getCodCentral() , sep );
			str += sinNull(peticion.getDescCentral() , sep );
			str += sinNull(peticion.getTelefono(),sep);
			str += sinNull(peticion.getLen() , sep );
			str += sinNull(peticion.getPosicionHorizontal(),sep);
			str += sinNull(peticion.getDistr() , sep );
			str += sinNull(peticion.getArmario() , sep );
			str += sinNull(peticion.getListon() , sep );
			str += sinNull(peticion.getParListon(),sep);
			str += sinNull(peticion.getCable(), sep);
			str += sinNull(peticion.getParCable(),sep);
			str += sinNull(peticion.getDirecDistr() , sep );
			str += sinNull(peticion.getCaja() , sep );
			str += sinNull(peticion.getParCaja() , sep );
			str += sinNull(peticion.getDirecCaja() , sep );
			str += sinNull(peticion.getPuertoActual() , sep );
			str += sinNull(peticion.getPostActual() , sep );
			str += sinNull(peticion.getAdslActual() , sep );
			str += sinNull(peticion.getMascLanActual() , sep );
			str += sinNull(peticion.getDirIpDslamActual() , sep );
			str += sinNull(peticion.getDirIpLanNva() , sep );
			str += sinNull(peticion.getDirIpWanActual() , sep );
			str += sinNull(peticion.getFrameActual() , sep );
			str += sinNull(peticion.getVpiVciActual() , sep );
			str += sinNull(peticion.getVpiVciRedActual() , sep );
			str += sinNull(peticion.getCodPsAveria(),sep) ;
			str += sinNull(peticion.getDescPsAveria() , sep );
				
			//Datos Reclamo
			TipoCodigoStLocalHome tipoCodigoStLocalHome = (TipoCodigoStLocalHome) HomeFactory.getHome(TipoCodigoStLocalHome.JNDI_NAME);
			TipoCodigoStLocal tipoCodigoStLocal = tipoCodigoStLocalHome.create();
			
			CodigoStLocalHome codigoStLocalHome = (CodigoStLocalHome) HomeFactory.getHome(CodigoStLocalHome.JNDI_NAME);
			CodigoStLocal codigoStLocal = codigoStLocalHome.create();
			//log.debug("Busco la descripcion de la Averia.");
			String desCodAve = "";
			if(peticion.getCod_apt_ave_cd() != null){
				
				TipoCodigoDto tipoCodigoDtoCod_apt_ave_cd = tipoCodigoStLocal.getTipoCodigoByAtributo("COD_APE_AVE_CD");
				
				if(tipoCodigoDtoCod_apt_ave_cd != null){
					CodigoStDto codigoStDtoCod_apt_ave_cd = codigoStLocal.getCodigoStByTipoAndCodigo(tipoCodigoDtoCod_apt_ave_cd.getTipo(),peticion.getCod_apt_ave_cd());
					desCodAve=codigoStDtoCod_apt_ave_cd.getDescripcion();
				}
			}
			String averiaDesc = peticion.getCod_apt_ave_cd();
			//log.debug("Desc Averia:" + desCodAve);
			if(desCodAve != null && !"".equals(desCodAve))
			{
				averiaDesc = averiaDesc +"["+desCodAve+"]";
			}
			str += sinNull(averiaDesc , sep );
			str += sinNull(peticion.getObs_ave_ds() , sep );
			Fecha fAve = new Fecha(peticion.getFec_apt_ave_ts());
			str += sinNull(fAve.getFechaconFormato(9) , sep );
				
			//Ultimo Diagnostico
			PruebaLineaDTO ultimaPrueba = iC.getUtlimaPruebaLineaByPeticion(peticion.getCod_ave_cd());
			if (ultimaPrueba ==null){
				ultimaPrueba= new PruebaLineaDTO();
			}
			str += sinNull(ultimaPrueba.getDescPrueba() , sep );
			str += sinNull(ultimaPrueba.getObservacion() , sep );
			String fechaPrub= "";
			if(ultimaPrueba.getFecha()!=null){
				Fecha fPrub = new Fecha(ultimaPrueba.getFecha());
				fechaPrub = fPrub.getFechaconFormato(9);
			}
			str += sinNull(fechaPrub , sep );
			// INICIO CR 13247
			try{
			
			TipoCodigoDto tipoCodigoDtoCod_est_ave_cd = tipoCodigoStLocal.getTipoCodigoByAtributo("COD_EST_AVE_CD");
			CodigoStDto codigoStDtoCod_pra_ave_cd = codigoStLocal.getCodigoStByTipoAndCodigo(tipoCodigoDtoCod_est_ave_cd.getTipo(),peticion.getCod_est_ave_cd());
			str += sinNull(peticion.getCod_pra_ave_cd()+"["+codigoStDtoCod_pra_ave_cd.getDescripcion()+"]", sep);
			
			}catch(FinderException e){
				log.debug("No se encontro codigo de la aver�a para la petici�n "+peticion.getPetiNumero());
			}
			// FIN CR 13247
				
//			CR17031 pcawen
			str += sinNull(peticion.getDescSgmto(),sep);
			str += sinNull(peticion.getDescSubSgmto(),sep);
			
			//Codigo y descripcion para categoria.
			String codCat = peticion.getCod_ctz_cd();
			if(codCat != null && !"".equals(codCat)){
				
				TipoCodigoDto tipoCodigoDtoCod_ctz_cd = tipoCodigoStLocal.getTipoCodigoByAtributo("COD_CTZ_CD");
				
				if(tipoCodigoDtoCod_ctz_cd != null){
					CodigoStDto codigoStDtoCod_ctz_cd = codigoStLocal.getCodigoStByTipoAndCodigo(tipoCodigoDtoCod_ctz_cd.getTipo(),codCat);
					
					str += codCat + "[" + sinNull(codigoStDtoCod_ctz_cd.getDescripcion(), "]")+ sep;
				}
			}
//			CR17031 - Fin
			
			fi.write( str );
			fi.write("\n");
		}				
		fi.close();
		log.debug("tiene Siguiente:"+tieneSiguiente);
		if(tieneSiguiente)
		{
			ArrayList pet=new ArrayList();
			AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
			aM.setNumeroPeticion(idUser);//Se pone el id del usuario
			aM.setCodigoAccion(new Integer(STConfig.getVariable("CODIGO_AM_ARCHIVO_ODS_ST")));
			aM.setCodigoActividad(usuario);
			aM.setInstanciaActividad(ruta+"#"+valorfinal);
			pet.add(aM);
			enviaAccion(pet);
		}		
	}

	private boolean yaTieneArchivoReciente(String usuario, int tipoArchivo)
	{
		log.debug("Validando Archivo Reciente");
		Fecha fechahora=new Fecha();
		String valorRutaMasIsp=STConfig.getVariable("URL_ACCION_ARCHIV_CEN");
		long toleranciaArchivo=new Long(STConfig.getVariable("TOLERANCIA_ARCHIVO")).longValue();
		File file=new File(valorRutaMasIsp);
		switch(tipoArchivo)
		{
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
							if(!nombre.startsWith("ASTODS"))
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
	
	private String sinNull(Object valor,String sep)
	{
		if(valor==null)
			return ""+sep;
		return valor.toString()+sep;
	}	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface#enviaAccion(java.util.ArrayList)
	 */
	public void enviaAccion(ArrayList peticiones) throws ATiempoAppEx {
		try{
			Iterator iter = peticiones.iterator();
			AccionMasivaMSGService aMS = new AccionMasivaMSGService();
			while (iter.hasNext()){
				AccionMasivaMSGDTO aM = (AccionMasivaMSGDTO)iter.next();
				log.debug("Enviando mensaje accion masiva ST Peticion:" + aM.getNumeroPeticion());
				String mensaje = aMS.marshall(aM);
				this.enviaMensaje(mensaje, aM.getNumeroPeticion());
			}
		}catch(Exception e)
		{
			log.debug("Error al enviar mensaje de accion masiva ST",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	
	private void enviaMensaje(String mensaje, Long numeroPeticion) throws ATiempoAppEx{
		try{
			WFSessionLocal fmc;
			InitialContext ic = new InitialContext();
			WFSessionLocalHome home = (WFSessionLocalHome) HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
			fmc = home.create();
			fmc.enviarMensaje(queue, mensaje, numeroPeticion, WFSessionLocal.FLUJO_SOLTEC);
		}catch(Exception e){
			log.debug("Error al enviar mensaje de accion masiva ST",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

}