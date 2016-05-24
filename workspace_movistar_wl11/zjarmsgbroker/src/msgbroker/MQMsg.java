package msgbroker;

import java.io.IOException;
import java.util.HashMap;

public class MQMsg {

//
// Responsable del manejo del contenido de un mensaje
// asociado a un servicio con protocol=mq
//

    private String headerVersion;
    private String messageType;
    private String service;
    private String serverUser;
    private String serverPassword;
    private String serverAppl;
    private String cicsTrx;
    private int timeout;
    private int retcode;
    private int datalen;

    private String data;

    public static final String MQM_NOTIFICATION = "N";
    public static final String MQM_REPROCESS = "D";
    // inicializa un MBMessage para ser enviado al MB

    public MQMsg() {

        messageType     = " ";
        service         = "SSSSSSSS";
        serverUser      = "UUUUUUUU";
        serverPassword  = "PPPPPPPP";
        serverAppl      = "AAAAAAAA";
        cicsTrx         = "CICS";
        timeout         = 30;
        retcode         = 0;
        data            = "";

    }
    // carga campos de MQMsg y extrae datos de un mensaje
    // llegado vía MQ (trae header y datos)

    public MQMsg( byte[] aMsg ) throws MBException {
        super();

        try {
            headerVersion = new String( aMsg, 0, 1 );
            messageType = new String( aMsg, 1, 1 );
            service = (new String( aMsg, 2, 8 )).trim();
            serverUser = (new String( aMsg, 10, 8 )).trim();
            serverPassword = (new String( aMsg, 18, 8 )).trim();
            serverAppl = (new String( aMsg, 26, 8 )).trim();
            cicsTrx = (new String( aMsg, 34, 4 )).trim();

            timeout = (new Integer( new String( aMsg, 38, 4 ) )).intValue();
            retcode = (new Integer( new String( aMsg, 42, 5 ) )).intValue();
            datalen = (new Integer( new String( aMsg, 47, 5 ) )).intValue();
            data = new String( aMsg, 64, aMsg.length - 64);
        }
        catch( Exception e ) {
e.printStackTrace();
            throw new MBException( "error en mensaje MQ" + e );
        }
    }
    private String format( int valor, String formato ) {
        java.text.DecimalFormat decform;

        decform = new java.text.DecimalFormat();
        decform.applyPattern( formato );

        return decform.format( valor );

    }
    public String getData() {

        return data;

    }
    public int getDatalen() {

        return datalen;

    }
    public String getHeaderVersion() {

        return headerVersion;

    }
    public String getMessageType() {

        return messageType;

    }
    public byte[] getMQMessage() {
        // entrega 64 bytes de header + datos

        String fullMsg = headerVersion + messageType + service + serverUser +
                  serverPassword + serverAppl +
                  cicsTrx +
                  format( timeout, "0000" ) +
                  format( retcode, "00000" ) +
                  format( datalen, "00000" ) +
                  "            " +
                  data;


        return( fullMsg.getBytes() );
    }
    public int getRetcode() {

        return retcode;

    }
    public String getServerPassword() {

        return serverPassword;

    }
    public String getServerUser() {

        return serverUser;

    }
    public String getService() {

        return service;

    }
    public int getTimeout() {

        return timeout;

    }
    public static void main(String[] args) {

        MQMsg mqMsg;

        try {
            String msg;

            msg = "A";
            msg = msg + "SALDOS  ";         // service
            msg = msg + "PHOFFER ";         // serverUser
            msg = msg + "PEPE    ";         // serverPassword
            msg = msg + "APLICA01";         // serverAppl
            msg = msg + "TRXA";             // cicsTrx
            msg = msg + "0030";             // timeout
            msg = msg + "00000";        // retcode
            msg = msg + "00020";        // datalen
            msg = msg + "             ";// filler

            msg = msg + "12345678901234567890";

            mqMsg = new MQMsg( msg.getBytes() );

            System.out.println( "messageType: " + mqMsg.getMessageType() );
            System.out.println( "service: " + mqMsg.getService() );
            System.out.println( "timeout: " + mqMsg.getTimeout() );
            System.out.println( "datalen: " + mqMsg.getDatalen() );
            System.out.println( "datos: " + mqMsg.getData() );

            mqMsg = new MQMsg();

            mqMsg.setMessageType( "R" );
            mqMsg.setService( "ALGO" );
            mqMsg.setServerUser( "RAFAEL" );
            mqMsg.setServerPassword( "PEPITO" );
            mqMsg.setServerAppl( "aplica" );
            mqMsg.setCicsTrx( "cics" );
            mqMsg.setTimeout( 40 );
            mqMsg.setRetcode( 11 );

            mqMsg.setData( "LOS DATOS" );

            System.out.println( new String(mqMsg.getMQMessage() ) );

            }
        catch( Exception e ) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(0 );
        }

    }
    public void setCicsTrx( String aCicsTrx ) throws MBException {
        int len = aCicsTrx.length();

        if( len != 4 )
            throw new MBException( "cicsTrx" );

        cicsTrx = aCicsTrx;

    }
    public void setData( String aData ) {

        data = aData;
        datalen = data.length();

    }
    public void setHeaderVersion( String aHeaderVersion ) throws MBException {

        if( aHeaderVersion.length() != 1 )
            throw new MBException( "setMessageType" );

        headerVersion = aHeaderVersion.substring( 0, 1 );

    }
    public void setMessageType( String aMsgType ) throws MBException {

        if( aMsgType.length() != 1 )
            throw new MBException( "setMessageType" );

        messageType = aMsgType.substring( 0, 1 );

    }
    public void setRetcode( int aRetcode ) {

        retcode = aRetcode;

    }
    public void setServerAppl( String aServerAppl ) throws MBException {
        int len = aServerAppl.length();

        if( len > 8 || len == 0 )
            throw new MBException( "setServerAppl" );

        serverAppl = aServerAppl;
        for( int i = len; i < 8; i++ )
            serverAppl = serverAppl + " ";

    }
    public void setServerPassword( String aPassword ) throws MBException  {
        int     len = aPassword.length();

        if( len > 8 || len == 0 )
            throw new MBException( "setServerPassword" );

        serverPassword = aPassword;
        for( int i = len; i < 8; i++ )
            serverPassword = serverPassword + " ";

    }
    public void setServerUser( String aUser ) throws MBException {
        int     len = aUser.length();

        if( len > 8 || len == 0 )
            throw new MBException( "setServerUser" );

        serverUser = aUser;
        for( int i = len; i < 8; i++ )
            serverUser = serverUser + " ";
    }
    public void setService( String aService ) throws MBException {
        int     len = aService.length();

        if( len > 8 || len == 0 )
            throw new MBException( "setService" );

        service = aService;
        for( int i = len; i < 8; i++ )
            service = service + " ";

    }
    public void setTimeout( int aTimeout ) {

        timeout = aTimeout;

    }
    
	public byte[] setResponse(String newData)
	{
		try
		{
			if(getMessageType().equals("I") || getMessageType().equals("U"))
				setMessageType("R");
			setData(newData);
		}
		catch(Exception e)
		{
			System.err.println("MBroker error : ");
			if(e instanceof MBException)
				System.err.println("Error Code\t\t\t:\t\t" + ((MBException)e).errorCode);
			System.err.println("Message\t\t\t\t:\t\t" + e.getMessage());
			return null;
		}
		return getMQMessage();
	}

	public byte[] setMessage(String serviceName, String newData) throws MBException	{

		Config aConfig = null;
		try {
			aConfig = new Config( serviceName + ".cfg" );
		}
		catch( IOException e ) {
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "Error Archivo Configuracion CFG" );
		}

		if (headerVersion == null)
			headerVersion = "1";

		service = aConfig.getValue( "service" );
		if( service == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro service en CFG" );
		messageType = aConfig.getValue( "type" );
		if( messageType == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro type en CFG" );    	
    
		serverUser = aConfig.getValue( "userid" );
		if( serverUser == null )
			serverUser = "        ";
    
		serverPassword = aConfig.getValue( "password" );
		if( serverPassword == null )
			serverPassword = "        ";
    
		serverAppl = aConfig.getValue( "serverAppl" );
		if( serverAppl == null )
			serverAppl = "        ";

		String sTimeout = aConfig.getValue( "timeout" );
		if( sTimeout == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro timeout en CFG" );
		try {
			timeout = (new Integer( sTimeout )).intValue();
		}
		catch( Exception e ) {
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "parametro timeout invalido en CFG" );    	
		}   

		setData( newData );

		return getMQMessage();
	}

	// Genera el Mensaje MQ Message Broker a partir de un HashMap con la Configuracion
	public byte[] setMessage(HashMap hashCfg, String newData) throws MBException	{

		if (headerVersion == null)
			headerVersion = "1";

		service = (String) hashCfg.get( "service" );
		if( service == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro service en CFG" );
		messageType = (String) hashCfg.get( "type" );
		if( messageType == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro type en CFG" );    	
    
		serverUser = (String) hashCfg.get( "userid" );
		if( serverUser == null )
			serverUser = "        ";
    
		serverPassword = (String) hashCfg.get( "password" );
		if( serverPassword == null )
			serverPassword = "        ";
    
		serverAppl = (String) hashCfg.get( "serverAppl" );
		if( serverAppl == null )
			serverAppl = "        ";

		String sTimeout = (String) hashCfg.get( "timeout" );
		if( sTimeout == null )
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "falta parametro timeout en CFG" );
		try {
			timeout = (new Integer( sTimeout )).intValue();
		}
		catch( Exception e ) {
			throw new MBException( MBException.ERROR_ARCHIVO_CFG,
								   "parametro timeout invalido en CFG" );    	
		}   

		setData( newData );

		return getMQMessage();
	}

}
