package co.com.telefonica.atiempo.sigres.emu.tr;

import co.com.telefonica.atiempo.sigres.emu.tr.impl.ConfMediacionMovil;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB.ActualizarInventarioGranite;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB.AsignarRecursoSTB;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB.AsignarRecursoSTBGranite;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB.ReversaAsignarRecursoSTBGranite;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ConfAutEOC.ConfiguracionAutomaticaEOCEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.INFTV.ConfirmacionPagoReciboEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.INFTV.obtenerInfoTV;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos.ActivarDecosTarjetas;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos.AlistarDecos;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos.RefrescarDecosTarjetasTOA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ActualizarInventario;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ActualizarInventarioBA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ActualizarInventarioBAPE;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.CambioNroBA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.CreaOdsGraniteEmulacion;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.NotificacionSAPEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.ObtenerConfBA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv.VistaGlobalEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvSS.ActualizarInventarioSS;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvSTB.ActualizarInventarioSTB;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvTV.ActualizarInventario_TV;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira.Altamira;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.aula.Aula365;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.ActivarLineasTroncalSip;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.BajaBASigres;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.ConfiguracionBA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.ConsultaActuacionASCEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.ConsultaDisponibilidadAgendaSC;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.CreacionActuacionAgendaSC;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarConfiguracionTeraBox;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarConfiguracionWebFilterOptenet;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarCorreoTeraBox;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarMensajeAutoInstalacionModem;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarPrimeraFacturaInternetEquipado;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.EnviarSMSACS;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.InternetMovilEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.MensajeTutorWeb;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.SolicitudReagendamientoASCEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.activarEquiposAgendaSC;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.activarModemAgendaSC;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ActualizarInventarioEquipo;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ConsultaEquipos;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.ObtEquipos;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ci.ConfigurarInternet;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ci.ConfigurarInternetAT;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ci.ConfigurarInternetCambioPlan;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ci.ConfigurarTerra;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cm.ConsultaModems;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutIMS.ConfiguracionAutomaticaIMSEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutIMS.ConfiguracionAutomaticaMSANEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutSTB.ConfAutomaticaSTB;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.confNapster.ConfigurarNapsterEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.iri.InformarResultadoInstalacion;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.modOperadora.ModificarIdOperadoraSigres;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.occ.ObtenerCuentaCorreo;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.opr.ObtenerPuntosDeRed;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.opr.ObtenerPuntosRedGranite;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.paqueteFijoMovil.ConfigurarPaqueteMovilEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.paqueteFijoMovil.RecargaFijoMovilEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.pdva.ConfigurarPDVAEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.publicidad.ConfigurarPresenciaDigitalEmu;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.ConsultarLineasTroncalSip;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.RefrescarDatosBA;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.sr.SuspensionReconexion;

;

/**
 * TRMessageProcessFactory Factory de procesos emulados para las distintas
 * interfaces (TR).
 * 
 * @author Gonzalo Arreche
 *  
 */
public class TRMessageProcessFactory {
	/**
	 * Devuelve el proceso de aceurdo al tipo de interfaz que recibe como
	 * parametro.
	 * 
	 * @param type
	 *            es el tipo de Intefaz(TR)
	 * @return el proceso emulado que debe ejecutarse.
	 */
	public static TRMessageProcess getTRMessage(String type)
			throws NoMessageDefFoundException {
		TRMessageProcess tr = null;
		if (type.equals("tr-012-e")) {
			tr = new ObtenerPuntosDeRed();
		} else if (type.equals("tr-013-e")) {
			tr = new ConfigurarInternetAT();
		} else if (type.equals("tr-007-e")) {
			tr = new ActualizarInventarioBA();
		} else if (type.equals("tr-014-e")) {
			tr = new ObtenerConfBA();
		} else if (type.equals("tr-015-e")) {
			tr = new CambioNroBA();
		} else if (type.equals("tr-030-e")) {
			tr = new ConfigurarInternet();
		} else if (type.equals("tr-043-e")) {
			tr = new ObtenerCuentaCorreo();
		} else if (type.equals("tr-033-e")) {
			tr = new InformarResultadoInstalacion();
		} else if (type.equals("tr-022-e")) {
			tr = new ConsultaModems();
		} else if (type.equals("tr-007-e")) {
			tr = new ActualizarInventario();
		} else if (type.equals("tr-017-e")) {
			tr = new ActivarDecosTarjetas();
		} else if (type.equals("tr-019-e")) {
			tr = new obtenerInfoTV();
		} else if (type.equals("tr-018-e")) {
			tr = new ActualizarInventario_TV();
		} else if (type.equals("tr-010-e")) {
			tr = new AsignarRecursoSTB();
		} else if (type.equals("tr-016-e")) {
			tr = new AlistarDecos();
		} else if (type.equals("tr-036-e")) {
			tr = new BajaBASigres();
		} else if (type.equals("tr-038-e")) {
			tr = new ConfigurarInternetCambioPlan();
		} else if (type.equals("tr-004-e")) {
			tr = new ActualizarInventarioSTB();
		} else if (type.equals("tr-014-e")) {
			tr = new ObtenerConfBA();
		} else if (type.equals("tr-042-e")) {
			tr = new SuspensionReconexion();
		} else if (type.equals("tr-024-e")) {
			tr = new Aula365();
		} else if (type.equals("tr-023-e")) {
			tr = new ConfigurarTerra();
		} else if (type.equals("tr-013-e")) {
			tr = new ConfiguracionBA();
		} else if (type.equals("tr-011-e")) {
			tr = new ActualizarInventarioSS();
		} else if (type.equals("tr-035-e")) {
			tr = new RefrescarDatosBA();
		} else if (type.equals("tr-041-e")) {
			tr = new ModificarIdOperadoraSigres();
		} else if (type.equals("tr-510-e")) {
			tr = new AsignarRecursoSTBGranite();
		} else if (type.equals("tr-511-e")) {
			tr = new ConfAutomaticaSTB();
		} else if (type.equals("tr-512-e")) {
			tr = new ActualizarInventarioGranite();
		} else if (type.equals("tr-513-e")) {
			tr = new ReversaAsignarRecursoSTBGranite();
		} else if (type.equals("tr-514-e")) {
			tr = new ObtenerPuntosRedGranite();
		} else if (type.equals("tr-515-e")) {
			tr = new ActualizarInventarioBAPE();
		} else if (type.equals("tr-517-e")) {
			tr = new CreaOdsGraniteEmulacion();
		} else if (type.equals("tr-004-e")) {
			tr = new ActualizarInventarioSTB();
		} else if (type.equals("tr-601-e")) {
			tr = new Altamira();
		} else if (type.equals("tr-026-e")) {
			tr = new ConsultaEquipos();
		} else if (type.equals("tr-028-e")) {
			tr = new ActualizarInventarioEquipo();
		} else if (type.equals("tr-027-e")) {
			tr = new ObtEquipos();
		} else if (type.equals("tr-044-e")) {
			tr = new EnviarPrimeraFacturaInternetEquipado();
		} else if (type.equals("tr-048-e")) {
			tr = new EnviarConfiguracionWebFilterOptenet();
		} else if (type.equals("tr-701-e")) {
			tr = new CreacionActuacionAgendaSC();
		} else if (type.equals("tr-708-e")) {
			tr = new activarEquiposAgendaSC();
		} else if (type.equals("tr-047-e")) {
			tr = new MensajeTutorWeb();
		} else if (type.equals("tr-049-e")) {//@idrincon - req 3274
			tr = new ConsultarLineasTroncalSip();
		} else if (type.equals("tr-050-e")) {
			tr = new ActivarLineasTroncalSip();
		} else if (type.equals("tr-051-e")) {
			tr = new EnviarConfiguracionTeraBox();
		} else if (type.equals("tr-002-e")) {
			tr = new EnviarLiberacionRecursosSTB();
		} else if (type.equals("tr-052-e")) {
			tr = new EnviarInformeQuiebresConstructora();
		} else if (type.equals("tr-135-e")) {
			tr = new EnviarMensajeAutoInstalacionModem();
		} else if (type.equals("AGENDASC")) {
			tr = new EnviarSMSACS();
		} else if (type.equals("tr-717-e")) {
			tr = new activarModemAgendaSC();
		} else if (type.equals("tr-029-e")) {
			tr = new EnviarInformacionEquiposMMSAP();
		} else if (type.equals("tr-610-e")) {
			tr = new InternetMovilEmu();
		} else if (type.equals("tr-611-e")) {
			tr = new ConfirmacionPagoReciboEmu();
		} else if (type.equals("tr-025-e")) {
			tr = new VistaGlobalEmu();
		} else if (type.equals("tr-020-e")) {
			tr = new NotificacionSAPEmu();
		} else if (type.equals("tr-703-e")) {
			tr = new ConsultaDisponibilidadAgendaSC();
		} else if (type.equals("tr-702-e")) {
			tr = new ConsultaActuacionASCEmu();
		} else if (type.equals("tr-705-e")) {
			tr = new SolicitudReagendamientoASCEmu();
		} else if (type.equals("tr-054-e")) {
			tr = new ConfigurarPresenciaDigitalEmu();
		} else if (type.equals("tr-055-e")) {
			tr = new ConfigurarPDVAEmu();
		} else if (type.equals("tr-718-e")) {
			tr = new EnviarCorreoTeraBox();
		} else if (type.equals("tr-612-e")) {
			tr = new ConfMediacionMovil();
		} else if (type.equals("tr-613-e")) {
			tr = new ConfigurarPaqueteMovilEmu();
		} else if (type.equals("tr-614-e")) {
			tr = new RecargaFijoMovilEmu();
		} else if (type.equals("tr-518-e")) {
			tr = new ConfiguracionAutomaticaEOCEmu();
		}
		else if (type.equals("tr-602-e")) {
			tr = new ConfiguracionAutomaticaIMSEmu();
		}else if (type.equals("tr-603-e")) {
			tr = new ConfiguracionAutomaticaMSANEmu();
		} else if (type.equals("tr-604-e")) {
			tr = new ConfigurarNapsterEmu();
		} else if (type.equals("tr-805-e")) {
			tr = new RefrescarDecosTarjetasTOA();
		} else {
			throw new NoMessageDefFoundException("La intefaz " + type
					+ " no se encuentra definida.");
		}
		return tr;
	}
}