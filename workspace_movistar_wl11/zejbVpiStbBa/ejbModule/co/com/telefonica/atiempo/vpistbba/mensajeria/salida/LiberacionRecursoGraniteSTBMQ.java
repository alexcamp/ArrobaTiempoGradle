package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR513E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

public class LiberacionRecursoGraniteSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_LIB_REC_STB_GR_Q";
	
	public LiberacionRecursoGraniteSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}


	protected Object procesarDatos(Object obj)
	{
		if (obj instanceof TR513E)
		{
			TR513E tr513e = (TR513E) obj;
			tr513e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr513e.getId())));
		}
		return obj;
	}

}
