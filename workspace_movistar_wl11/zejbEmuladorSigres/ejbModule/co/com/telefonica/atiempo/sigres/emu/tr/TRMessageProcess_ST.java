package co.com.telefonica.atiempo.sigres.emu.tr;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.sigres.emu.dao.DaoService;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;
import co.com.telefonica.atiempo.sigres.emu.util.XMLProcessor;



/**
 * TRMessage
 * 
 * Esta es la clase abstracta que representa el proceso 
 * emulado para cierta TR la cual se debe definir en las
 * distintas implementaciones.
 * 
 * @author Gonzalo Arreche
 */
public abstract class TRMessageProcess_ST {
	protected Logger log = Logger.getLogger(getClass());
    /** Lista de mensajes de respuesta*/
    protected List respuestas;
    /** Instancia del servicio de acceso a la base de datos*/
    protected DaoService daoService;
    /** JNDI del MDB que recibira las respuestas*/
    private String MDBJndiName;
    /** Properties que define el comportamiento de cada TR */
    private Properties trProperties = ServiceLocatorEmulator_ST.getTRProperties();
    /** El procesador de XML */
    private XMLProcessor xmlProcessor ;
    /** Este es el método que emula la respuesta de cada TR*/
    public abstract List emulateResponse(String msg);
    /**
     * Accesores y modificadores.
     */
    public XMLProcessor getXmlProcessor() {
        if(xmlProcessor==null)xmlProcessor = new XMLProcessor();
        return xmlProcessor;
    }
    public void setXmlProcessor(XMLProcessor xml) {
        this.xmlProcessor = xml;
    }
    public String getMDBJndiName() {
        return MDBJndiName;
    }
    public Properties getTrProperties() {
        return trProperties;
    }
    public DaoService getDaoService() {
        return daoService;
    }
    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }
           
}
