package co.com.telefonica.atiempo.sigres.emu.tr.impl.marav;

import co.com.telefonica.atiempo.sigres.emu.tr.TRResponse_ST;

public class MARAV_RespuestaGR extends TRResponse_ST{
	public static final String SERVICE_CLASS_NAME = "co.com.telefonica.atiempo.soltec.servicios.SolicitudMarcaLineaSTBGRServicio";
	
			public MARAV_RespuestaGR(String message){
				this.serviceClassName = SERVICE_CLASS_NAME;
				this.setMessage(message);
			}
}
