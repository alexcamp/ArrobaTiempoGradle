/*
 * Created on 22-10-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.actividades;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import co.com.atiempo.dto.PsVsOcVO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActividadEJBDTO {

	private ArrayList psOk = null;
	private String ACCION_DESPLIEGA_PANTALLA = null; //VpistbbaConfig.getVariable("ACCION_DESPLIEGA_PANTALLA");
	private Long idUserDummy= null; //new Long(VpistbbaConfig.getVariable("IDDUMMY"));

//	private boolean iniciada = false;
	private String idTemplateWf = null;
	private Long numeroPeticion = null; //Es el ID proceso en el WF
	private String codigoActividad = null;
	private String actImplCorrelID = null;
	private Map datos = null;
	private Long idUsuario = null;//USERDUMY asignado
	private Long idUsuarioCierre = null;//USERDUMY que hace el cierre desde la pantalla
	
//	protected boolean esActividadVacia = false;
	private boolean grabarWfInstancia = false; // Por defecto ninguna se graba. 
	private boolean realizarTerminoInmediato = false; //Por defecto quedan abiertas.
	private boolean noTerminar = false; // para cuando llame al on termino, no haga nada mas que la logica del ejb sin terminar nada.
	private boolean vacia = false; //Por defecto quedan abiertas.
	
	private String observacion = "";
	private Long idCausaCierre = null;
	private String nombreEstructuraDatos="";
	private String nombreAplicacion="";
	private Long idAplicacion=null;
	
	private ActividadDTO actividadBD = null;	
	// TODO:CR11267
	private boolean grabarEnBitacora=true;
	
	private boolean overwriteObs = false;
	
	// CR29928 - adocarmo - inicio
	// Propiedad que indica si se publico la peticion en la bandeja
	private boolean sePublicoPeticion = false;
	// CR29928 - adocarmo - fin
	
	public ActividadEJBDTO(){
		//inicializar
		this.psOk=null;
		this.actImplCorrelID=null;
		this.codigoActividad=null;
		this.datos=null;
		this.idAplicacion=null;
		this.idCausaCierre=null;
		this.idTemplateWf=null;
		this.idUserDummy=new Long(VpistbbaConfig.getVariable("IDDUMMY"));;
		this.idUsuario=idUserDummy;
		this.idUsuarioCierre=idUserDummy;
		this.nombreAplicacion="";
		this.nombreEstructuraDatos="";
		this.numeroPeticion=null;
		this.observacion="";
		this.grabarWfInstancia=false;
		this.realizarTerminoInmediato=false;
		this.noTerminar = false; // para cuando llame al on termino, no haga nada mas que la logica del ejb sin terminar nada.
		this.vacia=false;
		this.actividadBD = null;
		this.ACCION_DESPLIEGA_PANTALLA = "";//VpistbbaConfig.getVariable("ACCION_DESPLIEGA_PANTALLA");
		
		// CR29928 - adocarmo - inicio
		sePublicoPeticion = false;		
		// CR29928 - adocarmo - fin
	}


	/**
	 * @return
	 */
	public String getACCION_DESPLIEGA_PANTALLA() {
		return ACCION_DESPLIEGA_PANTALLA;
	}

	/**
	 * @return
	 */
	public String getActImplCorrelID() {
		return actImplCorrelID;
	}

	/**
	 * @return
	 */
	public ActividadDTO getActividadBD() {
		return actividadBD;
	}

	/**
	 * @return
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @return
	 */
	public Map getDatos() {
		return datos;
	}



	/**
	 * @return
	 */
	public Long getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * @return
	 */
	public Long getIdCausaCierre() {
		return idCausaCierre;
	}

/**
 * @return
 */
	public String getIdTemplateWf() {
		return idTemplateWf;
	}

	/**
	 * @return
	 */
	public Long getIdUserDummy() {
		return idUserDummy;
	}

	/**
	 * @return
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return
	 */
	public Long getIdUsuarioCierre() {
		return idUsuarioCierre;
	}

	/**
	 * @return
	 */
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	/**
	 * @return
	 */
	public String getNombreEstructuraDatos() {
		return nombreEstructuraDatos;
	}

	/**
	 * @return
	 */
	public boolean isNoTerminar() {
		return noTerminar;
	}

	/**
	 * @return
	 */
	public Long getNumeroPeticion() {
		return numeroPeticion;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @return
	 */
	public boolean isRealizarTerminoInmediato() {
		return realizarTerminoInmediato;
	}

	/**
	 * @param string
	 */
	public void setACCION_DESPLIEGA_PANTALLA(String string) {
		ACCION_DESPLIEGA_PANTALLA = string;
	}

	/**
	 * @param string
	 */
	public void setActImplCorrelID(String string) {
		actImplCorrelID = string;
	}

	/**
	 * @param actividadDTO
	 */
	public void setActividadBD(ActividadDTO actividadDTO) {
		actividadBD = actividadDTO;
	}

	/**
	 * @param string
	 */
	public void setCodigoActividad(String string) {
		codigoActividad = string;
	}

	/**
	 * @param map
	 */
	public void setDatos(Map map) {
		datos = map;
	}

	/**
	 * @param long1
	 */
	public void setIdAplicacion(Long long1) {
		idAplicacion = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdCausaCierre(Long long1) {
		idCausaCierre = long1;
	}

/**
 * @param string
 */
	public void setIdTemplateWf(String string) {
		idTemplateWf = string;
	}

	/**
	 * @param long1
	 */
	public void setIdUserDummy(Long long1) {
		idUserDummy = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuario(Long long1) {
		idUsuario = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdUsuarioCierre(Long long1) {
		idUsuarioCierre = long1;
	}

	/**
	 * @param string
	 */
	public void setNombreAplicacion(String string) {
		nombreAplicacion = string;
	}

	/**
	 * @param string
	 */
	public void setNombreEstructuraDatos(String string) {
		nombreEstructuraDatos = string;
	}

	/**
	 * @param b
	 */
	public void setNoTerminar(boolean b) {
		noTerminar = b;
	}

	/**
	 * @param long1
	 */
	public void setNumeroPeticion(Long long1) {
		numeroPeticion = long1;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}
	public void setObservacion(String string,boolean overwrite) {
			observacion = string;
			overwriteObs = overwrite;
	}
	public boolean getOverwriteObs(){
		return this.overwriteObs;	
	}
	/**
	 * @param b
	 */
	public void setRealizarTerminoInmediato(boolean b) {
		realizarTerminoInmediato = b;
	}

/**
 * @return
 */
	public boolean isGrabarWfInstancia() {
		return grabarWfInstancia;
	}

	/**
	 * @return
	 */
	public boolean isVacia() {
		return vacia;
	}

/**
 * @param b
 */
	public void setGrabarWfInstancia(boolean b) {
		grabarWfInstancia = b;
	}

	/**
	 * @param b
	 */
	public void setVacia(boolean b) {
		vacia = b;
	}
	
	public Integer getIdActividadFlujo(){
		Integer idAF= null;
		if (this.actividadBD != null){
			idAF=this.actividadBD.getIdActFlujo();
		}
		return idAF;
	}	

	public void setDato(String llave, String valor) {
//		this.checkDatos();
		if(null == this.datos) {
			this.datos = new HashMap();
		}
		this.datos.put(llave,valor);
	}
	
	public String getDato(String llave) {
//		this.checkDatos();
		String dato = "";
		try {
			if(null == this.datos) {
				this.datos = new HashMap();
			}
			dato = (String) this.datos.get(llave);
			//TODO Inicio AT-1308
			if(null == dato || dato.equals("X")) {
            //TODO Fin AT-1308
				return "";
			}
			return dato;
		} catch (NullPointerException e) {
			return "";
		}		
	}	

	public boolean containsKeyDato(String llave) {
//		checkDatos();
		if(this.datos==null)
			return false;
		return this.datos.containsKey(llave);
	}	

	public String getXMLDatos(){
		String nombreEstructura = this.getNombreEstructuraDatos();
		if(null == nombreEstructura || "".equals(nombreEstructura)) {
			throw new IllegalArgumentException("Se está intentando insertar una actividad de tipo " + this.getClass().getName() + "(" + this.getCodigoActividad() + ") sin haber definido el nombre de la estructura de datos, (revisar el metodo getNombreEstructuraDatos() de dicha clase )");
		}
		StringBuffer xmlStr = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<"+nombreEstructura+">\n");
		Set llaves = this.datos.keySet();
		Iterator iteradorLlaves = llaves.iterator();
		while (iteradorLlaves.hasNext()) {
			String llave = (String) iteradorLlaves.next();
			xmlStr.append("<"+llave+">"+this.getDato(llave)+"</"+llave+">\n");
		}
		xmlStr.append("</"+nombreEstructura+">\n");
		return xmlStr.toString();
	}
	
	public String getUrlBandeja() {
		return ACCION_DESPLIEGA_PANTALLA
			+ "template="
			+ this.getIdTemplateWf()
			+ "&folio="
			+ this.getNumeroPeticion()
			+ "&actividad="
			+ URLEncoder.encode(this.getCodigoActividad())
			+ "&instanciaActividad="
			+ URLEncoder.encode(this.getActImplCorrelID());
	}
	/**
	 * @return
	 */
	public ArrayList getPsOk() {
		return psOk;
	}

	/**
	 * @param list
	 */
	public void setPsOk(ArrayList list) {
		psOk = list;
	}
	
	/**
	 * @return
	 */
	public boolean isGrabarEnBitacora() {
		return grabarEnBitacora;
	}

	/**
	 * @param b
	 */
	public void setGrabaEnBitacora(boolean b) {
		grabarEnBitacora = b;
	}
	public static PsVsOcVO getPsPrimario(ArrayList arrayList) throws TnProcesoExcepcion {
		PsVsOcVO psPrim = null;
		int size = arrayList.size();
		if(size==1)	{
			Iterator iterTemp = arrayList.iterator();
			//Obtengo el primer PS
			psPrim= (PsVsOcVO) iterTemp.next();
		}else{
			if(size>1) {
				Iterator iterTemp = arrayList.iterator();
				PsVsOcVO psTemp = null;
				while(iterTemp.hasNext()) {
					psTemp= (PsVsOcVO) iterTemp.next();
					if(psTemp.getOpComTipo()==null)
						continue;

					if(!psTemp.getOpComTipo().equals("A") && !psTemp.getOpComTipo().equals("ACP") 
							&& !psTemp.getOpComTipo().equals("B") && !psTemp.getOpComTipo().equals("BCP")
							)
						continue;
					psPrim = psTemp;
				}
				
				if (psPrim == null) {
					Iterator iter2Temp = arrayList.iterator();
					PsVsOcVO ps2Temp = null;
					while(iter2Temp.hasNext()) {
						ps2Temp= (PsVsOcVO) iter2Temp.next();
						if(ps2Temp.getOpComTipo()==null)
							continue;
//						REQ BA NAKED adicion familia PC naked
						if(ps2Temp.getFaPsId().intValue() == ComunInterfaces.familiaPcBA || ps2Temp.getFaPsId().intValue() == ComunInterfaces.familiaPcBANaked){
							psPrim = ps2Temp;
							break;
						}else if(ps2Temp.getFaPsId().intValue() == ComunInterfaces.familiaBandaAncha || ps2Temp.getFaPsId().intValue() == ComunInterfaces.familiaBandaAnchaNaked){
							psPrim = ps2Temp;
						}
					}
				}
				
				if (psPrim == null) {
					throw new TnProcesoExcepcion("No se encontro ps primario para 'A', 'ACP', 'B' o 'BCP' size=" + size);
				}
			
			} else {
				throw new TnProcesoExcepcion("No hay ps primario para ps=0");
			}
		}
		return psPrim;
		
	}

	// CR29928 - adocarmo - inicio
	/**
	 * @return Returns the sePublicoPeticion.
	 */
	public boolean getSePublicoPeticion() {
		return sePublicoPeticion;
	}
	/**
	 * @param sePublicoPeticion The sePublicoPeticion to set.
	 */
	public void setSePublicoPeticion(boolean sePublicoPeticion) {
		this.sePublicoPeticion = sePublicoPeticion;
	}
	// CR29928 - adocarmo - fin

}
