package co.com.telefonica.atiempo.vpistbba.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OdsListDTO
{
	private Long idPeticion;
	private InformacionPeticionDTO informacionPeticionDTO;
	private InformacionAdslDTO informacionAdslDTO;
	private InformacionAdslDTO informacionAdslActualDTO;
	private InformacionTecnicaDTO informacionTecnicaDTO;
	private Integer trasladoOn;
	private ArrayList listaAsignaciones;
	private String ods;
	private ArrayList listadoBitacora;
	private ArrayList listadoPS;
	private ArrayList listadoPC;
	private ArrayList listadoDecos;
	private InformacionTVDTO infoTv;
	private String cuentaCorreoPadre;
	private InfoPlanDTO infoPlanDTO;
	
	private String fechaVisita = "";
	private String horaIni = "";
	private String horaFin = "";
	
	/********Linea Nueva******/
	private String centralDes="";
	private String centralDesDesc="";
	private String direcCajaDes;
	private String parCajaDes;
	private String cajaDes;
	private String tipoCajaDes;
	private String direcDistDes;
	private String parCableDes;
	private String cableDes;
	private String parListonDes;
	private String listonDes;
	private String armarioDes;
	private String distDes;
	private String posicionHorizontalDes;
	private String lenAntDes;
	private String lenDes;
	private String numAbonDes;
	
	private String puertoDes;
	private String discDuroDes;
	private String memoriaRamDes;
	private String tarRedDes;
	private String ptoUsbDes;
	private String sisOpDes;
	private String numPcDes;
	private String ctaCorreoPaDes;
	private String tarjetaDes;
	private String vpivciRedDes;
	private String vpivciCliDes;
	private String frameDes;
	private String direcIpWanDes;
	private String direcIpLanCliDes;
	private String direcIpDslamDes;
	private String mascaraDes;
	private String adslDes;
	private String potsDes;
	/*************************/
	
	/******Linea Origen*************/
	private String centralOri;
	private String centralOriDesc;
	private String direcCajaOri;
	private String parCajaOri;
	private String cajaOri;
	private String tipoCajaOri;
	private String direcDistOri;
	private String parCableOri;
	private String cableOri;
	private String parListonOri;
	private String listonOri;
	private String armarioOri;
	private String distOri;
	private String posicionHorizontalOri;
	private String lenAntOri;
	private String lenOri;
	private String numAbonOri;
	
	private String puertoOri;
	private String discDuroOri;
	private String memoriaRamOri;
	private String tarRedOri;
	private String ptoUsbOri;
	private String sisOpOri;
	private String numPcOri;
	private String ctaCorreoPaOri;
	private String tarjetaOri;
	private String vpivciRedOri;
	private String vpivciCliOri;
	private String frameOri;
	private String direcIpWanOri;
	private String direcIpLanCliOri;
	private String direcIpDslamOri;
	private String mascaraOri;
	private String adslOri;
	private String potsOri;
	/*******************************/
	
	
	public OdsListDTO()
	{
		direcCajaDes="";
		parCajaDes="";
		cajaDes="";
		tipoCajaDes="";
		direcDistDes="";
		parCableDes="";
		cableDes="";
		parListonDes="";
		listonDes="";
		armarioDes="";
		distDes="";
		lenAntDes="";
		lenDes="";
		numAbonDes="";
		discDuroDes="";
		memoriaRamDes="";
		tarRedDes="";
		ptoUsbDes="";
		sisOpDes="";
		numPcDes="";
		ctaCorreoPaDes="";
		tarjetaDes="";
		vpivciRedDes="";
		vpivciCliDes="";
		frameDes="";
		direcIpWanDes="";
		direcIpLanCliDes="";
		direcIpDslamDes="";
		mascaraDes="";
		adslDes="";
		potsDes="";
		centralOri="";
		direcCajaOri="";
		parCajaOri="";
		cajaOri="";
		tipoCajaOri="";
		direcDistOri="";
		parCableOri="";
		cableOri="";
		parListonOri="";
		listonOri="";
		armarioOri="";
		distOri="";
		lenAntOri="";
		lenOri="";
		numAbonOri="";
		puertoOri="";
		discDuroOri="";
		memoriaRamOri="";
		tarRedOri="";
		ptoUsbOri="";
		sisOpOri="";
		numPcOri="";
		ctaCorreoPaOri="";
		tarjetaOri="";
		vpivciRedOri="";
		vpivciCliOri="";
		frameOri="";
		direcIpWanOri="";
		direcIpLanCliOri="";
		direcIpDslamOri="";
		mascaraOri="";
		adslOri="";
		potsOri="";
		cuentaCorreoPadre="";
		posicionHorizontalDes="";
		posicionHorizontalOri="";
	}
	public String getFechaVisita()
	{
		try
		{
			SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");	
			for (int i=0; listaAsignaciones!=null && i<listaAsignaciones.size(); i++)
			{
				InformacionAgendamientoDTO infoAgenda = (InformacionAgendamientoDTO) listaAsignaciones.get(i);
				if ( "Activo".equals(infoAgenda.getDescripcionEstado()) )
				{
					fechaVisita = sdfFecha.format(infoAgenda.getFechaCompromiso());
					horaIni = infoAgenda.getHoraDesde() ;
					horaFin = infoAgenda.getHoraHasta() ;
				}
			 }
			 return fechaVisita;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public void setIdPeticion(Long long1)
	{
		idPeticion=long1;
	}
	
	/**
	 * @return
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @return
	 */
	public InformacionAdslDTO getInformacionAdslActualDTO() {
		return informacionAdslActualDTO;
	}

	/**
	 * @return
	 */
	public InformacionAdslDTO getInformacionAdslDTO() {
		return informacionAdslDTO;
	}

	/**
	 * @return
	 */
	public InformacionPeticionDTO getInformacionPeticionDTO() {
		return informacionPeticionDTO;
	}

	/**
	 * @return
	 */
	public InformacionTecnicaDTO getInformacionTecnicaDTO() {
		return informacionTecnicaDTO;
	}

	/**
	 * @return
	 */
	public InformacionTVDTO getInfoTv() {
		return infoTv;
	}

	/**
	 * @return
	 */
	public ArrayList getListaAsignaciones() {
		return listaAsignaciones;
	}

	/**
	 * @return
	 */
	public ArrayList getListadoBitacora() {
		return listadoBitacora;
	}

	/**
	 * @return
	 */
	public ArrayList getListadoDecos() {
		return listadoDecos;
	}

	/**
	 * @return
	 */
	public ArrayList getListadoPC() {
		return listadoPC;
	}

	/**
	 * @return
	 */
	public ArrayList getListadoPS() {
		return listadoPS;
	}

	/**
	 * @return
	 */
	public String getOds() {
		return ods;
	}

	/**
	 * @param adslDTO
	 */
	public void setInformacionAdslActualDTO(InformacionAdslDTO adslDTO) {
		informacionAdslActualDTO = adslDTO;
		if ( informacionAdslActualDTO != null ) {
			this.puertoOri 			= 	informacionAdslActualDTO.getPuerto();
			this.potsOri			=	informacionAdslActualDTO.getPost();
			this.adslOri			= 	informacionAdslActualDTO.getAdsl();
			this.mascaraOri			=	informacionAdslActualDTO.getMascaraLan();
			this.direcIpDslamOri	=	informacionAdslActualDTO.getDirecIpDslam();
			this.direcIpLanCliOri	=	informacionAdslActualDTO.getDirecIpLan();
			this.direcIpWanOri		=	informacionAdslActualDTO.getDirecIpWan();
			this.frameOri			=	informacionAdslActualDTO.getFrame();
			this.vpivciCliOri		=	informacionAdslActualDTO.getVpiVciCliente();
			this.vpivciRedOri		=	informacionAdslActualDTO.getVpiVciRed();
			this.tarjetaOri			=	informacionAdslActualDTO.getSlot();
			this.ctaCorreoPaOri		= 	informacionAdslActualDTO.getUsuarioAcc();
			this.numPcOri			=	informacionAdslActualDTO.getNumPc();
			this.sisOpOri			=	informacionAdslActualDTO.getSistOp();
			this.ptoUsbOri			=	informacionAdslActualDTO.getNumUsb();
			this.tarRedOri			=	informacionAdslActualDTO.getNumTarj();
			this.memoriaRamOri		=	informacionAdslActualDTO.getRam();
			this.discDuroOri		=	informacionAdslActualDTO.getDiscoDuro();
		}
	}

	/**
	 * @param adslDTO
	 */
	public void setInformacionAdslDTO(InformacionAdslDTO adslDTO) {
		informacionAdslDTO = adslDTO;
		if ( informacionAdslDTO != null )
		{
			this.puertoDes 			= 	informacionAdslDTO.getPuerto();
			this.potsDes			=	informacionAdslDTO.getPost();
			this.adslDes			= 	informacionAdslDTO.getAdsl();
			this.mascaraDes			=	informacionAdslDTO.getMascaraLan();
			this.direcIpDslamDes	=	informacionAdslDTO.getDirecIpDslam();
			this.direcIpLanCliDes	=	informacionAdslDTO.getDirecIpLan();
			this.direcIpWanDes		=	informacionAdslDTO.getDirecIpWan();
			this.frameDes			=	informacionAdslDTO.getFrame();
			this.vpivciCliDes		=	informacionAdslDTO.getVpiVciCliente();
			this.vpivciRedDes		=	informacionAdslDTO.getVpiVciRed();
			this.tarjetaDes			=	informacionAdslDTO.getSlot();
			this.ctaCorreoPaDes		= 	informacionAdslDTO.getFatherEmail();
			this.numPcDes			=	informacionAdslDTO.getNumPc();
			this.sisOpDes			=	informacionAdslDTO.getSistOp();
			this.ptoUsbDes			=	informacionAdslDTO.getNumUsb();
			this.tarRedDes			=	informacionAdslDTO.getNumTarj(); //Numeros tarjetas de red
			this.memoriaRamDes		=	informacionAdslDTO.getRam();
			this.discDuroDes		=	informacionAdslDTO.getDiscoDuro();
		}		
	}

	/**
	 * @param peticionDTO
	 */
	public void setInformacionPeticionDTO(InformacionPeticionDTO peticionDTO) {
		informacionPeticionDTO = peticionDTO;
	}

	/**
	 * @param tecnicaDTO
	 */
	public void setInformacionTecnicaDTO(InformacionTecnicaDTO tecnicaDTO) {
		this.informacionTecnicaDTO = tecnicaDTO;
		if (this.trasladoOn!=null && this.trasladoOn.intValue() > 0){
			//Si es traslado SOLO BA
			if(this.trasladoOn.intValue()==1){ // alta
				if(this.informacionTecnicaDTO.LineaNueva!=null){
					//Seteo para planta interna de destino
					if(this.informacionTecnicaDTO.LineaNueva.getCdCentral()!=null){
						this.centralDes	=	this.informacionTecnicaDTO.LineaNueva.getCdCentral().toString();
						this.centralDesDesc = this.informacionTecnicaDTO.LineaNueva.getCentral();
					}
					this.numAbonDes		=	this.informacionTecnicaDTO.LineaNueva.getTelefono();
					this.lenDes			=	this.informacionTecnicaDTO.LineaNueva.getLen();
					this.lenAntDes		=	"";
					
					this.posicionHorizontalDes=this.informacionTecnicaDTO.LineaNueva.getPosicionHorizontal();
					
					//Seteo para planta externa de destino
					this.distDes		=	this.informacionTecnicaDTO.LineaNueva.getDistr();
					this.armarioDes		=	this.informacionTecnicaDTO.LineaNueva.getArmario();
					this.listonDes		=	this.informacionTecnicaDTO.LineaNueva.getListon();
					this.parListonDes	=	this.informacionTecnicaDTO.LineaNueva.getParListon();
					this.cableDes		=	this.informacionTecnicaDTO.LineaNueva.getCable();
					this.parCableDes	=	this.informacionTecnicaDTO.LineaNueva.getParCable();
					this.direcDistDes	=	this.informacionTecnicaDTO.LineaNueva.getDirecDistr();
					this.tipoCajaDes	=	"";
					this.cajaDes 		= 	this.informacionTecnicaDTO.LineaNueva.getCaja();
					this.parCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getParCaja();
					this.direcCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getDirecCaja();				
				}
			}else{//baja
				if(this.informacionTecnicaDTO.LineaNueva!=null){
					if(this.informacionTecnicaDTO.LineaNueva.getCdCentral()!=null){
						this.centralOri	=	this.informacionTecnicaDTO.LineaNueva.getCdCentral().toString();
						this.centralOriDesc	=	this.informacionTecnicaDTO.LineaNueva.getCentral();
					}
					this.numAbonOri		=	this.informacionTecnicaDTO.LineaNueva.getTelefono();
					this.lenOri			=	this.informacionTecnicaDTO.LineaNueva.getLen();
					this.lenAntOri		=	"";
					
					this.posicionHorizontalOri=this.informacionTecnicaDTO.LineaNueva.getPosicionHorizontal();
					
					//Seteo para planta externa de origen
					this.distOri		=	this.informacionTecnicaDTO.LineaNueva.getDistr();
					this.armarioOri		=	this.informacionTecnicaDTO.LineaNueva.getArmario();
					this.listonOri		=	this.informacionTecnicaDTO.LineaNueva.getListon();
					this.parListonOri	=	this.informacionTecnicaDTO.LineaNueva.getParListon();
					this.cableOri		=	this.informacionTecnicaDTO.LineaNueva.getCable();
					this.parCableOri	=	this.informacionTecnicaDTO.LineaNueva.getParCable();
					this.direcDistOri	=	this.informacionTecnicaDTO.LineaNueva.getDirecDistr();
					this.tipoCajaOri	=	"";
					this.cajaOri 		= 	this.informacionTecnicaDTO.LineaNueva.getCaja();
					this.parCajaOri 	= 	this.informacionTecnicaDTO.LineaNueva.getParCaja();
					this.direcCajaOri 	= 	this.informacionTecnicaDTO.LineaNueva.getDirecCaja();
				} 
			}
			
		}else{
			//si no tiene identificador es un alta de linea nueva
			if (this.informacionPeticionDTO.getIdentificadorOriLinea()==null || "".equals(this.informacionPeticionDTO.getIdentificadorOriLinea().trim())){
				//Seteo para planta interna de destino
				if(this.informacionTecnicaDTO.LineaNueva.getCdCentral()!=null){
					this.centralDes	=	this.informacionTecnicaDTO.LineaNueva.getCdCentral().toString();
					this.centralDesDesc	=	this.informacionTecnicaDTO.LineaNueva.getCentral();
				}
				this.numAbonDes		=	this.informacionTecnicaDTO.LineaNueva.getTelefono();
				this.lenDes			=	this.informacionTecnicaDTO.LineaNueva.getLen();
				this.lenAntDes		=	"";
				
				this.posicionHorizontalDes=this.informacionTecnicaDTO.LineaNueva.getPosicionHorizontal();
				
				//Seteo para planta externa de destino
				this.distDes		=	this.informacionTecnicaDTO.LineaNueva.getDistr();
				this.armarioDes		=	this.informacionTecnicaDTO.LineaNueva.getArmario();
				this.listonDes		=	this.informacionTecnicaDTO.LineaNueva.getListon();
				this.parListonDes	=	this.informacionTecnicaDTO.LineaNueva.getParListon();
				this.cableDes		=	this.informacionTecnicaDTO.LineaNueva.getCable();
				this.parCableDes	=	this.informacionTecnicaDTO.LineaNueva.getParCable();
				this.direcDistDes	=	this.informacionTecnicaDTO.LineaNueva.getDirecDistr();
				this.tipoCajaDes	=	"";
				this.cajaDes 		= 	this.informacionTecnicaDTO.LineaNueva.getCaja();
				this.parCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getParCaja();
				this.direcCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getDirecCaja();					
			}else{
				//si es sobre linea existente
				if(this.informacionTecnicaDTO.LineaNueva!=null && this.informacionTecnicaDTO.LineaExistente==null)
				{
					//Este seria una baja
					//Seteo para planta interna de origen
					if(this.informacionTecnicaDTO.LineaNueva.getCdCentral()!=null){
						this.centralOri	=	this.informacionTecnicaDTO.LineaNueva.getCdCentral().toString();
						this.centralOriDesc	=	this.informacionTecnicaDTO.LineaNueva.getCentral();
					}
					this.numAbonOri		=	this.informacionTecnicaDTO.LineaNueva.getTelefono();
					this.lenOri			=	this.informacionTecnicaDTO.LineaNueva.getLen();
					this.lenAntOri		=	"";
					
					this.posicionHorizontalOri=this.informacionTecnicaDTO.LineaNueva.getPosicionHorizontal();
					
					//Seteo para planta externa de origen
					this.distOri		=	this.informacionTecnicaDTO.LineaNueva.getDistr();
					this.armarioOri		=	this.informacionTecnicaDTO.LineaNueva.getArmario();
					this.listonOri		=	this.informacionTecnicaDTO.LineaNueva.getListon();
					this.parListonOri	=	this.informacionTecnicaDTO.LineaNueva.getParListon();
					this.cableOri		=	this.informacionTecnicaDTO.LineaNueva.getCable();
					this.parCableOri	=	this.informacionTecnicaDTO.LineaNueva.getParCable();
					this.direcDistOri	=	this.informacionTecnicaDTO.LineaNueva.getDirecDistr();
					this.tipoCajaOri	=	"";
					this.cajaOri 		= 	this.informacionTecnicaDTO.LineaNueva.getCaja();
					this.parCajaOri 	= 	this.informacionTecnicaDTO.LineaNueva.getParCaja();
					this.direcCajaOri 	= 	this.informacionTecnicaDTO.LineaNueva.getDirecCaja(); 
		

				}	
				if(this.informacionTecnicaDTO.LineaNueva!=null && informacionTecnicaDTO.LineaExistente != null)
				{
					//Esto seria un traslado
					//Seteo para planta interna de origen
					if(this.informacionTecnicaDTO.LineaExistente.getCdCentral()!=null){
						this.centralOri	=	this.informacionTecnicaDTO.LineaExistente.getCdCentral().toString();
						this.centralOriDesc	=	this.informacionTecnicaDTO.LineaExistente.getCentral();
					}
					this.numAbonOri		=	this.informacionTecnicaDTO.LineaExistente.getTelefono();
					this.lenOri			=	this.informacionTecnicaDTO.LineaExistente.getLen();
					this.lenAntOri		=	"";
					this.posicionHorizontalOri=this.informacionTecnicaDTO.LineaExistente.getPosicionHorizontal();
					//Seteo para planta externa de origen
					this.distOri		=	this.informacionTecnicaDTO.LineaExistente.getDistr();
					this.armarioOri		=	this.informacionTecnicaDTO.LineaExistente.getArmario();
					this.listonOri		=	this.informacionTecnicaDTO.LineaExistente.getListon();
					this.parListonOri	=	this.informacionTecnicaDTO.LineaExistente.getParListon();
					this.cableOri		=	this.informacionTecnicaDTO.LineaExistente.getCable();
					this.parCableOri	=	this.informacionTecnicaDTO.LineaExistente.getParCable();
					this.direcDistOri	=	this.informacionTecnicaDTO.LineaExistente.getDirecDistr();
					this.tipoCajaOri	=	"";
					this.cajaOri 		= 	this.informacionTecnicaDTO.LineaExistente.getCaja();
					this.parCajaOri 	= 	this.informacionTecnicaDTO.LineaExistente.getParCaja();
					this.direcCajaOri 	= 	this.informacionTecnicaDTO.LineaExistente.getDirecCaja(); 

					//Seteo para planta interna de destino
					if(this.informacionTecnicaDTO.LineaNueva.getCdCentral()!=null){
						this.centralDes	=	this.informacionTecnicaDTO.LineaNueva.getCdCentral().toString();
						this.centralDesDesc	=	this.informacionTecnicaDTO.LineaNueva.getCentral();
					}
					this.numAbonDes		=	this.informacionTecnicaDTO.LineaNueva.getTelefono();
					this.lenDes			=	this.informacionTecnicaDTO.LineaNueva.getLen();
					this.lenAntDes		=	"";
					this.posicionHorizontalDes=this.informacionTecnicaDTO.LineaNueva.getPosicionHorizontal();
					//Seteo para planta externa de destino
					this.distDes		=	this.informacionTecnicaDTO.LineaNueva.getDistr();
					this.armarioDes		=	this.informacionTecnicaDTO.LineaNueva.getArmario();
					this.listonDes		=	this.informacionTecnicaDTO.LineaNueva.getListon();
					this.parListonDes	=	this.informacionTecnicaDTO.LineaNueva.getParListon();
					this.cableDes		=	this.informacionTecnicaDTO.LineaNueva.getCable();
					this.parCableDes	=	this.informacionTecnicaDTO.LineaNueva.getParCable();
					this.direcDistDes	=	this.informacionTecnicaDTO.LineaNueva.getDirecDistr();
					this.tipoCajaDes	=	"";
					this.cajaDes 		= 	this.informacionTecnicaDTO.LineaNueva.getCaja();
					this.parCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getParCaja();
					this.direcCajaDes 	= 	this.informacionTecnicaDTO.LineaNueva.getDirecCaja();			
				}
			}
		}
	}

	/**
	 * @param informacionTVDTO
	 */
	public void setInfoTv(InformacionTVDTO informacionTVDTO) {
		infoTv = informacionTVDTO;
	}

	/**
	 * @param list
	 */
	public void setListaAsignaciones(ArrayList list) {
		listaAsignaciones = list;
	}

	/**
	 * @param list
	 */
	public void setListadoBitacora(ArrayList list) {
		listadoBitacora = list;
	}

	/**
	 * @param list
	 */
	public void setListadoDecos(ArrayList list) {
		listadoDecos = list;
	}

	/**
	 * @param list
	 */
	public void setListadoPC(ArrayList list) {
		listadoPC = list;
	}

	/**
	 * @param list
	 */
	public void setListadoPS(ArrayList list) {
		listadoPS = list;
	}

	/**
	 * @param string
	 */
	public void setOds(String string) {
		ods = string;
	}

	/**
	 * @return
	 */
	public Integer getTrasladoOn() {
		return trasladoOn;
	}

	/**
	 * @param integer
	 */
	public void setTrasladoOn(Integer integer) {
		trasladoOn = integer;
	}

	/**
	 * @return
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @return
	 */
	public String getHoraIni() {
		return horaIni;
	}

	/**
	 * @param string
	 */
	public void setHoraFin(String string) {
		horaFin = string;
	}

	/**
	 * @param string
	 */
	public void setHoraIni(String string) {
		horaIni = string;
	}

	/**
	 * @return
	 */
	public String getCentralDes()
	{
		return centralDes;
	}

	/**
	 * @return
	 */
	public String getArmarioDes() {
		return armarioDes;
	}

	/**
	 * @return
	 */
	public String getCableDes() {
		return cableDes;
	}

	/**
	 * @return
	 */
	public String getCajaDes() {
		return cajaDes;
	}

	/**
	 * @return
	 */
	public String getDirecCajaDes() {
		return direcCajaDes;
	}

	/**
	 * @return
	 */
	public String getDirecDistDes() {
		return direcDistDes;
	}

	/**
	 * @return
	 */
	public String getDistDes() {
		return distDes;
	}

	/**
	 * @return
	 */
	public String getLenAntDes() {
		return lenAntDes;
	}

	/**
	 * @return
	 */
	public String getLenDes() {
		return lenDes;
	}

	/**
	 * @return
	 */
	public String getListonDes() {
		return listonDes;
	}

	/**
	 * @return
	 */
	public String getNumAbonDes() {
		return numAbonDes;
	}

	/**
	 * @return
	 */
	public String getParCableDes() {
		return parCableDes;
	}

	/**
	 * @return
	 */
	public String getParCajaDes() {
		return parCajaDes;
	}

	/**
	 * @return
	 */
	public String getParListonDes() {
		return parListonDes;
	}

	/**
	 * @return
	 */
	public String getTipoCajaDes() {
		return tipoCajaDes;
	}

	/**
	 * @param string
	 */
	public void setArmarioDes(String string) {
		armarioDes = string;
	}

	/**
	 * @param string
	 */
	public void setCableDes(String string) {
		cableDes = string;
	}

	/**
	 * @param string
	 */
	public void setCajaDes(String string) {
		cajaDes = string;
	}

	/**
	 * @param string
	 */
	public void setCentralDes(String string) {
		centralDes = string;
	}

	/**
	 * @param string
	 */
	public void setDirecCajaDes(String string) {
		direcCajaDes = string;
	}

	/**
	 * @param string
	 */
	public void setDirecDistDes(String string) {
		direcDistDes = string;
	}

	/**
	 * @param string
	 */
	public void setDistDes(String string) {
		distDes = string;
	}

	/**
	 * @param string
	 */
	public void setFechaVisita(String string) {
		fechaVisita = string;
	}

	/**
	 * @param object
	 */
	public void setLenAntDes(String object) {
		lenAntDes = object;
	}

	/**
	 * @param string
	 */
	public void setLenDes(String string) {
		lenDes = string;
	}

	/**
	 * @param string
	 */
	public void setListonDes(String string) {
		listonDes = string;
	}

	/**
	 * @param string
	 */
	public void setNumAbonDes(String string) {
		numAbonDes = string;
	}

	/**
	 * @param string
	 */
	public void setParCableDes(String string) {
		parCableDes = string;
	}

	/**
	 * @param string
	 */
	public void setParCajaDes(String string) {
		parCajaDes = string;
	}

	/**
	 * @param string
	 */
	public void setParListonDes(String string) {
		parListonDes = string;
	}

	/**
	 * @param string
	 */
	public void setTipoCajaDes(String string) {
		tipoCajaDes = string;
	}

	/**
	 * @return
	 */
	public String getPuertoDes()
	{
		return puertoDes;
	}
	/**
	 * @return
	 */
	public String getAdslDes() {
		return adslDes;
	}

	/**
	 * @return
	 */
	public String getCtaCorreoPaDes() {
		return ctaCorreoPaDes;
	}

	/**
	 * @return
	 */
	public String getDirecIpDslamDes() {
		return direcIpDslamDes;
	}

	/**
	 * @return
	 */
	public String getDirecIpLanCliDes() {
		return direcIpLanCliDes;
	}

	/**
	 * @return
	 */
	public String getDirecIpWanDes() {
		return direcIpWanDes;
	}

	/**
	 * @return
	 */
	public String getDiscDuroDes() {
		return discDuroDes;
	}

	/**
	 * @return
	 */
	public String getFrameDes() {
		return frameDes;
	}

	/**
	 * @return
	 */
	public String getMascaraDes() {
		return mascaraDes;
	}

	/**
	 * @return
	 */
	public String getMemoriaRamDes() {
		return memoriaRamDes;
	}

	/**
	 * @return
	 */
	public String getNumPcDes() {
		return numPcDes;
	}

	/**
	 * @return
	 */
	public String getPotsDes() {
		return potsDes;
	}

	/**
	 * @return
	 */
	public String getPtoUsbDes() {
		return ptoUsbDes;
	}

	/**
	 * @return
	 */
	public String getSisOpDes() {
		return sisOpDes;
	}

	/**
	 * @return
	 */
	public String getTarjetaDes() {
		return tarjetaDes;
	}

	/**
	 * @return
	 */
	public String getTarRedDes() {
		return tarRedDes;
	}

	/**
	 * @return
	 */
	public String getVpivciCliDes() {
		return vpivciCliDes;
	}

	/**
	 * @return
	 */
	public String getVpivciRedDes() {
		return vpivciRedDes;
	}

	/**
	 * @param string
	 */
	public void setAdslDes(String string) {
		adslDes = string;
	}

	/**
	 * @param string
	 */
	public void setCtaCorreoPaDes(String string) {
		ctaCorreoPaDes = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpDslamDes(String string) {
		direcIpDslamDes = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpLanCliDes(String string) {
		direcIpLanCliDes = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpWanDes(String string) {
		direcIpWanDes = string;
	}

	/**
	 * @param string
	 */
	public void setDiscDuroDes(String string) {
		discDuroDes = string;
	}

	/**
	 * @param string
	 */
	public void setFrameDes(String string) {
		frameDes = string;
	}

	/**
	 * @param string
	 */
	public void setMascaraDes(String string) {
		mascaraDes = string;
	}

	/**
	 * @param string
	 */
	public void setMemoriaRamDes(String string) {
		memoriaRamDes = string;
	}

	/**
	 * @param string
	 */
	public void setNumPcDes(String string) {
		numPcDes = string;
	}

	/**
	 * @param string
	 */
	public void setPotsDes(String string) {
		potsDes = string;
	}

	/**
	 * @param string
	 */
	public void setPtoUsbDes(String string) {
		ptoUsbDes = string;
	}

	/**
	 * @param string
	 */
	public void setPuertoDes(String string) {
		puertoDes = string;
	}

	/**
	 * @param string
	 */
	public void setSisOpDes(String string) {
		sisOpDes = string;
	}

	/**
	 * @param string
	 */
	public void setTarjetaDes(String string) {
		tarjetaDes = string;
	}

	/**
	 * @param string
	 */
	public void setTarRedDes(String string) {
		tarRedDes = string;
	}

	/**
	 * @param string
	 */
	public void setVpivciCliDes(String string) {
		vpivciCliDes = string;
	}

	/**
	 * @param string
	 */
	public void setVpivciRedDes(String string) {
		vpivciRedDes = string;
	}

	/**
	 * @return
	 */
	public String getCentralOri()
	{
		return centralOri;
	}

	/**
	 * @return
	 */
	public String getArmarioOri() {
		return armarioOri;
	}

	/**
	 * @return
	 */
	public String getCableOri() {
		return cableOri;
	}

	/**
	 * @return
	 */
	public String getCajaOri() {
		return cajaOri;
	}

	/**
	 * @return
	 */
	public String getDirecCajaOri() {
		return direcCajaOri;
	}

	/**
	 * @return
	 */
	public String getDirecDistOri() {
		return direcDistOri;
	}

	/**
	 * @return
	 */
	public String getDistOri() {
		return distOri;
	}

	/**
	 * @return
	 */
	public String getLenAntOri() {
		return lenAntOri;
	}

	/**
	 * @return
	 */
	public String getLenOri() {
		return lenOri;
	}

	/**
	 * @return
	 */
	public String getListonOri() {
		return listonOri;
	}

	/**
	 * @return
	 */
	public String getNumAbonOri() {
		return numAbonOri;
	}

	/**
	 * @return
	 */
	public String getParCableOri() {
		return parCableOri;
	}

	/**
	 * @return
	 */
	public String getParCajaOri() {
		return parCajaOri;
	}

	/**
	 * @return
	 */
	public String getParListonOri() {
		return parListonOri;
	}

	/**
	 * @return
	 */
	public String getTipoCajaOri() {
		return tipoCajaOri;
	}

	/**
	 * @param string
	 */
	public void setArmarioOri(String string) {
		armarioOri = string;
	}

	/**
	 * @param string
	 */
	public void setCableOri(String string) {
		cableOri = string;
	}

	/**
	 * @param string
	 */
	public void setCajaOri(String string) {
		cajaOri = string;
	}

	/**
	 * @param string
	 */
	public void setCentralOri(String string) {
		centralOri = string;
	}

	/**
	 * @param string
	 */
	public void setDirecCajaOri(String string) {
		direcCajaOri = string;
	}

	/**
	 * @param string
	 */
	public void setDirecDistOri(String string) {
		direcDistOri = string;
	}

	/**
	 * @param string
	 */
	public void setDistOri(String string) {
		distOri = string;
	}

	/**
	 * @param string
	 */
	public void setLenAntOri(String string) {
		lenAntOri = string;
	}

	/**
	 * @param string
	 */
	public void setLenOri(String string) {
		lenOri = string;
	}

	/**
	 * @param string
	 */
	public void setListonOri(String string) {
		listonOri = string;
	}

	/**
	 * @param string
	 */
	public void setNumAbonOri(String string) {
		numAbonOri = string;
	}

	/**
	 * @param string
	 */
	public void setParCableOri(String string) {
		parCableOri = string;
	}

	/**
	 * @param string
	 */
	public void setParCajaOri(String string) {
		parCajaOri = string;
	}

	/**
	 * @param string
	 */
	public void setParListonOri(String string) {
		parListonOri = string;
	}

	/**
	 * @param string
	 */
	public void setTipoCajaOri(String string) {
		tipoCajaOri = string;
	}

	/**
	 * @return
	 */
	public String getPuertoOri()
	{
		return puertoOri;
	}

	/**
	 * @return
	 */
	public String getAdslOri() {
		return adslOri;
	}

	/**
	 * @return
	 */
	public String getCtaCorreoPaOri() {
		return ctaCorreoPaOri;
	}

	/**
	 * @return
	 */
	public String getDirecIpDslamOri() {
		return direcIpDslamOri;
	}

	/**
	 * @return
	 */
	public String getDirecIpLanCliOri() {
		return direcIpLanCliOri;
	}

	/**
	 * @return
	 */
	public String getDirecIpWanOri() {
		return direcIpWanOri;
	}

	/**
	 * @return
	 */
	public String getDiscDuroOri() {
		return discDuroOri;
	}

	/**
	 * @return
	 */
	public String getFrameOri() {
		return frameOri;
	}

	/**
	 * @return
	 */
	public String getMascaraOri() {
		return mascaraOri;
	}

	/**
	 * @return
	 */
	public String getMemoriaRamOri() {
		return memoriaRamOri;
	}

	/**
	 * @return
	 */
	public String getNumPcOri() {
		return numPcOri;
	}

	/**
	 * @return
	 */
	public String getPotsOri() {
		return potsOri;
	}

	/**
	 * @return
	 */
	public String getPtoUsbOri() {
		return ptoUsbOri;
	}

	/**
	 * @return
	 */
	public String getSisOpOri() {
		return sisOpOri;
	}

	/**
	 * @return
	 */
	public String getTarjetaOri() {
		return tarjetaOri;
	}

	/**
	 * @return
	 */
	public String getTarRedOri() {
		return tarRedOri;
	}

	/**
	 * @return
	 */
	public String getVpivciCliOri() {
		return vpivciCliOri;
	}

	/**
	 * @return
	 */
	public String getVpivciRedOri() {
		return vpivciRedOri;
	}

	/**
	 * @param string
	 */
	public void setAdslOri(String string) {
		adslOri = string;
	}

	/**
	 * @param string
	 */
	public void setCtaCorreoPaOri(String string) {
		ctaCorreoPaOri = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpDslamOri(String string) {
		direcIpDslamOri = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpLanCliOri(String string) {
		direcIpLanCliOri = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpWanOri(String string) {
		direcIpWanOri = string;
	}

	/**
	 * @param string
	 */
	public void setDiscDuroOri(String string) {
		discDuroOri = string;
	}

	/**
	 * @param string
	 */
	public void setFrameOri(String string) {
		frameOri = string;
	}

	/**
	 * @param string
	 */
	public void setMascaraOri(String string) {
		mascaraOri = string;
	}

	/**
	 * @param string
	 */
	public void setMemoriaRamOri(String string) {
		memoriaRamOri = string;
	}

	/**
	 * @param string
	 */
	public void setNumPcOri(String string) {
		numPcOri = string;
	}

	/**
	 * @param string
	 */
	public void setPotsOri(String string) {
		potsOri = string;
	}

	/**
	 * @param string
	 */
	public void setPtoUsbOri(String string) {
		ptoUsbOri = string;
	}

	/**
	 * @param string
	 */
	public void setPuertoOri(String string) {
		puertoOri = string;
	}

	/**
	 * @param string
	 */
	public void setSisOpOri(String string) {
		sisOpOri = string;
	}

	/**
	 * @param string
	 */
	public void setTarjetaOri(String string) {
		tarjetaOri = string;
	}

	/**
	 * @param string
	 */
	public void setTarRedOri(String string) {
		tarRedOri = string;
	}

	/**
	 * @param string
	 */
	public void setVpivciCliOri(String string) {
		vpivciCliOri = string;
	}

	/**
	 * @param string
	 */
	public void setVpivciRedOri(String string) {
		vpivciRedOri = string;
	}

	/**
	 * @return
	 */
	public String getCuentaCorreoPadre() {
		return cuentaCorreoPadre;
	}

	/**
	 * @param string
	 */
	public void setCuentaCorreoPadre(String string) {
		cuentaCorreoPadre = string;
	}
	/**
	 * @return
	 */
	public String getCuentaCorreoPadreOri()
	{
		if(trasladoOn!=null && trasladoOn.intValue()!=0)
			return cuentaCorreoPadre;
		return "";
	}

	/**
	 * @return
	 */
	public InfoPlanDTO getInfoPlanDTO() {
		return infoPlanDTO;
	}

	/**
	 * @param planDTO
	 */
	public void setInfoPlanDTO(InfoPlanDTO planDTO) {
		infoPlanDTO = planDTO;
	}

	/**
	 * @return
	 */
	public String getCentralDesDesc() {
		return centralDesDesc;
	}

	/**
	 * @return
	 */
	public String getCentralOriDesc() {
		return centralOriDesc;
	}

	/**
	 * @param string
	 */
	public void setCentralDesDesc(String string) {
		centralDesDesc = string;
	}

	/**
	 * @param string
	 */
	public void setCentralOriDesc(String string) {
		centralOriDesc = string;
	}

	/**
	 * @return
	 */
	public String getPosicionHorizontalDes() {
		return posicionHorizontalDes;
	}

	/**
	 * @return
	 */
	public String getPosicionHorizontalOri() {
		return posicionHorizontalOri;
	}

	/**
	 * @param string
	 */
	public void setPosicionHorizontalDes(String string) {
		posicionHorizontalDes = string;
	}

	/**
	 * @param string
	 */
	public void setPosicionHorizontalOri(String string) {
		posicionHorizontalOri = string;
	}

}
