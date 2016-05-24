package com.telefonica_chile.bandeja.semaforos;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;


/**
 * @author jorge dijo
 */
public class Alertas {

	public static final int TIPO_COMPROMISO = 1;
	public static final int TIPO_ACTIVIDAD =2;
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private Calendar fechaFinal;//Fecha compromiso
	private Calendar fechaFinalSec;//Fecha compromiso secundatia
	private Calendar fechaInicio;//fecha inicio
	private TimeZone tz = TimeZone.getTimeZone("America/Santiago");
	private String rol;
	private Horario horario = new Horario();
	private String apId;
	private String codCat; //Codigo Categoria
	
	
	
	public void setCodCat(String codCat) {
		this.codCat = codCat;
	}
	
	public void setFechaInicio(Date fechaInicio) {
//		this.fechaInicio = fechaInicio;
		this.fechaInicio = Calendar.getInstance(tz);
		if ( fechaInicio==null) {
			this.fechaInicio = Calendar.getInstance(tz);
		} else {
			this.fechaInicio.setTime(fechaInicio);	
		}	
		this.fechaInicio.set(Calendar.SECOND, 0);
		this.fechaInicio.set(Calendar.MILLISECOND, 0);
	}
	
	public void setFechaFinalSec(Date fechaFinalSec) {
//		this.fechaFinalSec = fechaFinalSec;
		this.fechaFinalSec = Calendar.getInstance(tz);
		if ( fechaFinalSec==null) {
			this.fechaFinalSec = Calendar.getInstance(tz);
		} else {
			this.fechaFinalSec.setTime(fechaFinalSec);	
		}	
		this.fechaFinalSec.set(Calendar.SECOND, 0);
		this.fechaFinalSec.set(Calendar.MILLISECOND, 0);
	}
	
	public Alertas() throws AlertasException {
		setHorario();
	}

	public void setFechaFinal(Date fecha) {
		fechaFinal = Calendar.getInstance(tz);
		if ( fecha==null) {
			fechaFinal = Calendar.getInstance(tz);
		} else {
			fechaFinal.setTime(fecha);	
		}	
		fechaFinal.set(Calendar.SECOND, 0);
		fechaFinal.set(Calendar.MILLISECOND, 0);
	}		

	private Calendar getFechaActual() {
		Calendar fechaActual = Calendar.getInstance(tz);
		fechaActual.set(Calendar.SECOND, 0);
		fechaActual.set(Calendar.MILLISECOND, 0);
		return fechaActual;
	}

	public void setRol(String param) {
		rol = param;
		horario.setRol(param);
	}
	
	public void setApId(String string) {
		apId = string;
		horario.setApId(string);
	}

	public void setActividadSemaforo(String param) {
		horario.setActividad(param);
	}

	private void setHorario() throws AlertasException {
		horario.setHorario();
	}

	/**
	 * Si la Fecha Actual esta fuera del Horario de Trabajo, 
	 * se "avanza" la fecha actual a la siguiente hora de inicio
 	 */
	private Calendar calculaFechaActualValida() {

		Calendar fechaActual = getFechaActual();

		int ha = fechaActual.get(Calendar.HOUR_OF_DAY);
		int ma = fechaActual.get(Calendar.MINUTE);

		// Revisamos si es un dia Habil(Lunes a Viernes) o Sabado o Domingo.
		String tipo = "HAB";
		if (fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			tipo = "SAB";
		else if (fechaActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			tipo = "DOM";

		int hi = horario.getData(Horario.DT_HORARIO, "HH-INICIO-"+tipo);
		int mi = horario.getData(Horario.DT_HORARIO, "MM-INICIO-"+tipo);
		int hf = horario.getData(Horario.DT_HORARIO, "HH-TERMINO-"+tipo);
		int mf = horario.getData(Horario.DT_HORARIO, "MM-TERMINO-"+tipo);

		// Si la Fecha Actual esta fuera del Horario de Trabajo, 
		// Se "avanza" la fecha a actual a la siguiente hora de inicio
		if (ha < hi || (ha == hi && ma < mi)) {
			fechaActual.set(Calendar.HOUR_OF_DAY, hi);
			fechaActual.set(Calendar.MINUTE, mi);
		} else if (ha > hf || (ha == hf && ma > mf)) {
			fechaActual.set(Calendar.HOUR_OF_DAY, hi);
			fechaActual.set(Calendar.MINUTE, mi);
			fechaActual.add(Calendar.DAY_OF_MONTH, +1);
		}
		
		return fechaActual;
	}
	
	private String getTipoDia(Calendar f) {
		// Revisamos si es un dia Habil(Lunes a Viernes) o Sabado o Domingo.
		String tipo = "HAB";
		if (f.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			tipo = "SAB";
		else if (f.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			tipo = "DOM";

		return tipo;
	}

	public int getHorasDia(Calendar f) {
		String tipo = getTipoDia(f);
		int x = horario.getData(Horario.DT_HORARIO, "NUM-HH-" + tipo);

		return (x*60*60);

	}
/*	
	public class Hora {
			 private Calendar cal;
 
			 public Hora() {
			  TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
			  cal = Calendar.getInstance();
			  cal.add(Calendar.MILLISECOND, timeZone.getRawOffset());
			 }
 
			 public Date getHoraActual() {
			  return cal.getTime();
			 }
			}
*/	

	public Calendar getHoraTermino(Calendar f, int sumHora) {
		
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		String tipo = getTipoDia(f);

		int hf = horario.getData(Horario.DT_HORARIO, "HH-TERMINO-"+tipo);
		int mf = horario.getData(Horario.DT_HORARIO, "MM-TERMINO-"+tipo);

		f.set(Calendar.HOUR_OF_DAY, hf);
		f.set(Calendar.MINUTE, mf);
		f.set(Calendar.SECOND, 0);
		f.set(Calendar.MILLISECOND, 0);
		f.add(Calendar.HOUR_OF_DAY, - sumHora);
		//
		f.add(Calendar.MILLISECOND, timeZone.getRawOffset());
		
		return f;

	}
	
	public int getHorasHabiles(Calendar f) {
		String tipo = getTipoDia(f);
		
		int hf = horario.getData(Horario.DT_HORARIO, "HH-TERMINO-"+tipo);
		int mf = horario.getData(Horario.DT_HORARIO, "MM-TERMINO-"+tipo);

		Calendar fHelp = getFechaActual();
		fHelp.set(Calendar.HOUR_OF_DAY, hf);
		fHelp.set(Calendar.MINUTE, mf);
		
		if ( f.before(fHelp) )
			return getSegundosDiferencia(f, fHelp);
		else
			return 0;

		
	}
	
	/**
	 * Calcula la diferencia entre dos fechas.
	 * @return Un arreglo:  [diffHorasTotales, diffMinutosTotales, diffSegundos]
	 */
	private int[] getDiferencia(Calendar fIni, Calendar fFin) {
		int[] dias = calculaDiasDiferencia(fIni, fFin);
		int diasHabiles = dias[0];
		int sabados = dias[1];
		int domingos = dias[2];

		int seg = getSegundosDiferencia(fIni, fFin);
		int diffHoras = (seg / 3600);
		int diffSegundos = (seg % 3600);

		diffHoras = diffHoras - (24 - horario.getData(Horario.DT_HORARIO, "NUM-HH-HAB")) * diasHabiles;
		diffHoras = diffHoras - (24 - horario.getData(Horario.DT_HORARIO, "NUM-HH-SAB")) * sabados;
		diffHoras = diffHoras - (24 - horario.getData(Horario.DT_HORARIO, "NUM-HH-DOM")) * domingos;
		
		// Calculamos los minutos de diferencia totales.
		int diffMinutos = (diffHoras*60 + diffSegundos/60);
		
		// Los segundos solo van como el 'resto'
		diffSegundos = diffSegundos % 60;

		return new int[] {
			diffHoras,
			diffMinutos,
			diffSegundos
		};
	}

	/**
	 * Calcula los dias de diferencia entre dos fechas
	 * @return Un arreglo con 3 elementos: [diasHabiles, sabados, domingos]
	 */
	private int[] calculaDiasDiferencia(Calendar fIni, Calendar fFin) {
		int diasHabiles = fFin.get(Calendar.DAY_OF_YEAR) - fIni.get(Calendar.DAY_OF_YEAR);
		diasHabiles = (diasHabiles >= 0) ? diasHabiles : ((365 + diasHabiles) % 365);

		int domingos = fFin.get(Calendar.WEEK_OF_YEAR) - fIni.get(Calendar.WEEK_OF_YEAR);
		domingos = (domingos >= 0) ? domingos : ((52 + domingos) % 52);

		int sabados = domingos;

		if (fFin.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			sabados++;

		if (fIni.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			domingos++;

		if (diasHabiles > 0)
			diasHabiles = diasHabiles - (sabados + domingos);
		
		return new int[] {
			diasHabiles,
			sabados,
			domingos
		};
	}

	/**
	 * Calcula los segundos totales de diferencia entre 2 fechas.
	 */
	private int getSegundosDiferencia(Calendar fInicial, Calendar fFinal) {
		long diffMilisegundos = fFinal.getTime().getTime() - fInicial.getTime().getTime();
		
		return (int) (diffMilisegundos / 1000); 
	}

//	CR17031 - pcawen - modifico metodo para permitir el nuevo calculo de semaforos para soltec
	public String getSemaforoCompromiso() {
		horario.setTipoSemaforo(TIPO_COMPROMISO);
		Calendar fechaActual = calculaFechaActualValida();
		if(!this.apId.equals("2")){
			//Calculo semaforo compromiso normal
			int[] diff = getDiferencia(fechaActual, fechaFinal);
			int diffMinutosTotales = diff[1];
			int diffSegundosResto = diff[2];
	
			int la = horario.getData(Horario.DT_LIMITE, "LIM-AMARILLO");
			int lr = horario.getData(Horario.DT_LIMITE, "LIM-ROJO");
			
	//		if (log.isDebugEnabled())
	//			log.debug("Diff Minutos: " + diffMinutosTotales
	//				+ ", limites (amarillo, rojo, negro) = (" + la + ", " + lr + ")");
	
	
			if (diffMinutosTotales <= 0 && diffSegundosResto <= 0)			
				return "N";			
	
			if (diffMinutosTotales < lr || (diffMinutosTotales == lr && diffSegundosResto == 0))
				return "R";
	
			if (diffMinutosTotales < la || (diffMinutosTotales == la && diffSegundosResto == 0))
				return "A";
	
			return "V";
	
		}else{
			//calculo semaforo compromiso solTec
			fechaActual = Calendar.getInstance();
			Date fec = fechaActual.getTime();

			String colrSem  = "N";
			//Si la categoria esta definida el el propertie como negra retorno semaforo negro
			String codsCat = VpistbbaConfig.getVariable("COD_CATEGORIAS_SEMAFORO_NEGRO");
			if(this.codCat != null){
				if(codsCat != null){
					StringTokenizer tokens = new StringTokenizer(codsCat,",");
					while(tokens.hasMoreTokens()){
	//					if(this.codCat.equals(tokens.nextToken()))
						if(tokens.nextToken().equals(this.codCat))
							return colrSem;
					}
				}
			}else
				return colrSem;

			Date fin = null;
			if(fechaFinal != null)
				fin = fechaFinal.getTime();
			else
				return colrSem;

			Date inicio = null;
			if(fechaInicio != null)
				inicio = fechaInicio.getTime();
			else
				return colrSem;

			//DEBUG log.debug(inicio);
			Date dif = new Date((fin.getTime() - inicio.getTime())/2 + inicio.getTime());
			//DEBUG log.debug(dif);
			Date mitad1 = new Date((fechaFinal.getTime().getTime() - fechaInicio.getTime().getTime()) / 2 + fechaInicio.getTime().getTime());
			Date mitad2 = new Date((fechaFinalSec.getTime().getTime() - fechaFinal.getTime().getTime())/2 + fechaFinal.getTime().getTime());
			int dif1 = fechaActual.getTime().compareTo(mitad1);
			int dif2 = fechaActual.getTime().compareTo(mitad2);
			if(dif1 <= 0)//Verde
				colrSem = "V";
			else if(dif2 <= 0)//Amarillo
				colrSem = "A";
			else //Rojo
				colrSem = "R";//Rojo
			return colrSem;
		}
	}
	

	public String getSemaforoActividad() {
		horario.setTipoSemaforo(TIPO_ACTIVIDAD);
		Calendar fechaActual = calculaFechaActualValida();
		
		// Se mandan los parametros al reves porque
		// 	el sem. actividad _es_al_reves_por_definicion_
		int[] diff = getDiferencia(fechaFinal, fechaActual);
		int diffMinutosTotales = diff[1];
		
		int la = horario.getData(Horario.DT_LIMITE, "LIM-AMARILLO");
		int lr = horario.getData(Horario.DT_LIMITE, "LIM-ROJO");
		int ln = horario.getData(Horario.DT_LIMITE, "LIM-NEGRO");

//		if (log.isDebugEnabled())
//			log.debug("Diff Minutos: " + diffMinutosTotales
//					+ ", limites (amarillo, rojo, negro) = (" + la + ", " + lr + ", " + ln + ")");

		if (diffMinutosTotales >= ln)
			return "N";
		
		if (diffMinutosTotales >= lr)
			return "R";

		if (diffMinutosTotales >= la)
			return "A";

		return "V";
	}
	
}
