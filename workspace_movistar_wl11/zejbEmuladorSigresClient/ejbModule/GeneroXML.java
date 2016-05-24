import co.com.telefonica.atiempo.interfaces.atiempo.TR023S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;




/*
 * Created on Jun 19, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author 808962
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * 
 */
public class GeneroXML {

	// dada la TR024S me genera el string xml
	public static void main(String[] args) {
		
		TR024S tr024 = new TR024S();
		tr024.setAtisRequestNumber(22);
		tr024.setError(false);
		tr024.setId("hola");
		String tr024XML ="";
		try {
			tr024XML = XMLUtilities.marshall(tr024);
		} catch (ATiempoAppEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TR24 - Respuesta ERROR: " + tr024XML);
		
		TR023S tr023 = new TR023S();
		tr023.setAtisRequestNumber(22);
		tr023.setError(false);
		tr023.setId("hola");
		String tr023XML ="";
		try {
			tr023XML = XMLUtilities.marshall(tr023);
		} catch (ATiempoAppEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TR23 - Respuesta ERROR: " + tr023XML);
	}
}
