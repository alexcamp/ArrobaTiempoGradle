package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.util.ArrayList;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

//CR24918 - TV Masivo - PCawen
/**
 * Bean implementation class for Enterprise Bean: AInstalarDTHMasivo
 */
public class AInstalarDTHMasivoBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("onInicioActividad InstalarDTHMasivo " + nroPeticion);
		int decosAlta = 0;
		int decosBaja = 0;
		decosAlta = getNroDecos(nroPeticion)[0];
		decosBaja = getNroDecos(nroPeticion)[1];
		int nroDecosMass = ComunInterfaces.NRO_DECOS_MASIVO;
		if(decosAlta < ComunInterfaces.NRO_DECOS_MASIVO){
		 	log.debug("Seteamos RealizarTerminoInmediato en true!");
			act.setRealizarTerminoInmediato(true);
			act.setObservacion("Se inhibe la actividad de InstalarDTHMasivo por no ser de tipo Masivo");
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
			log.debug("Se finaliza la Actividad InstalarDTHMasivo[" + nroPeticion + "]");
		 }else{
		 	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "S");
		 }
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		Long nroPeticion = act.getNumeroPeticion();
		log.debug("onTerminoActividad AInstalarDTHMasivoBean " + nroPeticion);
		String forza = act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza);
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)){
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, forza);
		}else{
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "S");
		}
	}
	
	private int[] getNroDecos(Long nroPeticion) {
		int[] cantidadDeco = new int[2];
		int decosAlta = 0;
		int decosBaja = 0;
		try{
			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(nroPeticion);
			
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++) {
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				if ( psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoTV || psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoPVRTV ) {
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++decosAlta;
					else if ( "B".equals(psDto.getTipoOperacionComercial()) )
						++decosBaja;
				}
				else if(psDto.getIdFaps().intValue()==ComunInterfaces.familiaPcTV){
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++decosAlta;
				}
			}
		}catch (Exception e){
	 		log.error("Error al obtener la cantidad de decos");
	 	}
		cantidadDeco[0] = decosAlta;
		cantidadDeco[1] = decosBaja;
	 	return cantidadDeco;
	}
	
}
