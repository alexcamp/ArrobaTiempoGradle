/*
 * Created on 07-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva;

import java.io.StringReader;
import java.io.StringWriter;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.IParserMSGService;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.castor.ACCION_MASIVA;

//import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaMSGService implements IParserMSGService{ 
//	protected transient Logger log = LoggerFactory.getLogger(getClass());
	private	ACCION_MASIVA accion_masiva = null;
	
	public Object unmarshall(String xmlEntrada) throws ATiempoAppEx {
		//ACCION_MASIVAFactory aMF=null;
		AccionMasivaMSGDTO aMDTO= new AccionMasivaMSGDTO();
		
		try{
			/*aMF = new ACCION_MASIVAFactory();
		
			aMF.setPackageName("co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva");
			aM = (ACCION_MASIVA) aMF.loadDocument("ACCION_MASIVA",new InputSource(new StringReader(xmlEntrada)));*/
			accion_masiva = ACCION_MASIVA.unmarshal(new StringReader(xmlEntrada));
		
			aMDTO.setCodigoAccion(new Integer(accion_masiva.getCODIGO_ACCION()));
			aMDTO.setNumeroPeticion(new Long(accion_masiva.getNUMERO_PETICION()));
			aMDTO.setCodigoActividad(accion_masiva.getCODIGO_ACTIVIDAD());
			aMDTO.setInstanciaActividad(accion_masiva.getINSTANCIA_ACTIVIDAD());
		
			return aMDTO;
		}
		catch (Exception e)
		{
//			log.debug("Error en el Parseo del XML",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.UNMARSHALL);
		}
		
	}
	
	public String marshall(Object msgDTO) throws ATiempoAppEx{
		//ACCION_MASIVAFactory aMF=null;
		String xmlSalida="";
		try
		{
			/*aMF = new ACCION_MASIVAFactory();
			aMF.setPackageName("co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva");
	
			// include schemaLocation hint for validation
			//iMRMFactory.setXSDFileName("SolCita.xsd");
	
			// encoding for output document
			aMF.setEncoding("Cp1252");
	
			// encoding tag for xml declaration
			aMF.setEncodingTag("ISO-8859-1");
	
			// Create the root element in the document using the specified root element name
			aM = (ACCION_MASIVA) aMF.createRoot("ACCION_MASIVA");*/
			
			AccionMasivaMSGDTO aMDTO = (AccionMasivaMSGDTO) msgDTO;
			accion_masiva=new ACCION_MASIVA();
			createACCION_MASIVA(aMDTO);
	
			StringWriter salida =new StringWriter();
			accion_masiva.marshal(salida);
			xmlSalida=salida.toString();
			return xmlSalida;
		}
		catch (Exception e)
		{
//			log.debug("Error en la Generacion del XML",e);
			throw new ATiempoAppEx(e.getMessage(),ATiempoAppEx.MARSHALL);
		}
	}

	private void createACCION_MASIVA(AccionMasivaMSGDTO aMDTO)
	{
		accion_masiva.setCODIGO_ACCION(aMDTO.getCodigoAccion().toString());
		accion_masiva.setNUMERO_PETICION(aMDTO.getNumeroPeticion().toString());
		accion_masiva.setCODIGO_ACTIVIDAD(aMDTO.getCodigoActividad());
		accion_masiva.setINSTANCIA_ACTIVIDAD(aMDTO.getInstanciaActividad());
	}
  
}
