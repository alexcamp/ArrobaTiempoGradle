package msgbroker;

// RHC 04/09/2002
//     Se modifica main para realizar operacion CallService dentro
//     del loop interno

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallService {

private BasicCallService callService;
private Config serviceConfig;
private String protocol;

public CallService( String serviceName ) throws MBException {

	    try {
	        serviceConfig = new Config( serviceName + ".cfg" );
	    }
	    catch( IOException e ) {
	        throw new MBException( MBException.ERROR_ARCHIVO_CFG,
	                               "Error Archivo Configuracion CFG: " + e);
	    }
	
	    protocol = serviceConfig.getValue( "protocol" );

    if( protocol == null )
        throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                               "Archivo CFG: falta protocolo" );

    if( protocol.equals( "sockets" ) )
        callService = new SocketsCallService( serviceConfig );
    else if( protocol.equals( "MB" ) )
        callService = new MBCallService( serviceConfig );
    else if( protocol.equals( "MQ" ) ){
        callService = new MQCallService( serviceConfig );
//    else if( protocol.equals( "CTG" ) )
//        callService = new CTGCallService( serviceConfig );
	}
    else
        throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                               "Archivo CFG: protocolo invalido" );

}

public static boolean isTraceActive() {

    if( System.getProperty( "MBTrace" ) == null )
        return false;
    else
        return true;
}

public static void trace( String msg ) {

    if( System.getProperty( "MBTrace" ) != null ) {
        SimpleDateFormat formatter = new
            SimpleDateFormat( "[yyyy/MM/dd kk:mm:ss.SSS] " );
        String sDate = formatter.format( new Date() );
        System.out.println( sDate + msg );
    }

}

public void exec() throws MBException {

    String response = serviceConfig.getValue( "response" );
    String type = serviceConfig.getValue( "type" );

    if( response != null && !type.equals( "N" ) ) {
        callService.setResp( response.getBytes() );
        return;
    }

    callService.exec();

}

public void setData( byte[] aData ) {

    callService.setData( aData );

}

public byte[] getResp() {

    return callService.getResp();

}

public void setTimeout( int aTimeout ) {

    callService.setTimeout( aTimeout );

}

public void setUserid( String aUserid ) {

    callService.setUserid( aUserid );

}

public void setPassword( String aPassword ) {

        callService.setPassword( aPassword );

}

public static void main(String[] args) {
    CallService cs = null;
    int nR;

    if( args.length == 0 )
        System.exit(0 );
    else
        try {
                // cs = new CallService( args[0] );
        }
        catch( Exception e ) {
            System.out.println(e);
            e.printStackTrace();
            System.exit( 0 );
        }

    if( args.length >= 2 )
        nR = (new Integer( args[1] ) ).intValue();
    else
        nR = 1;

    for( int i = 0; i < nR; i++ ) {
            try {
                    cs = new CallService( args[0] );
            }
            catch( Exception e ) {
                System.out.println(e);
                e.printStackTrace();
                System.exit( 0 );
            }

            try {
                if( args.length >= 3 )
                    cs.setData( (args[2] + i).getBytes() );
                // cs.setData( ("rafael:" + i).getBytes() );
                // SimpleDateFormat formatter = new
                // SimpleDateFormat( "[yyyy/MM/dd kk:mm:ss.SSS] " );
                // String sDate = formatter.format( new Date() );
                // cs.setData( sDate.getBytes() );
                cs.exec();
                byte[] resp = cs.getResp();
                System.out.println( "CallService [" + i + "]" );
                System.out.println("Resp = <" + new String(resp) +">");
                System.out.println("RespLen = " + resp.length);
            }
            catch( Exception e ) {
                System.out.println( "!!!RAFA!!" );
                System.out.println(e);
                e.printStackTrace();
            }
            
    }

}


}
