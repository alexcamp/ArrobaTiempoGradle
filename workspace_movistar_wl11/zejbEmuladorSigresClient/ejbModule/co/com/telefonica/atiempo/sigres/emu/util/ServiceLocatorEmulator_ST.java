package co.com.telefonica.atiempo.sigres.emu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.omg.CosNaming.IstringHelper;
import co.com.telefonica.atiempo.sigres.emu.Receptor_ST;
import co.com.telefonica.atiempo.sigres.emu.ReceptorHome_ST;
import co.com.telefonica.atiempo.sigres.emu.ReceptorLocal_ST;
import co.com.telefonica.atiempo.sigres.emu.ReceptorLocalHome_ST;

/**
 * ServiceLocatorEmulator
 * 
 * @author Gonzalo Arreche
 *
 */
public class ServiceLocatorEmulator_ST {
	
	private static Logger log = Logger.getLogger(ServiceLocatorEmulator_ST.class);
	/** Constantes de clase*/
	private static final String TRS_NO_EMULADAS_PROP = "tr_noemuladas";
	private static final String EMU_PROPS = "/home/atiemweb/etc/emu.properties";
	private static final String BEHAVIOUR_PROPS = "/home/atiemweb/etc/tr_behaviour.properties";
	private static final String EMU_ACT = "emulador.activado";
	
	/** Contexto para realizar el lookup*/
	private static Context context;
	
    /** Guarda el properties que define los comportamientos emulados*/
    private static Properties trProperties;
    
	/** Properties de la aplicacion */
	static private Properties emuProps;
	
	private static Boolean activated;
	
    /** 
     * Devuelve un InputStream con el nombre especificado
     */
	public static InputStream getRecurso(String path){
		return ServiceLocatorEmulator_ST.class.getResourceAsStream("/resources/"+path);	
	}
	
	/**
	 * Acceso al ReceptorBean
	 * 
	 * @param context
	 * @return
	 * @throws NamingException
	 * @throws CreateException
	 * @throws RemoteException
	 */
    public static Receptor_ST getReceptor(InitialContext context) throws NamingException, CreateException, RemoteException {
        ReceptorHome_ST receptor = (ReceptorHome_ST) context.lookup("ejb/co/com/telefonica/atiempo/sigres/emu/ReceptorHome_ST");
        Receptor_ST r = receptor.create();
        return r;
    }
	/**
	 * Acceso al ReceptorBean
	 * 
	 * @param context
	 * @return
	 * @throws NamingException
	 * @throws CreateException
	 * @throws RemoteException
	 */
	public static ReceptorLocal_ST getReceptorLocal(InitialContext context)
		throws NamingException, CreateException, RemoteException {
		ReceptorLocalHome_ST receptor =
			(ReceptorLocalHome_ST) context.lookup(
				"ejb/co/com/telefonica/atiempo/sigres/emu/ReceptorHomeLocal_ST");
		ReceptorLocal_ST r = receptor.create();
		return r;
	}
    /**
     * Devuelve el properties.
     * 
     * @return
     */
	 public static Properties getTRProperties(){
        if(trProperties == null){
            trProperties = new Properties();
            try {
            	log.debug("Inicio propertie tr_behaviour.properties");
            	trProperties = new Properties();
            	File file = new File(BEHAVIOUR_PROPS);
            	if(file.exists()&& file.canRead()){
            		FileInputStream fis = new FileInputStream(file);
            		trProperties.load(fis);
            	}else{
            		log.debug("The properties file '"+BEHAVIOUR_PROPS+"' does not exist or canot be readed");
            	}
               // trProperties.load(getRecurso(BEHAVIOUR_PROPS));
            } catch (IOException e) {
                log.debug("An error has ocurred when loading tr properties", e);
            }
        }
        return trProperties;
    }
    /**
     * getContext
     * 
     * Devuelve la instancia del contexto.
     * 
     * @return el contexto.
     */    
    private static Context getContext(){
    		if(context == null)
				try {
					context = new InitialContext();
				} catch (NamingException e) {
					e.printStackTrace();
				}
    		return context;
    }
    /**
     * getDataSource
     * 
     * Devuelve el dataSource
     * 
     * @param dataSourceName
     * @return
     */
    public static DataSource getDataSource(String dataSourceName){
        DataSource ds=null;
        try {
            ds = (DataSource)context.lookup("java:comp/env/"+dataSourceName);
        } catch (NamingException e) {
            log.error("Error obteniendo el DataSource " +dataSourceName+ ".El mensaje es: " + e.getMessage());
        }
        return ds;
    }
    /**
     * getEmuProps
     * 
     * Devuelve el properties general del emulador.
     * 
     * @return
     */
    public static Properties getEmuProps() {
		try {
			if (emuProps == null) {
				log.debug("Inicio propertie emu.properties");
				emuProps = new Properties();
				File file = new File(EMU_PROPS);
				if(file.exists() && file.canRead()){
					FileInputStream fis = new FileInputStream(file);
					emuProps.load(fis);	
				}else{
					log.debug("The properties file '"+EMU_PROPS+"' does not exist or canot be readed");
				}
			}	
		} catch (IOException e) {
			log.debug("An error has ocurred when loading emu properties", e);
		}
		return emuProps;
	}
	/**
	 * getTRNoEmuladas
	 * 
	 * Devuelve la lista de TR que no deben ser emuladas, estas 
	 * se deben especificar en el archivo emu.properties bajo la 
	 * property tr_noemuladas separadas por coma (,).
	 * 
	 * @return
	 */
	private static List getTRNoEmuladas(){
		List tr_emuladas = new ArrayList();
		String trs = getEmuProps().getProperty(TRS_NO_EMULADAS_PROP);
		StringTokenizer st = new StringTokenizer(trs, ",");
		while(st.hasMoreTokens()){
			tr_emuladas.add(st.nextToken());
		}
		return tr_emuladas;
	}
	/**
	 * isTREmulada
	 * 
	 * Devuelve true si la TR debe emularse.
	 * 
	 * @param tr es la TR a verificar.
	 * @return
	 */
	public static boolean isTREmulada(String tr){
		try {
			String tipo_tr = new XMLProcessor().getMessageType(tr);
			String trs = getEmuProps().getProperty(TRS_NO_EMULADAS_PROP);
			if("all".equalsIgnoreCase(trs)){
				return true;
			}else{
				log.info("Verificando si la TR esta siendo emulada...");
				log.info("trs:" +getTRNoEmuladas());
				return !getTRNoEmuladas().contains(tipo_tr);
			}
		} catch (RuntimeException e) {
			return false;
		}
	}
	/**
	 * emuladorActivado
	 * 
	 * Devuelve true si el emulador esta activo.
	 * @return
	 */
//	AT-1868 - Se modificó para que no muestre en el log cada vez q se consulta el estado de los emuladores.
	public static boolean emuladorActivado(){
		if(activated == null){//primera vez
			try {
				String propActivo = getEmuProps().getProperty(EMU_ACT);
				activated = new Boolean(propActivo);
				log.info("Emulador Activado: " + activated);
			} catch (Exception e) {
				return false;
			}
		}
		return activated.booleanValue(); 
	}
}
