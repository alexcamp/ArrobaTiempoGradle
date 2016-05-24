/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.dummy;

import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;

/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class DisabledTraceManager implements TraceManager {
	// Correccion NullPointer del 19/Jul en producción en el startup del server - guido - 20/Jul/2009
	private static final TraceOperation disabledOperation = new DisabledTraceOperation();
	private static final CurrentExecution disabledExecution = new DisabledCurrentExecution();
	
	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#newOperation(java.lang.Short)
	 */
	public final TraceOperation newOperation(Short columnId) {		
		return disabledOperation;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#newCurrentExecution()
	 */
	public final CurrentExecution newCurrentExecution() {		
		return disabledExecution;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#isTraceDisabled()
	 */
	public final boolean isTraceDisabled() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#traceOpStart(com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation)
	 */
	public final void traceOpStart(TraceOperation operation) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#traceOpEnd(com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation)
	 */
	public final void traceOpEnd(TraceOperation operation) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#closeCurrentExecution()
	 */
	public void closeCurrentExecution(CurrentExecution cExecution) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceManager#newOperation(java.lang.Short, com.telefonica_chile.atiempo.utiles.trace.api.CurrentExecution)
	 */
	public TraceOperation newOperation(Short operationId, CurrentExecution currentExecution) {
		// Correccion NullPointer del 19/Jul en producción en el startup del server - guido - 20/Jul/2009
		//return null;
		return disabledOperation;
	}
}
