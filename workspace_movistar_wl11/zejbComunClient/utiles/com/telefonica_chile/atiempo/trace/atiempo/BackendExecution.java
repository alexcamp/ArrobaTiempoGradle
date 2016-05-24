/*
 * Created on Aug 6, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.atiempo;

import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;

/**
 * @author 808027
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BackendExecution {
	
	private TraceManager traceManager;
	
	private CurrentExecution cExecution;
	
	private TraceOperation tOperation;

	//	metodos de acceso y modificacion

	public CurrentExecution getCExecution() {
		return cExecution;
	}
 	
	public TraceOperation getTOperation() {
		return tOperation;
	}
	
	public TraceManager getTraceManager() {
		return traceManager;
	}

	public void setCExecution(CurrentExecution execution) {
		cExecution = execution;
	}

	public void setTOperation(TraceOperation operation) {
		tOperation = operation;
	}

	public void setTraceManager(TraceManager manager) {
		traceManager = manager;
	}

	// metodo que realiza el start de la operacion
	public void startOperation() {
		traceManager.traceOpStart(tOperation);	
	}

	// Metodo que realiza el end y close tanto de la operacion como del currentExecution
	public void endAll() {
		if (traceManager == null) {
			return;
		}
		traceManager.traceOpEnd(tOperation); 
		traceManager.closeCurrentExecution(cExecution);	
	}

	// Metodo que indica si hubo error
	public void setErrorOp(boolean b) {
		String error ="";
		if (b == true){
			error = "true";
		}else{
			error = "false";
		}
		tOperation.setColumn(TraceManager.COL_ERROR,error);
	}
	
	public void setIdMensajeOp(String codMsg) {
		tOperation.setColumn(TraceManager.COL_ID_MSG,codMsg);
	}
	
	public void setNumPetAtiempo(Object numPetAtiempo) {
		tOperation.setColumn(TraceManager.COL_NUM_PET_ATIEMPO,numPetAtiempo);
	}
	
	public void setNumPetAtis(Object numPetAtis) {
		tOperation.setColumn(TraceManager.COL_NUM_PET_ATIS,numPetAtis);
	}

	
	public void setColumnOp(Short columnId, String value) {
		tOperation.setColumn(columnId, value);
	}

}
