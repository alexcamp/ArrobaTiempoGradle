package msgbroker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Config {
    private Hashtable configHashtable;

    public Config( String fName ) throws IOException {
        BufferedReader br;
        String line, key, value;
        int pos;

        configHashtable = new Hashtable();

        br = new BufferedReader( new FileReader( fName ) );

        try {
            line = br.readLine();
            while( line != null ) {
                if( line.length() > 0 && !line.substring( 0, 1 ).equals( "'" ) ) {
                    pos = line.indexOf( '=' );
                    if( pos >= 0 ) {
                        key = line.substring( 0, pos );
                        value = line.substring( pos + 1 );
                        if( value.substring( 0, 1 ).equals( "$" ) ) {
                            value = value.substring( 1 );
                            value = System.getProperty( value );
                            if( value == null )
                                value = line.substring( pos + 1 );
                        }
                        configHashtable.put( key, value );
                    }
                }
                line = br.readLine();
            }
            br.close();
        }
        catch( IOException e ) {
            e.printStackTrace( System.err );
        }

    }

    public String getValue( String key ) {

        return( (String) configHashtable.get( key ) );

    }

	public int getIntValue(String key)
	{
		try
		{
			return Integer.parseInt((String)configHashtable.get(key));
		}
		catch(Exception e)
		{
			return 0;
		}
	}


}

/*
public static void main( String[] args )
    {
      BufferedReader br;
      Config myConfig;
      String line;

      if( args.length == 0 )
        {
          
          System.exit( 0 );
        }


      myConfig = new Config( args[0] + ".cfg" );

      if( myConfig == null ) {
         
         System.exit( 0 );
      }


      br = new BufferedReader( new InputStreamReader( System.in ) );

      try {
        line = br.readLine();
        if( line == null )
            System.exit( 0 );



      }
      catch( IOException e ) {
        e.printStackTrace( System.err );
        System.exit( 0 );
      }

      System.exit( 0 );
    }


*/

