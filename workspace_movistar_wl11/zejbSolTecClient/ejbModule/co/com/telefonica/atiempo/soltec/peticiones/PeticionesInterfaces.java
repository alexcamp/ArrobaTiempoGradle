package co.com.telefonica.atiempo.soltec.peticiones;

import java.util.ArrayList;

import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.soltec.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

public interface PeticionesInterfaces extends ComunInterfaces
{
	public InformacionEmpresaDTO obtenerInformacionEmpresa(Long idUsuario);
	public ArrayList buscarPorCliente(String rutCli, String rutDv);
	public ArrayList buscarAv(Long codAve,String rutCli, String rutDv, String idPC);
	public ArrayList buscarPorIDPC(String idPC);
	
//	CR-14657 Granite
	public boolean usaGranite(Long nroPeticion) throws ATiempoAppEx;
	public Regla_RetencionesLocal verificarRetencion(Long peticion)throws ATiempoAppEx ;
	public void marcarGranite(Long nroPeticion) throws ATiempoAppEx;

	public String recuperarParametroFromPropertiesBD(String codigo);
	
	public ArrayList recuperarListaMensajesConfModemsACS(Long petiNumero);
}
