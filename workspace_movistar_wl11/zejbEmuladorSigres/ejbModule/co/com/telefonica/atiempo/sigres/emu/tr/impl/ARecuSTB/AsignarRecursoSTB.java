/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
//package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import co.com.telefonica.atiempo.interfaces.atiempo.TR010E;
//import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;
//import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
//import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;
//
///**
// * @author 801936
// *
// * To change the template for this generated type comment go to
// * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
// */
//public class AsignarRecursoSTB extends TRMessageProcess {
//
//	/* (non-Javadoc)
//	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
//	 */
//	public List emulateResponse(String msg) {
//		/* Este ArrayList es para cargar las respuestas*/        
//			   ArrayList respuestas = new ArrayList();
//			   /* Se definen las tr de entrada y salida que se van a utilizar*/
//				TR010E entrada=null;
//				TR010S salida = null;
//			   /*
//				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
//				* una para la entrada y otra para la salida.
//				*/
//    
//			   entrada = (TR010E) getXmlProcessor().unmarshal(msg);
//			   salida = (TR010S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_010_S.xml"));
//		
//			   /*
//				* Se consulta el properties para conocer el estado de respuesta
//				* definido para este caso emulado.
//				*/
//			   String resultado = getTrProperties().getProperty("tr_010_s.status");
//			   
//			   String reservaDeRecursos = getTrProperties().getProperty("tr_010_s.recu");
//			   
//			   
//			   /*
//				* De acuerdo al resultado se procesan los datos segun convenga.
//				*/
//			   if (resultado.equals("ok")) {
//				  
//				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
//				  salida.setId(entrada.getId());
//				  salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
//				  if (reservaDeRecursos.equals("ok")) {
//				  	salida.setReserveResult(true);	
//				  }
//				  else {
//				  	salida.setReserveResult(false);
//				  }
//				  
//				  salida.setOdsNumber((int) entrada.getAtisRequestNumber());				  
//				   /* Se setea error false en la respuesta*/
//				   salida.setError(false);
//				  
//				  
//			   } else {
//				   salida.setError(true);
//				   salida.setErrorMessage("Error Emulando Asignacion de Recursos STB.");
//			   }
//			   /*
//				* Se genera el o los String de salida y se agregan a la lista de respuestas.
//				*/
//			   String responseMessage = getXmlProcessor().marshal(salida);
//				ARecuSTB_Respuesta r = new ARecuSTB_Respuesta(responseMessage);
//			   respuestas.add(r);
//			   return respuestas;
//	}
//
//}*/
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;

import java.util.ArrayList;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;

import co.com.telefonica.atiempo.interfaces.atiempo.Dslam;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.interfaces.atiempo.TR003S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR010S;

import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AsignarRecursoSTB extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR010E entrada = null;
		TR010S salida = null;
		TR003S asigManual = null;
		TR003S gestRecu = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		entrada = (TR010E) getXmlProcessor().unmarshal(msg);
		salida = (TR010S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_010_S.xml"));
		asigManual = (TR003S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_003_S.xml"));
		gestRecu = (TR003S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_003_S.xml"));
		
		salida.setPhoneNumber(entrada.getPhoneNumber());
		
		log.debug(">>>>>>>>>>SALIDA (TR010s) - TEL: " + salida.getPhoneNumber());
		
		// La propiedad origenYDestinoIguales tiene sentido en el caso de traslado. 
		// En ella indico si el nro origen y el destino coinciden o no
		if (getTrProperties().getProperty("tr_003_s.origenYDestinoIguales").equals("true")) {

			asigManual.setPreviousPhoneNumber(entrada.getPhoneNumber());
			asigManual.setPhoneNumber(entrada.getPhoneNumber());
			
			gestRecu.setPreviousPhoneNumber(entrada.getPhoneNumber());
			gestRecu.setPhoneNumber(entrada.getPhoneNumber());
			
			log.debug(">>>>>>>>>>IGUALES - ASIG MANUAL - PREVIO " + asigManual.getPreviousPhoneNumber());
			log.debug(">>>>>>>>>>IGUALES - ASIG MANUAL - NUEVO " + asigManual.getPhoneNumber());
			
			log.debug(">>>>>>>>>>IGUALES - GEST RECU - PREVIO " + gestRecu.getPreviousPhoneNumber());
			log.debug(">>>>>>>>>>IGUALES - GEST RECU - NUEVO " + gestRecu.getPhoneNumber());
			
		}else {
			asigManual.setPreviousPhoneNumber(entrada.getPhoneNumber());
			asigManual.setPhoneNumber(entrada.getPhoneNumber()+1);
			
			gestRecu.setPreviousPhoneNumber(entrada.getPhoneNumber());
			gestRecu.setPhoneNumber(entrada.getPhoneNumber()+1);
					
			log.debug(">>>>>>>>>>DISTINTOS - ASIG MANUAL - PREVIO " + asigManual.getPreviousPhoneNumber());
			log.debug(">>>>>>>>>>DISTINTOS - ASIG MANUAL - NUEVO " + asigManual.getPhoneNumber());
			
			log.debug(">>>>>>>>>>DISTINTOS - GEST RECU - PREVIO " + gestRecu.getPreviousPhoneNumber());
			log.debug(">>>>>>>>>>DISTINTOS - GEST RECU - NUEVO " + gestRecu.getPhoneNumber());
		}
		
		
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_010_s.status");
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			int intOds = 0;
			try {
				DBManager dbSeq = new DBManager();
				dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
				Integer ods = new Integer(dbSeq.seqNextValInteger("CORRELATIVO_MENSAJE"));
				intOds = Integer.parseInt(ods.toString());
			} catch (Exception e) {
				log.debug("No se pudo asignar la ODS ", e);
			}
			salida.setId(entrada.getId());
			salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			salida.setError(false);
			String recuSTB = getTrProperties().getProperty("tr_003_s.recuSTB");			
			
			if (!recuSTB.equals("ok")){
				//Si quiero que falle la asigancion automatica
				salida.setReserveResult(false);
			}else{
				//Si no quiero que falle la asignacion automatica
				salida.setReserveResult(true);
			}
			salida.setOdsNumber(intOds);
			String responseMessage = getXmlProcessor().marshal(salida);
			ARecuSTB_Respuesta r = new ARecuSTB_Respuesta(responseMessage);
			respuestas.add(r);
			
			/* Entra en caso de que la Asignacion de Recursos Automatica falle*/
			if (!recuSTB.equals("ok")) {
				String aManual = getTrProperties().getProperty("tr_003_s.aManual");
				String aManualPGI = getTrProperties().getProperty("tr_003_s.aManualPGI");
				String derivarGrecu = getTrProperties().getProperty("tr_003_s.grecu");
				/*Entra si la asignacion manual se realiza correctamente*/
				if(aManual.equals("ok")){
					
						log.debug("Realizo Asignacion Manual correctamente");
						asigManual.setId(entrada.getId());
						asigManual.setAtisRequestNumber(entrada.getAtisRequestNumber());
						asigManual.setError(false);
						//asigManual.setReserveResult(false);
						//Si no derivo a PGI
						if(!aManualPGI.equals("true")){
							asigManual.setResult(1);
						}else{
							log.debug("Emulando quiebre a PGI");
							asigManual.setResult(3);
							asigManual.setCna("611");
						}						
						asigManual.setOdsNumber(intOds);
						String responseAsigManual = getXmlProcessor().marshal(asigManual);
						AsignaRecuManual_Respuesta respAManual = new AsignaRecuManual_Respuesta(responseAsigManual);
						respuestas.add(respAManual);
				}else{					
					log.debug("Derivando a Gestion de Recursos ...");
					asigManual.setId(entrada.getId());
					asigManual.setAtisRequestNumber(entrada.getAtisRequestNumber());
					
					asigManual.setError(true);					
					asigManual.setResult(2);
					asigManual.setOdsNumber(intOds);
					//Ingresa si quiero que Gestion de recursos se haga correctamente
					if (derivarGrecu.equals("ok")) {
						String derivarGrecuPGI = getTrProperties().getProperty("tr_003_s.grecuPGI");						
						log.debug("Realizo Gestion de Recursos correctamente");
						gestRecu.setId(entrada.getId());
						gestRecu.setAtisRequestNumber(entrada.getAtisRequestNumber());
						gestRecu.setError(false);
						//gestRecu.setReserveResult(false);
						//Si no quiero q derive a PGI
						if(derivarGrecuPGI.equals("true")){							
							gestRecu.setResult(1);
						}else{
							log.debug("Emulando quiebre a PGI");
							gestRecu.setResult(3);
							gestRecu.setCna("611");
						}
						
						gestRecu.setOdsNumber(intOds);						
					}//Ingresa si quiero que Gestion de recursos no se haga correctamente 
					else if(!derivarGrecu.equals("ok")){
						log.debug("Realizo Gestion de Recursos incorrectamente");
						gestRecu.setId(entrada.getId());
						gestRecu.setAtisRequestNumber(entrada.getAtisRequestNumber());
						gestRecu.setError(true);
						//gestRecu.setReserveResult(false);
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
		    salida.setId(entrada.getId());
			salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			salida.setError(true);
			salida.setErrorMessage("Error Emulando Asignacion de Recursos STB.");

			String responseMessage = getXmlProcessor().marshal(salida);
			ARecuSTB_Respuesta r = new ARecuSTB_Respuesta(responseMessage);
			respuestas.add(r);
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de
		 * respuestas.
		 */

		return respuestas;
	}
}
