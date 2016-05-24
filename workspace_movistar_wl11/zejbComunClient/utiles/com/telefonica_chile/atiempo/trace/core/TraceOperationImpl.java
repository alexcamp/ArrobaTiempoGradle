/*
 * Created on Jul 31, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.trace.core;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TraceOperationImpl implements TraceOperation {

	private CurrentExecutionImpl currentExecution;
	
	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#setColumn(java.lang.Short, java.lang.Object)
	 */
	private HashMap tabla = new HashMap(25);
	private boolean esPrincipal;
	 
	public final void setColumn(Short columnId, Object value) {
		tabla.put(columnId,value);
	}
	
	public final void setEsPrincipal(boolean esPpal) {
		esPrincipal = esPpal;
	}
	
	public boolean isOpPrincipal(){
		return esPrincipal;
	}

	public final boolean getEsPrincipal() {
		return esPrincipal;
	}


	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#init(org.apache.log4j.Logger)
	 */
	public final void init(Logger plog) {					
		this.log = plog;
	}
	public final Logger getLog(){
		return this.log;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#getColumn(java.lang.Short)
	 */
	public final Object getColumn(Short columnId) {
		/*switch(columnId.shortValue()){		
			case  (short)4: // OPERATION TIME
		    	return interval;
		} */
		if (TraceManager.COL_OP_TIME.equals(columnId)) {
			return getOpTime();
		} else if (TraceManager.COL_ES_OP_PRINCIPAL.equals(columnId)) {
			return String.valueOf(isOpPrincipal());
		}
		return tabla.get(columnId);	
		//switch por columnId
		// si colId es OP_TIME return interval
		// si colId es OP_ID return operationId;
		// si colId es OP_NAME return TraceOperationDef.getOperationName(opId)
		// default otherColumn.get(colId)
		//return null;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#isDisabledOperation()
	 */
	public final boolean isDisabledOperation() {
		return false;
	}

	/**
	 * @param currentExecution
	 */
	void setCurrentExecution(CurrentExecutionImpl currentExecution) {
		this.currentExecution = currentExecution;
	}
	CurrentExecutionImpl getCurrentExecution() {
		return this.currentExecution;
	}
	final void start() {
		startTime = System.currentTimeMillis();		
	}
	final void end() {
		long interval2 = System.currentTimeMillis() - startTime;
		interval = new Long(interval2);
	}
	final Long getOpTime() {
		return interval;
	}
	
	private Logger log = null;
	private long startTime = 0;
	private Long interval = null;
}
