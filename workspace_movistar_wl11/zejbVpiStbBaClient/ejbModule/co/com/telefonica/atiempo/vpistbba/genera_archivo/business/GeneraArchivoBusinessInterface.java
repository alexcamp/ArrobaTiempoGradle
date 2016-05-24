package co.com.telefonica.atiempo.vpistbba.genera_archivo.business;

import java.util.ArrayList;
import java.util.HashMap;


public interface  GeneraArchivoBusinessInterface
{
	public ArrayList getDatosPeticiones(ArrayList listaPeticiones);
	public ArrayList getDatosODS(ArrayList peticiones);
	public ArrayList getDatosODSChulo(HashMap peticiones);
	public ArrayList getDatosODSOutBound(ArrayList listaIdPeticiones);
}