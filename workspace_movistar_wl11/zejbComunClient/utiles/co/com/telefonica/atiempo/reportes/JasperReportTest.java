package co.com.telefonica.atiempo.reportes;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

 
public class JasperReportTest {
	public static net.sf.jasperreports.engine.JasperReport jasperReport;
	 
//	get the data to pass into jrxml
	public static List findReportData(){
	    //declare a list of object
	    List reports = new LinkedList();
	 
	    //declare 2nd ReportObject and set the fields
	    ReportObject report = new ReportObject();
	    report.setDia_entrega("29");
	    report.setMes_entrega("7");
	    report.setAnio_entrega("2013");
	    reports.add((Object) report);
	 
	    return reports;
	}
	 
	public static void main(String[] args) throws IOException {
		java.io.FileOutputStream file = new java.io.FileOutputStream("C:/prueba.pdf");
		GenerarReporteCRE reporte = new GenerarReporteCRE();
		reporte.setReporte(findReportData(), file);
		file.close();
	}
 
    
}