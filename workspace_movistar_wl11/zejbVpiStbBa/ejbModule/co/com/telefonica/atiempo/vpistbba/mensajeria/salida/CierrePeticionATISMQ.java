/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import java.util.Iterator;

import co.com.telefonica.atiempo.interfaces.atiempo.Group1;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_001_E
 */
public class CierrePeticionATISMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_CIERRE_TECNICO_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public CierrePeticionATISMQ() throws ATiempoAppEx {
		super(QUEUE);
	}
	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj)
	{
		//TODO: continuar chequeando aqui
		if (obj instanceof TR001E)
		{
			TR001E tr001e = (TR001E) obj;
			tr001e.setErrorMessage(Utiles.ConversorGuion(filtraInvalidaCharacter(tr001e.getErrorMessage())));
			tr001e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr001e.getId())));
			//tr001e.setIdE(Utiles.ConversorGuion(filtraInvalidaCharacter(tr001e.getIdE())));
//			tr001e.setErrorMessage(Utiles.ConversorGuion(filtraInvalidaCharacter(tr001e.getErrorMessage())));
			for(Iterator iterator=tr001e.getGroups().iterator();iterator.hasNext();)
			{
				Group1 group=(Group1) iterator.next();
				//group.setComercialProductIdNumber(Utiles.ConversorGuion(filtraInvalidaCharacter(group.getComercialProductIdNumber())));
			}
		}
		return obj;
	}

}
