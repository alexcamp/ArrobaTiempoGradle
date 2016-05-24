package co.com.telefonica.atiempo.sigres.emu.tr;

import co.com.telefonica.atiempo.sigres.emu.tr.impl.InfTvST.obtenerInfoTVST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ActualizarInventarioBA_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ConfigurarPresenciaDigitalEmu_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ObtenerConfBA_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.STNotificacionSAPEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.STVistaGlobalEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvTV.ActualizarInventario_TV_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.ActivarLineasTroncalSip;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.MensajeTutorWeb;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ActivarDecosTarjetasST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ActualizarInventarioEquipo_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.AlistarDecosst; 
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ObtEquiposST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cm.ConsultaEquiposST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cm.ConsultaModems_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.marav.MarcarAveria;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.marav.MarcarAveriaGR;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.occ.ObtenerCuentaCorreo_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.opr.ObtenerPuntosDeRedGR_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.opr.ObtenerPuntosDeRed_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.opr.RefrescarRecursosST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.publicidad.ConfigurarPresenciaDigitalEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.ActivacionModemsAgendaSCST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.ActivacionRecursosAgendaSCST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.ConsultarLineasTroncalSip;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.CreacionActuacionAgendaSCST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.RefrescarDatosBA_ST;

/**
 * TRMessageProcessFactory
 * Factory de procesos emulados para las distintas interfaces (TR).
 * 
 * @author Gonzalo Arreche
 *
 */
public class TRMessageProcessFactory_ST {
	/**
	 * Devuelve el proceso de aceurdo al tipo de interfaz
	 * que recibe como parametro.
	 * @param type es el tipo de Intefaz(TR)
	 * @return el proceso emulado que debe ejecutarse.
	 */
	public static TRMessageProcess_ST getTRMessage(String type)
		throws NoMessageDefFoundException {
		TRMessageProcess_ST trst=null;
		if (type.equals("tr-012-e")) {
			trst = new ObtenerPuntosDeRed_ST();
		} else if (type.equals("tr-007-e")) {
			trst = new ActualizarInventarioBA_ST();
		} else if (type.equals("tr-014-e")) {
			trst = new ObtenerConfBA_ST();
		} else if (type.equals("tr-022-e")) {
			trst = new ConsultaModems_ST();
		} else if (type.equals("tr-021-e")){
			trst = new MarcarAveria();
		}
		else if (type.equals("tr-514-e")){
			trst = new ObtenerPuntosDeRedGR_ST();
		}
		else if (type.equals("tr-516-e")){
			trst = new MarcarAveriaGR();
		}
		else if (type.equals("tr-043-e")){
			trst = new RefrescarRecursosST();
		}
		else if (type.equals("tr-019-e")){
			trst = new obtenerInfoTVST();
		}else if (type.equals("tr-035-e")) {
			trst = new RefrescarDatosBA_ST();
		}else if (type.equals("tr-043-e")) {
			trst = new ObtenerCuentaCorreo_ST();		
		}else if (type.equals("tr-027-e")) {
			trst = new ObtEquiposST();
		}else if (type.equals("tr-026-e")) {
			trst = new ConsultaEquiposST();
		}else if (type.equals("tr-028-e")) {
			trst = new ActualizarInventarioEquipo_ST();
		}else if (type.equals("tr-016-e")) {
			trst = new AlistarDecosst();
		}else if (type.equals("tr-017-e")) {
			trst = new ActivarDecosTarjetasST();
		}else if (type.equals("tr-035-e")) {
			trst = new RefrescarDatosBA_ST();
		}else if (type.equals("tr-701-e")) {
			trst = new CreacionActuacionAgendaSCST();
		}else if (type.equals("tr-708-e")) {
			trst = new ActivacionRecursosAgendaSCST();
		}else if (type.equals("tr-717-e")) {
			trst = new ActivacionModemsAgendaSCST();
		}else if (type.equals("tr-025-e")) {
			trst = new STVistaGlobalEmu();
		}else if (type.equals("tr-020-e")) {
			trst = new STNotificacionSAPEmu();
		}else if (type.equals("tr-054-e")) {
			trst = new ConfigurarPresenciaDigitalEmu_ST();
		}else if (type.equals("tr-018-e")) {
			trst = new ActualizarInventario_TV_ST();
		}else {
			throw new NoMessageDefFoundException(
				"La intefaz " + type + " no se encuentra definida.");
		}
		return trst;
	}
}
