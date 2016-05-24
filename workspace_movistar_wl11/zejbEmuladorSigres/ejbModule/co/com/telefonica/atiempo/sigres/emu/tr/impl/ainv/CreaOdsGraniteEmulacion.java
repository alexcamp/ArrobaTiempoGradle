package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import java.util.ArrayList;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;

import co.com.telefonica.atiempo.interfaces.atiempo.TR515E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR515S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreaOdsGraniteEmulacion extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR517E entrada=null;
		   TR517S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/
    
		   entrada = (TR517E) getXmlProcessor().unmarshal(msg);
		   salida = (TR517S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_517_S.xml"));
       
		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_517_s.status");
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
				  
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			   salida.setId(entrada.getId());
			   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			   salida.setPhoneNumber(entrada.getPhoneNumber());
			   /* Se setea error false en la respuesta*/
			   salida.setErrorCode(0);
			   int intOds = 0;
				try {
					DBManager dbSeq = new DBManager();
					dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
					Integer ods = new Integer(dbSeq
							.seqNextValInteger("CORRELATIVO_MENSAJE"));
					intOds = Integer.parseInt(ods.toString());
				} catch (Exception e) {
					log.debug("No se pudo asignar la ODS ", e);
				}
				
			   salida.setOdsNumber(new Integer (intOds));
			   salida.setOperationType(entrada.getOperationType());
		   } else {
		   		salida.setId(entrada.getId());
			   salida.setErrorCode(1);
			   salida.setErrorMessage("Error actualizando inventario.");
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
		   CreaODSGranite_Respuesta r = new CreaODSGranite_Respuesta(responseMessage);
		   respuestas.add(r);
		   return respuestas;
	}

}
