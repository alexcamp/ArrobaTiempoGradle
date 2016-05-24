package co.com.telefonica.atiempo.sigres.emu.tr;

/**
 * NoMessageDefFoundException
 * 
 * Exception creada para el Factory de procesos emulados.
 * 
 * @author Gonzalo Arreche
 *
 */
public class NoMessageDefFoundException extends Exception{
    public NoMessageDefFoundException(String mensajeError){
        super(mensajeError);
    }
}
