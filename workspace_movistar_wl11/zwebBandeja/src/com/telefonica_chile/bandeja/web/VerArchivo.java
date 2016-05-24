package com.telefonica_chile.bandeja.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class VerArchivo extends HttpServlet
{
	private static int MAX_AGE_IN_SECONDS=300;
	protected transient Logger log = LoggerFactory.getLogger(getClass());
		
	protected void processRequest(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, java.io.IOException 
	{
			OutputStream out=null;
			BufferedInputStream bufferedInputStream=null;
			BufferedOutputStream stream=null;
			try 
			{
				HttpSession session=req.getSession();
				if(session.getAttribute("controladorDeAplicacion")==null)
					return;
				String urlArchivoTrabajo=req.getParameter("url");
				String nombreArchivo=req.getParameter("nameArchi");
				if(nombreArchivo==null)
					nombreArchivo="Archivo";
				
				int ind=urlArchivoTrabajo.lastIndexOf("/");
				
//				log.debug("Pasa por aqui:"+req.getParameter("url"));
				res.setContentLength(getFileSize(urlArchivoTrabajo));
				res.setContentType("application/x-file-download");
				
				res.setHeader("Content-disposition","attachment; filename=" + nombreArchivo);
					
				res.setHeader("Cache-Control", "max-age=" + MAX_AGE_IN_SECONDS);
	
				ServletOutputStream outStream = res.getOutputStream();
	
				getFileBinary(urlArchivoTrabajo, outStream);
				 
			outStream.flush();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally 
			{
				if(stream!=null ) stream.close();
				if(bufferedInputStream!=null) bufferedInputStream.close();
				if(out!=null) out.close();
			}
		}
	
	public int getFileSize(String filename) throws FileNotFoundException, IOException
	{
		File file = new File(filename);
		RandomAccessFile fw = new RandomAccessFile(file, "rw"); 
		int len = (int) fw.length();
		fw.close();
//		log.debug("Len:"+len);
		return len;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{		
		processRequest(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		processRequest(req,resp);
	}
	
	private static final int tope=524288;//medio mega
	
	public void getFileBinary(String filename, OutputStream outStream) throws FileNotFoundException, IOException
	{
		File file = new File(filename);
		RandomAccessFile fw = new RandomAccessFile(file, "rw"); 
		byte chr = -1;
		int i=0;
		try
		{
			while (true)
			{
				try
				{
					i++;
					outStream.write(fw.readByte());
					if(i==tope)
					{
						outStream.flush();
						i=0;
					}
				}
				catch (EOFException e)
				{
					break;
				}
			} 	
		}
		catch(Exception e)
		{
			log.error(e);
		}
		finally
		{
			fw.close();	
		}
	}
}
