package msgbroker;

// RHC 04/09/2002
//     Se modifica disconnect para asegurar el cierre de las colas y 
//     desconexion del Queue Manager

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQConnection {

    //
    // Una conexion con el QueueManager
    // permitiendo el manejo de dos colas:
    //
    // outputQueueName          nombre de la cola a la que se escribe
    // outputQueueManagerName   Queue Manager propietario cola output
    // replyQueueName           nombre de la cola de la que se lee

    private String outputQueueName;
    private String outputQueueManagerName;
    private String replyQueueName;
    private String queueManagerName;
    private String messageType;
    private int timeout;

    private MQQueueManager qManager;
    private MQQueue mqOutputQueue;
    private MQQueue mqReplyQueue;
    private MQMessage putMQMsg;
    private MQMessage replyMQMsg;

    public MQConnection( Config config, String outputQManager, String outputQueue, String replyQueue ) throws MBException {

        super();

        qManager = null;
        mqOutputQueue = null;
        mqReplyQueue  = null;

		timeout = 15;
		messageType = "I";
		
        queueManagerName = "";
        outputQueueName = outputQueue;
        outputQueueManagerName = outputQManager;
        replyQueueName = replyQueue;

        queueManagerName = config.getValue( "qManager" );
        if( queueManagerName == null )
           queueManagerName = "";
		
		String mqHostname = config.getValue( "mqHostname" );
        String mqChannel = config.getValue( "mqChannel" );
        String mqPort = config.getValue( "mqPort" );      
        Integer nmqPort = new Integer(mqPort);
		
        try {
            MQEnvironment.addConnectionPoolToken();
            if( mqHostname != null ) { // use MQ Client definitions
                MQEnvironment.hostname = mqHostname;
                MQEnvironment.port = nmqPort.intValue();
                MQEnvironment.channel = mqChannel;
            }
            qManager = new MQQueueManager( queueManagerName );
        }
        catch( MQException e ) {
            CallService.trace( "FALLO MQQueueManager: " + queueManagerName );
            throw new MBException( "MBConnection (MQQueueManager): " + e );
        }

            // abro colas de salida y de respuesta

        try  {
            mqOutputQueue = qManager.accessQueue(
                outputQueueName,
                MQC.MQOO_OUTPUT,
                outputQManager,
                null,
                null );
        }
        catch( MQException e ) {
            throw new MBException( "MBConnection (accessQueue output): " + e );
        }

        try {
            mqReplyQueue = qManager.accessQueue(
                replyQueueName,
                MQC.MQOO_INPUT_AS_Q_DEF );
        }
        catch( MQException e ) {
            throw new MBException( "MBConnection (accessQueue input): " + e );
        }

        // creo mqMsg

        putMQMsg = new MQMessage();
        replyMQMsg = new MQMessage();

    }

    public void disconnect() throws MBException {

        try {
            if( mqOutputQueue != null ) {
                mqOutputQueue.close();
                mqOutputQueue = null;
            }
            if( mqReplyQueue != null ) {
                mqReplyQueue.close();
                mqReplyQueue = null;
            }
            if( qManager != null ) {
                qManager.disconnect();
                qManager = null;
            }
        }
        catch( Exception e ) {
            if( qManager != null )
            try {
                qManager.disconnect();
            }
            catch( Exception e1 ) {
                throw new MBException( "disconnect: " + e1 );
            }
            throw new MBException( "disconnect: " + e );
        }
    }

    public void setMessageType( String aMessageType ) {

        messageType = aMessageType;

    }

	public void setTimeout( int aTimeout ) {
		
		timeout = aTimeout;
		
	}
	
    public void putMessage( String message ) throws MBException {
        MQPutMessageOptions pmo;

        pmo = new MQPutMessageOptions();

        CallService.trace( "MQPUT [" + message + "]" );

        try {
            putMQMsg.replyToQueueName = replyQueueName;
            putMQMsg.replyToQueueManagerName = queueManagerName;
            putMQMsg.writeBytes( message );
            if( messageType.equals("N" ) ) // mensaje de notificacion
                putMQMsg.persistence = MQC.MQPER_PERSISTENT;
            else
                putMQMsg.persistence = MQC.MQPER_NOT_PERSISTENT;
                        putMQMsg.format = MQC.MQFMT_STRING;
            mqOutputQueue.put( putMQMsg, pmo );
        }
        catch( Exception e ) {
            throw new MBException( "putMessage: " + e );
        }
    }

    public String getReply() throws MBException {
        MQGetMessageOptions gmo;

        gmo = new MQGetMessageOptions();

        replyMQMsg.messageId = putMQMsg.messageId;
	    gmo.waitInterval = timeout * 1000;
	    	
        gmo.options = MQC.MQGMO_WAIT;

        try {
            mqReplyQueue.get( replyMQMsg, gmo );

            String r = replyMQMsg.readString( replyMQMsg.getMessageLength() );

            CallService.trace( "MQGET [" + r + "]" );

            return r;
        }
        catch( MQException e ) {
            if( e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE  )
                throw new MBException( MBException.ERROR_TIMEOUT, 
                                       "waitServiceRequest: timeout" );
			else
            	throw new MBException( "getReply: " + e );
        }
        catch( Exception e ) {
        	throw new MBException( "readString" );
        }
    }

}




