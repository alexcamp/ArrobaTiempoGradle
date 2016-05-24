/*
 * Created on 28-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR010E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author TCS
 * 
 * Mensaje: TR_010_E
 */
public class AsignacionRecursosSTBMQ extends QManagerService {

	private static final String QUEUE = "jms/QMAT/IT_BUS_ASIG_REC_LB_ALTA_Q";

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 */
	public AsignacionRecursosSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_010_E
		if (obj instanceof TR010E) {
			TR010E aux = (TR010E) obj;
			aux.setClientDocument(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getClientDocument())));
			aux.setClientName(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getClientName())));
			aux.setFirstPathCharacters(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getFirstPathCharacters())));
			aux.setFirstPathCharacters2(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getFirstPathCharacters2())));
			aux.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getId())));
			aux.setInstallAddress(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getInstallAddress())));
			aux.setPathType(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPathType())));
			aux.setPathType2(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPathType2())));
			aux.setPathZone(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPathZone())));
			aux.setPlaceNumber1(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceNumber1())));
			aux.setPlaceNumber2(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceNumber2())));
			aux.setPlaceNumber3(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceNumber3())));
			aux.setPlaceType1(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceType1())));
			aux.setPlaceType2(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceType2())));
			aux.setPlaceType3(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPlaceType3())));
			aux.setSecondPathCharacters(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getSecondPathCharacters())));
			aux.setSecondPathCharacters2(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getSecondPathCharacters2())));

			aux.setAddressCharacters(Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getAddressCharacters())) );
			aux.setPathZone2( Utiles.ConversorGuion(filtraInvalidaCharacter(aux.getPathZone2())) );
		}
		return obj;
	}

}
