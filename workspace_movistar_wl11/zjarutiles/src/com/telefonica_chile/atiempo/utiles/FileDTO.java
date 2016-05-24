package com.telefonica_chile.atiempo.utiles;

import java.io.File;

public class FileDTO implements Comparable
{
	private File file;

	public int compareTo(Object o)
	{
		FileDTO otro=(FileDTO) o;
		if(this.file!=null && otro!=null)
		{
			try
			{
				String nombre1=this.file.getName();
				nombre1=nombre1.substring(nombre1.lastIndexOf("_")+1,nombre1.lastIndexOf(".")-1);
				
				String nombre2=otro.getFile().getName();
				nombre2=nombre2.substring(nombre2.lastIndexOf("_")+1,nombre2.lastIndexOf(".")-1);
				
				Fecha fecha1=new Fecha(nombre1,"ddMMyyyyHHmmss");
				Fecha fecha2=new Fecha(nombre2,"ddMMyyyyHHmmss");
				
				return fecha2.compareTo(fecha1);
				
			}
			catch(Exception e)
			{
				
			}
		}
		return 0;
	}
	/**
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
