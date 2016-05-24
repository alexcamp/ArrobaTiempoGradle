/*
 * Created on 13-feb-07
 */
package co.com.telefonica.atiempo.utiles;


/**
 * @author TCS
 */
public abstract class MensajeDrivenBean
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {

	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	/**
	 * getMessageDrivenContext
	 */
	public javax.ejb.MessageDrivenContext getMessageDrivenContext() {
		return fMessageDrivenCtx;
	}
	
	/**
	 * setMessageDrivenContext
	 */
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx) {
		fMessageDrivenCtx = ctx;
	}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate() {
	}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

}
