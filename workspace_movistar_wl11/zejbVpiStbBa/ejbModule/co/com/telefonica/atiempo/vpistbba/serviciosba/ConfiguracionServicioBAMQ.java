/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import java.util.Iterator;

import co.com.telefonica.atiempo.interfaces.atiempo.Dslam3;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionServicioBAMQ extends QManagerService {
	
	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_RECU_BA_Q";

	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public ConfiguracionServicioBAMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR013E) {
			TR013E tr013e = (TR013E) obj;

			//tr013e.setAtisRequestNumber(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getAtisRequestNumber())));
			tr013e.setClientDocument(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientDocument())));
			tr013e.setClientDocumentType(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientDocumentType())));
			tr013e.setAddress(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getAddress())));
			tr013e.setClientType(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientType())));
			tr013e.setClientName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientName())));
			tr013e.setClientFirstLastName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientFirstLastName())));
			tr013e.setClientSecondLastName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getClientSecondLastName())));
			tr013e.setContactPhone(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getContactPhone())));
			tr013e.setContactName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getContactName())));
			tr013e.setContactFirstLastName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getContactFirstLastName())));
			tr013e.setContactSecondLastName(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getContactSecondLastName())));
			//tr013e.setPhoneNumber(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getPhoneNumber())));
			//tr013e.setPreviousServicePhone(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getPreviousServicePhone())));
			//tr013e.setProductServiceCode(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getPhoneNumber())));
			//tr013e.setComercialOperationType(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getComercialOperationType())));
			tr013e.setCityCode(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getCityCode())));
						
			//tr013e.setDslams(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getContactSecondLastName())));
			for(Iterator iterator=tr013e.getDslams().iterator();iterator.hasNext();)
			{
				Dslam3 dslam3=(Dslam3) iterator.next();
				dslam3.setIp(Utiles.ConversorGuion(filtraInvalidaCharacter(dslam3.getIp())));
//				dslam3.setDslamType(Utiles.ConversorGuion(filtraInvalidaCharacter(dslam3.getDslamType()))); 
			}
			tr013e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr013e.getId())));
			
//			tr013e.setServicePhone()
		}
		return obj;
	}





}
