
function muestraMayusculas(el) {
	el.value = el.value.toUpperCase();
}

function muestraOcultaTabla(label,imagen, campovisible)
{
	el = document.getElementById(label);
	im = document.getElementById(imagen);
	if (el.style.display == '') {
		el.style.display = 'none';
		im.src = 'img/restaurar.gif';
		im.alt = "Mostrar";
		campovisible.value = 0;
	} else {
		el.style.display = '';
		im.src = 'img/minimizar.gif';
		im.alt = "Ocultar";
		campovisible.value = 1;
	}
}

function iniciaMuestraOcultaTabla(label, imagen, campovisible) {
	el = document.getElementById(label);
	im = document.getElementById(imagen);
	if (campovisible.value == 1) {
		el.style.display = '';
		im.src = 'img/minimizar.gif';
		im.alt = "Ocultar";
	}
}
