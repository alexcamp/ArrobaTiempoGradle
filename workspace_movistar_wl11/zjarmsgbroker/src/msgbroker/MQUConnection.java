//
// Maneja una MQConnection para transacciones de Update (type=U)
//
// Para estos servicios se incorpora la funcionalidad de reversar todas
// las respuestas tardias que lleguen a la cola de respuestas
//
// La cola de respuestas la obtiene el llamados desde la clase QPoolManager
// y sera de uso exclusivo para esta invocacion
//

package msgbroker;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQUConnection {

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

    private int status;
    private int type;
    long timeLastUse;
    long timeLastReserve;

    public MQUConnection( Config config,
                          String outputQManager,
                          String outputQueue,
                          String aReplyQueueName ) throws MBException {

        super();

        queueManagerName = config.getValue( "qManager" );
        if( queueManagerName == null )
            queueManagerName = "";

        replyQueueName = aReplyQueueName;
        outputQueueName = outputQueue;
        outputQueueManagerName = outputQManager;

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
            CallService.trace( "FALLO MQUConnection: " + queueManagerName );
            throw new MBException( "MQUConnection (MQQueueManager): " + e );
        }

        // abro cola de envio

        mqOutputQueue = null;
        try  {
            mqOutputQueue = qManager.accessQueue(
                outputQueueName,
                MQC.MQOO_OUTPUT,
                outputQueueManagerName,
                null,
                null );
        }
        catch( MQException e ) {
            throw new MBException( "MBConnection (accessQueue output): " + e );
        }

        // abro cola de respuesta

        mqReplyQueue = null;

        try {
            mqReplyQueue = qManager.accessQueue(
                replyQueueName,
                MQC.MQOO_INPUT_AS_Q_DEF );
        }
        catch( MQException e ) {
            throw new MBException( "MQUConnection (accessQueue input): " + e );
        }

        // creo mqMsg

        putMQMsg = new MQMessage();
        replyMQMsg = new MQMessage();

        timeLastUse = System.currentTimeMillis();


    }

    public void close() throws MBException {

        if( mqOutputQueue != null ) {
            try {
                mqOutputQueue.close();
                mqOutputQueue = null;
            }
            catch( Exception e ) {
                mqOutputQueue = null;
            }
        }

        if( mqReplyQueue != null ) {
            try {
                mqReplyQueue.close();
                mqReplyQueue = null;
            }
            catch( Exception e ) {
                mqReplyQueue = null;
            }
        }

        try {
            qManager.disconnect();
        }
        catch( Exception e ) {
           throw new MBException( "MQUConnection(close) falla disconnect" + e );
        }

    }

    public String getReply() throws MBException {
        //
        // getReply: se traga todos los mensajes que hayan en la
        // cola hasta que aparezca el de respuesta buscado o se
        // cumpla el periodo de timeout. Los mensajes que sean
        // esperados son enviados al MB para que gestione su
        // servicio de reversa si corresponde
        //

        byte[] messageId;
        int     milisToTimeout, elapsed;
        long currentTime;

        MQGetMessageOptions gmo;

        messageId = putMQMsg.messageId;

        gmo = new MQGetMessageOptions();

        milisToTimeout = timeout * 1000;
        currentTime = System.currentTimeMillis();
        elapsed = 0;

        while( true ) {
            replyMQMsg.messageId = MQC.MQMI_NONE;
            elapsed = (int) (System.currentTimeMillis() - currentTime );
            milisToTimeout = milisToTimeout - elapsed;
            currentTime = System.currentTimeMillis();
            if( milisToTimeout > 0 )
                gmo.waitInterval = milisToTimeout;
            else
                gmo.waitInterval = 0;
            gmo.options = MQC.MQGMO_WAIT;

            try {
                replyMQMsg.clearMessage();
                mqReplyQueue.get( replyMQMsg, gmo );


                if( iguales( replyMQMsg.messageId, messageId ) ) {
                    String msg = replyMQMsg.readString( replyMQMsg.getMessageLength() );
                    CallService.trace( "MQGET [" + replyQueueName + "]" +
                                   " <" + msg + ">" );
                    return msg;
                }
                else {
                    sendLateReply( replyMQMsg );
                }
            }
            catch( MQException e ) {
                if( e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE ) {
                    throw new MBException( MBException.ERROR_TIMEOUT,
                                           "waitServiceRequest: timeout" );
                }
                else
                    throw new MBException( "getReply: " + e );
            }
            catch( Exception e ) {
                throw new MBException( "readString" + e );
            }
        }
    }
    public int getStatus() {

        return status;

    }
    public int getType() {

        return type;

    }
    private boolean iguales( byte[] m1, byte[] m2 ) {
        int i;

        for( i = 0; i < m1.length; i++ )
            if( m1[i] != m2[i] )
                return false;

        return true;

    }
    public void putMessage( String message ) throws MBException {
        MQPutMessageOptions pmo;

        pmo = new MQPutMessageOptions();

        CallService.trace( "MQPUT <" + message + ">" );

        try {
            putMQMsg.messageId = MQC.MQMI_NONE;
            putMQMsg.replyToQueueName = replyQueueName;
            putMQMsg.replyToQueueManagerName = queueManagerName;
            putMQMsg.clearMessage();
            putMQMsg.writeBytes( message );
            if( messageType.equals("N" ) || messageType.equals( "U" ) )
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
    public void sendLateReply( MQMessage replyMQMsg ) throws MBException {
        MQPutMessageOptions pmo;

        pmo = new MQPutMessageOptions();

        try {
            String msg = replyMQMsg.readString(
                               replyMQMsg.getMessageLength() );

            CallService.trace( "data <" + msg + ">" );

            replyMQMsg.replyToQueueName = replyQueueName;
            replyMQMsg.replyToQueueManagerName = queueManagerName;
            replyMQMsg.clearMessage();
            replyMQMsg.writeBytes( msg );
            replyMQMsg.persistence = MQC.MQPER_PERSISTENT;
            replyMQMsg.format = MQC.MQFMT_STRING;
            mqOutputQueue.put( replyMQMsg, pmo );
        }
        catch( Exception e ) {
            throw new MBException( "putMessage: " + e );
        }
    }

    public void setMessageType( String aMessageType ) {

        messageType = aMessageType;

    }
    public void setOutputQueueManagerName( String aName ) {

        outputQueueManagerName = aName;

    }
    public void setOutputQueueName( String aOutputQueueName ) {

        outputQueueName = aOutputQueueName;

    }

    public void setTimeout( int aTimeout ) {

        timeout = aTimeout;

    }
}
