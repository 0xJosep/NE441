package esisasr.ne441.tdm01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ClientPingPong {
	public static void main(String[] args) throws Exception
	{
		ClientPingPong client = new ClientPingPong();
		client.execute();
	}
	
	public void execute() throws IOException
	{
		DatagramSocket socket = new DatagramSocket();
		
		InetSocketAddress adresse = new InetSocketAddress("localhost", 29000);
		for (int i=0; i<10; i++)
		{
			System.out.println("=========================");
			System.out.println("Début de la partie " + i);
			
			//Envoi du message JOUER
			byte[] jouer = new String("JOUER\n").getBytes();
			DatagramPacket packetJouer = new DatagramPacket(jouer, jouer.length, adresse);
			socket.send(packetJouer);
			System.out.println("Envoi d'un paquet UDP avec JOUER");
			
			//Réception de la réponse du serveur
			byte[] reponseServ = new byte[2048];
			DatagramPacket packetReponseServ = new DatagramPacket(reponseServ, reponseServ.length);
			socket.receive(packetReponseServ);
			String reponseServTexte = new String();
			System.out.println("Le serveur a répondu " + reponseServTexte);
			
			//Envoi du paquet client
			
			//Mise en place des conditions selon la réponse du serveur
			if (reponseServTexte.equals("PING"))
			{
				byte[] reponseClient = new String("PONG").getBytes();
				DatagramPacket packetReponseClient = new DatagramPacket(reponseClient, reponseClient.length);
				socket.send(packetReponseClient);
				System.out.println("Envoi d'un paquet UDP avec " + reponseClient);
			} else
			{
				byte[] reponseClient = new String("PING").getBytes();
				DatagramPacket packetReponseClient = new DatagramPacket(reponseClient, reponseClient.length);
				socket.send(packetReponseClient);
				System.out.println("Envoi d'un paquet UDP avec " + reponseClient);
			}
			System.out.println("Fin de la partie " + i);
		}
	}
}
