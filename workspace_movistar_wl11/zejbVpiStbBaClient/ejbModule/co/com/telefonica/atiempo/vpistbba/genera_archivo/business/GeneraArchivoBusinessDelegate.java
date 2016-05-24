/*
 * Created on Feb 12, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.genera_archivo.business;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.genera_archivo.ejb.sb.GeneraArchivoLocal;
import co.com.telefonica.atiempo.vpistbba.genera_archivo.ejb.sb.GeneraArchivoLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

public class GeneraArchivoBusinessDelegate implements GeneraArchivoBusinessInterface
{

	private GeneraArchivoLocal genearArchivoLocal;
	
	public GeneraArchivoBusinessDelegate() throws ATiempoAppEx
	{
		try
		{
			GeneraArchivoLocalHome gaHome = (GeneraArchivoLocalHome) HomeFactory.getHome(GeneraArchivoLocalHome.JNDI_NAME);
			genearArchivoLocal = gaHome.create();
		}
		catch (NamingException nex)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex)
		{
				new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}

	}

	public ArrayList getDatosPeticiones(ArrayList listaPeticiones)
	{
		return genearArchivoLocal.getDatosPeticiones( listaPeticiones );
	}

	public ArrayList getDatosODS(ArrayList peticiones)
	{
		return genearArchivoLocal.getDatosODS(peticiones);
	}

	public ArrayList getDatosODSOutBound(ArrayList listaIdPeticiones)
	{
		return genearArchivoLocal.getDatosODSOutBound(listaIdPeticiones);
	}

	public ArrayList getDatosODSChulo(HashMap peticiones)
	{
		return genearArchivoLocal.getDatosODSChulo(peticiones);
	}
}