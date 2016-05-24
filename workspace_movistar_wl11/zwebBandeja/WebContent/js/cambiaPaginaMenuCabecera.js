function PopWindow(combo, titulo, w, h)
{
	var newWin;
	var url = combo.options[combo.selectedIndex].value;
	var idMenuSelect = combo.options[combo.selectedIndex].id;

	if (newWin != null)
		newWin.close();

	newWin = window.open(url,titulo,'width='+w+',height='+h+',scrollbars=yes,resizable=yes,top=0,left=0');
//	newWin.resizeTo(w, h);
	newWin.focus();
}
function cambiaPaginaMenuCabecera( combo )
{
	var idMenuSelect = combo.options[combo.selectedIndex].id;
	if (idMenuSelect=="3" )
	{
		PopWindow(combo, 'BuscarPeticos', "820", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="5")
	{
		PopWindow(combo, 'ControlTecnico', "900", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="6")
	{
		PopWindow(combo, 'BackOffice', "900", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="7")
	{
		PopWindow(combo, 'BuscarPeticionST', "800", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="12")
	{
		PopWindow(combo, 'BandejaReporte', "800", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="14")
	{
		PopWindow(combo, 'BuscarAvPeticionST', "800", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="15")
	{
		PopWindow(combo, 'ReporteTerreno', "900", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="19")
	{
        
		PopWindow(combo, 'BuscadorHistoricoVPI', "900", "600");
		combo.options[0].selected = true;
	}
	else if(idMenuSelect=="20")
	{
		PopWindow(combo, 'BuscadorHistoricoST', "900", "600");
		combo.options[0].selected = true;
	}
	else {
		var url = combo.options[combo.selectedIndex].value;
		document.location.href = url;
	}
}