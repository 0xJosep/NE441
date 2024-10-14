package tdm03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tdm03.Compteur;


public class ServeurTCP1 extends Thread
{
	
    int port;
    Compteur num;

    public ServeurTCP1(int port, Compteur num) {
        super();
        this.num = num;
        this.port = port;
    }
    public void run() {
        try {
               handleClient();

        } catch (IOException e) {
            System.out.println("ERREUR");
            e.printStackTrace();
        }
    }
 



	public void handleClient() throws IOException
    {
        
        //System.out.println("Demarrage du serveur ..." + port);
        //System.out.println("Attente de la connexion du client ...");
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(this.port));


        // Attente de la connexion d'un client
        while(true) {
        Socket socketConnexion = socketEcoute.accept();
        //System.out.println("Un client est connecté");

        byte[] bufR = new byte[2048];
        int n; 
        String message="";
        while((n=socketConnexion.getInputStream().read(bufR))!=-1) {
        	message+= new String(bufR, 0 , n);
        	if (message.length()==7){
        		break;
        	}
        	
        }
        if (message.equals("NUMERO?")) {
        	String response= "NUMERO=" + num.incrementer();
        	OutputStream os = socketConnexion.getOutputStream();
        	byte[] bufE = new String(response).getBytes();
        	os.write(bufE);
        }
        else {
        	String ERREUR_response= "VOUS AVEZ FAIT UNE ERREUR.";
        	byte[] bufERROR = new String(ERREUR_response).getBytes();
        	OutputStream os = socketConnexion.getOutputStream();
        	os.write(bufERROR);
        	 System.out.println("Message d'erreur envoyé");
        	
        }
        
        socketConnexion.close();
      
    }}
    public static void main(String[] args) throws InterruptedException
    {

    	List<ServeurTCP1> l = new ArrayList<>();
    	Compteur com = new Compteur();
    	for (int i = 21000; i < 23001; i++) {
			ServeurTCP1 s = new ServeurTCP1(i, com);
			l.add(s);
		}
    	
        for (ServeurTCP1 serveurTCP : l) {
			serveurTCP.start();  // Démarrer chaque serveur TCP dans un thread
			
		}
        for (ServeurTCP1 serveurTCP : l) {
			serveurTCP.join();  // Démarrer chaque serveur TCP dans un thread
			
		}
        
    }


}