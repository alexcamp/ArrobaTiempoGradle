/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.core;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.dummy.DisabledTraceOperation;

/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//Columna operacion root
public final class TraceManagerImpl implements TraceManager {
	private static Logger logMng = Logger.getLogger(TraceManagerImpl.class);
	private String prefijo = "@Trace";
	private String prefijoTitles = "@Titles";
	private String SEP = "|";
	private static final TraceOperation disabledOperation = new DisabledTraceOperation();
	private static long currentTransactionId = 0;
	private static ThreadLocal threadLocal = new ThreadLocal();
	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#newOperation(java.lang.Short)
	 */	
	 private List disabledOperations = Collections.EMPTY_LIST;
	 public void setDisabledOperations(List disabledOperations) {
		this.disabledOperations = disabledOperations; 
	 }
	public final TraceOperation newOperation(Short operationId, CurrentExecution currentExecution) {
	    // Arreglo Trace por operacion de publicacion lanzada desde SolTec que no tiene CurrentExecution - guido - 23-Dic-2008 - Incio
		if (disabledOperations.contains(operationId) || currentExecution == null) {
			return disabledOperation;
		}
		TraceOperationImpl op = new TraceOperationImpl();
		op.setColumn(TraceManager.COL_ID_OPERATION , operationId);
		Long idTr = new Long(this.nextTransactionId());
		String opName = TraceOperatiosDef.getOperationName(operationId);
		op.setColumn(TraceManager.COL_ID_TRANSACTION , idTr);		
		op.setColumn(TraceManager.COL_OP_NAME , opName);
		//op.setColumn(TraceManager.COL_OP_TIME , op.getOpTime());
		// Se comenta por arreglo trace
		//if (currentExecution == null) {
		//	throw new IllegalArgumentException("There is not CurrentExecution in progress for operation id " + operationId);
		//}
	    // Arreglo Trace  - guido - 23-Dic-2008 - FIN
		op.setColumn(TraceManager.COL_USER_IP,currentExecution.getColumn(TraceManager.COL_USER_IP));
		op.setColumn(TraceManager.COL_USER_LOGIN,currentExecution.getColumn(TraceManager.COL_USER_LOGIN));
		Long idTrPrincipal = (Long) currentExecution.getColumn(TraceManager.COL_TRANS_PRINCIPAL);
		if (idTrPrincipal == null) { // La operacion es prinpal/root
			currentExecution.setColumn(TraceManager.COL_TRANS_PRINCIPAL, idTr);
			currentExecution.setColumn(TraceManager.COL_OP_PRINCIPAL, opName);
			op.setEsPrincipal(true);
		} else {
			op.setEsPrincipal(false);				
		}
		op.setCurrentExecution((CurrentExecutionImpl)currentExecution);
		return op;
	}
	public final TraceOperation newOperation(Short operationId) {
		CurrentExecutionImpl currentExecution = (CurrentExecutionImpl) threadLocal.get();
		return newOperation(operationId,currentExecution);
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#newCurrentExecution()
	 */
	public final CurrentExecution newCurrentExecution() {
		CurrentExecutionImpl currentExecution = new CurrentExecutionImpl();			
		Object o = threadLocal.get();
		if (o != null) {
			logMng.warn("YA hay un objecto en la ejecución actual object: " + o);
		} else {
			threadLocal.set(currentExecution);			
		}
		return currentExecution;
	}
	
	public final void closeCurrentExecution(CurrentExecution cExecution){
		threadLocal.set(null);			
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#isTraceDisabled()
	 */
	public final boolean isTraceDisabled() {
		return false;
	}
	/**
	 * Este metodo es el encargado de enviar al log, recorre las columnas (usando TraceOperations.getColumnsOrder()) 
	 * primero se fija en la operación, si la columna es vacia se fija en el CurrentExecution
	 */
	public final void traceOpStart(TraceOperation operation) {
		if (operation.isDisabledOperation()) {
			return;
		}
		TraceOperationImpl op = (TraceOperationImpl) operation;
		op.start();	
		log(op,"START");
	}
	public final void traceOpEnd(TraceOperation operation) {		
		if (operation == null || operation.isDisabledOperation()) {
			return;
		}
		TraceOperationImpl op = (TraceOperationImpl) operation;
		op.end();
		op.setColumn(TraceManager.COL_OP_TIME, op.getOpTime());				
		log(op,"END");		
	}
	
	
	
	
	private void log(TraceOperationImpl operation, String event) {
		if(operation.isDisabledOperation()){
			return;
		}
		CurrentExecutionImpl currentExecution = operation.getCurrentExecution(); 
		Short[] columns = TraceOperatiosDef.getColumnsOrder();
		Short colId = null;
		Object colValue = null;
		StringBuffer printLog = new StringBuffer();
		printLog.append(prefijo);
		printLog.append(SEP);		
		
		for (int i = 0; i < columns.length; i++) {
			colId = columns[i];
			if(colId.shortValue() == TraceManager.COL_OP_EVENT.shortValue()){
				colValue = event;
			}else{
				colValue = operation.getColumn(colId);
				if (colValue == null) {				
					colValue = currentExecution.getColumn(colId);
				}
			}					
			printLog.append(toString(colValue));
			printLog.append(SEP);
		}
		String msj = printLog.toString();
		Logger log = operation.getLog();
		if (operation.isOpPrincipal()) {
			log.info(msj);
		} else {
			log.debug(msj);
		}
		//((Logger) operation.getLog()).debug(printLog.toString());		
		//((Logger) operation.getLog()).log(Logger. printLog.toString());		
	}
	public void showColumnTitles() {
		Short[] columns = TraceOperatiosDef.getColumnsOrder();
		Short colId = null;
		StringBuffer sbuff = new StringBuffer();
		sbuff.append(prefijoTitles);
		sbuff.append(SEP);
		sbuff.append("TIMESTAMP");
		sbuff.append(SEP);
		sbuff.append("NIVEL TRACE");
		sbuff.append(SEP);
		sbuff.append("HILO");
		sbuff.append(SEP);
		sbuff.append("COMPONENTE");
		sbuff.append(SEP);
		sbuff.append("PREFIJO");
		sbuff.append(SEP);		
		for (int i = 0; i < columns.length; i++) {
			colId = columns[i];
			sbuff.append(TraceOperatiosDef.getColumnName(colId));
			sbuff.append(SEP);			
		}
		logMng.debug(sbuff.toString());		
	}
	private synchronized long nextTransactionId() {
		currentTransactionId++;
		return currentTransactionId;
			
	}
	public static long getTransactionId() {			
		return currentTransactionId;
	}
	
	private String toString(Object colValue) {
		if (colValue == null) {
			return "";
		}
		return colValue.toString();
	}
	
}
