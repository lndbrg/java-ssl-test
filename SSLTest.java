/*
   * Makes simple connection to SSL enabled site.
   * Useful for debugging SSL Exceptions :)
   */
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class SSLTest {

	/**
	  * @param args
	  * @throws IOException
	  * @throws UnknownHostException
	  * @throws NumberFormatException
	  */
	public static void main(String[] args) throws NumberFormatException,
	       UnknownHostException, IOException {
		       String Me = new Object() { }.getClass().getEnclosingClass().getSimpleName();
		       String Host = System.getProperty("host");
		       String Debug = System.getProperty("debug");
		       Integer Port = Integer.getInteger("port");
		       if ( Port == null )
			       Port = 443;
		       if ( Debug != null ) {
			       System.setProperty("javax.net.debug","all");
                       }
		       if ( Host == null || ( Host != null && Host.length() == 0) ) {
			       System.err.println("java -Ddebug -Dhost=hostname -Dport=port " + Me);
			       System.err.println("port defaults to 443");
			       System.err.println("provide hostname without protocol");
			       System.err.println("example: java -Dhost=bitbucket.org " + Me);
			       System.err.println("example: java -Dhost=bitbucket.org -Djavax.net.ssl.keyStore=keystore.jks -Djavax.net.ssl.keyStorePassword=password " + Me);
			       System.exit(1);
                       }
              	       HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			       public boolean verify(String hostname, SSLSession session) {
				       System.out.println("Verifying: " + hostname);
				       return true;
			       }
		       });

		       Socket s = SSLSocketFactory.getDefault()
			       .createSocket(Host, Port);
		       System.out.println(Host + ":" + Port + " looks okay to java!");
		       OutputStream out = s.getOutputStream();
		       out.write("I love you!".getBytes("UTF8"));
		       out.close();
		       s.close();
	}
}
