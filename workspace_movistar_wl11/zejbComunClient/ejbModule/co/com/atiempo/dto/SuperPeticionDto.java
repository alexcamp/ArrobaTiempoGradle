package co.com.atiempo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import co.com.telefonica.atiempo.interfaces.atiempo.Address;


public class SuperPeticionDto
{
	private PeticionAtisDTO peticionAtisDTO;
	private ArrayList listDireccion;
	
	public SuperPeticionDto()
	{
		peticionAtisDTO=new PeticionAtisDTO();
		listDireccion=new ArrayList();
	}
	
	
	public void addDireccionAtis(Address address, Collection listSubRequests, Integer codAgruSubNu)
	{
		boolean tieneladir=false;
		for(Iterator iterator=listDireccion.iterator();iterator.hasNext();)
  		{
  			SuperDirecPs direcPs=(SuperDirecPs) iterator.next();
  			if(esLaMismaDireccion(address,direcPs.getAddress()))
  			{
  				//revisaremos las agrupaciones
  				boolean agrupacion_ingresada=false;
  				for(Iterator iterator2=direcPs.getSuperAgrupPsPeticion().iterator();iterator2.hasNext();)
  				{
					SuperAgrupPsPeticion superAgrupPsPeticion=(SuperAgrupPsPeticion) iterator2.next();
					if(superAgrupPsPeticion.getCodAgruSubNu().intValue()==codAgruSubNu.intValue())
					{
						superAgrupPsPeticion.getListPsPeticion().addAll(listSubRequests);
						agrupacion_ingresada=true;
						break;
					}
  				}
  				if(!agrupacion_ingresada)
  				{
  					SuperAgrupPsPeticion superAgrupPsPeticion=new SuperAgrupPsPeticion();
  					superAgrupPsPeticion.setCodAgruSubNu(codAgruSubNu);
  					superAgrupPsPeticion.getListPsPeticion().addAll(listSubRequests);
  					direcPs.getSuperAgrupPsPeticion().add(superAgrupPsPeticion);
  				}
				tieneladir=true;
				break;
  			}
  		}
  		if(!tieneladir)
  		{
			SuperDirecPs nuevo=new SuperDirecPs();
			nuevo.setAddress(address);
			SuperAgrupPsPeticion agrupPsPeticion=new SuperAgrupPsPeticion();
			agrupPsPeticion.setCodAgruSubNu(codAgruSubNu);
			agrupPsPeticion.getListPsPeticion().addAll(listSubRequests);
			nuevo.getSuperAgrupPsPeticion().add(agrupPsPeticion);
			listDireccion.add(nuevo);
  		}
	}
	
	private boolean esLaMismaDireccion(Address d1, Address d2)
	{
		if(!sonIguales(d1.getStreetNumber(),d2.getStreetNumber()))
			return false;
		if(!sonIguales(d1.getDepartmentCode(),d2.getDepartmentCode()))
			return false;
		if(!sonIguales(d1.getCityCode(),d2.getCityCode()))
			return false;
		if(!sonIguales(d1.getStreetName(),d2.getStreetName()))
			return false;
		if(!sonIguales(d1.getPathType(),d2.getPathType()))
			return false;
		if(d1.getPathNumber()!=d2.getPathNumber())
			return false;
		if(!sonIguales(d1.getFirstPathCharacters(),d2.getFirstPathCharacters()))
			return false;
		if(!sonIguales(d1.getSecondPathCharacters(),d2.getSecondPathCharacters()))
			return false;
		if(!sonIguales(d1.getPathZone(),d2.getPathZone()))
			return false;
		if(d1.getPathNumber2()!=d2.getPathNumber2())
			return false;
		if(!sonIguales(d1.getFirstPathCharacters2(),d2.getFirstPathCharacters2()))
			return false;
		if(!sonIguales(d1.getSecondPathCharacters2(),d2.getSecondPathCharacters2()))
			return false;
		if(!sonIguales(d1.getPathZone2(),d2.getPathZone2()))
			return false;
		if(!sonIguales(d1.getStreetType(),d2.getStreetType()))
			return false;
		if(!sonIguales(d1.getPlaceType1(),d2.getPlaceType1()))
			return false;
		if(!sonIguales(d1.getPlaceNumber1(),d2.getPlaceNumber1()))
			return false;
		if(!sonIguales(d1.getPlaceType2(),d2.getPlaceType2()))
			return false;
		if(!sonIguales(d1.getPlaceNumber2(),d2.getPlaceNumber2()))
			return false;
		if(!sonIguales(d1.getPlaceType3(),d2.getPlaceType3()))
			return false;
		if(!sonIguales(d1.getPlaceNumber3(),d2.getPlaceNumber3()))
			return false;
		if(!sonIguales(d1.getComplementsDescription1(),d2.getComplementsDescription1()))
			return false;
		if(!sonIguales(d1.getComplementsDescription2(),d2.getComplementsDescription2()))
			return false;
		if(!sonIguales(d1.getSubCityName(),d2.getSubCityName()))
			return false;
		if(!sonIguales(d1.getExternalCityCode(),d2.getExternalCityCode()))
			return false;
		if(!sonIguales(d1.getTerritory(),d2.getTerritory()))
			return false;
		return true;
	}

	private boolean sonIguales(String uno,String dos)
	{
		if(uno!=null && dos!=null)
		{
			return uno.equals(dos);
		}
		else if(uno==null && dos==null)
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public PeticionAtisDTO getPeticionAtisDTO() {
		return peticionAtisDTO;
	}

	/**
	 * @param atisDTO
	 */
	public void setPeticionAtisDTO(PeticionAtisDTO atisDTO) {
		peticionAtisDTO = atisDTO;
	}

	/**
	 * @return
	 */
	public ArrayList getListDireccion() {
		return listDireccion;
	}

	/**
	 * @param list
	 */
	public void setListDireccion(ArrayList list) {
		listDireccion = list;
	}

}
