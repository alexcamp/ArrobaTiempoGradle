package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;

import java.util.ArrayList;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;


import co.com.telefonica.atiempo.interfaces.atiempo.TR510E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;

import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AsignarRecursoSTBGranite extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR510E entrada = null;
		TR510S salida = null;
		TR510S asigManual = null;
		TR510S gestRecu = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		entrada = (TR510E) getXmlProcessor().unmarshal(msg);
		salida = (TR510S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_510_S.xml"));
		asigManual = (TR510S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_510_S.xml"));
		gestRecu = (TR510S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_510_S.xml"));
		
		// La propiedad origenYDestinoIguales tiene sentido en el caso de traslado. 
		// En ella indico si el nro origen y el destino coinciden o no
		if (getTrProperties().getProperty("tr_510_s.origenYDestinoIguales").equals("true")) {
			salida.setPreviousPhoneNumber(salida.getPhoneNumber());
			asigManual.setPreviousPhoneNumber(salida.getPhoneNumber());
			gestRecu.setPreviousPhoneNumber(salida.getPhoneNumber());
		}
		
		// En cualquiera de los 3 casos seteo el indicador de que la central soporta conf automatica.
		salida.setEocEnable(false);
		asigManual.setEocEnable(false);
		gestRecu.setEocEnable(false);
		if (getTrProperties().getProperty("tr_510_s.soporta_conf_automatica").equals("true")) {
			salida.setEocEnable(true);
			asigManual.setEocEnable(true);
			gestRecu.setEocEnable(true);
		}
		
		log.debug("(EN EMULADORES) SOPORTA CONF AUTOMATICA: " + getTrProperties().getProperty("tr_510_s.soporta_conf_automatica"));
		
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_510_s.status");
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		salida.setId(entrada.getId());
		salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber().longValue());
		if (resultado.equals("ok")) {
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
			
			
		//	salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			
			salida.setErrorCode(0);
			String recuSTB = getTrProperties().getProperty("tr_510_s.recuSTB");			
			
			if (!recuSTB.equals("ok")){
				//Si quiero que falle la asigancion automatica
				salida.setReserveResult(false);
			}else{
				//Si no quiero que falle la asignacion automatica
				salida.setReserveResult(true);
			}
			salida.setOdsNumber(intOds);
			String responseMessage = getXmlProcessor().marshal(salida);
			ARecuSTBGranite_Respuesta r = new ARecuSTBGranite_Respuesta(responseMessage);
			respuestas.add(r);
			
			/* Entra en caso de que la Asignacion de Recursos Automatica falle*/
			if (!recuSTB.equals("ok")) {
				String aManual = getTrProperties().getProperty("tr_510_s.aManual");
				String aManualPGI = getTrProperties().getProperty("tr_510_s.aManualPGI");
				String derivarGrecu = getTrProperties().getProperty("tr_510_s.grecu");
				/*Entra si la asignacion manual se realiza correctamente*/
				if(aManual.equals("ok")){
					
						log.debug("Realizo Asignacion Manual correctamente");
						asigManual.setId(entrada.getId());
						asigManual.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber().longValue());
						asigManual.setErrorCode(0);
						asigManual.setReserveResult(false);
						//Si no derivo a PGI
						if(!aManualPGI.equals("true")){
							asigManual.setResult(1);
						}else{
							log.debug("Emulando quiebre a PGI");
							asigManual.setResult(3);
							asigManual.setCna(611);
						}						
						asigManual.setOdsNumber(intOds);
						String responseAsigManual = getXmlProcessor().marshal(asigManual);
						AsignaRecuManual_Respuesta respAManual = new AsignaRecuManual_Respuesta(responseAsigManual);
						respuestas.add(respAManual);
				}else{					
					log.debug("Derivando a Gestion de Recursos ...");
					asigManual.setId(entrada.getId());
					asigManual.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber().longValue());
					asigManual.setErrorCode(1);
					asigManual.setReserveResult(false);
					asigManual.setResult(2);
					asigManual.setOdsNumber(intOds);
					//Ingresa si quiero que Gestion de recursos se haga correctamente
					if (derivarGrecu.equals("ok")) {
						String derivarGrecuPGI = getTrProperties().getProperty("tr_510_s.grecuPGI");						
						log.debug("Realizo Gestion de Recursos correctamente");
						gestRecu.setId(entrada.getId());
						gestRecu.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber().longValue());
						gestRecu.setErrorCode(0);
						gestRecu.setReserveResult(false);
						//Si no quiero q derive a PGI
						if(derivarGrecuPGI.equals("true")){							
							gestRecu.setResult(1);
						}else{
							log.debug("Emulando quiebre a PGI");
							gestRecu.setResult(3);
							gestRecu.setCna(611);
						}
						
						gestRecu.setOdsNumber(intOds);						
					}//Ingresa si quiero que Gestion de recursos no se haga correctamente 
					else if(!derivarGrecu.equals("ok")){
						log.debug("Realizo Gestion de Recursos incorrectamente");
						gestRecu.setId(entrada.getId());
						gestRecu.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber().longValue());
						gestRecu.setErrorCode(1);
						gestRecu.setReserveResult(false);
						gestRecu.setResult(2);
						gestRecu.setOdsNumber(intOds);								
					}					
					
					String responseAsigManual = getXmlProcessor().marshal(asigManual);
					String responseGesRecu = getXmlProcessor().marshal(gestRecu);
					AsignaRecuManual_Respuesta respAManual = new AsignaRecuManual_Respuesta(responseAsigManual);
					AsignaRecuManual_Respuesta respGRecu = new AsignaRecuManual_Respuesta(responseGesRecu);
					respuestas.add(respAManual);
					respuestas.add(respGRecu);
				}
			}

		} else {
			salida.setErrorCode(1);
			salida.setErrorMessage("Error Emulando Asignacion de Recursos STB.");

			String responseMessage = getXmlProcessor().marshal(salida);
			ARecuSTBGranite_Respuesta r = new ARecuSTBGranite_Respuesta(responseMessage);
			respuestas.add(r);
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de
		 * respuestas.
		 */

		return respuestas;
	}

}
