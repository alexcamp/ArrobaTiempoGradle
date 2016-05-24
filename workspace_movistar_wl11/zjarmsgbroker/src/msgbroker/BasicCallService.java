package msgbroker;


public class BasicCallService {

private byte[] data;
private byte[] resp;
private int timeout;
private String userid;
private String password;
private Config config;
private String service;
private String serverAppl;
private String messageType;

public BasicCallService( Config aConfig ) throws MBException {
    super();

    config = aConfig;
    
    service = aConfig.getValue( "service" );
    if( service == null )
    	throw new MBException( MBException.ERROR_ARCHIVO_CFG,
    	                       "falta parametro service en CFG" );
    messageType = aConfig.getValue( "type" );
    if( messageType == null )
		throw new MBException( MBException.ERROR_ARCHIVO_CFG,
    	                       "falta parametro type en CFG" );    	
    
    userid = aConfig.getValue( "userid" );
    if( userid == null )
        userid = "        ";
    
    password = aConfig.getValue( "password" );
    if( password == null )
        password = "        ";
    
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

	String sData = aConfig.getValue( "data" );
	if( sData!= null )
		data = sData.getBytes();
	else
		data = null;

}

public void exec() throws MBException {

}

public void setService( String aService ) {

    service = aService;

}

public String getService() {

    return service;

}

public void setConfig( Config aConfig ) {

    config = aConfig;

}

public Config getConfig() {

    return config;

}

public void setData( byte[] aData ) {

    data = aData;

}

public byte[] getData() {

    return data;

}

public void setResp( byte[] aResp ) {

    resp = aResp;

}

public byte[] getResp() {

    return resp;

}

public void setTimeout( int aTimeout ) {

    timeout = aTimeout;

}

public int getTimeout() {

    return timeout;

}

public void setUserid( String aUserid ) {

    userid = aUserid;

}

public String getUserid() {

    return userid;

}

public void setPassword( String aPassword ) {

    password = aPassword;

}

public String getPassword() {

    return password;

}

public String getServerAppl() {
	
	return serverAppl;
	
}

public void setServerAppl( String aServerAppl ) {

	serverAppl = aServerAppl;
	
}

public String getMessageType() {
	
	return messageType;
	
}

public void setMessageType( String aMessageType ) {

	messageType = aMessageType ;
	
}

public String fixto8bytes( String s ) {

    if( s == null )
        return new String( "        " );

    if( s.length() > 8 )
        s = s.substring( 0, 8 );
    else
        for( int i = s.length(); i < 8; i++ )
            s = s + " ";
    return s.toUpperCase();

}

public String fixtoNbytes( String s, int len ) {

    if( s == null )
        return new String( "        " );

    if( s.length() > len )
        s = s.substring( 0, len );
    else
        for( int i = s.length(); i < len; i++ )
            s = s + " ";
            
    return s.toUpperCase();

}

	public void getMqMessage() throws MBException {
	}

}
