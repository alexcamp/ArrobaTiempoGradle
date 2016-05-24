package msgbroker;

/**
 * Insert the type's description here.
 * Creation date: (06-08-2002 22:27:33)
 * @author: Administrator
 */
public class TestMQPMgr extends Thread {
	static String m_fName;
	int 		  m_appNumber;
/**
 * TestMQPMgr constructor comment.
 */
public TestMQPMgr(int appNumber) {
	super();

	m_appNumber = appNumber;
	
	start();
}
/**
 * Insert the method's description here.
 * Creation date: (06-08-2002 22:34:41)
 */
public static void init() {
}
/**
 * Insert the method's description here.
 * Creation date: (06-08-2002 22:32:27)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	TestMQPMgr test[];
	int        numThread;

	TestMQPMgr.init();
	numThread = Integer.parseInt(args[0]);
	
	test = new TestMQPMgr[numThread];
	while ( true ) {
		for ( int i = 0; i < numThread; i++ )
			test[i] = new TestMQPMgr(i);
			
		for ( int i = 0; i < numThread; i++ ) {
			try {
				while ( test[i].isAlive() );
			} catch (Exception e ) {
				System.out.println("TestMQPMgr: Wait error");
				e.printStackTrace();
			}
		}
		System.out.println("Enter....");
		try {
			System.in.read();
		} catch ( Exception e ) {
		}
	}
}
/**
 * Insert the method's description here.
 * Creation date: (06-08-2002 22:35:36)
 */
public void run() {
	MBCallService 	mbCall = null;
	Config 			cfg = null;
	String			data = "090102006000";
	byte 			resp[];
	String			file;
	String  		respString;

	file = "testmq";
	try {
		cfg = new Config(file);
		mbCall = new MBCallService(cfg);

//		mbCall.setData(data.getBytes());
		mbCall.exec();
//		sleep(1000);
		resp = mbCall.getResp();
		respString = new String(resp);
		System.out.println("Test app (" + m_appNumber + ") [" + respString + "]");
	}
	catch ( Exception e ) {
		System.out.println("Error:" + e.toString());
	}
}
}
