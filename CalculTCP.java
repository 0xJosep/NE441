package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CalculTCP {
	
	public static void main(String[] args) throws Exception
    {
        CalculTCP clientTCP = new CalculTCP();
        clientTCP.execute();                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
	
	
	private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("172.20.11.2", 7500);
        socket.connect(adrDest);        
        int[] nb = new int[20];
        String reponse= new String("");
        while(true) {
	        // Attente de la reponse 
	        byte[] bufR = new byte[2048];
	        InputStream is = socket.getInputStream();
	        int lenBufR = is.read(bufR);
	        
	        if (lenBufR!=-1)
	        {
	            reponse += new String(bufR, 0 , lenBufR );
	            System.out.println("===============");
	            System.out.println("message du serveur recue ="+reponse);
	        }
	        if (reponse.contains("Erreur")) {
	        	break;
	        }

	        // Envoi de la requete
	        int indPoint = 0;
	        int indEgal = 0;
	        int indice2 = 0;
	        int i =0;
	        while (indPoint != -1) {
	        	indPoint = reponse.indexOf('?');
	        	indEgal = reponse.indexOf('=');
	        	if (indPoint == -1) {
	        		break;
 		
	        	}
	        	String exp = new String(reponse.substring(indice2 , indEgal));
	        	nb[i] = Integer.parseInt(exp.substring(indice2 , exp.indexOf('+')));
	        	nb[i+1] = Integer.parseInt(exp.substring(exp.indexOf("+"), exp.length()));
	        	

	        	reponse = reponse.substring(indPoint+1, reponse.length());

	        	String res = Integer.toString(nb[i]+ nb[i+1]);
	        	res = res + ";";
	        	System.out.println("res "+res);
		        byte[] bufE = res.getBytes();
		        OutputStream os = socket.getOutputStream();
		        os.write(bufE);
		        System.out.println("Message envoye " );
		        i+=2;


	        } 
	        
	        	
	        


        
        }
        
        socket.close();
        

    }
    
	

}
