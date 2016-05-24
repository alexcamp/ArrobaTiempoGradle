package msgbroker;

public class MBException extends Exception {
    public int errorCode;
    public String msg;

    // Codigos de Error

    public static final int ERROR_TIMEOUT         = 1000;
    public static final int ERROR_NO_ESPECIFICADO = 1001;
    public static final int ERROR_ARCHIVO_PRC     = 1002;
    public static final int ERROR_ARCHIVO_CFG     = 1003;

public MBException() {
    super();
}

public MBException(String s) {
    super(s);
    msg = s;
    errorCode = ERROR_NO_ESPECIFICADO;
}

public MBException( int aErrorCode ) {
    super();
    msg = "no message specified";
    errorCode = aErrorCode;

}

public MBException( int aErrorCode, String aMsg ) {
	super();
	errorCode = aErrorCode;
	msg = aMsg;
}

public String toString() {
    // Insert code to print the receiver here.
    // This implementation forwards the message to super. You may replace or supplement this.
    return "MBException(" + errorCode + ") " + msg;
}
}
