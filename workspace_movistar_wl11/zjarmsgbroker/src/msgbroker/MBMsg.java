package msgbroker;

public class MBMsg {

    private String messageType;
    private String service;
    private String serverUser;
    private String serverPassword;
    private String serverAppl;
    private String cicsTrx;
    private int timeout;
    private int retcode;

    private String mbMessage;

    // carga campos de MBMessage y extrae datos de un mensaje
    // llegado desde el MB

    public MBMsg( String aMsg ) {
        super();

        mbMessage = aMsg;

    }


    // inicializa un MBMessage para ser enviado al MB

    public MBMsg() {

    }

    public void setMessageType( String aMsgType ) {

    }

    public void setService( String aService ) {

    }

    public void setServerUser( String aServerUser ) {

    }

    public void setMBHeaderFields() {
    }
}

