/*
 * Created on 20-12-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.navigogroup.inyectorsig.main;

/**
 * @author Admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import co.com.telefonica.atiempo.utiles.MQ;
//import com.navigogroup.comunes.colas.basic.MQ;

public class Traspaso 
{
	private MQ mq;
//	private static Logger log=Logger.getLogger(Escritor.class);
	private static final String colaOrigen="WF_TO_VPISTBBA_ERR_Q";
	private static final String colaDestino="WF_TO_VPISTBBA_Q";
    
	public Traspaso()
	{
		try
		{
			while(mq==null)
				mq=new MQ("127.0.0.1",1530,"SYSTEM.ADMIN.SVRCONN");
			String msg=mq.getAny(colaOrigen);
			while(msg!=null)
			{
				mq.put(colaDestino,msg);
				mq.commit();
				msg=mq.getAny(colaOrigen);
			}
			mq.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Traspaso traspaso = new Traspaso();
	}
}
