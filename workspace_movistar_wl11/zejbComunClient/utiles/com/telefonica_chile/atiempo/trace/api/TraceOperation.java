/*
 * Created on Jul 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.trace.api;

import org.apache.log4j.Logger;
/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TraceOperation {

	public void setColumn(Short columnId, Object value);
	public void init(Logger log);
	public Object getColumn(Short columnId);
	public boolean isDisabledOperation();
}
