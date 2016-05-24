package msgbroker;

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Vector;

// MBPoolManager
//
// Administra Pool de Conexiones al MB (MBConnection)
//
// La configuracion a usar se carga desde el archivo de configuracion
// MBPoolManager.cfg:
//
//      qManager    Nombre del QueueManager del MB
//      inputQ      Lista de colas de entrada a administrar
//                  Cada cola de entrada se asocia a un MBConnection
//

final public class MBPoolManager {
	private static long timeAlive;

    private static Config config;
    private static Vector mbConnectionTable;

    private static boolean loaded = false;

    public void close() throws MBException {
        int     i;
        MBConnection mb;

        for( i = 0; i < mbConnectionTable.size(); i++ ) {
            mb = (MBConnection) mbConnectionTable.elementAt( i );
            mb.close();
            mb.setStatus( MBConnection.MBC_CLOSED );
        }
    }
    public static synchronized void free( MBConnection aMBConnection ) {
        int     i;
        MBConnection mb;

        for( i = 0; i < mbConnectionTable.size(); i++ ) {
            mb = (MBConnection) mbConnectionTable.elementAt( i );
            if( mb == aMBConnection )
                mb.setStatus( MBConnection.MBC_FREE );
        }

    }
    public static synchronized void initPool() throws MBException {

        if( !loaded )  {
            loaded = true;
            mbConnectionTable = new Vector();
            loadConfig();
        }

    }
    private static void loadConfig() throws MBException {
        String line, qName, sType, qMName, inputQ, timeAliveString;
        BufferedReader br;
        int pos, type;

        try {
            config = new Config( "MBPoolManager.cfg" );
        }
        catch( Exception e ) {
            throw new MBException( "loadConfig() " + e );
        }

        qMName = config.getValue( "qManager" );
        if( qMName == null )
            qMName = "";

        timeAliveString = config.getValue( "timealive" );
        if( timeAliveString == null )
            timeAliveString = "15";
        timeAlive = Long.parseLong(timeAliveString) * 60 * 1000;
        System.out.println("Time Alive : " + timeAlive);

        inputQ = config.getValue( "inputQ" );
        if( inputQ == null )
            throw new MBException( "falta parametro inputQ en MBPoolManager.cfg" );

        StringTokenizer st = new StringTokenizer( inputQ, "(),:", true );
        int state = 0;
        type = 0;
        qName = "";

        try {
            while( state != 5 && st.hasMoreTokens() ) {
                String token = st.nextToken();

                // CallService.trace( "state " + state + " Token <" + token + ">" );

                if( state == 0 ) { // expecting "("
                    if( !token.equals( "(" ) )
                        throw new MBException( "inputQ syntax error" );
                    state = 1;
                }
                else if( state == 1 ) { // expecting qName
                    qName = new String( token );
                    state = 2;
                }
                else if( state == 2 ) { // expecting ":"
                    if( !token.equals( ":" ) )
                        throw new MBException( "inputQ syntax error" );
                    state = 3;
                }
                else if( state == 3 ) { // expecting integer (type
                    type = (new Integer( token )).intValue();
                    state = 4;
                }
                else if( state == 4 ) { // expecting ","
                    if( token.equals( ")" ) )
                        state = 5;
                    else if( !token.equals( "," ) ) {
                        throw new MBException( "inputQ syntax error" );
                    }
                    else
                        state = 1;

                    // CallService.trace( "mbc: " + qName + " type " + type );

                    MBConnection mbc = new MBConnection( qMName, qName, type );
                    mbConnectionTable.addElement( mbc );
                }

            }
        }
        catch( Exception e ) {
            throw new MBException( "loadConfig() + " + e );
        }

    }
    public static synchronized MBConnection reserve() {
        int     i;
        MBConnection mb;

        for( i = 0; i < mbConnectionTable.size(); i++ ) {
            mb = (MBConnection) mbConnectionTable.elementAt( i );
            if( mb.getStatus() == MBConnection.MBC_FREE ||
	            (System.currentTimeMillis() - mb.timeLastReserve) > timeAlive ) {
                mb.setStatus( MBConnection.MBC_RESERVED );
                return mb;
            }
        }

        return null;
    }
public static synchronized MBConnection reserve( int mbType ) {
  int     i;
  MBConnection mb;

  for( i = 0; i < mbConnectionTable.size(); i++ ) {
     mb = (MBConnection) mbConnectionTable.elementAt( i );
     if( ( mb.getStatus() == MBConnection.MBC_FREE ||
	      (System.currentTimeMillis() - mb.timeLastReserve ) > timeAlive ) &&
          mb.getType() == mbType ) {
            mb.setStatus( MBConnection.MBC_RESERVED );
            return mb;
     }
  }

  return null;
}
}
