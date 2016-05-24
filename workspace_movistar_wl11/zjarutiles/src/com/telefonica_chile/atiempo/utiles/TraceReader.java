/*
 * Created on Nov 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 805538
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TraceReader {

	public static void main(String[] args) {
		TraceReader tR = new TraceReader();
		//String path = "C:/Documents and Settings/805538/My Documents/Varios/logs/20081126/logVpistbba-srv2.log.2008-11-21-09";
		String path = "C:/Documents and Settings/805538/My Documents/Varios/logs/20081126/vpi.3_18.txt";
		try {
			
			tR.read(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static final SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

	private void read(String path) throws IOException, ParseException {
		long start = System.currentTimeMillis();
		long interval = 0;
		ExtendedInfo currentMaxTime = null;
		String currOpPath = path + ".currentState.txt";
		FileWriter w = new FileWriter(currOpPath);
		PrintWriter printer = new PrintWriter(w);
		printer.println(CURRENT_STATE_COLUMNS);
		Map currentOp = new HashMap(300); 
		File f = new File(path);
		System.out.println("Reading file: " + f.getName());
		System.out.println("Writing current operations " + currOpPath);
		FileReader r = new FileReader(f);
		LineNumberReader lineR = new LineNumberReader(r);
		String l = null;
		int lines=0, maxCurrentOps = 0, cant;
		Object ret = null;
		String prefix = null;
		String[] cols = null, maxCols=null;
		String idTr, evento, ts, esOpPrincipal, opName;
		ExtendedInfo extInfo = null;
		Date date = null;
		boolean remove = false;
		while ((l = lineR.readLine()) != null) {
			lines++;
			cols = l.split("\\|");//, -1);
			if (cols.length < 10) {
				System.err.println("The line number " + lines + " has no @Trace data");
				continue;
			}
			esOpPrincipal = cols[COL_ES_OP_PRINCIPAL];
			if (esOpPrincipal.equals("false")) {
				continue;
			}
			ts = cols[COL_TIMESTAMP];
			ts = ts.trim();
			
			idTr = cols[COL_ID_TRANSACCION];
			//TODO: Si idTr es 1, entonces el server fue reiniciado.
			opName = cols[COL_NOMBRE_OP];
			evento = cols[COL_EVENTO];
			date = dFormat.parse(ts);
			if (evento.equals("END")) {
				remove = true;
			} else {
				remove = false;
				extInfo = new ExtendedInfo();
				extInfo.line = cols;
				extInfo.startTime = date.getTime();
				currentOp.put(idTr, extInfo);
			}
			cant = currentOp.size();
			if (cant > maxCurrentOps) {
				maxCurrentOps = cant;
				maxCols = cols;
				//System.out.println(cant + "|" + ts + "|" + opName);
			}
			// Imprime todos las current operations
			currentMaxTime = printCurrentState(printer, ts, date.getTime(), currentOp, currentMaxTime);
			if (lines % 1000 == 0) {
				//System.out.println(ts + "|" + cant);
				prefix = (l.length() >= 17 ? l.substring(0, 16) :l.substring(0, l.length()));
				interval = System.currentTimeMillis() - start;
				System.out.println(lines + " lines read.\t Current line: [" + prefix + "] interval=" + (interval/1000) + " seconds currentLineOp=[" + cols[7] + "] currentLineOps="+cant+" maxCurrentOps=" + maxCurrentOps + " ### maxOperation: " + currentMaxTime);
			}
//			if (lines > 3300) {
//				System.out.println("ts:   " + ts);
//				System.out.println("date: " + date);
//				break;
//			}
			if (remove) {
				ret = currentOp.remove(idTr);
				if (ret == null) {
					System.err.println("The START of transaction with id: " + idTr + " was not found timestamp=" + ts);
				}
			}

//			if (lines == 1) {
//				for (int i = 0; i < cols.length; i++) {
//					String s = cols[i];
//					System.out.println("col " + i + " " + s);
//				}
//			}
			
		}
		printer.close();
		w.close();
		lineR.close();
		r.close();
		interval = System.currentTimeMillis() - start;
		System.out.println(lines + " lines was read . " + " interval=" + (interval/1000) + " seconds maxCurrentOps=" + maxCurrentOps + " maxTS=" + maxCols[COL_TIMESTAMP] + " ### maxOperation: " + currentMaxTime);
	}
	private ExtendedInfo printCurrentState(PrintWriter p, String currentTS, long lcurrentTS, Map currentOp, ExtendedInfo currentMaxOp) {
		long maxTime = 0, acumTime;
		if (currentMaxOp != null) {
			maxTime = currentMaxOp.currentMaxTime;
		}
		StringBuffer buff = null;
		ExtendedInfo info = null;
		Iterator it = currentOp.keySet().iterator();
		int cantParalelas = currentOp.size(); 
		while (it.hasNext()) {
			String trId = (String) it.next();
			info = (ExtendedInfo) currentOp.get(trId);
			buff = new StringBuffer();
			buff.append(currentTS);
			buff.append("|");
			buff.append(info.line[COL_TIMESTAMP]);
			buff.append("|");
			buff.append(trId);
			buff.append("|");
			buff.append(info.line[COL_NOMBRE_OP]);
			buff.append("|");
			acumTime = lcurrentTS - info.startTime;
			buff.append(acumTime);
			buff.append("|");
			buff.append(info.getRange(lcurrentTS));
			buff.append("|");
			buff.append(cantParalelas);
			p.println(buff.toString());
			if (maxTime < acumTime) {
				maxTime = acumTime;
				currentMaxOp = info;
				currentMaxOp.currentMaxTime = maxTime;
			}
		}
		return currentMaxOp;
	}
	private static final String CURRENT_STATE_COLUMNS = "Current Time|Start Time|Tr. Id|Op. Name|Time (ms)|Time Range|Parellal (count)";
	private static final int COL_TIMESTAMP = 0;
	private static final int COL_ID_TRANSACCION = 5;
	private static final int COL_ID_OP = 6;
	private static final int COL_NOMBRE_OP = 7;
	private static final int COL_EVENTO = 8;
	private static final int COL_TIEMPO = 9;
	private static final int COL_USER_LOGIN = 10;
	private static final int COL_USER_IP = 11;
	private static final int COL_OP_PRINCIPAL = 20;
	private static final int COL_ES_OP_PRINCIPAL = 22;

	private static class ExtendedInfo {
		public String[] line = null;
		public long currentMaxTime;
		public long startTime;
		public final String getRange(long currentTime) {
			long diff = (currentTime - startTime) / 1000;
			if (diff < 10) {
				return "00 -> 09";
			} else if (diff < 60) {
				return "10 -> 59";
			} else {
				return "60 - etc";
			}
		}
		public String toString() {
			StringBuffer buff = new StringBuffer();
			buff.append("trID=");
			buff.append(line[COL_ID_TRANSACCION]);
			buff.append(" ");
			buff.append("ts=");
			buff.append(line[COL_TIMESTAMP]);
			buff.append(" ");
			buff.append("opName=[");
			buff.append(line[COL_NOMBRE_OP]);
			buff.append("] ");
			buff.append(" login='");
			buff.append(line[COL_USER_LOGIN]);
			buff.append("' ");
			buff.append(" IP=");
			buff.append(line[COL_USER_IP]);
			buff.append(" ");
			buff.append("currentMaxTime=");
			buff.append(currentMaxTime);
			return buff.toString();
		}
	}

}
