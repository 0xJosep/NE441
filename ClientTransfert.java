package test_tp_tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTransfert {
	public static void main(String []args) {
		
	}
	 private void execute() throws IOException
	    {
	        //
	        System.out.println("Demarrage du client ...");

	        //Creation de la socket
	        Socket socket = new Socket();

	        // Connexion au serveur 
	        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
	        socket.connect(adrDest);        
	        FileOutputStream fos = new FileOutputStream("/home/userir/file_client.txt");
	        OutputStream os = socket.getOutputStream();

	        byte[] buf = new byte[1000];
	        
	        fos.write(buf, 0, buf.length);
	        
	        fos.close();

	        // Fermeture de la socket
	        socket.close();
	        System.out.println("Arret du client .");
	    }
}
