/*
 * Created on 01-mar-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Address;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class DireccionConvertidor
{
	private DepartamentoLocalHome depHome;
	private LocalidadLocalHome locHome;
	
	public DireccionConvertidor() throws ATiempoAppEx
	{
		try
		{
			depHome=(DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
			locHome=(LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
		}
		catch(NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		}
	}
	
	public Address getDTO(Direccion_atisLocal entity) {
		
		Address dto = new Address();
//		dto.setCodigoExternoLocalidad(entity.getCod_ext_loc_cd());
//		dto.setCodigoSegunda(entity.getCodigoSegunda());
//		dto.setDepartamento(entity.getDepartamento());
//		dto.setDescripcionComplementosDos(entity.getDescripcionComplementosDos());
//		dto.setDescripcionComplementosUno(entity.getDescripcionComplementosUno());
//		//dto.setDireccionId(entity.getDireccionId());
//		dto.setLetraDosVia(entity.getLetraDosVia());
//		dto.setLetraDosViaDos(entity.getLetraDosViaDos());
//		dto.setLetraUnoVia(entity.getLetraUnoVia());
//		dto.setLetraUnoViaDos(entity.getLetraUnoViaDos());
//		dto.setLetraZona(entity.getLetraZona());
//		dto.setLetraZonaViaDos(entity.getLetraZonaViaDos());
//		dto.setLocalidad(entity.getLocalidad());
//		dto.setNombreCalle(entity.getNombreCalle());
//		dto.setNombreSublocalidad(entity.getNombreSublocalidad());
//		dto.setNumeroVia(entity.getNumeroVia());
//		dto.setNumeroCalle(entity.getNumeroCalle());
//		//dto.setPeticionId()
//		dto.setTerritorio(entity.getTerritorio());
//		dto.setTipoCalle(entity.getTipoCalle());
//		dto.setTipoVia(entity.getTipoVia());
//		dto.setTipoViaDos(entity.getTipoViaDos());
		return dto;
	}

	public void setEntity(Direccion_atisLocal entity, Address dto) throws ATiempoAppEx
	{
		try
		{
//			TODO: LCA arreglar campos.
			DepartamentoLocal departamentoLocal=depHome.findByPrimaryKey(new DepartamentoKey(dto.getDepartmentCode()));
			LocalidadLocal local=locHome.findByPrimaryKey(new LocalidadKey(dto.getCityCode()));
			entity.setCod_ext_loc_cd(dto.getExternalCityCode());
			entity.setCod_ter_cd(dto.getTerritory());
			entity.setDsc_cmp_pri_ds(dto.getComplementsDescription1());
			entity.setDsc_cmp_seg_ds(dto.getComplementsDescription2());
			entity.setDir_lt2_via_1(dto.getSecondPathCharacters());
			entity.setDir_lt2_via_2(dto.getSecondPathCharacters2());
			entity.setDir_lt1_via_1(dto.getFirstPathCharacters());
			entity.setDir_lt1_via_2(dto.getFirstPathCharacters2());
			entity.setDir_nro_lg1(dto.getPlaceNumber1());
			entity.setDir_nro_lg2(dto.getPlaceNumber2());
			entity.setDir_nro_lg3(dto.getPlaceNumber3());
			entity.setNom_cal_ds(dto.getStreetName());
			entity.setNom_slo_no(dto.getSubCityName());
			entity.setNum_cal_nu(dto.getStreetNumber());
			entity.setDir_nro_via_1(String.valueOf(dto.getPathNumber()));
			entity.setDir_nro_via_2(String.valueOf(dto.getPathNumber2()));
			entity.setTip_cal_atis_cd(dto.getStreetType());
			entity.setDir_tip_lg1(dto.getPlaceType1());
			entity.setDir_tip_lg2(dto.getPlaceType2());
			entity.setDir_tip_lg3(dto.getPlaceType2());
			entity.setDir_tip_via_1(dto.getPathType());
			entity.setDir_tip_via_2(dto.getPathType2());
			entity.setDir_zon_via_1(dto.getPathZone());
			entity.setDir_zon_via_2(dto.getPathZone2());
		}
		catch(FinderException finderException)
		{
			throw new ATiempoAppEx(ATiempoAppEx.FINDER,finderException);	
		}
	}
}
