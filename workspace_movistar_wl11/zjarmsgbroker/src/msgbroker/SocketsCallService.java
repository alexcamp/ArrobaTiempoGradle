package msgbroker;

import java.io.IOException;

public class SocketsCallService extends BasicCallService {

    private static final short CALLSERVICE_HEADER_LEN = 2 + 64;

    private String  version;        // version del header
    private String  msg_type;       // tipo mensaje (tran., resp., ...)
    private int     server_node;    // direccion IP servidor
    private short   server_port;    // puerto IP del servidor
    private String  server_trx;     // Programa a llamar en destino final
    private String  cics_server;    // CICS donde se ejecuta el programa final
    private int     cliente_node;   // direccion IP cliente
    private String  cliente_user;   // nombre usuario requirent
    private String  cliente_appl;   // Aplicación cliente
    private String  cliente_pwd;    // password del usuario
    private short   retcode;        // codigo retorno en respuesta
    private short   datalen;        // largo buffer de datos
    private String  filler;         // 6 bytes para uso futuro

    private String  server_host;    // Nombre del host


public SocketsCallService( Config aConfig )throws MBException {

    super( aConfig );

    version      = "A";
    msg_type     = "T";
    server_node  = 0;
    server_trx   = "        ";
    cics_server  = "        ";
    cliente_node = 0;
    cliente_user = "        ";
    cliente_appl = "        ";
    cliente_pwd  = "        ";
    setTimeout( 60 );
    retcode      = 0;
    datalen      = 0;
    filler       = "000000";

    server_host = aConfig.getValue( "host" );

    server_port  = new Short( aConfig.getValue( "port" ) ).shortValue();

    server_trx = aConfig.getValue( "service" );
    server_trx = fixto8bytes( server_trx );
    cics_server = server_trx;
    cliente_appl = server_trx;

    String sData = aConfig.getValue( "data" );
    if( sData != null )
        setData( sData.getBytes() );

    if( getData() != null )
        datalen = (short) getData().length;
    else
        datalen = 0;

    cliente_user = aConfig.getValue( "userid" );
    cliente_user = fixto8bytes( cliente_user );

    cliente_pwd = aConfig.getValue( "password" );
    cliente_pwd = fixto8bytes( cliente_pwd );


}

public void exec() throws MBException {
    Cliente comm = null;

    System.out.println( "<" + server_trx + "><" + cliente_user + "> <" + cliente_pwd + ">" );

	try {
    	// Conección cliente
    	comm = new Cliente(server_host, server_port);

	    // Setear timeout
 	   comm.setTimeout( (short) getTimeout() );

	    // Armar header callservice
 	   sendHeader(comm);

	    // enviar data
 	   comm.so_write( getData() );
	
 	   // leer header
  	  getHeader(comm);

	    // Leer respuesta sobre resp
 	   setResp( comm.so_read(datalen) );
	}
	catch( Exception e ) {
		throw new MBException( "exec: " + e );
	}

}

public void getHeader(Cliente comm) throws IOException {
    short lenHdr;

    lenHdr = comm.so_readShort();
    if ( lenHdr != CALLSERVICE_HEADER_LEN ) {
        throw new IOException("Largo header no corresponde");
    }


    version      = comm.so_readString(1);
    msg_type     = comm.so_readString(1);
    server_node  = (int)comm.so_readLong();
    server_port  = comm.so_readShort();
    cics_server  = comm.so_readString(8);
    cliente_node = (int)comm.so_readLong();
    cliente_user = comm.so_readString(8);
    cliente_appl = comm.so_readString(8);
    cliente_pwd  = comm.so_readString(8);
    setTimeout( (int) comm.so_readShort() );
    retcode      = comm.so_readShort();
    datalen      = comm.so_readShort();
    filler       = comm.so_readString(6);

}

public void sendHeader(Cliente comm) throws IOException {
    comm.so_writeShort(CALLSERVICE_HEADER_LEN);
    comm.so_fixwrite(version.getBytes());
    comm.so_fixwrite(msg_type.getBytes());
    comm.so_writeInt(server_node);
    comm.so_writeShort(server_port);
    comm.so_fixwrite(server_trx.getBytes());
    comm.so_fixwrite(cics_server.getBytes());
    comm.so_writeInt(cliente_node);
    comm.so_fixwrite(cliente_user.getBytes());
    comm.so_fixwrite(cliente_appl.getBytes());
    comm.so_fixwrite(cliente_pwd.getBytes());
    comm.so_writeShort((short) getTimeout() );
    comm.so_writeShort(retcode);
    comm.so_writeShort(datalen);
    comm.so_fixwrite(filler.getBytes());
}


}
