/*
 * Created on Jul 30, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.api;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.trace.core.TraceManagerImpl;
import com.telefonica_chile.atiempo.trace.core.TraceOperatiosDef;
import com.telefonica_chile.atiempo.trace.dummy.DisabledTraceManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;

/**
 * @author 805538
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TraceMainConfiguration {
	private static Logger log = Logger.getLogger(TraceMainConfiguration.class);
	private static TraceMainConfiguration instanceConfiguration = null;
	private static final TraceManager disabledManager = new DisabledTraceManager();
	private TraceManager currentManager = disabledManager;
	//Operaciones apagadas por defecto -- DBManager, Autorizacion por defecto
	private String defaultDisabledOpStr = ""; 
	private static final String TRACE_NO_ACTIVOS = "trace_no_activos";
	private String currentDisabledOpPropertyValue = null;
	private TraceMainConfiguration(){
	}
	public void startTrace() {	
		defaultDisabledOpStr += TraceManager.OP_AUTORIZACION_BANDEJA + "," +
		TraceManager.OP_AUTORIZACION_VPI + "," +
		TraceManager.OP_DBMANAGER_CONSULTA_HASH + "," +
		TraceManager.OP_DBMANAGER_EJECUTA + "," +
		TraceManager.OP_DBMANAGER_SELECT_COMMIT + "," +
		TraceManager.OP_DBMANAGER_SELECT_COMMIT_PREPARED + "," +
		TraceManager.OP_DBMANAGER_SELECT_READ_UNCOMMIT + "," +
		TraceManager.OP_DBMANAGER_UPDATE;
		
		TraceManagerImpl localMng = new TraceManagerImpl();
		localMng.setDisabledOperations(getTraceNoActivos(defaultDisabledOpStr));
		log.debug("@Tiempo Trace is starting ... default disabled operation are: " + defaultDisabledOpStr);
		TraceOperatiosDef.start();
		localMng.showColumnTitles();		
		currentManager = localMng;
	}
	public static TraceMainConfiguration getInstance() {
		if(instanceConfiguration == null) {
		    instanceConfiguration = new TraceMainConfiguration();
			instanceConfiguration.startTrace();
			TraceAdapter.startAdapter();
		}
		return instanceConfiguration;
	}
	public TraceManager getManager() {		
		refreshConfig();
		return currentManager;
	}
	private void refreshConfig() {	
		String tr = ApplicationConfig.getVariable(TRACE_NO_ACTIVOS);
		if (tr == null && currentDisabledOpPropertyValue == null) {
			return;	
		}
		if (tr != null && currentDisabledOpPropertyValue != null && currentDisabledOpPropertyValue.equals(tr)) {
			return;
		}
		log.debug("La habilitación/deshablitación del trace se va modificar configuracionAnterior: '" + currentDisabledOpPropertyValue + "' configuracionNueva: " + tr);
		TraceManager localMng = null; 
		if (tr != null && tr.equals("all")) {
			localMng = disabledManager;
		} else if (currentDisabledOpPropertyValue != null && currentDisabledOpPropertyValue.equals("all")) {
			localMng = new TraceManagerImpl();
			((TraceManagerImpl)localMng).showColumnTitles();
		} else {
			localMng = currentManager;
		}
		List disOp = null;
		if (tr != null) {
			if(!tr.equalsIgnoreCase("all"))
				disOp = getTraceNoActivos(tr);
		} else {
			disOp = getTraceNoActivos(defaultDisabledOpStr); 
		}
		if (localMng instanceof TraceManagerImpl) {			
			((TraceManagerImpl)localMng).setDisabledOperations(disOp);
		}
		currentDisabledOpPropertyValue = tr;
		currentManager = localMng;		
		
						
	}
	private String currentTraceDisabledProperyValue = null; // Por defecto false
	private String currentOperationsDisabledProperyValue = null;
	
	private static List getTraceNoActivos(String trs){
		List trace_no_activos = new ArrayList();
		if(trs!=null){		
			StringTokenizer st = new StringTokenizer(trs, ",");
			while(st.hasMoreTokens()){
				try{									
					trace_no_activos.add(new Short(st.nextToken()));
				}catch(Exception e){
					log.debug("La property " + TRACE_NO_ACTIVOS + " contiene valores no númericos, las operaciones deshabilitadas son las que se encuentran por defecto, excepcion: " + e);
					trace_no_activos.clear();
				}
			}
		}
		return trace_no_activos;
	}
	
	
}
