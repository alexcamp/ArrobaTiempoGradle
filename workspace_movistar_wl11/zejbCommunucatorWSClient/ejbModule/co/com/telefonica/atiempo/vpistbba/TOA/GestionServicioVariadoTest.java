/*
 * Creado el 31/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.TOA;

import co.com.telefonica.atiempo.interfaces.atiempo.TR801E;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GestionServicioVariadoTest {

	public static void main(String[] args) {
		TR801E tr801e = new TR801E();
		tr801e.setApptNumber("Prueba");
		tr801e.setIdSchedule("IT");
		
		System.out.println(tr801e.toString());
		GestionServiciosVariado a = new GestionServiciosVariado();
		//a.servicioInBound(tr801e, new Long(3));
	}
}
