package com.telefonica_chile.atiempo.trace.dummy;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.trace.api.TraceOperation;


public final class DisabledTraceOperation implements TraceOperation {

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#setColumn(java.lang.Short, java.lang.Object)
	 */
	public final void setColumn(Short columnId, Object value) {		

	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#init(org.apache.log4j.Logger)
	 */
	public final void init(Logger log) {
		
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#getColumn(java.lang.Short)
	 */
	public final Object getColumn(Short columnId) {		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.utiles.trace.api.TraceOperation#isDisabledOperation()
	 */
	public final boolean isDisabledOperation() {		
		return true;
	}

}
