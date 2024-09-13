package esisar.fr.tdm3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EnvoiTCP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EnvoiTCP client = new EnvoiTCP();
		client.execute();
	}
	private void execute() throws IOException
    {

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("localhost", 6969);
        socket.connect(adrDest);        
        // Envoi de la requete
        byte[] bufE = new String("Requête initiale").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoyé");

      
        // Fermeture de la socket
        socket.close();
        System.out.println("Client arrêté");
    }
}
