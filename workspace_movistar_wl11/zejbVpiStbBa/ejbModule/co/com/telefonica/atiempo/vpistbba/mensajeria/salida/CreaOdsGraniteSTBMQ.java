package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR513E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR517E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

public class CreaOdsGraniteSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CREAR_ODS_Q";
	
	public CreaOdsGraniteSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}


	protected Object procesarDatos(Object obj)
	{
		if (obj instanceof TR517E)
		{
			TR517E tr517e = (TR517E) obj;
			tr517e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr517e.getId())));
		}
		return obj;
	}

}
