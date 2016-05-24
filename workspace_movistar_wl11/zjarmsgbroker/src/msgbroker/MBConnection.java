package msgbroker;

import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
public class MBConnection {

    //
    // Un MBConnection es reponsable del manejo de una conexión
    // con el MB
    //
    // qManager             Handle de conexion al Queue Manager
    // mqOutputQueue        Cola de escritura al MB
    // mqReplyQueue         Cola de respuestas desde el MB
    // type                 Tipo del MBConnection (se indica al
    //                      solicitar uno libre)
    // status               Estado del MBConnection
    // timeLastUse         hora del ultimo uso del MBConnection
    //


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

    public static final int MBC_FREE        = 0;
    public static final int MBC_RESERVED    = 1;
    public static final int MBC_WORKING     = 2;
    public static final int MBC_CLOSED      = 3;

    public MBConnection(
        String myQueueManagerName,
        String aReplyQueueName,
        int aType ) throws MBException {

        super();

        type = aType;

        queueManagerName = myQueueManagerName;
        replyQueueName = aReplyQueueName;

        try {
            qManager = new MQQueueManager( queueManagerName );
        }
        catch( MQException e ) {
            System.out.println( "FALLO MQQueueManager: " + queueManagerName );
            throw new MBException( "MBConnection (MQQueueManager): " + e );
        }

        mqReplyQueue = null;
        setStatus( MBC_CLOSED );

        // abro cola de respuesta

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

        timeLastUse = System.currentTimeMillis();

        setStatus( MBC_FREE );

    }
    public void close() throws MBException {

        if( mqReplyQueue != null ) {
            try {
                mqReplyQueue.close();
                qManager.disconnect();
            }
            catch( Exception e ) {
                mqReplyQueue = null;
                throw new MBException( "disconnect: " + e );
            }
        }

        mqReplyQueue = null;

    }
    public String getOutputQueueManagerName() {

        return outputQueueManagerName;

    }
    public String getOutputQueueName() {

        return outputQueueName;

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

        // nos aseguramos que se haya definido la cola de salida

        if( outputQueueManagerName == null ||
            outputQueueName == null )
            throw new MBException( "putMessage: colas de salida indefinidas" );

        // abro cola de envio


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

        timeLastUse = System.currentTimeMillis();

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
            mqOutputQueue.close();
        }
        catch( Exception e ) {
            outputQueueName = null;
            outputQueueManagerName = null;
            try {
                mqOutputQueue.close();
            }
            catch( Exception e1 ) {
                throw new MBException( "fallo MQCLOSE" );
            }
            throw new MBException( "putMessage: " + e );
        }
    }
    public void sendLateReply( MQMessage replyMQMsg ) throws MBException {
        MQPutMessageOptions pmo;

        // nos aseguramos que se haya definido la cola de salida

        if( outputQueueManagerName == null ||
            outputQueueName == null )
            throw new MBException( "sendLateReply: colas de salida indefinidas" );

        // abro cola de envio

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
            mqOutputQueue.close();
            outputQueueName = null;
            outputQueueManagerName = null;
        }
        catch( Exception e ) {
            outputQueueName = null;
            outputQueueManagerName = null;
            try {
                mqOutputQueue.close();
            }
            catch( Exception e1 ) {
                throw new MBException( "fallo MQCLOSE" );
            }
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
    public void setStatus( int aStatus ) {

        status = aStatus;
        if ( status == MBC_RESERVED )
        	timeLastReserve = System.currentTimeMillis();

    }
    public void setTimeout( int aTimeout ) {

        timeout = aTimeout;

    }
}
