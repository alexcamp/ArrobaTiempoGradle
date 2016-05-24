package msgbroker;

import java.io.IOException;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class Process {

    private Config processConfig;
    private String processName;
    private String serviceName;
    private String userid;
    private String password;
    private int retcode;
    private String data;
    private String response;

    // componentes MQSeries

    private String inputQueueName;
    private String replyQueueName;
    private String replyQueueManagerName;
    private MQQueueManager qManager;
    private MQQueue mqInputQueue;
    private MQMessage getMQMsg;
    private MQMsg mqMsg = null;
    public Process( String aProcessName ) throws MBException{

        processName = aProcessName;

        try {
            processConfig = new Config( processName + ".prc" );
        }
        catch( IOException e ) {
            throw new MBException( MBException.ERROR_ARCHIVO_PRC,
                                   "Archivo .PRC no encontrado" );
        }

        String queueManagerName = processConfig.getValue( "qManager" );
        String mqHostname        =processConfig.getValue( "mqHostname" );
        String mqChannel        = processConfig.getValue( "mqChannel" );
        String mqPort           = processConfig.getValue( "mqPort" );

        if( queueManagerName == null )
           queueManagerName = "";

        inputQueueName = processConfig.getValue( "inputQ" );
        if( inputQueueName == null )
            throw new MBException( "inputQ no especificado" );

        try {
            if( mqHostname != null ) { // use MQ Client definitions
                MQEnvironment.hostname = mqHostname;
                MQEnvironment.port = 1414;
                MQEnvironment.channel = mqChannel;
            }

            qManager = new MQQueueManager( queueManagerName );

            mqInputQueue = qManager.accessQueue(
                inputQueueName,
                MQC.MQOO_INPUT_AS_Q_DEF );

            // creo getMQMsg

            getMQMsg = new MQMessage();
        }
        catch( MQException e ) {
            throw new MBException( "falla MQSeries " + e );
        }

    }
    public void closeProcess() throws MBException {

        try {
            mqInputQueue.close();
            qManager.disconnect();
        }
        catch( Exception e ) {
            throw new MBException( "closeProcess: " + e );
        }

    }
/**
 * Insert the method's description here.
 * Creation date: (05-08-2002 20:46:35)
 */
public void commit() throws MQException {
	qManager.commit();
}
    public String getData() {

        if( mqMsg != null )
            return( mqMsg.getData() );
        else
            return null;

    }
    public String getMessageType() {

        if( mqMsg != null )
            return( mqMsg.getMessageType() );
        else
            return null;

    }
    public String getPassword() {

        if( mqMsg != null )
            return( mqMsg.getServerPassword() );
        else
            return null;

    }
    public int getRetcode() {

        if( mqMsg != null )
            return( mqMsg.getRetcode() );
        else
            return -1;

    }
    public String getServiceName() {

        if( mqMsg != null )
            return( mqMsg.getService() );
        else
            return null;

    }
    public String getUserid() {

        if( mqMsg != null )
            return( mqMsg.getServerUser() );
        else
            return null;

    }
    public static void main(String[] args) {
        Process myProcess;
        byte cmd[];
        String cmdStr;

        cmd = new byte[100];
        if( args.length == 0 )
            System.exit( 0 );

        try {
            while( true ) {
 	            myProcess = new Process( args[0] );
 	            System.out.println("Esperando mensaje ....");
	            myProcess.waitServiceRequest( 2000 );
                System.out.println( myProcess.getData() );
                try {
	                System.out.println("Cmd (1:backout, 2:reproc, 3:proc, 0:end)");
	                System.in.read(cmd);
                } catch ( Exception e ) {
                }
                cmdStr = new String(cmd);

                if ( cmdStr.charAt(0) == '1' ) {
	                try {
		                System.out.println("Backout");
		                myProcess.backout();
	                } catch ( Exception e ) {
	                }
                }
                if ( cmdStr.charAt(0) == '2' ) {
	                try {
		                System.out.println("reproc");
		                myProcess.reprocess();
	                } catch ( Exception e ) {
	                }
                }
                if ( cmdStr.charAt(0) == '3' ) { 
	                try {
		                System.out.println("proc");
		                myProcess.sendResponse("HOLA LOLITO", 0);
	                } catch ( Exception e ) {
	                }
                }
                if ( cmdStr.charAt(0) == '0' ) { 
	                try {
		                System.out.println("end");
		                return;
	                } catch ( Exception e ) {
	                }
                }
                myProcess.closeProcess();
            }
        }
        catch( MBException e ) {
            System.out.println(e);
            e.printStackTrace();
            System.exit( 0 );
        }

    }
/**
 * Insert the method's description here.
 * Creation date: (05-08-2002 21:00:15)
 */
public void reprocess() throws MBException {
   MQPutMessageOptions pmo;
   MQMessage putMQMsg;
   MQQueue mqOutputQueue;

   pmo = new MQPutMessageOptions();

   if( mqMsg != null ) {
      mqMsg.setMessageType( MQMsg.MQM_REPROCESS );
	  mqMsg.setHeaderVersion( mqMsg.getHeaderVersion() );
      mqMsg.setService( mqMsg.getService() );
      mqMsg.setServerUser( mqMsg.getServerUser() );
      mqMsg.setServerPassword( mqMsg.getServerPassword() );

      putMQMsg = new MQMessage();
      putMQMsg.replyToQueueName = replyQueueName;
      putMQMsg.replyToQueueManagerName = replyQueueManagerName;
      putMQMsg.format = MQC.MQFMT_STRING;
      putMQMsg.messageId = getMQMsg.messageId;
      String fullMsg = new String( mqMsg.getMQMessage() );
      try {
         putMQMsg.writeBytes( fullMsg );
         mqOutputQueue = qManager.accessQueue(
                         replyQueueName,			
                         MQC.MQOO_OUTPUT,
                         replyQueueManagerName,
                         null,
                         null );
         CallService.trace( "MQPUT REPROCESS <" + fullMsg + ">" );
         mqOutputQueue.put( putMQMsg, pmo );
         mqOutputQueue.close();
      }
      catch( Exception e ) {
         throw new MBException( "reprocess " + e );
      }
   }

   try {
      commit();
   }
   catch ( MQException e ) {
	  throw new MBException(e.completionCode, "reprocess: commit error");
   }
}
    public void sendResponse( String aResponse, int aRetcode ) throws MBException {
        MQPutMessageOptions pmo;
        MQMessage putMQMsg;
        MQQueue mqOutputQueue;

        pmo = new MQPutMessageOptions();

        if( mqMsg != null ) {
            if( mqMsg.getMessageType().equals( "I" ) ||
                mqMsg.getMessageType().equals( "U" ) ) {
                mqMsg.setMessageType( "R" );
                mqMsg.setHeaderVersion( mqMsg.getHeaderVersion() );
                mqMsg.setService( mqMsg.getService() );
                mqMsg.setServerUser( mqMsg.getServerUser() );
                mqMsg.setServerPassword( mqMsg.getServerPassword() );
                mqMsg.setServerAppl( " ");
                mqMsg.setCicsTrx( "    " );
                mqMsg.setData( aResponse );
                mqMsg.setRetcode( aRetcode );
                String fullMsg = new String( mqMsg.getMQMessage() );
                putMQMsg = new MQMessage();
                putMQMsg.replyToQueueName = " ";
                putMQMsg.replyToQueueManagerName = " ";
                putMQMsg.format = MQC.MQFMT_STRING;
                putMQMsg.messageId = getMQMsg.messageId;
                try {
                    putMQMsg.writeBytes( fullMsg );
                    mqOutputQueue = qManager.accessQueue(
                        replyQueueName,
                        MQC.MQOO_OUTPUT,
                        replyQueueManagerName,
                        null,
                        null );
                    CallService.trace( "MQPUT <" + fullMsg + ">" );
                    mqOutputQueue.put( putMQMsg, pmo );
                    mqOutputQueue.close();
                }
                catch( Exception e ) {
                    throw new MBException( "sendResponse " + e );
                }

            }
        }

    }
public void setResponse( String aResponse ) {
   response = aResponse;
}
    public void waitServiceRequest( int aTimeout ) throws MBException {
        MQGetMessageOptions gmo;

        gmo = new MQGetMessageOptions();

        // En el caso de que el mensaje es de notificación se
        // mantiene pendiente de Commit.
        // Si se llama a la función nuevamente primero se hace commit del mensaje anterior
        try {
 	       commit();
        }
		catch ( MQException e ) {
		   throw new MBException(e.completionCode, "waitServiceRequest: commit error");
		} 
	        
        gmo.options = MQC.MQGMO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
        
        gmo.waitInterval = aTimeout * 1000;

        try {
            getMQMsg.messageId = MQC.MQMI_NONE;
            mqInputQueue.get( getMQMsg, gmo );
            try {
                replyQueueName = getMQMsg.replyToQueueName;
                replyQueueManagerName = getMQMsg.replyToQueueManagerName;
                String r = getMQMsg.readString( getMQMsg.getMessageLength() );
                mqMsg = new MQMsg( r.getBytes() );

                CallService.trace( "MQGET <" + r + ">" );
            }
            catch( Exception e ) {
                throw new MBException( "error en MQMsg" );
            }
        }
        catch( MQException e ) {
            if( e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE  ) {
                throw new MBException( MBException.ERROR_TIMEOUT,
                                       "waitServiceRequest: timeout" );
            }
            else
                throw new MBException( "getMsg: " + e );
        }

        // Si el mensaje es distinto de NOTIFICACION 
        // se hace commit 
        try {
	        if ( mqMsg.getMessageType().compareToIgnoreCase(MQMsg.MQM_NOTIFICATION) != 0 )
 	          	commit();
        }
		catch ( MQException e ) {
		   throw new MBException(e.completionCode, "waitServiceRequest: No Notification commit error");
		} 

    }

/**
 * Insert the method's description here.
 * Creation date: (05-08-2002 20:46:35)
 */
public void backout() throws MQException {
	qManager.backout();
}
}
