package co.com.telefonica.acs;

import java.rmi.RemoteException;

import javax.xml.rpc.soap.SOAPFaultException;

import org.apache.log4j.Logger;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import weblogic.webservice.context.WebServiceContext;
import weblogic.webservice.core.handler.WSSEClientHandler;
import weblogic.xml.security.UserInfo;
import weblogic.xml.security.wsse.Security;
import weblogic.xml.security.wsse.SecurityElementFactory;

import com.alcatel.hdm.service.nbi.client.NBIService;
import com.alcatel.hdm.service.nbi.client.NBIServicePort;
import com.alcatel.hdm.service.nbi.client.NBIService_Impl;
import com.alcatel.hdm.service.nbi.dto.NBIDeviceCreationData;
import com.alcatel.hdm.service.nbi.dto.NBIDeviceData;
import com.alcatel.hdm.service.nbi.dto.NBIDeviceID;
import com.alcatel.hdm.service.nbi.dto.NBIDeviceUpdateData;
import com.alcatel.hdm.service.nbi.dto.NBIDynamicVariable;





/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NBIWebServicesInvoker {
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	
	 public static void main(String args[]){
	 	
	 		NBIWebServicesInvoker invocador = new NBIWebServicesInvoker();
	 		String retorno = invocador.configurarModem("1541487", "000AC3", "HG110", "Marzo12", "15999999", "0", "4096", "88282898", null, "00980722345");
	 		System.out.println("retorno : " + retorno);
	 		
	 	  // TODO Auto-generated method stub
	 	  //String retorno = NBIWebServicesInvoker.configurarModem("1541487", "000AC3", "HG110", "Marzo12", "15999999", "0", "4096", "88282898", null);
	 	  //System.out.println("retorno : " + retorno);
	 	 }
	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		NBIServicePort nbiService;
		long timeout=20000;
		
		try {
			ParametrosMotiveDTO parametros = obtenerDatosParametricos();
			ModemDTO modemDatos = obtenerDatosModem();

			nbiService = obtenerServicio(parametros);
			
			if (modemDatos.getOperacion().equals("Alta")){
				configurarModem(nbiService, parametros, modemDatos);
			}else if (modemDatos.getOperacion().equals("Baja")){
				desconfigurarModem(nbiService, modemDatos);
			}else{
				//modificarModem(nbiService, parametros, modemDatos);
			}
	        
		} catch (AtiempoACSException e) {
			System.out.println(e.getMessage());
		}
	}*/
	
	


	public ParametrosMotiveDTO obtenerDatosParametricos(String UrlWebService, String UserWebService, String PasswdWebService, 
			String CertifacadeWebService, String ProtocolModem, String NameWifi, String SecurityModem, String PasswordModem ){
		ParametrosMotiveDTO parametros = null ;
		try{
			
			parametros = new ParametrosMotiveDTO();
			
			
						
			parametros.setUrlWebService(UrlWebService);
			parametros.setUserWebService(UserWebService);
			parametros.setPasswdWebService(PasswdWebService);
			parametros.setCertifacadeWebService(CertifacadeWebService);
			parametros.setProtocolModem(ProtocolModem);
			parametros.setNameWifi(NameWifi);
			parametros.setSecurityModem(SecurityModem);
			parametros.setPasswordModem(PasswordModem);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return parametros;
	}
	
	
	
	
	public NBIServicePort obtenerServicio(ParametrosMotiveDTO parametros) {
		log.debug("Entro a obtener servicio");
		NBIServicePort nbiService = null;
		try {
			// Setup the global JAXM message factory
			System.setProperty("javax.xml.soap.MessageFactory","weblogic.webservice.core.soap.MessageFactoryImpl");
			// Setup the global JAX-RPC service factory
			System.setProperty("javax.xml.rpc.ServiceFactory","weblogic.webservice.core.rpc.ServiceFactoryImpl");
			// Set name of file containing the PEM-encoded certificates and
			// the ca certificate file name for SSL
			System.setProperty("weblogic.webservice.client.ssl.trustedcertfile",parametros.getCertifacadeWebService());
			log.debug("Paso propiedades en obtenerServicio");
			
			NBIService nbiWService = new NBIService_Impl(parametros.getUrlWebService());
			UserInfo ui = new UserInfo(parametros.getUserWebService(), parametros.getPasswdWebService());

			WebServiceContext context = nbiWService.context();
			// Add the username/password to the header as a UsernameToken
			context.getSession().setAttribute(WSSEClientHandler.REQUEST_USERINFO, ui);

			// Add the security element to the request...
			SecurityElementFactory factory = SecurityElementFactory.getDefaultFactory();

			Security security = factory.createSecurity(null);
			security.addToken(ui);

			nbiService = nbiWService.getNBIServicePort();
	        context.getSession().setAttribute(WSSEClientHandler.REQUEST_SECURITY, security);
	        
	        
	        System.out.println("Connected to...." + nbiWService.getWSDLDocumentLocation());
	        log.debug("Connected to...." + nbiWService.getWSDLDocumentLocation());
		} catch (RemoteException e) {
			SOAPFaultException soap = (SOAPFaultException) e.getCause();
			System.out.println("Genero Error de SOAP FaultCode : "+ soap.getDetail().getAttribute("faultcode")  + " FaultString : " +soap.getFaultString());
			log.debug("Genero Error de SOAP FaultCode : "+ soap.getDetail().getAttribute("faultcode")  + " FaultString : " +soap.getFaultString());
			e.printStackTrace();
		} catch (Exception exc) {
			System.out.println("Genero Error Inesperado : "+ exc.getMessage());
			log.debug("Genero Error Inesperado : "+ exc.getMessage());
			exc.printStackTrace();
		}
		return nbiService;
	}

	/*public static ModemDTO obtenerDatosModem(TR135E tr135e) throws AtiempoACSException{
		ModemDTO modem ;
		try{
			modem = new ModemDTO();
			modem.setSerialNumber(tr135e.getSerialNumber());
			modem.setOUI("000AC2");
			modem.setProductClass(tr135e.getProductServiceCode().toString());
			modem.setFatherEmail(tr135e.getFatherEmail());
			modem.setSuscriberID(tr135e.getPhoneNumber().toString());
			modem.setSuscriberIDNew(tr135e.getNewPhoneNumber().toString());
			modem.setVelocidad("4096");
			modem.setIdAtiempo(tr135e.getAtiempoRequestNumber());
			
			if (tr135e.getProductServiceCode().intValue() == ComunInterfaces.operacionParActivar){
				modem.setOperacion("Alta");
			}else if (tr135e.getProductServiceCode().intValue() == ComunInterfaces.operacionParDesactivar){
				modem.setOperacion("Baja");
			}else  if (tr135e.getProductServiceCode().intValue() == ComunInterfaces.altaMigracionPS){
				modem.setOperacion("Modificar");
			}		
		}catch (Exception e){
			throw new AtiempoACSException ("BRK:Genero un Error Obteniendo los Objetos del Modem");
		}
		return modem;
	}*/

	public String configurarModem(String serialNumber,String OUI,String productClass,String fatherEmail,String suscriberID,String suscriberIDNew,
			String velocidad,String atiempoRequestNumber, ParametrosMotiveDTO parametrosOut, String passwordModemWIFI){
		log.debug("Entro a configurar modems con la siguiente información: serial:"+serialNumber+" , OUI:"+OUI+" ,product Class:"+productClass+
					" ,father Email:"+fatherEmail+" ,suscriberID:"+suscriberID+" , suscriber ID New:"+suscriberIDNew+", velocidad:"+velocidad+
					" ,atiempo Request Number: "+atiempoRequestNumber+", password Modem WIFI: "+passwordModemWIFI);
		String retorno = "";
		try{
			ParametrosMotiveDTO parametros = parametrosOut;
			NBIServicePort nbiService = obtenerServicio(parametros);
			log.debug("Se obtiene conexion del servicio web configurarModem...");
			
			String variableSSID = null;
			String variableSSIDPassword = null;
			
			NBIDeviceID DevID = new NBIDeviceID(); 
	        DevID.setOUI( OUI); 
	        DevID.setProductClass(productClass ); 
	        DevID.setProtocol(parametros.getProtocolModem() ); 
	        DevID.setSerialNumber(serialNumber); 
	        NBIDeviceCreationData cpe = new NBIDeviceCreationData(); 
	        cpe.setDeviceId(DevID); 
	        
	        
	        
	        if ((suscriberIDNew != null &&  suscriberIDNew.equals("0")) || suscriberIDNew == null){
		        cpe.setSubscriberID(suscriberID);
	        	variableSSID = parametros.getNameWifi() + suscriberID;
	        	//variableSSIDPassword = suscriberID;
	        	variableSSIDPassword = passwordModemWIFI;
	        }else{
		        cpe.setSubscriberID(suscriberIDNew);
	        	variableSSID = parametros.getNameWifi() + suscriberIDNew;
	        	//variableSSIDPassword = suscriberIDNew;
	        	variableSSIDPassword = passwordModemWIFI;
	        }
	        //cpe.setHTTPPublicUsername(modemDatos.getFatherEmail());
	        //cpe.setHTTPPublicUsername(fatherEmail);
	        //cpe.setHTTPPublicPassword(parametros.getPasswordModem());
	        Long GUID = new Long(nbiService.createDevice(cpe, false ).toString());
	        
	        //Long GUID = (Long) nbiService.createDevice(cpe, false );
	        
	        log.debug("Se pasaron datos para el servicio web configurarModem...");
	        
	    	NBIDynamicVariable[] dynamicVariables = new NBIDynamicVariable[7];
	        NBIDynamicVariable dv = new NBIDynamicVariable();
	        dv.setName("Velocidad");dv.setValue(velocidad);
	        dynamicVariables[0]=dv;
	        dv = new NBIDynamicVariable();dv.setName("ID");dv.setValue(atiempoRequestNumber);
	        dynamicVariables[1]=dv;
	        dv = new NBIDynamicVariable();dv.setName("PPPoEUser");dv.setValue(fatherEmail);
	        dynamicVariables[2]=dv;
	        dv = new NBIDynamicVariable();dv.setName("PPPoEPassword");dv.setValue(parametros.getPasswordModem());
	        dynamicVariables[3]=dv;
	        dv = new NBIDynamicVariable();dv.setName("SSID");dv.setValue(variableSSID);
	        dynamicVariables[4]=dv;
	        dv = new NBIDynamicVariable();dv.setName("SSIDPassword");dv.setValue(variableSSIDPassword);
	        dynamicVariables[5]=dv;
	        dv = new NBIDynamicVariable();dv.setName("SecurityLevel");dv.setValue(parametros.getSecurityModem());
	        dynamicVariables[6]=dv;
		    nbiService.updateDynamicVariablesByDeviceGUID(GUID, dynamicVariables);
		    log.debug("Termino con exito el proceso del servicio web configurarModem...");
		} catch (RemoteException e) {
			retorno = e.getMessage();	
			log.debug("Entro a excepción RemoteException en el método configurarModem:");
			e.printStackTrace();
		} catch (Exception e) {
			retorno = e.getMessage();
			log.debug("Entro a excepción Exception en el método configurarModem:");
			e.printStackTrace();
		}
		return retorno;
	}

	public String desconfigurarModem(String suscriberID, ParametrosMotiveDTO parametrosOut) {
		log.debug("Entro al método desconfigurarModem, con el suscriberID:"+suscriberID);
		String retorno = new String();
		boolean encontroComponentes = true;
		Long GUID  = new Long("0");
		try{
			ParametrosMotiveDTO parametros = parametrosOut;
			NBIServicePort nbiService = obtenerServicio(parametros);
			log.debug("Obtuvo conexion para el web service desconfigurarModem...");
			
			String variableSSID = null;
			String variableSSIDPassword = null;
			
			try{
				NBIDeviceData[] cpes = nbiService.findDevicesBySubscriberId(suscriberID);
		        GUID = cpes[0].getDeviceGUID();
		        
				log.debug("La respuesta de la eliminación es: GUID"+GUID+" ,Serial:"+cpes[0].getDeviceId().getSerialNumber()+
						" , OUI:"+cpes[0].getDeviceId().getOUI()+" ,suscriber ID:"+cpes[0].getSubscriberID());
			}catch (RemoteException e) {
				log.debug("Entro a excepción RemoteException para buscar el susriber ID, se setea como OK" +
						" por favor revice si se encuentra registrado en el legado:"+e);
				encontroComponentes = false;
			}
			 
	        if (encontroComponentes){
	        	nbiService.removeDeviceByGUID(GUID);
		        log.debug("Terminó el proceso del web service desconfigurarModem");
	        }
	        
	        
		} catch (RemoteException e) {
			retorno = e.getMessage();			
			log.debug("Entro a excepción RemoteException en el método desconfigurarModem:"+e);
			e.printStackTrace();
		} catch (Exception e) {
			retorno = e.getMessage();
			log.debug("Entro a excepción Exception en el método desconfigurarModem:"+e);
			e.printStackTrace();
		}
		return retorno;
	}

	public String modificarModem(String serialNumber,String OUI,String productClass,String fatherEmail,String suscriberID,
			String suscriberIDNew,String velocidad,String atiempoRequestNumber, ParametrosMotiveDTO parametrosOut, String passwordModemWIFI){
			log.debug("Entro al método modificarModem, con serial:"+serialNumber+" , OUI:"+OUI+" ,product Class:"+productClass+
					" ,father Email:"+fatherEmail+" ,suscriberID:"+suscriberID+" , suscriber ID New:"+suscriberIDNew+", velocidad:"+velocidad+
					" ,atiempo Request Number: "+atiempoRequestNumber+", password Modem WIFI:"+passwordModemWIFI);
			String retorno = "";
			boolean encontroComponentes = true;
			Long GUID  = new Long("0");
			NBIDeviceData[] cpes = null;
			
			try{
				ParametrosMotiveDTO parametros = parametrosOut;
				NBIServicePort nbiService = obtenerServicio(parametros);
				log.debug("Se obtuvieron datos para el web service modificarModem...");
				String variableSSID = null;
				String variableSSIDPassword = null;
				
				try{
					cpes = nbiService.findDevicesBySubscriberId(suscriberID);
			        GUID = cpes[0].getDeviceGUID();
				}catch (RemoteException e) {	
					log.debug("Entro a excepción RemoteException para buscar el susriber ID, se envìa a crear como nuevo:"+e);
					configurarModem(serialNumber,OUI,productClass,fatherEmail,suscriberID,suscriberIDNew,velocidad,atiempoRequestNumber,parametrosOut,passwordModemWIFI);
					encontroComponentes = false;
				}
				
				if (encontroComponentes){
			        if (cpes[0].getDeviceId().getSerialNumber().equals(serialNumber)){
				        NBIDeviceUpdateData data = new NBIDeviceUpdateData();
				        
				        if ((suscriberIDNew != null &&  suscriberIDNew.equals("0")) || suscriberIDNew == null){
				        	data.setSubscriberID(suscriberID);
				        	variableSSID = parametros.getNameWifi() + suscriberID;
				        	//variableSSIDPassword = suscriberID;
				        	variableSSIDPassword = passwordModemWIFI;
				        }else{
				        	data.setSubscriberID(suscriberIDNew);
				        	variableSSID = parametros.getNameWifi() + suscriberIDNew;
				        	//variableSSIDPassword = suscriberIDNew;
				        	variableSSIDPassword = passwordModemWIFI;
				        }
				    	nbiService.updateDeviceByGUID(GUID, data);
				    	
						log.debug("Se realizo actualizacion de componentes del web service configurarModem...");

				    	NBIDynamicVariable[] dynamicVariables = new NBIDynamicVariable[7];
				        NBIDynamicVariable dv = new NBIDynamicVariable();
				        dv.setName("Velocidad");
				        dv.setValue(velocidad);
				        dynamicVariables[0]=dv;
				        dv = new NBIDynamicVariable();dv.setName("ID");
				        dv.setValue(atiempoRequestNumber);
				        dynamicVariables[1]=dv;
				        dv = new NBIDynamicVariable();
				        dv.setName("PPPoEUser");
				        dv.setValue(fatherEmail);
				        dynamicVariables[2]=dv;
				        dv = new NBIDynamicVariable();
				        dv.setName("PPPoEPassword");
				        dv.setValue(parametros.getPasswordModem());
				        dynamicVariables[3]=dv;
				        dv = new NBIDynamicVariable();
				        dv.setName("SSID");
				        dv.setValue(variableSSID);
				        dynamicVariables[4]=dv;
				        dv = new NBIDynamicVariable();
				        dv.setName("SSIDPassword");
				        dv.setValue(variableSSIDPassword);
				        dynamicVariables[5]=dv;
				        dv = new NBIDynamicVariable();
				        dv.setName("SecurityLevel");
				        dv.setValue(parametros.getSecurityModem());
				        dynamicVariables[6]=dv;
					    nbiService.updateDynamicVariablesByDeviceGUID(GUID, dynamicVariables);		      
						log.debug("Termino el proceso para el web service modificarModem...");
			        }else{
						log.debug("El web service modificarModem detectó que el equipo a asignar es diferente al registrado...");
				        nbiService.removeDeviceByGUID(GUID);
				        configurarModem(serialNumber,OUI,productClass,fatherEmail,suscriberID,suscriberIDNew,velocidad,atiempoRequestNumber, parametrosOut, passwordModemWIFI);
			        }
				}
			} catch (RemoteException e) {				
				retorno = e.getMessage();
				log.debug("Entro a excepción RemoteException en el método modificarModem:"+e);
				e.printStackTrace();
			} catch (Exception e) {
				retorno = e.getMessage();
				log.debug("Entro a excepción Exception en el método modificarModem:"+e);
				e.printStackTrace();
			}
			return retorno;
		}
}
	
