/*
 * Created on May 20, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaDelegate;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.db.DBManager;
import com.tecnonautica.utiles.web.MensajesWeb;
import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.servicios.util.Utiles;
//--

/**
 * @author 804226
 *
 * For TCS ( TATA Consultancy Services).
 */
public class UploadFileAcc extends Accion{

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private PeticionLocalHome peticionHome;
	
	ArrayList listaPeticiones = new ArrayList();
	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		log.debug("Inicio procesamiento masivo de cierres de actividad, mediante archivo plano");
		
		MensajesWeb msgs = MensajesWeb.getInstance(request);
		msgs.addError("Error de prueba unooo!!!!!!");
		try{
//			TODO: Eliminar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!---------------------
//			if(true)
//				throw new Exception("error mio");
			AccionMasivaInterface aMI = new AccionMasivaDelegate();
			
			//Obtengo archivo y lista de peticiones a procesas
			DiskFileUpload fu = new DiskFileUpload();
			fu.setSizeMax(157286400); //150M
			fu.setSizeThreshold(1024);
			fu.setRepositoryPath(System.getProperty("java.io.tmpdir"));
			List fileList = fu.parseRequest(request);
			InputStream uploadedFileStream = null;
			String uploadedFileName = null; 
			for (Iterator i = fileList.iterator(); i.hasNext(); )
			{
				FileItem fi = (FileItem)i.next();
				if (!fi.isFormField())
				{
					if (fi.getSize() < 1)
					{
						throw new Exception("No file was uplaoded");
					}
					BufferedReader entrada = new BufferedReader(new InputStreamReader(fi.getInputStream()));

					int y = 0;
					String linea = "";
					//leo archivo hasta el final
					while ((linea = entrada.readLine()) != null) {
						log.debug("nroPeticion a procesar " + linea); //TODO: syso
						String[] temp = Utiles.split(linea, '|');
						String petAtis = temp[1];
						String petATiem = temp[0];
						log.debug("nroPeticion a procesar: ATIS " + petAtis + " @tiempo " + petATiem); //TODO: syso
						procesarPeticion(petATiem);
						y++;
						//Proceso de a 50 peticiones a la vez
						if(y > 50){
							//envio lista peticiones
							//Si hay peticiones, se realiza el cierre
//							if (listaPeticiones.size()>0){
//								log.debug("Se envian para cierre "+ listaPeticiones.size() + " Peticiones.");
//								aMI.enviaAccion(listaPeticiones);
//							}
							y = 0;
						}
					}
//					if (listaPeticiones.size()>0){
//						log.debug("Se envian para cierre "+ listaPeticiones.size() + " Peticiones.");
//						aMI.enviaAccion(listaPeticiones);
//					}
				}
			}
		}catch(Exception e){
			msgs.addError("Error de prueba!!!!!!");
			log.debug("Error mio exception" + e);
		}
		log.debug("Finaliza procesamiento masivo de cierres de actividad, mediante archivo plano");
	}
	
	private void procesarPeticion(String strPeti)throws ATiempoAppEx, NamingException, FinderException{
			
//		TODO obtengo nro peticion atis PETICION.COD_PET_CD
//		se usa solo para imprimir el listado.
		//strPetiAtis = 	
		//Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
		TraceManager traceManager = traceConf.getManager();
		CurrentExecution cExecution = null;
		String clase = UploadFileAcc.class.getName();
		String operacion = clase.substring(clase.lastIndexOf(".")+1); 
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(operacion));
		TraceOperation traceOp2 = traceManager.newOperation(TraceAdapter.getAccion(operacion));
		TraceOperation traceOp3 = traceManager.newOperation(TraceAdapter.getAccion(operacion));					
		try{
			traceOp.init(log);			
			cExecution = traceManager.newCurrentExecution();														
			cExecution.init();		
			traceManager.traceOpStart(traceOp);
			traceManager.traceOpStart(traceOp2);
			traceManager.traceOpStart(traceOp3);	
				 	
		String sqlPetiAtis = "SELECT VPISTBBA.PETICION.COD_PET_CD  FROM VPISTBBA.PETICION " +
					  		"WHERE VPISTBBA.PETICION.PETI_NUMERO = ?";
			
		String sqlCodActiv = "SELECT AC.ACT_CODIGO  AS COD " +
							 "FROM ATIEMPO.ACTIVIDAD AC, VPISTBBA.BITACORA_PETICION BP " +
							 "WHERE BP.BIPE_ID = (" +
							 "SELECT max(b.bipe_id) " +
							 "FROM VPISTBBA.BITACORA_PETICION B " + 
	 						 "WHERE B.PETI_NUMERO = ?" +
							 ") " +
							 "AND AC.ACT_ID = BP.ACT_ID";
			
		String sqlInstAct = "SELECT BI.BI_URL_DETALLE " +
							"FROM ATIEMPO.BINTEGRADA BI " +
							"WHERE BI_NRO_PETICION = ? " +
							"AND BI_VISIBLE = 1";
		long nroPet = new Long(strPeti).longValue();
			
		Connection conn=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
			
		String codAtis = "";
		String codAct = "";
		String urlInstAct = ""; 
		try
		{
			conn=DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			conn.setReadOnly(true);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(true);
			//******Obtengo nro peticion atis*****
			pstmt=conn.prepareStatement(sqlPetiAtis);
			traceOp.setColumn(TraceManager.COL_SQL, sqlPetiAtis); //@Trace
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			traceManager.traceOpEnd(traceOp); //@Trace
			if(rs.next())
				codAtis = rs.getString("COD_PET_CD");
			//************************************
			//******obtengo codigo actividad******
			pstmt=conn.prepareStatement(sqlCodActiv);
			traceOp2.setColumn(TraceManager.COL_SQL, sqlCodActiv); //@Trace
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			traceManager.traceOpEnd(traceOp2);//@Trace
			if(rs.next())
				codAct = rs.getString("COD");
			//************************************
			//********Obtenco Instancia actividad*****
			pstmt=conn.prepareStatement(sqlInstAct);
			traceOp3.setColumn(TraceManager.COL_SQL, sqlInstAct); //@Trace
			pstmt.setLong(1,nroPet);
			rs=pstmt.executeQuery();
			traceManager.traceOpEnd(traceOp3);
			if(rs.next())
				urlInstAct = rs.getString("BI_URL_DETALLE");
			//****************************************
		}
		catch(Exception e){
			log.error("Exception", e);
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
				log.error("Exception en consultas SQL",e);
			}
		}
//		String codActFromConf = codAct;
		log.debug("codigo actividad " + codAct);
		//obtengo Instancia actividad de URL Instancia Actividad 
		String aBuscar="instanciaActividad=";
		String idTemp = urlInstAct.substring(urlInstAct.indexOf(aBuscar) + aBuscar.length() );
		String idTempCodi = idTemp.substring(0,idTemp.indexOf("&"));
		String instAct = URLDecoder.decode(idTempCodi);
		log.debug("instancia actividad" + instAct);
			
		boolean esTerminable = false;
		//Verifico Si es terminable  
		if (this.esActividadTerminable(codAct))
		{
			if(this.esOCTerminable(strPeti))
			{
				esTerminable=true;
			}
		}
		//Solo si es actividad Terminable se envia el mensaje para que se haga el cierre
		if (esTerminable)
		{
			AccionMasivaMSGDTO aM = new AccionMasivaMSGDTO();
			aM.setNumeroPeticion(new Long( strPeti));
	
			//Obtengo el tipo de familia de la peticion
			if(peticionHome == null)
				peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal petLocal=peticionHome.findByPrimaryKey(new PeticionKey(new Long(strPeti)));
			String petIdInstancia = petLocal.getPeti_id_instancia();
	
			if(codAct.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGI")))
				aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("l")));
			//permite continuar el flujo masivamente para las actividades de desinstalación
			else if(codAct.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_DESINSTALACION")) && petIdInstancia.equals("LB"))
					aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_DESINSTALACION")));
			else if(codAct.equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_CONTROL_DESINSTALACION")) && petIdInstancia.equals("LB"))
					aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_CONTROL_DESINSTALACION")));
			else
				aM.setCodigoAccion(new Integer(VpistbbaConfig.getVariable("CODIGO_AM_CIERRE_GRAL")));
			aM.setCodigoActividad(codAct);
			aM.setInstanciaActividad(instAct);
			listaPeticiones.add(aM);
			//listaPeticionesAtis.put(strPeti,strPetiAtis); //se usa para imprimir el listado de peticiones procesadas
		}
		}finally{			
			traceManager.closeCurrentExecution(cExecution); //@Trace
	}
	}
	
	
	//TODO: Pones estas funciones en las librerias Util
	//****************************************************
	private boolean esActividadTerminable(String strCodActi){
		boolean esTerminable = false;
		String actividades=VpistbbaConfig.getVariable("ACTIVIDAD_CIERRE");
		ArrayList datosActividades = new ArrayList();
		String[] var = Utiles.split(actividades, ';');
		for (int i = 0; var != null && i < var.length; i++) {
			if (var[i] == null || var[i].length() == 0)
				continue;
			datosActividades.add(var[i]);
		}
		for (Iterator iter = datosActividades.iterator();iter.hasNext();){
			String codAct = (String) iter.next();
			if (codAct.equals(strCodActi)){
				esTerminable = true;
				break;
			}
		}
		return esTerminable;		
	}
	
	private boolean esOCTerminable(String numeroPeticion) throws ATiempoAppEx{
		boolean esTerminable = false;
		ArrayList listaPs = null;
		ArrayList datosOC = new ArrayList();
		
		String listaOCTerminables = VpistbbaConfig.getVariable("ID_OC_CIERRE");
		String[] var = Utiles.split(listaOCTerminables, ';');
		for (int i = 0; var != null && i < var.length; i++) {
			if (var[i] == null || var[i].length() == 0)
				continue;
			datosOC.add(var[i]);
		}

		//Obtengo los ps de la peticion
		InfoComunColombiaBusinessInterface iCO= new InfoComunColombiaBusinessDelegate();
		listaPs=iCO.getProductoServicio(new Long(numeroPeticion));
		
		for (Iterator iterPs = listaPs.iterator();iterPs.hasNext();){
			ProductoDTO ps = (ProductoDTO) iterPs.next();
			String ocPs = ps.getIdOpComercial().toString();
			for (Iterator iterOC = datosOC.iterator();iterOC.hasNext();){
				String ocTerminable = (String) iterOC.next();
				if (ocTerminable.equals(ocPs)){
					esTerminable = true;
					break;
				}
			}
			if (esTerminable){
				break;
			}
		}
		return esTerminable;
	}
	
//****************************************************
}
