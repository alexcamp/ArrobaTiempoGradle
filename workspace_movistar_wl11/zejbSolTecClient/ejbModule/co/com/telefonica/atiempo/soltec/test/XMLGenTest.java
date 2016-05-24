/*
 * Created on 09-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.test;

import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author alevera
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class XMLGenTest {

	public static String generaTR005STest() throws ATiempoAppEx, ParseException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");
		TR005S tr005s=new TR005S();
		tr005s.setBreakdownNumber(50);
		tr005s.setClientCode(300000000);
		tr005s.setProductServiceCode(360000000);
		tr005s.setComercialProductIdentificationNumber("L");
		tr005s.setComercialProductIdentification("15642603");
		tr005s.setBreakdownOpenCode("117B3");
		tr005s.setBreakdownCloseCode("");
		tr005s.setBreakdownSymptomCode("");
		tr005s.setBreakdownTestResponseCode("");
		tr005s.setManagementAreaCode(18);
		tr005s.setBreakdownStatusCode('A');
		tr005s.setRequestNumber(0);
		tr005s.setMassiveBreakdownCode(0);
		tr005s.setBreakdownOriginalCode(0);
		tr005s.setClimantDocumentType("CC");
		tr005s.setClimantDocumentNumber("20142673");
		tr005s.setClimantDocumentVerification(" ");
		tr005s.setClimantName("ARIAS CORTES FLOR DE");
		tr005s.setClimantFirstLastname("-");
		tr005s.setClimantSecondLastname(" ");
		tr005s.setClientRelationshipCode("CL");
		tr005s.setClimantRelationshipOtherDescription(" ");
		tr005s.setNotificationWayCode(' ');
		tr005s.setContactName("TEST INTERFASES");
		tr005s.setContactPhone("11112222");
		tr005s.setContactName2("TEST INTERFASES");
		tr005s.setContactPhone2("66667777");
		tr005s.setCommunicationMediaCode('C');
		tr005s.setCategoryCode(" ");
		tr005s.setBreakdownPriorityCode('B');
		tr005s.setMaxResolutionTerm(2640);
		tr005s.setDaysToAlarm(1);
		tr005s.setAnswerIndicator('N');
		tr005s.setCollectIndicator('N');
		tr005s.setDelayIndicator('N');
		tr005s.setBreakdownReiterateIndicator('N');
		tr005s.setExtremeDefectIndicator('N');
		tr005s.setLackOfServiceIndicator('N');
		tr005s.setMaterialAmountIndicator('N');
		tr005s.setCompletedServiceIndicator('N');
		tr005s.setUpdatedEquipmentIndicator('N');
		tr005s.setMakeAppoitmentIndicator('N');
		//tr005s.setAppoitmentDate("0001-01-01");
		tr005s.setAnswerIndicator('N');
		tr005s.setBreakdownCommitmentDate(format.parse("2007-03-09-05.46.00.000"));
		tr005s.setBreakdownCloseDate(format.parse("0001-01-01-00.00.00.000"));
		tr005s.setSecondLineNumber("");
		tr005s.setBreakdownSource(' ');
		tr005s.setBreakdownCancelationCode("1");
		tr005s.setEmittingUser("CICSUSER");
		tr005s.setLastModificationUser("CICSUSER");
		tr005s.setEmmitingTime(format.parse("2007-03-07-10.07.15.553"));
		tr005s.setLastModificationTime(format.parse("2007-03-07-10.44.47.538"));
		tr005s.setInformationElementLength(100);
		tr005s.setProductServiceCode(0);
		tr005s.setInformationComponentLengthSynonym(0);
		tr005s.setStreetType("NOE");
		tr005s.setStreetName("VEREDA BOJACA - CHIA 1 - 03");
		tr005s.setStreetNumber("-");
//		tr005s.setExternalCityCode("11001000");
		tr005s.setCityCode("1100");
		tr005s.setDepartmentCode("1000");
		String s=XMLUtilities.marshall(tr005s);
		return s;
	}

	public static void main(String[] args) {
		
		System.out.println("---- Generando XML ----");
		try {
			String xml=generaTR005STest();
			System.out.print(xml);
			FileWriter fw=new FileWriter("./tr005s.xml");
			fw.write(xml);
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
