/*
 * Created on 26-12-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.navigogroup.inyectorsig.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import co.com.telefonica.atiempo.utiles.MQ;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TraspasoDesdeArchivo {

	//MQ para enviar el mensaje
	private MQ mq;

	//Setear correctamente el nombre del archivo procesado.
	private static final String ArchivoOrigen="D:\\Victor\\Projects\\AtiempoColombia\\ST\\Mensajeria\\FMCQM\\colas_FMCQM_20.12.07_04.00.00.txt";
	private static final String ArchivoDestinoST=ArchivoOrigen + "_ProcesadosST.txt";
	private static final String ArchivoDestinoVPI=ArchivoOrigen + "_ProcesadosVPI.txt";
	
	//Setear Cola de destino	
	private static final String colaDestinoST="WF_TO_STSTBBA_Q";
	private static final String colaDestinoVPI="WF_TO_VPISTBBA_Q";
	private static final String ipDestino="127.0.0.1";
	private static final int portDestino=1530;
	private static final String canalDestino="SYSTEM.ADMIN.SVRCONN";
	
	public void formateaYEnvia(){
		try{
			//cola para inyectar. Setear estos datos
			while(mq==null){
				mq=new MQ(ipDestino,portDestino,canalDestino);
			}
			
			//Apertura de Archivos
			File f = new File( ArchivoOrigen );
			File rST = new File( ArchivoDestinoST );
			File rVPI = new File( ArchivoDestinoVPI );
			
			boolean lee= true;
			BufferedReader entrada = new BufferedReader( new FileReader( f ) );
			BufferedWriter salidaST = new BufferedWriter( new FileWriter( rST ) );
			BufferedWriter salidaVPI = new BufferedWriter( new FileWriter( rVPI ) );
			
			//Lectura del archivo con los mensajes respaldados
			if ( f.exists() ){
				String mensaje = "";
				while(lee){
					String linea = entrada.readLine();
					if(linea==null)
					{
						lee=false;
					}
					else{
						if(linea.indexOf("</WfMessage>============================================================================")<0){
							linea=linea.replace('\n','\0');
							linea=linea.replace('\r','\0');
							mensaje=mensaje + linea;  
						}
						else{
							//Se inyecta el mensaje con la actividad
							mensaje=mensaje + "</WfMessage>";
							if(mensaje.indexOf("<ActivityImplInvoke>")>-1){
								if(mensaje.indexOf("inci_")>-1){ //es ST
									String mensajeTmp =mensaje.substring(mensaje.indexOf("inci_") + 5); 
									String inci = mensajeTmp.substring(0,mensajeTmp.indexOf("<"));
									mq.put(colaDestinoST,mensaje);
									mq.commit();
									salidaST.write(inci + "\n");

								}else{
									String mensajeTmp =mensaje.substring(mensaje.indexOf("<_PROCESS>") + 10);
									String pet = mensajeTmp.substring(0,mensajeTmp.indexOf("<"));
									mq.put(colaDestinoVPI,mensaje);
									mq.commit();
									salidaVPI.write(pet + "\n");
								}
							}
							mensaje = "";
						}
					}
				}
			}
			salidaST.close();
			salidaVPI.close();
		}catch(Exception e){

		}
	}
	
	public static void main(String[] args) {
		TraspasoDesdeArchivo t = new TraspasoDesdeArchivo();
		t.formateaYEnvia();
	}
}
