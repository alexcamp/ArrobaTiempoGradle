package com.telefonica_chile.bandeja.dto;

import java.util.ArrayList;
import java.util.Iterator;

public class ResultMatrizDTO
{
	private ArrayList list;
	
	public ResultMatrizDTO()
	{
		list=new ArrayList();
	}
	
	public void addResultadoTec(String codTec,Integer idRango,int total)
	{
		ResultadoTecDTO tecDTO=new ResultadoTecDTO();
		tecDTO.setCodTecnico(codTec);
		tecDTO.setIdRango(idRango);
		tecDTO.setCantidad(total);
		list.add(tecDTO);	
	}
	
	public int getValorCodTecnicoEnRango(String coTec,Integer idRango)
	{
		for(Iterator iterator=list.iterator();iterator.hasNext();)
		{
			ResultadoTecDTO tecDTO=(ResultadoTecDTO) iterator.next();
			if(tecDTO.getCodTecnico().equals(coTec) && tecDTO.getIdRango().equals(idRango))
				return tecDTO.getCantidad();
		}
		return 0;
	}
}
