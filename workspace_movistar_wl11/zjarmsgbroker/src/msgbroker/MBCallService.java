package msgbroker;

// MBCallService
//
// Implementa llamadas al MB via protocol MB
//
// Para cada llamada a exec() se solicita un MBConnection
// al MBPoolManager via reserve(), se usa el MBConnection para la
// transaccion y se libera al MBPoolManager via free()
//

public class MBCallService extends BasicCallService {

    public MBCallService( Config aConfig ) throws MBException {
        super( aConfig );

        MBPoolManager.initPool();

    }

    public void exec() throws MBException {
        MBConnection mbc;
        Config config = getConfig();
        String outputQName;
        String outputQManagerName;
        String cicsTrx;
        String messageType;

        messageType = getMessageType();

        outputQName = config.getValue( "outputQ" );
        if( outputQName == null )
            throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                                   "parametro outputQ no definido" );

        outputQManagerName = config.getValue( "outputQManager" );
        if( outputQManagerName == null )
            throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                                   "parametro outputQManager no definido" );

        cicsTrx = config.getValue( "cicsTrx" );
        if( cicsTrx == null )
            cicsTrx = "TTTT";

        String mbType = config.getValue( "mbType" );

        try {
            if( mbType == null )
                mbc = MBPoolManager.reserve();
            else
                mbc = MBPoolManager.reserve(
                                     ( new Integer( mbType )).intValue() );
        }
        catch( Exception e ) {
            throw new MBException( "Fallo reserve() " + e );
        }


        if( mbc == null )
            throw new MBException( "No hay MBConnection disponibles" );

        try {
            mbc.setMessageType( messageType );
            mbc.setOutputQueueName( outputQName );
            mbc.setOutputQueueManagerName( outputQManagerName );
            mbc.setTimeout( getTimeout() );

            MQMsg mqMsg = new MQMsg();

            mqMsg.setHeaderVersion( "1" );
            mqMsg.setMessageType( messageType );
            mqMsg.setService( getService() );
            mqMsg.setTimeout( getTimeout() );
            mqMsg.setRetcode( 0 );
            mqMsg.setServerUser( getUserid() );
            mqMsg.setServerPassword( getPassword() );
            mqMsg.setCicsTrx( cicsTrx );
            mqMsg.setServerAppl( getServerAppl() );
            mqMsg.setData( new String( getData() ) );

            String mqFullMsg = new String( mqMsg.getMQMessage() );

            mbc.putMessage( mqFullMsg );

            if( messageType.equals( "I" ) || messageType.equals( "U" ) ) {
                mqMsg = new MQMsg( mbc.getReply().getBytes() );
                setResp( mqMsg.getData().getBytes() );
            }
            else
                setResp( (new String("sin respuesta" )).getBytes() );
        }
        catch( MBException e ) {
            MBPoolManager.free( mbc );
            throw e;
        }

        MBPoolManager.free( mbc );

    }

}
