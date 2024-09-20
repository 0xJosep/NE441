package test_tp_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileInputStream;
/**
 * Serveur basique TCP
 */
public class ServeurTransfert
{

    public static void main(String[] args) throws Exception
    {
        ServeurTransfert serveurTCP = new ServeurTransfert();
        serveurTCP.execute();

    }



    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 5099
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(5099));


        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());

        // Emission d'un message en retour
        
        FileInputStream fis = new FileInputStream("/home/userir/file_serveur.txt");
        byte[] bufE = new byte[1000];
        OutputStream os = socketConnexion.getOutputStream();
        os.write(bufE);

        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }

}
