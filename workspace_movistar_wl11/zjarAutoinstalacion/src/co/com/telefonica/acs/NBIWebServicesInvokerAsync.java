/*
 * Created on Jan 3, 2012
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.acs;

import java.util.ArrayList;


import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import commonj.work.Work;
import commonj.work.WorkItem;
import commonj.work.WorkManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


/**
 * @author damartinezv
 *
 * Esta clase es un proxy que implementa la l�gica para usar de manera asincrona el 
 * llamado de los WebServices.
 */
public class NBIWebServicesInvokerAsync extends NBIWebServicesInvoker implements Work,Cloneable {
	
	private static int configurarModem=1;
	private static int modificarModem=2;
	private static int desconfigurarModem=3;
		
	private NBIWebServicesInvoker invoker;
	private int operacionRealizar;
	private WorkManager workManager = null;
	private int timeOutMilisegundos;
	
	private String retorno;
	private String serialNumber; 
	private String OUI;
	private String productClass;
	private String fatherEmail;
	private String suscriberID;
	private String suscriberIDNew;
	private String velocidad;
	private String atiempoRequestNumber;
	private ParametrosMotiveDTO parametrosOut;
	private String passwordModemWIFI;
	private String UrlWebService;
	private String UserWebService;
	private String PasswdWebService;
	private String CertifacadeWebService;
	private String ProtocolModem;
	private String NameWifi;
	private String SecurityModem;
	private String PasswordModem;
	
	/**
	 * Constructor
	 * @param timeOutMilisegundos Inicializa el timeout de la operaci�n a realizar en el webservice
	 */
	public NBIWebServicesInvokerAsync(int timeOutMilisegundos) {
		super();
		this.timeOutMilisegundos = timeOutMilisegundos;
	}

	/**
	 * Constructor por defecto
	 * Se inicializa el timeout de las operaciones por defecto en 50001
	 */
	public NBIWebServicesInvokerAsync(){
		this.timeOutMilisegundos = 50001;
	}
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	/* (non-Javadoc)
	 * @see co.com.telefonica.acs.NBIWebServicesInvoker#configurarModem(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, co.com.telefonica.acs.ParametrosMotiveDTO, java.lang.String)
	 */
	public String configurarModem(String serialNumber, String OUI,
			String productClass, String fatherEmail, String suscriberID,
			String suscriberIDNew, String velocidad,
			String atiempoRequestNumber, ParametrosMotiveDTO parametrosOut,
			String passwordModemWIFI) {
		operacionRealizar=configurarModem;
		this.serialNumber=serialNumber;
		this.OUI=OUI;
		this.productClass=productClass;
		this.fatherEmail=fatherEmail;
		this.suscriberID=suscriberID;
		this.suscriberIDNew=suscriberIDNew;
		this.velocidad= velocidad;
		this.atiempoRequestNumber=atiempoRequestNumber;
		this.parametrosOut=parametrosOut;
		this.passwordModemWIFI=passwordModemWIFI;
		try{
			cargarWorkManager();
			NBIWebServicesInvokerAsync item= (NBIWebServicesInvokerAsync) this.clone();
			
			WorkItem workItem = workManager.schedule((Work)item);
			workManager.wait(this.timeOutMilisegundos);
			
			//WorkItem workItem =workManager.startWork((Work)item,this.timeOutMilisegundos,null);
//			ArrayList a=new ArrayList();
//			a.add(workItem);
			
			
			
			//boolean exito=workManager.join(a,WorkManager.JOIN_AND,this.timeOutMilisegundos);
			boolean exito = workItem.getStatus() < 0;
			if(exito){
				retorno = item.getRetorno();
			}else{
				log.error("Ocurrio un error con el modem serial "+serialNumber);
				retorno = "<faultstring>Ocurrio un error de time out con el WebService </faultstring>";
			}
		}catch (Exception e) {
			log.error("Ocurrio un error con el modem serial "+serialNumber,e);
			retorno = "Ocurrio de comunicacion con el WebService "+e.getMessage();
		}
		return retorno;
	}

	/**
	 * @throws NamingException
	 * 
	 */
	private void cargarWorkManager() throws NamingException {
		if (workManager == null){
			InitialContext ic = new InitialContext();
			
			workManager = (WorkManager) ic.lookup("wm/NBIWebServices");
		}
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.acs.NBIWebServicesInvoker#modificarModem(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, co.com.telefonica.acs.ParametrosMotiveDTO, java.lang.String)
	 */
	public String modificarModem(String serialNumber, String OUI,
			String productClass, String fatherEmail, String suscriberID,
			String suscriberIDNew, String velocidad,
			String atiempoRequestNumber, ParametrosMotiveDTO parametrosOut,
			String passwordModemWIFI) {
		operacionRealizar=modificarModem;
		this.serialNumber=serialNumber;
		this.OUI=OUI;
		this.productClass=productClass;
		this.fatherEmail=fatherEmail;
		this.suscriberID=suscriberID;
		this.suscriberIDNew=suscriberIDNew;
		this.velocidad= velocidad;
		this.atiempoRequestNumber=atiempoRequestNumber;
		this.parametrosOut=parametrosOut;
		this.passwordModemWIFI=passwordModemWIFI;
		try{
			cargarWorkManager();
			NBIWebServicesInvokerAsync item= (NBIWebServicesInvokerAsync) this.clone();
			
//			WorkItem workItem =workManager.startWork((Work) item,this.timeOutMilisegundos,null);
//			ArrayList a=new ArrayList();
//			a.add(workItem);
//			
//			
//			boolean exito=workManager.join(a,WorkManager.JOIN_AND,this.timeOutMilisegundos);
			
			WorkItem workItem = workManager.schedule((Work) item);
			workManager.wait(timeOutMilisegundos);
			boolean exito = workItem.getStatus() < 0;
			if(exito){
				retorno = item.getRetorno();
			}else{
				log.error("Ocurrio un error con el modem serial "+serialNumber);
				retorno = "<faultstring>Ocurrio un error de time out con el WebService </faultstring>";
			}
		}catch (Exception e) {
			log.error("Ocurrio un error con el modem serial "+serialNumber,e);
			retorno = "Ocurrio de comunicacion con el WebService "+e.getMessage();
		}
		return retorno;
//		return super.modificarModem(serialNumber, OUI, productClass,
//				fatherEmail, suscriberID, suscriberIDNew, velocidad,
//				atiempoRequestNumber, parametrosOut, passwordModemWIFI);
	}

	
	/* (non-Javadoc)
	 * @see co.com.telefonica.acs.NBIWebServicesInvoker#desconfigurarModem(java.lang.String, co.com.telefonica.acs.ParametrosMotiveDTO)
	 */
	public String desconfigurarModem(String suscriberID,
			ParametrosMotiveDTO parametrosOut) {
		operacionRealizar=desconfigurarModem;
		this.suscriberID=suscriberID;
		this.parametrosOut=parametrosOut;
		try{
			cargarWorkManager();
			NBIWebServicesInvokerAsync item= (NBIWebServicesInvokerAsync) this.clone();
			
//			WorkItem workItem =workManager.startWork((Work) item,this.timeOutMilisegundos,null);
//			ArrayList a=new ArrayList();
//			a.add(workItem);
//			boolean exito=workManager.join(a,WorkManager.JOIN_AND,this.timeOutMilisegundos);
			
			WorkItem workItem = workManager.schedule((Work) item);
			workManager.wait(timeOutMilisegundos);
			boolean exito = workItem.getStatus() < 0;
			if(exito){
				retorno = item.getRetorno();
			}else{
				log.error("Ocurrio un error con el modem serial "+serialNumber);
				retorno = "<faultstring>Ocurrio un error de time out con el WebService </faultstring>";
			}
		}catch (Exception e) {
			log.error("Ocurrio un error con el modem serial "+serialNumber,e);
			retorno = "Ocurrio de comunicacion con el WebService "+e.getMessage();
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see com.ibm.websphere.asynchbeans.Work#release()
	 */
	public void release() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		cargarInvoker();
		log.debug("Se va a realizar la opercion para la operacion"+operacionRealizar);
		
		switch(operacionRealizar){
			case 1 :
				retorno=invoker.configurarModem(serialNumber, OUI,
						productClass, fatherEmail, suscriberID,
						suscriberIDNew, velocidad,
						atiempoRequestNumber, parametrosOut,
						passwordModemWIFI);
				break;
			case 2 :
				retorno=invoker.modificarModem(serialNumber, OUI,
						productClass, fatherEmail, suscriberID,
						suscriberIDNew, velocidad,
						atiempoRequestNumber, parametrosOut,
						passwordModemWIFI);
				break;
			case 3 :
				retorno=invoker.desconfigurarModem(suscriberID, parametrosOut);
				break;
		}
		
	}
	/**
	 * 
	 */
	private void cargarInvoker() {
		if(invoker == null){
			invoker= new NBIWebServicesInvoker();
		}
		
	}

	/**
	 * @return Returns the retorno.
	 */
	public String getRetorno() {
		return retorno;
	}
	/**
	 * @param retorno The retorno to set.
	 */
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	
	@Override
	public boolean isDaemon() {
		// TODO Auto-generated method stub
		return false;
	}
}
