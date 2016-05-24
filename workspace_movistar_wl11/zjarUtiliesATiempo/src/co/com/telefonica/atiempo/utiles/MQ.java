package co.com.telefonica.atiempo.utiles;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQ {

	private MQQueueManager qManager;
	private MQQueue qBrowse = null;
	private int backoutCount = 0;
	private Logger log = Logger.getLogger(MQ.class);
    
	public int getBackoutCount() {
		return( backoutCount );
	}
    
	public MQ( String hostname, int port, String canal ) throws Exception {
        
		MQEnvironment.hostname = hostname;
		MQEnvironment.port = port;
		MQEnvironment.channel = canal;
		qManager = new MQQueueManager( "" );        

	}
    
	public void close() throws MQException {
		qManager.disconnect();
	}
	
	public MQ closeMQ(MQ mq)
	{
		try
		{
			mq.close();
		}
		catch(MQException mqexception)
		{
			
		}
		return null;
	}
    
	public void browseOpen( String qName ) throws Exception {
		qBrowse = qManager.accessQueue( qName, MQC.MQOO_INQUIRE +  MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED  );        
	}
    
	public int depthMQ(String qName)
		   throws Exception
   {
	   MQQueue q = qManager.accessQueue(qName, 42);
	   int pro = 0;
	   if(q != null)
	   {
		   pro = q.getCurrentDepth();
		   q.close();
	   } else
	   {
		   throw new Exception("No se pudo obtener la profundidad");
	   }
	   return pro;
   }
    
	public int getMsgTime( String qName ) throws Exception {
		MQQueue q = qManager.accessQueue( qName, MQC.MQOO_INQUIRE +  MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED  );
        
		if( q.getCurrentDepth() <= 0 ) {
			q.close();
			return( 0 );
		}

		MQGetMessageOptions gmo = new MQGetMessageOptions();
		gmo.waitInterval = MQC.MQWI_UNLIMITED;
		gmo.options = gmo.options + MQC.MQGMO_NO_WAIT + MQC.MQGMO_BROWSE_NEXT;  
		MQMessage mqMsg = new MQMessage();             
                
		q.get( mqMsg, gmo );
          
		q.close();
        
		long diff = new Date().getTime() - mqMsg.putDateTime.getTime().getTime();
        
		return( (int) diff/1000 );                                     
        
	}
    
	public String browseNext() throws Exception {
        
		try {
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = MQC.MQWI_UNLIMITED;
			gmo.options = gmo.options + MQC.MQGMO_NO_WAIT + MQC.MQGMO_BROWSE_NEXT;  
			MQMessage mqMsg = new MQMessage(); 
            
			if( qBrowse.getCurrentDepth() <= 0 )
				return null;
                
			qBrowse.get( mqMsg, gmo );
                        
			return( mqMsg.readString( mqMsg.getMessageLength() ) );
		}
		catch( Exception e ) {            
			return null;
		}        
        
	}
    
	public void browseClose() throws Exception {
       
	   qBrowse.close();
        
	}
    
	public void put( String qName, String msg ) throws Exception {
		MQQueue qPut;
        
		try {
			qPut = qManager.accessQueue( 
					qName,
					MQC.MQOO_OUTPUT,
					"",
					null,
					null );
		}
		catch( Exception e ) {
			log.debug( "fallo open de cola " + qName );
			throw e;
		}
        
		try {                            
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			pmo.options = pmo.options + MQC.MQPMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage();
			mqMsg.format = MQC.MQFMT_STRING;
			mqMsg.writeBytes( msg );
			qPut.put( mqMsg, pmo );
			qPut.close();
		}
		catch( MQException e ) {
			log.debug( "fallo mqput cola " + qName );
			qPut.close();
			throw e;
		}       
                                          
	}
    
	public byte[] putConMessageIdSinReply( String qName, String msg,byte[] messageId) throws Exception
	{
		MQQueue qPut;

		try {
			qPut = qManager.accessQueue( 
					qName,
					MQC.MQOO_OUTPUT,
					"",
					null,
					null );
		}
		catch( Exception e ) {
			log.debug( "fallo open de cola " + qName );
			throw e;
		}

		try
		{                            
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			pmo.options = pmo.options + MQC.MQPMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage();
			mqMsg.format = MQC.MQFMT_STRING;
			mqMsg.messageId=messageId;
			mqMsg.writeBytes( msg );
			qPut.put( mqMsg, pmo );
			qPut.close();
			return mqMsg.messageId;
		}
		catch( MQException e ) {
			log.debug( "fallo mqput cola " + qName );
			qPut.close();
			throw e;
		}       
                      
	}
	
	public byte[] putConMessageyCorrel( String qName, String msg,byte[] messageId,byte[] correlId) throws Exception
	{
		MQQueue qPut;

		try {
			qPut = qManager.accessQueue( 
					qName,
					MQC.MQOO_OUTPUT,
					"",
					null,
					null );
		}
		catch( Exception e ) {
			log.debug( "fallo open de cola " + qName );
			throw e;
		}

		try
		{                            
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			pmo.options = pmo.options + MQC.MQPMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage();
			mqMsg.format = MQC.MQFMT_STRING;
			mqMsg.messageId=messageId;
			mqMsg.correlationId=correlId;
			mqMsg.writeBytes( msg );
			qPut.put( mqMsg, pmo );
			qPut.close();
			return mqMsg.messageId;
		}
		catch( MQException e ) {
			log.debug( "fallo mqput cola " + qName );
			qPut.close();
			throw e;
		}       
                  
	}
	
	public byte[] put( String qName, String msg,String replyToQueue,String replyToQManager) throws Exception
	{
		MQQueue qPut;
    
		try
		{
			qPut = qManager.accessQueue( 
					qName,
					MQC.MQOO_OUTPUT,
					"",
					null,
					null );
		}
		catch( Exception e )
		{
			log.debug( "fallo open de cola " + qName );
			throw e;
		}
    
		try
		{                            
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			pmo.options = pmo.options + MQC.MQPMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage();
			mqMsg.format = MQC.MQFMT_STRING;
			mqMsg.replyToQueueName=replyToQueue;
			mqMsg.replyToQueueManagerName=replyToQManager;
			mqMsg.writeBytes( msg );
			qPut.put( mqMsg, pmo );
			qPut.close();
			return mqMsg.messageId;
		}
		catch( MQException e ) {
			log.debug( "fallo mqput cola " + qName );
			qPut.close();
			throw e;
		}       
                                      
	}
    
	public Mensaje getAnyMessage( String qName, int timeout ) throws Exception
	{
		MQQueue qGet;        
    
		try {
			qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		}
		catch( Exception e ) {
			log.debug( "fallo open (lectura) cola " + qName );
			throw e;
		}
    
		try
		{
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = timeout;
			gmo.options = gmo.options + MQC.MQGMO_WAIT + MQC.MQGMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage(); 
        
			qGet.get( mqMsg, gmo );
			backoutCount = mqMsg.backoutCount;
			qGet.close();             
			Mensaje men=new Mensaje();
			men.setTxtMsg(mqMsg.readString( mqMsg.getMessageLength() ));
			men.setMessageId(mqMsg.messageId);
			men.setReplyToQueueName(mqMsg.replyToQueueName);
			men.setReplyToQueueManagerName(mqMsg.replyToQueueManagerName);
			men.setCorrelationId(mqMsg.correlationId);
			return(men);
		}
		catch( MQException e ) {
			log.debug( "CC " + e.completionCode + " R " + e.reasonCode );
			if( e.reasonCode != 2033 )
				log.debug( "fallo mqget de " + qName );
			qGet.close();
			if( e.reasonCode == 2033 )
				return null;
			else
				throw e;
		}
   	}
   	
	public Mensaje getAnyMessage( String qName, int timeout,byte[] correlId ) throws Exception
	{
		MQQueue qGet;        

		try {
			qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		}
		catch( Exception e ) {
			log.debug( "fallo open (lectura) cola " + qName );
			throw e;
		}

		try
		{
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = timeout;
			gmo.options = gmo.options + MQC.MQGMO_WAIT + MQC.MQGMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage();
			mqMsg.correlationId=correlId;
			qGet.get( mqMsg, gmo );
			backoutCount = mqMsg.backoutCount;
			qGet.close();             
			Mensaje men=new Mensaje();
			men.setTxtMsg(mqMsg.readString( mqMsg.getMessageLength() ));
			men.setMessageId(mqMsg.messageId);
			men.setReplyToQueueName(mqMsg.replyToQueueName);
			men.setReplyToQueueManagerName(mqMsg.replyToQueueManagerName);
			men.setCorrelationId(mqMsg.correlationId);
			return(men);
		}
		catch( MQException e ) {
			log.debug( "CC " + e.completionCode + " R " + e.reasonCode );
			if( e.reasonCode != 2033 )
				log.debug( "fallo mqget de " + qName );
			qGet.close();
			if( e.reasonCode == 2033 )
				return null;
			else
				throw e;
		}
	}
	public String getAny( String qName, int timeout ) throws Exception {
		MQQueue qGet;        
        
		try {
			qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		}
		catch( Exception e ) {
			log.debug( "fallo open (lectura) cola " + qName );
			throw e;
		}
        
		try {
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = timeout;
			gmo.options = gmo.options + MQC.MQGMO_WAIT + MQC.MQGMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage(); 
            
			qGet.get( mqMsg, gmo );
			backoutCount = mqMsg.backoutCount;
			qGet.close();             
			return( mqMsg.readString( mqMsg.getMessageLength() ) );
		}
		catch( MQException e ) {
			log.debug( "CC " + e.completionCode + " R " + e.reasonCode );
			if( e.reasonCode != 2033 )
				log.debug( "fallo mqget de " + qName );
			qGet.close();
			if( e.reasonCode == 2033 )
				return null;
			else
				throw e;
		}
    	
	}
    
	public String getAny( String qName, int timeout,byte[] correlId) throws Exception
	{
		MQQueue qGet;        
        
		try
		{
			qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		}
		catch( Exception e )
		{
			log.debug( "fallo open (lectura) cola " + qName );
			throw e;
		}
    
		try
		{
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = timeout;
			gmo.options = gmo.options + MQC.MQGMO_WAIT + MQC.MQGMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage(); 
			mqMsg.correlationId=correlId;
			qGet.get( mqMsg, gmo );
			backoutCount = mqMsg.backoutCount;
			qGet.close();
			return( mqMsg.readString( mqMsg.getMessageLength() ) );
		}
		catch( MQException e ) {
			log.debug( "CC " + e.completionCode + " R " + e.reasonCode );
			if( e.reasonCode != 2033 )
				log.debug( "fallo mqget de " + qName );
			qGet.close();
			if( e.reasonCode == 2033 )
				return null;
			else
				throw e;
		}
	
	}
 
	public String getAnyNoWait( String qName) throws Exception {
		 MQQueue qGet;        
        
		 try {
			 qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		 }
		 catch( Exception e ) {
			 log.debug( "fallo open (lectura) cola " + qName );
			 throw e;
		 }
        
		 try {
			 MQGetMessageOptions gmo = new MQGetMessageOptions();
			 gmo.options = gmo.options + MQC.MQGMO_NO_WAIT + MQC.MQGMO_SYNCPOINT; 
			 MQMessage mqMsg = new MQMessage(); 
            
			 qGet.get( mqMsg, gmo );
			 backoutCount = mqMsg.backoutCount;
			 qGet.close();             
			 return( mqMsg.readString( mqMsg.getMessageLength() ) );
		 }
		 catch( MQException e ) {
			 log.debug( "CC " + e.completionCode + " R " + e.reasonCode );
			 if( e.reasonCode != 2033 )
				 log.debug( "fallo mqget de " + qName );
			 qGet.close();
			 if( e.reasonCode == 2033 )
				 return null;
			 else
				 throw e;
		 }
    	
	 }
     
	public String getAny( String qName ) throws Exception {
		MQQueue qGet;        
        
		try {
			qGet = qManager.accessQueue( qName, MQC.MQOO_INPUT_AS_Q_DEF );
		}
		catch( Exception e ) {
			log.debug( "fallo open (lectura) cola " + qName );
			throw e;
		}
        
		try {
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.waitInterval = MQC.MQWI_UNLIMITED;
			gmo.options = gmo.options + MQC.MQGMO_WAIT + MQC.MQGMO_SYNCPOINT; 
			MQMessage mqMsg = new MQMessage(); 
            
			qGet.get( mqMsg, gmo );
			backoutCount = mqMsg.backoutCount;
			qGet.close();             
			return( mqMsg.readString( mqMsg.getMessageLength() ) );
		}
		catch( MQException e ) {
			log.debug( "fallo MQ cc " + e.completionCode + " reason " + e.reasonCode );
			if( e.reasonCode != 2033 )
				log.debug( "fallo mqget de " + qName );
			qGet.close();
			if( e.reasonCode == 2033 )
				return null;
			else
				throw e;
		}
                    
	}    
    
	public void backout() throws Exception
	{
		qManager.backout();
	}
    
	public void commit() throws Exception 
	{ 
		qManager.commit();
	}
}