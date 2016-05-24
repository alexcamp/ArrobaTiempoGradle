package msgbroker;

//
// Pool de colas de respuestas para ser usadas en las transacciones
// tipo 'U' que requieren del servicio de reversas usado por el Message
// Broker
//


import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Vector;

    final public class QPoolManager {

    private static Config config;
    private static Vector qPoolList;

    private static boolean loaded = false;

    private static synchronized void initPool() throws MBException {

        if( !loaded )  {
            loaded = true;
            qPoolList = new Vector();
            loadConfig();
        }

    }

    public static synchronized String reserveQueue() throws MBException {
        int i;
        QPoolNode qNode;

        if( !loaded )
            initPool();

        for( i = 0; i < qPoolList.size(); i++ ) {
            qNode = (QPoolNode) qPoolList.elementAt( i );
            if( qNode.getQueueStatus() == QPoolNode.QNODE_FREE ) {
                qNode.setQueueStatus(QPoolNode.QNODE_RESERVED );
                return qNode.getQueueName();
            }
        }

        return null;



    }

    public static synchronized String reserveQueue( int queueClass )throws MBException  {
        int i;
        QPoolNode qNode;

        if( !loaded )
            initPool();

        for( i = 0; i < qPoolList.size(); i++ ) {
            qNode = (QPoolNode) qPoolList.elementAt( i );
            if( qNode.getQueueStatus() == QPoolNode.QNODE_FREE &&
                qNode.getQueueClass() == queueClass ) {
                qNode.setQueueStatus(QPoolNode.QNODE_RESERVED );
                return qNode.getQueueName();
            }
        }

        return null;

    }

    public static synchronized void freeQueue( String queueName ) throws MBException {
        int     i;
        QPoolNode qNode;

        if( !loaded )
            initPool();

        for( i = 0; i < qPoolList.size(); i++ ) {
            qNode = (QPoolNode) qPoolList.elementAt( i );
            if( qNode.getQueueName().equals( queueName ) )
                qNode.setQueueStatus( QPoolNode.QNODE_FREE );
        }

    }

    private static void loadConfig() throws MBException {
        String line, qName, sType, inputQ;
        BufferedReader br;
        int pos, type;

        try {
            config = new Config( "QPoolManager.cfg" );
        }
        catch( Exception e ) {
            throw new MBException( "loadConfig() " + e );
        }

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

                    CallService.trace( "qNode: " + qName + " type " + type );

                    QPoolNode qNode = new QPoolNode( qName, type );
                    qPoolList.addElement( qNode );
                }

            }
        }
        catch( Exception e ) {
            throw new MBException( "loadConfig() + " + e );
        }

    }

}
