package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.FinderException;
import javax.naming.NamingException;



import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.CamaraKey;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocal;
import co.com.telefonica.atiempo.ejb.eb.CamaraLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqKey;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocal;
import co.com.telefonica.atiempo.ejb.eb.Ps_Tipo_EqLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoKey;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocal;
import co.com.telefonica.atiempo.ejb.eb.Tipo_Eq_ElementoLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044EEquipment;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocal;
import ejb.co.com.telefonica.atiempo.ejb.eb.caracteristicaPS.CaracteristicaPSLocalHome;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarFactura
 */
public class AConfigurarFacturaBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Entro a onInicio de la actividad Configuración Factura");
		boolean tieneEquipos = false;
		
		
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			Long psQuickWins= obtenerPsQW(peticionLocal);
			Collection productosServiciosPeticion=peticionLocal.getProducto_servicio_peticion();
			Elemento_PeticionLocalHome elementoPeticionLocalHome = (Elemento_PeticionLocalHome)HomeFactory.getHome(Elemento_PeticionLocalHome.JNDI_NAME);
			Collection elementoPeticionList = elementoPeticionLocalHome.findPeticion(act.getNumeroPeticion());
			RecursosBADelegate delegate = new RecursosBADelegate();
			ArrayList equiposList = new ArrayList();
			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			
			CamaraLocalHome camaraLocalHome = (CamaraLocalHome )HomeFactory.getHome(CamaraLocalHome.JNDI_NAME);
			Collection camaras = camaraLocalHome.findByPeticion(act.getNumeroPeticion());
			tieneEquipos = camaras!=null && !camaras.isEmpty();
			
			if(tieneEquipos){
				PeticionesDelegate incidentesDelegate = new PeticionesDelegate();
				String[] id_ps = incidentesDelegate.recuperarParametroFromPropertiesBD("PSIDS_CAMARA_MONITOREO").split(",");
				Map idPsCamaras = new  TreeMap();
				for (int i = 0; i < id_ps.length; i++) {
					idPsCamaras.put(id_ps[i],id_ps[i]);
				}
				for (Iterator iter = camaras.iterator(); iter.hasNext();) {
					CamaraLocal camara = (CamaraLocal) iter.next();
					CamaraKey key = (CamaraKey)camara.getPrimaryKey();
					TR044EEquipment equipo = new TR044EEquipment();
					equipo.setCodigoPS(camara.getPsId().longValue());
					equipo.setSerial(key.cameraSerial);
					equipo.setCodigoPaquete(getPsPDTI(productosServiciosPeticion));
					equipo.setCodigoCaracteristica(obtenerCodigoCaracteristicaCamara(camara,productosServiciosPeticion,idPsCamaras));
					equiposList.add(equipo);
				}
			}
			
			for (Iterator elementoPeticionIter =  elementoPeticionList.iterator(); elementoPeticionIter.hasNext();){
				Elemento_PeticionLocal elementoPeticionLocal = (Elemento_PeticionLocal) elementoPeticionIter.next();
				Long psID;
				TR044EEquipment equipos = new TR044EEquipment();
				 if(elementoPeticionLocal.getTipo_elemento() != null && elementoPeticionLocal.getTipo_elemento().toString().equals("18")){
					long temp=elementoPeticionLocal.getPs_id().longValue()*100+1;
					psID=new Long(temp);
				 }else{
				 	psID=elementoPeticionLocal.getPs_id();
				 }
				if(psQuickWins!=null){
					equipos.setCodigoPS(psQuickWins.longValue());
				}else{
					equipos.setCodigoPS(psID.longValue());
				}
				Subpeticion_caracteristicasLocal sc=infoComunColombiaBI.obtenerCaracteristica(act.getNumeroPeticion(),new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW));
				if(sc != null)
					equipos.setCodigoCaracteristica(sc.getCod_val_crc_cd());
				equipos.setSerial(elementoPeticionLocal.getSerial());
				equipos.setCodigoPaquete(getPsPDTI(productosServiciosPeticion));

				equiposList.add(equipos);
				
				tieneEquipos = true;
			}
			
			if (!tieneEquipos){
				ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
				IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(act.getCodigoActividad());
				
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGI"));
				act.setObservacion("Se deriva la petición a PGI porque no trae equipos para enviar a facturar");
				act.setRealizarTerminoInmediato(true);
			}else{
				//InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				Subpeticion_caracteristicasLocal sc=infoComunColombiaBI.obtenerCaracteristica(act.getNumeroPeticion(),new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW));
				if(sc!=null){
					((TR044EEquipment)equiposList.get(0)).setCodigoCaracteristica(sc.getCod_val_crc_cd());
				}
				delegate.solicitarPrimeraFacturaInternetEquipado(act.getNumeroPeticion(), equiposList, act.getCodigoActividad(), "0",act.getIdActividadFlujo());
			}
			
		}catch(Exception ex){
			log.debug("Se presento un error en la ejecucion del proceso en onInicioActividadVPI de la clase AConfigurarFacturaBean:" ,ex);
		}

	}

	/**
	 * Obtiene el código de la característica de una cámara en particular
	 * @param camara Camara
	 * @param producto_servicio_peticion Colección de items de tipo producto_servicio_peticion
	 * @param idPsCamaras Map con los ps's de cámaras existentes
	 * @return El código de la características
	 * @throws Exception Cualquier error no contemplado en la operación
	 */
	private Long obtenerCodigoCaracteristicaCamara(CamaraLocal camara, Collection producto_servicio_peticion,Map idPsCamaras) throws Exception {
		Ps_Tipo_EqLocalHome psTipoEquipoLH = (Ps_Tipo_EqLocalHome)HomeFactory.getHome(Ps_Tipo_EqLocalHome.JNDI_NAME);
		Tipo_Eq_ElementoLocalHome tipoEquipoElementoLH = (Tipo_Eq_ElementoLocalHome)HomeFactory.getHome(Tipo_Eq_ElementoLocalHome.JNDI_NAME);
		CaracteristicaPSLocalHome caracteristicaPSLH =(CaracteristicaPSLocalHome)HomeFactory.getHome(CaracteristicaPSLocalHome.JNDI_NAME);
		Grpe_PsLocalHome grPSLH = (Grpe_PsLocalHome)HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);;
		Long retorno = null;
		superFor: for (Iterator iter = producto_servicio_peticion.iterator(); iter.hasNext();) {
			Producto_servicio_peticionLocal productoServicioPeticion = (Producto_servicio_peticionLocal) iter.next();
			
			if(!idPsCamaras.containsKey(productoServicioPeticion.getPsId().toString())) continue;
			
			Subpeticion_atisLocal subpeticionAtis = productoServicioPeticion.getFk_01_subp_atis();
			Subpeticion_caracteristicasLocal subpeticionCaracteristica = obtenerSubPeticionCaracteristica(subpeticionAtis);
			CaracteristicaPSLocal caracteristicaPS = caracteristicaPSLH.findByCaracteristica(subpeticionCaracteristica.getVal_ral_crc_cd(),subpeticionAtis.getCod_pro_ser_cd());
			Ps_Tipo_EqLocal tipoEquipo = psTipoEquipoLH.findTipoByPs(caracteristicaPS.getPsId().longValue());
			
			if(tipoEquipo.getId_tipo_eq().intValue() != Integer.valueOf(camara.getTipoEquipo()).intValue()) continue;
			
			Grpe_PsLocal grpse = grPSLH.findGrupoByPS(caracteristicaPS.getPsId());
			
			if(grpse.getGrpe_id().longValue() != Long.valueOf(camara.getTipoInventario()).longValue()) continue;
			
			Collection equiposElementos =  tipoEquipoElementoLH.findElementoByTipo(tipoEquipo.getId_tipo_eq().longValue());
			for (Iterator iterator = equiposElementos.iterator(); iterator.hasNext();) {
				Tipo_Eq_ElementoLocal elemento = (Tipo_Eq_ElementoLocal) iterator.next();
				if(elemento.getId_elemento().intValue() == camara.getTipoElemento().intValue()){
					retorno = subpeticionCaracteristica.getCod_val_crc_cd();
					break superFor;
				}
			}
		}
		return retorno;
	}
	
	/**
	 * Obtiene la subpeticioncaracteristica de tipo equipo 51, teniendo una subpetición atis
	 * @param subpeticion_atisLocal La subpetición atis
	 * @return La subpeticionCaracteristica sí es encontrada, null en caso contrario
	 * @throws Exception
	 */
	private Subpeticion_caracteristicasLocal obtenerSubPeticionCaracteristica(Subpeticion_atisLocal subpeticion_atisLocal) throws Exception {
		Subpeticion_caracteristicasLocalHome subpeticionCaracteristicasLocalHome = (Subpeticion_caracteristicasLocalHome) HomeFactory.getHome(Subpeticion_caracteristicasLocalHome.JNDI_NAME);
		Subpeticion_atisKey subpeticion_atisKey = (Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey();
		Subpeticion_caracteristicasKey sck=new Subpeticion_caracteristicasKey(new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW),subpeticion_atisKey);
		try{
			return subpeticionCaracteristicasLocalHome.findByPrimaryKey(sck);
		}catch (FinderException e) { 
			log.debug ("La subpeticion "+((Subpeticion_atisKey)subpeticion_atisLocal.getPrimaryKey()).cod_sub_cd +" no tiene la caracteristica"+e.getMessage());
			return null;
		}
	}

	/**
	 * @param psID
	 * @return
	 * @throws Exception
	 */
	private Long getPsPDTI(Collection productosPeticion) throws Exception {
		PeticionesDelegate pDelegate = new PeticionesDelegate();
		String psString= pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.PS_PDTI_PROPERTIES);
		 if(psString == null || psString.equals("")){
		 	return null;
		 }
    	String []listaPsIntTotal = psString.split(",");
    	for(Iterator it=productosPeticion.iterator();it.hasNext();){
    		Producto_servicio_peticionLocal psp = (Producto_servicio_peticionLocal) it.next();
    		String psEntrada= psp.getPsId().toString();
	    	for (int i = 0; i < listaPsIntTotal.length; i++) {
	    		String temp= listaPsIntTotal[i];
				if(psEntrada.equals(temp.trim())){
					return psp.getPsId();
				}
			}
    	}
		 
		 return null;
	}

	/**
	 * Indica si se esta realizando una venta de contado de Quick Wins
	 * @param peticionLocal
	 * @return
	 */
	private Long obtenerPsQW(PeticionLocal peticionLocal) {
		Collection peticionFlujoList = peticionLocal.getPeticion_flujo();
		for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
			if(peticionFlujoLocal.getFk_opco_2_pefl().getOpco_nombre().equals(ComunInterfaces.FLUJO_VENTA_EQUIPO_CONTADO)){
				return peticionFlujoLocal.getPrse_id();
			}
		}
		return null; 
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Entro a onTermino de la actividad Configuración Factura");
	}

}