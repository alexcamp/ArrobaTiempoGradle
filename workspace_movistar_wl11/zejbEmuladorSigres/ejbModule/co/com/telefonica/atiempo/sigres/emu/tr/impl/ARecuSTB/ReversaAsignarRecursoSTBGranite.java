package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;

import java.util.ArrayList;
import java.util.List;


import co.com.telefonica.atiempo.interfaces.atiempo.TR513E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR513S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

import com.tecnonautica.utiles.db.DBManager;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReversaAsignarRecursoSTBGranite extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR513E entrada = null;
		TR513S salida = null;
		TR513S asigManual = null;
		TR513S gestRecu = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		entrada = (TR513E) getXmlProcessor().unmarshal(msg);
		salida = (TR513S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_513_S.xml"));
		asigManual = (TR513S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_513_S.xml"));
		gestRecu = (TR513S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_513_S.xml"));
		
		String resultado = getTrProperties().getProperty("tr_513_s.status");
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		salida.setId(entrada.getId());
		salida.setAtiempoRequestNumber(entrada.getAtisRequestNumber().longValue());
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
					
			salida.setErrorCode(0);

		} else {
			salida.setErrorCode(1);
			salida.setErrorMessage("Error Emulando Asignacion de Recursos STB.");
		}
		
		String responseMessage = getXmlProcessor().marshal(salida);
		ReversaAsignarRecursosSTBGranite_Respuesta r = new ReversaAsignarRecursosSTBGranite_Respuesta(responseMessage);
		respuestas.add(r);
		/*
		 * Se genera el o los String de salida y se agregan a la lista de
		 * respuestas.
		 */

		return respuestas;
	}

}
