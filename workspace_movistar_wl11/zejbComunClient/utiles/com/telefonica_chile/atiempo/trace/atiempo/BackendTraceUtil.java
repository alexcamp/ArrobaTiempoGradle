/*
 * Created on Aug 6, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.atiempo;

import java.util.HashMap;

import javax.ejb.EJBException;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;

import org.apache.log4j.Logger;

import com.ibm.websphere.management.AdminService;
import com.ibm.websphere.management.AdminServiceFactory;
import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * @author 808027
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BackendTraceUtil {	
	
	private static HashMap operationsHash = new HashMap();
	
	protected transient static Logger log = Logger.getLogger(BackendTraceUtil.class) ;
	
	private static String server ="";
	private static Boolean showAppServer = null;
	 
	static String shortClassName;
	static Short opId;	
		
	public static final BackendExecution initExecution(Object trInstance, Logger log) {		
		String className = trInstance.getClass().getName();
		String shortClassName = Utiles.getClassName(className); 
		Short opId = getOperationId(shortClassName);
		 
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
		TraceManager traceManager = traceConf.getManager();			
				
		CurrentExecution cExecution = traceManager.newCurrentExecution();
		cExecution.init();
			
		TraceOperation traceOp = traceManager.newOperation(opId);
		traceOp.init(log);	
		
		if(isShowAppServerEnabled()){
			setServerData();
		    traceOp.setColumn(TraceManager.COL_SERVER_APPLICATION, server);
	   }
		
		
		traceOp.setColumn(TraceManager.COL_TR_NAME,shortClassName);
		traceOp.setColumn(TraceManager.COL_TIPO_OPERACION, TraceAdapter.COL_TIPO_OP_MENSAJE_TR);
		
		BackendExecution bExecution	= new BackendExecution();	
		bExecution.setCExecution(cExecution);
		bExecution.setTOperation(traceOp);	
		bExecution.setTraceManager(traceManager);
		
		return bExecution;

	}
	private static final boolean isShowAppServerEnabled() {
		if (showAppServer == null) {
			showAppServer = new Boolean(false);
			String logAppServer = VpistbbaConfig.getVariable("cluster.admService.showAppServer");
		    log.debug("Show AppServer? logAppServer="+logAppServer);
			if(logAppServer != null && logAppServer.trim().equals("true")){
				showAppServer = new Boolean(true);
			}
		}
		return showAppServer.booleanValue();
	}
	
	 private static void setServerData()
	    {
	        try
	        {
	            AdminService adminservice = AdminServiceFactory.getAdminService();
	            javax.management.ObjectName objectname = adminservice.getLocalServer();
	            server = (String)adminservice.getAttribute(objectname, "name");	            
	           
	            
	            
	        }
	        catch(AttributeNotFoundException attributenotfoundexception)
	        {
	            attributenotfoundexception.printStackTrace();
	            EJBException ejbexception = new EJBException("Attribute Not Found", attributenotfoundexception);
	            throw ejbexception;
	        }
	        catch(InstanceNotFoundException instancenotfoundexception)
	        {
	            instancenotfoundexception.printStackTrace();
	            EJBException ejbexception1 = new EJBException("Server Instance Not Found", instancenotfoundexception);
	            throw ejbexception1;
	        }
	        catch(MBeanException mbeanexception)
	        {
	            mbeanexception.printStackTrace();
	            EJBException ejbexception2 = new EJBException("WebSphere System Management Exception", mbeanexception);
	            throw ejbexception2;
	        }
	        catch(ReflectionException reflectionexception)
	        {
	            reflectionexception.printStackTrace();
	            EJBException ejbexception3 = new EJBException("WebSphere System Management Exception", reflectionexception);
	            throw ejbexception3;
	        }
	    }
	
	private final static Short getOperationId(String trName) {
		if (operationsHash.isEmpty()) {
			initOperationsHash();
		}
		Short opId = (Short) operationsHash.get(trName);
		if (opId == null) {
			log.debug("The tr name '"+trName+"' doesn't have the trace operation associated. Use default operation.");
			opId = TraceManager.OP_DEFECTO;
		}
		return opId;
	}
	
	private static void initOperationsHash() {
		operationsHash.put("TR001S",TraceManager.OP_TR001S);
		operationsHash.put("TR002S",TraceManager.OP_TR002S);
		operationsHash.put("TR003S",TraceManager.OP_TR003S);
		operationsHash.put("TR004S",TraceManager.OP_TR004S);
		operationsHash.put("TR005S",TraceManager.OP_TR005S);		
		operationsHash.put("TR007S",TraceManager.OP_TR007S);
		operationsHash.put("TR010S",TraceManager.OP_TR010S);
		operationsHash.put("TR011S",TraceManager.OP_TR011S);
		operationsHash.put("TR012S",TraceManager.OP_TR012S);
		operationsHash.put("TR013S",TraceManager.OP_TR013S);
		operationsHash.put("TR014S",TraceManager.OP_TR014S);
		operationsHash.put("TR015S",TraceManager.OP_TR015S);
		operationsHash.put("TR016S",TraceManager.OP_TR016S);
		operationsHash.put("TR017S",TraceManager.OP_TR017S);
		operationsHash.put("TR018S",TraceManager.OP_TR018S);
		operationsHash.put("TR019S",TraceManager.OP_TR019S);
		operationsHash.put("TR021S",TraceManager.OP_TR021S);
		operationsHash.put("TR022S",TraceManager.OP_TR022S);
		operationsHash.put("TR023S",TraceManager.OP_TR023S);
		operationsHash.put("TR031S",TraceManager.OP_TR031S);
		operationsHash.put("TR032S",TraceManager.OP_TR032S);
		operationsHash.put("TR034S",TraceManager.OP_TR034S);
		operationsHash.put("TR035S",TraceManager.OP_TR035S);
		operationsHash.put("TR037S",TraceManager.OP_TR037S);
		operationsHash.put("TR039S",TraceManager.OP_TR039S);
		operationsHash.put("TR040S",TraceManager.OP_TR040S);
		operationsHash.put("TR041S",TraceManager.OP_TR041S);
		operationsHash.put("TR042S",TraceManager.OP_TR042S);
		operationsHash.put("TR043S",TraceManager.OP_TR043S);
		//Cr20948 - Altamira - guido - 22/May/2009
		operationsHash.put("TR601S",TraceManager.OP_TR601S);	
		// Se agrega TR028S - German P.
		operationsHash.put("TR028S",TraceManager.OP_TR028S);		
		
		// ana santos - 24 Nov - Granite - inicio
		operationsHash.put("TR510S",TraceManager.OP_ASIG_REC_LB);
		operationsHash.put("TR511S",TraceManager.OP_CONFIG_AUTOM_SERV_CENTRALES);
		operationsHash.put("TR512S",TraceManager.OP_ACTUALIZAR_INVENTARIO_STB);
		operationsHash.put("TR513S",TraceManager.OP_LIBERACION_RECURSOS);
		operationsHash.put("TR514S",TraceManager.OP_CONSULTA_PUNTOS_RED);
		operationsHash.put("TR515S",TraceManager.OP_PLAN_BA_ASOCIADO_LB);
		operationsHash.put("TR516S",TraceManager.OP_ACTUALIZACION_ESTADO_LB);
		// ana santos - 24 Nov - Granite - fin
		
		operationsHash.put("TR135S",TraceManager.OP_TR135S);
		
		/*
		 * JOSE BAEZ PRESENCIA DIGITAL
		 */
		
		operationsHash.put("TR054S",TraceManager.OP_TR054S);
		
	}
	
}
