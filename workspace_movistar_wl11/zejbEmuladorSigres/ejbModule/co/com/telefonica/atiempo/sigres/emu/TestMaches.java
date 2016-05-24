/*
 * Creado el 28/04/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TestMaches {

	public static void main(String[] args) {
		
		String oracion = "Esta es la prueba de IP FIJA para ver si funciona";
		boolean funciona = oracion.matches(".*IP FIJA.*");
		System.out.println(funciona);
	}
}
