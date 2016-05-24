/*
 * Created on 15-feb-07
 */
package co.com.telefonica.atiempo.utiles;

/**
 * @author TCS
 */
public class ATiempoAppEx extends Exception {

	/**
	 * @param string
	 */
	public ATiempoAppEx(String e)
	{
		super(e);
	}

	// CR20948 Altamira - Creo constructor para poder mensajes costomizados incluyendo datos como petiNumero - guido - 20/Abr/2009
	public ATiempoAppEx(String customMessage, Throwable t)
	{
		super(customMessage, t);
	}

	public static final int NAMING = 1001;
	public static final int CREATE = 1002;
	public static final int TN_PROCESS = 1003;
	public static final int XML = 1004;
	public static final int MARSHALL = 1005;
	public static final int UNMARSHALL = 1006;
	public static final int FINDER = 1007;
	public static final int LOAD_FILE = 1008;
	public static final int JMS = 1009;
	public static final int JMS_NOT_INITIALIZED = 1010;
	public static final int NUMBER_FORMAT = 1011;
	public static final int EXCEPTION = 1012;
	public static final int EJBEXCEPTION = 1013;
	public static final int REMOVEEXCEPTION = 1014;
	public static final int FECHAEXCEPTION = 1015;
	

	private int type;

	public ATiempoAppEx(int type, Throwable cause) {
		super(cause.getMessage());
		this.type = type;
	}
	
	public ATiempoAppEx(String string, int type)
	{
		super(string);
		this.type=type;
	}
	
	public ATiempoAppEx(int type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public int getType() {
		return type;
	}

}
