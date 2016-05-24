package co.com.telefonica.atiempo.sigres.emu.process;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.sigres.emu.ClienteEmulador_ST;
import co.com.telefonica.atiempo.sigres.emu.ServiceLocatorClienteEmulador_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.NoMessageDefFoundException;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcessFactory_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;



/**
 * Despachador
 * 
 * Esta clase se encarga de despachar los mensajes que 
 * delega el <code>ReceptorBean</code>.
 *  
 * @author Gonzalo Arreche
 *
 */
public class Despachador_ST implements Runnable{
	
	private Logger log = Logger.getLogger(getClass());
	
    /** 
     * Es el nombre de la propertie que contiene el tiempo de espera 
     * entre mensajes sucesivos
     */
    private final String MESSAGE_INTERVAL = "MessageInterval";
    
	/** XMLProcessor ejecuta las actividades relacionadas al manejo de XML*/
	private XMLProcessor_ST xml;
		
	/** Variable para almacenar el mensaje que procesa */
	private String mensaje ;
	/**
	 * Constructor de la clase.
	 * 
	 * @param mensaje es el mensaje que derive del <code>ReceptorBean</code>.
	 */
	public Despachador_ST(String mensaje){
	    this.mensaje = mensaje;
	    this.xml = new XMLProcessor_ST();
	  
	}
	/**
	 * despacharMensaje
	 * 
	 * Procesa los mensajes entrantes dependiendo del tipo de TR
	 * @param xmlmsg es el mensaje XML.
	 */
	public void despacharMensaje(){
	
		TRMessageProcess_ST actual_tr;
		ArrayList respuestas = new ArrayList();
        try {
            String type = xml.getMessageType(mensaje);                    
            actual_tr = TRMessageProcessFactory_ST.getTRMessage(type);
            log.debug("JNDI " + actual_tr.getMDBJndiName());
          	log.debug(mensaje);
            respuestas = (ArrayList)actual_tr.emulateResponse(mensaje);	
            Iterator it = respuestas.iterator();
            while (it.hasNext()){
                TRResponse_ST r = (TRResponse_ST)it.next();
                log.info(r);
                long interval = Long.parseLong(ServiceLocatorEmulator_ST.getEmuProps().getProperty(MESSAGE_INTERVAL));
                log.debug("Emulator is wating " + interval + " ms to send the response");
				Thread.sleep(interval);//1 segundo
                enviarMensaje(r.getMessage(),r.getServiceClassName());           
                               
                /*
                 * Si hay mas mensajes espera un tiempo para enviar el siguiente.
                 */
                if(it.hasNext()){
                    log.debug("Emulator is wating " + interval + " ms to send a second response");
    				Thread.sleep(interval);//1 segundo
                }
            }
        } catch (NoMessageDefFoundException e) {
           log.error(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Implementacion del metodo de la interfaz Runnable
	 */
    public void run() {
       despacharMensaje();
    }
	
	
	void enviarMensaje(String mensaje, String serviceClassName) {
    	
		InitialContext context = null;
		ClienteEmulador_ST clienteEmulador =null;        
		try {
			context = new InitialContext();
			clienteEmulador = ServiceLocatorClienteEmulador_ST.getClienteEmulador(context);
			clienteEmulador.recibirMensaje(mensaje,serviceClassName);
        } catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
	}
}
