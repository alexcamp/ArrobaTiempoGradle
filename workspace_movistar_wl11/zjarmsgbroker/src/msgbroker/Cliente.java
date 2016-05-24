package msgbroker;

////////////////////////////////////////////////////////////////
// Cliente
//
// Descripci¢n: Clase que permite manejar socket en el cliente
//
// Autor : Daniel Verdugo Bosnich
////////////////////////////////////////////////////////////////

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class Cliente {
    protected int              port;         // Puerto de atenci¢n
    protected Socket           clt_socket;   // Socket de conexion
    protected DataInputStream  in;           // Entrada del Socket
    protected DataOutputStream out;          // Salida del Socket

    //
    // Crear el socket que espera la conecci¢n
    //
    public Cliente(String host, int port) throws IOException {
       if (port > 0) {
          this.port  = port;
          clt_socket = new Socket(host, port);
          in         = new DataInputStream(clt_socket.getInputStream());
          out        = new DataOutputStream(clt_socket.getOutputStream());
       }
       else {
          throw new IOException ("Puerto invalido");
       }
    }

    //
    // Leer un mensage desde el servidor.
    //
    public byte[] so_read(int len) throws java.io.IOException {
       byte buff[] = null;
       byte buffaux[] = null;
       int  lenRead;

       try {
          lenRead = in.readShort() - 2;
          if ( len < lenRead ) {
              buff = new byte[len];
              in.readFully(buff, 0, len);
          }
          else {
                  buff = new byte[lenRead];
                  in.readFully(buff, 0, lenRead);

                  if ( lenRead - len > 0) {
                      buffaux = new byte[lenRead - len];
                      in.readFully(buff, 0, lenRead - len);
                  }
          }
       }
       catch ( Exception e ) {
          so_close();
          throw new java.io.IOException("Se cerro conecci¢n");
       }

       return buff;
    }

    //
    // Escribir un mensaje al servidor
    //
    public void so_write(byte buff[]) throws java.io.IOException {
       try {
          out.writeShort(buff.length + 2);
          out.write(buff, 0, buff.length);
       }
       catch ( IOException e ) {
          so_close();
          throw e;
       }
    }





    public void so_close() {
       try { clt_socket.close(); } catch ( IOException e ) { }
    }

/**
 * Insert the method's description here.
 * Creation date: (31-05-2001 16:30:46)
 */
public void setTimeout(int timeout) throws IOException {
    clt_socket.setSoTimeout(timeout*1000);
}   //
    // Escribir un mensaje al servidor
    //
    public void so_fixwrite(byte buff[]) throws java.io.IOException {
       try {
          out.write(buff, 0, buff.length);
       }
       catch ( IOException e ) {
          so_close();
          throw e;
       }
    }/**
 * Insert the method's description here.
 * Creation date: (01-06-2001 10:07:45)
 * @return short
 */
public int so_readInt() throws IOException {
    return in.readInt();
}/**
 * Insert the method's description here.
 * Creation date: (01-06-2001 10:07:45)
 * @return short
 */
public long so_readLong() throws IOException {
    return in.readLong();
}/**
 * Insert the method's description here.
 * Creation date: (01-06-2001 10:07:45)
 * @return short
 */
public short so_readShort() throws IOException {
    return in.readShort();
}/**
 * Insert the method's description here.
 * Creation date: (01-06-2001 10:07:45)
 * @return short
 */
public String so_readString(int len) throws IOException {
    byte buff[];

    buff = new byte[len];
    in.readFully(buff,0,len);

    return new String(buff);
}   //
    // Escribir un mensaje al servidor
    //
    public void so_writeInt(int value) throws java.io.IOException {
       try {
          out.writeInt(value);
       }
       catch ( IOException e ) {
          so_close();
          throw e;
       }
    }   //
    // Escribir un mensaje al servidor
    //
    public void so_writeLong(long value) throws java.io.IOException {
       try {
          out.writeLong(value);
       }
       catch ( IOException e ) {
          so_close();
          throw e;
       }
    }   //
    // Escribir un mensaje al servidor
    //
    public void so_writeShort(short value) throws java.io.IOException {
       try {
          out.writeShort(value);
       }
       catch ( IOException e ) {
          so_close();
          throw e;
       }
    }
} // Class
