/*
 * Created on 28-11-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author lcaldera
 *
 */
public class ConsultaEquipoServicio extends ServicioBasico
{

    protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR026S tr026s= (TR026S) obj[0];
        EquipoSTDelegate eDelegate = new EquipoSTDelegate();
        eDelegate.actualizaEquipoPorUtilizar(tr026s);	
	}

    protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR026S tr026s = (TR026S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr026s;
		return obj;
	}

}
