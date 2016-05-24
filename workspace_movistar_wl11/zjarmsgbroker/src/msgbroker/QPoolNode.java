//
// Clase que representa un nodo de la lista de nombres de cola
// disponibles y asignables por QPoolManager
//

package msgbroker;


public class QPoolNode {

    private String queueName;
    private int queueStatus;
    private int queueClass;

    public static final int QNODE_FREE      = 1;
    public static final int QNODE_RESERVED  = 2;

    public QPoolNode( String aQueueName, int aQueueClass ) {

    queueName = aQueueName;
    queueClass = aQueueClass;
    queueStatus = QNODE_FREE;

    }

    public String getQueueName() {
        return queueName;
    }

    public int getQueueStatus() {
        return queueStatus;
    }

    public int getQueueClass() {
        return queueClass;
    }

    public void setQueueName( String aQueueName ) {
        queueName = aQueueName;
    }

    public void setQueueStatus( int aQueueStatus ) {
        queueStatus = aQueueStatus;
    }

    public void setQueueClass( int aQueueClass ) {
        queueClass = aQueueClass;
    }


}
